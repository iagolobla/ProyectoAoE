/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Juego.Civilizacion;
import Juego.Posicion;

/**
 *
 * @author iagolobla
 */
public class Arquero extends Soldado {

    public static final int SALUD = 80;
    public static final int ATAQUE = 40;
    public static final int ARMADURA = 10;

    public Arquero(String Nombre, Posicion posicion, Civilizacion civilizacion) {
        super(Nombre, posicion, civilizacion);
        this.setAtaque(ATAQUE);
        this.setArmadura(ARMADURA);
        this.setSalud(SALUD);

    }
}
