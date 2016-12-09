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
public class Torre extends Edificio{
    public static final int SALUD = 500;
    public static final int CAPACIDAD = 3;
    public static final int ATAQUE = 100;
    public static final int DEFENSA = 100;

    public Torre(Posicion posicion, String Nombre, Civilizacion civilizacion) {
        super(posicion, Nombre, civilizacion);
        this.setSalud(SALUD);
        this.setCapPersonajes(CAPACIDAD);
        this.setAtaque(ATAQUE);
        this.setDefensa(DEFENSA);
        
    }
    
    @Override
    public String toString() {
        String impresion = super.toString();
        impresion+="Tipo de edificio: Torre";
        

        return impresion;
    }
}
