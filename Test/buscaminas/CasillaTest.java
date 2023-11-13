package buscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CasillaTest {

    @Test
    void coordenadasCorrectas() {
        // Arrange
        int x = 1;
        int y = 2;
        Casilla casilla = new Casilla(x, y);

        // Act
        int coordenadaX = casilla.getX();
        int coordenadaY = casilla.getY();

        // Assert
        assertEquals(x, coordenadaX, "Las coordenadas X deberían ser iguales");
        assertEquals(y, coordenadaY, "Las coordenadas Y deberían ser iguales");
    }

    @Test
    void EstarOcultaInicialmente() {
        // Arrange
        Casilla casilla = new Casilla(1, 1);

        // Act
        boolean oculta = casilla.isOculta();

        // Assert
        assertTrue(oculta, "La casilla debería estar oculta inicialmente");
    }

    @Test
    void descubrirCambiaEstado() {
        // Arrange
        Casilla casilla = new Casilla(1, 1);

        // Act
        casilla.descubrir();
        boolean ocultaDespuesDeDescubrir = casilla.isOculta();

        // Assert
        assertFalse(ocultaDespuesDeDescubrir, "La casilla debería estar descubierta después de llamar a descubrir()");
    }
}

