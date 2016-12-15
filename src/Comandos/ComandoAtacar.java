/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Edificios.Edificio;
import Excepciones.ExcepcionAtacar;
import Excepciones.ExcepcionSintaxis;
import Juego.Celda;
import Juego.Mapa;
import static Juego.Principal.SHELL;
import Personajes.Personaje;
import java.util.ArrayList;

/**
 *
 * @author iagolobla
 */
public class ComandoAtacar implements Comando {

    private Mapa mapa;
    private String pto_cardinal;
    private String personaje;

    public ComandoAtacar(Mapa mapa, String pto_cardinal, String personaje) {
        this.mapa = mapa;
        this.pto_cardinal = pto_cardinal;
        this.personaje = personaje;
    }

    public void ejecutar() throws ExcepcionSintaxis, ExcepcionAtacar {
        Personaje p = mapa.getCivilizacion().getPersonajes().get(personaje);
        if (p == null) {
            throw new NullPointerException("El Personaje especificado no existe!");
        }
        if (mapa.checkCoords(p.mover(pto_cardinal))) {
            Celda cell = mapa.getCelda(p.mover(pto_cardinal));
            if (cell.isEdificio()) {
                Edificio ef = cell.getEdificio();
                p.atacar(ef);
                if (ef.getSalud() <= 0) {
                    SHELL.imprimir("El edificio " + ef.getNombre() + " ha sido fatalmente destruido!");
                    ArrayList<Personaje> pers = new ArrayList<Personaje>();
                    for (Personaje P : ef.getPersonajes().values()) {
                        pers.add(P);
                    }
                    for (Personaje P : pers) {
                        ef.getPersonajes().remove(P.getNombre());
                        cell.getPersonajes().remove(P);
                        //mapa.getCivilizaciones().remove(P.getNombre());
                        mapa.getCivilizaciones().get(P.getCivilizacion().getNombre()).getPersonajes().remove(P.getNombre());
                    }

                    mapa.getCivilizaciones().get(ef.getCivilizacion().getNombre()).getEdificios().remove(ef.getNombre());
                    cell.setEdificio(null);
                    if (!(mapa.getCivilizaciones().get(ef.getCivilizacion().getNombre()).civilizacionViva())) {
                        System.out.println("LA CIVILIZACION " + ef.getCivilizacion().getNombre() + " HA MUERTO");
                        mapa.getCivilizaciones().remove(ef.getCivilizacion().getNombre());
                        //mapa.borrarCivilizacion(ef.getCivilizacion().getNombre());
                    }
                }
            } else if (cell.getPersonajes().size() > 0) {
                p.atacar(cell.getPersonajes());
                Personaje person = cell.getPersonajes().get(0);
                if (person.getSalud() <= 0) {   //Si muere
                    System.out.println("El personaje " + person.getNombre() + " de la civilizacion " + person.getCivilizacion().getNombre() + " ha sufrido una horrible y dolorosa muerte!");

                    cell.getPersonajes().remove(person);
                    mapa.getCivilizaciones().get(person.getCivilizacion().getNombre()).getPersonajes().remove(person.getNombre());
                }
            } else {
                throw new ExcepcionAtacar("En esta celda no hay entidades a las que atacar");
            }
        }
    }
}
