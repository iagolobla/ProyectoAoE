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
import Excepciones.ExcepcionReparar;
import Juego.Civilizacion;
import Juego.Posicion;
import Recursos.Contenedor;
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
        throw new ExcepcionConstruir("Los gruposno construyen");
    }
    
    public void reparar(Edificio edificio) throws ExcepcionReparar{
        throw new ExcepcionReparar("Los grupos no pueden reparar");
    }
    
    public void recolectar(Contenedor contenedor) throws ExcepcionRecolectar{
        //comprobar que todos son paisanos, sino dar excepcion
        for(Personaje p:personajes){
            if(!(p instanceof Paisano)){
                throw new ExcepcionRecolectar("Este grupo no puede recolectar");
            }
        }
    }
    
    public void almacenar(Ciudadela ciudadela) throws ExcepcionAlmacenar{
        for(Personaje p:personajes){
            if(!(p instanceof Paisano)){
                throw new ExcepcionAlmacenar("Este grupo no puede recolectar");
            }
        }
        for(Personaje p:personajes){
            Paisano paisano=(Paisano) p;
            ciudadela.almacenar(paisano.getRecurso());
        }
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
