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
import Entidades.Personaje;
import Entidades.Recurso;
import Entidades.Edificio;
import java.util.ArrayList;
import java.util.HashMap;

public class Mapa {

    ArrayList<ArrayList<Celda>> mapa;
    HashMap<String, Personaje> personajes;
    HashMap<String, Edificio> edificios;
    HashMap<String, Recurso> recursos;

    public final int MAPAX = 16;
    public final int MAPAY = 8;

    public Mapa() {
        edificios = new HashMap<String, Edificio>();
        personajes = new HashMap<String, Personaje>();
        recursos = new HashMap<String, Recurso>();
        mapa = new ArrayList<>();
        Celda cell;
        for (int i = 0; i < MAPAY; i++) {
            mapa.add(new ArrayList<>());
            for (int j = 0; j < MAPAX; j++) {   //Inicializamos visible a false para todas las celdas
                cell = new Celda(new Posicion(i, j));
                cell.setVisible(false);
                mapa.get(i).add(cell);
            }
        }

        //Creacion de la ciudadela
        String Name = "Ciudadela-" + edificios.size() + 1;    //edificios.size() ayuda a crear el nombre
        mapa.get(3).set(3, new Celda("Ciudadela", new Posicion(3, 3), Name));
        edificios.put(Name, getCelda(new Posicion(3, 3)).getEf());

        //creacion un personaje
        Name = "Paisano-" + personajes.size() + 1;
        mapa.get(3).set(4, new Celda("Paisano", new Posicion(3, 4), Name));
        personajes.put(Name, getCelda(new Posicion(3, 4)).getPj());//Aqui creo que hay ALIASING Assign return variable to new variable.

        //Recorremos mapa para actualizar las visibilidades
        for (int i = 0; i < MAPAY; i++) {
            for (int j = 0; j < MAPAX; j++) {
                cell = this.getCelda(new Posicion(i, j));
                this.ponerVisible(cell);    //Pone visible esa celda y sus adyacentes
            }
        }
    }

    public Celda getCelda(Posicion p) {
        if (p == null) {
            System.out.println("Posicion pasada nula!");
            return null;
        }
        int x = p.getX();
        int y = p.getY();

        return mapa.get(x).get(y);
    }

    public void ponerVisible(Celda cell) {
        Celda aux;
        Posicion pos;
        if (cell.getTipo().equals("Soldado") || cell.getTipo().equals("Paisano")) {   //Cuando localiza un soldado o paisano pone
            pos = new Posicion(cell.getPosicion());                                 //sus celdas adyacentes en visible
            aux = this.getCelda(pos);
            aux.setVisible(true);   //Pone visible la celda del personaje

            pos.moverX(1);
            if (this.checkCoords(pos)) {
                aux = this.getCelda(pos);
                aux.setVisible(true);   //Pone visible la celda de abajo
            }
            pos.moverX(-2);
            if (this.checkCoords(pos)) {
                aux = this.getCelda(pos);
                aux.setVisible(true);   //Pone visible la celda de arriba
            }
            pos.moverX(1);
            pos.moverY(1);
            if (this.checkCoords(pos)) {
                aux = this.getCelda(pos);
                aux.setVisible(true);   //Pone visible la celda derecha
            }
            pos.moverY(-2);
            if (this.checkCoords(pos)) {
                aux = this.getCelda(pos);
                aux.setVisible(true);   //Pone visible la celda izquierda
            }
        }
    }

    public void imprimir() {
        Celda cell;
        Celda aux;
        Posicion pos;

        for (int i = 0; i < MAPAX + 2; i++) {
            System.out.print("&");
        }
        System.out.println("");

        for (int i = 0; i < MAPAY; i++) {   //Ahora recorremos mapa para imprimirlo
            System.out.print("&");
            for (int j = 0; j < MAPAX; j++) {
                if (mapa.get(i).get(j).getVisible()) {
                    switch (mapa.get(i).get(j).getTipo()) {
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
                } else {
                    System.out.print("*");
                }
            }
            System.out.print("&");
            System.out.println("");
        }
        for (int i = 0; i < MAPAX + 2; i++) {
            System.out.print("&");
        }
        System.out.println("");
    }
    
    public boolean checkBuilding(Posicion pos) {
        if(this.getCelda(pos).getTipo().equals("Pradera")){
            return true;
        }
        return false;
    }

    public boolean checkCoords(Posicion pos) {  //Devuelve true si la posicion pasada es valida en el mapa
        if (pos.getX() < MAPAY && pos.getY() < MAPAX) {
            if (pos.getX() >= 0 && pos.getY() >= 0) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<ArrayList<Celda>> getMapa() {
        return mapa;
    }

    public HashMap<String, Personaje> getPersonajes() {
        return new HashMap<String, Personaje>(personajes);
    }

    public HashMap<String, Edificio> getEdificios() {
        return new HashMap<String, Edificio>(edificios);
    }

    public HashMap<String, Recurso> getRecursos() {
        return new HashMap<String, Recurso>(recursos);
    }

}
