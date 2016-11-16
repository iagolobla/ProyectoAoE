/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Mapa.Mapa;

/**
 *
 * @author iagolobla
 */
public class Recurso {

    public static final int DFLCANTIDAD = 100;

    private String tipo;  //El tipo de una instancia no puede cambiar
    private int cantidad;
    private String nombre;
    private Posicion pos;

    public Recurso(String tipe, String nombre, Posicion pos) {
        tipo = tipe;
        cantidad = DFLCANTIDAD;
        this.nombre = nombre;
        this.pos = new Posicion(pos);
        /*switch(tipe){
            case("bosque"):
                break;
            case("cantera"):
                break;
            case("arbusto"):
                break;
        }*/
    }

    public Recurso(String tipe, int cantidad, String nombre, Posicion pos) {
        tipo = tipe;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.pos = new Posicion(pos);
    }

    @Override
    public String toString() {
        String impresion = "";
        impresion += "Tipo: " + tipo + "\n";
        impresion += "Cantidad: " + cantidad + "\n";

        return impresion;
    }
    //GETTERS Y SETTERS
    public void setCantidad(int cantidad) {  //Este metodo es para el caso en el que un personaje sin suficiente
        if (cantidad > 0) {
            this.cantidad = cantidad;
        }                                   //capacidad de carga intenta recolectar un recurso, hay que mermar
    }                                       //la cantidad de ese recurso, es decir, solo quitar la capRecoleccion

    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo=tipo;
    }

    public int getCantidad() {
        return cantidad;
    }
    

    public Posicion getPos() {
        return new Posicion(pos);
    }

    public void setPos(Posicion p) {
        if (p.getX() >= 0 && p.getX() < Mapa.MAPAY && p.getY() >= 0 && p.getY() < Mapa.MAPAX) {
            pos = new Posicion(p);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int restarCantidad(int cantidadRestada, Mapa map) {
        if (cantidadRestada < 0) {
            System.out.println("Cantidad no valida!");
            return 0;
        }
        if (cantidadRestada < cantidad) {  //En caso de que le restemos al recurso una cantidad igual o inferior a su cantidad
            this.setCantidad(cantidad - cantidadRestada); //restamos y devolvemos la cantidad restada
            return cantidadRestada;
        } else if (cantidadRestada == cantidad) {  //En caso de que tengan la misma cantidad debe hacer ambas
            this.setCantidad(cantidad - cantidadRestada); //restamos y devolvemos la cantidad restada
            map.getCelda(new Posicion(this.getPos())).liberarCelda();   //Libera la celda del mapa
            System.out.println("Se ha agotado el recurso!");
            map.imprimir();
            return (cantidadRestada); //Devolvemos la cantidad obtenida
        } else {    //En caso de que intentemos sacar mas que lo que hay
            //Destruimos el recurso
            map.getCelda(new Posicion(this.getPos())).liberarCelda();   //Libera la celda del mapa
            System.out.println("Se ha agotado el recurso!");
            return (this.getCantidad()); //Devolvemos la cantidad obtenida

        }
    }

}
