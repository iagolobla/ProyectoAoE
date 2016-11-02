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

    Personaje personaje;
    Edificio edificio;
    Recurso recurso;
    Posicion pos;
    String tipo;

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
                break;
            case "Paisano":
                personaje = new Personaje("Paisano", Nombre, pos);
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

    public Object getObj() {
        if (tipo == "Soldado" || tipo == "Paisano") {
            if (personaje != null) {
                return personaje;
            }
        } else if (tipo == "Ciudadela" || tipo == "Casa" || tipo == "Cuartel") {
            if (personaje != null) {
                return edificio;
            }
        } else if (tipo == "Bosque" || tipo == "Cantera" || tipo == "Arbusto") {
            if (personaje != null) {
                return recurso;
            }
        }
        System.out.println("Celda Vacia!");
        return null;
    }
    
    public Posicion getPosicion(){
        return new Posicion(pos);
    }
    
    public void liberarCelda(){
        edificio = null;
        personaje = null;
        recurso = null;
        tipo = "Pradera";
    }

}
