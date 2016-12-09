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
public class Cantera extends Contenedor implements Piedra{
    
    public static final int CANTIDAD = 150;

    public Cantera(String Nombre, Posicion posicion) {
        super(Nombre,posicion);
        this.setCantidad(CANTIDAD);
        
    }

    public Cantera(int cantidad, String Nombre, Posicion posicion) {
        super(cantidad,Nombre,posicion);
    }
}
