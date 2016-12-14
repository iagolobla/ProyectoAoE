/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Edificios.Ciudadela;
import Edificios.Edificio;
import Excepciones.ExcepcionAlmacenar;
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
public class Caballero extends Soldado{
    public static final int SALUD = 150;
    public static final int ATAQUE = 60;
    public static final int ARMADURA = 40;

    public Caballero(String Nombre, Posicion posicion, Civilizacion civilizacion) {
        super(Nombre, posicion, civilizacion);
        this.setAtaque(ATAQUE);
        this.setArmadura(ARMADURA);
        this.setSalud(SALUD);

    }
    
    public int capacidadMovimiento(){   //Los caballeros se mueven dos casillas
        return 2;
    }
    
    public Edificio construir(String tipo_edificio) throws ExcepcionConstruir{
        throw new ExcepcionConstruir("Los caballeros no construyen");
    }
    
    public void reparar(Edificio edificio) throws ExcepcionReparar{
        throw new ExcepcionReparar("Los caballeros no pueden reparar");
    }
    
    public void recolectar(Contenedor contenedor) throws ExcepcionRecolectar{
        throw new ExcepcionRecolectar("Los caballeros no pueden recolectar");
    }
    
    public void almacenar(Ciudadela ciudadela) throws ExcepcionAlmacenar{
        throw new ExcepcionAlmacenar("Los caballeros no pueden almacenar");
    }
    
}
