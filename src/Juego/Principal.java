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
import Comandos.ComandoConstruir;
import Comandos.ComandoCambiar;
import Comandos.ComandoCivilizacion;
import Comandos.ComandoCrear;
import Comandos.ComandoDescribir;
import Comandos.ComandoImprimir;
import Comandos.ComandoListar;
import Comandos.ComandoMirar;
import Comandos.ComandoMover;
import Comandos.ComandoReparar;
import Comandos.ComandoSalir;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import Personajes.Personaje;
import Excepciones.ExcepcionSintaxis;

//Bienvenido al mejor juego de la historia, hecho de la mejor forma posible
public class Principal {

    public Principal() {
        ConsolaNormal Shell = new ConsolaNormal();
        Boolean seguir = true;
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
        Juego juego = new Juego(new Mapa(6, 6, 6, civilizaciones.values()));
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
                            if (comando.length != 3) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoMover(comando[2], comando[1], juego.getMapa(), Shell).ejecutar();
                            new ComandoImprimir(juego.getMapa(), Shell).ejecutar();

                        } catch (Exception E) {
                            Shell.imprimir("Error: " + E.getMessage());
                            break;
                        }
                        break;
                    case "crear":
                        try {
                            if (comando.length != 3) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoCrear(comando[1], comando[2], juego.getMapa(), Shell).ejecutar();
                            new ComandoImprimir(juego.getMapa(), Shell).ejecutar();

                        } catch (Exception E) {
                            Shell.imprimir("Error: " + E.getMessage());
                            break;
                        }
                        break;
                    case "listar":
                        try {
                            if (comando.length != 2) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoListar(comando[1], juego.getMapa(), Shell).ejecutar();

                        } catch (Exception E) {
                            Shell.imprimir("Error: " + E.getMessage());
                            break;
                        }
                        break;
                    case "describir":
                        try {
                            if (comando.length != 2) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoDescribir(comando[1], juego.getMapa(), Shell).ejecutar();

                        } catch (Exception E) {
                            Shell.imprimir("Error: " + E.getMessage());
                            break;
                        }
                        break;
                    case "mirar":
                        try {
                            if (comando.length != 2) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoMirar(comando[1], juego.getMapa(), Shell).ejecutar();

                        } catch (Exception E) {
                            Shell.imprimir("Error: " + E.getMessage());
                            break;
                        }
                        break;
                    case "reparar":
                        try {
                            if (comando.length != 3) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoReparar(comando[2], comando[1], juego.getMapa(), Shell).ejecutar();
                            new ComandoImprimir(juego.getMapa(), Shell).ejecutar();

                        } catch (Exception E) {
                            Shell.imprimir("Error: " + E.getMessage());
                            break;
                        }
                        break;
                    case "construir":
                        try {
                            if (comando.length != 4) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoConstruir(comando[1], comando[3], comando[2], juego.getMapa(), Shell).ejecutar();
                            new ComandoImprimir(juego.getMapa(), Shell).ejecutar();

                        } catch (Exception E) {
                            Shell.imprimir("Error: " + E.getMessage());
                            break;
                        }
                        break;
                    case "recolectar":
                        try {

                        } catch (Exception E) {
                            Shell.imprimir("Error: " + E.getMessage());
                            break;
                        }
                        break;
                    case "cambiar":
                        try {
                            if (comando.length == 2) {
                                new ComandoCambiar(comando[1], juego.getMapa(), Shell).ejecutar();
                            } else {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }
                        } catch (Exception E) {
                            Shell.imprimir("Error: " + E.getMessage());
                            break;
                        }
                        new ComandoImprimir(juego.getMapa(), Shell).ejecutar();
                        break;
                    case "civilizacion":
                        try {
                            if (comando.length == 1) {
                                new ComandoCivilizacion(juego.getMapa(), Shell).ejecutar();
                            } else {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }
                        } catch (Exception E) {
                            Shell.imprimir("Error: " + E.getMessage());
                            break;
                        }
                        break;
                    case "imprimir":
                        try {
                            if (comando.length == 1) {
                                new ComandoImprimir(juego.getMapa(), Shell).ejecutar();
                            } else {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }
                        } catch (Exception E) {
                            Shell.imprimir("Error: " + E.getMessage());
                            break;
                        }

                        break;
                    case "salir":
                        try {
                            if (comando.length == 1) {
                                seguir=false;
                            } else {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }
                        } catch (Exception E) {
                            Shell.imprimir("Error: " + E.getMessage());
                            break;
                        }

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
