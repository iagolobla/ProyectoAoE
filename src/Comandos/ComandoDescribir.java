/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Edificios.Ciudadela;
import Edificios.Edificio;
import Excepciones.ExcepcionEntidadNoEncontrada;
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

    public ComandoDescribir(String entidad, Mapa mapa) {
        this.entidad = entidad;
        this.map = mapa;
    }

    public void ejecutar() throws ExcepcionEntidadNoEncontrada{
        if (map.getCivilizacion().getPersonajes().containsKey(entidad)) {
            Personaje personaje = map.getCivilizacion().getPersonajes().get(entidad);
            System.out.println(personaje);
        } else if (map.getCivilizacion().getEdificios().containsKey(entidad)) {
            Edificio edificio = map.getCivilizacion().getEdificios().get(entidad);
            System.out.println(edificio);
        } else if (map.getRecursos().containsKey(entidad)) {
            Contenedor recurso = map.getRecursos().get(entidad);
            if (map.getCelda(recurso.getPosicion()).isVisible(map.getCivilizacion())) {
                System.out.println(recurso);
            } else {
                System.out.println("La celda no es visible.");
            }
        } else if (map.getCivilizacion().getGrupos().containsKey(entidad)) {
            Grupo G = map.getCivilizacion().getGrupos().get(entidad);
            System.out.println(G);
        } else {
            throw new ExcepcionEntidadNoEncontrada("Entidad no encontrada");
        }
    }
}
