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
        if (contenedor instanceof Pradera) {
            if (personajes.size() == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isPersonaje() {
        if (this.isPaisano() || this.isSoldado()) {
            for (Personaje P : personajes) {
                if (P.isGrupo()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isGrupo() {
        return (grupos.size() > 0);
    }

    public boolean isRecurso() {
        return (recurso != null);
    }

    public boolean isEdificio() {
        return (edificio != null);
    }

    public void quitarPersonaje(Personaje P) {
        if (this.isPaisano() || this.isSoldado()) {
            if (personajes.size() > 1) {
                personajes.remove(P);
            } else {
                liberarCelda();
            }
        } else if (this.isEdificio()) {
            if (edificio.getNPersonajes() > 0) {
                if (edificio.getPersonajes().containsKey(P.getNombre())) {
                    edificio.getPersonajes().remove(P.getNombre());
                    edificio.setNPersonajes(edificio.getNPersonajes() - 1);
                }
            }
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

    public void setEdificio(Edificio edificio) {    //Se puede poner el edificio a null(necesario)
        this.edificio = edificio;
    }

    public void setRecurso(Recurso recurso) {
        if (recurso != null) {
            this.recurso = recurso;
        } else {
            System.out.println("El recurso pasado es nulo!");
        }
    }

    public HashMap<String, Boolean> getVisible() {
        return visible;
    }

    public void setVisible(HashMap<String, Boolean> visible) {
        this.visible = visible;
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

    public boolean isVisible(String civilizacion) {
        return visible.get(civilizacion);
    }

    public void ponerVisible(String civilizacion) {
        visible.replace(civilizacion, Boolean.TRUE);
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(ArrayList<Grupo> grupos) {
        this.grupos = grupos;
    }

    public void setGrupo(Grupo grupo) {
        if (grupo != null) {
            grupos.add(grupo);
        } else {
            System.out.println("grupo pasado null");
        }
    }

}
}
