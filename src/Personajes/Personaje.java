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
import Excepciones.ExcepcionAlmacenar;
import Excepciones.ExcepcionAtacar;
import Excepciones.ExcepcionConstruir;
import Excepciones.ExcepcionCrear;
import Excepciones.ExcepcionDefender;
import Excepciones.ExcepcionRecolectar;
import Excepciones.ExcepcionReparar;
import Excepciones.ExcepcionSintaxis;
import static Juego.Principal.SHELL;
import java.util.ArrayList;

/**
 *
 * @author iagolobla
 */
public abstract class Personaje {

    private Grupo G;
    private Civilizacion civilizacion;
    private int armadura;
    private int salud;
    private int ataque;
    private String Nombre;  //Aqui se necesita poner un Nombre al personaje para usar eso como clave en el hashmap de personajes en el mapa
    private Posicion posicion;
    private boolean grupo;

    public Personaje(String Nombre, Posicion posicion, Civilizacion civilizacion) {
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

    public Posicion mover(String pto_cardinal) throws ExcepcionSintaxis {
        Posicion p = new Posicion(posicion);
        switch (pto_cardinal) {
            case "s":
                p.moverX(1);
                break;
            case "n":
                p.moverX(-1);
                break;
            case "e":
                p.moverY(1);
                break;
            case "o":
                p.moverY(-1);
                break;
            default:
                throw new ExcepcionSintaxis("Direccion pasada erronea!");

        }
        return p;
    }

    public abstract int capacidadMovimiento();

    public abstract Edificio construir(String tipo_edificio) throws ExcepcionConstruir;

    public abstract void reparar(Edificio edificio) throws ExcepcionReparar;

    public abstract void recolectar(Contenedor contenedor) throws ExcepcionRecolectar;

    public abstract void almacenar(Ciudadela ciudadela) throws ExcepcionAlmacenar;

    public void defender(Edificio edificio) throws ExcepcionDefender {
        int contador = 0;
        if (!(edificio.getCivilizacion().getNombre().equals(this.getCivilizacion().getNombre()))) {
            throw new ExcepcionDefender("Este edificio nos es de tu civilizacion!");
        }
        if ((edificio.getCapPersonajes() - edificio.getNPersonajes()) <= 0) {
            throw new ExcepcionDefender("No cogen mas personajes aqui dentro");
        }

        if (this instanceof Grupo) {
            Grupo G = (Grupo) this;
            for (Personaje person : G.getPersonajes()) {
                person.restaurarVida(person);
                contador++;
            }
        } else {
            this.restaurarVida(this);
            contador++;
        }

        edificio.setAtaque(edificio.getAtaque() + ataque);
        edificio.setDefensa(edificio.getDefensa() + armadura);
        edificio.getPersonajes().put(Nombre, this);
        edificio.setNPersonajes(edificio.getNPersonajes() + contador);
    }

    public void restaurarVida(Personaje personaje) {
        if (personaje instanceof Paisano) {
            personaje.setSalud(Paisano.SALUD);
        } else if (personaje instanceof Arquero) {
            personaje.setSalud(Arquero.SALUD);
        } else if (personaje instanceof Caballero) {
            personaje.setSalud(Caballero.SALUD);
        } else if (personaje instanceof Legionario) {
            personaje.setSalud(Legionario.SALUD);
        }
    }

    public double danhoAtaque(Edificio edificio) {
        return ataque - edificio.getDefensa();
    }

    public void atacar(Edificio edificio) throws ExcepcionAtacar {
        if (edificio.getCivilizacion().getNombre().equals(civilizacion.getNombre())) {
            throw new ExcepcionAtacar("El edificio al que intentas atacar es de tu civilizacion");
        }
        int atack = (int)this.danhoAtaque(edificio);
        if (atack <= 0) {
            atack = 1;
        }
        edificio.recibirDaño(atack);
        SHELL.imprimir("El "+edificio.getNombre()+" ha recibido "+atack+" ptos de daño");
        
    }

    
 /*
    public abstract void recolectar(Contenedor contenedor);
    
    
    public abstract void atacar(Personaje[] personajes);
    
    
    public abstract double danhoAtaque(Personaje personaje);
    
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
