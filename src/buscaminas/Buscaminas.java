/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.Scanner;

public class Buscaminas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Bienvenido al juego de Buscaminas!");
        Partida partida = new Partida();
        partida.crearJugador(scanner);
        partida.jugarPartida();
        scanner.close();
    }


}
