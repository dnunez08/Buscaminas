package buscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CasillaVaciaTest {

    @Test
    void CoordenadasCorrectas() {
        // Arrange
        int x = 1;
        int y = 2;
        CasillaVacia casillaVacia = new CasillaVacia(x, y, 0);

        // Act
        int coordenadaX = casillaVacia.getX();
        int coordenadaY = casillaVacia.getY();

        // Assert
        assertEquals(x, coordenadaX, "Las coordenadas X deberían ser iguales");
        assertEquals(y, coordenadaY, "Las coordenadas Y deberían ser iguales");
    }

    @Test
    void EstarOcultaInicialmente() {
        // Arrange
        CasillaVacia casillaVacia = new CasillaVacia(1, 1, 0);

        // Act
        boolean oculta = casillaVacia.isOculta();

        // Assert
        assertTrue(oculta, "La casilla debería estar oculta inicialmente");
    }

    @Test
    void TenerNumMinasAlrededorCorrecto() {
        // Arrange
        int numMinasAlrededor = 3;
        CasillaVacia casillaVacia = new CasillaVacia(1, 1, numMinasAlrededor);

        // Act
        int numMinasObtenido = casillaVacia.getNumMinasAlrededor();

        // Assert
        assertEquals(numMinasAlrededor, numMinasObtenido, "El número de minas alrededor debería ser el mismo");
    }

    @Test
    void CambiarNumMinasAlrededor() {
        // Arrange
        CasillaVacia casillaVacia = new CasillaVacia(1, 1, 0);

        // Act
        int nuevoNumMinasAlrededor = 5;
        casillaVacia.setNumMinasAlrededor(nuevoNumMinasAlrededor);
        int numMinasObtenidoDespuesDeCambiar = casillaVacia.getNumMinasAlrededor();

        // Assert
        assertEquals(nuevoNumMinasAlrededor, numMinasObtenidoDespuesDeCambiar, "El número de minas alrededor debería cambiar");
    }

    @Test
    void descubrirCambiaEstado() {
        // Arrange
        CasillaVacia casillaVacia = new CasillaVacia(1, 1, 0);

        // Act
        casillaVacia.descubrir();
        boolean ocultaDespuesDeDescubrir = casillaVacia.isOculta();

        // Assert
        assertFalse(ocultaDespuesDeDescubrir, "La casilla debería estar descubierta después de llamar a descubrir()");
    }
}

