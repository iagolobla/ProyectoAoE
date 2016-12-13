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
import Edificios.Casa;
import Juego.Mapa;
import Edificios.Ciudadela;
import Edificios.Cuartel;
import Excepciones.ExcepcionCrear;
import Excepciones.ExcepcionEdificioVacio;
import Excepciones.ExcepcionEntidadNoEncontrada;
import Excepciones.ExcepcionLimiteAlojamiento;
import Excepciones.ExcepcionPosicionNoValida;
import Excepciones.ExcepcionSintaxis;
import Personajes.Personaje;
import Juego.Celda;

public class ComandoCrear implements Comando {

    private String edificio;
    private String tipopersonaje;
    private Mapa mapa;

    public ComandoCrear(String edificio, String tipopersonaje, Mapa mapa) {
        this.edificio = edificio;
        this.tipopersonaje = tipopersonaje;
        this.mapa = mapa;
    }

    public void ejecutar() throws ExcepcionSintaxis, ExcepcionPosicionNoValida, ExcepcionEdificioVacio, ExcepcionEntidadNoEncontrada,ExcepcionLimiteAlojamiento,ExcepcionCrear {
        Celda cell;
        Personaje p;
        if (tipopersonaje.equals("paisano")) {
            if (mapa.getCivilizacion().getCantidades()[3] * Casa.CAPACIDAD <= mapa.getCivilizacion().getCantidades()[0] + mapa.getCivilizacion().getCantidades()[1]) {   //Comprobamos si la suma de paisanos y soldados es mayor igual que la capacidad de almacenamiento
                throw new ExcepcionLimiteAlojamiento("Se necesitan mas casas, no se pueden crear personajes");
            }

            if (mapa.getCivilizacion().getEdificios().containsKey(edificio)) {
                if(edificio.equals("cuartel")){
                    throw new ExcepcionCrear("Tipo edificio incorrecto");
                }
                Ciudadela C = (Ciudadela) mapa.getCivilizacion().getEdificios().get(edificio);
                p = C.crear(tipopersonaje);
            } else {
                throw new ExcepcionEntidadNoEncontrada("No existe el edificio introducido");
            }
            if (mapa.checkCoords(p.mover("n")) && mapa.checkBuilding(p.mover("n"))) {
                cell = mapa.getCelda(p.mover("n"));
                p.setPosicion(p.mover("n"));
                cell.addPersonaje(p);
                mapa.getCivilizacion().getPersonajes().put(p.getNombre(), p);
                mapa.getCivilizacion().setComida(mapa.getCivilizacion().getComida() - 10);

            } else if (mapa.checkCoords(p.mover("s")) && mapa.checkBuilding(p.mover("s"))) {
                cell = mapa.getCelda(p.mover("s"));
                p.setPosicion(p.mover("s"));
                cell.addPersonaje(p);
                mapa.getCivilizacion().getPersonajes().put(p.getNombre(), p);
                mapa.getCivilizacion().setComida(mapa.getCivilizacion().getComida() - 10);
            } else if (mapa.checkCoords(p.mover("e")) && mapa.checkBuilding(p.mover("e"))) {
                cell = mapa.getCelda(p.mover("e"));
                p.setPosicion(p.mover("e"));
                cell.addPersonaje(p);
                mapa.getCivilizacion().getPersonajes().put(p.getNombre(), p);
                mapa.getCivilizacion().setComida(mapa.getCivilizacion().getComida() - 10);
            } else if (mapa.checkCoords(p.mover("o")) && mapa.checkBuilding(p.mover("o"))) {
                cell = mapa.getCelda(p.mover("o"));
                p.setPosicion(p.mover("o"));
                cell.addPersonaje(p);
                mapa.getCivilizacion().getPersonajes().put(p.getNombre(), p);
                mapa.getCivilizacion().setComida(mapa.getCivilizacion().getComida() - 10);
            } else {
                throw new ExcepcionPosicionNoValida("No se puede crear el personaje!");
            }

        } else if (tipopersonaje.equals("caballero") || tipopersonaje.equals("arquero") || tipopersonaje.equals("legionario")) {
            if (mapa.getCivilizacion().getCantidades()[3] * Casa.CAPACIDAD <= mapa.getCivilizacion().getCantidades()[0] + mapa.getCivilizacion().getCantidades()[1]) {   //Comprobamos si la suma de paisanos y soldados es mayor igual que la capacidad de almacenamiento
                throw new ExcepcionLimiteAlojamiento("Se necesitan mas casas, no se pueden crear personajes");
            }
            if (mapa.getCivilizacion().getEdificios().containsKey(edificio)) {
                if(edificio.equals("ciudadela")){
                    throw new ExcepcionCrear("Tipo edificio incorrecto");
                }
                Cuartel C = (Cuartel) mapa.getCivilizacion().getEdificios().get(edificio);
                p = C.crear(tipopersonaje);
            } else {
                throw new ExcepcionEntidadNoEncontrada("No existe el edificio introducido");
            }
            if (mapa.checkCoords(p.mover("n")) && mapa.checkBuilding(p.mover("n"))) {
                cell = mapa.getCelda(p.mover("n"));
                p.setPosicion(p.mover("n"));
                cell.addPersonaje(p);
                mapa.getCivilizacion().getPersonajes().put(p.getNombre(), p);
                mapa.getCivilizacion().setComida(mapa.getCivilizacion().getComida() - 10);

            } else if (mapa.checkCoords(p.mover("s")) && mapa.checkBuilding(p.mover("s"))) {
                cell = mapa.getCelda(p.mover("s"));
                p.setPosicion(p.mover("s"));
                cell.addPersonaje(p);
                mapa.getCivilizacion().getPersonajes().put(p.getNombre(), p);
                mapa.getCivilizacion().setComida(mapa.getCivilizacion().getComida() - 10);
            } else if (mapa.checkCoords(p.mover("e")) && mapa.checkBuilding(p.mover("e"))) {
                cell = mapa.getCelda(p.mover("e"));
                p.setPosicion(p.mover("e"));
                cell.addPersonaje(p);
                mapa.getCivilizacion().getPersonajes().put(p.getNombre(), p);
                mapa.getCivilizacion().setComida(mapa.getCivilizacion().getComida() - 10);
            } else if (mapa.checkCoords(p.mover("o")) && mapa.checkBuilding(p.mover("o"))) {
                cell = mapa.getCelda(p.mover("o"));
                p.setPosicion(p.mover("o"));
                cell.addPersonaje(p);
                mapa.getCivilizacion().getPersonajes().put(p.getNombre(), p);
                mapa.getCivilizacion().setComida(mapa.getCivilizacion().getComida() - 10);
            } else {
                throw new ExcepcionPosicionNoValida("No se puede crear el personaje!");
            }
        } else {
            throw new ExcepcionEntidadNoEncontrada(" tipo de personaje mal introducido");
        }
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
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
