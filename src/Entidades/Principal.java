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
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import Mapa.Mapa;
import Mapa.Celda;

public class Principal {

    public Principal() {
        boolean seguir = true;
        Scanner scanner = new Scanner(System.in);
        Mapa map = new Mapa();
        while (seguir) {
            System.out.print("$ ");
            String linea = scanner.nextLine();
            String[] comando = linea.split(" ");
            if (comando.length > 0) {
                switch (comando[0].toLowerCase()) {
                    case "salir":
                        seguir = false;
                        break;
                    case "mover":
                        if (comando.length != 3) {
                            System.out.println("Errorsintactico: MOVER ...");
                        } else {
                            System.out.println(comando[1]);
                            System.out.println(comando[2]);
                            if (map.getPersonajes().containsKey(comando[1])) {
                                Personaje personaje = (Personaje) map.getPersonajes().get(comando[1]);
                                Posicion p = new Posicion(personaje.getPosicion());
                                Posicion pos = personaje.moverPj(map, comando[2]);
                                if(!(p.equals(pos)))
                                    System.out.println("El " + comando[1] + " se ha movido a la " + pos);
                                //personaje.mover(comando[2]);
                            } else {
                                System.out.println("El personaje no existe");
                            }
                            //procesar comando
                            //obtener personaje del mapa
                            //comprobar si se puede mover a esa posicion
                            //....
                        }
                        map.imprimir();
                        break;
                    case "listar":
                        switch (comando[1].toLowerCase()) {
                            case "personajes":
                                for (String k : map.getPersonajes().keySet()) {
                                    System.out.println("clave : " + k + ", Posicion: " + map.getPersonajes().get(k).getPosicion());
                                }
                                break;
                            case "edificios":
                                for (String k : map.getEdificios().keySet()) {
                                    System.out.println("Nombre : " + k + ", Posicion: " + map.getEdificios().get(k).getPosicion());
                                }
                                break;
                            default:
                                System.out.println("comando incorrecto. Debe introducir listar personajes o listar edificios.");
                                break;
                        }
                        break;
                    case "construir":
                        if (comando.length == 4) {
                            switch (comando[2].toLowerCase()) {
                                case "casa":
                                    if (map.getPersonajes().containsKey(comando[1])) {
                                        Personaje personaje = map.getPersonajes().get(comando[1]);
                                        personaje.construir(map, comando[3], comando[2]);
                                    } else {
                                        System.out.println("El personaje no existe");
                                    }
                                    break;
                                case "ciudadela":

                                    break;
                                case "cuartel":
                                    break;
                                default:
                                    System.out.println("comando incorrecto. Debe introducir listar personajes o listar edificios.");
                                    break;
                            }

                        } else {
                            System.out.println("Comando construir incorrecto");
                        }
                        break;
                    case "describir":
                        if (map.getPersonajes().containsKey(comando[1])) {
                            Personaje personaje = map.getPersonajes().get(comando[1]);
                            System.out.println(personaje);
                        } else if (map.getEdificios().containsKey(comando[1])) {
                            Edificio edificio = map.getEdificios().get(comando[1]);
                            System.out.println(edificio);
                        } else if (map.getRecursos().containsKey(comando[1])) {
                            Recurso recurso = map.getRecursos().get(comando[1]);
                        } else {
                            System.out.println("No existe la entidad introducida");
                        }
                        break;
                        
                    case "mirar":
                        Posicion posMirar = new Posicion(comando[1]);  //guarda la posicion pasada
                        Celda cellMirar = map.getCelda(posMirar);
                        
                        switch(cellMirar.getTipo()){    //En funcion del tipo de la celda
                            case "Soldado":
                                System.out.println("Celda tipo Pradera, hay un Soldado aqui");
                                break;
                            case "Paisano":
                                System.out.println("Celda tipo Pradera, hay un Paisano aqui");
                                break;
                            case "Ciudadela":
                                System.out.println("Celda tipo Ciudadela");
                                break;
                            case "Casa":
                                System.out.println("Celda tipo Casa");
                                break;
                            case "Cuartel":
                                System.out.println("Celda tipo Cuartel");
                                break;
                            case "Bosque":
                                System.out.println("Celda tipo Bosque");
                                break;
                            case "Cantera":
                                System.out.println("Celda tipo Cantera");
                                break;
                            case "Arbusto":
                                System.out.println("Celda tipo Arbusto");
                                break;
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
