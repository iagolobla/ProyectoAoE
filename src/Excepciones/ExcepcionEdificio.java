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
public class ExcepcionEdificio extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionEdificio</code> without detail
     * message.
     */
    public ExcepcionEdificio() {
    }

    /**
     * Constructs an instance of <code>ExcepcionEdificio</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionEdificio(String msg) {
        super(msg);
    }
}
