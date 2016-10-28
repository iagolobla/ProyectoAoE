/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;
import Entidades.Personaje;
import Entidades.Edificio;
import Entidades.Posicion;
import Entidades.Recurso;
/**
 *
 * @author javier
 */
public class Celda {
    Personaje personaje;
    Edificio  edificio;
    Recurso recurso;
    Posicion pos;
    String tipo;
    
    public Celda(Posicion posicion){
        if(posicion==null){
            System.out.println("ERROR EN LA POSICION ->NULL");
            return;
        }
        tipo="Pradera";
        pos=new Posicion(posicion);
    }
    public Celda(String tipo){
        switch(tipo){
            case "Soldado":
                personaje=new Personaje("Soldado","Soldado",pos);
                break;
            case "Paisano":
                personaje=new Personaje("Paisano","Paisano",pos);
                break;
            case "Ciudadela":
                edificio=new Edificio("Ciudadela",pos,"Ciudadela");
                break;
            case "Casa":
                edificio=new Edificio("Casa",pos,"Casa");
                break;
            case "Cuartel":
                edificio=new Edificio("Cuartel",pos,"Cuartel");
                break;
            default:
                System.out.println("Tipo mal introducido");
            
        }
    }
    
    
    
}
