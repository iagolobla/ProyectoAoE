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
    private ConsolaNormal Shell;

    public ComandoRecolectar(String direccion, String personaje, Mapa mapa, ConsolaNormal Shell) {
        this.direccion = direccion;
        this.personaje = personaje;
        this.mapa = mapa;
        this.Shell = Shell;
    }
    
    

    public void ejecutar() {
        
    }
}
