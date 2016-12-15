/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Edificios.Casa;
import Edificios.Ciudadela;
import Edificios.Cuartel;
import Edificios.Edificio;
import Edificios.Torre;
import Excepciones.ExcepcionCelda;
import static Juego.Mapa.MAPAX;
import static Juego.Mapa.MAPAY;
import static Juego.Principal.SHELL;
import Personajes.Arquero;
import Personajes.Caballero;
import Personajes.Grupo;
import Personajes.Legionario;
import Personajes.Paisano;
import Personajes.Personaje;
import Recursos.Arbusto;
import Recursos.Bosque;
import Recursos.Cantera;
import Recursos.Comida;
import Recursos.Contenedor;
import Recursos.Madera;
import Recursos.Piedra;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author javier
 */
public class CargadorJuegoDeFichero implements CargadorJuego {

    private String ruta;

    public CargadorJuegoDeFichero(String ruta) {
        this.ruta = ruta;
    }

    public Juego cargar() throws FileNotFoundException, ExcepcionCelda {
        Personaje p = null;
        Grupo grupo = null;
        HashMap<String, Civilizacion> civilizaciones = new HashMap<String, Civilizacion>();
        ArrayList<ArrayList<Celda>> map = new ArrayList<ArrayList<Celda>>();
        Celda cell = null;
        String linea;
        Posicion pos = null;
        HashMap<String, Contenedor> recursos = new HashMap<String, Contenedor>();
        String mapa, personajes, edificios;
        mapa = ruta + "mapa.csv";
        personajes = ruta + "personajes.csv";
        edificios = ruta + "edificios.csv";
        Scanner scanner = new Scanner(new File(mapa));
        scanner.useDelimiter("\n");
        while (scanner.hasNext()) {
            linea = scanner.next();

            System.out.println(linea);
            String lineaLowerCase = linea.toLowerCase();    //Pasamos todo a minusculas
            String[] line = lineaLowerCase.split(";");
            if (!(line[0].startsWith("#"))) {
                pos = new Posicion(line[0]);
                if (pos.getY() == 0) {
                    map.add(new ArrayList<Celda>());
                }
                switch (line[1]) {
                    case "pradera":
                        cell = new Celda(pos);
                        break;
                    case "bosque":
                        Bosque b = new Bosque(line[2], pos, new Madera(Integer.parseInt(line[4])));
                        b.setDescripcion(line[3]);
                        cell = new Celda(b, pos);
                        recursos.put(b.getNombre(), b);

                        break;
                    case "cantera":
                        Cantera c = new Cantera(line[2], pos, new Piedra(Integer.parseInt(line[4])));
                        c.setDescripcion(line[3]);
                        cell = new Celda(c, pos);
                        recursos.put(c.getNombre(), c);
                        break;
                    case "arbusto":
                        Arbusto a = new Arbusto(line[2], pos, new Comida(Integer.parseInt(line[4])));
                        a.setDescripcion(line[3]);
                        cell = new Celda(a, pos);
                        recursos.put(a.getNombre(), a);
                        break;

                }
                map.get(pos.getX()).add(cell);
            }

        }

        Juego juego = new Juego(new Mapa(map, recursos));
        scanner.close();

        scanner = new Scanner(new File(personajes));
        scanner.useDelimiter("\n");
        while (scanner.hasNext()) {
            linea = scanner.next();

            System.out.println(linea);
            String lineaLowerCase = linea.toLowerCase();    //Pasamos todo a minusculas
            String[] line = lineaLowerCase.split(";");
            if (!(line[0].startsWith("#"))) {
                pos = new Posicion(line[0]);
                if (!civilizaciones.containsKey(line[8])) {
                    civilizaciones.put(line[8], new Civilizacion(line[8]));
                }
                switch (line[1]) {
                    case "paisano":
                        Paisano pa = null;
                        pa = new Paisano(line[2], pos, civilizaciones.get(line[8]));

                        pa.setCapacidadRecurso(Integer.parseInt(line[6]));
                        pa.setArmadura(Integer.parseInt(line[5]));
                        pa.setAtaque(Integer.parseInt(line[4]));
                        if (!line[7].equals("")) {
                            if (civilizaciones.get(line[8]).getPersonajes().containsKey(line[7])) {
                                grupo = (Grupo) civilizaciones.get(line[8]).getPersonajes().get(line[7]);
                                grupo.getPersonajes().add(pa);
                                grupo.setNPersonajes(grupo.getNPersonajes() + 1);
                                grupo.setArmadura(grupo.getArmadura() + pa.getArmadura());
                                grupo.setAtaque(grupo.getAtaque() + pa.getAtaque());
                                grupo.setSalud(grupo.getSalud() + pa.getSalud());
                                //Esta linea tan larga actualiza el grupo en la celda
                                juego.getMapa().getCelda(pos).getPersonajes().set(juego.getMapa().getCelda(pos).getPersonajes().indexOf(grupo), grupo);
                            } else {
                                ArrayList<Personaje> g = new ArrayList<Personaje>();
                                g.add(pa);
                                grupo = new Grupo(g, line[7], pos, civilizaciones.get(line[8]));
                                civilizaciones.get(line[8]).getCantidades()[6]++;
                                civilizaciones.get(line[8]).getPersonajes().put(line[7], (Personaje) grupo);
                                juego.getMapa().getCelda(pos).addPersonaje(grupo);
                            }

                            pa.setG(grupo);
                            pa.setDescripcion(line[3]);
                        } else {
                            pa.setDescripcion(line[3]);
                            juego.getMapa().getCelda(pos).addPersonaje(pa);
                        }
                        civilizaciones.get(line[8]).getCantidades()[0]++;
                        civilizaciones.get(line[8]).getPersonajes().put(line[2], (Personaje) pa);
                        break;
                    case "legionario":
                        Legionario l = ((Legionario) p);
                        l = new Legionario(line[2], pos, civilizaciones.get(line[8]));
                        l.setArmadura(Integer.parseInt(line[5]));
                        l.setAtaque(Integer.parseInt(line[4]));
                        if (!line[7].equals("")) {
                            if (civilizaciones.get(line[8]).getPersonajes().containsKey(line[7])) {
                                grupo = (Grupo) civilizaciones.get(line[8]).getPersonajes().get(line[7]);
                                grupo.getPersonajes().add(l);
                                grupo.setNPersonajes(grupo.getNPersonajes() + 1);
                                grupo.setArmadura(grupo.getArmadura() + l.getArmadura());
                                grupo.setAtaque(grupo.getAtaque() + l.getAtaque());
                                grupo.setSalud(grupo.getSalud() + l.getSalud());
                                //Esta linea tan larga actualiza el grupo en la celda
                                juego.getMapa().getCelda(pos).getPersonajes().set(juego.getMapa().getCelda(pos).getPersonajes().indexOf(grupo), grupo);
                            } else {
                                ArrayList<Personaje> g = new ArrayList<Personaje>();
                                g.add(l);
                                grupo = new Grupo(g, line[7], pos, civilizaciones.get(line[8]));
                                civilizaciones.get(line[8]).getCantidades()[6]++;
                                civilizaciones.get(line[8]).getPersonajes().put(line[7], (Personaje) grupo);
                                juego.getMapa().getCelda(pos).addPersonaje(grupo);
                            }

                            l.setG(grupo);
                            l.setDescripcion(line[3]);
                        } else {
                            l.setDescripcion(line[3]);
                            juego.getMapa().getCelda(pos).addPersonaje(l);
                        }

                        civilizaciones.get(line[8]).getCantidades()[1]++;
                        civilizaciones.get(line[8]).getPersonajes().put(line[2], (Personaje) l);
                        break;
                    case "arquero":
                        Arquero ar = ((Arquero) p);
                        ar = new Arquero(line[2], pos, civilizaciones.get(line[8]));
                        ar.setArmadura(Integer.parseInt(line[5]));
                        ar.setAtaque(Integer.parseInt(line[4]));
                        if (!line[7].equals("")) {
                            if (civilizaciones.get(line[8]).getPersonajes().containsKey(line[7])) {
                                grupo = (Grupo) civilizaciones.get(line[8]).getPersonajes().get(line[7]);
                                grupo.getPersonajes().add(ar);
                                grupo.setNPersonajes(grupo.getNPersonajes() + 1);
                                grupo.setArmadura(grupo.getArmadura() + ar.getArmadura());
                                grupo.setAtaque(grupo.getAtaque() + ar.getAtaque());
                                grupo.setSalud(grupo.getSalud() + ar.getSalud());
                                //Esta linea tan larga actualiza el grupo en la celda
                                juego.getMapa().getCelda(pos).getPersonajes().set(juego.getMapa().getCelda(pos).getPersonajes().indexOf(grupo), grupo);
                            } else {
                                ArrayList<Personaje> g = new ArrayList<Personaje>();
                                g.add(ar);
                                grupo = new Grupo(g, line[7], pos, civilizaciones.get(line[8]));
                                civilizaciones.get(line[8]).getCantidades()[6]++;
                                civilizaciones.get(line[8]).getPersonajes().put(line[7], (Personaje) grupo);
                                juego.getMapa().getCelda(pos).addPersonaje(grupo);
                            }

                            ar.setG(grupo);
                            ar.setDescripcion(line[3]);
                        } else {
                            ar.setDescripcion(line[3]);
                            juego.getMapa().getCelda(pos).addPersonaje(ar);
                        }
                        civilizaciones.get(line[8]).getCantidades()[7]++;
                        civilizaciones.get(line[8]).getPersonajes().put(line[2], (Personaje) ar);
                        break;
                    case "caballero":
                        Caballero c = ((Caballero) p);
                        c = new Caballero(line[2], pos, civilizaciones.get(line[8]));
                        c.setArmadura(Integer.parseInt(line[5]));
                        c.setAtaque(Integer.parseInt(line[4]));
                        if (!line[7].equals("")) {
                            if (civilizaciones.get(line[8]).getPersonajes().containsKey(line[7])) {
                                grupo = (Grupo) civilizaciones.get(line[8]).getPersonajes().get(line[7]);
                                grupo.getPersonajes().add(c);
                                grupo.setNPersonajes(grupo.getNPersonajes() + 1);
                                grupo.setArmadura(grupo.getArmadura() + c.getArmadura());
                                grupo.setAtaque(grupo.getAtaque() + c.getAtaque());
                                grupo.setSalud(grupo.getSalud() + c.getSalud());
                                //Esta linea tan larga actualiza el grupo en la celda
                                juego.getMapa().getCelda(pos).getPersonajes().set(juego.getMapa().getCelda(pos).getPersonajes().indexOf(grupo), grupo);
                            } else {
                                ArrayList<Personaje> g = new ArrayList<Personaje>();
                                g.add(c);
                                grupo = new Grupo(g, line[7], pos, civilizaciones.get(line[8]));
                                civilizaciones.get(line[8]).getCantidades()[6]++;
                                civilizaciones.get(line[8]).getPersonajes().put(line[7], (Personaje) grupo);
                                juego.getMapa().getCelda(pos).addPersonaje(grupo);
                            }

                            c.setG(grupo);
                            c.setDescripcion(line[3]);
                        }else {
                            c.setDescripcion(line[3]);
                            juego.getMapa().getCelda(pos).addPersonaje(c);
                        }

                        
                        civilizaciones.get(line[8]).getCantidades()[8]++;
                        civilizaciones.get(line[8]).getPersonajes().put(line[2], (Personaje) c);
                        break;

                }

            }
        }

        scanner.close();

        scanner = new Scanner(new File(edificios));
        scanner.useDelimiter("\n");
        while (scanner.hasNext()) {
            linea = scanner.next();

            System.out.println(linea);
            String lineaLowerCase = linea.toLowerCase();    //Pasamos todo a minusculas
            String[] line = lineaLowerCase.split(";");
            if (!(line[0].startsWith("#"))) {
                pos = new Posicion(line[0]);
                if (!civilizaciones.containsKey(line[4])) {
                    civilizaciones.put(line[8], new Civilizacion(line[4]));
                }
                switch (line[1]) {
                    case "casa":
                        Casa c = new Casa(pos, line[2], civilizaciones.get(line[4]));
                        c.setDescripcion(line[3]);
                        juego.getMapa().getCelda(pos).setEdificio(c);
                        civilizaciones.get(line[4]).getCantidades()[3]++;
                        civilizaciones.get(line[4]).getEdificios().put(line[2], (Edificio) c);
                        break;
                    case "ciudadela":
                        Ciudadela ciu = new Ciudadela(pos, line[2], civilizaciones.get(line[4]));
                        ciu.setDescripcion(line[3]);
                        juego.getMapa().getCelda(pos).setEdificio(ciu);
                        civilizaciones.get(line[4]).getCantidades()[2]++;
                        civilizaciones.get(line[4]).getEdificios().put(line[2], (Edificio) ciu);
                        break;
                    case "torre":
                        Torre t = new Torre(pos, line[2], civilizaciones.get(line[4]));
                        t.setDescripcion(line[3]);
                        juego.getMapa().getCelda(pos).setEdificio(t);
                        civilizaciones.get(line[4]).getCantidades()[4]++;
                        civilizaciones.get(line[4]).getEdificios().put(line[2], (Edificio) t);
                        break;
                    case "cuartel":
                        Cuartel cua = new Cuartel(pos, line[2], civilizaciones.get(line[4]));
                        cua.setDescripcion(line[3]);
                        juego.getMapa().getCelda(pos).setEdificio(cua);
                        civilizaciones.get(line[4]).getCantidades()[5]++;
                        civilizaciones.get(line[4]).getEdificios().put(line[2], (Edificio) cua);
                        break;

                }
            }

        }
        juego.getMapa().setCivilizaciones(new HashMap<String, Civilizacion>(civilizaciones));
        Civilizacion C = null;
        for (Civilizacion c : civilizaciones.values()) {
            C = c;
        }
        juego.getMapa().setCivilizacion(C);
        HashMap<String, Boolean> aux = new HashMap<String, Boolean>();
        for (Civilizacion x : civilizaciones.values()) {
            aux.put(x.getNombre(), Boolean.FALSE);
        }
        for (int i = 0; i < MAPAY; i++) {
            for (int j = 0; j < MAPAX; j++) {   //Inicializamos visible a false para todas las celdas
                juego.getMapa().getCelda(i, j).setVisible(new HashMap<String, Boolean>(aux));
            }
        }
        for (Civilizacion civi : juego.getMapa().getCivilizaciones().values()) {
            juego.getMapa().setCivilizacion(civi);
            juego.getMapa().actualizarVisibilidad();
        }
        /*Celda celdita = null;
        Edificio E = null;
        Personaje T = null;
        for (int i = 0; i < MAPAY; i++) {
            for (int j = 0; j < MAPAX; j++) {   //Poner visibles las celdas con personajes
                celdita = juego.getMapa().getCelda(i, j);
                if (celdita.isEdificio()) {
                    E = celdita.getEdificio();
                    celdita.getVisible().put(E.getCivilizacion().getNombre(), Boolean.TRUE);
                }
                if (celdita.getPersonajes().size() > 0) {
                    T = celdita.getPersonajes().get(0);
                    celdita.getVisible().put(T.getCivilizacion().getNombre(), Boolean.TRUE);
                }
            }
        }*/
        SHELL.imprimir(juego.getMapa().print());
        return juego;
    }
}
