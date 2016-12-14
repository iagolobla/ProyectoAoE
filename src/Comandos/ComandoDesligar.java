/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Juego.Mapa;
import Personajes.Grupo;
import Personajes.Personaje;

/**
 *
 * @author iagolobla
 */
public class ComandoDesligar implements Comando {

    private Mapa mapa;
    private String personaje;
    private String Nombregrupo;

    public ComandoDesligar(Mapa mapa, String personaje, String Nombregrupo) {
        this.mapa = mapa;
        this.personaje = personaje;
        this.Nombregrupo = Nombregrupo;
    }

    public void ejecutar() {
        Personaje person = mapa.getCivilizacion().getPersonajes().get(personaje);
        if (person == null) {
            throw new NullPointerException("El Personaje especificado no existe!");
        }
        Grupo grupo =  (Grupo) mapa.getCivilizacion().getPersonajes().get(Nombregrupo);
        if (grupo == null) {
            throw new NullPointerException("El Personaje especificado no existe!");
        }
        grupo.desligar(person, mapa);
    }
}
