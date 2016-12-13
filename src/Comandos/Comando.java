/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Excepciones.ExcepcionAgrupar;
import Excepciones.ExcepcionAlmacenar;
import Excepciones.ExcepcionAtacar;
import Excepciones.ExcepcionMover;
import Excepciones.ExcepcionConstruir;
import Excepciones.ExcepcionCrear;
import Excepciones.ExcepcionSintaxis;
import Excepciones.ExcepcionCambiar;
import Excepciones.ExcepcionCelda;
import Excepciones.ExcepcionCivilizacion;
import Excepciones.ExcepcionComando;
import Excepciones.ExcepcionDefender;
import Excepciones.ExcepcionDesagrupar;
import Excepciones.ExcepcionDescribir;
import Excepciones.ExcepcionDesligar;
import Excepciones.ExcepcionImprimir;
import Excepciones.ExcepcionListar;
import Excepciones.ExcepcionMirar;
import Excepciones.ExcepcionRecolectar;
import Excepciones.ExcepcionReparar;
import Excepciones.ExcepcionSalir;


/**
 *
 * @author iagolobla
 */
public interface Comando{
    public void ejecutar() 
            throws ExcepcionAgrupar, ExcepcionAlmacenar, ExcepcionAtacar, ExcepcionCambiar,
            ExcepcionCelda, ExcepcionCivilizacion, ExcepcionConstruir, ExcepcionCrear,
            ExcepcionDefender, ExcepcionDesagrupar, ExcepcionDescribir, ExcepcionDesligar,
            ExcepcionImprimir, ExcepcionListar, ExcepcionMirar, ExcepcionMover,
            ExcepcionRecolectar,ExcepcionReparar, ExcepcionSalir, ExcepcionSintaxis;
}
