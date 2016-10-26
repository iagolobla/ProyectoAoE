/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author iagolobla
 */
import java.util.Scanner;

public class Principal {

    public Principal() {
        boolean seguir = true;
        Scanner scanner = new Scanner(System.in);
        //CREAR EL MAPA
        while (seguir) {
            System.out.print("$ ");
            String linea = scanner.nextLine();
            String[] comando = linea.split(" ");
            if (comando.length > 0) {
                switch (comando[0].toLowerCase()) {
                    case "salir":
                        seguir=false;
                        break;
                    case "mover":
                        if(comando.length !=3){
                            System.out.println("Errorsintactico: MOVER ...");
                        }else{
                            //procesar comando
                            //obtener personaje del mapa
                            //comprobar si se puede mover a esa posicion
                            //....
                        }
                        break;
                    default:
                        System.out.println("Comando incorrecto");
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
