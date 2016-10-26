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
    private float x;
    private float y;

    public Posicion() {
        x = 0;
        y = 0;
    }

    public Posicion(float valorX, float valorY) {
        x = valorX;
        y = valorY;
    } 

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float valor) {
        x = valor;
    }

    public void setY(float valor) {
        y = valor;
    }

    public void moverX(float valor) {
        x += valor;
    }

    public void moverY(float valor) {
        y += valor;
    }

    @Override
    public String toString() {
        return "Posicion{" + "x=" + x + ", y=" + y + "}";
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
