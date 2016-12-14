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
public class ComandoCivilizacion implements Comando {

    private Mapa mapa;
    private ConsolaNormal Shell;
    
    public ComandoCivilizacion(Mapa mapa, ConsolaNormal Shell) {
        this.mapa = mapa;
        this.Shell = Shell;
    }
    
    public void ejecutar() {
        Shell.imprimir("Esta jugando con la civilizacion " + mapa.getCivilizacion().getNombre());
    }
}
