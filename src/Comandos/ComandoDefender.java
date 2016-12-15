/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Edificios.Edificio;
import Excepciones.ExcepcionDefender;
import Excepciones.ExcepcionReparar;
import Excepciones.ExcepcionSintaxis;
import Juego.Celda;
import Juego.Mapa;
import Juego.Posicion;
import Personajes.Grupo;
import Personajes.Personaje;

/**
 *
 * @author iagolobla
 */
public class ComandoDefender implements Comando {

    private Mapa mapa;
    String personaje;
    String pto_cardinal;

    public ComandoDefender(Mapa mapa, String personaje, String pto_cardinal) {
        this.mapa = mapa;
        this.personaje = personaje;
        this.pto_cardinal = pto_cardinal;
    }

    public void ejecutar() throws ExcepcionDefender,ExcepcionSintaxis{
        Personaje p = mapa.getCivilizacion().getPersonajes().get(personaje);
        if (p == null) {
            throw new NullPointerException("El Personaje especificado no existe!");
        }
        if (mapa.getCelda(p.mover(pto_cardinal)).isEdificio() && mapa.checkCoords(p.mover(pto_cardinal))) {
            Celda cell = mapa.getCelda(p.mover(pto_cardinal));
            Edificio ef = cell.getEdificio();
            if (ef == null) {
                throw new ExcepcionDefender("No hay un edificio aqui");
            }
            p.defender(ef);
            mapa.getCelda(p.getPosicion()).quitarPersonaje(p);
            Posicion posnueva=p.mover(pto_cardinal);
            if(p instanceof Grupo){
                p.setPosicion(posnueva);
                Grupo G=(Grupo) p;
                for(Personaje person:G.getPersonajes()){
                    person.setPosicion(posnueva);
                }
            }else{
                p.setPosicion(posnueva);
            }
        }else{
            throw new ExcepcionDefender("No es posible defender en esa direccion");
        }
    }
}
