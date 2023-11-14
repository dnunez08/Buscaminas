package buscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ResultadoTest {

    @Test
    void tenerValoresCorrectos() {
        Resultado ganada = Resultado.GANADA;
        Resultado perdida = Resultado.PERDIDA;
        Resultado abandono = Resultado.ABANDONO;

        assertEquals("GANADA", ganada.name(), "El nombre del resultado ganada debería ser 'GANADA'");
        assertEquals("PERDIDA", perdida.name(), "El nombre del resultado perdida debería ser 'PERDIDA'");
        assertEquals("ABANDONO", abandono.name(), "El nombre del resultado abandono debería ser 'ABANDONO'");

        Resultado[] valoresEsperados = {Resultado.GANADA, Resultado.PERDIDA, Resultado.ABANDONO};
        assertArrayEquals(valoresEsperados, Resultado.values(), "Los valores de Resultado deberían ser GANADA, PERDIDA y ABANDONO");
    }
}

