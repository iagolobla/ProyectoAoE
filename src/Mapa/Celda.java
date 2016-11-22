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
import java.util.ArrayList;

/**
 *
 * @author javier
 */
public class Celda {

    private ArrayList<Personaje> personajes;
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
        personajes = new ArrayList<Personaje>();
        tipo = "Pradera";
        pos = new Posicion(posicion);
    }

    public Celda(String tipo, Posicion posicion, String Nombre) {
        if (posicion == null) {
            System.out.println("ERROR EN LA POSICION ->NULL");
            return;
        }
        personajes = new ArrayList<Personaje>();
        pos = new Posicion(posicion);
        this.tipo = "Pradera";
        switch (tipo) {
            case "soldado":
                personajes.add(new Personaje("soldado", Nombre, pos));
                visible = true;
                break;
            case "paisano":
                personajes.add(new Personaje("paisano", Nombre, pos));
                visible = true;
                break;
            case "ciudadela":
                edificio = new Edificio("ciudadela", pos, Nombre);
                visible = true;
                this.tipo = tipo;
                break;
            case "casa":
                edificio = new Edificio("casa", pos, Nombre);
                visible = true;
                this.tipo = tipo;
                break;
            case "cuartel":
                edificio = new Edificio("cuartel", pos, Nombre);
                visible = true;
                this.tipo = tipo;
                break;
            case "bosque":
                recurso = new Recurso("bosque", 150, Nombre, pos);   //De momento dejaremos 200 por defecto
                visible = false;
                this.tipo = tipo;
                break;
            case "cantera":
                recurso = new Recurso("cantera", 200, Nombre, pos);   //De momento dejaremos 200 por defecto
                visible = false;
                this.tipo = tipo;
                break;
            case "arbusto":
                recurso = new Recurso("arbusto", 200, Nombre, pos);   //De momento dejaremos 200 por defecto
                visible = false;
                this.tipo = tipo;
                break;
            default:
                System.out.println("Tipo mal introducido");

        }
    }

    public ArrayList<Personaje> getPersonajes() {// CAMBIAR EL NOMBRE DE ESTE GETTER
        if (personajes != null) {
            return personajes;
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

    public boolean isLibre() {
        if (tipo.equals("Pradera")) {
            return !(isPaisano() || isSoldado());
        }
        return false;
    }

    public boolean isPaisano() {
        int Tam = personajes.size();
        if (Tam == 0) {
            return false;
        }
        return personajes.get(Tam - 1).isPaisano();
    }

    public boolean isSoldado() {
        int Tam = personajes.size();
        if (Tam == 0) {
            return false;
        }
        return personajes.get(Tam - 1).isSoldado();
    }

    public void quitarPersonaje(Personaje P) {
        if (personajes.size() > 1) {
            personajes.remove(P);
        } else {
            liberarCelda();
        }
    }

    public void liberarCelda() {
        edificio = null;
        personajes = new ArrayList<Personaje>();
        recurso = null;
        tipo = "Pradera";
    }

    public void setPersonajes(Personaje personaje) {
        if (personaje != null) {
            this.personajes.add(personaje);
        } else {
            System.out.println("El personaje pasado es nulo!");
        }
    }

    public void setEdificio(Edificio edificio) {
        if (edificio != null) {
            this.edificio = edificio;
        } else {
            System.out.println("El edificio pasado es nulo!");
        }
    }

    public void setRecurso(Recurso recurso) {
        if (recurso != null) {
            this.recurso = recurso;
        } else {
            System.out.println("El recurso pasado es nulo!");
        }
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean estado) {
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
        if (p.getX() >= 0 && p.getX() < Mapa.MAPAY && p.getY() >= 0 && p.getY() < Mapa.MAPAX) {
            pos = new Posicion(p);
        } else {
            System.out.println("Posicion pasada fuera de los limites del mapa!");
        }
    }

    public boolean isVisible() {
        return visible;
    }

}
