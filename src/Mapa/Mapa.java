/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

/**
 *
 * @author javier
 */
import Entidades.Posicion;
import java.util.ArrayList;
import java.util.HashMap;
public class Mapa {
    ArrayList<ArrayList<Celda>> mapa; 
    HashMap personajes;
    HashMap edificios;
    HashMap recursos;
    
    public Mapa(){
        mapa=new ArrayList();
        for(int i=0;i<10;i++){
            mapa.add(new ArrayList());
            for(int j=0;j<10;j++){
                mapa.get(i).add(new Celda(new Posicion(i,j)));
            }
        }
        mapa.get(3).get(3).crearCiudadela();
    }
    
}
