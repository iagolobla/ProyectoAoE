/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author iagolobla
 */
public class ExcepcionPersonaje extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionPersonaje</code> without detail
     * message.
     */
    public ExcepcionPersonaje() {
    }

    /**
     * Constructs an instance of <code>ExcepcionPersonaje</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionPersonaje(String msg) {
        super(msg);
    }
}
