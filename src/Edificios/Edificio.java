/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Edificios;

import java.util.HashMap;
import Juego.Civilizacion;
import Juego.Posicion;
import Personajes.Personaje;
import Juego.Mapa;
import Recursos.Recurso;

/**
 *
 * @author iagolobla
 */
public abstract class Edificio {

    private HashMap<String, Personaje> Personajes;
    private int salud;
    private Posicion posicion;
    private String Nombre;
    private Civilizacion civilizacion;
    private int capPersonajes;  //Establece cuantos personajes pueden entrar a defender un edificio
    private int NPersonajes;
    private int ataque;
    private int defensa;

    public Edificio(Posicion posicion, String Nombre, Civilizacion civilizacion) {
        if (posicion == null) {
            System.out.println("Posicion pasada a Edificio nula!");
            return;
        }
        this.posicion = new Posicion(posicion);
        if (civilizacion == null) {
            System.out.println("Civilizacion pasada nula");
            return;
        }
        this.civilizacion = civilizacion;
        this.Nombre = Nombre;
        Personajes = new HashMap<String, Personaje>();
        NPersonajes = 0;
        ataque = 0;
        defensa = 0;
    }
    
    @Override
    public String toString() {
        String impresion = "";
        if (Personajes.size() > 0) {
            impresion += "Personajes dentro del edificio:\n";
            for (Personaje p : Personajes.values()) {
                impresion += "\t" + p.getNombre() + "\n";
            }
        }
        impresion += "Salud: " + salud + "\n";
        impresion += "Ataque: " + ataque + "\n";
        impresion += "Defensa: " + defensa + "\n";
        impresion += "Posicion: " + posicion + "\n";
        

        return impresion;
    }
    
    
    public abstract void almacenar(Recurso recurso);
    
    public abstract Personaje crear(String tipo_personaje);
    
    public abstract void atacar(Personaje[] personajes);

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        if (salud >= 0) {
            this.salud = salud;
        } else {
            System.out.println("Salud introducida debe ser mayor que 0!");
        }
    }

    public int getCapPersonajes() {

        return capPersonajes;
    }

    public void setCapPersonajes(int cap) {
        if (cap >= 0);
        capPersonajes = cap;
    }

    public int getNPersonajes() {
        return NPersonajes;
    }

    public void setNPersonajes(int NPersonajes) {
        if (NPersonajes >= 0) {
            this.NPersonajes = NPersonajes;
        }
    }

    public HashMap<String, Personaje> getPersonajes() {
        return Personajes;
    }

    public void setPersonajes(HashMap<String, Personaje> Personajes) {
        this.Personajes = Personajes;
    }

    public Civilizacion getCivilizacion() {
        return civilizacion;
    }

    public void setCivilizacion(Civilizacion civilizacion) {
        this.civilizacion = civilizacion;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        if (ataque >= 0) {
            this.ataque = ataque;
        }
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        if (defensa >= 0) {
            this.defensa = defensa;
        }
    }

    public String getNombre() {
        return new String(Nombre);
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Posicion getPosicion() {
        return new Posicion(posicion);
    }

    public void setPosicion(Posicion p) {
        if (p != null) {
            if (p.getX() >= 0 && p.getX() < Mapa.MAPAY && p.getY() >= 0 && p.getY() < Mapa.MAPAX) {
                posicion = new Posicion(p);
            }
        }
    }
}
