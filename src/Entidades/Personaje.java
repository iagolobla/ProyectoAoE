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
        if(pos == null){
            System.out.println("Posicion pasada a Personaje nula!");
            return;
        }
        posicion = new Posicion(pos);
        switch (tipo) {
            case "soldado":
                this.tipo = tipo;
                armadura = 100;
                salud = 200;
                ataque = 50;
                capRecolectar = 0;
                cantidadRecolectada = 0;
                this.Nombre = Nombre;   //El parametro nombre debe ser unico para cada personaje
                break;
            case "paisano":
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
            System.out.println("Este Paisano no tiene recursos!");
            return;
        }

        switch (this.tipoRecurso) {
            case "bosque":
                System.out.println("Se van a almacenar " + cantidadRecolectada + " uds. de Madera en " + ciudadela.getNombre());
                ciudadela.setMadera(ciudadela.getMadera() + cantidadRecolectada);
                cantidadRecolectada = 0;
                break;
            case "cantera":
                System.out.println("Se van a almacenar " + cantidadRecolectada + " uds. de Piedra en " + ciudadela.getNombre());
                ciudadela.setPiedra(ciudadela.getPiedra() + cantidadRecolectada);
                cantidadRecolectada = 0;
                break;
            case "arbusto":
                System.out.println("Se van a almacenar " + cantidadRecolectada + " uds. de Comida en " + ciudadela.getNombre());
                ciudadela.setComida(ciudadela.getComida() + cantidadRecolectada);
                cantidadRecolectada = 0;
                break;
        }
    }

    public void construir(Mapa mapa, String direccion, String Edificio) {//DESCONTAR RECURSOS AL CONSTRUIR.
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
        Edificio Ciudadela = mapa.getEdificios().get("ciudadela-1");
        switch (Edificio.toLowerCase()) {
            case "casa":
                if (Ciudadela.getMadera() >= 50 && Ciudadela.getPiedra() >= 20) {
                    if (mapa.checkCoords(pos) && mapa.checkBuilding(pos)) { //Comprueba que la posicion esta en el mapa y que no esta ocupada
                        String Name = "casa-" + (mapa.getCantidades()[3] + 1);
                        mapa.getCantidades()[3]++;
                        mapa.getMapa().get(pos.getX()).set(pos.getY(), new Celda("casa", new Posicion(pos), Name)); //Metemos la celda en su posicion del mapa

                        mapa.getEdificios().put(Name, mapa.getCelda(new Posicion(pos)).getEdificio());
                        System.out.println("Casa construida en " + pos + "Se han gastado 20 unidades de piedra y 50 de madera");

                        Ciudadela.setMadera(Ciudadela.getMadera() - 50);
                        Ciudadela.setPiedra(Ciudadela.getPiedra() - 20);
                        System.out.println("Quedan los siguientes recursos: ");
                        System.out.println("Comida: " + Ciudadela.getComida());
                        System.out.println("Madera: " + Ciudadela.getMadera());
                        System.out.println("Piedra: " + Ciudadela.getPiedra());
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
                        String Name = "cuartel-" + (mapa.getCantidades()[4] + 1);
                        mapa.getCantidades()[4]++;
                        mapa.getMapa().get(pos.getX()).set(pos.getY(), new Celda("cuartel", new Posicion(pos), Name)); //Metemos la celda en su posicion del mapa

                        mapa.getEdificios().put(Name, mapa.getCelda(new Posicion(pos)).getEdificio());
                        System.out.println("Cuartel construido en " + pos + "Se han gastado 20 unidades de piedra y 50 de madera");

                        Ciudadela.setMadera(Ciudadela.getMadera() - 50);
                        Ciudadela.setPiedra(Ciudadela.getPiedra() - 20);
                        System.out.println("Quedan los siguientes recursos: ");
                        System.out.println("Comida: " + Ciudadela.getComida());
                        System.out.println("Madera: " + Ciudadela.getMadera());
                        System.out.println("Piedra: " + Ciudadela.getPiedra());
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
                        String Name = "ciudadela-" + (mapa.getCantidades()[2] + 1);
                        mapa.getCantidades()[2]++;
                        mapa.getMapa().get(pos.getX()).set(pos.getY(), new Celda("ciudadela", new Posicion(pos), Name)); //Metemos la celda en su posicion del mapa

                        mapa.getEdificios().put(Name, mapa.getCelda(new Posicion(pos)).getEdificio());
                        System.out.println("Ciudadela construida en " + pos + "Se han gastado 20 unidades de piedra y 50 de madera");

                        Ciudadela.setMadera(Ciudadela.getMadera() - 50);
                        Ciudadela.setPiedra(Ciudadela.getPiedra() - 20);
                        System.out.println("Quedan los siguientes recursos: ");
                        System.out.println("Comida: " + Ciudadela.getComida());
                        System.out.println("Madera: " + Ciudadela.getMadera());
                        System.out.println("Piedra: " + Ciudadela.getPiedra());
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
            Edificio ciudadelilla = mapa.getEdificios().get("ciudadela-1");
            switch (edificio.getTipo()) {
                case "casa":
                    if (ciudadelilla.getMadera() > (Edificio.SALUDCASA - vida) && ciudadelilla.getMadera() > (Edificio.SALUDCASA - vida)) {
                        edificio.setSalud(Edificio.SALUDCASA);
                        System.out.println("Se han recuperado " + (Edificio.SALUDCASA - vida) + " puntos de salud");
                        ciudadelilla.setMadera(ciudadelilla.getMadera() - (Edificio.SALUDCASA - vida));
                        ciudadelilla.setPiedra(ciudadelilla.getPiedra() - (Edificio.SALUDCASA - vida));
                        System.out.println("Costes de la reparacion: " + (Edificio.SALUDCASA - vida) + " uds. de Madera y " + (Edificio.SALUDCASA - vida) + " uds de Piedra");
                    } else {
                        System.out.println("No hay recursos suficientes para reparar el edificio");
                    }
                    break;
                case "ciudadela":
                    if (ciudadelilla.getMadera() > (Edificio.SALUDCIUDADELA - vida) && ciudadelilla.getMadera() > (Edificio.SALUDCIUDADELA - vida)) {
                        edificio.setSalud(Edificio.SALUDCIUDADELA);
                        System.out.println("Se han recuperado " + (Edificio.SALUDCIUDADELA - vida) + " puntos de salud");
                        ciudadelilla.setMadera(ciudadelilla.getMadera() - (Edificio.SALUDCASA - vida));
                        ciudadelilla.setPiedra(ciudadelilla.getPiedra() - (Edificio.SALUDCASA - vida));
                        System.out.println("Costes de la reparacion: " + (Edificio.SALUDCASA - vida) + " uds. de Madera y " + (Edificio.SALUDCASA - vida) + " uds de Piedra");

                    } else {
                        System.out.println("No hay recursos suficientes para reparar el edificio");
                    }
                    break;
                case "cuartel":
                    if (ciudadelilla.getMadera() > (Edificio.SALUDCUARTEL - vida) && ciudadelilla.getMadera() > (Edificio.SALUDCUARTEL - vida)) {
                        edificio.setSalud(Edificio.SALUDCUARTEL);
                        System.out.println("Se han recuperado " + (Edificio.SALUDCUARTEL - vida) + " puntos de salud");
                        ciudadelilla.setMadera(ciudadelilla.getMadera() - (Edificio.SALUDCASA - vida));
                        ciudadelilla.setPiedra(ciudadelilla.getPiedra() - (Edificio.SALUDCASA - vida));
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
    
    public void reparar(Mapa mapa, String direccion, int cantidad) {   //Sobrecarga: solo repara la cantidad pasada
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
            
            Edificio ciudadelilla = mapa.getEdificios().get("ciudadela-1");
            switch (edificio.getTipo()) {
                case "casa":
                    cantidadReparar = Edificio.SALUDCASA - vida;
                    if(cantidad < cantidadReparar){
                        cantidadReparar = cantidad;
                    }
                    if (ciudadelilla.getMadera() > (cantidadReparar) && ciudadelilla.getMadera() > (cantidadReparar)) {
                        edificio.setSalud(vida + cantidadReparar);
                        System.out.println("Se han recuperado " + (cantidadReparar) + " puntos de salud");
                        ciudadelilla.setMadera(ciudadelilla.getMadera() - (cantidadReparar));
                        ciudadelilla.setPiedra(ciudadelilla.getPiedra() - (cantidadReparar));
                        System.out.println("Costes de la reparacion: " + (cantidadReparar) + " uds. de Madera y " + (cantidadReparar) + " uds de Piedra");
                    } else {
                        System.out.println("No hay recursos suficientes para reparar el edificio");
                    }
                    break;
                case "ciudadela":
                    cantidadReparar = Edificio.SALUDCIUDADELA - vida;
                    if(cantidad < cantidadReparar){
                        cantidadReparar = cantidad;
                    }
                    if (ciudadelilla.getMadera() > (cantidadReparar) && ciudadelilla.getMadera() > (cantidadReparar)) {
                        edificio.setSalud(vida + cantidadReparar);
                        System.out.println("Se han recuperado " + (cantidadReparar) + " puntos de salud");
                        ciudadelilla.setMadera(ciudadelilla.getMadera() - (cantidadReparar));
                        ciudadelilla.setPiedra(ciudadelilla.getPiedra() - (cantidadReparar));
                        System.out.println("Costes de la reparacion: " + (cantidadReparar) + " uds. de Madera y " + (cantidadReparar) + " uds de Piedra");

                    } else {
                        System.out.println("No hay recursos suficientes para reparar el edificio");
                    }
                    break;
                case "cuartel":
                    cantidadReparar = Edificio.SALUDCUARTEL - vida;
                    if(cantidad < cantidadReparar){
                        cantidadReparar = cantidad;
                    }
                    if (ciudadelilla.getMadera() > (cantidadReparar) && ciudadelilla.getMadera() > (cantidadReparar)) {
                        edificio.setSalud(vida + cantidadReparar);
                        System.out.println("Se han recuperado " + (cantidadReparar) + " puntos de salud");
                        ciudadelilla.setMadera(ciudadelilla.getMadera() - (cantidadReparar));
                        ciudadelilla.setPiedra(ciudadelilla.getPiedra() - (cantidadReparar));
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

        if (tipo.equals("soldado")) { //Comprueba que el personaje no sea un soldado
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
                int cantidadRestante = cantidadRecolectada;
                cantidadRecolectada += recurso.restarCantidad(capRecolectar - cantidadRecolectada, mapa);   //Si el recurso es del mismo tipo se añade recurso al personaje y se resta la capacidad restante del personaje al recurso
                cantidadRestante = cantidadRecolectada - cantidadRestante;
                System.out.println("El Paisano ha recogido " + cantidadRestante + "uds de " + tipoRecurso);
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
}
