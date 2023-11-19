package buscaminas;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    void testCrearJugadorCorrectamente() {
        String nombre = "Jugador1";
        Image foto = null;

        Jugador jugador = new Jugador(nombre, foto);

        assertNotNull(jugador, "El jugador no debería ser nulo");
        assertEquals(nombre, jugador.getNombre(), "El nombre del jugador debería ser 'Jugador1'");
        assertEquals(foto, jugador.getFoto(), "La foto del jugador debería ser la proporcionada");
        assertEquals(0, jugador.getPuntosTotales(), "Los puntos totales deberían ser 0");
        assertEquals(0, jugador.getPartidasGanadas(), "Las partidas ganadas deberían ser 0");
        assertEquals(1, jugador.getVidas(), "El número de vidas debería ser 1");
    }

    @Test
    void testModificarAtributosDeJugadorCorrectamente() {
        Jugador jugador = new Jugador("Jugador1", null);

        jugador.setPuntosTotales(100);
        jugador.setPartidasGanadas(5);
        jugador.agregarVida();

        assertEquals(100, jugador.getPuntosTotales(), "Los puntos totales deberían ser 100");
        assertEquals(5, jugador.getPartidasGanadas(), "Las partidas ganadas deberían ser 5");
        assertEquals(2, jugador.getVidas(), "El número de vidas debería ser 2 después de agregar una vida");
    }

    @Test
    void testCompararJugadoresCorrectamente() {
        Jugador jugador1 = new Jugador("Jugador1", null);
        Jugador jugador2 = new Jugador("Jugador1", null);

        assertTrue(jugador1.equals(jugador2), "Los jugadores deberían ser iguales según el método equals");
    }
}

