/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Mapa.Celda;
import Mapa.Mapa;
import Mapa.Civilizacion;
import java.util.HashMap;

/**
 *
 * @author javier
 */
public class Personaje {

    public static final int SALUD_PAISANO = 150;
    public static final int SALUD_SOLDADO = 200;

    private String tipo;
    private String nombreGrupo;
    private String nombreCivilizacion;
    private int armadura;
    private int salud;
    private int ataque;
    private int capRecolectar;
    private String Nombre;  //Aqui se necesita poner un nombre al personaje para usar eso como clave en el hashmap de personajes en el mapa
    private Posicion posicion;
    private int cantidadRecolectada;
    private String tipoRecurso; //Como solo se puede recolectar un tipo de recurso de cada vez, guardaremos aqui cual
    private boolean grupo;

    public Personaje(String tipo, String Nombre, Posicion pos) {
        if (pos == null) {
            System.out.println("Posicion pasada a Personaje nula!");
            return;
        }
        posicion = new Posicion(pos);
        switch (tipo) {
            case "soldado":
                this.tipo = tipo;
                armadura = 100;
                salud = SALUD_SOLDADO;
                ataque = 50;
                capRecolectar = 0;
                cantidadRecolectada = 0;
                this.Nombre = Nombre;   //El parametro nombre debe ser unico para cada personaje
                grupo = false;
                break;
            case "paisano":
                this.tipo = tipo;
                armadura = 50;
                salud = SALUD_PAISANO;
                ataque = 10;
                capRecolectar = 100;
                cantidadRecolectada = 0;
                this.Nombre = Nombre;
                grupo = false;
                break;
            default:
                System.out.println("Tipo mal introducido");

        }
    }

    public Personaje(String tipo, String Nombre, Posicion pos, String civilizacion) {
        if (pos == null) {
            System.out.println("Posicion pasada a Personaje nula!");
            return;
        }
        posicion = new Posicion(pos);
        nombreCivilizacion = civilizacion;
        switch (tipo) {
            case "soldado":
                this.tipo = tipo;
                armadura = 100;
                salud = SALUD_SOLDADO;
                ataque = 50;
                capRecolectar = 0;
                cantidadRecolectada = 0;
                this.Nombre = Nombre;   //El parametro nombre debe ser unico para cada personaje
                grupo = false;
                break;
            case "paisano":
                this.tipo = tipo;
                armadura = 50;
                salud = SALUD_PAISANO;
                ataque = 10;
                capRecolectar = 100;
                cantidadRecolectada = 0;
                this.Nombre = Nombre;
                grupo = false;
                break;
            default:
                System.out.println("Tipo mal introducido");

        }
    }

    public void almacenarRecurso(Edificio ciudadela, Civilizacion C) {
        if (ciudadela == null) {  //Si el parametro es nulo
            System.out.println("Ciudadela pasada nula!");
            return;
        }
        if (ciudadela.getTipo().equals("casa")) { //En caso de que el edificio sea una casa
            System.out.println("No se pueden almacenar Recursos en una Casa!");
            return;
        }
        if (ciudadela.getTipo().equals("cuartel")) {  //En caso de que el edificio sea un cuartel
            System.out.println("No se pueden almacenar Recursos en un Cuartel!");
            return;
        }
        if (this.getTipo().equals("soldado")) {   //Comprueba si es un paisano o un soldado
            System.out.println("Los soldados no cargan Recursos!");
            return;
        }
        if (this.getCantidadRecolectada() <= 0) {  //Comprobamos si tiene algun recurso
            System.out.println(Nombre + " no tiene recursos!");
            return;
        }

        switch (this.tipoRecurso) {
            case "bosque":
                System.out.println("El " + Nombre + " va a almacenar " + cantidadRecolectada + " uds. de Madera en " + ciudadela.getNombre());
                C.setMadera(C.getMadera() + cantidadRecolectada);
                cantidadRecolectada = 0;
                break;
            case "cantera":
                System.out.println("El " + Nombre + " va a almacenar " + cantidadRecolectada + " uds. de Piedra en " + ciudadela.getNombre());
                C.setPiedra(C.getPiedra() + cantidadRecolectada);
                cantidadRecolectada = 0;
                break;
            case "arbusto":
                System.out.println("El " + Nombre + " a almacenar " + cantidadRecolectada + " uds. de Comida en " + ciudadela.getNombre());
                C.setComida(C.getComida() + cantidadRecolectada);
                cantidadRecolectada = 0;
                break;
        }
    }
/////////////////////////////////////////////////////

    /*
    ESTA MAL MAL. HAY UN PROBLEMA CON EL CONSTRUCTOR DE CELDA YA QUE SE CARGA LOS HASH MAP DE VISIVILIDADES
     */
    //////////////////////////////////////////////
    public void construir(Mapa mapa, String direccion, String Edificio) {//DESCONTAR RECURSOS AL CONSTRUIR.
        if (this.getTipo().equals("soldado")) {   //Comprueba si es un paisano o un soldado
            System.out.println("Los soldados no pueden construir!");
            return;
        }
        Posicion pos = new Posicion(posicion);
        switch (direccion) {
            case "s":
                pos.moverX(1);
                break;
            case "n":
                pos.moverX(-1);
                break;
            case "e":
                pos.moverY(1);
                break;
            case "o":
                pos.moverY(-1);
                break;
            default:
                System.out.println("Error, direccion no valida!");

        }
        switch (Edificio.toLowerCase()) {
            case "casa":
                if (mapa.getCivilizacion().getMadera() >= 50 && mapa.getCivilizacion().getPiedra() >= 20) {
                    if (mapa.checkCoords(pos) && mapa.checkBuilding(pos)) { //Comprueba que la posicion esta en el mapa y que no esta ocupada
                        String Name = "casa-" + (mapa.getCivilizacion().getCantidades()[3] + 1);
                        mapa.getCivilizacion().getCantidades()[3]++;
                        Celda nueva = mapa.getCelda(pos);
                        nueva.setEdificio(new Edificio("casa", new Posicion(pos), Name, mapa.getCivilizacion().getNombre()));
                        nueva.setTipo("casa");
                        nueva.getVisible().replace(mapa.getCivilizacion().getNombre(), Boolean.TRUE);
                        mapa.getCivilizacion().getEdificios().put(Name, mapa.getCelda(new Posicion(pos)).getEdificio());
                        /*mapa.getMapa().get(pos.getX()).set(pos.getY(), new Celda("casa", new Posicion(pos), Name, mapa.getCivilizacion().getNombre())); //Metemos la celda en su posicion del mapa
                        mapa.getCelda(pos).getEdificio().setNombreCivilizacion(nombreCivilizacion);
                        mapa.getCivilizacion().getEdificios().put(Name, mapa.getCelda(new Posicion(pos)).getEdificio());*/
                        System.out.println("Casa construida en " + pos + "Se han gastado 20 unidades de piedra y 50 de madera");

                        mapa.getCivilizacion().setMadera(mapa.getCivilizacion().getMadera() - 50);
                        mapa.getCivilizacion().setPiedra(mapa.getCivilizacion().getPiedra() - 20);
                        System.out.println("Quedan los siguientes recursos: ");
                        System.out.println("Comida: " + mapa.getCivilizacion().getComida());
                        System.out.println("Madera: " + mapa.getCivilizacion().getMadera());
                        System.out.println("Piedra: " + mapa.getCivilizacion().getPiedra());
                    } else {
                        System.out.println("No se puede Contruir en esa direccion!");
                    }
                } else {
                    System.out.println("No hay suficientes recursos para construir la casa");
                }
                break;
            case "cuartel":
                if (mapa.getCivilizacion().getMadera() >= 50 && mapa.getCivilizacion().getPiedra() >= 20) {
                    if (mapa.checkCoords(pos) && mapa.checkBuilding(pos)) { //Comprueba que la posicion esta en el mapa y que no esta ocupada
                        String Name = "cuartel-" + (mapa.getCivilizacion().getCantidades()[4] + 1);
                        mapa.getCivilizacion().getCantidades()[4]++;
                        Celda nueva = mapa.getCelda(pos);
                        nueva.setEdificio(new Edificio("cuartel", new Posicion(pos), Name, mapa.getCivilizacion().getNombre()));
                        nueva.setTipo("cuartel");
                        nueva.getVisible().replace(mapa.getCivilizacion().getNombre(), Boolean.TRUE);
                        mapa.getCivilizacion().getEdificios().put(Name, mapa.getCelda(new Posicion(pos)).getEdificio());
                        /*mapa.getMapa().get(pos.getX()).set(pos.getY(), new Celda("cuartel", new Posicion(pos), Name, mapa.getCivilizacion().getNombre())); //Metemos la celda en su posicion del mapa
                        mapa.getCelda(pos).getEdificio().setNombreCivilizacion(nombreCivilizacion);
                        mapa.getCivilizacion().getEdificios().put(Name, mapa.getCelda(new Posicion(pos)).getEdificio());*/
                        System.out.println("Cuartel construido en " + pos + "Se han gastado 20 unidades de piedra y 50 de madera");

                        mapa.getCivilizacion().setMadera(mapa.getCivilizacion().getMadera() - 50);
                        mapa.getCivilizacion().setPiedra(mapa.getCivilizacion().getPiedra() - 20);
                        System.out.println("Quedan los siguientes recursos: ");
                        System.out.println("Comida: " + mapa.getCivilizacion().getComida());
                        System.out.println("Madera: " + mapa.getCivilizacion().getMadera());
                        System.out.println("Piedra: " + mapa.getCivilizacion().getPiedra());
                    } else {
                        System.out.println("No se puede Contruir en esa direccion!");
                    }
                } else {
                    System.out.println("No hay suficientes recursos para construir el cuartel");
                }
                break;
            case "ciudadela":
                if (mapa.getCivilizacion().getMadera() >= 50 && mapa.getCivilizacion().getPiedra() >= 20) {
                    if (mapa.checkCoords(pos) && mapa.checkBuilding(pos)) { //Comprueba que la posicion esta en el mapa y que no esta ocupada
                        String Name = "ciudadela-" + (mapa.getCivilizacion().getCantidades()[2] + 1);
                        mapa.getCivilizacion().getCantidades()[2]++;
                        Celda nueva = mapa.getCelda(pos);
                        nueva.setEdificio(new Edificio("ciudadela", new Posicion(pos), Name, mapa.getCivilizacion().getNombre()));
                        nueva.setTipo("ciudadela");
                        nueva.getVisible().replace(mapa.getCivilizacion().getNombre(), Boolean.TRUE);
                        mapa.getCivilizacion().getEdificios().put(Name, mapa.getCelda(new Posicion(pos)).getEdificio());
                        /*mapa.getMapa().get(pos.getX()).set(pos.getY(), new Celda("ciudadela", new Posicion(pos), Name, mapa.getCivilizacion().getNombre())); //Metemos la celda en su posicion del mapa
                        mapa.getCelda(pos).getEdificio().setNombreCivilizacion(nombreCivilizacion);
                        mapa.getCivilizacion().getEdificios().put(Name, mapa.getCelda(new Posicion(pos)).getEdificio());*/
                        System.out.println("Ciudadela construida en " + pos + "Se han gastado 20 unidades de piedra y 50 de madera");

                        mapa.getCivilizacion().setMadera(mapa.getCivilizacion().getMadera() - 50);
                        mapa.getCivilizacion().setPiedra(mapa.getCivilizacion().getPiedra() - 20);
                        System.out.println("Quedan los siguientes recursos: ");
                        System.out.println("Comida: " + mapa.getCivilizacion().getComida());
                        System.out.println("Madera: " + mapa.getCivilizacion().getMadera());
                        System.out.println("Piedra: " + mapa.getCivilizacion().getPiedra());
                    } else {
                        System.out.println("No se puede Contruir en esa direccion!");
                    }
                } else {
                    System.out.println("No hay suficientes recursos para construir la ciudadela");
                }
                break;
            case "torre":
                if (mapa.getCivilizacion().getMadera() >= 80 && mapa.getCivilizacion().getPiedra() >= 40) {
                    if (mapa.checkCoords(pos) && mapa.checkBuilding(pos)) { //Comprueba que la posicion esta en el mapa y que no esta ocupada
                        String Name = "torre-" + (mapa.getCivilizacion().getCantidades()[5] + 1);
                        mapa.getCivilizacion().getCantidades()[5]++;
                        Celda nueva = mapa.getCelda(pos);
                        nueva.setEdificio(new Edificio("torre", new Posicion(pos), Name, mapa.getCivilizacion().getNombre()));
                        nueva.setTipo("torre");
                        nueva.getVisible().replace(mapa.getCivilizacion().getNombre(), Boolean.TRUE);
                        mapa.getCivilizacion().getEdificios().put(Name, mapa.getCelda(new Posicion(pos)).getEdificio());
                        /*mapa.getMapa().get(pos.getX()).set(pos.getY(), new Celda("torre", new Posicion(pos), Name, mapa.getCivilizacion().getNombre())); //Metemos la celda en su posicion del mapa
                        mapa.getCelda(pos).getEdificio().setNombreCivilizacion(nombreCivilizacion);
                        mapa.getCivilizacion().getEdificios().put(Name, mapa.getCelda(new Posicion(pos)).getEdificio());*/
                        System.out.println("Torre construida en " + pos + "Se han gastado 40 unidades de piedra y 80 de madera");

                        mapa.getCivilizacion().setMadera(mapa.getCivilizacion().getMadera() - 80);
                        mapa.getCivilizacion().setPiedra(mapa.getCivilizacion().getPiedra() - 40);
                        System.out.println("Quedan los siguientes recursos: ");
                        System.out.println("Comida: " + mapa.getCivilizacion().getComida());
                        System.out.println("Madera: " + mapa.getCivilizacion().getMadera());
                        System.out.println("Piedra: " + mapa.getCivilizacion().getPiedra());
                    } else {
                        System.out.println("No se puede Contruir en esa direccion!");
                    }
                } else {
                    System.out.println("No hay suficientes recursos para construir la ciudadela");
                }
                break;
            default:
                System.out.println("Error, direccion no valida!");

        }

    }

    public void reparar(Mapa mapa, String direccion) {
        if (this.getTipo().equals("soldado")) {   //Comprueba si es un paisano o un soldado
            System.out.println("Los soldados no pueden reparar!");
            return;
        }
        Posicion pos = new Posicion(posicion);
        switch (direccion) {
            case "s":
                pos.moverX(1);
                break;
            case "n":
                pos.moverX(-1);
                break;
            case "e":
                pos.moverY(1);
                break;
            case "o":
                pos.moverY(-1);
                break;
            default:
                System.out.println("Error, direccion no valida!");

        }
        if (mapa.getCelda(pos).getTipo().equals("casa") || mapa.getCelda(pos).getTipo().equals("ciudadela") || mapa.getCelda(pos).getTipo().equals("cuartel")) {
            Edificio edificio = mapa.getCelda(pos).getEdificio();
            int vida = edificio.getSalud();
            if (vida == 0) {  //Comprobamos si el edificio esta destruido
                System.out.println("Este edificio esta destruido, no se puede reparar");
                return;
            }
            if (Edificio.SALUDCASA - vida == 0) {
                System.out.println("El edificio ya esta a full vida");
                return;
            }
            switch (edificio.getTipo()) {
                case "casa":
                    if (mapa.getCivilizacion().getMadera() > (Edificio.SALUDCASA - vida) && mapa.getCivilizacion().getMadera() > (Edificio.SALUDCASA - vida)) {
                        edificio.setSalud(Edificio.SALUDCASA);
                        System.out.println("Se han recuperado " + (Edificio.SALUDCASA - vida) + " puntos de salud");
                        mapa.getCivilizacion().setMadera(mapa.getCivilizacion().getMadera() - (Edificio.SALUDCASA - vida));
                        mapa.getCivilizacion().setPiedra(mapa.getCivilizacion().getPiedra() - (Edificio.SALUDCASA - vida));
                        System.out.println("Costes de la reparacion: " + (Edificio.SALUDCASA - vida) + " uds. de Madera y " + (Edificio.SALUDCASA - vida) + " uds de Piedra");
                    } else {
                        System.out.println("No hay recursos suficientes para reparar el edificio");
                    }
                    break;
                case "ciudadela":
                    if (mapa.getCivilizacion().getMadera() > (Edificio.SALUDCIUDADELA - vida) && mapa.getCivilizacion().getMadera() > (Edificio.SALUDCIUDADELA - vida)) {
                        edificio.setSalud(Edificio.SALUDCIUDADELA);
                        System.out.println("Se han recuperado " + (Edificio.SALUDCIUDADELA - vida) + " puntos de salud");
                        mapa.getCivilizacion().setMadera(mapa.getCivilizacion().getMadera() - (Edificio.SALUDCASA - vida));
                        mapa.getCivilizacion().setPiedra(mapa.getCivilizacion().getPiedra() - (Edificio.SALUDCASA - vida));
                        System.out.println("Costes de la reparacion: " + (Edificio.SALUDCASA - vida) + " uds. de Madera y " + (Edificio.SALUDCASA - vida) + " uds de Piedra");

                    } else {
                        System.out.println("No hay recursos suficientes para reparar el edificio");
                    }
                    break;
                case "cuartel":
                    if (mapa.getCivilizacion().getMadera() > (Edificio.SALUDCUARTEL - vida) && mapa.getCivilizacion().getMadera() > (Edificio.SALUDCUARTEL - vida)) {
                        edificio.setSalud(Edificio.SALUDCUARTEL);
                        System.out.println("Se han recuperado " + (Edificio.SALUDCUARTEL - vida) + " puntos de salud");
                        mapa.getCivilizacion().setMadera(mapa.getCivilizacion().getMadera() - (Edificio.SALUDCASA - vida));
                        mapa.getCivilizacion().setPiedra(mapa.getCivilizacion().getPiedra() - (Edificio.SALUDCASA - vida));
                        System.out.println("Costes de la reparacion: " + (Edificio.SALUDCASA - vida) + " uds. de Madera y " + (Edificio.SALUDCASA - vida) + " uds de Piedra");
                    } else {
                        System.out.println("No hay recursos suficientes para reparar el edificio");
                    }
                    break;
                default:
                    System.out.println("Error, direccion no valida!");

            }
        } else {
            System.out.println("No hay un edificio en esta celda!");
        }
    }

    public boolean isPaisano() {
        return tipo.equals("paisano");
    }

    public boolean isSoldado() {
        return tipo.equals("soldado");
    }

    public void reparar(Mapa mapa, String direccion, int cantidad) {   //Sobrecarga: solo repara la cantidad pasada
        if (this.getTipo().equals("soldado")) {   //Comprueba si es un paisano o un soldado
            System.out.println("Los soldados no pueden reparar!");
            return;
        }
        Posicion pos = new Posicion(posicion);
        switch (direccion) {
            case "s":
                pos.moverX(1);
                break;
            case "n":
                pos.moverX(-1);
                break;
            case "e":
                pos.moverY(1);
                break;
            case "o":
                pos.moverY(-1);
                break;
            default:
                System.out.println("Error, direccion no valida!");

        }
        if (mapa.getCelda(pos).getTipo().equals("casa") || mapa.getCelda(pos).getTipo().equals("ciudadela") || mapa.getCelda(pos).getTipo().equals("cuartel")) {
            Edificio edificio = mapa.getCelda(pos).getEdificio();
            int vida = edificio.getSalud();
            if (vida == 0) {  //Comprobamos si el edificio esta destruido
                System.out.println("Este edificio esta destruido, no se puede reparar");
                return;
            }
            if (Edificio.SALUDCASA - vida == 0) {
                System.out.println("El edificio ya esta a full vida");
                return;
            }
            int cantidadReparar;

            switch (edificio.getTipo()) {
                case "casa":
                    cantidadReparar = Edificio.SALUDCASA - vida;
                    if (cantidad < cantidadReparar) {
                        cantidadReparar = cantidad;
                    }
                    if (mapa.getCivilizacion().getMadera() > (cantidadReparar) && mapa.getCivilizacion().getMadera() > (cantidadReparar)) {
                        edificio.setSalud(vida + cantidadReparar);
                        System.out.println("Se han recuperado " + (cantidadReparar) + " puntos de salud");
                        mapa.getCivilizacion().setMadera(mapa.getCivilizacion().getMadera() - (cantidadReparar));
                        mapa.getCivilizacion().setPiedra(mapa.getCivilizacion().getPiedra() - (cantidadReparar));
                        System.out.println("Costes de la reparacion: " + (cantidadReparar) + " uds. de Madera y " + (cantidadReparar) + " uds de Piedra");
                    } else {
                        System.out.println("No hay recursos suficientes para reparar el edificio");
                    }
                    break;
                case "ciudadela":
                    cantidadReparar = Edificio.SALUDCIUDADELA - vida;
                    if (cantidad < cantidadReparar) {
                        cantidadReparar = cantidad;
                    }
                    if (mapa.getCivilizacion().getMadera() > (cantidadReparar) && mapa.getCivilizacion().getMadera() > (cantidadReparar)) {
                        edificio.setSalud(vida + cantidadReparar);
                        System.out.println("Se han recuperado " + (cantidadReparar) + " puntos de salud");
                        mapa.getCivilizacion().setMadera(mapa.getCivilizacion().getMadera() - (cantidadReparar));
                        mapa.getCivilizacion().setPiedra(mapa.getCivilizacion().getPiedra() - (cantidadReparar));
                        System.out.println("Costes de la reparacion: " + (cantidadReparar) + " uds. de Madera y " + (cantidadReparar) + " uds de Piedra");

                    } else {
                        System.out.println("No hay recursos suficientes para reparar el edificio");
                    }
                    break;
                case "cuartel":
                    cantidadReparar = Edificio.SALUDCUARTEL - vida;
                    if (cantidad < cantidadReparar) {
                        cantidadReparar = cantidad;
                    }
                    if (mapa.getCivilizacion().getMadera() > (cantidadReparar) && mapa.getCivilizacion().getMadera() > (cantidadReparar)) {
                        edificio.setSalud(vida + cantidadReparar);
                        System.out.println("Se han recuperado " + (cantidadReparar) + " puntos de salud");
                        mapa.getCivilizacion().setMadera(mapa.getCivilizacion().getMadera() - (cantidadReparar));
                        mapa.getCivilizacion().setPiedra(mapa.getCivilizacion().getPiedra() - (cantidadReparar));
                        System.out.println("Costes de la reparacion: " + (cantidadReparar) + " uds. de Madera y " + cantidadReparar + " uds de Piedra");
                    } else {
                        System.out.println("No hay recursos suficientes para reparar el edificio");
                    }
                    break;
                default:
                    System.out.println("Error, direccion no valida!");

            }
        } else {
            System.out.println("No hay un edificio en esta celda!");
        }
    }

    public boolean defender(Mapa mapa, String direccion) {   //Si funciona devuelve true
        if (mapa == null) {
            System.out.println("Mapa pasado nulo!");
            return false;
        }
        Posicion pos = new Posicion(posicion);
        Celda cell = mapa.getCelda(pos);
        switch (direccion) {
            case "n":
                pos.moverX(-1);
                break;
            case "s":
                pos.moverX(1);
                break;
            case "e":
                pos.moverY(1);
                break;
            case "o":
                pos.moverY(-1);
                break;
            default:
                System.out.println("Direccion pasada no valida!");
                return false;
        }
        Celda newCell = mapa.getCelda(pos);
        Edificio ef = newCell.getEdificio();
        if (ef == null) {
            System.out.println("En esta posicion no hay ningun edificio!");
            return false;
        }
        if (!(ef.getNombreCivilizacion().equals(this.getNombreCivilizacion()))) {
            System.out.println("Este no es un edificio aliado!");
            return false;
        }
        if ((ef.getCapPersonajes() - ef.getNPersonajes()) <= 0) {
            System.out.println("No hay mas sitio aqui dentro!");
            return false;
        }
        if (this.isPaisano()) {
            salud = SALUD_PAISANO;
        } else {
            salud = SALUD_SOLDADO;
        }

        ef.setAtaque(ef.getAtaque() + ataque);
        ef.setDefensa(ef.getDefensa() + armadura);
        posicion = pos; //Actualizamos posicion del Pj
        ef.getPersonajes().put(Nombre, this);
        ef.setNPersonajes(ef.getNPersonajes() + 1);
        cell.quitarPersonaje(this); //Quitamos al personaje de la celda

        mapa.actualizarVisibilidad();
        mapa.imprimir();
        return true;
    }

    public Posicion moverPj(Mapa mapa, String direccion) {

        Celda cell = mapa.getCelda(posicion);
        Posicion pos = new Posicion(posicion);

        switch (direccion) {
            case "s":
                pos.moverX(1);
                break;
            case "n":
                pos.moverX(-1);
                break;
            case "e":
                pos.moverY(1);
                break;
            case "o":
                pos.moverY(-1);
                break;
            default:
                System.out.println("Error, direccion no valida!");

        }
        if (mapa.checkCoords(pos) && mapa.checkBuilding(pos)) { //Comprueba que la posicion esta en el mapa y que no esta ocupada

            Celda newcell = mapa.getCelda(pos);

            if (newcell.isPaisano() || newcell.isSoldado()) {
                if (newcell.getPersonajes().get(0).getNombreCivilizacion().equals(nombreCivilizacion)) {
                    this.setPosicion(pos);
                    newcell.setPersonajes(this);   //Metemos el personaje en la nueva celda
                    mapa.getMapa().get(posicion.getX()).set(posicion.getY(), newcell); //Metemos la celda en su posicion del mapa

                    cell.quitarPersonaje(this);    //Ponemos la celda donde estaba el personaje como pradera
                    //Recorremos mapa para actualizar las visibilidades
                    mapa.actualizarVisibilidad();
                    return new Posicion(posicion);
                }
            } else {
                this.setPosicion(pos);
                newcell.setPersonajes(this);   //Metemos el personaje en la nueva celda
                mapa.getMapa().get(posicion.getX()).set(posicion.getY(), newcell); //Metemos la celda en su posicion del mapa

                cell.quitarPersonaje(this);    //Ponemos la celda donde estaba el personaje como pradera
                //Recorremos mapa para actualizar las visibilidades
                mapa.actualizarVisibilidad();
                return new Posicion(posicion);
            }

        }
        return posicion;

    }

    public void recolectar(Recurso recurso, Mapa mapa) {
        if (recurso == null) {    //Comprobamos si se pasa bien el argumento
            System.out.println("Argumento Recurso pasado nulo!");
            return;
        }

        if (tipo.equals("soldado")) { //Comprueba que el personaje no sea un soldado
            System.out.println("Un soldado no puede recolectar!");
            return;
        }
        if (cantidadRecolectada < capRecolectar) {  //Comprueba que la cantidad que lleva sea menor a su capacidad total
            if (cantidadRecolectada == 0) {   //En caso de que el paisano no lleve nada encima, puede recolectar el recurso que sea
                cantidadRecolectada = recurso.restarCantidad(capRecolectar, mapa);    //Devuelve la cantidad obtenida y en caso de agotarse el recurso lo elimina
                tipoRecurso = recurso.getTipo();   //Establece el tipo de recurso que carga el personaje
                System.out.println("El  " + Nombre + "  ha recogido " + cantidadRecolectada + "uds de " + tipoRecurso);  //Esto va a haber que cambiarlo, el tipoRecurso imprime bosque, no madera
                if (cantidadRecolectada == capRecolectar) {
                    System.out.println("El  " + Nombre + "  no puede recolectar mas");
                }
            } else {    //Si ya ha recogido recursos de algun tipo
                if (!recurso.getTipo().equals(tipoRecurso)) { //Comprobamos que el recurso sea del tipo que estamos recolectando
                    System.out.println("No se puede recolectar ese recurso, ya se esta cargando " + tipoRecurso);
                    return;
                }
                int cantidadRestante = cantidadRecolectada;
                cantidadRecolectada += recurso.restarCantidad(capRecolectar - cantidadRecolectada, mapa);   //Si el recurso es del mismo tipo se añade recurso al personaje y se resta la capacidad restante del personaje al recurso
                cantidadRestante = cantidadRecolectada - cantidadRestante;
                System.out.println("El " + Nombre + " ha recogido " + cantidadRestante + "uds de " + tipoRecurso);
            }
        } else {
            System.out.println("El  " + Nombre + "  no puede recolectar mas!");
        }
    }

    @Override
    public String toString() {
        String impresion = "";
        impresion += "Tipo: " + tipo + "\n";
        impresion += "Armadura: " + armadura + "\n";
        impresion += "Salud: " + salud + "\n";
        impresion += "Ataque: " + ataque + "\n";
        impresion += "Capacidad Total Recolección: " + capRecolectar + "\n";
        impresion += "Cantidad de Recurso: " + cantidadRecolectada + "\n";
        if (cantidadRecolectada > 0) //En caso de que lleve algun recurso se imprime el tipo
        {
            impresion += "Tipo de Recurso: " + tipoRecurso + "\n";
        }
        impresion += "Nombre: " + Nombre + "\n";
        if (grupo) {
            impresion += "Pertenece a: " + nombreGrupo + "\n";
        }
        impresion += "Posicion: " + posicion + "\n";

        return impresion;
    }
    //GETTERS Y SETTERS

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        if (tipo.equals("arbusto") || tipo.equals("bosque") || tipo.equals("cantera")) {
            this.tipoRecurso = tipoRecurso;
        } else {
            System.out.println("Tipo Recurso introducido incorrecto!");
        }
    }

    public int getCantidadRecolectada() {
        return cantidadRecolectada;
    }

    public Posicion getPosicion() {
        return new Posicion(posicion);
    }

    public void setPosicion(Posicion p) {
        if (p.getX() >= 0 && p.getX() < Mapa.MAPAY && p.getY() >= 0 && p.getY() < Mapa.MAPAX) {
            posicion = new Posicion(p);
        } else {
            System.out.println("Posicion introducida fuera de los limites del mapa!");
        }
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
        if (salud >= 0) {
            this.salud = salud;
        } else {
            System.out.println("Salud introducida debe ser mayor que 0!");
        }
    }

    public int getAtaque() {
        return ataque;
    }

    public int getCapRecolectar() {
        return capRecolectar;
    }

    public void setCapRecolectar(int capRecolectar) {
        if (capRecolectar >= 0) {
            this.capRecolectar = capRecolectar;
        } else {
            System.out.println("Capacidad de Recolectar introducida debe ser mayor que 0!");
        }
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setTipo(String tipo) {
        if (tipo.equals("paisano") || tipo.equals("soldado")) {
            this.tipo = tipo;
        } else {
            System.out.println("Tipo Personaje mal introducido!");
        }
    }

    public void setArmadura(int armadura) {
        if (armadura >= 0) {
            this.armadura = armadura;
        } else {
            System.out.println("Armadura introducida debe ser mayor que 0!");
        }
    }

    public void setAtaque(int ataque) {
        if (ataque >= 0) {
            this.ataque = ataque;
        } else {
            System.out.println("Ataque introducido debe ser mayor que 0!");
        }
    }

    public void setCantidadRecolectada(int cantidadRecolectada) {
        if (cantidadRecolectada >= 0) {
            this.cantidadRecolectada = cantidadRecolectada;
        } else {
            System.out.println("Cantidad Recolectada introducida debe ser mayor que 0!");
        }
    }

    public String getNombreCivilizacion() {
        return nombreCivilizacion;
    }

    public void setNombreCivilizacion(String nombreCivilizacion) {
        this.nombreCivilizacion = nombreCivilizacion;
    }

    public boolean isGrupo() {
        return grupo;
    }

    public void setGrupo(boolean grupo) {
        this.grupo = grupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

}
