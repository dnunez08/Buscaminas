/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;
import java.util.Random;

/**
 *
 * @author danus
 */


public class Tablero {
    private int dimX;
    private int dimY;
    private int numMinas;
    private int numVidas;

    public Tablero(int dimX, int dimY, int numMinas, int numVidas) {
        this.dimX = dimX;
        this.dimY = dimY;
        this.numMinas = numMinas;
        this.numVidas = numVidas;
    }


    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }

    public int getNumMinas() {
        return numMinas;
    }
    public int getNumVidas() {
        return numVidas;
    }


    public Casilla[][] inicializarTablero() {
        Casilla[][] casillas = new Casilla[dimX][dimY];
        Random random = new Random();
        // Inicializar todas las casillas como CasillaVacia por defecto
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
            casillas[i][j] = new CasillaVacia(i, j, 0);
            }
        }
        // Colocar minas aleatoriamente en el tablero
        int minasColocadas = 0;
        while (minasColocadas < numMinas) {
            int fila = random.nextInt(dimX);
            int columna = random.nextInt(dimY);

            // Verificar si ya hay una mina en la casilla
            if (casillas[fila][columna] instanceof CasillaVacia) {
                casillas[fila][columna] = new CasillaConMina(fila, columna);
                minasColocadas++;
            }
        }

        // Colocar casillas con vida aleatoriamente en el tablero
        int vidasColocadas = 0;
        while (vidasColocadas < numVidas) {
            int fila = random.nextInt(dimX);
            int columna = random.nextInt(dimY);

            // Verificar si la casilla está vacía
            if (casillas[fila][columna] instanceof CasillaVacia) {
                casillas[fila][columna] = new CasillaConVida(fila, columna);
                vidasColocadas++;
            }
        }

        // Calcular el número de minas alrededor de las casillas vacías
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                if (casillas[i][j] instanceof CasillaVacia) {
                    int minasAlrededor = calcularMinasAlrededor(casillas, i, j);
                    ((CasillaVacia) casillas[i][j]).setNumMinasAlrededor(minasAlrededor);
                }
            }
        }
        // Calcular el número de minas alrededor de las casillas con vida
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                if (casillas[i][j] instanceof CasillaConVida) {
                    int minasAlrededor = calcularMinasAlrededor(casillas, i, j);
                    ((CasillaConVida) casillas[i][j]).setNumMinasAlrededor(minasAlrededor);
                }
            }
        }
        return casillas;
    }

    private int calcularMinasAlrededor(Casilla[][] casillas, int fila, int columna) {
        int minasAlrededor = 0;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int newX = fila + dx[i];
            int newY = columna + dy[i];

            if (newX >= 0 && newX < dimX && newY >= 0 && newY < dimY) {
                if (casillas[newX][newY] instanceof CasillaConMina) {
                    minasAlrededor++;
                }
            }
        }
        return minasAlrededor;
    }

    public void mostrarVidas() {
        System.out.println("Número de vidas: " + numVidas);
    }

    public void mostrarTablero(Casilla[][] casillas) {
        // Imprimir encabezado de columnas
        System.out.print(""); // Espacio para el número de fila
        for (int columna = 1; columna <= casillas[0].length; columna++) {
            System.out.printf("%6d ", columna); // Número de columna
        }
        System.out.println(); // Cambiar de línea

        // Imprimir encabezado de filas
        for (int fila = 0; fila < casillas.length; fila++) {
            System.out.printf("%2d", fila + 1);

            for (int columna = 0; columna < casillas[fila].length; columna++) {
                if (casillas[fila][columna].isOculta()) {
                    System.out.print(" [   ] ");
                } else {
                    if (casillas[fila][columna] instanceof CasillaConMina) {
                        System.out.print(" [ * ] "); // Mina
                    } else if (casillas[fila][columna] instanceof CasillaConVida) {
                        int minasAlrededor = ((CasillaConVida) casillas[fila][columna]).getNumMinasAlrededor();
                        System.out.print(" ["+ minasAlrededor +"/V] "); // Vida extra
                    } else if (casillas[fila][columna] instanceof CasillaVacia) {
                        int minasAlrededor = ((CasillaVacia) casillas[fila][columna]).getNumMinasAlrededor();
                        System.out.printf(" [ " + minasAlrededor + " ] "); // Número de minas alrededor
                    }
                }
            }
            System.out.println(); // Cambiar de línea
        }
    }

}








