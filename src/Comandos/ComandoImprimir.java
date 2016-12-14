/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Juego.Mapa;
import Juego.ConsolaNormal;
import static Juego.Principal.SHELL;

/**
 *
 * @author iagolobla
 */
public class ComandoImprimir implements Comando{
    Mapa mapa;
    public ComandoImprimir(Mapa mapa){
        if(mapa!=null)
            this.mapa=mapa;
        if(SHELL != null){
            
        }
    }

    public void ejecutar() {
        SHELL.imprimir(mapa.print());
    }
}
