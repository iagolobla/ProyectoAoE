/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author javier
 */
import java.util.ArrayList;
import Mapa.Mapa;
import Mapa.Celda;
import Mapa.Civilizacion;
import java.util.Collection;

public class Grupo {

    ArrayList<Personaje> personajes;
    private String nombreCivilizacion;
    private int armadura;
    private int salud;
    private int ataque;
    private String Nombre;  //Aqui se necesita poner un nombre al personaje para usar eso como clave en el hashmap de personajes en el mapa
    private Posicion posicion;

    public Grupo(ArrayList<Personaje> person, String Nombre, Posicion pos, String civilizacion) {
        personajes = new ArrayList<Personaje>();
        nombreCivilizacion = civilizacion;
        this.Nombre = Nombre;
        int soldado = 0;
        posicion = new Posicion(pos);
        for (Personaje p : person) {
            p.setGrupo(true);
            p.setNombreGrupo(Nombre);
            personajes.add(p);
            armadura += p.getArmadura();
            salud += p.getSalud();
            ataque += p.getAtaque();

        }
    }

    @Override
    public String toString() {
        String imprimir;
        int salud = 0;

        imprimir = "\n";
        imprimir += "La lista de personajes del grupo es:\n";
        for (Personaje P : personajes) {
            salud += P.getSalud();
            imprimir += "\t" + P.getNombre() + "\n";
        }
        imprimir += "Salud total del Grupo: " + salud + "\n";
        imprimir += "Armadura del Grupo: " + armadura + "\n";
        imprimir += "Ataque del Grupo: " + ataque + "\n";
        return imprimir;
    }

    public Posicion moverGrupo(Mapa mapa, String direccion) {
        for (Personaje person : personajes) {
            person.moverPj(mapa, direccion);

        }
        Posicion pos = new Posicion(posicion);
        switch (direccion) {
            case "s":
                pos.moverX(1);
                break;
            case "n":
                pos.moverX(-1);
                break;
            case "e":
                pos.moverY(1);
                break;
            case "o":
                pos.moverY(-1);
                break;
            default:
                System.out.println("Error, direccion no valida!");
                return posicion;
        }
        if (mapa.checkCoords(pos) && mapa.checkBuilding(pos)) {
            if (mapa.getCelda(pos).getPersonajes().get(0).getNombreCivilizacion().equals(nombreCivilizacion)) {
                mapa.getCelda(pos).setGrupo(this);
                mapa.getCelda(posicion).getGrupos().remove(this);
                /*for(Personaje p:personajes){//GIT DEBIO DE QUITAR ESTO NO SE POR QUE(creo que no hace falta(cosas mias));
                    mapa.getCelda(posicion).getPersonajes().remove(p);
                }*/
                posicion = new Posicion(pos);
                return posicion;
            } else {
                return posicion;
            }
        } else {
            return posicion;
        }
    }

    public boolean defender(Mapa mapa, String direccion) {   //Si funciona devuelve true
        if (mapa == null) {
            System.out.println("Mapa pasado nulo!");
            return false;
        }
        Posicion pos = new Posicion(posicion);
        Celda cell = mapa.getCelda(pos);
        switch (direccion) {
            case "n":
                pos.moverX(-1);
                break;
            case "s":
                pos.moverX(1);
                break;
            case "e":
                pos.moverY(1);
                break;
            case "o":
                pos.moverY(-1);
                break;
            default:
                System.out.println("Direccion pasada no valida!");
                return false;
        }
        Celda newCell = mapa.getCelda(pos);
        Edificio ef = newCell.getEdificio();
        if (ef == null) {
            System.out.println("En esta posicion no hay ningun edificio!");
            return false;
        }
        if (!(ef.getNombreCivilizacion().equals(this.getNombreCivilizacion()))) {
            System.out.println("Este no es un edificio aliado!");
            return false;
        }
        if ((ef.getCapPersonajes() - ef.getNPersonajes() - personajes.size()) <= 0) {
            System.out.println("No hay mas sitio para todos aqui dentro!");
            return false;
        }
        //Restaurar la salud de todos los personajes
        for (Personaje P : personajes) {
            if (P.isPaisano()) {
                P.setSalud(Personaje.SALUD_PAISANO);
            }
            if (P.isSoldado()) {
                P.setSalud(Personaje.SALUD_SOLDADO);
            }
            P.setPosicion(pos); //Actualizamos posicion del Pj
            ef.getPersonajes().put(P.getNombre(), P);
            cell.quitarPersonaje(P); //Quitamos al personaje de la celda
        }

        ef.setAtaque(ef.getAtaque() + ataque);
        ef.setDefensa(ef.getDefensa() + armadura);

        ef.setNPersonajes(ef.getNPersonajes() + personajes.size());
        cell.getGrupos().remove(this);
        this.setPosicion(pos);

        mapa.actualizarVisibilidad();
        mapa.imprimir();
        return true;
    }

    public boolean atacar(Mapa mapa, String direccion) {
        if (mapa == null) {
            System.out.println("Mapa pasado nulo!");
            return false;
        }
        Posicion posp = new Posicion(this.posicion);
        switch (direccion) {   //Comprobacion de la direccion
            case "n":
                posp.moverX(-1);
                break;
            case "s":
                posp.moverX(1);
                break;
            case "e":
                posp.moverY(1);
                break;
            case "o":
                posp.moverY(-1);
                break;
            default:
                System.out.println("Direccion no valida!");
                return false;
        }

        Celda cell = mapa.getCelda(posp);    //Extraemos la celda que se quiere atacar
        if (cell.isLibre() || cell.isRecurso()) { //Si no hay nada en la celda o hay un recurso
            return false;   //Se termina
        }

        if (cell.isPersonaje()) {    //Si en la celda hay un personaje individual
            ArrayList<Personaje> pers = new ArrayList<Personaje>();
            for (Personaje p : cell.getPersonajes()) {
                if (!p.isGrupo()) {
                    pers.add(p);
                }
            }
            if (pers.get(0).getNombreCivilizacion().equals(nombreCivilizacion)) {
                return false;
            }

            Personaje P = pers.get(0);
            int atack = ataque - P.getArmadura();
            if (atack <= 0) {
                atack = 1;
            }
            if (P.recibirDaño(atack)) {   //Si muere
                System.out.println("El personaje " + P.getNombre() + " de la civilizacion " + P.getNombreCivilizacion() + " ha sufrido una horrible y dolorosa muerte!");

                cell.getPersonajes().remove(P);
                mapa.getCivilizaciones().get(P.getNombreCivilizacion()).getPersonajes().remove(P.getNombre());
                mapa.imprimir();
            } else {
                System.out.println("El personaje " + P.getNombre() + " de la civilizacion " + P.getNombreCivilizacion() + " ha recibido " + atack + " puntos de daño!");
            }
            return true;
        } else if (cell.isEdificio()) { //Si en la celda hay un edificio
            Edificio ef = cell.getEdificio();

            if (ef.getNombreCivilizacion().equals(nombreCivilizacion)) {
                return false;
            }

            int atack = ataque - ef.getDefensa();
            if (atack <= 0) {
                atack = 1;
            }

            if (ef.recibirDaño(atack)) {  //Le pega
                System.out.println("El edificio " + ef.getNombre() + " de la civilizacion " + ef.getNombreCivilizacion() + " ha sido fatalmente destruido!");
                Collection<Personaje> pers = ef.getPersonajes().values();
                for (Personaje P : pers) {
                    ef.getPersonajes().remove(P.getNombre());
                    cell.getPersonajes().remove(P);
                    //mapa.getCivilizaciones().remove(P.getNombre());
                    mapa.getCivilizaciones().get(P.getNombreCivilizacion()).getPersonajes().remove(P.getNombre());
                }

                mapa.getCivilizaciones().get(ef.getNombreCivilizacion()).getEdificios().remove(ef.getNombre());
                cell.setEdificio(null);
                cell.setTipo("Pradera");
                if (!(mapa.getCivilizaciones().get(ef.getNombreCivilizacion()).civilizacionViva())) {
                    System.out.println("LA CIVILIZACION " + ef.getNombreCivilizacion() + " HA MUERTO");
                    mapa.getCivilizaciones().remove(ef.getNombreCivilizacion());
                    mapa.borrarCivilizacion(ef.getNombreCivilizacion());
                }
                mapa.imprimir();

            } else {
                System.out.println("El edificio " + ef.getNombre() + " ha recibido " + atack + " puntos de daño!");
            }
            return true;
        } else if (cell.isGrupo()) {     //Si en la celda hay un grupo
            Grupo G = cell.getGrupos().get(0);

            if (G.getNombreCivilizacion().equals(nombreCivilizacion)) {
                return false;
            }

            int tam = G.getPersonajes().size(); //Numero de personajes a repartir el daño
            int atack = ataque - G.getArmadura();
            int daño = atack / tam;

            if (daño <= 0) {
                daño = 1;   //Nos aseguramos de que siempre se hace daño
            }
            ArrayList<Personaje> aux = new ArrayList<Personaje>(G.getPersonajes());

            for (Personaje P : aux) {   //Para cada personaje del grupo
                if (P.recibirDaño(daño)) {    //Si muere
                    System.out.println("El personaje " + P.getNombre() + " de la civilizacion" + P.getNombreCivilizacion() + " ha sufrido una horrible y dolorosa muerte!");

                    G.getPersonajes().remove(P);
                    G.setArmadura(G.getArmadura() - P.getArmadura()); //Quitamos la armadura del personaje
                    cell.getPersonajes().remove(P);
                    mapa.getCivilizaciones().get(P.getNombreCivilizacion()).getPersonajes().remove(P.getNombre());

                } else {
                    System.out.println("Al personaje " + P.getNombre() + " de la civilizacion" + P.getNombreCivilizacion() + " se le han hecho " + daño + " puntos de daño(Y duele...)");
                }
            }
            if (G.getPersonajes().size() == 0) {
                cell.getGrupos().remove(G);
                mapa.getCivilizaciones().get(G.getNombreCivilizacion()).getGrupos().remove(G.getNombre());
                mapa.imprimir();
            }
            return true;
        }
        return false;
    }

    public void desligar(Personaje person) {
        personajes.remove(person);
        person.setGrupo(false);
        person.setNombreGrupo(null);
    }

    public void desagrupar(Mapa mapa) {
        mapa.getCivilizacion().getGrupos().remove(Nombre);
        for (Personaje p : personajes) {
            p.setGrupo(false);
            p.setNombreGrupo(null);
        }
        mapa.getCelda(posicion).getGrupos().remove(this);
    }

    public void recolectar(Recurso recurso, Mapa mapa) {
        if (recurso == null) {    //Comprobamos si se pasa bien el argumento
            System.out.println("Argumento Recurso pasado nulo!");
            return;
        }
        for (Personaje person : personajes) {
            if (person.isSoldado()) {
                System.out.println("El grupo no puede recolectar ya que no todos sus integrantes son paisanos");
                return;
            }
        }
        for (Personaje p : personajes) {
            p.recolectar(recurso, mapa);
        }
    }

    public void almacenarRecurso(Edificio ciudadela, Civilizacion C) {
        if (ciudadela == null) {  //Si el parametro es nulo
            System.out.println("Ciudadela pasada nula!");
            return;
        }
        for (Personaje person : personajes) {
            if (person.isSoldado()) {
                System.out.println("El grupo no puede almacenar ya que no todos sus integrantes son paisanos");
                return;
            }
        }
        for (Personaje p : personajes) {
            p.almacenarRecurso(ciudadela, C);
        }
    }

    public void reparar(Mapa mapa, String direccion) {
        for (Personaje person : personajes) {
            if (person.isSoldado()) {
                System.out.println("El grupo no puede almacenar ya que no todos sus integrantes son paisanos");
                return;
            }
        }
        personajes.get(0).reparar(mapa, direccion);
    }

    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
    }

    public String getNombreCivilizacion() {
        return nombreCivilizacion;
    }

    public void setNombreCivilizacion(String nombreCivilizacion) {
        this.nombreCivilizacion = nombreCivilizacion;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

}
