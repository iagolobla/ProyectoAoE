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
public class ExcepcionComando extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionComando</code> without detail
     * message.
     */
    public ExcepcionComando() {
    }

    /**
     * Constructs an instance of <code>ExcepcionComando</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionComando(String msg) {
        super(msg);
    }
}
