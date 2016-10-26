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
        punto = new Posicion(posicion.getX(), posicion.getY());
        switch(tipe){
            case("Ciudadela"):
                tipo = tipe;
                salud = SALUDCIUDADELA;
                break;
            case("Cuartel"):
                tipo = tipe;
                salud = SALUDCUARTEL;
                break;
            case("Casa"):
                tipo = tipe;
                salud = SALUDCASA;
                break;
            default:
                System.out.println("Error, tipo de edificio incorrecto");
        }
    }
    
    public Personaje crearPaisano(String Nombre){
        if(!this.tipo.equals("Ciudadela")){
            System.out.println("Este edificio no puede crear personajes");
            return null;
        }
        return new Personaje("Paisano", Nombre);
    }
}
