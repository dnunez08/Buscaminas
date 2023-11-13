package buscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NivelTest {

    @Test
    void DevolverNivelCorrectoParaPrincipiante() {
        // Arrange
        int nivelElegido = 1;

        // Act
        Nivel nivel = Nivel.obtenerNivel(nivelElegido);

        // Assert
        assertEquals(Nivel.PRINCIPIANTE, nivel, "Debería devolver PRINCIPIANTE para nivelElegido = 1");
    }

    @Test
    void DevolverNivelCorrectoParaIntermedio() {
        // Arrange
        int nivelElegido = 2;

        // Act
        Nivel nivel = Nivel.obtenerNivel(nivelElegido);

        // Assert
        assertEquals(Nivel.INTERMEDIO, nivel, "Debería devolver INTERMEDIO para nivelElegido = 2");
    }

    @Test
    void DevolverNivelCorrectoParaExperto() {
        // Arrange
        int nivelElegido = 3;

        // Act
        Nivel nivel = Nivel.obtenerNivel(nivelElegido);

        // Assert
        assertEquals(Nivel.EXPERTO, nivel, "Debería devolver EXPERTO para nivelElegido = 3");
    }

    @Test
    void DevolverNivelCorrectoParaPersonalizado() {
        // Arrange
        int nivelElegido = 4;

        // Act
        Nivel nivel = Nivel.obtenerNivel(nivelElegido);

        // Assert
        assertEquals(Nivel.PERSONALIZADO, nivel, "Debería devolver PERSONALIZADO para nivelElegido = 4");
    }

    @Test
    void DevolverNivelDefaultParaNivelInvalido() {
        // Arrange
        int nivelElegido = 5;

        // Act
        Nivel nivel = Nivel.obtenerNivel(nivelElegido);

        // Assert
        assertEquals(Nivel.PRINCIPIANTE, nivel, "Debería devolver PRINCIPIANTE para nivelElegido fuera de rango");
    }
}

