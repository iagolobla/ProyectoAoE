/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Edificios.Edificio;
import Excepciones.ExcepcionConstruir;
import Excepciones.ExcepcionCrear;
import Juego.Civilizacion;
import Juego.Posicion;
import java.util.ArrayList;

/**
 *
 * @author iagolobla
 */
public class Grupo extends Personaje{
    private ArrayList<Personaje> personajes;

    public Grupo(ArrayList<Personaje> person, String Nombre, Posicion posicion, Civilizacion civilizacion) {
        super(Nombre, posicion, civilizacion);
        personajes = new ArrayList<Personaje>();
        for (Personaje p : person) {
            p.setGrupo(true);
            personajes.add(p);
            this.setArmadura(this.getArmadura()+p.getArmadura());
            this.setSalud(this.getSalud()+p.getSalud());
            this.setAtaque(this.getAtaque()+p.getAtaque());

        }

    }
    
    public Edificio construir(String tipo_edificio) throws ExcepcionConstruir{
        throw new ExcepcionConstruir("Las torres no crean personajes");
    }

    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
    }
    
    public int capacidadMovimiento(){   //Los Grupos se mueven una casilla
        return 1;
    }
    
    public int getNPersonajes(){
        return personajes.size();
    }
    
}
