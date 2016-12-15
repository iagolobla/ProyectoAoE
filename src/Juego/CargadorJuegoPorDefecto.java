/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import static Juego.Principal.SHELL;
import java.util.HashMap;

/**
 *
 * @author javier
 */
public class CargadorJuegoPorDefecto implements CargadorJuego {

    public Juego cargar() {
        int numciv;
        Civilizacion C;
        HashMap<String, Civilizacion> civilizaciones = new HashMap<String, Civilizacion>();
        do {
            numciv = SHELL.leerInt("Introduzca el numero de civilizaciones con el que desea jugar (maximo 2. Nombres de una palabra)");
        } while (numciv > 2 || numciv < 1);
        String nombre = null;
        for (int i = 1; i <= numciv; i++) {
            nombre = SHELL.leer("Introduzca el nombre de la civilizacion " + i + ": ");
            C = new Civilizacion(nombre);

            if (i == 1) {
                C.setColor("azul");
            } else if (i == 2) {
                C.setColor("rojo");
            } else {
                C.setColor("morado");
            }
            civilizaciones.put(nombre, C);

        }

        C = civilizaciones.get(nombre);
        SHELL.imprimir("Estas jugando con los: " + C.getNombre());
        Juego juego;
        try {
            juego = new Juego(new Mapa(6, 6, 6, civilizaciones.values()));
            juego.getMapa().setCivilizacion(C);
        } catch (Exception E) {
            SHELL.imprimir("Error: " + E.getMessage());
            return null;
        }
        
        SHELL.imprimir(juego.getMapa().print());
        return juego;
    }
}
