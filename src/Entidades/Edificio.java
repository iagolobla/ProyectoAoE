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

    public static final int SALUDCIUDADELA = 200;
    public static final int SALUDCASA = 50;
    public static final int SALUDCUARTEL = 150;
    public static final int CAPACIDADCUARTEL = 15;
    public static final int CAPACIDADCASA = 10;

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
            case ("ciudadela"):
                tipo = tipe;
                salud = SALUDCIUDADELA;//solo es para hacer la prueba
                nombre = Nombre;
                madera = 100;//son los valores iniciales que toman(se los damos nosotros).
                piedra = 100;
                comida = 100;
                break;
            case ("cuartel"):
                tipo = tipe;
                salud = SALUDCUARTEL;
                nombre = Nombre;
                break;
            case ("casa"):
                tipo = tipe;
                salud = SALUDCASA;
                nombre = Nombre;
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
        if (mapa.getCantidades()[3] * CAPACIDADCASA <= mapa.getCantidades()[0] + mapa.getCantidades()[1]) {   //Comprobamos si la suma de paisanos y soldados es mayor igual que la capacidad de almacenamiento
            System.out.println("No hay sitio para mas Paisanos, se necesitan mas casas!");
            return;
        }
        Posicion pos1 = new Posicion(punto);
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

        String Name = "paisano-" + (mapa.getCantidades()[0] + 1);
        mapa.getCantidades()[0]++;
        mapa.getMapa().get(pos1.getX()).set(pos1.getY(), new Celda("paisano", new Posicion(pos1), Name)); //Metemos la celda en su posicion del mapa
        
        mapa.getPersonajes().put(Name, mapa.getCelda(new Posicion(pos1)).getPersonaje());
        
        mapa.getEdificios().get(nombre).setComida(mapa.getEdificios().get(nombre).getComida() - 10);
        
        System.out.println("Se han gastado 10 unidades de comida en crear el paisano");
        System.out.println("Quedan los siguientes recursos: ");
        System.out.println("Comida: " + mapa.getEdificios().get(nombre).getComida());
        System.out.println("Madera: " + mapa.getEdificios().get(nombre).getMadera());
        System.out.println("Piedra: " + mapa.getEdificios().get(nombre).getPiedra());
        //Hay que hacer actualizacion de visibilidades ya que hay un nuevo personaje
        mapa.actualizarVisibilidad();
    }

    public void crearSoldado(Mapa mapa) {
        if (!this.tipo.equals("cuartel")) {
            System.out.println("Este edificio no puede crear Soldados");
        }
        if (mapa.getCantidades()[3] * CAPACIDADCASA <= mapa.getCantidades()[0] + mapa.getCantidades()[1]) {   //Comprobamos si la suma de paisanos y soldados es mayor igual que la capacidad de almacenamiento
            System.out.println("No hay sitio para mas Soldados, se necesitan mas casas!");
            return;
        }
        Posicion pos1 = new Posicion(punto);
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

        String Name = "soldado-" + (mapa.getCantidades()[1] + 1);
        mapa.getCantidades()[1]++;
        mapa.getMapa().get(pos1.getX()).set(pos1.getY(), new Celda("soldado", new Posicion(pos1), Name)); //Metemos la celda en su posicion del mapa
        
        mapa.getPersonajes().put(Name, mapa.getCelda(new Posicion(pos1)).getPersonaje());
        mapa.getEdificios().get(nombre).setComida(mapa.getEdificios().get("ciudadela-1").getComida() - 10);
        System.out.println("Se han gastado 10 unidades de comida en crear el soldado");
        System.out.println("Quedan los siguientes recursos: ");
        System.out.println("Comida: " + mapa.getEdificios().get("ciudadela-1").getComida());
        System.out.println("Madera: " + mapa.getEdificios().get("ciudadela-1").getMadera());
        System.out.println("Piedra: " + mapa.getEdificios().get("ciudadela-1").getPiedra());
        //Hay que hacer actualizacion de visibilidades ya que hay un nuevo personaje
        mapa.actualizarVisibilidad();

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
    public String getNombre() {
        return new String(nombre);
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Posicion getPosicion() {//A LO MEJOR HAY QUE CAMBIAR EL NOMBRE DEL ATRIBUTO
        return new Posicion(punto);
    }

    public void setPunto(Posicion p) {
        if (p.getX() >= 0 && p.getX() < Mapa.MAPAX && p.getY() >= 0 && p.getY() < Mapa.MAPAY) {
            punto = new Posicion(p);
        }
    }

    public String getTipo() {
        return tipo;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        if (salud>=0) {
            this.salud = salud;
        }
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
