/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Excepciones.ExcepcionCivilizacionNoEncontrada;
import Excepciones.ExcepcionConstruir;
import Excepciones.ExcepcionCrear;
import Excepciones.ExcepcionEdificioVacio;
import Excepciones.ExcepcionEntidadNoEncontrada;
import Excepciones.ExcepcionLimiteAlojamiento;
import Excepciones.ExcepcionPersonajeNoEncontrado;
import Excepciones.ExcepcionPosicionNoValida;
import Excepciones.ExcepcionReparar;
import Excepciones.ExcepcionSintaxis;

/**
 *
 * @author iagolobla
 */
public interface Comando{
    public void ejecutar() throws ExcepcionSintaxis, ExcepcionPosicionNoValida, ExcepcionEdificioVacio,ExcepcionEntidadNoEncontrada,ExcepcionReparar,ExcepcionLimiteAlojamiento,ExcepcionCrear,ExcepcionConstruir, ExcepcionCivilizacionNoEncontrada;
}
