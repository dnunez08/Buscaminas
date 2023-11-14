package buscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CasillaConMinaTest {
    @Test
    void esCasillaConMina() {
        CasillaConMina casillaConMina = new CasillaConMina(1, 1);

        boolean resultado = casillaConMina.esMina();

        assertTrue(resultado, "Debería ser una mina");
    }

    @Test
    void coordenadasCorrectas() {
        int x = 1;
        int y = 2;
        CasillaConMina casillaConMina = new CasillaConMina(x, y);

        int coordenadaX = casillaConMina.getX();
        int coordenadaY = casillaConMina.getY();

        assertEquals(x, coordenadaX, "Las coordenadas X deberían ser iguales");
        assertEquals(y, coordenadaY, "Las coordenadas Y deberían ser iguales");
    }
}