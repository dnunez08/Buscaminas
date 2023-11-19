package buscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CasillaConVidaTest {

    @Test
    void testEsCasillaConVida() {
        CasillaConVida casillaConVida = new CasillaConVida(1, 1);

        boolean resultado = casillaConVida.esCasillaConVida();

        assertTrue(resultado, "Debería ser una casilla con vida");
    }

    @Test
    void testCoordenadasCorrectas() {
        int x = 1;
        int y = 2;
        CasillaConVida casillaConVida = new CasillaConVida(x, y);

        int coordenadaX = casillaConVida.getX();
        int coordenadaY = casillaConVida.getY();

        assertEquals(x, coordenadaX, "Las coordenadas X deberían ser iguales");
        assertEquals(y, coordenadaY, "Las coordenadas Y deberían ser iguales");
    }
}
