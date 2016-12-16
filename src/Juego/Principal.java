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
import Comandos.ComandoAgrupar;
import Comandos.ComandoAlmacenar;
import Comandos.ComandoAtacar;
import Comandos.ComandoConstruir;
import Comandos.ComandoCambiar;
import Comandos.ComandoCivilizacion;
import Comandos.ComandoCrear;
import Comandos.ComandoDefender;
import Comandos.ComandoDesagrupar;
import Comandos.ComandoDescribir;
import Comandos.ComandoDesligar;
import Comandos.ComandoImprimir;
import Comandos.ComandoListar;
import Comandos.ComandoMirar;
import Comandos.ComandoMover;
import Comandos.ComandoRecolectar;
import Comandos.ComandoReparar;
import Comandos.ComandoSalir;
import Excepciones.ExcepcionComando;
import Excepciones.ExcepcionMover;
import Excepciones.ExcepcionRecolectar;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import Personajes.Personaje;
import Excepciones.ExcepcionSintaxis;
import java.io.FileNotFoundException;

//Bienvenido al mejor juego de la historia, hecho de la mejor forma posible
public class Principal {

    public static final ConsolaNormal SHELL = new ConsolaNormal();

    public Principal() {

        Boolean seguir = true;
        SHELL.imprimir("Bienvenido al Juego!");
        Juego juego = null;
        String opcion;
        boolean cargar = true;
        opcion = SHELL.leer("Quiere cargar ficheros? (S/N): ");

        do {
            switch (opcion) {
                case "S":
                    cargar = false;
                    SHELL.imprimir("Introduzca el comando de carga de ficheros: ");
                    String comandocargar = SHELL.leer("> ");
                    String[] ruta = comandocargar.split(" ");
                    String com = ruta[0].toLowerCase();
                    if (ruta[0].equals("cargar")) {
                        try {
                            juego = new CargadorJuegoDeFichero(ruta[1]).cargar();
                        } catch (Exception E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            cargar = true;
                            break;
                        }
                    } else {
                        cargar = true;
                    }
                    break;
                case "N":
                    cargar = false;
                    juego = new CargadorJuegoPorDefecto().cargar();
                    break;
                default:
                    cargar = true;

            }
        } while (cargar);

        

        while (seguir) {
            String linea = SHELL.leer("$ ");
            String lineaLowerCase = linea.toLowerCase();    //Pasamos todo a minusculas
            String[] comando = lineaLowerCase.split(" ");
            if (comando.length > 0) {
                switch (comando[0].toLowerCase()) {
                    case "mover":
                        try {
                            if (comando.length != 3) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoMover(comando[2], comando[1], juego.getMapa()).ejecutar();
                            new ComandoImprimir(juego.getMapa()).ejecutar();
                            juego.getMapa().turnoTorres();

                        } catch (ExcepcionComando E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (ExcepcionSintaxis E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (Exception E) {
                            SHELL.imprimir("Error Inesperado: " + E.getMessage());
                            break;
                        }
                        break;
                    case "crear":
                        try {
                            if (comando.length != 3) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoCrear(comando[1], comando[2], juego.getMapa()).ejecutar();
                            new ComandoImprimir(juego.getMapa()).ejecutar();
                            juego.getMapa().turnoTorres();

                        } catch (ExcepcionComando E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (ExcepcionSintaxis E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (Exception E) {
                            SHELL.imprimir("Error Inesperado: " + E.getMessage());
                            break;
                        }
                        break;
                    case "recolectar":
                        try {
                            if (comando.length != 3) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoRecolectar(comando[2], comando[1], juego.getMapa()).ejecutar();
                            new ComandoImprimir(juego.getMapa()).ejecutar();
                            juego.getMapa().turnoTorres();
                        } catch (ExcepcionRecolectar E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (ExcepcionSintaxis E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (Exception E) {
                            SHELL.imprimir("Error Inesperado: " + E.getMessage());
                            break;
                        }
                        break;
                    case "agrupar":
                        try {
                            if (comando.length != 2) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoAgrupar(juego.getMapa(), comando[1]).ejecutar();
                            new ComandoImprimir(juego.getMapa()).ejecutar();
                            juego.getMapa().turnoTorres();

                        } catch (ExcepcionComando E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (ExcepcionSintaxis E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (Exception E) {
                            SHELL.imprimir("Error Inesperado: " + E.getMessage());
                            break;
                        }
                        break;
                    case "desagrupar":
                        try {
                            if (comando.length != 2) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoDesagrupar(juego.getMapa(), comando[1]).ejecutar();
                            new ComandoImprimir(juego.getMapa()).ejecutar();
                            juego.getMapa().turnoTorres();

                        } catch (ExcepcionComando E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (ExcepcionSintaxis E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (Exception E) {
                            SHELL.imprimir("Error Inesperado: " + E.getMessage());
                            break;
                        }
                        break;
                    case "defender":
                        try {
                            if (comando.length != 3) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoDefender(juego.getMapa(), comando[1], comando[2]).ejecutar();
                            new ComandoImprimir(juego.getMapa()).ejecutar();
                            juego.getMapa().turnoTorres();

                        } catch (ExcepcionComando E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (ExcepcionSintaxis E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (Exception E) {
                            SHELL.imprimir("Error Inesperado: " + E.getMessage());
                            break;
                        }
                        break;
                    case "atacar":
                        try {
                            if (comando.length != 3) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoAtacar(juego.getMapa(), comando[2], comando[1]).ejecutar();
                            new ComandoImprimir(juego.getMapa()).ejecutar();
                            juego.getMapa().turnoTorres();

                        } catch (ExcepcionComando E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (ExcepcionSintaxis E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (Exception E) {
                            SHELL.imprimir("Error Inesperado: " + E.getMessage());
                            break;
                        }
                        break;
                    case "desligar":
                        try {
                            if (comando.length != 3) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoDesligar(juego.getMapa(), comando[1], comando[2]).ejecutar();
                            new ComandoImprimir(juego.getMapa()).ejecutar();
                            juego.getMapa().turnoTorres();

                        } catch (ExcepcionComando E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (ExcepcionSintaxis E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (Exception E) {
                            SHELL.imprimir("Error Inesperado: " + E.getMessage());
                            break;
                        }
                        break;

                    case "almacenar":
                        try {
                            if (comando.length != 3) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoAlmacenar(comando[1], comando[2], juego.getMapa()).ejecutar();
                            new ComandoImprimir(juego.getMapa()).ejecutar();
                            juego.getMapa().turnoTorres();

                        } catch (ExcepcionComando E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (ExcepcionSintaxis E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (Exception E) {
                            SHELL.imprimir("Error Inesperado: " + E.getMessage());
                            break;
                        }
                        break;
                    case "listar":
                        try {
                            if (comando.length != 2) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoListar(comando[1], juego.getMapa()).ejecutar();

                        
                        } catch (ExcepcionSintaxis E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (Exception E) {
                            SHELL.imprimir("Error Inesperado: " + E.getMessage());
                            break;
                        }
                        break;
                    case "describir":
                        try {
                            if (comando.length != 2) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoDescribir(comando[1], juego.getMapa()).ejecutar();

                        } catch (ExcepcionComando E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (ExcepcionSintaxis E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (Exception E) {
                            SHELL.imprimir("Error Inesperado: " + E.getMessage());
                            break;
                        }
                        break;
                    case "mirar":
                        try {
                            if (comando.length != 2) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoMirar(comando[1], juego.getMapa()).ejecutar();

                        } catch (ExcepcionComando E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (ExcepcionSintaxis E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (Exception E) {
                            SHELL.imprimir("Error Inesperado: " + E.getMessage());
                            break;
                        }
                        break;
                    case "reparar":
                        try {
                            if (comando.length != 3) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoReparar(comando[2], comando[1], juego.getMapa()).ejecutar();
                            new ComandoImprimir(juego.getMapa()).ejecutar();
                            juego.getMapa().turnoTorres();

                        } catch (ExcepcionComando E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (ExcepcionSintaxis E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (Exception E) {
                            SHELL.imprimir("Error Inesperado: " + E.getMessage());
                            break;
                        }
                        break;
                    case "construir":
                        try {
                            if (comando.length != 4) {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }

                            new ComandoConstruir(comando[1], comando[3], comando[2], juego.getMapa()).ejecutar();
                            new ComandoImprimir(juego.getMapa()).ejecutar();
                            juego.getMapa().turnoTorres();

                        } catch (ExcepcionComando E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (ExcepcionSintaxis E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (Exception E) {
                            SHELL.imprimir("Error Inesperado: " + E.getMessage());
                            break;
                        }
                        break;

                    case "cambiar":
                        try {
                            if (comando.length == 2) {
                                new ComandoCambiar(comando[1], juego.getMapa()).ejecutar();
                            } else {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }
                        } catch (ExcepcionComando E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (ExcepcionSintaxis E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (Exception E) {
                            SHELL.imprimir("Error Inesperado: " + E.getMessage());
                            break;
                        }
                        new ComandoImprimir(juego.getMapa()).ejecutar();
                        break;
                    case "civilizacion":
                        try {
                            if (comando.length == 1) {
                                new ComandoCivilizacion(juego.getMapa()).ejecutar();
                            } else {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }
                        } catch (ExcepcionSintaxis E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (Exception E) {
                            SHELL.imprimir("Error Inesperado: " + E.getMessage());
                            break;
                        }
                        break;
                    case "imprimir":
                        try {
                            if (comando.length == 1) {
                                new ComandoImprimir(juego.getMapa()).ejecutar();
                            } else {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }
                        } catch (ExcepcionSintaxis E) {
                            SHELL.imprimir("Error: " + E.getMessage());
                            break;
                        } catch (Exception E) {
                            SHELL.imprimir("Error Inesperado: " + E.getMessage());
                            break;
                        }

                        break;
                    case "salir":
                        try {
                            if (comando.length == 1) {
                                seguir = false;
                            } else {
                                throw new ExcepcionSintaxis("Error Sintactico, Comando mal introducido");
                            }
                        } catch (Exception E) {
                            SHELL.imprimir("Error: " + E.getMessage());
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
