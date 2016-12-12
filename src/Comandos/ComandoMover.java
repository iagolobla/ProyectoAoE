/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Personajes.Personaje;
import Juego.Posicion;
import Juego.Mapa;
import Juego.Celda;
import Edificios.Edificio;
/**
 *
 * @author iagolobla
 */
public class ComandoMover implements Comando {

    String direccion;
    Personaje personaje;
    Mapa mapa;

    public ComandoMover(String direccion, Personaje personaje, Mapa mapa) {
        if (mapa != null) {
            this.mapa = mapa;
        }
        this.direccion = direccion;
        if (personaje != null) {
            this.personaje = personaje;
        }

    }

    public void ejecutar() {
        Posicion vieja = personaje.getPosicion();
        Posicion nueva = personaje.mover(direccion);
        Celda newcell=null;
        Celda cell = mapa.getCelda(vieja);
        if (mapa.checkCoords(nueva) && mapa.checkBuilding(nueva) ) {
            newcell=mapa.getCelda(nueva);
        }else{
            return;//excepcion
        }
        cell.quitarPersonaje(personaje);
        newcell.addPersonaje(personaje);
        personaje.setPosicion(nueva);
        mapa.actualizarVisibilidad();
    }
}
