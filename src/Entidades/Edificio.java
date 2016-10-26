/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author iagolobla
 */
public class Edificio {
    private final int SALUDCIUDADELA = 200;
    private final int SALUDCASA = 50;
    private final int SALUDCUARTEL = 150;
    
    private String tipo;
    private int salud;
    private Posicion punto;
    
    public Edificio(String tipe, Posicion posicion){
        //Igualar punto y posicion evitando aliasing
        switch(tipe){
            case("Ciudadela"):
                salud = SALUDCIUDADELA;
                break;
            case("Cuartel"):
                salud = SALUDCUARTEL;
                break;
            case("Casa"):
                salud = SALUDCASA;
                break;
        }
    }
}
