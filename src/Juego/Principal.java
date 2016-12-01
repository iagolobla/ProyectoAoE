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
        
        System.out.println("Estas jugando con los: " + C.getNombre());
        
        Mapa map = new Mapa(6, 6, 6, civilizaciones.values());
        map.setCivilizacion(C);
        if (map == null) {
            //map = new Mapa();
        }
        map.imprimir();
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
                            if (map.getCivilizaciones().containsKey(comando[1])) {
                                if (comando[1].equals(map.getCivilizacion())) {
                                    System.out.println("Ya se esta jugando con los " + comando[1]);
                                } else {
                                    C = map.getCivilizaciones().get(comando[1]);
                                    map.setCivilizacion(C);
                                    System.out.println("Ha cambiado a la civilizacion: " + C.getNombre());
                                }
                            } else {
                                System.out.println("La civilizacion indicada no existe!");
                            }
                        } else {
                            System.out.println("Error sintactico!");
                        }
                        map.imprimir();
                        break;
                    case "agrupar":
                        Posicion posAgrupar = new Posicion(comando[1]);  //guarda la posicion pasada
                        map.getCelda(posAgrupar).agrupar(map);
                        map.imprimir();
                        break;
                    case "desligar":
                        if (comando.length == 3) {
                            if (map.getCivilizacion().getGrupos().containsKey(comando[2]) && map.getCivilizacion().getPersonajes().containsKey(comando[1])) {
                                Grupo group = map.getCivilizacion().getGrupos().get(comando[2]);
                                
                                Personaje personaje = map.getCivilizacion().getPersonajes().get(comando[1]);
                                group.desligar(personaje);
                                if (group.getPersonajes().size() == 1) {
                                    Personaje p = group.getPersonajes().get(0);
                                    group.desligar(p);
                                    map.getCelda(group.getPosicion()).getGrupos().remove(group);
                                    map.getCivilizacion().getGrupos().remove(group.getNombre());
                                }
                            } else {
                                System.out.println("No existe el grupo o el personaje introducido");
                            }
                        } else {
                            System.out.println("Comando desligar incorrecto");
                        }
                        map.imprimir();
                        break;
                    case "desagrupar":
                        if (comando.length == 2) {
                            if(map.getCivilizacion().getGrupos().containsKey(comando[1])){
                                Grupo g = map.getCivilizacion().getGrupos().get(comando[1]);
                                g.desagrupar(map);
                            } else {
                                System.out.println("Esa cofradia de los amigos de los niños no existe en el registro civil!");
                            }
                        } else {
                            System.out.println("Comando desagrupar incorrecto");
                        }
                        map.imprimir();
                        break;
                    case "atacar":
                        if(comando.length == 3){
                            
                            if(C.getPersonajes().containsKey(comando[1])){  //Si se hace "atacar personaje dir"
                                Personaje P = C.getPersonajes().get(comando[1]);
                                if(!P.atacar(map, comando[2])){
                                    System.out.println("El personaje no ha sido quien de atacar!");
                                }
                            } else if(C.getGrupos().containsKey(comando[1])){   //Si se hace "atacar grupo dir"
                                
                            } else if(C.getEdificios().containsKey(comando[1])){    //Si se hace "atacar edificio dir"
                                Edificio ef = C.getEdificios().get(comando[1]);
                                if(!ef.atacar(map, comando[2])){
                                    System.out.println("Esta basura de edificio no ha tenido los santos cimientos de atacar!");
                                }
                            } else {
                                System.out.println("La entidad especificada no existe!");
                            }
                        } else {
                            System.out.println("Error Sintáctico!");
                        }
                        break;
                    case "mover":
                        if (comando.length != 3) {
                            System.out.println("Errorsintactico: MOVER ...");
                        } else if (C.getPersonajes().containsKey(comando[1])) {
                            Personaje personaje = (Personaje) map.getCivilizacion().getPersonajes().get(comando[1]);
                            if (personaje.isGrupo()) {
                                System.out.println("El personaje pertenece a un grupo, no se puede mover individualmente");
                                break;
                            }
                            Posicion p = new Posicion(personaje.getPosicion());
                            Posicion pos = personaje.moverPj(map, comando[2]);
                            if (!(p.equals(pos))) {
                                System.out.println("El " + comando[1] + " se ha movido a la " + pos);
                                if (map.getCelda(p).isEdificio()) {   //Comprueba si el personaje venia de un ef
                                    Edificio ef = map.getCelda(p).getEdificio();
                                    ef.setAtaque(ef.getAtaque() - personaje.getAtaque());
                                    ef.setDefensa(ef.getDefensa() - personaje.getArmadura());
                                }
                            } else {
                                System.out.println("No se puede mover en esa direccion!");
                            }
                            //personaje.mover(comando[2]);
                        } else if (map.getCivilizacion().getGrupos().containsKey(comando[1])) {
                            Grupo group = map.getCivilizacion().getGrupos().get(comando[1]);
                            Posicion pG = new Posicion(group.getPosicion());
                            Posicion posG = group.moverGrupo(map, comando[2]);
                            if (!(pG.equals(posG))) {
                                System.out.println("El " + comando[1] + " se ha movido a la " + posG);
                                if (map.getCelda(pG).isEdificio()) {   //Comprueba si el personaje venia de un ef
                                    Edificio ef = map.getCelda(pG).getEdificio();
                                    ef.setAtaque(ef.getAtaque() - group.getAtaque());
                                    ef.setDefensa(ef.getDefensa() - group.getArmadura());
                                }
                            } else {
                                System.out.println("No se puede mover en esa direccion!");
                            }
                        } else {
                            System.out.println("Esa entidad no es de los autenticos " + C.getNombre() + "!");
                        } //procesar comando
                        //obtener personaje del mapa
                        //comprobar si se puede mover a esa posicion
                        //....
                        map.imprimir();
                        break;
                    case "defender":    //No se puede usar por grupos
                        if (comando.length == 3) {
                            if (C.getPersonajes().containsKey(comando[1])) { //Si existe el personaje pasado
                                Personaje pers = C.getPersonajes().get(comando[1]);
                                if (pers.isGrupo()) {
                                    System.out.println("El personaje tiene un compromiso con su grupo!");
                                } else if (pers.defender(map, comando[2])) {
                                    System.out.println("El Personaje servira a su patria en este edificio!");
                                } else {
                                    System.out.println("El personaje no pudo defender, una autentica deshonra");
                                }
                                
                            } else if (C.getGrupos().containsKey(comando[1])) { //Si es un grupo
                                Grupo G = C.getGrupos().get(comando[1]);
                                if (G.defender(map, comando[2])) {
                                    System.out.println("Este grupo de compatriotas ha entrado a defender el edificio");
                                } else {
                                    System.out.println("Este atajo de individuos no han sido capaces de defender");
                                }
                            } else {
                                System.out.println("Este no es de los autenticos " + C.getNombre() + "!");
                            }
                        } else {
                            System.out.println("Error sintactico!");
                        }
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
                                    for (String k : map.getCivilizaciones().keySet()) {
                                        System.out.println("Nombre : " + k);
                                    }
                                    break;
                                case "grupos":
                                    for (String G : C.getGrupos().keySet()) {
                                        System.out.println("Grupo: " + G);
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
                            if (edificio.getTipo().equals("ciudadela")) {
                                edificio.imprimirCiudadela(map.getCivilizacion());
                            } else {
                                System.out.println(edificio);
                            }
                        } else if (map.getRecursos().containsKey(comando[1])) {
                            Recurso recurso = map.getRecursos().get(comando[1]);
                            if (map.getCelda(recurso.getPos()).isVisible(map.getCivilizacion().getNombre())) {
                                System.out.println(recurso);
                            } else {
                                System.out.println("La celda no es visible.");
                            }
                        } else if (C.getGrupos().containsKey(comando[1])) {
                            Grupo G = C.getGrupos().get(comando[1]);
                            System.out.println(G);
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
                                            ciudadelita.imprimirCiudadela(map.getCivilizacion());
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
                        if (comando.length != 3) {
                            System.out.println("Error sintactico en el comando, la forma correcta de usarlo es: recolectar paisano-x Direccion(N,S,E,O)");
                        } else if (map.getCivilizacion().getPersonajes().containsKey(comando[1])) {
                            Personaje paisanito = map.getCivilizacion().getPersonajes().get(comando[1]);  //Recoge al paisano del mapa
                            if (paisanito.isGrupo()) {
                                System.out.println("El paisano no puede recolectar ya que pertenece a un grupo");
                                break;
                            }
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
                        } else if (map.getCivilizacion().getGrupos().containsKey(comando[1])) {
                            Grupo group = map.getCivilizacion().getGrupos().get(comando[1]);
                            Posicion posG = new Posicion(group.getPosicion());
                            
                            switch (comando[2]) {
                                case "n":
                                    posG.moverX(-1);
                                    break;
                                case "s":
                                    posG.moverX(1);
                                    break;
                                case "o":
                                    posG.moverY(-1);
                                    break;
                                case "e":
                                    posG.moverY(1);
                                    break;
                            }
                            if (map.checkCoords(posG)) {  //En caso de que la coordenada este bien
                                Celda celdita = map.getCelda(posG);
                                //Comprobamos que la celda tiene un recurso
                                if (celdita.getTipo().equals("arbusto") || celdita.getTipo().equals("cantera") || celdita.getTipo().equals("bosque")) {
                                    Recurso recursito = map.getCelda(posG).getRecurso();
                                    group.recolectar(recursito, map);
                                } else {
                                    System.out.println("No hay un recurso aqui!");
                                }
                            }
                            
                        } else {
                            System.out.println("No existe la entidad introducida");
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
                        if (comando.length != 3) {
                            System.out.println("Error sintactico!");
                            
                        } else if (map.getCivilizacion().getPersonajes().containsKey(comando[1])) {
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
                                paisanoAlmacenar.almacenarRecurso(ciudadelaAlmacenar, C);
                            } else {
                                System.out.println("No hay un edificio aqui");
                            }
                        } else if (map.getCivilizacion().getGrupos().containsKey(comando[1])) {
                            Grupo grupoAlmacenar = map.getCivilizacion().getGrupos().get(comando[1]);
                            Posicion gAlmacenar = new Posicion(grupoAlmacenar.getPosicion());
                            
                            switch (comando[2]) {
                                case "n":
                                    gAlmacenar.moverX(-1);
                                    break;
                                case "s":
                                    gAlmacenar.moverX(1);
                                    break;
                                case "e":
                                    gAlmacenar.moverY(1);
                                    break;
                                case "o":
                                    gAlmacenar.moverY(-1);
                                    break;
                            }
                            
                            Celda celdaAlmacenar = map.getCelda(gAlmacenar);
                            if (celdaAlmacenar.getTipo().equals("ciudadela") || celdaAlmacenar.getTipo().equals("cuartel") || celdaAlmacenar.getTipo().equals("casa")) {  //Comprueba que sea un edificio
                                Edificio ciudadelaAlmacenar = celdaAlmacenar.getEdificio();
                                grupoAlmacenar.almacenarRecurso(ciudadelaAlmacenar, C);
                            } else {
                                System.out.println("No hay un edificio aqui");
                            }
                        } else {
                            System.out.println("Error sintactico en el comando, forma correcta: almacenar paisano-x Direccion(N,S,E,O)");
                        }
                        break;
                    case "reparar":
                        if (comando.length != 3) {
                            System.out.println("Comando reparar mal introducido. Ejemplo: reparar Paisano-1 S");
                        } else if (map.getCivilizacion().getPersonajes().containsKey(comando[1])) {
                            Personaje personaje = (Personaje) map.getCivilizacion().getPersonajes().get(comando[1]);
                            personaje.reparar(map, comando[2]);
                        } else if (map.getCivilizacion().getGrupos().containsKey(comando[1])) {
                            Grupo group=map.getCivilizacion().getGrupos().get(comando[1]);
                            group.reparar(map, comando[2]);
                        } else {
                            System.out.println("Comando reparar mal introducido. Ejemplo: reparar Paisano-1 S");
                        }
                        break;
                    case "imprimir":
                        if (comando.length == 2) {
                            if (comando[1].equals("mapa")) {
                                map.imprimir();
                            } else {
                                System.out.println("Comando incorrecto!");
                            }
                        } else {
                            System.out.println("Error sintactico!");
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
