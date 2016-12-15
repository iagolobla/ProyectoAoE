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
import Edificios.Edificio;
import Excepciones.ExcepcionCelda;
import Excepciones.ExcepcionCrear;
import Excepciones.ExcepcionSintaxis;
import Personajes.Personaje;
import Juego.Celda;
import Juego.Posicion;
import static Juego.Principal.SHELL;

public class ComandoCrear implements Comando {

    private String edificio;
    private String tipopersonaje;
    private Mapa mapa;
    

    public ComandoCrear(String edificio, String tipopersonaje, Mapa mapa) {
        this.edificio = edificio;
        this.tipopersonaje = tipopersonaje;
        this.mapa = mapa;
        
    }

    public void ejecutar() throws ExcepcionSintaxis, ExcepcionCrear, ExcepcionCelda {
        Celda cell;
        Personaje p;
        Edificio ef;
        int gasto = 10;  //Cantidad de comida para crear un personaje
        int comida = mapa.getCivilizacion().getComida();    //Comida disponible

        //Comprueba si hay sitio, si existe el edificio, y si hay suficiente comida (Por ese orden)
        if (mapa.getCivilizacion().getCantidades()[3] * Casa.CAPACIDAD <= mapa.getCivilizacion().getPersonajes().size()) {   //Comprobamos si la suma de paisanos y soldados es mayor igual que la capacidad de almacenamiento
            throw new ExcepcionCrear("No hay sitio para mas personajes, se necesitan mas casas!");
        }
        if (mapa.getCivilizacion().getEdificios().containsKey(edificio)) {
            ef = mapa.getCivilizacion().getEdificios().get(edificio);
        } else {
            throw new ExcepcionCrear("El edificio no existe!");
        }
        if (gasto > comida) { //Si no hay comida para crear un paisano
            throw new ExcepcionCrear("No hay suficiente comida!");
        }

        switch (tipopersonaje) {
            case "paisano":
                if (ef instanceof Ciudadela) {
                    p = ((Ciudadela) ef).crear(tipopersonaje);
                } else {
                    throw new ExcepcionCrear("Ese edificio no crea Paisanos!");
                }
                break;

            case "caballero":   //El orden importa(Caballeros mas caros)
                gasto += 10;
            case "legionario":
                gasto += 10;
            case "arquero":
                gasto += 10;
                
                if (gasto > comida) { //Si no hay comida para crear un soldado
                    throw new ExcepcionCrear("No hay suficiente comida!");
                }
                
                if (ef instanceof Cuartel) {
                    p = ((Cuartel) ef).crear(tipopersonaje);
                } else {
                    throw new ExcepcionCrear("Ese edificio no crea Soldados!");
                }
                break;

            default:
                throw new ExcepcionCrear("El tipo de personaje especificado no existe!");
        }

        Posicion nueva = p.getPosicion();   //Busqueda de posicion para crear
        nueva.moverX(-1);
        if (!(mapa.checkCoords(nueva) && mapa.checkBuilding(nueva))) {
            nueva.moverX(2);
        }
        if (!(mapa.checkCoords(nueva) && mapa.checkBuilding(nueva))) {
            nueva.moverX(-1);
            nueva.moverY(1);
        }
        if (!(mapa.checkCoords(nueva) && mapa.checkBuilding(nueva))) {
            nueva.moverY(-2);
        }
        if (!(mapa.checkCoords(nueva) && mapa.checkBuilding(nueva))) {
            throw new ExcepcionCrear("No hay posiciones libres en el entorno del edificio!");
        }

        cell = mapa.getCelda(nueva);
        p.setPosicion(nueva);
        cell.addPersonaje(p);
        mapa.getCivilizacion().getPersonajes().put(p.getNombre(), p);
        mapa.getCivilizacion().setComida(mapa.getCivilizacion().getComida() - gasto);

        SHELL.imprimir(
                "Se ha creado el personaje " + p.getNombre() + " en la posicion " + p.getPosicion());
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
