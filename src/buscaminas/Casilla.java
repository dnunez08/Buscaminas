/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.io.Serializable;

public class Casilla implements Serializable {
    private int x;
    private int y;
    private boolean oculta;
    private Tablero tablero;

    public Casilla(int x, int y) {
        this.x = x;
        this.y = y;
        this.oculta = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOculta() {
        return oculta;
    }

    public void descubrir() {
        oculta = false;
    }

}

