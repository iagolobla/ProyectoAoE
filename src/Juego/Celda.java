/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.util.ArrayList;
import java.util.HashMap;
import Personajes.Personaje;
import Personajes.Grupo;
import Edificios.Edificio;
import Recursos.Contenedor;
import Recursos.Pradera;

/**
 *
 * @author iagolobla
 */
public class Celda {

    private ArrayList<Personaje> personajes;
    private ArrayList<Grupo> grupos;
    private Edificio edificio;
    private Contenedor contenedor;
    private Posicion posicion;
    private HashMap<String, Boolean> visible;

    public Celda(Posicion posicion) {
        if (posicion == null) {
            System.out.println("ERROR EN LA POSICION ->NULL");
            return;
        }
        edificio = null;
        personajes = new ArrayList<Personaje>();
        visible = new HashMap<String, Boolean>();
        grupos = new ArrayList<Grupo>();
        this.posicion = new Posicion(posicion);
        contenedor = new Pradera(this.posicion);
    }

    public Celda(Contenedor contenedor, Posicion posicion) {
        if (posicion == null) {
            System.out.println("ERROR EN LA POSICION ->NULL");
            return;
        }
        if (visible == null) {
            visible = new HashMap<String, Boolean>();
        }
        edificio = null;
        personajes = new ArrayList<Personaje>();
        grupos = new ArrayList<Grupo>();
        this.posicion = new Posicion(posicion);
        this.contenedor = contenedor;

    }

    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public Contenedor getContenedor() {
        return contenedor;
    }

    public boolean isLibre() {
        if (contenedor instanceof Pradera && !this.isEdificio()) {
            if (personajes.size() == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isGrupo() {
        return (grupos.size() > 0);
    }

    public boolean isContenedor() {
        return (contenedor != null);
    }

    public boolean isEdificio() {
        return (edificio != null);
    }
    
    public Personaje getPersonaje(){    //Devuelve el personaje que está mas arriba para imprimirlo
        if(contenedor instanceof Pradera){
            if(personajes.size() > 0){
                return personajes.get(personajes.size()-1); //Ultimo personaje que entró
            }
        }
        return null;
    }

    public void quitarPersonaje(Personaje P) {
        if (contenedor instanceof Pradera) {
            if (!this.isEdificio()) {
                if (personajes.size() > 0) {
                    personajes.remove(P);
                }
            } else {
                if (edificio.getNPersonajes() > 0) {
                    if (edificio.getPersonajes().containsKey(P.getNombre())) {
                        edificio.getPersonajes().remove(P.getNombre());
                        edificio.setNPersonajes(edificio.getNPersonajes() - 1);
                    }
                }
            }
        }
    }

    public void addPersonaje(Personaje personaje) {
        if (personaje != null) {
            this.personajes.add(personaje);
        } else {
            System.out.println("El personaje pasado es nulo!");
        }
    }

    public void setEdificio(Edificio edificio) {    //Se puede poner el edificio a null(necesario)
        this.edificio = edificio;
    }

    public void setContenedor(Contenedor contenedor) {
        if (contenedor != null) {
            this.contenedor = contenedor;
        } else {
            System.out.println("El contenedor pasado es nulo!");
        }
    }

    public HashMap<String, Boolean> getVisible() {
        return visible;
    }

    public void setVisible(HashMap<String, Boolean> visible) {
        this.visible = visible;
    }

    public Posicion getPosicion() {
        return new Posicion(posicion);
    }

    public void setPosicion(Posicion p) {
        if (p.getX() >= 0 && p.getX() < Mapa.MAPAY && p.getY() >= 0 && p.getY() < Mapa.MAPAX) {
            posicion = new Posicion(p);
        } else {
            System.out.println("Posicion pasada fuera de los limites del mapa!");
        }
    }

    public boolean isVisible(Civilizacion civilizacion) {
        return visible.get(civilizacion.getNombre());
    }

    public void ponerVisible(Civilizacion civilizacion) {
        visible.replace(civilizacion.getNombre(), Boolean.TRUE);
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(ArrayList<Grupo> grupos) {
        this.grupos = grupos;
    }

    public void addGrupo(Grupo grupo) {
        if (grupo != null) {
            grupos.add(grupo);
        } else {
            System.out.println("Grupo pasado Nulo");
        }
    }

}
