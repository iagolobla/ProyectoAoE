/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;
import Mapa.Celda;
import Mapa.Mapa;
import Mapa.Civilizacion;

/**
 *
 * @author iagolobla
 */
public class Edificio {

    public static final int SALUDCIUDADELA = 200;
    public static final int SALUDTORRE = 400;
    public static final int SALUDCASA = 50;
    public static final int SALUDCUARTEL = 150;
    public static final int CAPACIDADCUARTEL = 15;
    public static final int CAPACIDADCASA = 10;

    private HashMap<String, Personaje> Personajes;
    private String tipo;
    private int salud;
    private Posicion posicion;
    private String nombre;
    private String nombreCivilizacion;
    private int capPersonajes;  //Establece cuantos personajes pueden entrar a defender un edificio
    private int NPersonajes;
    private int ataque;
    private int defensa;

    public Edificio(String tipe, Posicion posicion, String Nombre) {
        //Igualar posicion y posicion evitando aliasing
        if (posicion == null) {
            System.out.println("Posicion pasada a Edificio nula!");
            return;
        }

        Personajes = new HashMap<String, Personaje>();
        this.posicion = new Posicion(posicion);
        NPersonajes = 0;
        ataque = 0;
        defensa = 0;
        switch (tipe) {
            case ("ciudadela"):
                tipo = tipe;
                salud = SALUDCIUDADELA;//solo es para hacer la prueba
                nombre = Nombre;
                capPersonajes = 10;
                break;
            case ("cuartel"):
                tipo = tipe;
                salud = SALUDCUARTEL;
                nombre = Nombre;
                capPersonajes = 15;
                break;
            case ("casa"):
                tipo = tipe;
                salud = SALUDCASA;
                nombre = Nombre;
                capPersonajes = 5;
                break;
            case "torre":
                tipo = tipe;
                salud = SALUDTORRE;
                nombre = Nombre;
                ataque = 100;
                defensa = 100;
                capPersonajes = 2;
                break;
            default:
                System.out.println("Error, tipo de edificio incorrecto");
        }
    }

    public Edificio(String tipe, Posicion posicion, String Nombre, String civilizacion) {
        //Igualar posicion y posicion evitando aliasing
        if (posicion == null) {
            System.out.println("Posicion pasada a Edificio nula!");
            return;
        }

        Personajes = new HashMap<String, Personaje>();
        nombreCivilizacion = civilizacion;
        NPersonajes = 0;
        this.posicion = new Posicion(posicion);
        switch (tipe) {
            case ("ciudadela"):
                tipo = tipe;
                salud = SALUDCIUDADELA;//solo es para hacer la prueba
                nombre = Nombre;
                capPersonajes = 10;
                break;
            case ("cuartel"):
                tipo = tipe;
                salud = SALUDCUARTEL;
                nombre = Nombre;
                capPersonajes = 15;
                break;
            case ("casa"):
                tipo = tipe;
                salud = SALUDCASA;
                nombre = Nombre;
                capPersonajes = 5;
                break;
            case "torre":
                tipo = tipe;
                salud = SALUDTORRE;
                ataque = 100;
                defensa = 100;
                nombre = Nombre;
                capPersonajes = 2;
                break;
            default:
                System.out.println("Error, tipo de edificio incorrecto");
        }
    }

    public void crearPaisano(Mapa mapa) {
        if (mapa == null) {
            System.out.println("Mapa pasado nulo!");
            return;
        }
        if (!this.tipo.equals("ciudadela")) {
            System.out.println("Este edificio no puede crear Paisanos");
        }
        if (mapa.getCivilizacion().getCantidades()[3] * CAPACIDADCASA <= mapa.getCivilizacion().getCantidades()[0] + mapa.getCivilizacion().getCantidades()[1]) {   //Comprobamos si la suma de paisanos y soldados es mayor igual que la capacidad de almacenamiento
            System.out.println("No hay sitio para mas Paisanos, se necesitan mas casas!");
            return;
        }
        Posicion pos1 = new Posicion(posicion);
        pos1.moverX(-1);

        if (!(mapa.checkCoords(pos1) && mapa.checkBuilding(pos1))) {  //Comprobamos la primera posicion, si no es valida
            pos1.moverX(2);

        }
        if (!(mapa.checkCoords(pos1) && mapa.checkBuilding(pos1))) {  //Comprobamos la segunda posicion, si no es valida
            pos1.moverX(-1);
            pos1.moverY(1);
        }
        if (!(mapa.checkCoords(pos1) && mapa.checkBuilding(pos1))) {  //Comprobamos la tercera posicion, si no es valida
            pos1.moverY(-2);
        }
        if (!(mapa.checkCoords(pos1) && mapa.checkBuilding(pos1))) {  //Comprobamos la cuarta posicion, si no es valida
            System.out.println("No es posible crear el paisano, todas las posiciones en torno a la Ciudadela estan ocupadas");
            return;
        }

        String Name = "paisano-" + (mapa.getCivilizacion().getCantidades()[0] + 1);
        mapa.getCivilizacion().getCantidades()[0]++;
        Celda newcell = mapa.getCelda(pos1);
        newcell.setPersonajes(new Personaje("paisano", Name, pos1, mapa.getCivilizacion().getNombre()));
        mapa.getMapa().get(pos1.getX()).set(pos1.getY(), newcell); //Metemos la celda en su posicion del mapa

        ArrayList<Personaje> person = mapa.getCelda(new Posicion(pos1)).getPersonajes();
        mapa.getCivilizacion().getPersonajes().put(Name, person.get(person.size() - 1));

        mapa.getCivilizacion().setComida(mapa.getCivilizacion().getComida() - 10);
        System.out.println("El " + Name + " se encuentra en la posicion " + pos1);
        System.out.println("Quedan " + ((mapa.getCivilizacion().getCantidades()[3] * CAPACIDADCASA) - (mapa.getCivilizacion().getCantidades()[0] + mapa.getCivilizacion().getCantidades()[1])) + " espacios de almacenamiento");
        System.out.println("Se han gastado 10 unidades de comida en crear el paisano");
        System.out.println("Quedan los siguientes recursos: ");
        System.out.println("Comida: " + mapa.getCivilizacion().getComida());
        System.out.println("Madera: " + mapa.getCivilizacion().getMadera());
        System.out.println("Piedra: " + mapa.getCivilizacion().getPiedra());
        //Hay que hacer actualizacion de visibilidades ya que hay un nuevo personaje
        mapa.actualizarVisibilidad();
    }

    public void atacarTorre(Mapa mapa) {
        this.atacar(mapa, "n");
        this.atacar(mapa, "s");
        this.atacar(mapa, "e");
        this.atacar(mapa, "o");
    }

    public void crearSoldado(Mapa mapa) {
        if (!this.tipo.equals("cuartel")) {
            System.out.println("Este edificio no puede crear Soldados");
        }
        if (mapa.getCivilizacion().getCantidades()[3] * CAPACIDADCASA <= mapa.getCivilizacion().getCantidades()[0] + mapa.getCivilizacion().getCantidades()[1]) {   //Comprobamos si la suma de paisanos y soldados es mayor igual que la capacidad de almacenamiento
            System.out.println("No hay sitio para mas Soldados, se necesitan mas casas!");
            return;
        }
        Posicion pos1 = new Posicion(posicion);
        pos1.moverX(-1);

        if (!(mapa.checkCoords(pos1) && mapa.checkBuilding(pos1))) {  //Comprobamos la primera posicion, si no es valida
            pos1.moverX(2);
        }
        if (!(mapa.checkCoords(pos1) && mapa.checkBuilding(pos1))) {  //Comprobamos la segunda posicion, si no es valida
            pos1.moverX(-1);
            pos1.moverY(1);
        }
        if (!(mapa.checkCoords(pos1) && mapa.checkBuilding(pos1))) {  //Comprobamos la tercera posicion, si no es valida
            pos1.moverY(-2);
        }
        if (!(mapa.checkCoords(pos1) && mapa.checkBuilding(pos1))) {  //Comprobamos la cuarta posicion, si no es valida
            System.out.println("No es posible crear el soldado, todas las posiciones en torno al Cuartel estan ocupadas");
            return;
        }

        String Name = "soldado-" + (mapa.getCivilizacion().getCantidades()[1] + 1);
        mapa.getCivilizacion().getCantidades()[1]++;
        Celda newcell = mapa.getCelda(pos1);
        newcell.setPersonajes(new Personaje("soldado", Name, pos1, mapa.getCivilizacion().getNombre()));
        mapa.getMapa().get(pos1.getX()).set(pos1.getY(), newcell); //Metemos la celda en su posicion del mapa

        ArrayList<Personaje> person = mapa.getCelda(new Posicion(pos1)).getPersonajes();
        mapa.getCivilizacion().getPersonajes().put(Name, person.get(person.size() - 1));

        mapa.getCivilizacion().setComida(mapa.getCivilizacion().getComida() - 10);
        System.out.println("El " + Name + " se encuentra en la posicion " + pos1);
        System.out.println("Quedan " + ((mapa.getCivilizacion().getCantidades()[3] * CAPACIDADCASA) - (mapa.getCivilizacion().getCantidades()[0] + mapa.getCivilizacion().getCantidades()[1])) + " espacios de almacenamiento");
        System.out.println("Se han gastado 10 unidades de comida en crear el soldado");
        System.out.println("Quedan los siguientes recursos: ");
        System.out.println("Comida: " + mapa.getCivilizacion().getComida());
        System.out.println("Madera: " + mapa.getCivilizacion().getMadera());
        System.out.println("Piedra: " + mapa.getCivilizacion().getPiedra());
        //Hay que hacer actualizacion de visibilidades ya que hay un nuevo personaje
        mapa.actualizarVisibilidad();

    }

    public void imprimirCiudadela(Civilizacion civ) {
        System.out.println(this);
        System.out.println("Recursos: ");
        System.out.println("Piedra: " + civ.getPiedra());
        System.out.println("Madera: " + civ.getMadera());
        System.out.println("Comida: " + civ.getComida());
    }

    public boolean recibirDaño(int daño) {   //Si muere devuelve true
        salud -= daño;
        if (salud <= 0) {
            salud = 0;
            return true;
        }
        return false;
    }

    public boolean atacar(Mapa mapa, String direccion) {
        if (mapa == null) {
            System.out.println("Mapa pasado nulo!");
            return false;
        }
        if (ataque <= 0) {
            System.out.println("El ataque del edificio es 0!");
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
            Personaje P = pers.get(0);
            if (P.getNombreCivilizacion().equals(nombreCivilizacion)) {
                return false;
            }

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

            if (ef.recibirDaño(atack)) {  //Si muere
                System.out.println("El edificio " + ef.getNombre() + " ha sido fatalmente destruido!");
                ArrayList<Personaje> pers = new ArrayList<Personaje>();
                for (Personaje P : ef.getPersonajes().values()) {
                    pers.add(P);
                }

                for (Personaje P : pers) {
                    ef.getPersonajes().remove(P.getNombre());
                    cell.getPersonajes().remove(P);
                    mapa.getCivilizaciones().get(P.getNombreCivilizacion()).getPersonajes().remove(P.getNombre());
                }

                mapa.getCivilizaciones().get(ef.getNombreCivilizacion()).getEdificios().remove(ef.getNombre());
                cell.setEdificio(null);
                cell.setTipo("Pradera");
                if (!(mapa.getCivilizaciones().get(ef.getNombreCivilizacion()).civilizacionViva())) {
                    System.out.println("LA CIVILIZACION DE LOS " + ef.getNombreCivilizacion() + " HA MUERTO");
                    mapa.getCivilizaciones().remove(ef.getNombreCivilizacion());
                    mapa.borrarCivilizacion(ef.getNombreCivilizacion());
                    if (mapa.getCivilizaciones().size() == 1) {
                        System.out.println("LA CIVILIZACION " + mapa.getCivilizacion().getNombre() + " HA GANADO");
                    }
                }
                mapa.imprimir();

            } else {
                System.out.println("El edificio " + ef.getNombre() + " de la civilizacion " + ef.getNombreCivilizacion() + " ha recibido " + atack + " puntos de daño!");
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

    @Override
    public String toString() {
        String impresion = "";
        if (Personajes.size() > 0) {
            impresion += "Personajes dentro del edificio:\n";
            for (Personaje p : Personajes.values()) {
                impresion += "\t" + p.getNombre() + "\n";
            }
        }
        impresion += "Tipo: " + tipo + "\n";
        impresion += "Salud: " + salud + "\n";
        impresion += "Ataque: " + ataque + "\n";
        impresion += "Defensa: " + defensa + "\n";
        impresion += "Posicion: " + posicion + "\n";

        return impresion;
    }

    //GETTERS Y SETTERS
    public String getNombre() {
        return new String(nombre);
    }

    public void setTipo(String tipo) {
        if (tipo.equals("casa") || tipo.equals("ciudadela") || tipo.equals("cuartel")) {
            this.tipo = tipo;
        } else {
            System.out.println("Tipo edificio introducido incorrecto!");
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Posicion getPosicion() {
        return new Posicion(posicion);
    }

    public void setPosicion(Posicion p) {
        if (p.getX() >= 0 && p.getX() < Mapa.MAPAY && p.getY() >= 0 && p.getY() < Mapa.MAPAX) {
            posicion = new Posicion(p);
        }
    }

    public String getTipo() {
        return tipo;
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
        this.defensa = defensa;
    }

    public String getNombreCivilizacion() {
        return nombreCivilizacion;
    }

    public void setNombreCivilizacion(String nombreCivilizacion) {
        this.nombreCivilizacion = nombreCivilizacion;
    }

}
