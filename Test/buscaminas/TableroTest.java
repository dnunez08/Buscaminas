package buscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TableroTest {

    @Test
    void testInicializarTableroCorrectamente() {
        Tablero tablero = new Tablero(8, 8, 10, 2);

        Casilla[][] casillas = tablero.inicializarTablero();

        assertNotNull(casillas, "El tablero no debería ser nulo");
        assertEquals(8, casillas.length, "El número de filas debería ser 8");
        assertEquals(8, casillas[0].length, "El número de columnas debería ser 8");

        int minasContadas = 0;
        int casillasVaciasContadas = 0;
        int vidasContadas = 0;

        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if (casillas[i][j] instanceof CasillaConMina) {
                    minasContadas++;
                } else if (casillas[i][j] instanceof CasillaVacia) {
                    casillasVaciasContadas++;
                } else if (casillas[i][j] instanceof CasillaConVida) {
                    vidasContadas++;
                }
            }
        }

        System.out.println("Minas: " + minasContadas);
        System.out.println("Casillas vacías: " + casillasVaciasContadas);
        System.out.println("Vidas: " + vidasContadas);

        assertEquals(10, minasContadas, "Debería haber 10 minas en el tablero");
        assertEquals(2, vidasContadas, "Debería haber 2 casillas con vida en el tablero");
        assertEquals(52, casillasVaciasContadas, "Debería haber 46 casillas vacías en el tablero");
    }


    @Test
    void testMostrarTableroCorrectamente() {
        Tablero tablero = new Tablero(3, 3, 1, 1);
        Casilla[][] casillas = tablero.inicializarTablero();

        assertDoesNotThrow(() -> tablero.mostrarTablero(casillas), "No debería lanzar excepciones");
    }

    @Test
    void testMostrarVidasCorrectamente() {
        Tablero tablero = new Tablero(3, 3, 1, 3);

        assertDoesNotThrow(() -> tablero.mostrarVidas(), "No debería lanzar excepciones");
    }
}

