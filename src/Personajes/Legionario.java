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
public class Legionario extends Soldado{
    public static final int SALUD = 200;
    public static final int ATAQUE = 80;
    public static final int ARMADURA = 80;

    public Legionario(String Nombre, Posicion posicion, Civilizacion civilizacion) {
        super(Nombre, posicion, civilizacion);
        this.setAtaque(ATAQUE);
        this.setArmadura(ARMADURA);
        this.setSalud(SALUD);

    }
    
    public int capacidadMovimiento(){   //Los paisanos se mueven una casilla
        return 1;
    }
    
    public Edificio construir(String tipo_edificio) throws ExcepcionConstruir{
        throw new ExcepcionConstruir("Los legionario no construyen");
    }
    
    public void reparar(Edificio edificio) throws ExcepcionReparar{
        throw new ExcepcionReparar("Los legionarios no pueden reparar");
    }
    
    public void recolectar(Contenedor contenedor) throws ExcepcionRecolectar{
        throw new ExcepcionRecolectar("Los legionarios no pueden recolectar");
    }
    
    public void almacenar(Ciudadela ciudadela) throws ExcepcionAlmacenar{
        throw new ExcepcionAlmacenar("Los legionarios no pueden almacenar");
    }
    
    public   double danhoAtaque(Edificio edificio) {
        return this.getAtaque() - edificio.getDefensa();
    }
    
    public double danhoAtaque(Personaje personaje) {
        return this.getAtaque() - personaje.getArmadura();
    }
}
