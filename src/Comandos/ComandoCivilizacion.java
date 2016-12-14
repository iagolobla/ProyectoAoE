/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Juego.ConsolaNormal;
import Juego.Mapa;
import static Juego.Principal.SHELL;

/**
 *
 * @author iagolobla
 */
public class ComandoCivilizacion implements Comando {

    private Mapa mapa;
    
    
    public ComandoCivilizacion(Mapa mapa, ConsolaNormal SHELL) {
        this.mapa = mapa;
        
    }
    
    public void ejecutar() {
        SHELL.imprimir("Esta jugando con la civilizacion " + mapa.getCivilizacion().getNombre());
    }
}
