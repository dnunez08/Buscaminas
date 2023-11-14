package buscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TableroTest {

    @Test
    void inicializarTableroCorrectamente() {
        Tablero tablero = new Tablero(8, 8, 10);

        Casilla[][] casillas = tablero.inicializarTablero();

        assertNotNull(casillas, "El tablero no debería ser nulo");
        assertEquals(8, casillas.length, "El número de filas debería ser 8");
        assertEquals(8, casillas[0].length, "El número de columnas debería ser 8");

        int minasContadas = 0;
        int casillasVaciasContadas = 0;

        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if (casillas[i][j] instanceof CasillaConMina) {
                    minasContadas++;
                } else if (casillas[i][j] instanceof CasillaVacia) {
                    casillasVaciasContadas++;
                }
            }
        }

        assertEquals(10, minasContadas, "Debería haber 10 minas en el tablero");
        assertEquals(54, casillasVaciasContadas, "Debería haber 54 casillas vacías en el tablero");
    }

    @Test
    void mostrarTableroCorrectamente() {
        Tablero tablero = new Tablero(3, 3, 1);
        Casilla[][] casillas = tablero.inicializarTablero();

        assertDoesNotThrow(() -> tablero.mostrarTablero(casillas), "No debería lanzar excepciones");
    }
}
