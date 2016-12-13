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
public class ExcepcionAtacar extends ExcepcionComando {

    /**
     * Creates a new instance of <code>ExcepcionAtacar</code> without detail
     * message.
     */
    public ExcepcionAtacar() {
    }

    /**
     * Constructs an instance of <code>ExcepcionAtacar</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionAtacar(String msg) {
        super(msg);
    }
}
