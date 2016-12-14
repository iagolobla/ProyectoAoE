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

/**
 *
 * @author iagolobla
 */
public class Casa extends Edificio{
    public static final int SALUD = 200;
    public static final int CAPACIDAD = 5;

    public Casa(Posicion posicion, String Nombre, Civilizacion civilizacion) {
        super(posicion, Nombre, civilizacion);
        this.setSalud(SALUD);
        this.setCapPersonajes(CAPACIDAD);
    }
    
    @Override
    public String toString() {
        String impresion = super.toString();
        impresion+="Tipo de edificio: Casa";
        

        return impresion;
    }
    
    public void almacenar(Recurso recurso) throws ExcepcionAlmacenar {
        throw new ExcepcionAlmacenar("las casas no almacenan");
    }
    
    public Personaje crear(String tipo_personaje) throws ExcepcionCrear{
        throw new ExcepcionCrear("Las casas no crean personajes");
    }
    
    public void atacar(Personaje[] personajes){
        
    }
}
