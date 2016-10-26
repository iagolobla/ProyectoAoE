/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author iagolobla
 */
public class Recurso {
    private final int DFLCANTIDAD = 100;
    
    private final String tipo;  //El tipo de una instancia no puede cambiar
    private int cantidad;
    
    public Recurso(String tipe){
        tipo = tipe;
        cantidad = DFLCANTIDAD;
        /*switch(tipe){
            case("Bosque"):
                break;
            case("Cantera"):
                break;
            case("Arbusto"):
                break;
        }*/
    }
    public Recurso(String tipe, int cantidad){
        tipo = tipe;
        this.cantidad = cantidad;
    }
    
    public void setCantidad(int cantidad){  //Este metodo es para el caso en el que un personaje sin suficiente
        this.cantidad = cantidad;           //capacidad de carga intenta recolectar un recurso, hay que mermar
    }                                       //la cantidad de ese recurso, es decir, solo quitar la capRecoleccion
}
