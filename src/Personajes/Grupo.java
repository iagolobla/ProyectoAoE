/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Edificios.Ciudadela;
import Edificios.Edificio;
import Excepciones.ExcepcionAlmacenar;
import Excepciones.ExcepcionConstruir;
import Excepciones.ExcepcionCrear;
import Excepciones.ExcepcionRecolectar;
import Excepciones.ExcepcionRecurso;
import Excepciones.ExcepcionReparar;
import Juego.Celda;
import Juego.Civilizacion;
import Juego.Mapa;
import Juego.Posicion;
import Recursos.Contenedor;
import java.util.ArrayList;

/**
 *
 * @author iagolobla
 */
public class Grupo extends Personaje {

    private ArrayList<Personaje> personajes;
    private int NPersonajes;

    public Grupo(ArrayList<Personaje> person, String Nombre, Posicion posicion, Civilizacion civilizacion) {
        super(Nombre, posicion, civilizacion);
        personajes = new ArrayList<Personaje>();
        NPersonajes=0;
        for (Personaje p : person) {
            p.setGrupo(true);
            personajes.add(p);
            this.setArmadura(this.getArmadura() + p.getArmadura());
            this.setSalud(this.getSalud() + p.getSalud());
            this.setAtaque(this.getAtaque() + p.getAtaque());

        }

    }

    @Override
    public String toString() {
        String impresion = super.toString();
        impresion += "El grupo tiene los siguientes personajes: \n";
        for (Personaje p : personajes) {
            impresion += p.getNombre() + "\n";
        }
        return impresion;
    }

    public Edificio construir(String tipo_edificio) throws ExcepcionConstruir {
        throw new ExcepcionConstruir("Los gruposno construyen");
    }

    public void reparar(Edificio edificio) throws ExcepcionReparar {
        throw new ExcepcionReparar("Los grupos no pueden reparar");
    }

    public void recolectar(Contenedor contenedor) throws ExcepcionRecolectar, ExcepcionRecurso {
        //comprobar que todos son paisanos, sino dar excepcion
        for (Personaje p : personajes) {
            if (!(p instanceof Paisano)) {
                throw new ExcepcionRecolectar("Este grupo no puede recolectar");
            }
        }
        for (Personaje p : personajes) {
            Paisano paisano = (Paisano) p;
            p.recolectar(contenedor);
        }
    }

    public void almacenar(Ciudadela ciudadela) throws ExcepcionAlmacenar {
        for (Personaje p : personajes) {
            if (!(p instanceof Paisano)) {
                throw new ExcepcionAlmacenar("Este grupo no puede almacenar");
            }
        }
        for (Personaje p : personajes) {
            Paisano paisano = (Paisano) p;
            ciudadela.almacenar(paisano.getRecurso());
        }
    }

    public void desagrupar(Mapa mapa) {
        mapa.getCivilizacion().getGrupos().remove(this.getNombre());
        Celda cell = mapa.getCelda(this.getPosicion());
        for (Personaje p : personajes) {
            p.setGrupo(false);
            p.setPosicion(new Posicion(this.getPosicion()));
            cell.getPersonajes().add(p);
        }
        cell.getPersonajes().remove(this);
        mapa.getCivilizacion().getPersonajes().remove(this.getNombre());
    }

    public void desligar(Personaje p, Mapa mapa) {
        if (personajes.size() > 2) {
            
            Celda cell = mapa.getCelda(this.getPosicion());
            p.setGrupo(false);
            p.setPosicion(new Posicion(this.getPosicion()));
            cell.getPersonajes().add(p);
            personajes.remove(p);
        } else {
            this.desagrupar(mapa);
        }
    }
    
    public   double danhoAtaque(Edificio edificio) {
        return this.getAtaque() - edificio.getDefensa();
    }
    
    public double danhoAtaque(Personaje personaje) {
        return this.getAtaque() - personaje.getArmadura();
    }

    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
    }


    public int getNPersonajes() {
        return NPersonajes;
    }

    public void setNPersonajes(int NPersonajes) {
        this.NPersonajes = NPersonajes;
    }
    

}
