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
import static Juego.Principal.SHELL;
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
    

    public ComandoDescribir(String entidad, Mapa mapa,ConsolaNormal SHELL) {
        this.entidad = entidad;
        this.map = mapa;
        
    }

    public void ejecutar() throws ExcepcionDescribir{
        if (map.getCivilizacion().getPersonajes().containsKey(entidad)) {
            Personaje personaje = map.getCivilizacion().getPersonajes().get(entidad);
            SHELL.imprimir(personaje.toString());
        } else if (map.getCivilizacion().getEdificios().containsKey(entidad)) {
            Edificio edificio = map.getCivilizacion().getEdificios().get(entidad);
            SHELL.imprimir(edificio.toString());
        } else if (map.getRecursos().containsKey(entidad)) {
            Contenedor recurso = map.getRecursos().get(entidad);
            if (map.getCelda(recurso.getPosicion()).isVisible(map.getCivilizacion())) {
                SHELL.imprimir(recurso.toString());
            } else {
                throw new ExcepcionDescribir("La entidad que desea describir está en una celda no visible");
            }
        } else if (map.getCivilizacion().getGrupos().containsKey(entidad)) {
            Grupo G = map.getCivilizacion().getGrupos().get(entidad);
            SHELL.imprimir(G.toString());
        } else {
            throw new ExcepcionDescribir("No existe el tipo de entidad introducida");
        }
    }
}
