/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author javier
 */
public class ExcepcionCrear extends ExcepcionComando{
    /**
     * Creates a new instance of <code>ExcepcionEdificioVacio</code> without
     * detail message.
     */
    public ExcepcionCrear() {
    }

    /**
     * Constructs an instance of <code>ExcepcionEdificioVacio</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionCrear(String msg) {
        super(msg);
    }
}
