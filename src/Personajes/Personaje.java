/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;


import Juego.Posicion;
import Juego.Civilizacion;
import Juego.Mapa;
import Recursos.Contenedor;
import Edificios.Ciudadela;
import Edificios.Edificio;
/**
 *
 * @author iagolobla
 */
public abstract class Personaje  {
    
    private Grupo G;
    private Civilizacion civilizacion;
    private int armadura;
    private int salud;
    private int ataque;
    private String Nombre;  //Aqui se necesita poner un Nombre al personaje para usar eso como clave en el hashmap de personajes en el mapa
    private Posicion posicion;
    private boolean grupo;

 
    public Personaje(String Nombre, Posicion posicion, Civilizacion civilizacion){
        if (posicion == null) {
            System.out.println("Posicion pasada a Personaje nula!");
            return;
        }
        this.posicion = new Posicion(posicion);
        if (civilizacion == null) {
            System.out.println("Civilizacion pasada nula");
            return;
        }
        this.civilizacion = civilizacion;
        this.Nombre = Nombre;
        grupo = false;
        G = null;
    }
    
    @Override
    public String toString() {
        String impresion = "";
        impresion += "Armadura: " + armadura + "\n";
        impresion += "Salud: " + salud + "\n";
        impresion += "Ataque: " + ataque + "\n";
        impresion += "Nombre: " + Nombre + "\n";
        if (grupo) {
            impresion += "Pertenece a: " + G.getNombre() + "\n";
        }
        impresion += "Posicion: " + posicion + "\n";

        return impresion;
    }
    
    public Posicion mover(String pto_cardinal){
        Posicion p=new Posicion(posicion);
        switch (pto_cardinal) {
            case "s":
                p.moverX(this.capacidadMovimiento());
                break;
            case "n":
                p.moverX((-1)*this.capacidadMovimiento());
                break;
            case "e":
                p.moverY(this.capacidadMovimiento());
                break;
            case "o":
                p.moverY((-1)*this.capacidadMovimiento());
                break;
            default:
                System.out.println("Error, direccion no valida!");

        }
        return p;
    }
    
    public abstract int capacidadMovimiento();
    /*
    public abstract void recolectar(Contenedor contenedor);
    
    public abstract void almacenar(Ciudadela ciudadela);
    
    public abstract void reparar(Edificio edificio);
    
    public abstract Edificio construir(String tipo_edificio);
    
    public void defender(Edificio edificio){
        
    }
    
    public abstract void atacar(Personaje[] personajes);
    
    public abstract void atacar(Edificio edificio);
    
    public abstract double danhoAtaque(Personaje personaje);
    
    public abstract double danhoAtaque(Edificio edificio);
    */
    public boolean isGrupo() {
        return grupo;
    }

    public void setGrupo(boolean grupo) {
        this.grupo = grupo;
    }
    
    public void setArmadura(int armadura) {
        if (armadura >= 0) {
            this.armadura = armadura;
        } else {
            System.out.println("Armadura introducida debe ser mayor que 0!");
        }
    }

    public void setAtaque(int ataque) {
        if (ataque >= 0) {
            this.ataque = ataque;
        } else {
            System.out.println("Ataque introducido debe ser mayor que 0!");
        }
    }
    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public int getArmadura() {
        return armadura;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        if (salud >= 0) {
            this.salud = salud;
        } else {
            System.out.println("Salud introducida debe ser mayor que 0!");
        }
    }

    public int getAtaque() {
        return ataque;
    }
    
    public Posicion getPosicion() {
        return new Posicion(posicion);
    }

    public void setPosicion(Posicion p) {
        if (p.getX() >= 0 && p.getX() < Mapa.MAPAY && p.getY() >= 0 && p.getY() < Mapa.MAPAX) {
            posicion = new Posicion(p);
        } else {
            System.out.println("Posicion introducida fuera de los limites del mapa!");
        }
    }

    public Grupo getG() {
        return G;
    }

    public void setG(Grupo G) {
        this.G = G;
    }

    public Civilizacion getCivilizacion() {
        return civilizacion;
    }

    public void setCivilizacion(Civilizacion civilizacion) {
        if (civilizacion == null) {
            System.out.println("Civilizacion pasada nula");
            return;
        }
        this.civilizacion = civilizacion;
    }
    
}
