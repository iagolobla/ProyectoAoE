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
public class ExcepcionAgrupar extends ExcepcionComando {

    /**
     * Creates a new instance of <code>ExcepcionAgrupar</code> without detail
     * message.
     */
    public ExcepcionAgrupar() {
    }

    /**
     * Constructs an instance of <code>ExcepcionAgrupar</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionAgrupar(String msg) {
        super(msg);
    }
}
