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
import Entidades.Edificio;
import Entidades.Personaje;
import Entidades.Posicion;
import Entidades.Recurso;
import java.util.Scanner;
import Mapa.Mapa;
import Mapa.Celda;

public class Principal {

    public Principal() {
        boolean seguir = true;
        Scanner scanner = new Scanner(System.in);
        Mapa map = new Mapa(6, 6, 6);
        if(map == null){
            map = new Mapa();
        }
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
                    case "mover":
                        if (comando.length != 3) {
                            System.out.println("Errorsintactico: MOVER ...");
                        } else {
                            if (map.getPersonajes().containsKey(comando[1])) {
                                Personaje personaje = (Personaje) map.getPersonajes().get(comando[1]);
                                Posicion p = new Posicion(personaje.getPosicion());
                                Posicion pos = personaje.moverPj(map, comando[2]);
                                if (!(p.equals(pos))) {
                                    System.out.println("El " + comando[1] + " se ha movido a la " + pos);
                                }
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
                                    System.out.println("Nombre : " + k + ", Posicion: " + map.getPersonajes().get(k).getPosicion());
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
                            if (map.getPersonajes().containsKey(comando[1])) {
                                Personaje personaje = map.getPersonajes().get(comando[1]);
                                personaje.construir(map, comando[3], comando[2]);
                            } else {
                                System.out.println("El personaje no existe");
                            }

                        } else {
                            System.out.println("Comando construir incorrecto");
                        }
                        map.imprimir();
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
                            if (map.getCelda(recurso.getPos()).getVisible()) {
                                System.out.println(recurso);
                            } else {
                                System.out.println("La celda no es visible.");
                            }
                        } else {
                            System.out.println("No existe la entidad introducida");
                        }
                        break;

                    case "mirar":
                        if (comando.length == 2) {
                            Posicion posMirar = new Posicion(comando[1]);  //guarda la posicion pasada

                            if (map.checkCoords(posMirar)) {
                                Celda cellMirar = map.getCelda(posMirar);

                                if (cellMirar.getVisible()) {
                                    switch (cellMirar.getTipo()) {    //En funcion del tipo de la celda
                                        case "soldado":
                                            System.out.println("Celda tipo Pradera, hay un Personaje aqui");
                                            Personaje soldadito = cellMirar.getPersonaje();
                                            System.out.println(soldadito);
                                            break;
                                        case "paisano":
                                            System.out.println("Celda tipo Pradera, hay un Personaje aqui");
                                            Personaje paisanito = cellMirar.getPersonaje();
                                            System.out.println(paisanito);
                                            break;
                                        case "ciudadela":
                                            System.out.println("Celda tipo Edificio");
                                            Edificio ciudadelita = cellMirar.getEdificio();
                                            System.out.println(ciudadelita);
                                            break;
                                        case "casa":
                                            System.out.println("Celda tipo Edificio");
                                            Edificio casita = cellMirar.getEdificio();
                                            System.out.println(casita);
                                            break;
                                        case "cuartel":
                                            System.out.println("Celda tipo Edificio");
                                            Edificio cuartelito = cellMirar.getEdificio();
                                            System.out.println(cuartelito);
                                            break;
                                        case "bosque":
                                            System.out.println("Celda tipo Recurso");
                                            Recurso bosquecillo = cellMirar.getRecurso();
                                            System.out.println(bosquecillo);
                                            break;
                                        case "cantera":
                                            System.out.println("Celda tipo Recurso");
                                            Recurso canterilla = cellMirar.getRecurso();
                                            System.out.println(canterilla);
                                            break;
                                        case "arbusto":
                                            System.out.println("Celda tipo Recurso");
                                            Recurso arbustillo = cellMirar.getRecurso();
                                            System.out.println(arbustillo);
                                            break;
                                    }

                                } else {
                                    System.out.println("La celda no es visible!");
                                }

                            } else {
                                System.out.println("Esa celda no esta disponible!");
                            }
                        } else {
                            System.out.println("Error sintactico en el comando, la forma correcta de usarlo es: mirar (x,y)");
                        }
                        break;
                    case "recolectar":
                        if (comando.length == 3) {
                            Personaje paisanito = map.getPersonajes().get(comando[1]);  //Recoge al paisano del mapa
                            Posicion posPaisanito = paisanito.getPosicion();

                            switch (comando[2]) {
                                case "n":
                                    posPaisanito.moverX(-1);
                                    break;
                                case "s":
                                    posPaisanito.moverX(1);
                                    break;
                                case "o":
                                    posPaisanito.moverY(-1);
                                    break;
                                case "e":
                                    posPaisanito.moverY(1);
                                    break;
                            }
                            if (map.checkCoords(posPaisanito)) {  //En caso de que la coordenada este bien
                                Celda celdita = map.getCelda(posPaisanito);
                                //Comprobamos que la celda tiene un recurso
                                if (celdita.getTipo().equals("arbusto") || celdita.getTipo().equals("cantera") || celdita.getTipo().equals("bosque")) {
                                    Recurso recursito = map.getCelda(posPaisanito).getRecurso();
                                    paisanito.recolectar(recursito, map);
                                } else {
                                    System.out.println("No hay un recurso aqui!");
                                }
                            }
                        } else {
                            System.out.println("Error sintactico en el comando, la forma correcta de usarlo es: recolectar paisano-x Direccion(N,S,E,O)");
                        }
                        break;
                    case "crear":   //Quizas deberiamos comprobar que el usuario escribe Paisano bien
                        if (comando.length == 3) {
                            if (map.getEdificios().containsKey(comando[1])) {
                                switch (comando[2].toLowerCase()) {
                                    case "paisano":
                                        if (map.getEdificios().get(comando[1]).getTipo().equals("ciudadela")) {
                                            Edificio ciudadela = map.getEdificios().get(comando[1]);
                                            ciudadela.crearPaisano(map);

                                        } else {
                                            System.out.println("Este edificio no es  una ciudadela");
                                        }
                                        break;
                                    case "soldado":
                                        if (map.getEdificios().get(comando[1]).getTipo().equals("cuartel")) {
                                            Edificio cuartel = map.getEdificios().get(comando[1]);
                                            cuartel.crearSoldado(map);

                                        } else {
                                            System.out.println("Este edificio no es  un cuartel");
                                        }
                                        break;
                                    default:
                                        System.out.println("Tipo personaje mal introducido");
                                }
                            } else {
                                System.out.println("El edificio indicado no existe");
                            }

                        } else {
                            System.out.println("Comando construir incorrecto");
                        }

                        map.imprimir();
                        break;
                    case "almacenar":
                        if (comando.length == 3) {
                            Personaje paisanoAlmacenar = map.getPersonajes().get(comando[1]);
                            Posicion pAlmacenar = new Posicion(paisanoAlmacenar.getPosicion());

                            switch (comando[2]) {
                                case "n":
                                    pAlmacenar.moverX(-1);
                                    break;
                                case "s":
                                    pAlmacenar.moverX(1);
                                    break;
                                case "e":
                                    pAlmacenar.moverY(1);
                                    break;
                                case "o":
                                    pAlmacenar.moverY(-1);
                                    break;
                            }

                            Celda celdaAlmacenar = map.getCelda(pAlmacenar);
                            if (celdaAlmacenar.getTipo().equals("ciudadela") || celdaAlmacenar.getTipo().equals("cuartel") || celdaAlmacenar.getTipo().equals("casa")) {  //Comprueba que sea un edificio
                                Edificio ciudadelaAlmacenar = celdaAlmacenar.getEdificio();
                                paisanoAlmacenar.almacenarRecurso(ciudadelaAlmacenar);
                            } else {
                                System.out.println("No hay un edificio aqui");
                            }
                        } else {
                            System.out.println("Error sintactico en el comando, forma correcta: almacenar paisano-x Direccion(N,S,E,O)");
                        }
                        break;
                    case "reparar":
                        if (comando.length == 3) {
                            if (map.getPersonajes().containsKey(comando[1])) {
                                Personaje personaje = (Personaje) map.getPersonajes().get(comando[1]);
                                personaje.reparar(map, comando[2]);
                            }
                        } else {
                            System.out.println("Comando reparar mal intruducido. Ejemplo: reparar Paisano-1 S");
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
