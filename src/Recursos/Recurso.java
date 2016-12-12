/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

/**
 *
 * @author javier
 */
public abstract class Recurso {
    private int cantidad;
    
    public Recurso(int cantidad){
        this.cantidad=cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {  //Este metodo es para el caso en el que un personaje sin suficiente
        if (cantidad >= 0) {                //capacidad de carga intenta recolectar un recurso, hay que mermar
            this.cantidad = cantidad;       //la cantidad de ese recurso, es decir, solo quitar la capRecoleccion
        } else {
            System.out.println("Cantidad Recurso introducida debe ser mayor que 0!");
        }
    }
    
    
}
