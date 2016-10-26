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
import java.util.ArrayList;
import java.util.HashMap;
public class Mapa {
    ArrayList<ArrayList<Celda>> mapa; 
    HashMap personajes;
    HashMap edificios;
    HashMap recursos;
    
    public Mapa(){
        mapa=new ArrayList(10);
        for(int i=0;i<mapa.size();i++){
            mapa.add(new ArrayList(10));
            for(int j=0;j<mapa.get(i).size();j++){
                mapa.get(i).add(new Celda());
            }
        }
        
    }
    
}
