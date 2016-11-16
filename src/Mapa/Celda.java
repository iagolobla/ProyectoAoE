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
            case "soldado":
                personaje = new Personaje("soldado", Nombre, pos);
                visible = true;
                break;
            case "paisano":
                personaje = new Personaje("paisano", Nombre, pos);
                visible = true;
                break;
            case "ciudadela":
                edificio = new Edificio("ciudadela", pos, Nombre);
                visible = true;
                break;
            case "casa":
                edificio = new Edificio("casa", pos, Nombre);
                visible = true;
                break;
            case "cuartel":
                edificio = new Edificio("cuartel", pos, Nombre);
                visible = true;
                break;
            case "bosque":
                recurso = new Recurso("bosque", 200,Nombre,pos);   //De momento dejaremos 200 por defecto
                visible= false;
                break;
            case "cantera":
                recurso = new Recurso("cantera", 200,Nombre,pos);   //De momento dejaremos 200 por defecto
                visible= false;
                break;
            case "arbusto":
                recurso = new Recurso("arbusto", 200,Nombre,pos);   //De momento dejaremos 200 por defecto
                visible= false;
                break;
            default:
                System.out.println("Tipo mal introducido");

        }
    }

    public Personaje getPersonaje() {// CAMBIAR EL NOMBRE DE ESTE GETTER
        if (personaje != null) {
            return personaje;
        }
        System.out.println("Celda Vacia!");
        return null;
    }

    public Edificio getEdificio() {
        if (edificio != null) {
            return edificio;
        }
        System.out.println("Celda Vacia!");
        return null;
    }

    public Recurso getRecurso() {
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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    

    public Posicion getPos() {
        return new Posicion(pos);
    }

    public void setPos(Posicion p) {
        if (p.getX() >= 0 && p.getX() < Mapa.MAPAX && p.getY() >= 0 && p.getY() < Mapa.MAPAY) {
            pos = new Posicion(p);
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    

}
