package buscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CasillaTest {

    @Test
    void coordenadasCorrectas() {
        int x = 1;
        int y = 2;
        Casilla casilla = new Casilla(x, y);

        int coordenadaX = casilla.getX();
        int coordenadaY = casilla.getY();

        assertEquals(x, coordenadaX, "Las coordenadas X deberían ser iguales");
        assertEquals(y, coordenadaY, "Las coordenadas Y deberían ser iguales");
    }

    @Test
    void estarOcultaInicialmente() {
        Casilla casilla = new Casilla(1, 1);

        boolean oculta = casilla.isOculta();

        assertTrue(oculta, "La casilla debería estar oculta inicialmente");
    }

    @Test
    void descubrirCambiaEstado() {
        Casilla casilla = new Casilla(1, 1);

        casilla.descubrir();
        boolean ocultaDespuesDeDescubrir = casilla.isOculta();

        assertFalse(ocultaDespuesDeDescubrir, "La casilla debería estar descubierta después de llamar a descubrir()");
    }
}

