/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import Juego.Posicion;

/**
 *
 * @author iagolobla
 */
public class Bosque extends Contenedor{
    
    public static final int CANTIDAD = 150;

    public Bosque(String Nombre, Posicion posicion) {
        super(Nombre,posicion);
        this.setCantidad(CANTIDAD);
        
    }

    public Bosque(int cantidad, String Nombre, Posicion posicion) {
        super(cantidad,Nombre,posicion);
    }
}
