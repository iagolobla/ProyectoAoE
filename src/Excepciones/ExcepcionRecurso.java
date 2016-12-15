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
public class ExcepcionRecurso extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionRecurso</code> without detail
     * message.
     */
    public ExcepcionRecurso() {
    }

    /**
     * Constructs an instance of <code>ExcepcionRecurso</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionRecurso(String msg) {
        super(msg);
    }
}
