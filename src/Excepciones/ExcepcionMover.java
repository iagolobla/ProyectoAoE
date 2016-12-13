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
public class ExcepcionMover extends ExcepcionComando {

    /**
     * Creates a new instance of <code>ExcepcionMover</code> without detail
     * message.
     */
    public ExcepcionMover() {
    }

    /**
     * Constructs an instance of <code>ExcepcionMover</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionMover(String msg) {
        super(msg);
    }
}
