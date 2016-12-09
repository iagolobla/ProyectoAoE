/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Juego.Posicion;
import Juego.Civilizacion;
import Juego.Mapa;

/**
 *
 * @author iagolobla
 */
public class Paisano extends Personaje {

    public static final int CAPACIDAD = 100;
    public static final int SALUD = 100;
    public static final int ATAQUE = 20;
    public static final int ARMADURA = 10;

    private int cantidadRecolectada;
    private int capacidadRecurso;
    private String tipoRecurso;

    public Paisano(String Nombre, Posicion posicion, Civilizacion civilizacion) {
        super(Nombre, posicion, civilizacion);
        cantidadRecolectada = 0;
        capacidadRecurso = CAPACIDAD;
        tipoRecurso = null;
        this.setAtaque(ATAQUE);
        this.setArmadura(ARMADURA);
        this.setSalud(SALUD);

    }

    @Override
    public String toString() {
        String impresion = super.toString();
        impresion += "Capacidad de recoleccion: " + capacidadRecurso + "\n";
        impresion += "Cantidad recolectada: " + cantidadRecolectada + "\n";
        if (cantidadRecolectada > 0) {
            impresion += "Recurso cargado: " + tipoRecurso + "\n";
        }
        return impresion;
    }

    public int getCantidadRecolectada() {
        return cantidadRecolectada;
    }

    public void setCantidadRecolectada(int cantidadRecolectada) {
        this.cantidadRecolectada = cantidadRecolectada;
    }

    public int getCapacidadRecurso() {
        return capacidadRecurso;
    }

    public void setCapacidadRecurso(int capacidadRecurso) {
        this.capacidadRecurso = capacidadRecurso;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

}
