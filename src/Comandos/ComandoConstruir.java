/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Edificios.Ciudadela;
import Edificios.Edificio;
import Excepciones.ExcepcionConstruir;
import Excepciones.ExcepcionCrear;
import Excepciones.ExcepcionEntidadNoEncontrada;
import Excepciones.ExcepcionPosicionNoValida;
import Juego.Celda;
import Juego.Mapa;
import Personajes.Personaje;

/**
 *
 * @author iagolobla
 */
public class ComandoConstruir implements Comando {

    String personaje;
    String pto_cardinal;
    String tipo_edificio;
    Mapa mapa;

    public ComandoConstruir(String personaje, String pto_cardinal, String tipo_edificio, Mapa mapa) {
        this.personaje = personaje;
        this.pto_cardinal = pto_cardinal;
        this.tipo_edificio = tipo_edificio;
        this.mapa = mapa;
    }

    public void ejecutar() throws ExcepcionEntidadNoEncontrada,ExcepcionConstruir,ExcepcionCrear,ExceptionSintaxis{
        Celda cell;
        Personaje p;
        
        if (mapa.getCivilizacion().getPersonajes().containsKey(personaje)) {
            p = mapa.getCivilizacion().getPersonajes().get(personaje);
        } else {
            throw new ExcepcionEntidadNoEncontrada("No existe el edificio introducido");
        }
        Edificio ef=p.construir(tipo_edificio);
        
        
        if (mapa.checkCoords(p.mover("n")) && mapa.checkBuilding(p.mover("n"))) {
                cell = mapa.getCelda(p.mover("n"));
                p.setPosicion(p.mover("n"));
                cell.addPersonaje(p);
                mapa.getCivilizacion().getPersonajes().put(p.getNombre(), p);
                mapa.getCivilizacion().setComida(mapa.getCivilizacion().getComida() - 10);

            } else if (mapa.checkCoords(p.mover("s")) && mapa.checkBuilding(p.mover("s"))) {
                cell = mapa.getCelda(p.mover("s"));
                p.setPosicion(p.mover("s"));
                cell.addPersonaje(p);
                mapa.getCivilizacion().getPersonajes().put(p.getNombre(), p);
                mapa.getCivilizacion().setComida(mapa.getCivilizacion().getComida() - 10);
            } else if (mapa.checkCoords(p.mover("e")) && mapa.checkBuilding(p.mover("e"))) {
                cell = mapa.getCelda(p.mover("e"));
                p.setPosicion(p.mover("e"));
                cell.addPersonaje(p);
                mapa.getCivilizacion().getPersonajes().put(p.getNombre(), p);
                mapa.getCivilizacion().setComida(mapa.getCivilizacion().getComida() - 10);
            } else if (mapa.checkCoords(p.mover("o")) && mapa.checkBuilding(p.mover("o"))) {
                cell = mapa.getCelda(p.mover("o"));
                p.setPosicion(p.mover("o"));
                cell.addPersonaje(p);
                mapa.getCivilizacion().getPersonajes().put(p.getNombre(), p);
                mapa.getCivilizacion().setComida(mapa.getCivilizacion().getComida() - 10);
            } else {
                throw new ExcepcionPosicionNoValida("No se puede crear el personaje!");
            }
        
    }
}
