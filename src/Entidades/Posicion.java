/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author iagolobla
 */
public class Posicion {
    private int x;
    private int y;

    public Posicion() {
        x = 0;
        y = 0;
    }

    public Posicion(int valorX, int valorY) {
        x = valorX;
        y = valorY;
    } 
    public Posicion(Posicion p){
        x=p.getX();
        y=p.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int valor) {
        x = valor;
    }

    public void setY(int valor) {
        y = valor;
    }

    public void moverX(int valor) {
        x += valor;
    }

    public void moverY(int valor) {
        y += valor;
    }

    @Override
    public String toString() {
        return "Posicion("+x+","+y+")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Posicion other = (Posicion) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }
}
