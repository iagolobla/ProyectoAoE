/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Entidades.Personaje;
import Entidades.Edificio;
import Entidades.Posicion;
import Entidades.Recurso;
import java.util.ArrayList;
import java.util.HashMap;
import Entidades.Grupo;

/**
 *
 * @author javier
 */
public class Celda {

    private ArrayList<Personaje> personajes;
    private ArrayList<Grupo> grupos;
    private Edificio edificio;
    private Recurso recurso;
    private Posicion pos;
    private String tipo;
    HashMap<String, Boolean> visible;

    public Celda(Posicion posicion) {
        if (posicion == null) {
            System.out.println("ERROR EN LA POSICION ->NULL");
            return;
        }
        personajes = new ArrayList<Personaje>();
        visible = new HashMap<String, Boolean>();
        grupos = new ArrayList<Grupo>();
        tipo = "Pradera";
        pos = new Posicion(posicion);
    }

    public Celda(String tipo, Posicion posicion, String Nombre, String civilizacion) {
        if (posicion == null) {
            System.out.println("ERROR EN LA POSICION ->NULL");
            return;
        }
        if (visible == null) {
            visible = new HashMap<String, Boolean>();
        }
        personajes = new ArrayList<Personaje>();
        grupos = new ArrayList<Grupo>();
        pos = new Posicion(posicion);

        this.tipo = "Pradera";
        switch (tipo) {
            case "soldado":
                personajes.add(new Personaje("soldado", Nombre, pos));
                visible.put(civilizacion, Boolean.TRUE);
                break;
            case "paisano":
                personajes.add(new Personaje("paisano", Nombre, pos));
                visible.put(civilizacion, Boolean.TRUE);
                break;
            case "ciudadela":
                edificio = new Edificio("ciudadela", pos, Nombre);
                visible.put(civilizacion, Boolean.TRUE);
                this.tipo = tipo;
                break;
            case "casa":
                edificio = new Edificio("casa", pos, Nombre);
                visible.put(civilizacion, Boolean.TRUE);
                this.tipo = tipo;
                break;
            case "cuartel":
                edificio = new Edificio("cuartel", pos, Nombre);
                visible.put(civilizacion, Boolean.TRUE);
                this.tipo = tipo;
                break;
            case "torre":
                edificio = new Edificio("torre", pos, Nombre);
                visible.put(civilizacion, Boolean.TRUE);
                this.tipo = tipo;
                break;
            case "bosque":
                recurso = new Recurso("bosque", 400, Nombre, pos);   //De momento dejaremos 200 por defecto
                visible.put(civilizacion, Boolean.FALSE);
                this.tipo = tipo;
                break;
            case "cantera":
                recurso = new Recurso("cantera", 400, Nombre, pos);   //De momento dejaremos 200 por defecto
                visible.put(civilizacion, Boolean.FALSE);
                this.tipo = tipo;
                break;
            case "arbusto":
                recurso = new Recurso("arbusto", 400, Nombre, pos);   //De momento dejaremos 200 por defecto
                visible.put(civilizacion, Boolean.FALSE);
                this.tipo = tipo;
                break;

            default:
                System.out.println("Tipo mal introducido");

        }
    }

    public void agrupar(Mapa mapa) {//PUEDE DAR PROBLEMAS DE ALIASING. CUANDO METAS UN PERSONAJES CON UN GRUPO EN LA CELDA
        if (grupos.size() > 0) {
            Grupo g;
            int tam=grupos.size();
            for (int i=0;i<tam;i++) {
                g=grupos.get(0);
                g.desagrupar(mapa);
            }
            String Name = "grupo-" + (mapa.getCivilizacion().getCantidades()[6] + 1);
            mapa.getCivilizacion().getCantidades()[6]++;
            Grupo group = new Grupo(personajes, Name, pos, mapa.getCivilizacion().getNombre());
            grupos.add(group);
            mapa.getCivilizacion().getGrupos().put(Name, group);
            System.out.println("Se ha creado el " + Name + ". El grupo esta formado por:");
            for (Personaje x : group.getPersonajes()) {
                System.out.println(x.getNombre());
            }

        } else if (personajes.size() > 1) {
            String Name = "grupo-" + (mapa.getCivilizacion().getCantidades()[6] + 1);
            mapa.getCivilizacion().getCantidades()[6]++;
            Grupo group = new Grupo(personajes, Name, pos, mapa.getCivilizacion().getNombre());
            grupos.add(group);
            mapa.getCivilizacion().getGrupos().put(Name, group);
            System.out.println("Se ha creado el " + Name + ". El grupo esta formado por:");
            for (Personaje x : group.getPersonajes()) {
                System.out.println(x.getNombre());
            }
        } else {
            System.out.println("No se puede crear el grupo, solo hay un personaje");
        }
    }

    public ArrayList<Personaje> getPersonajes() {
        if (personajes != null) {
            return personajes;
        }
        return null;
    }

    public Edificio getEdificio() {
        if (edificio != null) {
            return edificio;
        }
        return null;
    }

    public Recurso getRecurso() {
        if (recurso != null) {
            return recurso;
        }
        return null;
    }

    public boolean isLibre() {
        if (tipo.equals("Pradera")) {
            return !(isPaisano() || isSoldado());
        }
        return false;
    }

    public boolean isPaisano() {
        int Tam = personajes.size();
        if (Tam == 0) {
            return false;
        }
        return personajes.get(Tam - 1).isPaisano();
    }

    public boolean isSoldado() {
        int Tam = personajes.size();
        if (Tam == 0) {
            return false;
        }
        return personajes.get(Tam - 1).isSoldado();
    }

    public boolean isGrupo() {
        if (grupos.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean isEdificio() {
        return (edificio != null);
    }

    public boolean isPersonaje() {
        if (personajes.size() > 0) {
            return true;
        }
        return false;
    }

    public void quitarPersonaje(Personaje P) {
        if (this.isPersonaje()) {
            if (personajes.size() > 1) {
                personajes.remove(P);
            } else {
                liberarCelda();
            }
        } else if (this.isEdificio()) {
            if (edificio.getNPersonajes() > 0) {
                if (edificio.getPersonajes().containsKey(P.getNombre())) {
                    edificio.getPersonajes().remove(P.getNombre());
                    edificio.setNPersonajes(edificio.getNPersonajes() - 1);
                }
            }
        }
    }

    public void liberarCelda() {
        edificio = null;
        personajes = new ArrayList<Personaje>();
        recurso = null;
        tipo = "Pradera";
    }

    public void setPersonajes(Personaje personaje) {
        if (personaje != null) {
            this.personajes.add(personaje);
        } else {
            System.out.println("El personaje pasado es nulo!");
        }
    }

    public void setEdificio(Edificio edificio) {
        if (edificio != null) {
            this.edificio = edificio;
        } else {
            System.out.println("El edificio pasado es nulo!");
        }
    }

    public void setRecurso(Recurso recurso) {
        if (recurso != null) {
            this.recurso = recurso;
        } else {
            System.out.println("El recurso pasado es nulo!");
        }
    }

    public HashMap<String, Boolean> getVisible() {
        return visible;
    }

    public void setVisible(HashMap<String, Boolean> visible) {
        this.visible = visible;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Posicion getPos() {
        return new Posicion(pos);
    }

    public void setPos(Posicion p) {
        if (p.getX() >= 0 && p.getX() < Mapa.MAPAY && p.getY() >= 0 && p.getY() < Mapa.MAPAX) {
            pos = new Posicion(p);
        } else {
            System.out.println("Posicion pasada fuera de los limites del mapa!");
        }
    }

    public boolean isVisible(String civilizacion) {
        return visible.get(civilizacion);
    }

    public void ponerVisible(String civilizacion) {
        visible.replace(civilizacion, Boolean.TRUE);
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(ArrayList<Grupo> grupos) {
        this.grupos = grupos;
    }

    public void setGrupo(Grupo grupo) {
        if (grupo != null) {
            grupos.add(grupo);
        } else {
            System.out.println("grupo pasado null");
        }
    }

}
