/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Edificios;

import Excepciones.ExcepcionAlmacenar;
import Excepciones.ExcepcionCrear;
import Juego.Civilizacion;
import Juego.Posicion;
import Personajes.Paisano;
import Personajes.Personaje;
import Recursos.Comida;
import Recursos.Madera;
import Recursos.Piedra;
import Recursos.Recurso;
import Personajes.Arquero;
import Personajes.Caballero;
import Personajes.Legionario;

/**
 *
 * @author iagolobla
 */
public class Cuartel extends Edificio {

    public static final int SALUD = 300;
    public static final int CAPACIDAD = 15;

    public Cuartel(Posicion posicion, String Nombre, Civilizacion civilizacion) {
        super(posicion, Nombre, civilizacion);
        this.setSalud(SALUD);
        this.setCapPersonajes(CAPACIDAD);
    }

    @Override
    public String toString() {
        String impresion = super.toString();
        impresion += "Tipo de edificio: Cuartel";

        return impresion;
    }

    public void almacenar(Recurso recurso) throws ExcepcionAlmacenar {
        throw new ExcepcionAlmacenar("los cuarteles no almacenan");
    }

    public Personaje crear(String tipo_personaje) throws ExcepcionCrear {
        String Name;
        if (tipo_personaje.equals("arquero")) {
            Name = "arquero-" + (this.getCivilizacion().getCantidades()[7]+1);
            this.getCivilizacion().getCantidades()[7]++;
            return new Arquero(Name, this.getPosicion(), this.getCivilizacion());

        } else if (tipo_personaje.equals("legionario")) {
            Name = "legionario-" + (this.getCivilizacion().getCantidades()[1]+1);
            this.getCivilizacion().getCantidades()[1]++;
            return new Legionario (Name, this.getPosicion(), this.getCivilizacion());
        } else if (tipo_personaje.equals("caballero")) {
            Name = "caballero-" + (this.getCivilizacion().getCantidades()[8]+1);
            this.getCivilizacion().getCantidades()[8]++;
            return new Caballero(Name, this.getPosicion(), this.getCivilizacion());
        } else {
            throw new ExcepcionCrear("Tipo personaje incorrecto");
        }
    }
    
}
