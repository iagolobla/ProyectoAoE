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
    
    public Celda(){
        tipo="Pradera";
    }
    /*public Celda(String tipo){
        switch(tipo){
            case "Soldado":
                personaje=new Personaje("Soldado",pos);
                break;
            case "Paisano":
                personaje=new Personaje("Paisano",pos);
                break;
            case "Ciudadela":
                edificio=new Edificio("Ciudadela",pos);
                break;
            case "Casa":
                edificio=new Edificio("Casa",pos);
                break;
            case "Cuartel":
                edificio=new Edificio("Cuartel",pos);
                break;
            default:
                System.out.println("Tipo mal introducido");
            
        }
    }*/
    public Edificio crearCiudadela(){
        return new Edificio("Ciudadela",pos);
    }
    
}
