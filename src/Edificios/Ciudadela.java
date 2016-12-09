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
public class Ciudadela extends Edificio {

    public static final int SALUD = 400;
    public static final int CAPACIDAD = 10;

    public Ciudadela(Posicion posicion, String Nombre, Civilizacion civilizacion) {
        super(posicion, Nombre, civilizacion);
        this.setSalud(SALUD);
        this.setCapPersonajes(CAPACIDAD);
    }
    
    @Override
    public String toString() {
        String impresion = super.toString();
        impresion+="Tipo de edificio: Ciudadela";
        

        return impresion;
    }
}
