/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import Excepciones.ExcepcionRecolectar;
import Excepciones.ExcepcionRecurso;
import Juego.Celda;
import Juego.Posicion;
import Juego.Mapa;

/**
 *
 * @author iagolobla
 */
public abstract class Contenedor {

    Recurso recurso;
    private String Nombre;
    private Posicion posicion;

    public Contenedor(String Nombre, Posicion posicion, Recurso R) {
        if (posicion == null) {
            System.out.println("Posicion pasada a Recurso nula!");
            return;
        }
        this.Nombre = Nombre;
        this.posicion = new Posicion(posicion);
        recurso = R;
    }

    @Override
    public String toString() {
        String impresion = "";
        impresion += "Tipo: " + this.getClass().getSimpleName()+"\n";
        impresion += "Cantidad: " + recurso.getCantidad() + "\n";

        return impresion;
    }

    public Recurso procesar() throws ExcepcionRecurso{
        int cantidad = this.getRecurso().getCantidad();
        if(this instanceof Cantera){    //Por cada 20% que falte a la cantera se rebaja un 10%
            Cantera cantera = new Cantera((Cantera) this);
            Recurso piedra = new Piedra((Piedra)cantera.getRecurso());
            // Calcula el numero de "20%" que le faltan para quitarle el correspondiente numero de "10%"
            int factor = (cantera.getCantInit()-cantidad)/cantera.getCant20();
            // Resta a la cantidad actual el 10% de la cantidad inicial * factor
            int Cquitar = cantidad - ((cantera.getCant20()/2) * factor);
            
            if(Cquitar < 1){
                piedra.setCantidad(1);
            } else {
                piedra.setCantidad(Cquitar);
            }
            return piedra;
        } else {
            if(this instanceof Bosque){
                return new Madera((Madera)recurso);
            } else {
                return new Comida((Comida)recurso);
            }
            
        }
    }

    public boolean esTransitable() {
        if (this instanceof Pradera) {
            return true;
        }
        return false;
    }
    
    public boolean destruir(Celda cell) throws ExcepcionRecolectar{
        if(!(this instanceof Pradera)){
            if(this.getRecurso().getCantidad() > 0){
                throw new ExcepcionRecolectar("No se puede eliminar un recurso no acabado!");
            }
            cell.setContenedor(new Pradera(cell.getPosicion()));
            return true;
        }
        return false;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Posicion getPosicion() {
        return new Posicion(posicion);
    }

    public void setPosicion(Posicion p) {
        if (p.getX() >= 0 && p.getX() < Mapa.MAPAY && p.getY() >= 0 && p.getY() < Mapa.MAPAX) {
            posicion = new Posicion(p);
        } else {
            System.out.println("Posicion introducidad fuera de los limites del mapa!");
        }
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

}
