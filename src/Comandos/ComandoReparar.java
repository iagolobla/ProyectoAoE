/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Edificios.Ciudadela;
import Edificios.Edificio;
import Excepciones.ExcepcionCrear;
import Excepciones.ExcepcionReparar;
import Excepciones.ExcepcionSintaxis;
import Juego.Celda;
import Juego.ConsolaNormal;
import Juego.Mapa;
import Personajes.Personaje;

/**
 *
 * @author iagolobla
 */
public class ComandoReparar implements Comando {

    private String direccion;
    private String personaje;
    private Mapa mapa;
    private ConsolaNormal Shell;

    public ComandoReparar(String direccion, String personaje, Mapa mapa, ConsolaNormal Shell) {
        this.direccion = direccion;
        this.personaje = personaje;
        this.mapa = mapa;
        this.Shell = Shell;
    }

    public void ejecutar() throws ExcepcionSintaxis, ExcepcionReparar {
        Celda cell;
        Personaje p = null;
        if (mapa.getCivilizacion().getPersonajes().containsKey(personaje)) {
            p = mapa.getCivilizacion().getPersonajes().get(personaje);
        } else {
            throw new ExcepcionReparar("No existe el paisano");
        }
        if (mapa.getCelda(p.mover(direccion)).isEdificio() && mapa.checkCoords(p.mover(direccion))) {
            cell = mapa.getCelda(p.mover(direccion));
            Edificio ef = cell.getEdificio();
            if(ef==null){
                throw new ExcepcionReparar("No hay un edificio aqui");
            }
            int vidarec = ef.getSalud();
            p.reparar(ef);
            Shell.imprimir("El edificio "+ ef.getNombre()+ " ahora esta full-vida");
        }
    }
}
