/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Excepciones.ExcepcionSintaxis;
import Juego.ConsolaNormal;
import Juego.Mapa;
import static Juego.Principal.SHELL;

/**
 *
 * @author iagolobla
 */
public class ComandoListar implements Comando {

    private Mapa map;
    private String tipoentidad;
    

    public ComandoListar(String tipoentidad, Mapa mapa) {
        this.map = mapa;
        this.tipoentidad = tipoentidad;
        
    }

    public void ejecutar() throws ExcepcionSintaxis{
        switch (tipoentidad) {
            case "personajes":
                for (String k : map.getCivilizacion().getPersonajes().keySet()) {
                    SHELL.imprimir("Nombre : " + k + ", Posicion: " + map.getCivilizacion().getPersonajes().get(k).getPosicion());
                }
                break;
            case "edificios":
                for (String k : map.getCivilizacion().getEdificios().keySet()) {
                    SHELL.imprimir("Nombre : " + k + ", Posicion: " + map.getCivilizacion().getEdificios().get(k).getPosicion());
                }
                break;
            case "civilizaciones":
                for (String k : map.getCivilizaciones().keySet()) {
                    SHELL.imprimir("Nombre : " + k);
                }
                break;
            case "grupos":
                for (String G : map.getCivilizacion().getGrupos().keySet()) {
                    SHELL.imprimir("Grupo: " + G);
                }
                break;
            default:
                throw new ExcepcionSintaxis("tipo de entidad incorrecto");
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
