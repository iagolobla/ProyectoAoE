/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author javier
 */
public class Personaje {
    private String tipo;
    private int armadura;
    private int salud;
    private int ataque;
    private int capRecolectar;
    
    public Personaje(String tipo){
        switch(tipo){
            case "Soldado":
                armadura=100;
                salud=200;
                ataque=50;
                capRecolectar=0;
                break;
            case "Paisano":
                armadura=50;
                salud=150;
                ataque=10;
                capRecolectar=100;
                break;
            default:
                System.out.println("Tipo mal introducido");
            
        }
    }
    
}
