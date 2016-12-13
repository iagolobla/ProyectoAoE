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
public class ExcepcionMirar extends ExcepcionComando {

    /**
     * Creates a new instance of <code>ExcepcionMirar</code> without detail
     * message.
     */
    public ExcepcionMirar() {
    }

    /**
     * Constructs an instance of <code>ExcepcionMirar</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionMirar(String msg) {
        super(msg);
    }
}
