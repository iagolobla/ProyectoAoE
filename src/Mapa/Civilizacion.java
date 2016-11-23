/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

/**
 *
 * @author javier
 */
import Entidades.Posicion;
import Entidades.Personaje;
import Entidades.Recurso;
import Entidades.Edificio;
import java.util.ArrayList;
import java.util.HashMap;

public class Civilizacion {

    private String nombre;
    private HashMap<String, Personaje> personajes;
    private HashMap<String, Edificio> edificios;

    private int[] cantidades;
    private String color;

    public Civilizacion(String nombre) {
        edificios = new HashMap<String, Edificio>();
        personajes = new HashMap<String, Personaje>();
        cantidades = new int[4];//guardara las cantidades de personajes, soldados,etc.
        /*
        0--Paisano
        1--Soldado
        2--Ciudadela
        3--Casa
        4--Cuartel
         */
        this.nombre = nombre;
        color = "rojo";
    }

    public HashMap<String, Personaje> getPersonajes() {
        return personajes;
    }

    public HashMap<String, Edificio> getEdificios() {
        return edificios;
    }



    public int[] getCantidades() {
        return cantidades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public void setPersonajes(HashMap<String, Personaje> personajes) {
        if (personajes != null) {
            this.personajes = new HashMap<>(personajes);
        } else {
            System.out.println("HashMap de personajes pasado nulo!");
        }
    }

    public void setEdificios(HashMap<String, Edificio> edificios) {
        if (edificios != null) {
            this.edificios = new HashMap<>(edificios);
        } else {
            System.out.println("HashMap de edificios pasado nulo!");
        }
    }



    public void setCantidades(int[] cantidades) {
        if (cantidades != null) {
            this.cantidades = cantidades;
        } else {
            System.out.println("Array de cantidades pasado nulo!");
        }
    }

}
