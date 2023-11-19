package buscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NivelTest {

    @Test
    void testDevolverNivelCorrectoParaPrincipiante() {
        int nivelElegido = 1;

        Nivel nivel = Nivel.obtenerNivel(nivelElegido);

        assertEquals(Nivel.PRINCIPIANTE, nivel, "Debería devolver PRINCIPIANTE para nivelElegido = 1");
    }

    @Test
    void testDevolverNivelCorrectoParaIntermedio() {
        int nivelElegido = 2;

        Nivel nivel = Nivel.obtenerNivel(nivelElegido);

        assertEquals(Nivel.INTERMEDIO, nivel, "Debería devolver INTERMEDIO para nivelElegido = 2");
    }

    @Test
    void testDevolverNivelCorrectoParaExperto() {
        int nivelElegido = 3;

        Nivel nivel = Nivel.obtenerNivel(nivelElegido);

        assertEquals(Nivel.EXPERTO, nivel, "Debería devolver EXPERTO para nivelElegido = 3");
    }

    @Test
    void testDevolverNivelCorrectoParaPersonalizado() {
        int nivelElegido = 4;

        Nivel nivel = Nivel.obtenerNivel(nivelElegido);

        assertEquals(Nivel.PERSONALIZADO, nivel, "Debería devolver PERSONALIZADO para nivelElegido = 4");
    }

    @Test
    void testDevolverNivelDefaultParaNivelInvalido() {
        int nivelElegido = 5;

        Nivel nivel = Nivel.obtenerNivel(nivelElegido);

        assertEquals(Nivel.PRINCIPIANTE, nivel, "Debería devolver PRINCIPIANTE para nivelElegido fuera de rango");
    }
}

