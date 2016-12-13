/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

/**
 *
 * @author javier
 */
public class Juego {
    Mapa map;
    
    public Juego(Mapa mapa){
        this.map=mapa;
               
    }

    public Mapa getMapa() {
        return map;
    }

    public void setMapa(Mapa mapa) {
        this.map = mapa;
    }
    
}
