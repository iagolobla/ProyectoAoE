/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

/**
 *
 * @author iagolobla
 */
public class ComandoSalir implements Comando {
    Boolean seguir;

    public ComandoSalir(Boolean seguir) {
        this.seguir = seguir;
    }
    
    public void ejecutar(){
        seguir=false;
    }
}
