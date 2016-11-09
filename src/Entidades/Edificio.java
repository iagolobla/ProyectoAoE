/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Mapa.Celda;
import Mapa.Mapa;

/**
 *
 * @author iagolobla
 */
public class Edificio {

    private static final int SALUDCIUDADELA = 200;
    private static final int SALUDCASA = 50;
    private static final int SALUDCUARTEL = 150;
    private static final int CAPACIDADCUARTEL = 15;
    private static final int CAPACIDADCASA = 10;

    private String tipo;
    private int salud;
    private Posicion punto;
    private String nombre;

    private int madera;
    private int piedra;
    private int comida;

    public Edificio(String tipe, Posicion posicion, String Nombre) {
        //Igualar punto y posicion evitando aliasing
        punto = new Posicion(posicion.getX(), posicion.getY());
        switch (tipe) {
            case ("Ciudadela"):
                tipo = tipe;
                salud = SALUDCIUDADELA;
                nombre = Nombre;
                madera = 100;//son los valores iniciales que toman(se los damos nosotros).
                piedra = 100;
                comida = 100;
                break;
            case ("Cuartel"):
                tipo = tipe;
                salud = SALUDCUARTEL;
                nombre = Nombre;
                break;
            case ("Casa"):
                tipo = tipe;
                salud = SALUDCASA;
                nombre = Nombre;
                break;
            default:
                System.out.println("Error, tipo de edificio incorrecto");
        }
    }

    public void crearPaisano(Mapa mapa) {
        if (!this.tipo.equals("Ciudadela")) {
            System.out.println("Este edificio no puede crear Paisanos");
        }
        Posicion pos1 = new Posicion(punto);
        Posicion pos2 = new Posicion(punto);
        Posicion pos3 = new Posicion(punto);
        Posicion pos4 = new Posicion(punto);
        pos1.moverX(-1);
        pos2.moverX(1);
        pos3.moverY(-1);
        pos4.moverY(1);
        if (mapa.checkCoords(pos1) && mapa.checkBuilding(pos1)){
            String Name = "Paisano-" + (mapa.getCantidades()[0] + 1);
            mapa.getCantidades()[0]++;
            mapa.getMapa().get(pos1.getX()).set(pos1.getY(), new Celda("Paisano", new Posicion(pos1), Name)); //Metemos la celda en su posicion del mapa
            mapa.getPersonajes().put(Name, mapa.getCelda(new Posicion(pos1)).getPj());
            mapa.getEdificios().get(nombre).setComida(mapa.getEdificios().get(nombre).getComida()-10);
            System.out.println("Se han gastado 10 unidades de comida en crear el personaje");
        }else if(mapa.checkCoords(pos2) && mapa.checkBuilding(pos2)){
            String Name = "Paisano-" + (mapa.getCantidades()[0] + 1);
            mapa.getCantidades()[0]++;
            mapa.getMapa().get(pos2.getX()).set(pos2.getY(), new Celda("Paisano", new Posicion(pos2), Name)); //Metemos la celda en su posicion del mapa
            mapa.getPersonajes().put(Name, mapa.getCelda(new Posicion(pos2)).getPj());
            mapa.getEdificios().get(nombre).setComida(mapa.getEdificios().get(nombre).getComida()-10);
            System.out.println("Se han gastado 10 unidades de comida en crear el personaje");
        }else if(mapa.checkCoords(pos3) && mapa.checkBuilding(pos3)){
            String Name = "Paisano-" + (mapa.getCantidades()[0] + 1);
            mapa.getCantidades()[0]++;
            mapa.getMapa().get(pos3.getX()).set(pos3.getY(), new Celda("Paisano", new Posicion(pos3), Name)); //Metemos la celda en su posicion del mapa
            mapa.getPersonajes().put(Name, mapa.getCelda(new Posicion(pos3)).getPj());
            mapa.getEdificios().get(nombre).setComida(mapa.getEdificios().get(nombre).getComida()-10);
            System.out.println("Se han gastado 10 unidades de comida en crear el personaje");
        }else if(mapa.checkCoords(pos4) && mapa.checkBuilding(pos4)){
            String Name = "Paisano-" + (mapa.getCantidades()[0] + 1);
            mapa.getCantidades()[0]++;
            mapa.getMapa().get(pos4.getX()).set(pos4.getY(), new Celda("Paisano", new Posicion(pos4), Name)); //Metemos la celda en su posicion del mapa
            mapa.getPersonajes().put(Name, mapa.getCelda(new Posicion(pos4)).getPj());
            mapa.getEdificios().get(nombre).setComida(mapa.getEdificios().get(nombre).getComida()-10);
            System.out.println("Se han gastado 10 unidades de comida en crear el personaje");
        }else{
            System.out.println("No es posible crear el paisano");
        }
        
        
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
        impresion += "Tipo: " + tipo + "\n";
        impresion += "Salud: " + salud + "\n";
        impresion += "Posicion: " + punto + "\n";
        impresion += "Comida: " + comida + "\n";
        impresion += "Piedra: " + piedra + "\n";
        impresion += "Madera: " + madera + "\n";

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

    public int getMadera() {
        return madera;
    }

    public void setMadera(int madera) {
        this.madera = madera;
    }

    public int getPiedra() {
        return piedra;
    }

    public void setPiedra(int piedra) {
        this.piedra = piedra;
    }

    public int getComida() {
        return comida;
    }

    public void setComida(int comida) {
        this.comida = comida;
    }
    
}
