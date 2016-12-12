/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

/**
 *
 * @author iagolobla
 */
import Comandos.ComandoImprimir;
import Comandos.ComandoMover;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import Personajes.Personaje;

//Bienvenido al mejor juego de la historia, hecho de la mejor forma posible
public class Juego {

    public Juego() {
        ConsolaNormal Shell = new ConsolaNormal();
        boolean seguir = true;
        Shell.imprimir("Bienvenido al Juego!");
        int numciv;

        Civilizacion C;
        HashMap<String, Civilizacion> civilizaciones = new HashMap<String, Civilizacion>();
        do {
            numciv = Shell.leerInt("Introduzca el numero de civilizaciones con el que desea jugar (maximo 3. Nombres de una palabra)");
        } while (numciv > 3 || numciv < 1);
        String nombre;
        nombre = Shell.leer(""); //Para coger \0
        for (int i = 1; i <= numciv; i++) {
            nombre = Shell.leer("Introduzca el nombre de la civilizacion " + i + ": ");
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
        System.out.println("Estas jugando con los: " + C.getNombre());
        Mapa map = new Mapa(6, 6, 6, civilizaciones.values());
        map.setCivilizacion(C);
        

        while (seguir) {
            String linea = Shell.leer("$ ");
            String lineaLowerCase = linea.toLowerCase();    //Pasamos todo a minusculas
            String[] comando = lineaLowerCase.split(" ");
            if (comando.length > 0) {
                switch (comando[0].toLowerCase()) {
                    case "mover":
                        if (C.getPersonajes().containsKey(comando[1])) {
                            Personaje p = map.getCivilizacion().getPersonajes().get(comando[1]);
                            new ComandoMover(comando[2],p,map).ejecutar();
                            new ComandoImprimir(map).ejecutar();
                        }
                        break;
                    case "imprimir":
                        new ComandoImprimir(map).ejecutar();
                        break;
                    case "salir":
                        seguir = false;
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        new Juego();
    }

}
