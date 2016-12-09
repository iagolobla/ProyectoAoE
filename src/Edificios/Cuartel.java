/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Edificios;

import Juego.Civilizacion;
import Juego.Posicion;

/**
 *
 * @author iagolobla
 */
public class Cuartel extends Edificio{
    public static final int SALUD = 300;
    public static final int CAPACIDAD = 15;

    public Cuartel(Posicion posicion, String Nombre, Civilizacion civilizacion) {
        super(posicion, Nombre, civilizacion);
        this.setSalud(SALUD);
        this.setCapPersonajes(CAPACIDAD);
    }
    
    @Override
    public String toString() {
        String impresion = super.toString();
        impresion+="Tipo de edificio: Cuartel";
        

        return impresion;
    }
}
