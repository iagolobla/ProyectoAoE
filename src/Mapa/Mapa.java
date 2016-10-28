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
        mapa.get(3).get(3);
    }
    
    
    public Celda getCelda(Posicion p){
        if (p == null){
            System.out.println("Posicion pasada nula!");
            return null;
        }
        int x = p.getX();
        int y = p.getY();
        
        return mapa.get(x).get(y);
    }
    public void imprimir() {
        for (int i = 0; i < mapa.size(); i++){
            System.out.print("==");
        }
        System.out.println("");
        for (int i = 0; i < mapa.size(); i++) {
            System.out.print("|");
            for (int j = 0; j < mapa.get(0).size(); j++) {
                switch (mapa.get(i).get(j).tipo) {
                    case ("Pradera"):
                        System.out.print(" ");
                        break;
                    case ("Ciudadela"):
                        System.out.print("C");
                        break;
                    case ("Cuartel"):
                        break;
                    case ("Casa"):
                        break;
                    case "Soldado":
                        break;
                    case "Paisano":
                        break;
                    default:
                        System.out.println("Error, tipo de edificio incorrecto");
                }
            }
            System.out.print("|");
            System.out.println("");
        }
        for (int i = 0; i < mapa.size(); i++){
            System.out.print("==");
        }
    }

}



