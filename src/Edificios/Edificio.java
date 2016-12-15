/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Edificios;

import Excepciones.ExcepcionAlmacenar;
import Excepciones.ExcepcionAtacar;
import Excepciones.ExcepcionCrear;
import Excepciones.ExcepcionSintaxis;
import Juego.Celda;
import java.util.HashMap;
import Juego.Civilizacion;
import Juego.Posicion;
import Personajes.Personaje;
import Juego.Mapa;
import static Juego.Principal.SHELL;
import Recursos.Recurso;
import java.util.ArrayList;

/**
 *
 * @author iagolobla
 */
public abstract class Edificio {
    
    private HashMap<String, Personaje> Personajes;
    private int salud;
    private Posicion posicion;
    private String Nombre;
    private Civilizacion civilizacion;
    private int capPersonajes;  //Establece cuantos personajes pueden entrar a defender un edificio
    private int NPersonajes;
    private int ataque;
    private int defensa;
    
    public Edificio(Posicion posicion, String Nombre, Civilizacion civilizacion) {
        if (posicion == null) {
            System.out.println("Posicion pasada a Edificio nula!");
            return;
        }
        this.posicion = new Posicion(posicion);
        if (civilizacion == null) {
            System.out.println("Civilizacion pasada nula");
            return;
        }
        this.civilizacion = civilizacion;
        this.Nombre = Nombre;
        Personajes = new HashMap<String, Personaje>();
        NPersonajes = 0;
        ataque = 0;
        defensa = 0;
    }
    
    @Override
    public String toString() {
        String impresion = "";
        if (Personajes.size() > 0) {
            impresion += "Personajes dentro del edificio:\n";
            for (Personaje p : Personajes.values()) {
                impresion += "\t" + p.getNombre() + "\n";
            }
        }
        impresion += "Salud: " + salud + "\n";
        impresion += "Ataque: " + ataque + "\n";
        impresion += "Defensa: " + defensa + "\n";
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
    
    public abstract void almacenar(Recurso recurso) throws ExcepcionAlmacenar;
    
    public abstract Personaje crear(String tipo_personaje) throws ExcepcionCrear;
    
    public double danhoAtaque(Edificio edificio) {
        return ataque - edificio.getDefensa();
    }
    
    public void atacar(Edificio edificio) throws ExcepcionAtacar {
        if (edificio.getCivilizacion().getNombre().equals(civilizacion.getNombre())) {
            throw new ExcepcionAtacar("El edificio al que intentas atacar es de tu civilizacion");
        }
        int atack = (int) this.danhoAtaque(edificio);
        if (atack <= 0) {
            atack = 1;
        }
        edificio.recibirDaño(atack);
        SHELL.imprimir("El " + edificio.getNombre() + " de la civilizacion " + edificio.getCivilizacion().getNombre() + " ha recibido " + atack + " ptos de daño");
        
    }
    
    public double danhoAtaque(Personaje personaje) {
        return ataque - personaje.getArmadura();
    }
    
    public void atacar(ArrayList<Personaje> personajes) throws ExcepcionAtacar {
        if (personajes.get(0).getCivilizacion().getNombre().equals(civilizacion.getNombre())) {
            throw new ExcepcionAtacar("La entidad a la que intentas atacar es de tu civilizacion");
        }
        Personaje p = personajes.get(0);
        int atack = (int) this.danhoAtaque(p);
        if (atack <= 0) {
            atack = 1;
        }
        p.recibirDaño(atack);
        SHELL.imprimir("El " + p.getNombre() + " de la civilizacion " + p.getCivilizacion().getNombre() + " ha recibido " + atack + " ptos de daño");
        
    }
    
    public void atacarTorres(Mapa mapa) throws ExcepcionSintaxis, ExcepcionAtacar {
        Posicion pos1 = this.mover("n");
        Posicion pos2 = this.mover("s");
        Posicion pos3 = this.mover("e");
        Posicion pos4 = this.mover("o");
        
        Celda celda1 = mapa.getCelda(pos1);
        Celda celda2 = mapa.getCelda(pos2);
        Celda celda3 = mapa.getCelda(pos3);
        Celda celda4 = mapa.getCelda(pos4);
        //Atacar a los edificios de su rango
        if (celda1.getEdificio() != null) {
            if (!(celda1.getEdificio().getCivilizacion().equals(this.getCivilizacion()))) {
                this.atacar(celda1.getEdificio());
                if (celda1.getEdificio().getSalud() <= 0) {
                    SHELL.imprimir("El edificio " + celda1.getEdificio().getNombre() + " ha sido fatalmente destruido!");
                    ArrayList<Personaje> pers = new ArrayList<Personaje>();
                    for (Personaje P : celda1.getEdificio().getPersonajes().values()) {
                        pers.add(P);
                    }
                    for (Personaje P : pers) {
                        celda1.getEdificio().getPersonajes().remove(P.getNombre());
                        celda1.getPersonajes().remove(P);
                        //mapa.getCivilizaciones().remove(P.getNombre());
                        mapa.getCivilizaciones().get(P.getCivilizacion().getNombre()).getPersonajes().remove(P.getNombre());
                    }
                    
                    mapa.getCivilizaciones().get(celda1.getEdificio().getCivilizacion().getNombre()).getEdificios().remove(celda1.getEdificio().getNombre());
                    celda1.setEdificio(null);
                    if (!(mapa.getCivilizaciones().get(celda1.getEdificio().getCivilizacion().getNombre()).civilizacionViva())) {
                        System.out.println("LA CIVILIZACION " + celda1.getEdificio().getCivilizacion().getNombre() + " HA MUERTO");
                        mapa.getCivilizaciones().remove(celda1.getEdificio().getCivilizacion().getNombre());
                        mapa.borrarCivilizacion(celda1.getEdificio().getCivilizacion().getNombre());
                    }
                }
            }
        }
        if (celda2.getEdificio() != null) {
            if (!(celda2.getEdificio().getCivilizacion().equals(this.getCivilizacion()))) {
                this.atacar(celda2.getEdificio());
            }
        }
        if (celda3.getEdificio() != null) {
            if (!(celda3.getEdificio().getCivilizacion().equals(this.getCivilizacion()))) {
                this.atacar(celda3.getEdificio());
            }
        }
        if (celda4.getEdificio() != null) {
            if (!(celda4.getEdificio().getCivilizacion().equals(this.getCivilizacion()))) {
                this.atacar(celda4.getEdificio());
            }
        }
        //Ataca a los personajes de su rango
        if (celda1.getPersonaje() != null) {
            if (!(celda1.getPersonaje().getCivilizacion().equals(this.getCivilizacion()))) {
                this.atacar(celda1.getPersonajes());
                if (celda1.getPersonaje().getSalud() <= 0) {   //Si muere
                    SHELL.imprimir("El personaje " + celda1.getPersonaje().getNombre() + " de la civilizacion " + celda1.getPersonaje().getCivilizacion().getNombre() + " ha sufrido una horrible y dolorosa muerte!");
                    Personaje p = celda1.getPersonaje();
                    celda1.getPersonajes().remove(p);
                    mapa.getCivilizaciones().get(p.getCivilizacion().getNombre()).getPersonajes().remove(p.getNombre());
                    SHELL.imprimir(mapa.print());
                }
            }
        }
        if (celda2.getPersonaje() != null) {
            if (!(celda2.getPersonaje().getCivilizacion().equals(this.getCivilizacion()))) {
                this.atacar(celda2.getPersonajes());
                if (celda2.getPersonaje().getSalud() <= 0) {   //Si muere
                    SHELL.imprimir("El personaje " + celda2.getPersonaje().getNombre() + " de la civilizacion " + celda2.getPersonaje().getCivilizacion().getNombre() + " ha sufrido una horrible y dolorosa muerte!");
                    Personaje p = celda2.getPersonaje();
                    celda2.getPersonajes().remove(p);
                    mapa.getCivilizaciones().get(p.getCivilizacion().getNombre()).getPersonajes().remove(p.getNombre());
                    SHELL.imprimir(mapa.print());
                }
            }
        }
        if (celda3.getPersonaje() != null) {
            if (!(celda3.getPersonaje().getCivilizacion().equals(this.getCivilizacion()))) {
                this.atacar(celda3.getPersonajes());
                if (celda3.getPersonaje().getSalud() <= 0) {   //Si muere
                    SHELL.imprimir("El personaje " + celda3.getPersonaje().getNombre() + " de la civilizacion " + celda3.getPersonaje().getCivilizacion().getNombre() + " ha sufrido una horrible y dolorosa muerte!");
                    Personaje p = celda3.getPersonaje();
                    celda3.getPersonajes().remove(p);
                    mapa.getCivilizaciones().get(p.getCivilizacion().getNombre()).getPersonajes().remove(p.getNombre());
                    SHELL.imprimir(mapa.print());
                }
            }
        }
        if (celda4.getPersonaje() != null) {
            if (!(celda4.getPersonaje().getCivilizacion().equals(this.getCivilizacion()))) {
                this.atacar(celda4.getPersonajes());
                if (celda4.getPersonaje().getSalud() <= 0) {   //Si muere
                    SHELL.imprimir("El personaje " + celda4.getPersonaje().getNombre() + " de la civilizacion " + celda4.getPersonaje().getCivilizacion().getNombre() + " ha sufrido una horrible y dolorosa muerte!");
                    Personaje p = celda4.getPersonaje();
                    celda4.getPersonajes().remove(p);
                    mapa.getCivilizaciones().get(p.getCivilizacion().getNombre()).getPersonajes().remove(p.getNombre());
                    SHELL.imprimir(mapa.print());
                }
            }
        }
        
    }
    
    public boolean recibirDaño(int daño) {   //Si muere devuelve true
        salud -= daño;
        if (salud <= 0) {
            salud = 0;
            return true;
        }
        return false;
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
    
    public int getCapPersonajes() {
        
        return capPersonajes;
    }
    
    public void setCapPersonajes(int cap) {
        if (cap >= 0);
        capPersonajes = cap;
    }
    
    public int getNPersonajes() {
        return NPersonajes;
    }
    
    public void setNPersonajes(int NPersonajes) {
        if (NPersonajes >= 0) {
            this.NPersonajes = NPersonajes;
        }
    }
    
    public HashMap<String, Personaje> getPersonajes() {
        return Personajes;
    }
    
    public void setPersonajes(HashMap<String, Personaje> Personajes) {
        this.Personajes = Personajes;
    }
    
    public Civilizacion getCivilizacion() {
        return civilizacion;
    }
    
    public void setCivilizacion(Civilizacion civilizacion) {
        this.civilizacion = civilizacion;
    }
    
    public int getAtaque() {
        return ataque;
    }
    
    public void setAtaque(int ataque) {
        if (ataque >= 0) {
            this.ataque = ataque;
        }
    }
    
    public int getDefensa() {
        return defensa;
    }
    
    public void setDefensa(int defensa) {
        if (defensa >= 0) {
            this.defensa = defensa;
        }
    }
    
    public String getNombre() {
        return new String(Nombre);
    }
    
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public Posicion getPosicion() {
        return new Posicion(posicion);
    }
    
    public void setPosicion(Posicion p) {
        if (p != null) {
            if (p.getX() >= 0 && p.getX() < Mapa.MAPAY && p.getY() >= 0 && p.getY() < Mapa.MAPAX) {
                posicion = new Posicion(p);
            }
        }
    }
}
