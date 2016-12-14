/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Excepciones.ExcepcionCambiar;
import Juego.Civilizacion;
import Juego.ConsolaNormal;
import Juego.Mapa;
import static Juego.Principal.SHELL;

/**
 *
 * @author iagolobla
 */
public class ComandoCambiar implements Comando {

    private Civilizacion civilizacion;
    private Mapa mapa;

    public ComandoCambiar(String civilizacion, Mapa mapa) {
        this.mapa = mapa;
        this.civilizacion = mapa.getCivilizaciones().get(civilizacion);
        
    }

    public void ejecutar() throws ExcepcionCambiar{
        if (civilizacion != null) {
            if(mapa.getCivilizaciones().containsKey(civilizacion.getNombre())){
                if(mapa.getCivilizacion().getNombre().equals(civilizacion.getNombre())){
                    throw new ExcepcionCambiar("Ya se esta jugando con los " + mapa.getCivilizacion().getNombre());
                }
                mapa.setCivilizacion(civilizacion);
                SHELL.imprimir("Ahora se esta jugando con los " + civilizacion.getNombre());
            } else {
                throw new ExcepcionCambiar("Civilizacion introducida erronea!");
            }
        } else {
            throw new ExcepcionCambiar("Civilizacion introducida erronea!");
        }
    }
}
