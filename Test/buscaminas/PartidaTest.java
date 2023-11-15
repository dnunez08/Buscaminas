/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class PartidaTest {

    @Test
    void inicializarPartidaCorrectamente() {
        Partida partida = new Partida();

        boolean resultado = partida.inicializarPartida(1);

        assertTrue(resultado, "Debería inicializar la partida correctamente");
        assertNotNull(partida.getTablero(), "El tablero no debería ser nulo");
        assertNotNull(partida.getNivelSeleccionado(), "El nivel seleccionado no debería ser nulo");
    }

    @Test
    void noInicializarPartidaConNivelInvalido() {
        Partida partida = new Partida();

        boolean resultado = partida.inicializarPartida(5);

        assertFalse(resultado, "No debería inicializar la partida con un nivel inválido");
        assertNull(partida.getTablero(), "El tablero debería ser nulo");
        assertNull(partida.getNivelSeleccionado(), "El nivel seleccionado debería ser nulo");
    }

    @Test
    void mostrarNivelesDisponibles() {
        Partida partida = new Partida();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput = "Niveles disponibles:\n" +
                "1. Principiante (8x8, 10 minas)\n" +
                "2. Intermedio (16x16, 40 minas)\n" +
                "3. Experto (16x30, 99 minas)\n" +
                "4. Personalizado";

        partida.mostrarNivelesDisponibles();
        String actualOutput = outContent.toString().trim();

        assertEquals(expectedOutput, actualOutput, "La salida debería ser igual a la esperada");
    }


    @Test
    void crearJugadorCorrectamente() {
        Partida partida = new Partida();
        String input = "Juan\nSi\nfoto.jpg\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Jugador jugador = partida.crearJugador(new Scanner(System.in));

        assertNotNull(jugador, "El jugador no debería ser nulo");
        assertEquals("Juan", jugador.getNombre(), "El nombre del jugador debería ser Juan");
        assertNotNull(jugador.getFoto(), "La foto del jugador no debería ser nula");
    }

    @Test
    void cargarImagenCorrectamente() {
        Partida partida = new Partida();
        String input = "Si\nfoto.jpg\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Image imagen = partida.cargarImagen(new Scanner(System.in));

        assertNotNull(imagen, "La imagen no debería ser nula");
    }

}
