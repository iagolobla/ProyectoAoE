/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Edificios.Edificio;
import Excepciones.ExcepcionConstruir;
import Excepciones.ExcepcionCrear;
import Juego.Civilizacion;
import Juego.Posicion;

/**
 *
 * @author iagolobla
 */
public class Caballero extends Soldado{
    public static final int SALUD = 150;
    public static final int ATAQUE = 60;
    public static final int ARMADURA = 40;

    public Caballero(String Nombre, Posicion posicion, Civilizacion civilizacion) {
        super(Nombre, posicion, civilizacion);
        this.setAtaque(ATAQUE);
        this.setArmadura(ARMADURA);
        this.setSalud(SALUD);

    }
    
    public int capacidadMovimiento(){   //Los caballeros se mueven dos casillas
        return 2;
    }
    
    public Edificio construir(String tipo_edificio) throws ExcepcionConstruir{
        throw new ExcepcionConstruir("Las torres no crean personajes");
    }
    
}
