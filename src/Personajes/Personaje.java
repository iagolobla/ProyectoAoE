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
import Excepciones.ExcepcionRecurso;
import Excepciones.ExcepcionReparar;
import Excepciones.ExcepcionSintaxis;
import Juego.Celda;
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
    private String Descripcion;

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

    public int capacidadMovimiento(){
        return 1;
    }

    public abstract Edificio construir(String tipo_edificio) throws ExcepcionConstruir;

    public abstract void reparar(Edificio edificio) throws ExcepcionReparar;

    public abstract void recolectar(Contenedor contenedor) throws ExcepcionRecolectar, ExcepcionRecurso;

    public abstract void almacenar(Ciudadela ciudadela) throws ExcepcionAlmacenar;

    public void defender(Edificio edificio) throws ExcepcionDefender {
        int contador = 0;
        if (!(edificio.getCivilizacion().getNombre().equals(this.getCivilizacion().getNombre()))) {
            throw new ExcepcionDefender("Este edificio no es de tu civilizacion!");
        }

        if (this instanceof Grupo) {
            Grupo G = (Grupo) this;
            contador = G.getNPersonajes();
            for (Personaje person : G.getPersonajes()) {
                person.restaurarVida(person);
                
            }
        } else {
            this.restaurarVida(this);
            contador = 1;
        }
        if ((edificio.getCapPersonajes() - edificio.getNPersonajes()) - contador < 0) {
            throw new ExcepcionDefender("No cogen mas personajes aqui dentro");
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

    public abstract double danhoAtaque(Edificio edificio);

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

    public abstract double danhoAtaque(Personaje personaje);

    public void atacar(ArrayList<Personaje> personajes) throws ExcepcionAtacar {
        if (personajes.get(0).getCivilizacion().getNombre().equals(civilizacion.getNombre())) {
            throw new ExcepcionAtacar("El edificio al que intentas atacar es de tu civilizacion");
        }
        Personaje p = personajes.get(0);
        int atack = (int) this.danhoAtaque(p);
        if (atack <= 0) {
            atack = 1;
        }
        p.recibirDaño(atack);
        SHELL.imprimir("El " + p.getNombre() + " de la civilizacion " + p.getCivilizacion().getNombre() + " ha recibido " + atack + " ptos de daño");

    }

    public boolean recibirDaño(int daño) {   //Si muere devuelve true
        if (this instanceof Grupo) {
            salud -= daño;
            if (salud <= 0) {
                salud = 0;
                return true;
            }
            Grupo G = (Grupo) this;
            for (Personaje p : G.getPersonajes()) {
                p.setSalud(p.getSalud() - daño / G.getNPersonajes());
            }
        } else {
            salud -= daño;
            if (salud <= 0) {
                salud = 0;
                return true;
            }
        }
        return false;
    }

    public void matarPersonaje(Mapa mapa) {
        Celda celda = mapa.getCelda(posicion);
        if (this.getSalud() <= 0) {   //Si muere
            SHELL.imprimir("El personaje " + this.getNombre() + " de la civilizacion " + celda.getPersonaje().getCivilizacion().getNombre() + " ha sufrido una horrible y dolorosa muerte!");
            celda.getPersonajes().remove(this);
            mapa.getCivilizaciones().get(this.getCivilizacion().getNombre()).getPersonajes().remove(this.getNombre());
            SHELL.imprimir(mapa.print());
        }

    }

    /*
    public abstract void recolectar(Contenedor contenedor);
    
    
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
            //System.out.println("Salud introducida debe ser mayor que 0!");
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

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

}
