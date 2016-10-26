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
    
    public Celda(String tipo){
        switch(tipo){
            case "Soldado":
                new Personaje("Soldado");
                break;
            case "Paisano":
                new Personaje("Paisano");
                break;
            case "Ciudadela":
                break;
            case "Casa":
                break;
            case "Cuartel":
                break;
            default:
                System.out.println("Tipo mal introducido");
            
        }
    }
    
}
