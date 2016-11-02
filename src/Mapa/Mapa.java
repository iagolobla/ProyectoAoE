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
  
    private final int MAPAX = 16;
    private final int MAPAY = 8;
    
    public Mapa(){
        edificios=new HashMap();
        personajes=new HashMap();
        recursos=new HashMap();
        mapa=new ArrayList<>();
        for(int i=0;i<MAPAY;i++){
            mapa.add(new ArrayList<>());
            for(int j=0;j<MAPAX;j++){
                mapa.get(i).add(new Celda(new Posicion(i,j)));
            }
        }
        
        //Creacion de la ciudadela
        String Name = "Ciudadela-" + edificios.size()+1;    //edificios.size() ayuda a crear el nombre
        mapa.get(3).set(3,new Celda("Ciudadela",new Posicion(3,3), Name));   
        edificios.put(Name, getCelda(new Posicion(3,3)).getObj());
        
        //creacion un personaje
        Name="Personaje-"+personajes.size()+1;
        mapa.get(3).set(4,new Celda("Paisano",new Posicion(3,4), Name));   
        edificios.put(Name, getCelda(new Posicion(3,4)).getObj());//Aqui creo que hay ALIASING Assign return variable to new variable.
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
        for (int i = 0; i < MAPAX+2; i++){
            System.out.print("&");
        }
        System.out.println("");
        for (int i = 0; i < MAPAY; i++) {
            System.out.print("&");
            for (int j = 0; j < mapa.get(0).size(); j++) {
                switch (mapa.get(i).get(j).tipo) {
                    case ("Pradera"):
                        System.out.print(" ");
                        break;
                    case ("Ciudadela"):
                        System.out.print("C");
                        break;
                    case ("Cuartel"):
                        System.out.print("c");
                        break;
                    case ("Casa"):
                        System.out.print("Ç");
                        break;
                    case "Soldado":
                        System.out.print("S");
                        break;
                    case "Paisano":
                        System.out.print("P");
                        break;
                    default:
                        System.out.println("Error, tipo de edificio incorrecto");
                }
            }
            System.out.print("&");
            System.out.println("");
        }
        for (int i = 0; i < MAPAX+2; i++){
            System.out.print("&");
        }
        System.out.println("");
    }
    

}



