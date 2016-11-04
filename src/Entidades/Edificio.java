/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Mapa.Celda;

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

    public Edificio(String tipe, Posicion posicion, String Nombre) {
        //Igualar punto y posicion evitando aliasing
        punto = new Posicion(posicion.getX(), posicion.getY());
        switch (tipe) {
            case ("Ciudadela"):
                tipo = tipe;
                salud = SALUDCIUDADELA;
                break;
            case ("Cuartel"):
                tipo = tipe;
                salud = SALUDCUARTEL;
                break;
            case ("Casa"):
                tipo = tipe;
                salud = SALUDCASA;
                break;
            default:
                System.out.println("Error, tipo de edificio incorrecto");
        }
    }

    public Personaje crearPaisano(String Nombre) {
        if (!this.tipo.equals("Ciudadela")) {
            System.out.println("Este edificio no puede crear Paisanos");
            return null;
        }

        return new Personaje("Paisano", Nombre, punto);
    }

    public Personaje crearSoldado(String Nombre) {
        if (!this.tipo.equals("Cuartel")) {
            System.out.println("Este edificio no puede crear Soldados");
            return null;
        }
        return new Personaje("Soldado", Nombre, punto);
    }
    
    @Override
    public String toString() {
        String impresion = "";
        impresion+="Tipo: "+tipo+"\n";
        impresion+="Salud: "+salud+"\n";
        impresion+="Posicion: "+punto+"\n";
        

        return impresion;
    }

    //GETTERS Y SETTERS
    public Posicion getPosicion() {
        return new Posicion(punto);
    }

    public String getTipo() {
        return tipo;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }
}
