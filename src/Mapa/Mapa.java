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

    private ArrayList<ArrayList<Celda>> mapa;
    private HashMap<String, Personaje> personajes;
    private HashMap<String, Edificio> edificios;
    private HashMap<String, Recurso> recursos;
    private int[] cantidades;

    public final int MAPAX = 16;
    public final int MAPAY = 8;

    public Mapa() {
        edificios = new HashMap<String, Edificio>();
        personajes = new HashMap<String, Personaje>();
        recursos = new HashMap<String, Recurso>();
        cantidades = new int[8];//guardara las cantidades de personajes, soldados,etc.
        /*
        0--Paisano
        1--Soldado
        2--Ciudadela
        3--Casa
        4--Cuartel
        5--Bosque
        6--Cantera
        7--Arbusto
         */
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
        String Name = "Ciudadela-" + (cantidades[2] + 1);    //edificios.size() ayuda a crear el nombre
        cantidades[2]++;
        mapa.get(3).set(3, new Celda("Ciudadela", new Posicion(3, 3), Name));
        edificios.put(Name, getCelda(new Posicion(3, 3)).getEf());

        //creacion un personaje
        Name = "Paisano-" + (cantidades[0] + 1);
        cantidades[0]++;
        mapa.get(3).set(4, new Celda("Paisano", new Posicion(3, 4), Name));
        personajes.put(Name, getCelda(new Posicion(3, 4)).getPj());//Aqui creo que hay ALIASING Assign return variable to new variable.
        for (int i = 6; i < 8; i++) {
            for (int j = 6; j < 8; j++) {
                Name = "Bosque-" + (cantidades[5] + 1);
                cantidades[5]++;
                mapa.get(i).set(j, new Celda("Bosque", new Posicion(i, j), Name));
                recursos.put(Name, getCelda(new Posicion(i, j)).getRs());
            }
        }
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Name = "Cantera-" + (cantidades[6] + 1);
                cantidades[6]++;
                mapa.get(i).set(j, new Celda("Cantera", new Posicion(i, j), Name));
                recursos.put(Name, getCelda(new Posicion(i, j)).getRs());
            }
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 6; j < 8; j++) {
                Name = "Arbusto-" + (cantidades[7] + 1);
                cantidades[7]++;
                mapa.get(i).set(j, new Celda("Arbusto", new Posicion(i, j), Name));
                recursos.put(Name, getCelda(new Posicion(i, j)).getRs());
            }
        }
        //INTRODUCIR BIEN ELEMENTOS BOSQUE EN EL MAPA

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
                        case "Bosque":
                            System.out.print("B");
                            break;
                        case "Cantera":
                            System.out.print("p");
                            break;
                        case "Arbusto":
                            System.out.print("^");
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
        if (this.getCelda(pos).getTipo().equals("Pradera")) {
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
        return personajes;
    }

    public HashMap<String, Edificio> getEdificios() {
        return edificios;
    }

    public HashMap<String, Recurso> getRecursos() {
        return recursos;
    }

    public int[] getCantidades() {
        return cantidades;
    }

}
