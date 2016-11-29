/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

/**
 *
 * @author iagolobla
 */
import Entidades.Edificio;
import Entidades.Personaje;
import Entidades.Posicion;
import Entidades.Recurso;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import Mapa.Civilizacion;
import Mapa.Mapa;
import Mapa.Celda;
import Entidades.Grupo;
//Bienvenido al mejor juego de la historia, hecho de la mejor forma posible

public class Principal {

    public Principal() {
        boolean seguir = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al juego.");
        int numciv;

        Civilizacion C;
        HashMap<String, Civilizacion> civilizaciones = new HashMap<String, Civilizacion>();
        do {
            System.out.println("Introduzca el numero de civilizaciones con el que desea jugar (maximo 3. Nombres de una palabra)");
            numciv = scanner.nextInt();
        } while (numciv > 3 || numciv < 1);
        String nombre;
        nombre = scanner.nextLine();//para coger el \0
        for (int i = 1; i <= numciv; i++) {
            System.out.println("Introduzca el nombre de la civilizacion " + i + ": ");
            nombre = scanner.nextLine();
            C = new Civilizacion(nombre);

            if (i == 1) {
                C.setColor("azul");
            } else if (i == 2) {
                C.setColor("rojo");
            } else {
                C.setColor("morado");
            }
            civilizaciones.put(nombre, C);

        }

        C = civilizaciones.get(nombre);

        Mapa map = new Mapa(6, 6, 6, civilizaciones.values());
        map.setCivilizacion(C);
        if (map == null) {
            //map = new Mapa();
        }
        while (seguir) {
            System.out.print("$ ");
            String linea = scanner.nextLine();
            String lineaLowerCase = linea.toLowerCase();    //Pasamos todo a minusculas
            String[] comando = lineaLowerCase.split(" ");
            if (comando.length > 0) {
                switch (comando[0].toLowerCase()) {
                    case "salir":
                        seguir = false;
                        break;
                    case "civilizacion":
                        if (comando.length == 1) {
                            System.out.println("Civilizacion: " + map.getCivilizacion().getNombre());
                        } else {
                            System.out.println("Comando incorrecto");
                        }

                        break;
                    case "cambiar":
                        if (comando.length == 2) {
                            C = civilizaciones.get(comando[1]);
                            map.setCivilizacion(C);
                        } else {
                            System.out.println("La civilizacion no existe.");
                        }
                        break;
                    case "agrupar":
                        Posicion posAgrupar = new Posicion(comando[1]);  //guarda la posicion pasada
                        map.getCelda(posAgrupar).agrupar(map);
                        map.imprimir();
                        break;
                    case "desligar":
                        if (comando.length == 3){
                            Grupo group=C.getGrupos().get(comando[2]);
                            Personaje personaje = map.getCivilizacion().getPersonajes().get(comando[1]);
                            group.desligar(personaje);
                            if(group.getPersonajes().size()==1){
                                Personaje p=group.getPersonajes().get(0);
                                group.desligar(p);
                                map.getCelda(group.getPosicion()).getGrupos().remove(group);
                            }
                        }else{
                            System.out.println("Comando agrupar incorrecto");
                        }
                        break;
                    case "mover":
                        if (comando.length != 3) {
                            System.out.println("Errorsintactico: MOVER ...");
                        } else if (map.getCivilizacion().getPersonajes().containsKey(comando[1])) {
                            Personaje personaje = (Personaje) map.getCivilizacion().getPersonajes().get(comando[1]);
                            if (personaje.isGrupo()) {
                                System.out.println("El personaje pertenece a un grupo, no se puede mover individualmente");
                                break;
                            }
                            Posicion p = new Posicion(personaje.getPosicion());
                            Posicion pos = personaje.moverPj(map, comando[2]);
                            if (!(p.equals(pos))) {
                                System.out.println("El " + comando[1] + " se ha movido a la " + pos);
                            }else{
                                System.out.println("No se puede mover en esa direccion!");
                            }
                            //personaje.mover(comando[2]);
                        } else if (map.getCivilizacion().getGrupos().containsKey(comando[1])) {
                            Grupo group=map.getCivilizacion().getGrupos().get(comando[1]);
                            group.moverGrupo(map, comando[2]);
                        } else {
                            System.out.println("El personaje o el grupo no existe");
                        } //procesar comando
                        //obtener personaje del mapa
                        //comprobar si se puede mover a esa posicion
                        //....
                        map.imprimir();
                        break;
                    case "listar":
                        if (comando.length == 2) {
                            switch (comando[1].toLowerCase()) {
                                case "personajes":
                                    for (String k : map.getCivilizacion().getPersonajes().keySet()) {
                                        System.out.println("Nombre : " + k + ", Posicion: " + map.getCivilizacion().getPersonajes().get(k).getPosicion());
                                    }
                                    break;
                                case "edificios":
                                    for (String k : map.getCivilizacion().getEdificios().keySet()) {
                                        System.out.println("Nombre : " + k + ", Posicion: " + map.getCivilizacion().getEdificios().get(k).getPosicion());
                                    }
                                    break;
                                case "civilizaciones":
                                    for (String k : civilizaciones.keySet()) {
                                        System.out.println("Nombre : " + k);
                                    }
                                    break;
                                default:
                                    System.out.println("comando incorrecto. Debe introducir listar personajes o listar edificios.");
                                    break;
                            }
                        } else {
                            System.out.println("Error sintactico!");
                        }
                        break;
                    case "construir":
                        if (comando.length == 4) {
                            if (map.getCivilizacion().getPersonajes().containsKey(comando[1])) {
                                Personaje personaje = map.getCivilizacion().getPersonajes().get(comando[1]);
                                personaje.construir(map, comando[3], comando[2]);
                            } else {
                                System.out.println("El personaje no existe");
                            }

                        } else {
                            System.out.println("Comando construir incorrecto");
                        }
                        map.imprimir();
                        break;
                    case "describir":
                        if (map.getCivilizacion().getPersonajes().containsKey(comando[1])) {
                            Personaje personaje = map.getCivilizacion().getPersonajes().get(comando[1]);
                            System.out.println(personaje);
                        } else if (map.getCivilizacion().getEdificios().containsKey(comando[1])) {
                            Edificio edificio = map.getCivilizacion().getEdificios().get(comando[1]);
                            System.out.println(edificio);
                        } else if (map.getRecursos().containsKey(comando[1])) {
                            Recurso recurso = map.getRecursos().get(comando[1]);
                            if (map.getCelda(recurso.getPos()).isVisible(map.getCivilizacion().getNombre())) {
                                System.out.println(recurso);
                            } else {
                                System.out.println("La celda no es visible.");
                            }
                        } else {
                            System.out.println("No existe la entidad introducida");
                        }
                        break;

                    case "mirar":
                        if (comando.length == 2) {
                            Posicion posMirar = new Posicion(comando[1]);  //guarda la posicion pasada

                            if (map.checkCoords(posMirar)) {
                                Celda cellMirar = map.getCelda(posMirar);

                                if (cellMirar.isVisible(map.getCivilizacion().getNombre())) {
                                    switch (cellMirar.getTipo()) {    //En funcion del tipo de la celda
                                        case "Pradera":
                                            System.out.println("Celda tipo Pradera");
                                            ArrayList<Personaje> personajes = cellMirar.getPersonajes();
                                            if (personajes.size() > 0) {
                                                System.out.println("Hay personajes aqui!");
                                                for (Personaje person : personajes) {
                                                    System.out.println(person);
                                                }
                                            }

                                            break;
                                        case "ciudadela":
                                            System.out.println("Celda tipo Edificio");
                                            Edificio ciudadelita = cellMirar.getEdificio();
                                            System.out.println(ciudadelita);
                                            break;
                                        case "casa":
                                            System.out.println("Celda tipo Edificio");
                                            Edificio casita = cellMirar.getEdificio();
                                            System.out.println(casita);
                                            break;
                                        case "cuartel":
                                            System.out.println("Celda tipo Edificio");
                                            Edificio cuartelito = cellMirar.getEdificio();
                                            System.out.println(cuartelito);
                                            break;
                                        case "bosque":
                                            System.out.println("Celda tipo Recurso");
                                            Recurso bosquecillo = cellMirar.getRecurso();
                                            System.out.println(bosquecillo);
                                            break;
                                        case "cantera":
                                            System.out.println("Celda tipo Recurso");
                                            Recurso canterilla = cellMirar.getRecurso();
                                            System.out.println(canterilla);
                                            break;
                                        case "arbusto":
                                            System.out.println("Celda tipo Recurso");
                                            Recurso arbustillo = cellMirar.getRecurso();
                                            System.out.println(arbustillo);
                                            break;
                                    }

                                } else {
                                    System.out.println("La celda no es visible!");
                                }

                            } else {
                                System.out.println("Esa celda no esta disponible!");
                            }
                        } else {
                            System.out.println("Error sintactico en el comando, la forma correcta de usarlo es: mirar (x,y)");
                        }
                        break;
                    case "recolectar":
                        if (comando.length == 3) {
                            Personaje paisanito = map.getCivilizacion().getPersonajes().get(comando[1]);  //Recoge al paisano del mapa
                            Posicion posPaisanito = paisanito.getPosicion();

                            switch (comando[2]) {
                                case "n":
                                    posPaisanito.moverX(-1);
                                    break;
                                case "s":
                                    posPaisanito.moverX(1);
                                    break;
                                case "o":
                                    posPaisanito.moverY(-1);
                                    break;
                                case "e":
                                    posPaisanito.moverY(1);
                                    break;
                            }
                            if (map.checkCoords(posPaisanito)) {  //En caso de que la coordenada este bien
                                Celda celdita = map.getCelda(posPaisanito);
                                //Comprobamos que la celda tiene un recurso
                                if (celdita.getTipo().equals("arbusto") || celdita.getTipo().equals("cantera") || celdita.getTipo().equals("bosque")) {
                                    Recurso recursito = map.getCelda(posPaisanito).getRecurso();
                                    paisanito.recolectar(recursito, map);
                                } else {
                                    System.out.println("No hay un recurso aqui!");
                                }
                            }
                        } else {
                            System.out.println("Error sintactico en el comando, la forma correcta de usarlo es: recolectar paisano-x Direccion(N,S,E,O)");
                        }
                        break;
                    case "crear":   //Quizas deberiamos comprobar que el usuario escribe Paisano bien
                        if (comando.length == 3) {
                            if (map.getCivilizacion().getEdificios().containsKey(comando[1])) {
                                switch (comando[2].toLowerCase()) {
                                    case "paisano":
                                        if (map.getCivilizacion().getEdificios().get(comando[1]).getTipo().equals("ciudadela")) {
                                            Edificio ciudadela = map.getCivilizacion().getEdificios().get(comando[1]);
                                            ciudadela.crearPaisano(map);

                                        } else {
                                            System.out.println("Este edificio no es  una ciudadela");
                                        }
                                        break;
                                    case "soldado":
                                        if (map.getCivilizacion().getEdificios().get(comando[1]).getTipo().equals("cuartel")) {
                                            Edificio cuartel = map.getCivilizacion().getEdificios().get(comando[1]);
                                            cuartel.crearSoldado(map);

                                        } else {
                                            System.out.println("Este edificio no es  un cuartel");
                                        }
                                        break;
                                    default:
                                        System.out.println("Tipo personaje mal introducido");
                                }
                            } else {
                                System.out.println("El edificio indicado no existe");
                            }

                        } else {
                            System.out.println("Comando construir incorrecto");
                        }

                        map.imprimir();
                        break;
                    case "almacenar":
                        if (comando.length == 3) {
                            Personaje paisanoAlmacenar = map.getCivilizacion().getPersonajes().get(comando[1]);
                            Posicion pAlmacenar = new Posicion(paisanoAlmacenar.getPosicion());

                            switch (comando[2]) {
                                case "n":
                                    pAlmacenar.moverX(-1);
                                    break;
                                case "s":
                                    pAlmacenar.moverX(1);
                                    break;
                                case "e":
                                    pAlmacenar.moverY(1);
                                    break;
                                case "o":
                                    pAlmacenar.moverY(-1);
                                    break;
                            }

                            Celda celdaAlmacenar = map.getCelda(pAlmacenar);
                            if (celdaAlmacenar.getTipo().equals("ciudadela") || celdaAlmacenar.getTipo().equals("cuartel") || celdaAlmacenar.getTipo().equals("casa")) {  //Comprueba que sea un edificio
                                Edificio ciudadelaAlmacenar = celdaAlmacenar.getEdificio();
                                paisanoAlmacenar.almacenarRecurso(ciudadelaAlmacenar);
                            } else {
                                System.out.println("No hay un edificio aqui");
                            }
                        } else {
                            System.out.println("Error sintactico en el comando, forma correcta: almacenar paisano-x Direccion(N,S,E,O)");
                        }
                        break;
                    case "reparar":
                        if (comando.length == 3) {
                            if (map.getCivilizacion().getPersonajes().containsKey(comando[1])) {
                                Personaje personaje = (Personaje) map.getCivilizacion().getPersonajes().get(comando[1]);
                                personaje.reparar(map, comando[2]);
                            }
                        } else {
                            System.out.println("Comando reparar mal intruducido. Ejemplo: reparar Paisano-1 S");
                        }
                        break;
                    case "imprimir":
                        if (comando[1].equals("mapa")) {
                            map.imprimir();
                        } else {
                            System.out.println("Comando incorrecto!");
                        }
                        break;
                    default:
                        System.out.println("Comando incorrecto");
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        new Principal();
    }

}
