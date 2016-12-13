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
public class ExcepcionJuego extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionJuego</code> without detail
     * message.
     */
    public ExcepcionJuego() {
    }

    /**
     * Constructs an instance of <code>ExcepcionJuego</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionJuego(String msg) {
        super(msg);
    }
}
