/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Juego.Mapa;

/**
 *
 * @author iagolobla
 */
public class ComandoListar implements Comando {

    Mapa map;
    String tipoentidad;

    public ComandoListar(String tipoentidad, Mapa mapa) {
        this.map = mapa;
        this.tipoentidad = tipoentidad;
    }

    public void ejecutar() {
        switch (tipoentidad) {
            case "personajes":
                for (String k : map.getCivilizacion().getPersonajes().keySet()) {
                    System.out.println("Nombre : " + k + ", Posicion: " + map.getCivilizacion().getPersonajes().get(k).getPosicion());
                }
                break;
            case "edificios":
                for (String k : map.getCivilizacion().getEdificios().keySet()) {
                    System.out.println("Nombre : " + k + ", Posicion: " + map.getCivilizacion().getEdificios().get(k).getPosicion());
                }
                break;
            case "civilizaciones":
                for (String k : map.getCivilizaciones().keySet()) {
                    System.out.println("Nombre : " + k);
                }
                break;
            case "grupos":
                for (String G : map.getCivilizacion().getGrupos().keySet()) {
                    System.out.println("Grupo: " + G);
                }
                break;
            default:
                System.out.println("comando incorrecto. Debe introducir listar personajes o listar edificios.");
                break;
        }
    }

    public Mapa getMap() {
        return map;
    }

    public void setMap(Mapa map) {
        this.map = map;
    }

    public String getTipoentidad() {
        return tipoentidad;
    }

    public void setTipoentidad(String tipoentidad) {
        this.tipoentidad = tipoentidad;
    }
    
}
/*if (comando.length == 2) {
                            switch (comando[1].toLowerCase()) {
                                case "personajes":
                                    for (String k : map.getCivilizacion().getPersonajes().keySet()) {
                                        System.out.println("Nombre : " + k + ", Posicion: " + map.getCivilizacion().getPersonajes().get(k).getPosicion());
                                    }
                                    break;
                                case "edificios":
                                    for (String k : map.getCivilizacion().getEdificios().keySet()) {
                                        System.out.println("Nombre : " + k + ", Posicion: " + map.getCivilizacion().getEdificios().get(k).getPosicion());
                                    }
                                    break;
                                case "civilizaciones":
                                    for (String k : map.getCivilizaciones().keySet()) {
                                        System.out.println("Nombre : " + k);
                                    }
                                    break;
                                case "grupos":
                                    for (String G : C.getGrupos().keySet()) {
                                        System.out.println("Grupo: " + G);
                                    }
                                    break;
                                default:
                                    System.out.println("comando incorrecto. Debe introducir listar personajes o listar edificios.");
                                    break;
                            }
                        } else {
                            System.out.println("Error sintactico!");
                        }*/
