/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

/**
 *
 * @author danus
 */
public enum Nivel {
    PRINCIPIANTE,
    INTERMEDIO,
    EXPERTO,
    PERSONALIZADO;

    public static Nivel obtenerNivel(int nivelElegido) {
        switch (nivelElegido) {
            case 1:
                return PRINCIPIANTE;
            case 2:
                return INTERMEDIO;
            case 3:
                return EXPERTO;
            case 4:
                return PERSONALIZADO;
            default:
                System.out.println("Nivel no válido. Seleccionando PRINCIPIANTE por defecto.");
                return PRINCIPIANTE;
        }
    }
}
