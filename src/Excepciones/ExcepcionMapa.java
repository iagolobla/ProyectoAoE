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
public class ExcepcionMapa extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionMapa</code> without detail
     * message.
     */
    public ExcepcionMapa() {
    }

    /**
     * Constructs an instance of <code>ExcepcionMapa</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionMapa(String msg) {
        super(msg);
    }
}
