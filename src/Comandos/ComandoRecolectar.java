/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Excepciones.ExcepcionRecolectar;
import Excepciones.ExcepcionSintaxis;
import Juego.Celda;
import Juego.ConsolaNormal;
import Juego.Mapa;
import Juego.Posicion;
import Personajes.Personaje;
import Recursos.Contenedor;
import Recursos.Pradera;

/**
 *
 * @author iagolobla
 */
public class ComandoRecolectar implements Comando {

    private String direccion;
    private String personaje;
    private Mapa mapa;
    

    public ComandoRecolectar(String direccion, String personaje, Mapa mapa, ConsolaNormal SHELL) {
        this.direccion = direccion;
        this.personaje = personaje;
        this.mapa = mapa;
        
    }
    
    

    public void ejecutar() throws ExcepcionRecolectar, ExcepcionSintaxis{
        Personaje P;
        Posicion cont;
        Celda cell;
        Contenedor contenedor;
        
        if (!mapa.getCivilizacion().getPersonajes().containsKey(personaje)){
            throw new ExcepcionRecolectar("El personaje indicado no existe!");
        }
        P = mapa.getCivilizacion().getPersonajes().get(personaje);
        
        cont = P.mover(direccion);
        
        cell = mapa.getCelda(cont);
        contenedor = cell.getContenedor();
        
        if(contenedor instanceof Pradera){
            throw new ExcepcionRecolectar("No hay un recurso en la direccion especificada!");
        }
        
        P.recolectar(contenedor);
    }
}