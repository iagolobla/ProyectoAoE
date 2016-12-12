/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.util.HashMap;
import Personajes.Personaje;
import Personajes.Grupo;
import Edificios.Edificio;
/**
 *
 * @author iagolobla
 */
public class Civilizacion {

    private String nombre;
    private HashMap<String, Personaje> personajes;
    private HashMap<String, Edificio> edificios;
    private HashMap<String, Grupo> grupos;

    private int[] cantidades;
    private String color;
    private int madera;
    private int piedra;
    private int comida;
    
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }
        final Civilizacion other = (Civilizacion) obj;
        if(other.getNombre() != this.getNombre()){
            return false;
        }
        return true;
    }

    public Civilizacion(String nombre) {
        edificios = new HashMap<String, Edificio>();
        personajes = new HashMap<String, Personaje>();
        grupos = new HashMap<String, Grupo>();
        madera = 100;
        piedra = 100;
        comida = 100;
        cantidades = new int[7];//guardara las cantidades de personajes, soldados,etc.
        /*
        0--Paisano
        1--Soldado
        2--Ciudadela
        3--Casa
        4--Cuartel
        5--Torre
        6--grupo
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

    /*public boolean civilizacionViva() {
        for (Edificio ef : this.getEdificios().values()) {
            if (ef.getTipo().equals("ciudadela")) {
                return true;
            }
        }
        return false;
    }*/

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

    public HashMap<String, Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(HashMap<String, Grupo> grupos) {
        if (grupos != null) {
            this.grupos = new HashMap<>(grupos);
        } else {
            System.out.println("HashMap de grupos pasado nulo!");
        }
    }

    public int getMadera() {
        return madera;
    }

    public void setMadera(int madera) {
        this.madera = madera;
    }

    public int getPiedra() {
        return piedra;
    }

    public void setPiedra(int piedra) {
        this.piedra = piedra;
    }

    public int getComida() {
        return comida;
    }

    public void setComida(int comida) {
        this.comida = comida;
    }
}
