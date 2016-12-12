/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Edificios;

import Juego.Civilizacion;
import Juego.Posicion;
import Personajes.Paisano;
import Recursos.Recurso;
import Recursos.Piedra;
import Recursos.Madera;
import Recursos.Comida;
import Personajes.Personaje;

/**
 *
 * @author iagolobla
 */
public class Ciudadela extends Edificio {

    public static final int SALUD = 400;
    public static final int CAPACIDAD = 10;

    public Ciudadela(Posicion posicion, String Nombre, Civilizacion civilizacion) {
        super(posicion, Nombre, civilizacion);
        this.setSalud(SALUD);
        this.setCapPersonajes(CAPACIDAD);
    }
    
    @Override
    public String toString() {
        String impresion = super.toString();
        impresion+="Tipo de edificio: Ciudadela";
        

        return impresion;
    }
    

    public void almacenar(Recurso recurso){
        if(recurso instanceof Comida){
            this.getCivilizacion().setComida(this.getCivilizacion().getComida()+recurso.getCantidad());
        }else if(recurso instanceof Piedra){
            this.getCivilizacion().setPiedra(this.getCivilizacion().getPiedra()+recurso.getCantidad());
        }else if(recurso instanceof Madera){
            this.getCivilizacion().setMadera(this.getCivilizacion().getMadera()+recurso.getCantidad());
        }else{
            
        }
    }
    
    public Personaje crear(String tipo_personaje){
        if(tipo_personaje.equals("paisano")){
            String Name="paisano-"+this.getCivilizacion().getCantidades()[0];
            this.getCivilizacion().getCantidades()[0]++;
            return new Paisano(Name, this.getPosicion(), this.getCivilizacion());
            
        }else{
            System.out.println("tipo personaje no valido introducido");
            return null;
        }
    }
    
        public void atacar(Personaje[] personajes){
        
    }
}
