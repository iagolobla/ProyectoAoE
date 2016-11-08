/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Entidades.Personaje;
import Entidades.Edificio;
import Entidades.Posicion;
import Entidades.Recurso;

/**
 *
 * @author javier
 */
public class Celda {

    private Personaje personaje;
    private Edificio edificio;
    private Recurso recurso;
    private Posicion pos;
    private String tipo;
    private boolean visible;

    public Celda(Posicion posicion) {
        if (posicion == null) {
            System.out.println("ERROR EN LA POSICION ->NULL");
            return;
        }
        tipo = "Pradera";
        pos = new Posicion(posicion);
    }

    public Celda(String tipo, Posicion posicion, String Nombre) {
        if (posicion == null) {
            System.out.println("ERROR EN LA POSICION ->NULL");
            return;
        }
        pos = new Posicion(posicion);
        this.tipo = tipo;
        switch (tipo) {
            case "Soldado":
                personaje = new Personaje("Soldado", Nombre, pos);
                visible = true;
                break;
            case "Paisano":
                personaje = new Personaje("Paisano", Nombre, pos);
                visible = true;
                break;
            case "Ciudadela":
                edificio = new Edificio("Ciudadela", pos, Nombre);
                break;
            case "Casa":
                edificio = new Edificio("Casa", pos, Nombre);
                break;
            case "Cuartel":
                edificio = new Edificio("Cuartel", pos, Nombre);
                break;
            case "Bosque":
                recurso = new Recurso("Bosque", 200);   //De momento dejaremos 200 por defecto
                break;
            case "Cantera":
                recurso = new Recurso("Cantera", 200);   //De momento dejaremos 200 por defecto
                break;
            case "Arbusto":
                recurso = new Recurso("Arbusto", 200);   //De momento dejaremos 200 por defecto
                break;
            default:
                System.out.println("Tipo mal introducido");

        }
    }

    public Personaje getPj() {
        if (personaje != null) {
            return personaje;
        }
        System.out.println("Celda Vacia!");
        return null;
    }

    public Edificio getEf() {
        if (edificio != null) {
            return edificio;
        }
        System.out.println("Celda Vacia!");
        return null;
    }

    public Recurso getRs() {
        if (recurso != null) {
            return recurso;
        }
        System.out.println("Celda Vacia!");
        return null;
    }

    public Posicion getPosicion() {
        return new Posicion(pos);
    }

    public void liberarCelda() {
        edificio = null;
        personaje = null;
        recurso = null;
        tipo = "Pradera";
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
        tipo = personaje.getTipo();
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }
    
    public boolean getVisible(){
        return visible;
    }
    
    public void setVisible(Boolean estado){
        visible = estado;
    }
    
    public String getTipo() {
        return tipo;
    }

}
