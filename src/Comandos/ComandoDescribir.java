/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Edificios.Ciudadela;
import Edificios.Edificio;
import Excepciones.ExcepcionDescribir;
import Juego.ConsolaNormal;
import Juego.Mapa;
import Personajes.Grupo;
import Personajes.Personaje;
import Recursos.Contenedor;
import Recursos.Recurso;

/**
 *
 * @author iagolobla
 */
public class ComandoDescribir implements Comando {

    private String entidad;
    private Mapa map;
    private ConsolaNormal Shell;

    public ComandoDescribir(String entidad, Mapa mapa,ConsolaNormal Shell) {
        this.entidad = entidad;
        this.map = mapa;
        this.Shell=Shell;
    }

    public void ejecutar() throws ExcepcionDescribir{
        if (map.getCivilizacion().getPersonajes().containsKey(entidad)) {
            Personaje personaje = map.getCivilizacion().getPersonajes().get(entidad);
            Shell.imprimir(personaje.toString());
        } else if (map.getCivilizacion().getEdificios().containsKey(entidad)) {
            Edificio edificio = map.getCivilizacion().getEdificios().get(entidad);
            Shell.imprimir(edificio.toString());
        } else if (map.getRecursos().containsKey(entidad)) {
            Contenedor recurso = map.getRecursos().get(entidad);
            if (map.getCelda(recurso.getPosicion()).isVisible(map.getCivilizacion())) {
                Shell.imprimir(recurso.toString());
            } else {
                throw new ExcepcionDescribir("La entidad que desea describir está en una celda no visible");
            }
        } else if (map.getCivilizacion().getGrupos().containsKey(entidad)) {
            Grupo G = map.getCivilizacion().getGrupos().get(entidad);
            Shell.imprimir(G.toString());
        } else {
            throw new ExcepcionDescribir("No existe el tipo de entidad introducida");
        }
    }
}
