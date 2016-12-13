/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Edificios.Casa;
import Edificios.Ciudadela;
import Edificios.Cuartel;
import Edificios.Edificio;
import Edificios.Torre;
import Excepciones.ExcepcionReparar;
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

    public Edificio construir(String tipo_edificio) {
        String Name;
        if (tipo_edificio.equals("casa")) {
            Name = "casa-" + (this.getCivilizacion().getCantidades()[3] + 1);
            this.getCivilizacion().getCantidades()[3]++;
            return new Casa(this.getPosicion(), Name, this.getCivilizacion());

        } else if (tipo_edificio.equals("torre")) {
            Name = "torre-" + (this.getCivilizacion().getCantidades()[5] + 1);
            this.getCivilizacion().getCantidades()[5]++;
            return new Torre(this.getPosicion(), Name, this.getCivilizacion());
        } else if (tipo_edificio.equals("ciudadela")) {
            Name = "ciudadela-" + (this.getCivilizacion().getCantidades()[2] + 1);
            this.getCivilizacion().getCantidades()[2]++;
            return new Ciudadela(this.getPosicion(), Name, this.getCivilizacion());
        } else if (tipo_edificio.equals("cuartel")) {
            Name = "cuartel-" + (this.getCivilizacion().getCantidades()[4] + 1);
            this.getCivilizacion().getCantidades()[4]++;
            return new Cuartel(this.getPosicion(), Name, this.getCivilizacion());
        } else {
            System.out.println("tipo personaje no valido introducido");
            return null;
        }
    }

    public void reparar(Edificio edificio) throws ExcepcionReparar {
        if (edificio instanceof Casa) {
            edificio.setSalud(Casa.SALUD);
        } else if (edificio instanceof Cuartel) {
            edificio.setSalud(Cuartel.SALUD);

        } else if (edificio instanceof Ciudadela) {
            edificio.setSalud(Ciudadela.SALUD);

        } else if (edificio instanceof Torre) {
            edificio.setSalud(Torre.SALUD);

        } else {
            throw new ExcepcionReparar("Edificio a reparar incorrecto");
        }
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

    public int capacidadMovimiento() {   //Los paisanos se mueven una casilla
        return 1;
    }

}
