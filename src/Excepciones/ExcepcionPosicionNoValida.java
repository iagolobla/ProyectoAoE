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
public class ExcepcionPosicionNoValida extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionPosicionNoValida</code> without
     * detail message.
     */
    public ExcepcionPosicionNoValida() {
    }

    /**
     * Constructs an instance of <code>ExcepcionPosicionNoValida</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionPosicionNoValida(String msg) {
        super(msg);
    }
}
