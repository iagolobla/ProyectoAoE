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
    private int cantidadRecolectada;
    private String tipoRecurso; //Como solo se puede recolectar un tipo de recurso de cada vez, guardaremos aqui cual

    public Personaje(String tipo, String Nombre, Posicion pos) {
        posicion = new Posicion(pos);
        switch (tipo) {
            case "Soldado":
                this.tipo = tipo;
                armadura = 100;
                salud = 200;
                ataque = 50;
                capRecolectar = 0;
                cantidadRecolectada = 0;
                this.Nombre = Nombre;   //El parametro nombre debe ser unico para cada personaje
                break;
            case "Paisano":
                this.tipo = tipo;
                armadura = 50;
                salud = 150;
                ataque = 10;
                capRecolectar = 100;
                cantidadRecolectada = 0;
                this.Nombre = Nombre;
                break;
            default:
                System.out.println("Tipo mal introducido");

        }
    }

    public void almacenarRecurso(Edificio ciudadela) {
        if (ciudadela == null) {  //Si el parametro es nulo
            System.out.println("Ciudadela pasada nula!");
            return;
        }
        if (this.getTipo().equals("Soldado")) {   //Comprueba si es un paisano o un soldado
            System.out.println("Los soldados no cargan Recursos!");
            return;
        }
        if (this.getCantidadRecolectada() < 0) {  //Comprobamos si tiene algun recurso
            System.out.println("Este Paisano no tiene recursos!");
            return;
        }

        switch (this.tipoRecurso) {
            case "Bosque":
                System.out.println("Se van a almacenar " + cantidadRecolectada + " uds. de Madera en " + ciudadela.getNombre());
                ciudadela.setMadera(ciudadela.getMadera() + cantidadRecolectada);
                cantidadRecolectada = 0;
                break;
            case "Cantera":
                System.out.println("Se van a almacenar " + cantidadRecolectada + " uds. de Piedra en " + ciudadela.getNombre());
                ciudadela.setPiedra(ciudadela.getPiedra() + cantidadRecolectada);
                cantidadRecolectada = 0;
                break;
            case "Arbusto":
                System.out.println("Se van a almacenar " + cantidadRecolectada + " uds. de Comida en " + ciudadela.getNombre());
                ciudadela.setComida(ciudadela.getComida() + cantidadRecolectada);
                cantidadRecolectada = 0;
                break;
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
        Edificio Ciudadela = mapa.getEdificios().get("Ciudadela-1");
        switch (Edificio.toLowerCase()) {
            case "casa":
                if (Ciudadela.getMadera() >= 50 && Ciudadela.getPiedra() >= 20) {
                    if (mapa.checkCoords(pos) && mapa.checkBuilding(pos)) { //Comprueba que la posicion esta en el mapa y que no esta ocupada
                        String Name = "Casa-" + (mapa.getCantidades()[3] + 1);
                        mapa.getCantidades()[3]++;
                        mapa.getMapa().get(pos.getX()).set(pos.getY(), new Celda("Casa", new Posicion(pos), Name)); //Metemos la celda en su posicion del mapa
                        mapa.getEdificios().put(Name, mapa.getCelda(new Posicion(pos)).getEf());
                        System.out.println("Casa construida en "+pos+"Se han gastado 20 unidades de piedra y 50 de madera");
                        
                        Ciudadela.setMadera(Ciudadela.getMadera() - 50);
                        Ciudadela.setPiedra(Ciudadela.getPiedra() - 20);
                        System.out.println("Quedan los siguientes recursos: ");
                        System.out.println("Comida: "+ Ciudadela.getComida());
                        System.out.println("Madera: "+ Ciudadela.getMadera());
                        System.out.println("Piedra: "+ Ciudadela.getPiedra());
                    } else {
                        System.out.println("No se puede Contruir en esa direccion!");
                    }
                } else {
                    System.out.println("No hay suficientes recursos para construir la casa");
                }
                break;
            case "cuartel":
                if (Ciudadela.getMadera() >= 50 && Ciudadela.getPiedra() >= 20) {
                    if (mapa.checkCoords(pos) && mapa.checkBuilding(pos)) { //Comprueba que la posicion esta en el mapa y que no esta ocupada
                        String Name = "Cuartel-" + (mapa.getCantidades()[4] + 1);
                        mapa.getCantidades()[4]++;
                        mapa.getMapa().get(pos.getX()).set(pos.getY(), new Celda("Cuartel", new Posicion(pos), Name)); //Metemos la celda en su posicion del mapa
                        mapa.getEdificios().put(Name, mapa.getCelda(new Posicion(pos)).getEf());
                        System.out.println("Cuartel construido en "+pos+"Se han gastado 20 unidades de piedra y 50 de madera");
                        
                        Ciudadela.setMadera(Ciudadela.getMadera() - 50);
                        Ciudadela.setPiedra(Ciudadela.getPiedra() - 20);
                        System.out.println("Quedan los siguientes recursos: ");
                        System.out.println("Comida: "+ Ciudadela.getComida());
                        System.out.println("Madera: "+ Ciudadela.getMadera());
                        System.out.println("Piedra: "+ Ciudadela.getPiedra());
                    } else {
                        System.out.println("No se puede Contruir en esa direccion!");
                    }
                } else {
                    System.out.println("No hay suficientes recursos para construir el cuartel");
                }
                break;
            case "ciudadela":
                if (Ciudadela.getMadera() >= 50 && Ciudadela.getPiedra() >= 20) {
                    if (mapa.checkCoords(pos) && mapa.checkBuilding(pos)) { //Comprueba que la posicion esta en el mapa y que no esta ocupada
                        String Name = "Ciudadela-" + (mapa.getCantidades()[2] + 1);
                        mapa.getCantidades()[2]++;
                        mapa.getMapa().get(pos.getX()).set(pos.getY(), new Celda("Ciudadela", new Posicion(pos), Name)); //Metemos la celda en su posicion del mapa
                        mapa.getEdificios().put(Name, mapa.getCelda(new Posicion(pos)).getEf());
                        System.out.println("Ciudadela construida en "+pos+"Se han gastado 20 unidades de piedra y 50 de madera");
                        
                        Ciudadela.setMadera(Ciudadela.getMadera() - 50);
                        Ciudadela.setPiedra(Ciudadela.getPiedra() - 20);
                        System.out.println("Quedan los siguientes recursos: ");
                        System.out.println("Comida: "+ Ciudadela.getComida());
                        System.out.println("Madera: "+ Ciudadela.getMadera());
                        System.out.println("Piedra: "+ Ciudadela.getPiedra());
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
        mapa.actualizarVisibilidad();
        return new Posicion(posicion);
    }

    public void recolectar(Recurso recurso, Mapa mapa) {
        if (recurso == null) {    //Comprobamos si se pasa bien el argumento
            System.out.println("Argumento Recurso pasado nulo!");
            return;
        }

        if (tipo.equals("Soldado")) { //Comprueba que el personaje no sea un soldado
            System.out.println("Un soldado no puede recolectar!");
            return;
        }
        if (cantidadRecolectada < capRecolectar) {  //Comprueba que la cantidad que lleva sea menor a su capacidad total
            if (cantidadRecolectada == 0) {   //En caso de que el paisano no lleve nada encima, puede recolectar el recurso que sea
                cantidadRecolectada = recurso.restarCantidad(capRecolectar, mapa);    //Devuelve la cantidad obtenida y en caso de agotarse el recurso lo elimina
                tipoRecurso = recurso.getTipo();   //Establece el tipo de recurso que carga el personaje
                System.out.println("El Paisano ha recogido " + cantidadRecolectada + "uds de " + tipoRecurso);  //Esto va a haber que cambiarlo, el tipoRecurso imprime bosque, no madera
                if (cantidadRecolectada == capRecolectar) {
                    System.out.println("El Paisano no puede recolectar mas");
                }
            } else {    //Si ya ha recogido recursos de algun tipo
                if (!recurso.getTipo().equals(tipoRecurso)) { //Comprobamos que el recurso sea del tipo que estamos recolectando
                    System.out.println("No se puede recolectar ese recurso, ya se esta cargando " + tipoRecurso);
                    return;
                }
                cantidadRecolectada += recurso.restarCantidad(capRecolectar - cantidadRecolectada, mapa);   //Si el recurso es del mismo tipo se añade recurso al personaje y se resta la capacidad restante del personaje al recurso
            }
        } else {
            System.out.println("El paisano no puede recolectar mas! Deben dejarse los recursos en la Ciudadela");
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
        impresion += "Posicion: " + posicion + "\n";

        return impresion;
    }

    //GETTERS Y SETTERS
    public int getCantidadRecolectada() {
        return cantidadRecolectada;
    }

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
