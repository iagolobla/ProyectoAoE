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
    private ConsolaNormal Shell;
    
    public ComandoConstruir(String personaje, String pto_cardinal, String tipo_edificio, Mapa mapa, ConsolaNormal Shell) {
        this.personaje = personaje;
        this.pto_cardinal = pto_cardinal;
        this.tipo_edificio = tipo_edificio;
        this.mapa = mapa;
        this.Shell = Shell;
    }
    
    public void ejecutar() throws ExcepcionConstruir, ExcepcionCrear, ExcepcionSintaxis {
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
                throw new ExcepcionSintaxis("tipo edificio mal introducido");
        }
        if (mapa.getCivilizacion().getMadera() < gasto || mapa.getCivilizacion().getPiedra() < gasto) {
            throw new ExcepcionConstruir("No quedan suficientes recursos");
        }
        
        if (mapa.getCivilizacion().getPersonajes().containsKey(personaje)) {
            p = mapa.getCivilizacion().getPersonajes().get(personaje);
        } else {
            //Excepcion Aqui
        }
        Edificio ef = p.construir(tipo_edificio);
        
        if (mapa.checkCoords(ef.mover(pto_cardinal)) && mapa.checkBuilding(ef.mover(pto_cardinal))) {
            cell = mapa.getCelda(ef.mover(pto_cardinal));
            ef.setPosicion(p.mover(pto_cardinal));
            cell.setEdificio(ef);
            mapa.getCivilizacion().getEdificios().put(ef.getNombre(), ef);
            mapa.getCivilizacion().setMadera(mapa.getCivilizacion().getMadera() - gasto);
            mapa.getCivilizacion().setPiedra(mapa.getCivilizacion().getPiedra() - gasto);
            Shell.imprimir("Se ha construido el edificio " + ef.getNombre() + " en la posicion " + ef.getPosicion());
        } else {
            //Excepcion Aqui
        }
        
    }
}
