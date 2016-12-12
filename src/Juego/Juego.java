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

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

//Bienvenido al mejor juego de la historia, hecho de la mejor forma posible

public class Juego {
    
    public Juego() {
        boolean seguir = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al juego.");
        int numciv;
        
        
        Civilizacion C;
        HashMap<String, Civilizacion> civilizaciones = new HashMap<String, Civilizacion>();
        do {
            System.out.println("Introduzca el numero de civilizaciones con el que desea jugar (maximo 3. Nombres de una palabra)");
            numciv = scanner.nextInt();
        } while (numciv > 3 || numciv < 1);
        String nombre;
        nombre = scanner.nextLine();//para coger el \0
        for (int i = 1; i <= numciv; i++) {
            System.out.println("Introduzca el nombre de la civilizacion " + i + ": ");
            nombre = scanner.nextLine();
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
        map.print();
        
        while (seguir) {
            System.out.print("$ ");
            String linea = scanner.nextLine();
            String lineaLowerCase = linea.toLowerCase();    //Pasamos todo a minusculas
            String[] comando = lineaLowerCase.split(" ");
            if (comando.length > 0) {
                switch (comando[0].toLowerCase()) {
                    case "salir":
                        seguir = false;
                        map.print();
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
