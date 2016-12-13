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
public class ExcepcionDescribir extends ExcepcionComando {

    /**
     * Creates a new instance of <code>ExcepcionDescribir</code> without detail
     * message.
     */
    public ExcepcionDescribir() {
    }

    /**
     * Constructs an instance of <code>ExcepcionDescribir</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionDescribir(String msg) {
        super(msg);
    }
}
