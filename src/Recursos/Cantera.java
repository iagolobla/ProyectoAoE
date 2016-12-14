/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import Juego.Posicion;

/**
 *
 * @author iagolobla
 */
public class Cantera extends Contenedor {

    private int CantInit;
    
    public Cantera(String Nombre, Posicion posicion, Recurso R) {
        super(Nombre, posicion, R);
        CantInit = R.getCantidad();
    }

    public int getCantInit() {
        return CantInit;
    }

    public void setCantInit(int accesos) {
        this.CantInit = accesos;
    }
    
}
