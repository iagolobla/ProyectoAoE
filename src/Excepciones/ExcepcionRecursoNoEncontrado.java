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
public class ExcepcionRecursoNoEncontrado extends ExcepcionEntidadNoEncontrada {

    /**
     * Creates a new instance of <code>ExcepcionRecursoNoEncontrado</code>
     * without detail message.
     */
    public ExcepcionRecursoNoEncontrado() {
    }

    /**
     * Constructs an instance of <code>ExcepcionRecursoNoEncontrado</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionRecursoNoEncontrado(String msg) {
        super(msg);
    }
}
