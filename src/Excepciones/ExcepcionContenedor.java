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
public class ExcepcionContenedor extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionContenedor</code> without detail
     * message.
     */
    public ExcepcionContenedor() {
    }

    /**
     * Constructs an instance of <code>ExcepcionContenedor</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionContenedor(String msg) {
        super(msg);
    }
}
