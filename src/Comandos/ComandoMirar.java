/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Edificios.Casa;
import Edificios.Ciudadela;
import Edificios.Cuartel;
import Edificios.Edificio;
import Edificios.Torre;
import Juego.Celda;
import Juego.Colores;
import Juego.ConsolaNormal;
import Juego.Mapa;
import Juego.Posicion;
import static Juego.Principal.SHELL;
import Personajes.Arquero;
import Personajes.Caballero;
import Personajes.Legionario;
import Personajes.Paisano;
import Personajes.Personaje;
import Recursos.Arbusto;
import Recursos.Bosque;
import Recursos.Pradera;
import java.util.ArrayList;

/**
 *
 * @author iagolobla
 */
public class ComandoMirar implements Comando {

    private String posicion;
    private Mapa mapa;
    

    public ComandoMirar(String posicion, Mapa mapa) {
        this.posicion = posicion;
        this.mapa = mapa;
        
    }

    public void ejecutar() {
        Posicion posMirar = new Posicion(posicion);
        if (mapa.checkCoords(posMirar)) {
            Celda cell = mapa.getCelda(posMirar);
            if (cell.isVisible(mapa.getCivilizacion())) {
                if (cell.getContenedor() instanceof Pradera) {
                    if (cell.getEdificio() instanceof Edificio) {
                        SHELL.imprimir(cell.getEdificio().toString());
                    } else if (cell.getPersonajes().size() > 0) {
                        ArrayList<Personaje> personajes = cell.getPersonajes();
                        if (personajes.size() > 0) {
                            SHELL.imprimir("Hay personajes aqui!");
                            for (Personaje person : personajes) {
                                SHELL.imprimir(person.toString());
                            }
                        }
                    }

                } else {
                    SHELL.imprimir(cell.getContenedor().toString());
                }
            } else {
                System.out.println("no es visible");
            }
        }
    }
}
/*
if (map.checkCoords(posMirar)) {
                                Celda cellMirar = map.getCelda(posMirar);
                                
                                if (cellMirar.isVisible(map.getCivilizacion().getNombre())) {
                                    switch (cellMirar.getTipo()) {    //En funcion del tipo de la celda
                                        case "Pradera":
                                            System.out.println("Celda tipo Pradera");
                                            ArrayList<Personaje> personajes = cellMirar.getPersonajes();
                                            if (personajes.size() > 0) {
                                                System.out.println("Hay personajes aqui!");
                                                for (Personaje person : personajes) {
                                                    System.out.println(person);
                                                }
                                            }
                                            
                                            break;
                                        case "ciudadela":
                                            System.out.println("Celda tipo Edificio");
                                            Edificio ciudadelita = cellMirar.getEdificio();
                                            ciudadelita.imprimirCiudadela(map.getCivilizacion());
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
 */
