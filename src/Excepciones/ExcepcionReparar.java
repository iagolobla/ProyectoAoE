/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author javier
 */
public class ExcepcionReparar extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionReparar</code> without detail
     * message.
     */
    public ExcepcionReparar() {
    }

    /**
     * Constructs an instance of <code>ExcepcionReparar</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionReparar(String msg) {
        super(msg);
    }
}
