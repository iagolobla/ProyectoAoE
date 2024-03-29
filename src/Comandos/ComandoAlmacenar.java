/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Edificios.Ciudadela;
import Edificios.Edificio;
import Excepciones.ExcepcionAlmacenar;
import Excepciones.ExcepcionReparar;
import Excepciones.ExcepcionSintaxis;
import Juego.Celda;
import Juego.ConsolaNormal;
import Juego.Mapa;
import static Juego.Principal.SHELL;
import Personajes.Grupo;
import Personajes.Paisano;
import Personajes.Personaje;
import Recursos.Madera;
import Recursos.Piedra;
import Recursos.Recurso;

/**
 *
 * @author iagolobla
 */
public class ComandoAlmacenar implements Comando {

    private String personaje;
    private String pto_cardinal;
    private Mapa mapa;

    public ComandoAlmacenar(String personaje, String pto_cardinal, Mapa mapa) {
        this.personaje = personaje;
        this.pto_cardinal = pto_cardinal;
        this.mapa = mapa;

    }

    public void ejecutar() throws ExcepcionSintaxis, ExcepcionAlmacenar {
        Celda cell;
        Personaje p = mapa.getCivilizacion().getPersonajes().get(personaje);
        Recurso R = null;
        String CantidadAlmacenada = "";
        String TipoRecurso = "";
        if (p == null) {
            throw new NullPointerException("El Personaje especificado no existe!");
        }

        if (p instanceof Paisano) {
            R = ((Paisano) p).getRecurso();
            if (R != null) {
                CantidadAlmacenada = Integer.toString(R.getCantidad());
                TipoRecurso = R.getClass().getSimpleName();
            }
        } else if (p instanceof Grupo) {
            CantidadAlmacenada = "varias";
            TipoRecurso = "varios Recursos";
        }

        if (mapa.getCelda(p.mover(pto_cardinal)).isEdificio() && mapa.checkCoords(p.mover(pto_cardinal))) {
            cell = mapa.getCelda(p.mover(pto_cardinal));
            Edificio ef = cell.getEdificio();
            if (ef == null) {
                throw new NullPointerException("El Personaje especificado no existe!");
            }
            if (ef instanceof Ciudadela) {

                p.almacenar((Ciudadela) ef);
            } else {
                throw new ExcepcionAlmacenar("El edificio debe ser una ciudadela");
            }
            SHELL.imprimir("Se han almacenado " + CantidadAlmacenada + " uds. de " + TipoRecurso);
        }
    }
}
