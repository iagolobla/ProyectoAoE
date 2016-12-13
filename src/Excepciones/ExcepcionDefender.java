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
public class ExcepcionDefender extends ExcepcionComando {

    /**
     * Creates a new instance of <code>ExcepcionDefender</code> without detail
     * message.
     */
    public ExcepcionDefender() {
    }

    /**
     * Constructs an instance of <code>ExcepcionDefender</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionDefender(String msg) {
        super(msg);
    }
}
