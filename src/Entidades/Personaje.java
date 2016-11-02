/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

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

    public Personaje(String tipo, String Nombre,Posicion pos) {
        posicion=new Posicion(pos);
        switch (tipo) {
            case "Soldado":
                this.tipo=tipo;
                armadura = 100;
                salud = 200;
                ataque = 50;
                capRecolectar = 0;
                this.Nombre = Nombre;   //El parametro nombre debe ser unico para cada personaje
                break;
            case "Paisano":
                this.tipo=tipo;
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

    public Edificio crearCuartel(String Nombre, String direccion) {
        switch (direccion) {
            case "NORTE":
                return new Edificio("Cuartel", new Posicion(posicion.getX(), posicion.getY() + 1), "Cuartel");    //Posteriormente hay que calcular el nombre
            case "SUR":
                return new Edificio("Cuartel", new Posicion(posicion.getX(), posicion.getY() - 1), "Cuartel");    //Posteriormente hay que calcular el nombre
            case "ESTE":
                return new Edificio("Cuartel", new Posicion(posicion.getX() + 1, posicion.getY()), "Cuartel");    //Posteriormente hay que calcular el nombre
            case "OESTE":
                return new Edificio("Cuartel", new Posicion(posicion.getX() - 1, posicion.getY() + 1), "Cuartel");    //Posteriormente hay que calcular el nombre
            default:
                return null;
        }
    }
    
    public void mover(String direccion){
        
    }
    //GETTERS Y SETTERS

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
}
