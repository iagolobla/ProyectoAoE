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
import Excepciones.ExcepcionEdificioVacio;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import Personajes.Personaje;
import Excepciones.ExcepcionSintaxis;
import Excepciones.ExcepcionPersonajeNoEncontrado;
import Excepciones.ExcepcionPosicionNoValida;

//Bienvenido al mejor juego de la historia, hecho de la mejor forma posible
public class Principal {

    public Principal() {
        ConsolaNormal Shell = new ConsolaNormal();
        boolean seguir = true;
        Shell.imprimir("Bienvenido al Juego!");
        int numciv;

        Civilizacion C;
        HashMap<String, Civilizacion> civilizaciones = new HashMap<String, Civilizacion>();
        do {
            numciv = Shell.leerInt("Introduzca el numero de civilizaciones con el que desea jugar (maximo 3. Nombres de una palabra)");
        } while (numciv > 3 || numciv < 1);
        String nombre = null;
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
        Juego juego=new Juego(new Mapa(6, 6, 6, civilizaciones.values()));
        juego.getMapa().setCivilizacion(C);
        Shell.imprimir(juego.getMapa().print());

        while (seguir) {
            String linea = Shell.leer("$ ");
            String lineaLowerCase = linea.toLowerCase();    //Pasamos todo a minusculas
            String[] comando = lineaLowerCase.split(" ");
            if (comando.length > 0) {
                switch (comando[0].toLowerCase()) {
                    case "mover":
                        try {
                            if(comando.length != 3){
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }
                            
                            new ComandoMover(comando[2], comando[1], juego.getMapa()).ejecutar();
                            new ComandoImprimir(juego.getMapa(), Shell).ejecutar();

                        }
                        catch (Exception E) {
                            Shell.imprimir("Error: " + E.getMessage());
                        }
                        break;
                    case "imprimir":
                        new ComandoImprimir(juego.getMapa(), Shell).ejecutar();
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
        new Principal();
    }

}
