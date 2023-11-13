package buscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TableroTest {

    @Test
    void InicializarTableroCorrectamente() {
        // Arrange
        Tablero tablero = new Tablero(8, 8, 10);

        // Act
        Casilla[][] casillas = tablero.inicializarTablero();

        // Assert
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
    void MostrarTableroCorrectamente() {
        // Arrange
        Tablero tablero = new Tablero(3, 3, 1);
        Casilla[][] casillas = tablero.inicializarTablero();

        // Act y Assert
        assertDoesNotThrow(() -> tablero.mostrarTablero(casillas), "No debería lanzar excepciones");
    }
}
