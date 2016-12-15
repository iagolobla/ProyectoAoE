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
import Excepciones.ExcepcionAgrupar;
import Excepciones.ExcepcionCelda;
import Recursos.Contenedor;
import Recursos.Pradera;

/**
 *
 * @author iagolobla
 */
public class Celda {

    private ArrayList<Personaje> personajes;
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
        this.posicion = new Posicion(posicion);
        this.contenedor = contenedor;

    }

    public void agrupar(Mapa mapa) throws ExcepcionAgrupar {
        if (personajes.size() > 1) {
            String Name = "grupo-" + (mapa.getCivilizacion().getCantidades()[6] + 1);
            mapa.getCivilizacion().getCantidades()[6]++;
            Grupo G = new Grupo(personajes, Name, new Posicion(posicion), mapa.getCivilizacion());
            
            for (Personaje person : personajes) {
                person.setG(G);
                person.setGrupo(true);
                if (person instanceof Grupo) {
                    Grupo group = (Grupo) person;
                    G.setNPersonajes(G.getNPersonajes() + group.getNPersonajes());
                } else {
                    G.setNPersonajes(G.getNPersonajes() + 1);
                }
            }
            personajes.removeAll(personajes);
            personajes.add(G);
            mapa.getCivilizacion().getPersonajes().put(Name, G);
            mapa.getCivilizacion().getGrupos().put(Name, G);
        } else {
            throw new ExcepcionAgrupar("Debe existir más de una entidad en la celda");
        }
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
        for (Personaje p : personajes) {
            if (p instanceof Grupo) {
                return true;
            }
        }
        return false;
    }

    public boolean isContenedor() {
        return (contenedor != null);
    }

    public boolean isEdificio() {
        return (edificio != null);
    }

    public Personaje getPersonaje() {    //Devuelve el personaje que está mas arriba para imprimirlo
        if (contenedor instanceof Pradera) {
            if (personajes.size() > 0) {
                return personajes.get(personajes.size() - 1); //Ultimo personaje que entró
            }
        }
        return null;
    }

    public void quitarPersonaje(Personaje P) throws ExcepcionCelda {  //Quita personajes de una celda, tiene en cuenta si hay edificio
        if (P == null) {
            throw new NullPointerException("Personaje especificado nulo!");
        }
        if (contenedor instanceof Pradera) {
            if (!this.isEdificio()) {
                if (personajes.size() > 0) {
                    personajes.remove(P);
                }
            } else if (edificio.getNPersonajes() > 0) {
                if (edificio.getPersonajes().containsKey(P.getNombre())) {
                    edificio.getPersonajes().remove(P.getNombre());
                    if(P instanceof Grupo){
                        edificio.setNPersonajes(edificio.getNPersonajes() - ((Grupo) P).getNPersonajes());
                    } else {
                    edificio.setNPersonajes(edificio.getNPersonajes() - 1);
                    }
                    edificio.setAtaque(edificio.getAtaque() - P.getAtaque());
                    edificio.setDefensa(edificio.getDefensa() - P.getArmadura());
                } else {
                    throw new ExcepcionCelda("El "+ P.getNombre() + " no se encuentra en " + edificio.getNombre());
                }
            } else {
                throw new ExcepcionCelda("El edificio " + edificio.getNombre() + " esta vacio!");

            }
        }
    }

    public void addPersonaje(Personaje personaje) throws ExcepcionCelda{ //Sirve para añadir personajes a una celda, tiene en cuenta si hay edificio
        if (personaje != null) {
            if (this.getContenedor().esTransitable()) {
                if (this.isEdificio()) {
                    Edificio ef = this.edificio;
                    if (personaje instanceof Grupo) {
                        int tamG = ((Grupo) personaje).getNPersonajes();
                        if ((ef.getCapPersonajes() - ef.getNPersonajes()) > tamG) {
                            ef.getPersonajes().put(personaje.getNombre(), personaje);
                            ef.setNPersonajes(ef.getNPersonajes() + tamG);
                        }
                    } else if ((ef.getCapPersonajes() - ef.getNPersonajes()) > 0) {
                        ef.getPersonajes().put(personaje.getNombre(), personaje);
                    }
                }
                this.personajes.add(personaje);
            } else {
                throw new ExcepcionCelda("La celda tiene que ser transitable!");

            }
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

}
