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
public class ExcepcionCivilizacionNoEncontrada extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionCivilizacionNoEncontrada</code>
     * without detail message.
     */
    public ExcepcionCivilizacionNoEncontrada() {
    }

    /**
     * Constructs an instance of <code>ExcepcionCivilizacionNoEncontrada</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionCivilizacionNoEncontrada(String msg) {
        super(msg);
    }
}
