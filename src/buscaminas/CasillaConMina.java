/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.io.Serializable;

public class CasillaConMina extends Casilla implements Serializable {
    public CasillaConMina(int x, int y) {
        super(x, y);
    }

    public boolean esMina() {
        return true;
    }
}



