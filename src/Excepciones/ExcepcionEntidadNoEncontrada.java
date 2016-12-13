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
public class ExcepcionEntidadNoEncontrada extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionEntidadNoEncontrada</code>
     * without detail message.
     */
    public ExcepcionEntidadNoEncontrada() {
    }

    /**
     * Constructs an instance of <code>ExcepcionEntidadNoEncontrada</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionEntidadNoEncontrada(String msg) {
        super(msg);
    }
}
