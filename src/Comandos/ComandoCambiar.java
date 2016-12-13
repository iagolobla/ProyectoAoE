/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Excepciones.ExcepcionCivilizacionNoEncontrada;
import Juego.Civilizacion;
import Juego.ConsolaNormal;
import Juego.Mapa;

/**
 *
 * @author iagolobla
 */
public class ComandoCambiar implements Comando {

    Civilizacion civilizacion;
    Mapa mapa;
    ConsolaNormal Shell;

    public ComandoCambiar(String civilizacion, Mapa mapa, ConsolaNormal Shell) {
        this.mapa = mapa;
        this.civilizacion = mapa.getCivilizaciones().get(civilizacion);
        this.Shell = Shell;
    }

    public void ejecutar() throws ExcepcionCivilizacionNoEncontrada{
        if (civilizacion != null) {
            if(mapa.getCivilizaciones().containsKey(civilizacion.getNombre())){
                if(mapa.getCivilizacion().getNombre().equals(civilizacion.getNombre())){
                    throw new ExcepcionCivilizacionNoEncontrada("Ya se esta jugando con los " + mapa.getCivilizacion().getNombre());
                }
                mapa.setCivilizacion(civilizacion);
                Shell.imprimir("Ahora se esta jugando con los " + civilizacion.getNombre());
            } else {
                throw new ExcepcionCivilizacionNoEncontrada("Civilizacion introducida erronea!");
            }
        } else {
            throw new ExcepcionCivilizacionNoEncontrada("Civilizacion introducida erronea!");
        }
    }
}
