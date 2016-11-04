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

    private final int MAPAX = 16;
    private final int MAPAY = 8;

    public Mapa() {
        edificios = new HashMap<String,Edificio>();
        personajes = new HashMap<String,Personaje>();
        recursos = new HashMap<String,Recurso>();
        mapa = new ArrayList<>();
        for (int i = 0; i < MAPAY; i++) {
            mapa.add(new ArrayList<>());
            for (int j = 0; j < MAPAX; j++) {
                mapa.get(i).add(new Celda(new Posicion(i, j)));
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

    public void imprimir() {
        for (int i = 0; i < MAPAX + 2; i++) {
            System.out.print("&");
        }
        System.out.println("");
        for (int i = 0; i < MAPAY; i++) {
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

    public boolean checkCeldaCoords(Celda cell) {  //Devuelve true si la celda tiene una posicion valida en el mapa
        if (cell.getPosicion().getX() < 0 || cell.getPosicion().getX() >= MAPAX) {
            return false;
        }
        if (cell.getPosicion().getY() < 0 || cell.getPosicion().getY() >= MAPAY) {
            return false;
        }
        return true;

    }

    public void moverPj(String Nombre, String direccion) {
        Personaje Pj = (Personaje) personajes.get(Nombre);
        Posicion pos = Pj.getPosicion();
        Celda cell = this.getCelda(pos);

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

        Celda newcell = this.getCelda(pos);

        if (!this.checkCeldaCoords(newcell) && !newcell.getTipo().equals("Pradera")) {    //Compruebo que la celda sea valida y que no haya nada en ella
            System.out.println("Imposible mover en esa direccion");
            return;
        }

        Pj.setPosicion(pos);    //Actualizamos la posicion del personaje
        newcell.setPersonaje(Pj);   //Metemos el personaje en la nueva celda

        this.mapa.get(pos.getX()).set(pos.getY(), newcell); //Metemos la celda en su posicion del mapa

        cell.liberarCelda();    //Ponemos la celda donde estaba el personaje como pradera
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
