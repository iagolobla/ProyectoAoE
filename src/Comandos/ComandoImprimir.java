/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;
import Juego.Mapa;
/**
 *
 * @author iagolobla
 */
public class ComandoImprimir implements Comando{
    Mapa mapa;
    public ComandoImprimir(Mapa mapa){
        if(mapa!=null)
            this.mapa=mapa;
    }
    public void ejecutar(){
        mapa.print();
    }
}
