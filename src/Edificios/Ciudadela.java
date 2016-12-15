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
        impresion += "Recursos: \n";
        impresion += "Piedra: " + this.getCivilizacion().getPiedra() + "\n";
        impresion += "Madera: " + this.getCivilizacion().getMadera() + "\n";
        impresion += "Comida: " + this.getCivilizacion().getComida() + "\n";
        impresion += "Tipo de edificio: Ciudadela";

        return impresion;
    }
    

    public void almacenar(Recurso recurso) throws ExcepcionAlmacenar{
        if(recurso instanceof Comida){
            this.getCivilizacion().setComida(this.getCivilizacion().getComida()+recurso.getCantidad());
        }else if(recurso instanceof Piedra){
            this.getCivilizacion().setPiedra(this.getCivilizacion().getPiedra()+recurso.getCantidad());
        }else if(recurso instanceof Madera){
            this.getCivilizacion().setMadera(this.getCivilizacion().getMadera()+recurso.getCantidad());
        }else{
            throw new ExcepcionAlmacenar("Tipo de recurso incorrecto");
        }
    }
    
    public Personaje crear(String tipo_personaje) throws ExcepcionCrear{
        if(tipo_personaje.equals("paisano")){
            String Name="paisano-"+(this.getCivilizacion().getCantidades()[0]+1);
            this.getCivilizacion().getCantidades()[0]++;
            return new Paisano(Name, this.getPosicion(), this.getCivilizacion());
            
        }else{
            throw new ExcepcionCrear("Tipo personaje incorrecto");
        }
    }
    
}
