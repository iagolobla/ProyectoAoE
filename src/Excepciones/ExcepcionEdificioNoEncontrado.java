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
public class ExcepcionEdificioNoEncontrado extends ExcepcionEntidadNoEncontrada {

    /**
     * Creates a new instance of <code>ExcepcionEdificioNoEncontrado</code>
     * without detail message.
     */
    public ExcepcionEdificioNoEncontrado() {
    }

    /**
     * Constructs an instance of <code>ExcepcionEdificioNoEncontrado</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionEdificioNoEncontrado(String msg) {
        super(msg);
    }
}
