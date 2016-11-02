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
import Mapa.Mapa;
public class Principal {

    public Principal() {
        boolean seguir = true;
        Scanner scanner = new Scanner(System.in);
        Mapa map=new Mapa();
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
                            System.out.println(comando[1]);
                            System.out.println(comando[2]);
                            if(map.getPersonajes().containsKey(comando[1])){
                                Personaje personaje=(Personaje) map.getPersonajes().get(comando[1]);
                                personaje.mover(comando[2]);
                            }else{
                                System.out.println("El personaje no existe");
                            }
                            //procesar comando
                            //obtener personaje del mapa
                            //comprobar si se puede mover a esa posicion
                            //....
                        }
                        break;
                    case "mapa":
                        map.imprimir();
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
