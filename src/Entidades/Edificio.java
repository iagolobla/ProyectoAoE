/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.ArrayList;
import java.util.HashMap;
import Mapa.Celda;
import Mapa.Mapa;

/**
 *
 * @author iagolobla
 */
public class Edificio {

    public static final int SALUDCIUDADELA = 200;
    public static final int SALUDTORRE = 400;
    public static final int SALUDCASA = 50;
    public static final int SALUDCUARTEL = 150;
    public static final int CAPACIDADCUARTEL = 15;
    public static final int CAPACIDADCASA = 10;

    private HashMap<String, Personaje> Personajes;
    private String tipo;
    private int salud;
    private Posicion posicion;
    private String nombre;
    private String nombreCivilizacion;
    private int capPersonajes;  //Establece cuantos personajes pueden entrar a defender un edificio
    private int NPersonajes;
    private int ataque;
    private int madera;
    private int piedra;
    private int comida;

    public Edificio(String tipe, Posicion posicion, String Nombre) {
        //Igualar posicion y posicion evitando aliasing
        if (posicion == null) {
            System.out.println("Posicion pasada a Edificio nula!");
            return;
        }

        Personajes = new HashMap<String, Personaje>();
        this.posicion = new Posicion(posicion);
        NPersonajes = 0;
        switch (tipe) {
            case ("ciudadela"):
                tipo = tipe;
                salud = SALUDCIUDADELA;//solo es para hacer la prueba
                nombre = Nombre;
                madera = 100;//son los valores iniciales que toman(se los damos nosotros).
                piedra = 100;
                comida = 100;
                capPersonajes = 10;
                break;
            case ("cuartel"):
                tipo = tipe;
                salud = SALUDCUARTEL;
                nombre = Nombre;
                madera = 0;
                piedra = 0;
                comida = 0;
                capPersonajes = 15;
                break;
            case ("casa"):
                tipo = tipe;
                salud = SALUDCASA;
                nombre = Nombre;
                madera = 0;
                piedra = 0;
                comida = 0;
                capPersonajes = 5;
                break;
            case "torre":
                tipo = tipe;
                salud = SALUDTORRE;
                nombre = Nombre;
                madera = 0;
                piedra = 0;
                comida = 0;
                capPersonajes = 2;
                break;
            default:
                System.out.println("Error, tipo de edificio incorrecto");
        }
    }

    public Edificio(String tipe, Posicion posicion, String Nombre, String civilizacion) {
        //Igualar posicion y posicion evitando aliasing
        if (posicion == null) {
            System.out.println("Posicion pasada a Edificio nula!");
            return;
        }

        Personajes = new HashMap<String, Personaje>();
        nombreCivilizacion = civilizacion;
        NPersonajes = 0;
        this.posicion = new Posicion(posicion);
        switch (tipe) {
            case ("ciudadela"):
                tipo = tipe;
                salud = SALUDCIUDADELA;//solo es para hacer la prueba
                nombre = Nombre;
                madera = 100;//son los valores iniciales que toman(se los damos nosotros).
                piedra = 100;
                comida = 100;
                capPersonajes = 10;
                break;
            case ("cuartel"):
                tipo = tipe;
                salud = SALUDCUARTEL;
                nombre = Nombre;
                madera = 0;
                piedra = 0;
                comida = 0;
                capPersonajes = 15;
                break;
            case ("casa"):
                tipo = tipe;
                salud = SALUDCASA;
                nombre = Nombre;
                madera = 0;
                piedra = 0;
                comida = 0;
                capPersonajes = 5;
                break;
            case "torre":
                tipo = tipe;
                salud = SALUDTORRE;
                nombre = Nombre;
                madera = 0;
                piedra = 0;
                comida = 0;
                capPersonajes = 2;
                break;
            default:
                System.out.println("Error, tipo de edificio incorrecto");
        }
    }

    public void crearPaisano(Mapa mapa) {
        if (mapa == null) {
            System.out.println("Mapa pasado nulo!");
            return;
        }
        if (!this.tipo.equals("ciudadela")) {
            System.out.println("Este edificio no puede crear Paisanos");
        }
        if (mapa.getCivilizacion().getCantidades()[3] * CAPACIDADCASA <= mapa.getCivilizacion().getCantidades()[0] + mapa.getCivilizacion().getCantidades()[1]) {   //Comprobamos si la suma de paisanos y soldados es mayor igual que la capacidad de almacenamiento
            System.out.println("No hay sitio para mas Paisanos, se necesitan mas casas!");
            return;
        }
        Posicion pos1 = new Posicion(posicion);
        pos1.moverX(-1);

        if (!(mapa.checkCoords(pos1) && mapa.checkBuilding(pos1))) {  //Comprobamos la primera posicion, si no es valida
            pos1.moverX(2);
        }
        if (!(mapa.checkCoords(pos1) && mapa.checkBuilding(pos1))) {  //Comprobamos la segunda posicion, si no es valida
            pos1.moverX(-1);
            pos1.moverY(1);
        }
        if (!(mapa.checkCoords(pos1) && mapa.checkBuilding(pos1))) {  //Comprobamos la tercera posicion, si no es valida
            pos1.moverY(-2);
        }
        if (!(mapa.checkCoords(pos1) && mapa.checkBuilding(pos1))) {  //Comprobamos la cuarta posicion, si no es valida
            System.out.println("No es posible crear el paisano, todas las posiciones en torno a la Ciudadela estan ocupadas");
            return;
        }

        String Name = "paisano-" + (mapa.getCivilizacion().getCantidades()[0] + 1);
        mapa.getCivilizacion().getCantidades()[0]++;
        Celda newcell = mapa.getCelda(pos1);
        newcell.setPersonajes(new Personaje("paisano", Name, pos1, mapa.getCivilizacion().getNombre()));
        mapa.getMapa().get(pos1.getX()).set(pos1.getY(), newcell); //Metemos la celda en su posicion del mapa

        ArrayList<Personaje> person = mapa.getCelda(new Posicion(pos1)).getPersonajes();
        mapa.getCivilizacion().getPersonajes().put(Name, person.get(person.size() - 1));

        mapa.getCivilizacion().getEdificios().get(nombre).setComida(mapa.getCivilizacion().getEdificios().get(nombre).getComida() - 10);
        System.out.println("El " + Name + " se encuentra en la posicion " + pos1);
        System.out.println("Quedan " + ((mapa.getCivilizacion().getCantidades()[3] * CAPACIDADCASA) - (mapa.getCivilizacion().getCantidades()[0] + mapa.getCivilizacion().getCantidades()[1])) + " espacios de almacenamiento");
        System.out.println("Se han gastado 10 unidades de comida en crear el paisano");
        System.out.println("Quedan los siguientes recursos: ");
        System.out.println("Comida: " + mapa.getCivilizacion().getEdificios().get(nombre).getComida());
        System.out.println("Madera: " + mapa.getCivilizacion().getEdificios().get(nombre).getMadera());
        System.out.println("Piedra: " + mapa.getCivilizacion().getEdificios().get(nombre).getPiedra());
        //Hay que hacer actualizacion de visibilidades ya que hay un nuevo personaje
        mapa.actualizarVisibilidad();
    }

    public void crearSoldado(Mapa mapa) {
        if (!this.tipo.equals("cuartel")) {
            System.out.println("Este edificio no puede crear Soldados");
        }
        if (mapa.getCivilizacion().getCantidades()[3] * CAPACIDADCASA <= mapa.getCivilizacion().getCantidades()[0] + mapa.getCivilizacion().getCantidades()[1]) {   //Comprobamos si la suma de paisanos y soldados es mayor igual que la capacidad de almacenamiento
            System.out.println("No hay sitio para mas Soldados, se necesitan mas casas!");
            return;
        }
        Posicion pos1 = new Posicion(posicion);
        pos1.moverX(-1);

        if (!(mapa.checkCoords(pos1) && mapa.checkBuilding(pos1))) {  //Comprobamos la primera posicion, si no es valida
            pos1.moverX(2);
        }
        if (!(mapa.checkCoords(pos1) && mapa.checkBuilding(pos1))) {  //Comprobamos la segunda posicion, si no es valida
            pos1.moverX(-1);
            pos1.moverY(1);
        }
        if (!(mapa.checkCoords(pos1) && mapa.checkBuilding(pos1))) {  //Comprobamos la tercera posicion, si no es valida
            pos1.moverY(-2);
        }
        if (!(mapa.checkCoords(pos1) && mapa.checkBuilding(pos1))) {  //Comprobamos la cuarta posicion, si no es valida
            System.out.println("No es posible crear el soldado, todas las posiciones en torno al Cuartel estan ocupadas");
            return;
        }

        String Name = "soldado-" + (mapa.getCivilizacion().getCantidades()[1] + 1);
        mapa.getCivilizacion().getCantidades()[1]++;
        Celda newcell = mapa.getCelda(pos1);
        newcell.setPersonajes(new Personaje("soldado", Name, pos1, mapa.getCivilizacion().getNombre()));
        mapa.getMapa().get(pos1.getX()).set(pos1.getY(), newcell); //Metemos la celda en su posicion del mapa

        ArrayList<Personaje> person = mapa.getCelda(new Posicion(pos1)).getPersonajes();
        mapa.getCivilizacion().getPersonajes().put(Name, person.get(person.size() - 1));

        mapa.getCivilizacion().getEdificios().get("ciudadela-1").setComida(mapa.getCivilizacion().getEdificios().get("ciudadela-1").getComida() - 10);
        System.out.println("El " + Name + " se encuentra en la posicion " + pos1);
        System.out.println("Quedan " + ((mapa.getCivilizacion().getCantidades()[3] * CAPACIDADCASA) - (mapa.getCivilizacion().getCantidades()[0] + mapa.getCivilizacion().getCantidades()[1])) + " espacios de almacenamiento");
        System.out.println("Se han gastado 10 unidades de comida en crear el soldado");
        System.out.println("Quedan los siguientes recursos: ");
        System.out.println("Comida: " + mapa.getCivilizacion().getEdificios().get("ciudadela-1").getComida());
        System.out.println("Madera: " + mapa.getCivilizacion().getEdificios().get("ciudadela-1").getMadera());
        System.out.println("Piedra: " + mapa.getCivilizacion().getEdificios().get("ciudadela-1").getPiedra());
        //Hay que hacer actualizacion de visibilidades ya que hay un nuevo personaje
        mapa.actualizarVisibilidad();

    }

    @Override
    public String toString() {
        String impresion = "";
        int salud_total = salud;
        if (Personajes.size() > 0) {
            impresion += "Personajes dentro del edificio:\n";
            for (Personaje p : Personajes.values()) {
                impresion += "\t" + p.getNombre() + "\n";
                salud_total += p.getSalud();
            }
        }
        impresion += "Tipo: " + tipo + "\n";
        impresion += "Salud: " + salud_total + "\n";
        impresion += "Ataque: " + ataque + "\n";
        impresion += "Posicion: " + posicion + "\n";
        if (getTipo().equals("ciudadela")) {
            impresion += "Comida: " + comida + "\n";
            impresion += "Piedra: " + piedra + "\n";
            impresion += "Madera: " + madera + "\n";
        }

        return impresion;
    }

    //GETTERS Y SETTERS
    public String getNombre() {
        return new String(nombre);
    }

    public void setTipo(String tipo) {
        if (tipo.equals("casa") || tipo.equals("ciudadela") || tipo.equals("cuartel")) {
            this.tipo = tipo;
        } else {
            System.out.println("Tipo edificio introducido incorrecto!");
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Posicion getPosicion() {
        return new Posicion(posicion);
    }

    public void setPosicion(Posicion p) {
        if (p.getX() >= 0 && p.getX() < Mapa.MAPAY && p.getY() >= 0 && p.getY() < Mapa.MAPAX) {
            posicion = new Posicion(p);
        }
    }

    public String getTipo() {
        return tipo;
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

    public int getMadera() {
        return madera;
    }

    public void setMadera(int madera) {
        if (madera >= 0) {
            this.madera = madera;
        } else {
            System.out.println("Cantidad Madera mal introducida!");
        }
    }

    public int getPiedra() {
        return piedra;
    }

    public void setPiedra(int piedra) {
        if (piedra >= 0) {
            this.piedra = piedra;
        } else {
            System.out.println("Cantidad Piedra mal introducida!");
        }
    }

    public int getComida() {
        return comida;
    }

    public void setComida(int comida) {
        if (comida >= 0) {
            this.comida = comida;
        } else {
            System.out.println("Cantidad Comida mal introducida!");
        }
    }

    public int getCapPersonajes() {

        return capPersonajes;
    }

    public void setCapPersonajes(int cap) {
        if (cap >= 0);
        capPersonajes = cap;
    }

    public int getNPersonajes() {
        return NPersonajes;
    }

    public void setNPersonajes(int NPersonajes) {
        if (NPersonajes >= 0) {
            this.NPersonajes = NPersonajes;
        }
    }

    public HashMap<String, Personaje> getPersonajes() {
        return Personajes;
    }

    public void setPersonajes(HashMap<String, Personaje> Personajes) {
        this.Personajes = Personajes;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        if (ataque >= 0) {
            this.ataque = ataque;
        }
    }

    public String getNombreCivilizacion() {
        return nombreCivilizacion;
    }

    public void setNombreCivilizacion(String nombreCivilizacion) {
        this.nombreCivilizacion = nombreCivilizacion;
    }

}
