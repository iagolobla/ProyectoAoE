/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

/**
 *
 * @author iagolobla
 */
import Juego.Mapa;
import Edificios.Ciudadela;
import Excepciones.ExcepcionEdificioVacio;
import Excepciones.ExcepcionPosicionNoValida;
import Excepciones.ExcepcionSintaxis;
import Personajes.Personaje;
import Juego.Celda;

public class ComandoCrear implements Comando{
    private String ciudadela;
    private String tipopersonaje;
    private Mapa mapa;
    
    public ComandoCrear(String ciudadela, String tipopersonaje, Mapa mapa){
        this.ciudadela=ciudadela;
        this.tipopersonaje=tipopersonaje;
        this.mapa=mapa;
    }
    
    public void ejecutar() throws ExcepcionSintaxis, ExcepcionPosicionNoValida, ExcepcionEdificioVacio {
        if(tipopersonaje.equals("paisano")){
            Celda cell;
            if (mapa.getCivilizacion().getEdificios().containsKey(ciudadela));
            Ciudadela C=(Ciudadela)mapa.getCivilizacion().getEdificios().get(ciudadela);
            Personaje p=C.crear(tipopersonaje);
            if(mapa.checkCoords(p.mover("n")) && mapa.checkBuilding(p.mover("n"))){
                cell=mapa.getCelda(p.mover("n"));
                p.setPosicion(p.mover("n"));
                cell.addPersonaje(p);
                mapa.getCivilizacion().getPersonajes().put(p.getNombre(), p);
                
            }else if(mapa.checkCoords(p.mover("s")) && mapa.checkBuilding(p.mover("s"))){
                cell=mapa.getCelda(p.mover("s"));
                p.setPosicion(p.mover("s"));
                cell.addPersonaje(p);
                mapa.getCivilizacion().getPersonajes().put(p.getNombre(), p);
            }else if(mapa.checkCoords(p.mover("e")) && mapa.checkBuilding(p.mover("e"))){
                cell=mapa.getCelda(p.mover("e"));
                p.setPosicion(p.mover("e"));
                cell.addPersonaje(p);
                mapa.getCivilizacion().getPersonajes().put(p.getNombre(), p);
            }else if(mapa.checkCoords(p.mover("o")) && mapa.checkBuilding(p.mover("o"))){
                cell=mapa.getCelda(p.mover("o"));
                p.setPosicion(p.mover("o"));
                cell.addPersonaje(p);
                mapa.getCivilizacion().getPersonajes().put(p.getNombre(), p);
            }else{
                throw new ExcepcionPosicionNoValida("No se puede crear el personaje!");
            }
            
        }
    }

    public String getCiudadela() {
        return ciudadela;
    }

    public void setCiudadela(String ciudadela) {
        this.ciudadela = ciudadela;
    }

    public String getTipopersonaje() {
        return tipopersonaje;
    }

    public void setTipopersonaje(String tipopersonaje) {
        this.tipopersonaje = tipopersonaje;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }
    
}
