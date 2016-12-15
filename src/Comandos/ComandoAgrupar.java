/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Excepciones.ExcepcionAgrupar;
import Juego.Celda;
import Juego.Mapa;
import Juego.Posicion;

/**
 *
 * @author iagolobla
 */
public class ComandoAgrupar implements Comando{
    
    private Mapa mapa;
    private String posicion;

    public ComandoAgrupar(Mapa mapa, String posicion) {
        this.mapa = mapa;
        this.posicion = posicion;
    }
    
    
    public void ejecutar()throws ExcepcionAgrupar{
        Posicion posMirar = new Posicion(posicion);
        if(!mapa.getCelda(posMirar).isVisible(mapa.getCivilizacion())){
           throw new ExcepcionAgrupar("La celda no es visible");
        }
        if (mapa.checkCoords(posMirar) && mapa.checkBuilding(posMirar)) {
            Celda cell=mapa.getCelda(posMirar);
            cell.agrupar(mapa);
            
        }
    }
}
