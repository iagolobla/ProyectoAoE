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

public class Grupo {

    ArrayList<Personaje> personajes;
    private String nombreCivilizacion;
    private int armadura;
    private int salud;
    private int ataque;
    private int capRecolectar;
    private String Nombre;  //Aqui se necesita poner un nombre al personaje para usar eso como clave en el hashmap de personajes en el mapa
    private Posicion posicion;
    private int madera;
    private int piedra;
    private int comida;

    public Grupo(ArrayList<Personaje> person, String Nombre, Posicion pos, String civilizacion) {
        personajes = new ArrayList<Personaje>();
        nombreCivilizacion = civilizacion;
        this.Nombre = Nombre;
        int soldado=0;
        posicion = new Posicion(pos);
        for (Personaje p : person) {
            p.setGrupo(true);
            personajes.add(p);
            armadura += p.getArmadura();
            salud += p.getSalud();
            ataque += p.getAtaque();
            capRecolectar += p.getCapRecolectar();
            switch (p.getTipoRecurso()) {
                case "madera":
                    madera+=p.getCantidadRecolectada();
                    break;
                case "piedra":
                    piedra+=p.getCantidadRecolectada();
                    break;
                case "comida":
                    comida+=p.getCantidadRecolectada();
                    break;
                default:
                    System.out.println("Tipo de recurso incorrecto");
            }
            if(p.isSoldado()){
                soldado=1;
            }
        }
        if (soldado==1){
            capRecolectar=0;
        }
    }

}
