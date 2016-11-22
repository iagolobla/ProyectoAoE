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
import java.util.Random;

public class Mapa {

    private ArrayList<ArrayList<Celda>> mapa;
    private HashMap<String, Personaje> personajes;
    private HashMap<String, Edificio> edificios;
    private HashMap<String, Recurso> recursos;
    private int[] cantidades;

    public static final int MAPAX = 16;
    public static final int MAPAY = 8;

    public Mapa(int bosques, int canteras, int arbustos) {
        if(bosques < 0 || canteras < 0 || arbustos < 0){
            System.out.println("Valores pasados al mapa menores que 0!");
            return;
        }
        
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
        String Name = "ciudadela-" + (cantidades[2] + 1);    //edificios.size() ayuda a crear el nombre
        cantidades[2]++;
        mapa.get(3).set(3, new Celda("ciudadela", new Posicion(3, 3), Name));
        edificios.put(Name, getCelda(new Posicion(3, 3)).getEdificio());

        //creacion un personaje
        Name = "paisano-" + (cantidades[0] + 1);
        cantidades[0]++;
        mapa.get(3).set(4, new Celda("paisano", new Posicion(3, 4), Name));
        personajes.put(Name, getCelda(new Posicion(3, 4)).getPersonaje());//Aqui creo que hay ALIASING Assign return variable to new variable.

        for (int i = 0; i < bosques; i++) {
            Random rn = new Random();
            int x = rn.nextInt(MAPAY);
            int y = rn.nextInt(MAPAX);
            if (getCelda(new Posicion(x, y)).getTipo() == "Pradera") {
                Name = "bosque-" + (cantidades[5] + 1);
                cantidades[5]++;
                mapa.get(x).set(y, new Celda("bosque", new Posicion(x, y), Name));
                recursos.put(Name, getCelda(new Posicion(x, y)).getRecurso());
            } else {
                i--;
            }
        }
        for (int i = 0; i < canteras; i++) {
            Random rn = new Random();
            int x = rn.nextInt(MAPAY);
            int y = rn.nextInt(MAPAX);
            if (getCelda(new Posicion(x, y)).getTipo() == "Pradera") {
                Name = "cantera-" + (cantidades[6] + 1);
                cantidades[6]++;
                mapa.get(x).set(y, new Celda("cantera", new Posicion(x, y), Name));
                recursos.put(Name, getCelda(new Posicion(x, y)).getRecurso());
            }else{
                i--;
            }
        }
        for (int i = 0; i < bosques; i++) {
            Random rn = new Random();
            int x = rn.nextInt(MAPAY);
            int y = rn.nextInt(MAPAX);
            if (getCelda(new Posicion(x, y)).getTipo() == "Pradera") {
                Name = "arbusto-" + (cantidades[7] + 1);
                cantidades[7]++;
                mapa.get(x).set(y, new Celda("arbusto", new Posicion(x, y), Name));
                recursos.put(Name, getCelda(new Posicion(x, y)).getRecurso());
            } else {
                i--;
            }
        }

        //INTRODUCIR BIEN ELEMENTOS BOSQUE EN EL MAPA
        //Recorremos mapa para actualizar las visibilidades
        this.actualizarVisibilidad();
    }

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
        String Name = "ciudadela-" + (cantidades[2] + 1);
        cantidades[2]++;
        mapa.get(3).set(3, new Celda("ciudadela", new Posicion(3, 3), Name));
        edificios.put(Name, getCelda(new Posicion(3, 3)).getEdificio());

        //creacion un personaje
        Name = "paisano-" + (cantidades[0] + 1);
        cantidades[0]++;
        mapa.get(3).set(4, new Celda("paisano", new Posicion(3, 4), Name));
        personajes.put(Name, getCelda(new Posicion(3, 4)).getPersonaje());//Aqui creo que hay ALIASING Assign return variable to new variable.
        for (int i = 6; i < 8; i++) {
            for (int j = 6; j < 8; j++) {
                Name = "bosque-" + (cantidades[5] + 1);
                cantidades[5]++;
                mapa.get(i).set(j, new Celda("bosque", new Posicion(i, j), Name));
                recursos.put(Name, getCelda(new Posicion(i, j)).getRecurso());
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Name = "cantera-" + (cantidades[6] + 1);
                cantidades[6]++;
                mapa.get(i).set(j, new Celda("cantera", new Posicion(i, j), Name));
                recursos.put(Name, getCelda(new Posicion(i, j)).getRecurso());
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 6; j < 8; j++) {
                Name = "arbusto-" + (cantidades[7] + 1);
                cantidades[7]++;
                mapa.get(i).set(j, new Celda("arbusto", new Posicion(i, j), Name));
                recursos.put(Name, getCelda(new Posicion(i, j)).getRecurso());
            }
        }
        //INTRODUCIR BIEN ELEMENTOS BOSQUE EN EL MAPA

        //Recorremos mapa para actualizar las visibilidades
        this.actualizarVisibilidad();
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

    public Celda getCelda(int x, int y) {//sobrecarga de metodo.
        if (x >= 0 && x < Mapa.MAPAY && y >= 0 && y < Mapa.MAPAX) {
            return mapa.get(x).get(y);
        } else {
            System.out.println("Coordenadas fuera del mapa");
            return null;
        }
    }

    public void ponerVisible(Celda cell) {
        Celda aux;
        Posicion pos;
        if (cell.getTipo().equals("soldado") || cell.getTipo().equals("paisano") || cell.getTipo().equals("ciudadela") || cell.getTipo().equals("cuartel") || cell.getTipo().equals("casa")) {   //Cuando localiza un soldado o paisano pone
            pos = new Posicion(cell.getPos());                                 //sus celdas adyacentes en visible
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

    public void actualizarVisibilidad() {
        Celda cell;
        for (int i = 0; i < MAPAY; i++) {
            for (int j = 0; j < MAPAX; j++) {
                cell = this.getCelda(new Posicion(i, j));
                this.ponerVisible(cell);    //Pone visible esa celda y sus adyacentes
            }
        }
    }

    public void imprimir() {
        Celda cell;
        Celda aux;
        Posicion pos;

        this.actualizarVisibilidad();

        for (int i = 0; i < MAPAX + 2; i++) {
            System.out.print(Colores.BACK_AZUL + Colores.TEXT_AZUL + "&" + Colores.TEXT_RESET + Colores.BACK_RESET);
        }
        System.out.println("");

        for (int i = 0; i < MAPAY; i++) {   //Ahora recorremos mapa para imprimirlo
            System.out.print(Colores.BACK_AZUL + Colores.TEXT_AZUL + "&" + Colores.TEXT_RESET + Colores.BACK_RESET);
            for (int j = 0; j < MAPAX; j++) {
                if (mapa.get(i).get(j).getVisible()) {
                    switch (mapa.get(i).get(j).getTipo()) {
                        case ("Pradera"):
                            System.out.print(Colores.BACK_VERDE + " " + Colores.BACK_RESET);
                            break;
                        case ("ciudadela"):
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_NEGRO + "♛" + Colores.TEXT_RESET + Colores.BACK_RESET);    //⍟♜♛
                            break;
                        case ("cuartel"):
                            System.out.print(Colores.BACK_VERDE + "♜" + Colores.BACK_RESET);
                            break;
                        case ("casa"):
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_NEGRO + "^" + Colores.TEXT_RESET + Colores.BACK_RESET);
                            break;
                        case "soldado":
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_AZUL + "S" + Colores.TEXT_RESET + Colores.BACK_RESET);
                            break;
                        case "paisano":
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_ROJO + "P" + Colores.TEXT_RESET + Colores.BACK_RESET);
                            break;
                        case "bosque":
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_AMARILLO + "@" + Colores.BACK_RESET + Colores.TEXT_RESET);
                            break;
                        case "cantera":
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_BLANCO + "♦" + Colores.BACK_RESET + Colores.TEXT_RESET);  //Ahora la piedra son ^
                            break;
                        case "arbusto":
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_VERDELIGHT + "♣" + Colores.BACK_RESET + Colores.TEXT_RESET);  //Y los arbustos *
                            break;
                        default:
                            System.out.println("Error, tipo de edificio incorrecto");
                    }
                } else {
                    System.out.print(Colores.BACK_GRIS + " " + Colores.BACK_RESET);
                }
            }
            System.out.print(Colores.BACK_AZUL + Colores.TEXT_AZUL + "&" + Colores.TEXT_RESET + Colores.BACK_RESET);
            System.out.println("");
        }
        for (int i = 0; i < MAPAX + 2; i++) {
            System.out.print(Colores.BACK_AZUL + Colores.TEXT_AZUL + "&" + Colores.TEXT_RESET + Colores.BACK_RESET);
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

    public void setMapa(ArrayList<ArrayList<Celda>> mapa) {
        if (mapa != null) {
            this.mapa = new ArrayList<>(mapa);
        } else {
            System.out.println("Mapa pasado es nulo!");
        }
    }

    public void setPersonajes(HashMap<String, Personaje> personajes) {
        if (personajes != null) {
            this.personajes = new HashMap<>(personajes);
        } else {
            System.out.println("HashMap de personajes pasado nulo!");
        }
    }

    public void setEdificios(HashMap<String, Edificio> edificios) {
        if (edificios != null) {
            this.edificios = new HashMap<>(edificios);
        } else {
            System.out.println("HashMap de edificios pasado nulo!");
        }
    }

    public void setRecursos(HashMap<String, Recurso> recursos) {
        if (recursos != null) {
            this.recursos = new HashMap<>(recursos);
        } else {
            System.out.println("HashMap de recursos pasado nulo!");
        }
    }

    public void setCantidades(int[] cantidades) {
        if (cantidades != null) {
            this.cantidades = cantidades;
        } else {
            System.out.println("Array de cantidades pasado nulo!");
        }
    }

}
