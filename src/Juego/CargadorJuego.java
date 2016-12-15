/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Excepciones.ExcepcionCelda;
import java.io.FileNotFoundException;

/**
 *
 * @author javier
 */
public interface CargadorJuego {
    public Juego cargar()throws FileNotFoundException,ExcepcionCelda;
    
}
