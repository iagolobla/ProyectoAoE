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
    private HashMap<String, Recurso> recursos;
    private int[] cantidades;
    private String color;

    public Civilizacion(String nombre) {
        edificios = new HashMap<String, Edificio>();
        personajes = new HashMap<String, Personaje>();
        recursos = new HashMap<String, Recurso>();
        cantidades = new int[8];//guardara las cantidades de personajes, soldados,etc.
        /*
        0--Paisano
        1--Soldado
        2--Ciudadela
        3--Casa
        4--Cuartel
        5--Bosque
        6--Cantera
        7--Arbusto
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

    public HashMap<String, Recurso> getRecursos() {
        return recursos;
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

    public void setRecursos(HashMap<String, Recurso> recursos) {
        if (recursos != null) {
            this.recursos = new HashMap<>(recursos);
        } else {
            System.out.println("HashMap de recursos pasado nulo!");
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
