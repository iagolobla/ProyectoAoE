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
public class ExcepcionCelda extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionCelda</code> without detail
     * message.
     */
    public ExcepcionCelda() {
    }

    /**
     * Constructs an instance of <code>ExcepcionCelda</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionCelda(String msg) {
        super(msg);
    }
}
