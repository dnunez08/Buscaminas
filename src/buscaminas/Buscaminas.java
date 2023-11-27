/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.ArrayList;
import java.util.Scanner;

public class Buscaminas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Bienvenido al juego de Buscaminas!");

        Partida partida = new Partida();
        partida.jugarPartida();

        // Mostrar la lista actualizada de jugadores
        System.out.println("*******************************");
        System.out.println("Lista de jugadores actualizada:");
        ArrayList<Jugador> jugadores = Jugador.cargarJugadores();
        for (Jugador aux : jugadores) {
            System.out.println(aux.toString());
        }

        // Cargar la partida desde el archivo binario
        //Partida partidaCargada = Partida.cargarPartida("partida_guardada.bin");

        //if (partidaCargada != null) {
            //System.out.println("Partida cargada exitosamente. Continuar el juego...");
            //partidaCargada.jugarPartida();
        //} else {
            //System.out.println("Error al cargar la partida. Iniciar un nuevo juego...");
            //Partida nuevaPartida = new Partida();
            //nuevaPartida.jugarPartida();
        //}

        scanner.close();
    }
}

