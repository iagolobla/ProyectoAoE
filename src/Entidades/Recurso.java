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
    
    private String tipo;
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
}
