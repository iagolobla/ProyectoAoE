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
public class ExcepcionCambiar extends ExcepcionComando {

    /**
     * Creates a new instance of <code>ExcepcionCambiar</code> without detail
     * message.
     */
    public ExcepcionCambiar() {
    }

    /**
     * Constructs an instance of <code>ExcepcionCambiar</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionCambiar(String msg) {
        super(msg);
    }
}
