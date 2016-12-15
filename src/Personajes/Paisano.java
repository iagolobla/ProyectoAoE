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
import Excepciones.ExcepcionAlmacenar;
import Excepciones.ExcepcionRecolectar;
import Excepciones.ExcepcionReparar;
import Juego.Posicion;
import Juego.Civilizacion;
import Juego.Mapa;
import Recursos.Comida;
import Recursos.Contenedor;
import Recursos.Madera;
import Recursos.Piedra;
import Recursos.Recurso;

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
    private Recurso recurso;

    public Paisano(String Nombre, Posicion posicion, Civilizacion civilizacion) {
        super(Nombre, posicion, civilizacion);
        cantidadRecolectada = 0;
        capacidadRecurso = CAPACIDAD;
        recurso = null;
        this.setAtaque(ATAQUE);
        this.setArmadura(ARMADURA);
        this.setSalud(SALUD);

    }

    @Override
    public String toString() {
        String impresion = super.toString();
        impresion += "Capacidad de recoleccion: " + capacidadRecurso + "\n";
        impresion += "Cantidad recolectada: " + recurso.getCantidad() + "\n";
        if (recurso.getCantidad() > 0) {
            impresion += "Recurso cargado: " + recurso.getClass() + "\n";
        }
        return impresion;
    }

    public Edificio construir(String tipo_edificio) {   //Devuelve el edificio a construir
        String Name;
        switch (tipo_edificio) {
            case "casa":
                Name = "casa-" + (this.getCivilizacion().getCantidades()[3] + 1);
                this.getCivilizacion().getCantidades()[3]++;
                return new Casa(this.getPosicion(), Name, this.getCivilizacion());

            case "torre":
                Name = "torre-" + (this.getCivilizacion().getCantidades()[5] + 1);
                this.getCivilizacion().getCantidades()[5]++;
                return new Torre(this.getPosicion(), Name, this.getCivilizacion());

            case "ciudadela":
                Name = "ciudadela-" + (this.getCivilizacion().getCantidades()[2] + 1);
                this.getCivilizacion().getCantidades()[2]++;
                return new Ciudadela(this.getPosicion(), Name, this.getCivilizacion());

            case "cuartel":
                Name = "cuartel-" + (this.getCivilizacion().getCantidades()[4] + 1);
                this.getCivilizacion().getCantidades()[4]++;
                return new Cuartel(this.getPosicion(), Name, this.getCivilizacion());

            default:
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

    public void recolectar(Contenedor contenedor) throws ExcepcionRecolectar {
        Recurso R = contenedor.procesar();

        if (this.recurso != null && !this.recurso.getClass().equals(contenedor.getRecurso().getClass())) {
            throw new ExcepcionRecolectar("El paisano ya esta cargando otro tipo de recurso!");
        }

        if (this.getCapacidadRecurso() == this.getCantidadRecolectada()) {
            throw new ExcepcionRecolectar("El paisano no puede recolectar mas!");
        }

        //Calculamos el minimo entre la capacidad del paisano y la cantidad del recurso
        int cantidad = Math.min(R.getCantidad(), this.getCapacidadRecurso() - this.getCantidadRecolectada());

        Recurso Copia;
        if (R instanceof Piedra) {
            Copia = new Piedra((Piedra) R);
        } else if (R instanceof Madera) {
            Copia = new Madera((Madera) R);
        } else {
            Copia = new Comida((Comida) R);
        }

        Copia.setCantidad(cantidad);

        this.setRecurso(Copia);

        contenedor.getRecurso().setCantidad(contenedor.getRecurso().getCantidad() - cantidad);
    }

    public void almacenar(Ciudadela ciudadela) throws ExcepcionAlmacenar {
        if (ciudadela == null) {
            throw new ExcepcionAlmacenar("Ciudadela para almacenar nula");
        }
        ciudadela.almacenar(recurso);
        recurso=null;
    }

    public int getCantidadRecolectada() {
        if (recurso != null) {
            return recurso.getCantidad();
        } else {
            return 0;
        }
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

    public int capacidadMovimiento() {   //Los paisanos se mueven una casilla
        return 1;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

}
