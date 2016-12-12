/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import Recursos.Recurso;
import Edificios.Edificio;
import Edificios.Ciudadela;
import Personajes.Paisano;
import Personajes.Personaje;
import Recursos.Bosque;
import Recursos.Madera;
import Recursos.Arbusto;
import Recursos.Comida;
import Recursos.Cantera;
import Recursos.Piedra;
import Recursos.Contenedor;
import Recursos.Pradera;
import Edificios.Casa;
import Edificios.Ciudadela;
import Edificios.Cuartel;
import Edificios.Torre;
import Personajes.Arquero;
import Personajes.Caballero;
import Personajes.Legionario;
import Personajes.Paisano;

/**
 *
 * @author iagolobla
 */
public class Mapa {

    public static final int MAPAX = 16;
    public static final int MAPAY = 8;

    private ArrayList<ArrayList<Celda>> mapa;
    private HashMap<String, Civilizacion> civilizaciones;
    private HashMap<String, Contenedor> recursos;
    int[] cantidades;
    Civilizacion civilizacion;

    public Mapa(int bosques, int canteras, int arbustos, Collection<Civilizacion> civ) {
        if (bosques < 0 || canteras < 0 || arbustos < 0) {
            System.out.println("Valores pasados al mapa menores que 0!");
            return;
        }
        recursos = new HashMap<String, Contenedor>();
        civilizaciones = new HashMap<String, Civilizacion>();
        civilizacion = null;
        cantidades = new int[3];
        /*
       
        0--Bosque
        1--Cantera
        2--Arbusto
         */
        HashMap<String, Boolean> aux = new HashMap<String, Boolean>();
        for (Civilizacion x : civ) {
            aux.put(x.getNombre(), Boolean.FALSE);
            civilizacion = x;
            civilizaciones.put(x.getNombre(), x);
        }
        mapa = new ArrayList<>();
        Celda cell;
        for (int i = 0; i < MAPAY; i++) {
            mapa.add(new ArrayList<>());
            for (int j = 0; j < MAPAX; j++) {   //Inicializamos visible a false para todas las celdas
                cell = new Celda(new Posicion(i, j));

                cell.setVisible(new HashMap<String, Boolean>(aux));
                mapa.get(i).add(cell);
            }
        }
        int j = 0;
        int x1 = 0;
        int y1 = 0;
        String Namec = "ciudadela-1";    //edificios.size() ayuda a crear el nombre
        String Namep = "paisano-1";
        for (Civilizacion c : civ) {
            switch (j) {
                case 0:
                    x1 = MAPAY - 3;
                    y1 = MAPAY - 3;
                    break;
                case 1:
                    x1 = MAPAY - 5;
                    y1 = MAPAX - 3;
                    break;
                case 2:
                    x1 = MAPAY - 6;
                    y1 = MAPAX - 14;
                    break;

            }
            c.getCantidades()[2]++; //Sumamos uno a los paisanos y ciudadelas de esa civ
            c.getCantidades()[0]++;
            Celda nueva = getCelda(x1, y1);
            Edificio ef = new Ciudadela(new Posicion(x1, y1), Namec, c);
            nueva.setEdificio(ef);
            nueva.getVisible().replace(c.getNombre(), Boolean.TRUE);
            c.getEdificios().put(Namec, getCelda(new Posicion(x1, y1)).getEdificio());

            Celda newcell = getCelda(new Posicion(x1, y1 + 1));
            newcell.setPersonajes(new Paisano(Namep, new Posicion(x1, y1 + 1), c));
            newcell.getVisible().replace(c.getNombre(), Boolean.TRUE);
            ArrayList<Personaje> person = getCelda(new Posicion(new Posicion(x1, y1 + 1))).getPersonajes();
            c.getPersonajes().put(Namep, person.get(person.size() - 1));
            j++;
        }
        String Name;

        /*
        AHORA A PARTIR DE AQUI DEBEREIAMOS METER LOS RECURSOS EN TODAS YA LAS CIVILIZACIONES YA QUE SON RECURSOSCOMPARTIDOS
         */
        //Creacion de la ciudadela
        /*String Name = "ciudadela-" + (cantidades[2] + 1);    //edificios.size() ayuda a crear el nombre
        cantidades[2]++;
        mapa.get(3).set(3, new Celda("ciudadela", new Posicion(3, 3), Name));
        edificios.put(Name, getCelda(new Posicion(3, 3)).getEdificio());

        //creacion un personaje
        Name = "paisano-" + (cantidades[0] + 1);
        cantidades[0]++;
        Celda newcell = getCelda(new Posicion(3, 4));
        newcell.setPersonajes(new Personaje("paisano", Name, new Posicion(3, 4)));
        ArrayList<Personaje> person = getCelda(new Posicion(new Posicion(3, 4))).getPersonajes();
        personajes.put(Name, person.get(person.size() - 1));*/
        for (int k = 0; k < bosques; k++) {
            Random rn = new Random();
            int x = rn.nextInt(MAPAY);
            int y = rn.nextInt(MAPAX);
            if (getCelda(new Posicion(x, y)).isLibre()) {
                Name = "bosque-" + (cantidades[0] + 1);
                cantidades[0]++;
                Celda auxiliar = new Celda(new Bosque(Name, new Posicion(x, y), new Madera(150)), new Posicion(x, y));
                auxiliar.setVisible(new HashMap<String, Boolean>(aux));
                mapa.get(x).set(y, auxiliar);
                recursos.put(Name, getCelda(new Posicion(x, y)).getContenedor());
            } else {
                k--;
            }
        }
        for (int k = 0; k < canteras; k++) {
            Random rn = new Random();
            int x = rn.nextInt(MAPAY);
            int y = rn.nextInt(MAPAX);
            if (getCelda(new Posicion(x, y)).isLibre()) {
                Name = "cantera-" + (cantidades[1] + 1);
                cantidades[1]++;
                Celda auxiliar = new Celda(new Cantera(Name, new Posicion(x, y), new Piedra(150)), new Posicion(x, y));
                auxiliar.setVisible(new HashMap<String, Boolean>(aux));
                mapa.get(x).set(y, auxiliar);
                recursos.put(Name, getCelda(new Posicion(x, y)).getContenedor());
            } else {
                k--;
            }
        }
        for (int k = 0; k < arbustos; k++) {
            Random rn = new Random();
            int x = rn.nextInt(MAPAY);
            int y = rn.nextInt(MAPAX);
            if (getCelda(new Posicion(x, y)).isLibre()) {
                Name = "arbusto-" + (cantidades[2] + 1);
                cantidades[2]++;
                Celda auxiliar = new Celda(new Arbusto(Name, new Posicion(x, y), new Comida(150)), new Posicion(x, y));
                auxiliar.setVisible(new HashMap<String, Boolean>(aux));
                mapa.get(x).set(y, auxiliar);
                recursos.put(Name, getCelda(new Posicion(x, y)).getContenedor());
            } else {
                k--;
            }
        }

        //INTRODUCIR BIEN ELEMENTOS BOSQUE EN EL MAPA
        //Recorremos mapa para actualizar las visibilidades
        for (Civilizacion civi : civ) {
            civilizacion = civi;
            this.actualizarVisibilidad();
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

    public Celda getCelda(int x, int y) {//sobrecarga de metodo.
        if (x >= 0 && x < Mapa.MAPAY && y >= 0 && y < Mapa.MAPAX) {
            return mapa.get(x).get(y);
        } else {
            System.out.println("Coordenadas fuera del mapa");
            return null;
        }
    }

    /*public void ponerVisible(Celda cell) {  //Pone visible cell y sus adyacentes
        Celda aux;
        Posicion pos;
        if (cell.isPaisano() || cell.isSoldado() || cell.getTipo().equals("ciudadela") || cell.getTipo().equals("cuartel") || cell.getTipo().equals("casa")) {   //Cuando localiza un soldado o paisano pone
            if (cell.getEdificio() != null) {
                Edificio edif = cell.getEdificio();
                if (edif.getNombreCivilizacion().equals(civilizacion.getNombre()) == false) {
                    return;
                }
            } else if (cell.getPersonajes() != null) {
                ArrayList<Personaje> person = cell.getPersonajes();
                if (person.get(0).getNombreCivilizacion().equals(civilizacion.getNombre()) == false) {
                    return;
                }
            }
            pos = new Posicion(cell.getPos());    //sus celdas adyacentes en visible
            aux = this.getCelda(pos);
            aux.ponerVisible(civilizacion.getNombre());  //Pone visible la celda del personaje

            pos.moverX(1);
            if (this.checkCoords(pos)) {
                aux = this.getCelda(pos);
                aux.ponerVisible(civilizacion.getNombre());   //Pone visible la celda de abajo
            }
            pos.moverX(-2);
            if (this.checkCoords(pos)) {
                aux = this.getCelda(pos);
                aux.ponerVisible(civilizacion.getNombre());   //Pone visible la celda de arriba
            }
            pos.moverX(1);
            pos.moverY(1);
            if (this.checkCoords(pos)) {
                aux = this.getCelda(pos);
                aux.ponerVisible(civilizacion.getNombre());   //Pone visible la celda derecha
            }
            pos.moverY(-2);
            if (this.checkCoords(pos)) {
                aux = this.getCelda(pos);
                aux.ponerVisible(civilizacion.getNombre());   //Pone visible la celda izquierda
            }
        }
    }*/

 /* public void actualizarVisibilidad() {
        Celda cell;
        for (int i = 0; i < MAPAY; i++) {
            for (int j = 0; j < MAPAX; j++) {
                cell = this.getCelda(new Posicion(i, j));
                this.ponerVisible(cell);    //Pone visible esa celda y sus adyacentes
            }
        }
    }*/

 /*public void borrarCivilizacion(String civ) {
        Celda cell;
        for (int i = 0; i < MAPAY; i++) {
            for (int j = 0; j < MAPAX; j++) {
                cell = this.getCelda(i, j);
                if (cell.isPersonaje() || cell.isGrupo()) {
                    Personaje p = cell.getPersonajes().get(0);

                    if (p.getNombreCivilizacion().equals(civ)) {
                        Collection<Personaje> per = cell.getPersonajes();
                        Collection<Grupo> gr = cell.getGrupos();
                        cell.getGrupos().removeAll(gr);
                        cell.getPersonajes().removeAll(per);

                    }
                } else if (cell.isEdificio()) {
                    Edificio ef = cell.getEdificio();
                    if (ef.getNombreCivilizacion().equals(civ)) {
                        cell.setEdificio(null);
                        cell.setTipo("Pradera");
                    }
                }
            }
        }
    }*/

 /*public void turnoTorres() {

        for (Civilizacion civ : civilizaciones.values()) {
            for (Edificio ef : civ.getEdificios().values()) {
                if (ef.getTipo().equals("torre")) {
                    if (!civ.getNombre().equals(civilizacion.getNombre())) {
                        ef.atacarTorre(this);
                    }
                }
            }
        }
    }*/
    public void print() {
        Celda cell;
        Celda aux;
        Posicion pos;
        //this.actualizarVisibilidad();
        for (int i = 0; i < MAPAX + 2; i++) {
            System.out.print(Colores.BACK_AZUL + Colores.TEXT_AZUL + "&" + Colores.TEXT_RESET + Colores.BACK_RESET);
        }
        System.out.println("");

        for (int i = 0; i < MAPAY; i++) {   //Ahora recorremos mapa para imprimirlo
            System.out.print(Colores.BACK_AZUL + Colores.TEXT_AZUL + "&" + Colores.TEXT_RESET + Colores.BACK_RESET);
            for (int j = 0; j < MAPAX; j++) {
                cell = this.getCelda(i, j);
                if (cell.getContenedor() instanceof Pradera) {
                    if (cell.isGrupo()) {
                        if (getCelda(i, j).getPersonajes().get(0).getCivilizacion().getNombre().equals(civilizacion.getNombre())) {
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_AZUL + "G" + Colores.TEXT_RESET + Colores.BACK_RESET);
                        } else {
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_ROJO + "G" + Colores.TEXT_RESET + Colores.BACK_RESET);
                        }
                    } else if (cell.getPersonaje() instanceof Paisano) {
                        if (getCelda(i, j).getPersonajes().get(0).getCivilizacion().getNombre().equals(civilizacion.getNombre())) {
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_AZUL + "P" + Colores.TEXT_RESET + Colores.BACK_RESET);
                        } else {
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_ROJO + "P" + Colores.TEXT_RESET + Colores.BACK_RESET);
                        }

                    } else if (cell.getPersonaje() instanceof Legionario) {
                        if (getCelda(i, j).getPersonajes().get(0).getCivilizacion().getNombre().equals(civilizacion.getNombre())) {
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_AZUL + "L" + Colores.TEXT_RESET + Colores.BACK_RESET);
                        } else {
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_ROJO + "L" + Colores.TEXT_RESET + Colores.BACK_RESET);
                        }
                    } else if (cell.getPersonaje() instanceof Arquero) {
                        if (getCelda(i, j).getPersonajes().get(0).getCivilizacion().getNombre().equals(civilizacion.getNombre())) {
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_AZUL + "A" + Colores.TEXT_RESET + Colores.BACK_RESET);
                        } else {
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_ROJO + "A" + Colores.TEXT_RESET + Colores.BACK_RESET);
                        }

                    } else if (cell.getPersonaje() instanceof Caballero) {
                        if (getCelda(i, j).getPersonajes().get(0).getCivilizacion().getNombre().equals(civilizacion.getNombre())) {
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_AZUL + "C" + Colores.TEXT_RESET + Colores.BACK_RESET);
                        } else {
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_ROJO + "C" + Colores.TEXT_RESET + Colores.BACK_RESET);
                        }

                    } else if (cell.getEdificio() instanceof Casa) {
                        if (getCelda(i, j).getPersonajes().get(0).getCivilizacion().getNombre().equals(civilizacion.getNombre())) {
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_AZUL + "^" + Colores.TEXT_RESET + Colores.BACK_RESET);
                        } else {
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_ROJO + "^" + Colores.TEXT_RESET + Colores.BACK_RESET);
                        }

                    } else if (cell.getEdificio() instanceof Ciudadela) {
                        if (getCelda(i, j).getPersonajes().get(0).getCivilizacion().getNombre().equals(civilizacion.getNombre())) {
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_AZUL + "♛" + Colores.TEXT_RESET + Colores.BACK_RESET);
                        } else {
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_ROJO + "♛" + Colores.TEXT_RESET + Colores.BACK_RESET);
                        }

                    } else if (cell.getEdificio() instanceof Torre) {
                        if (getCelda(i, j).getEdificio().getCivilizacion().getNombre().equals(civilizacion.getNombre())) {
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_AZUL + "T" + Colores.TEXT_RESET + Colores.BACK_RESET);
                        } else {
                            System.out.print(Colores.BACK_VERDE + Colores.TEXT_ROJO + "T" + Colores.TEXT_RESET + Colores.BACK_RESET);
                        }

                    } else if (getCelda(i, j).getEdificio().getCivilizacion().getNombre().equals(civilizacion.getNombre())) {
                        System.out.print(Colores.BACK_VERDE + Colores.TEXT_AZUL + "♜" + Colores.TEXT_RESET + Colores.BACK_RESET);
                    } else {
                        System.out.print(Colores.BACK_VERDE + Colores.TEXT_ROJO + "♜" + Colores.TEXT_RESET + Colores.BACK_RESET);
                    }
                } else if (cell.getContenedor() instanceof Bosque) {
                    System.out.print(Colores.BACK_VERDE + Colores.TEXT_AMARILLO + "@" + Colores.BACK_RESET + Colores.TEXT_RESET);

                } else if (cell.getContenedor() instanceof Arbusto) {
                    System.out.print(Colores.BACK_VERDE + Colores.TEXT_AMARILLO + "♣" + Colores.BACK_RESET + Colores.TEXT_RESET);

                } else {
                    System.out.print(Colores.BACK_VERDE + Colores.TEXT_AMARILLO + "♦" + Colores.BACK_RESET + Colores.TEXT_RESET);
                }

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
                if (mapa.get(i).get(j).isVisible(civilizacion.getNombre())) {
                    switch (mapa.get(i).get(j).getTipo()) {
                        case ("Pradera"):
                            if (getCelda(i, j).isGrupo()) {
                                if (getCelda(i, j).getPersonajes().get(0).getNombreCivilizacion().equals(civilizacion.getNombre())) {
                                    System.out.print(Colores.BACK_VERDE + Colores.TEXT_AZUL + "G" + Colores.TEXT_RESET + Colores.BACK_RESET);
                                } else {
                                    System.out.print(Colores.BACK_VERDE + Colores.TEXT_ROJO + "G" + Colores.TEXT_RESET + Colores.BACK_RESET);
                                }
                            } else if (getCelda(i, j).isPaisano()) {   //Si tiene un paisano
                                if (getCelda(i, j).getPersonajes().get(0).getNombreCivilizacion().equals(civilizacion.getNombre())) {
                                    System.out.print(Colores.BACK_VERDE + Colores.TEXT_AZUL + "P" + Colores.TEXT_RESET + Colores.BACK_RESET);
                                } else {
                                    System.out.print(Colores.BACK_VERDE + Colores.TEXT_ROJO + "P" + Colores.TEXT_RESET + Colores.BACK_RESET);
                                }
                            } else if (getCelda(i, j).isSoldado()) {    //Si tiene un soldado
                                if (getCelda(i, j).getPersonajes().get(0).getNombreCivilizacion().equals(civilizacion.getNombre())) {
                                    System.out.print(Colores.BACK_VERDE + Colores.TEXT_AZUL + "S" + Colores.TEXT_RESET + Colores.BACK_RESET);
                                } else {
                                    System.out.print(Colores.BACK_VERDE + Colores.TEXT_ROJO + "S" + Colores.TEXT_RESET + Colores.BACK_RESET);
                                }
                            } else {    //Si no tiene ninguno
                                System.out.print(Colores.BACK_VERDE + " " + Colores.BACK_RESET);
                            }
                            break;
                        case ("ciudadela"):
                            if (getCelda(i, j).getEdificio().getNombreCivilizacion().equals(civilizacion.getNombre())) {
                                System.out.print(Colores.BACK_VERDE + Colores.TEXT_AZUL + "♛" + Colores.TEXT_RESET + Colores.BACK_RESET);
                            } else {
                                System.out.print(Colores.BACK_VERDE + Colores.TEXT_ROJO + "♛" + Colores.TEXT_RESET + Colores.BACK_RESET);
                            }
                            break;
                        case ("cuartel"):
                            if (getCelda(i, j).getEdificio().getNombreCivilizacion().equals(civilizacion.getNombre())) {
                                System.out.print(Colores.BACK_VERDE + Colores.TEXT_AZUL + "♜" + Colores.TEXT_RESET + Colores.BACK_RESET);
                            } else {
                                System.out.print(Colores.BACK_VERDE + Colores.TEXT_ROJO + "♜" + Colores.TEXT_RESET + Colores.BACK_RESET);
                            }
                            break;
                        case ("casa"):
                            if (getCelda(i, j).getEdificio().getNombreCivilizacion().equals(civilizacion.getNombre())) {
                                System.out.print(Colores.BACK_VERDE + Colores.TEXT_AZUL + "^" + Colores.TEXT_RESET + Colores.BACK_RESET);
                            } else {
                                System.out.print(Colores.BACK_VERDE + Colores.TEXT_ROJO + "^" + Colores.TEXT_RESET + Colores.BACK_RESET);
                            }
                            break;
                        case "torre":
                            if (getCelda(i, j).getEdificio().getNombreCivilizacion().equals(civilizacion.getNombre())) {
                                System.out.print(Colores.BACK_VERDE + Colores.TEXT_AZUL + "T" + Colores.TEXT_RESET + Colores.BACK_RESET);
                            } else {
                                System.out.print(Colores.BACK_VERDE + Colores.TEXT_ROJO + "T" + Colores.TEXT_RESET + Colores.BACK_RESET);
                            }
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
                            System.out.println("Error, tipo pasado incorrecto!");
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

    public HashMap<String, Civilizacion> getCivilizaciones() {
        return civilizaciones;
    }

    public void setCivilizaciones(HashMap<String, Civilizacion> civilizaciones) {
        this.civilizaciones = civilizaciones;
    }

    public Civilizacion getCivilizacion() {
        return civilizacion;
    }

    public void setCivilizacion(Civilizacion civilizacion) {
        this.civilizacion = civilizacion;
    }
}
