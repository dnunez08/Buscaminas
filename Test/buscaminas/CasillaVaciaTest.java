package buscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CasillaVaciaTest {

    @Test
    void testCoordenadasCorrectas() {
        int x = 1;
        int y = 2;
        CasillaVacia casillaVacia = new CasillaVacia(x, y, 0);

        int coordenadaX = casillaVacia.getX();
        int coordenadaY = casillaVacia.getY();

        assertEquals(x, coordenadaX, "Las coordenadas X deberían ser iguales");
        assertEquals(y, coordenadaY, "Las coordenadas Y deberían ser iguales");
    }

    @Test
    void testEstarOcultaInicialmente() {
        CasillaVacia casillaVacia = new CasillaVacia(1, 1, 0);

        boolean oculta = casillaVacia.isOculta();

        assertTrue(oculta, "La casilla debería estar oculta inicialmente");
    }

    @Test
    void testTenerNumMinasAlrededorCorrecto() {
        int numMinasAlrededor = 3;
        CasillaVacia casillaVacia = new CasillaVacia(1, 1, numMinasAlrededor);

        int numMinasObtenido = casillaVacia.getNumMinasAlrededor();

        assertEquals(numMinasAlrededor, numMinasObtenido, "El número de minas alrededor debería ser el mismo");
    }

    @Test
    void testCambiarNumMinasAlrededor() {
        CasillaVacia casillaVacia = new CasillaVacia(1, 1, 0);

        int nuevoNumMinasAlrededor = 5;
        casillaVacia.setNumMinasAlrededor(nuevoNumMinasAlrededor);
        int numMinasObtenidoDespuesDeCambiar = casillaVacia.getNumMinasAlrededor();

        assertEquals(nuevoNumMinasAlrededor, numMinasObtenidoDespuesDeCambiar, "El número de minas alrededor debería cambiar");
    }

    @Test
    void testDescubrirCambiaEstado() {
        CasillaVacia casillaVacia = new CasillaVacia(1, 1, 0);

        casillaVacia.descubrir();
        boolean ocultaDespuesDeDescubrir = casillaVacia.isOculta();

        assertFalse(ocultaDespuesDeDescubrir, "La casilla debería estar descubierta después de llamar a descubrir()");
    }
}

