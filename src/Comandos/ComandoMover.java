/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Personajes.Personaje;
import Juego.Posicion;
import Juego.Mapa;
import Juego.Celda;
import Excepciones.ExcepcionSintaxis;
import Edificios.Edificio;
import Excepciones.ExcepcionCelda;
import Excepciones.ExcepcionMover;
import Juego.ConsolaNormal;
import static Juego.Principal.SHELL;


/**
 *
 * @author iagolobla
 */
public class ComandoMover implements Comando {

    private String direccion;
    private String personaje;
    private Mapa mapa;
    

    public ComandoMover(String direccion, String personaje, Mapa mapa) {
        if (mapa != null) {
            this.mapa = mapa;
        }
        this.direccion = direccion;
        this.personaje = personaje;
        

    }

    public void ejecutar() throws ExcepcionSintaxis, ExcepcionMover, ExcepcionCelda {

        Personaje p = mapa.getCivilizacion().getPersonajes().get(personaje);
        if (p == null) {
            throw new NullPointerException("El Personaje especificado no existe!");
        }
        if(p.isGrupo()==false){
        for (int i = 0; i < p.capacidadMovimiento(); i++) {
            Posicion vieja = p.getPosicion();
            Posicion nueva = p.mover(direccion);
            Celda newcell = null;
            Celda cell = mapa.getCelda(vieja);
            if (mapa.checkCoords(nueva) && mapa.checkBuilding(nueva)) {
                newcell = mapa.getCelda(nueva);
            } else {
                throw new ExcepcionMover("No se puede mover en esa direccion!");
            }

            cell.quitarPersonaje(p);
            newcell.addPersonaje(p);
            p.setPosicion(nueva);
            SHELL.imprimir("El "+ p.getNombre()+" se ha movido a la posicion "+p.getPosicion());
        }
        mapa.actualizarVisibilidad();
        }else{
            throw new ExcepcionMover("La entidad pertenece a un grupo!");
        }
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPersonaje() {
        return personaje;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

}
