/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.io.Serializable;

public class CasillaVacia extends Casilla implements Serializable {
    private int numMinasAlrededor;

    public CasillaVacia(int x, int y, int numMinasAlrededor) {
        super(x, y);
        this.numMinasAlrededor = numMinasAlrededor;
    }

    public int getNumMinasAlrededor() {
        return numMinasAlrededor;
    }

    public void setNumMinasAlrededor(int numMinasAlrededor) {
        this.numMinasAlrededor = numMinasAlrededor;
    }
    
    
}


