package buscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CasillaConMinaTest {
    @Test
    void esCasillaConMina() {
        // Arrange
        CasillaConMina casillaConMina = new CasillaConMina(1, 1);

        // Act
        boolean resultado = casillaConMina.esMina();

        // Assert
        assertTrue(resultado, "Debería ser una mina");
    }

    @Test
    void coordenadasCorrectas() {
        // Arrange
        int x = 1;
        int y = 2;
        CasillaConMina casillaConMina = new CasillaConMina(x, y);

        // Act
        int coordenadaX = casillaConMina.getX();
        int coordenadaY = casillaConMina.getY();

        // Assert
        assertEquals(x, coordenadaX, "Las coordenadas X deberían ser iguales");
        assertEquals(y, coordenadaY, "Las coordenadas Y deberían ser iguales");
    }

    public static class CasillaConVida {
    }
}