/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

/**
 *
 * @author javier
 */
public class Piedra extends Recurso{
    public Piedra(int cantidad){
        super(cantidad);
    }
    public Piedra(Piedra piedra){
        super(piedra);
    }
}
