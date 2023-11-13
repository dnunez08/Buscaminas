package buscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CasillaConVidaTest {

    @Test
    void esCasillaConVida() {
        // Arrange
        CasillaConVida casillaConVida = new CasillaConVida(1, 1);

        // Act
        boolean resultado = casillaConVida.esCasillaConVida();

        // Assert
        assertTrue(resultado, "Debería ser una casilla con vida");
    }

    @Test
    void coordenadasCorrectas() {
        // Arrange
        int x = 1;
        int y = 2;
        CasillaConVida casillaConVida = new CasillaConVida(x, y);

        // Act
        int coordenadaX = casillaConVida.getX();
        int coordenadaY = casillaConVida.getY();

        // Assert
        assertEquals(x, coordenadaX, "Las coordenadas X deberían ser iguales");
        assertEquals(y, coordenadaY, "Las coordenadas Y deberían ser iguales");
    }
}
