/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import Juego.Posicion;
import Juego.Mapa;

/**
 *
 * @author iagolobla
 */
public abstract class Contenedor {

    Recurso recurso;
    private String Nombre;
    private Posicion posicion;

    public Contenedor(String Nombre, Posicion posicion, Recurso R) {
        if (posicion == null) {
            System.out.println("Posicion pasada a Recurso nula!");
            return;
        }
        this.Nombre = Nombre;
        this.posicion = new Posicion(posicion);
        recurso = R;
    }

    @Override
    public String toString() {
        String impresion = "";
        impresion += "Tipo: " + this.getClass().getSimpleName()+"\n";
        impresion += "Cantidad: " + recurso.getCantidad() + "\n";

        return impresion;
    }

    public Recurso procesar() {
        if(this instanceof Cantera){
            //Por cada acceso rebajar un 
        } else {
            
        }
        
        return recurso;
    }

    public boolean esTransitable() {
        if (this instanceof Pradera) {
            return true;
        }
        return false;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Posicion getPosicion() {
        return new Posicion(posicion);
    }

    public void setPosicion(Posicion p) {
        if (p.getX() >= 0 && p.getX() < Mapa.MAPAY && p.getY() >= 0 && p.getY() < Mapa.MAPAX) {
            posicion = new Posicion(p);
        } else {
            System.out.println("Posicion introducidad fuera de los limites del mapa!");
        }
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

}
