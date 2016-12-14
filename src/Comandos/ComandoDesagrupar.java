/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Excepciones.ExcepcionDesagrupar;
import Juego.Mapa;
import Personajes.Grupo;
import Personajes.Personaje;

/**
 *
 * @author iagolobla
 */
public class ComandoDesagrupar implements Comando {

    private Mapa mapa;
    private String nombre;

    public ComandoDesagrupar(Mapa mapa, String nombre) {
        this.mapa = mapa;
        this.nombre = nombre;
    }

    public void ejecutar() throws ExcepcionDesagrupar{
        Personaje p = mapa.getCivilizacion().getPersonajes().get(nombre);
        if (p == null) {
            throw new NullPointerException("El Personaje especificado no existe!");
        }
        if(p instanceof Grupo){
            Grupo G=(Grupo)p;
            G.desagrupar(mapa);
        }else{
            throw new ExcepcionDesagrupar("La entidad introducida debe ser un grupo");
        }
    }
}
