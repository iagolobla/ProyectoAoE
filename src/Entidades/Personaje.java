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
 * @author javier
 */
public class Personaje {

    private String tipo;
    private int armadura;
    private int salud;
    private int ataque;
    private int capRecolectar;
    private String Nombre;  //Aqui se necesita poner un nombre al personaje para usar eso como clave en el hashmap de personajes en el mapa
    private Posicion posicion;

    public Personaje(String tipo, String Nombre, Posicion pos) {
        posicion = new Posicion(pos);
        switch (tipo) {
            case "Soldado":
                this.tipo = tipo;
                armadura = 100;
                salud = 200;
                ataque = 50;
                capRecolectar = 0;
                this.Nombre = Nombre;   //El parametro nombre debe ser unico para cada personaje
                break;
            case "Paisano":
                this.tipo = tipo;
                armadura = 50;
                salud = 150;
                ataque = 10;
                capRecolectar = 100;
                this.Nombre = Nombre;
                break;
            default:
                System.out.println("Tipo mal introducido");

        }
    }

    public void construir(Mapa mapa, String direccion, String Edificio) {//DESCONTAR RECURSOS AL CONSTRUIR.
        Posicion pos = new Posicion(posicion);
        switch (direccion) {
            case "S":
                pos.moverX(1);
                break;
            case "N":
                pos.moverX(-1);
                break;
            case "E":
                pos.moverY(1);
                break;
            case "O":
                pos.moverY(-1);
                break;
            default:
                System.out.println("Error, direccion no valida!");

        }
        switch (Edificio.toLowerCase()) {
            case "casa":

                if (mapa.checkCoords(pos) && mapa.checkBuilding(pos)) { //Comprueba que la posicion esta en el mapa y que no esta ocupada//COMPROBAR INTRODUCIR EN HASHMAP
                    String Name = "Casa-" + (mapa.getCantidades()[3] + 1);
                    mapa.getCantidades()[3]++;
                    mapa.getMapa().get(pos.getX()).set(pos.getY(), new Celda("Casa", new Posicion(pos), Name)); //Metemos la celda en su posicion del mapa
                    //COMPROBAR ESTO//mapa.getEdificios().put(Name, getCelda(new Posicion(3, 3)).getEf());
                } else {
                    System.out.println("No se puede Contruir en esa direccion!");
                }
                break;
            case "cuartel":

                if (mapa.checkCoords(pos) && mapa.checkBuilding(pos)) { //Comprueba que la posicion esta en el mapa y que no esta ocupada
                    String Name = "Cuartel-" + (mapa.getCantidades()[4] + 1);
                    mapa.getCantidades()[4]++;
                    mapa.getMapa().get(pos.getX()).set(pos.getY(), new Celda("Cuartel", new Posicion(pos), Name)); //Metemos la celda en su posicion del mapa
                } else {
                    System.out.println("No se puede Contruir en esa direccion!");
                }
                break;
            case "ciudadela":

                if (mapa.checkCoords(pos) && mapa.checkBuilding(pos)) { //Comprueba que la posicion esta en el mapa y que no esta ocupada
                    String Name = "Ciudadela-" + (mapa.getCantidades()[2] + 1);
                    mapa.getCantidades()[2]++;
                    mapa.getMapa().get(pos.getX()).set(pos.getY(), new Celda("Ciudadela", new Posicion(pos), Name)); //Metemos la celda en su posicion del mapa
                } else {
                    System.out.println("No se puede Contruir en esa direccion!");
                }
                break;
            default:
                System.out.println("Error, direccion no valida!");

        }

    }

    public Posicion moverPj(Mapa mapa, String direccion) {
        Celda cell = mapa.getCelda(posicion);
        Posicion pos = new Posicion(posicion);

        switch (direccion) {
            case "S":
                pos.moverX(1);
                break;
            case "N":
                pos.moverX(-1);
                break;
            case "E":
                pos.moverY(1);
                break;
            case "O":
                pos.moverY(-1);
                break;
            default:
                System.out.println("Error, direccion no valida!");

        }
        if (mapa.checkCoords(pos) && mapa.checkBuilding(pos)) { //Comprueba que la posicion esta en el mapa y que no esta ocupada
            this.setPosicion(pos);
            Celda newcell = mapa.getCelda(posicion);

            newcell.setPersonaje(this);   //Metemos el personaje en la nueva celda

            mapa.getMapa().get(posicion.getX()).set(posicion.getY(), newcell); //Metemos la celda en su posicion del mapa

            cell.liberarCelda();    //Ponemos la celda donde estaba el personaje como pradera
        } else {
            System.out.println("No se puede Mover en esa direccion!");
        }
        //Recorremos mapa para actualizar las visibilidades
        for (int i = 0; i < mapa.MAPAY; i++) {
            for (int j = 0; j < mapa.MAPAX; j++) {
                cell = mapa.getCelda(new Posicion(i, j));
                mapa.ponerVisible(cell);    //Pone visible esa celda y sus adyacentes
            }
        }
        return new Posicion(posicion);
    }

    @Override
    public String toString() {
        String impresion = "";
        impresion += "Tipo: " + tipo + "\n";
        impresion += "Armadura: " + armadura + "\n";
        impresion += "Salud: " + salud + "\n";
        impresion += "Ataque: " + ataque + "\n";
        impresion += "Capacidad Recolección: " + capRecolectar + "\n";
        impresion += "Nombre: " + Nombre + "\n";
        impresion += "Posicion: " + posicion + "\n";

        return impresion;
    }

    //GETTERS Y SETTERS
    public Posicion getPosicion() {
        return new Posicion(posicion);
    }

    public String getTipo() {
        return tipo;
    }

    public int getArmadura() {
        return armadura;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getCapRecolectar() {
        return capRecolectar;
    }

    public void setCapRecolectar(int capRecolectar) {
        this.capRecolectar = capRecolectar;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setPosicion(Posicion pos) {
        this.posicion = new Posicion(pos);
    }
}
