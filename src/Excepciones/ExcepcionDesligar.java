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
public class ExcepcionDesligar extends ExcepcionComando {

    /**
     * Creates a new instance of <code>ExcepcionDesligar</code> without detail
     * message.
     */
    public ExcepcionDesligar() {
    }

    /**
     * Constructs an instance of <code>ExcepcionDesligar</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionDesligar(String msg) {
        super(msg);
    }
}
