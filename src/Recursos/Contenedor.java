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
    
    private int cantidad;
    private String Nombre;
    private Posicion posicion;
    
    public Contenedor(String Nombre, Posicion posicion){
        if (posicion == null) {
            System.out.println("Posicion pasada a Recurso nula!");
            return;
        }
        this.Nombre = Nombre;
        this.posicion = new Posicion(posicion);
    }
    
    public Contenedor(int cantidad, String Nombre, Posicion posicion) {
        if (posicion == null) {
            System.out.println("Posicion pasada a Recurso nula!");
            return;
        }
        this.Nombre = Nombre;
        this.posicion = new Posicion(posicion);
        this.cantidad=cantidad;
    }
    
    @Override
    public String toString() {
        String impresion = "";
        impresion += "Cantidad: " + cantidad + "\n";

        return impresion;
    }

    //GETTERS Y SETTERS
    public void setCantidad(int cantidad) {  //Este metodo es para el caso en el que un personaje sin suficiente
        if (cantidad >= 0) {                //capacidad de carga intenta recolectar un recurso, hay que mermar
            this.cantidad = cantidad;       //la cantidad de ese recurso, es decir, solo quitar la capRecoleccion
        } else {
            System.out.println("Cantidad Recurso introducida debe ser mayor que 0!");
        }
    }

    public int getCantidad() {
        return cantidad;
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
