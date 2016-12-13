/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Edificios.Edificio;
import Excepciones.ExcepcionConstruir;
import Excepciones.ExcepcionCrear;
import Excepciones.ExcepcionRecolectar;
import Excepciones.ExcepcionReparar;
import Juego.Civilizacion;
import Juego.Posicion;
import Recursos.Contenedor;

/**
 *
 * @author iagolobla
 */
public class Arquero extends Soldado {

    public static final int SALUD = 80;
    public static final int ATAQUE = 40;
    public static final int ARMADURA = 10;

    public Arquero(String Nombre, Posicion posicion, Civilizacion civilizacion) {
        super(Nombre, posicion, civilizacion);
        this.setAtaque(ATAQUE);
        this.setArmadura(ARMADURA);
        this.setSalud(SALUD);

    }
    
    public int capacidadMovimiento(){   //Los paisanos se mueven una casilla
        return 1;
    }
    
    public Edificio construir(String tipo_edificio) throws ExcepcionConstruir{
        throw new ExcepcionConstruir("Los arqueros no construyen");
    }
    
    public void reparar(Edificio edificio) throws ExcepcionReparar{
        throw new ExcepcionReparar("Los arqueros no pueden reparar");
    }
    
    public void recolectar(Contenedor contenedor) throws ExcepcionRecolectar{
        throw new ExcepcionRecolectar("Los arqueros no pueden recolectar");
    }
    
}
