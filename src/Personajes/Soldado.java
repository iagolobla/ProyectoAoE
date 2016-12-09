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
public abstract class Soldado extends Personaje{
    
    public Soldado(String Nombre, Posicion posicion, Civilizacion civilizacion){
        super(Nombre, posicion, civilizacion);
    }
}
