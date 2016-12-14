/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Juego.ConsolaNormal;
import Juego.Mapa;

/**
 *
 * @author iagolobla
 */
public class ComandoRecolectar implements Comando {

    private String direccion;
    private String personaje;
    private Mapa mapa;
    

    public ComandoRecolectar(String direccion, String personaje, Mapa mapa, ConsolaNormal SHELL) {
        this.direccion = direccion;
        this.personaje = personaje;
        this.mapa = mapa;
        
    }
    
    

    public void ejecutar() {
        
    }
}
