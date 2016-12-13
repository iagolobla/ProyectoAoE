/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.util.Scanner;
/**
 *
 * @author iagolobla
 */
public class ConsolaNormal implements Consola{


    public void imprimir(String Mensaje) {
        System.out.println(Mensaje);
    }

    public String leer(String Mensaje) {
        System.out.printf(Mensaje);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public Integer leerInt(String Mensaje) {
        System.out.printf(Mensaje);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    
}
