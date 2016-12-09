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
      
        do {
            System.out.println("Introduzca el numero de civilizaciones con el que desea jugar (maximo 3. Nombres de una palabra)");
            numciv = scanner.nextInt();
        } while (numciv > 3 || numciv < 1);
        String Nombre;
        Nombre = scanner.nextLine();//para coger el \0
        
        while (seguir) {
            System.out.print("$ ");
            String linea = scanner.nextLine();
            String lineaLowerCase = linea.toLowerCase();    //Pasamos todo a minusculas
            String[] comando = lineaLowerCase.split(" ");
            if (comando.length > 0) {
                switch (comando[0].toLowerCase()) {
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
