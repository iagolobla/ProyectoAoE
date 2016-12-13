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
public class ExcepcionConstruir extends ExcepcionComando{
    /**
     * Creates a new instance of <code>ExcepcionEdificioVacio</code> without
     * detail message.
     */
    public ExcepcionConstruir() {
    }

    /**
     * Constructs an instance of <code>ExcepcionEdificioVacio</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionConstruir(String msg) {
        super(msg);
    }
}
