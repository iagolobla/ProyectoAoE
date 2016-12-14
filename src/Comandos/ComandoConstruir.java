/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Edificios.Ciudadela;
import Edificios.Edificio;
import Excepciones.ExcepcionConstruir;
import Excepciones.ExcepcionCrear;
import Excepciones.ExcepcionSintaxis;
import Juego.Celda;
import Juego.ConsolaNormal;
import Juego.Mapa;
import Juego.Posicion;
import static Juego.Principal.SHELL;
import Personajes.Personaje;

/**
 *
 * @author iagolobla
 */
public class ComandoConstruir implements Comando {
    
    public static final int GASTOCASA = 20;
    public static final int GASTOCUARTEL = 40;
    public static final int GASTOCIUDADELA = 40;
    public static final int GASTOTORRE = 50;
    
    private String personaje;
    private String pto_cardinal;
    private String tipo_edificio;
    private Mapa mapa;
    
    
    public ComandoConstruir(String personaje, String pto_cardinal, String tipo_edificio, Mapa mapa) {
        this.personaje = personaje;
        this.pto_cardinal = pto_cardinal;
        this.tipo_edificio = tipo_edificio;
        this.mapa = mapa;
        
    }
    
    public void ejecutar() throws ExcepcionConstruir, ExcepcionSintaxis {
        Celda cell;
        Personaje p;
        int gasto;
        switch (tipo_edificio) {
            case "casa":
                gasto = GASTOCASA;
                break;
            case "ciudadela":
                gasto = GASTOCIUDADELA;
                break;
            case "cuartel":
                gasto = GASTOCUARTEL;
                break;
            case "torre":
                gasto = GASTOTORRE;
                break;
            default:
                throw new ExcepcionSintaxis("Tipo de edificio mal introducido!");
        }
        
        if (mapa.getCivilizacion().getMadera() < gasto || mapa.getCivilizacion().getPiedra() < gasto) {
            throw new ExcepcionConstruir("No quedan suficientes recursos");
        }
        
        if (mapa.getCivilizacion().getPersonajes().containsKey(personaje)) {
            p = mapa.getCivilizacion().getPersonajes().get(personaje);
        } else {
            throw new ExcepcionConstruir("El personaje indicado no existe!");
        }
        
        Posicion nueva = p.mover(pto_cardinal);
        
        if (mapa.checkCoords(nueva) && mapa.checkBuilding(nueva)) {
            Edificio ef = p.construir(tipo_edificio);
            cell = mapa.getCelda(nueva);
            ef.setPosicion(nueva);
            cell.setEdificio(ef);
            mapa.getCivilizacion().getEdificios().put(ef.getNombre(), ef);
            mapa.getCivilizacion().setMadera(mapa.getCivilizacion().getMadera() - gasto);
            mapa.getCivilizacion().setPiedra(mapa.getCivilizacion().getPiedra() - gasto);
            SHELL.imprimir("Se ha construido el edificio " + ef.getNombre() + " en la posicion " + ef.getPosicion());
        } else {
            throw new ExcepcionConstruir("No se puede construir en esa direccion!");
        }
        
    }
}
