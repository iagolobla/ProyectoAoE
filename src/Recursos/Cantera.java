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
    private int Cant20;
    
    public Cantera(String Nombre, Posicion posicion, Recurso R) {
        super(Nombre, posicion, R);
        CantInit = R.getCantidad();
        Cant20 = CantInit/5; //Calcula el 20% del total
    }
    public Cantera(Cantera cantera){
        super(cantera.getNombre(), cantera.getPosicion(), cantera.getRecurso());
        CantInit = cantera.getCantInit();
        Cant20 = cantera.getCant20();
    }

    public int getCantInit() {
        return CantInit;
    }

    public void setCantInit(int CantInit) {
        this.CantInit = CantInit;
    }
    
    public int getCant20() {
        return Cant20;
    }

    public void setCant20(int Cant20) {
        this.Cant20 = Cant20;
    }
    
}
