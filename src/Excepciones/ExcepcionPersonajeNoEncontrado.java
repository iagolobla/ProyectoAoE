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
public class ExcepcionPersonajeNoEncontrado extends ExcepcionEntidadNoEncontrada {

    /**
     * Creates a new instance of <code>ExcepcionPersonajeNoEncontrado</code>
     * without detail message.
     */
    public ExcepcionPersonajeNoEncontrado() {
    }

    /**
     * Constructs an instance of <code>ExcepcionPersonajeNoEncontrado</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionPersonajeNoEncontrado(String msg) {
        super(msg);
    }
}
