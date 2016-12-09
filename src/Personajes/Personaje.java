/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;


import Juego.Posicion;
import Juego.Civilizacion;
/**
 *
 * @author iagolobla
 */
public abstract class Personaje  {
    
    private Grupo G;
    private Civilizacion civilizacion;
    private int armadura;
    private int salud;
    private int ataque;
    private String Nombre;  //Aqui se necesita poner un nombre al personaje para usar eso como clave en el hashmap de personajes en el mapa
    private Posicion posicion;
    private boolean grupo;

 
    public Personaje(String Nombre, Posicion posicion, Civilizacion civilizacion){
        if (posicion == null) {
            System.out.println("Posicion pasada a Personaje nula!");
            return;
        }
        this.posicion = new Posicion(posicion);
        if (civilizacion == null) {
            System.out.println("Civilizacion pasada nula");
            return;
        }
        this.civilizacion = civilizacion;
        this.Nombre = Nombre;
        grupo = false;
        G = null;
    }
}
