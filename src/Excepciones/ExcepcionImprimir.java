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
public class ExcepcionImprimir extends ExcepcionComando {

    /**
     * Creates a new instance of <code>ExcepcionImprimir</code> without detail
     * message.
     */
    public ExcepcionImprimir() {
    }

    /**
     * Constructs an instance of <code>ExcepcionImprimir</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionImprimir(String msg) {
        super(msg);
    }
}
