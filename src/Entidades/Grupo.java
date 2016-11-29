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
import java.util.ArrayList;
import Mapa.Mapa;

public class Grupo {

    ArrayList<Personaje> personajes;
    private String nombreCivilizacion;
    private int armadura;
    private int salud;
    private int ataque;
    private int capRecolectar;
    private String Nombre;  //Aqui se necesita poner un nombre al personaje para usar eso como clave en el hashmap de personajes en el mapa
    private Posicion posicion;
    private int madera;
    private int piedra;
    private int comida;

    public Grupo(ArrayList<Personaje> person, String Nombre, Posicion pos, String civilizacion) {
        personajes = new ArrayList<Personaje>();
        nombreCivilizacion = civilizacion;
        this.Nombre = Nombre;
        int soldado = 0;
        posicion = new Posicion(pos);
        for (Personaje p : person) {
            p.setGrupo(true);
            personajes.add(p);
            armadura += p.getArmadura();
            salud += p.getSalud();
            ataque += p.getAtaque();
            capRecolectar += p.getCapRecolectar();
            if (p.getTipoRecurso() != null) {
                switch (p.getTipoRecurso()) {
                    case "madera":
                        madera += p.getCantidadRecolectada();
                        break;
                    case "piedra":
                        piedra += p.getCantidadRecolectada();
                        break;
                    case "comida":
                        comida += p.getCantidadRecolectada();
                        break;
                    default:
                        System.out.println("Tipo de recurso incorrecto");
                }
            }
            if (p.isSoldado()) {
                soldado = 1;
            }
        }
        if (soldado == 1) {
            capRecolectar = 0;
        }
    }

    @Override
    public String toString(){
        String imprimir;
        int salud = 0;
        
        imprimir = "\n";
        imprimir += "La lista de personajes del grupo es:\n";
        for(Personaje P : personajes){
            salud += P.getSalud();
            imprimir += "\t" + P.getNombre() + "\n";
        }
        imprimir += "Salud total del Grupo: " + salud + "\n";
        imprimir += "Armadura del Grupo: " + armadura;
        return imprimir;
    }
    
    
    public void moverGrupo(Mapa mapa, String direccion) {
        for (Personaje person : personajes) {
            person.moverPj(mapa, direccion);
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
        if (mapa.checkCoords(pos) && mapa.checkBuilding(pos)) {
            if (mapa.getCelda(pos).getPersonajes().get(0).getNombreCivilizacion().equals(nombreCivilizacion)) {
                mapa.getCelda(pos).setGrupo(this);
                mapa.getCelda(posicion).getGrupos().remove(this);
                posicion = new Posicion(pos);
            } else {
                System.out.println("El personaje intenta mover a una celda con personajes enemigos!");
            }
        } else {
            System.out.println("No se puede Mover en esa direccion!");
        }

    }

    public void desligar(Personaje person) {
        personajes.remove(person);
        person.setGrupo(false);
    }

    public void desagrupar(Mapa mapa) {
        mapa.getCivilizacion().getGrupos().remove(this);
        mapa.getCivilizacion().getCantidades()[6]--;
        for (Personaje p : personajes) {
            p.setGrupo(false);
        }
        mapa.getCelda(posicion).getGrupos().remove(this);
    }

    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
    }

    public String getNombreCivilizacion() {
        return nombreCivilizacion;
    }

    public void setNombreCivilizacion(String nombreCivilizacion) {
        this.nombreCivilizacion = nombreCivilizacion;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
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

    public void setAtaque(int ataque) {
        this.ataque = ataque;
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

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
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
