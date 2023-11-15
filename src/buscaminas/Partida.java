/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.Scanner;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author danus
 */
public class Partida {
    private boolean vidaGastada;
    private int numCasillasDescubiertas;
    private int puntos;
    private boolean vidaEncontrada;
    private Resultado resultado;
    private Tablero tablero;
    private Casilla[][] casillas;
    private Nivel nivelSeleccionado;
    private int numVidasAcumuladas;



    public Partida() {
        this.vidaGastada = false;
        this.numCasillasDescubiertas = 0;
        this.puntos = 0;
        this.vidaEncontrada = false;
    }

    public boolean isVidaGastada() {
        return vidaGastada;
    }

    public int getNumCasillasDescubiertas() {
        return numCasillasDescubiertas;
    }

    public int getPuntos() {
        return puntos;
    }

    public boolean isVidaEncontrada() {
        return vidaEncontrada;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void gastarVida() {
        vidaGastada = true;
    }

    public void incrementarCasillasDescubiertas() {
        numCasillasDescubiertas++;
    }

    public void sumarPuntos(int puntosGanados) {
        puntos += puntosGanados;
    }

    public void encontrarVida() {
        vidaEncontrada = true;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public boolean esPartidaFinalizada() {
        return resultado != null;
    }

    public Tablero getTablero() { return tablero; }

    public Nivel getNivelSeleccionado() { return nivelSeleccionado; }
    
    public int calcularPuntosGanados(Nivel nivel, int numBombas) {
        int puntos = 0;
        switch (nivel) {
            case PRINCIPIANTE:
                puntos = 3;
                break;
            case INTERMEDIO:
                puntos = 6;
                break;
            case EXPERTO:
                puntos = 10;
                break;
            case PERSONALIZADO:
                if (numBombas >= 1 && numBombas <= 10) {
                    puntos = 3;
                } else if (numBombas >= 11 && numBombas <= 40) {
                    puntos = 6;
                } else if (numBombas >= 41 && numBombas <= 99) {
                    puntos = 10;
                } else if (numBombas > 100) {
                    puntos = 15;
                }
                break;
        }

        return puntos;
    }

    public Jugador crearJugador(Scanner scanner) {
        System.out.print("Ingresa tu nombre de jugador: ");
        String nombreJugador = scanner.nextLine();

        Image fotoJugador = cargarImagen(scanner);
        return new Jugador(nombreJugador, fotoJugador);
    }

    public Image cargarImagen(Scanner scanner) {
        System.out.print("¿Quieres ingresar una foto de perfil? (Si/No): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("Si")) {
            System.out.print("Ingresa la URL de tu foto de perfil: ");
            String urlFoto = scanner.nextLine();

            try {
                File archivoImagen = new File(urlFoto);
                return ImageIO.read(archivoImagen);
            } catch (IOException e) {
                System.out.println("Error al leer la imagen: " + e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }


    public void jugarPartida() {
        Scanner scanner = new Scanner(System.in);
        Partida partida;

        do {
            mostrarNivelesDisponibles();
            System.out.print("Elige un nivel (1/2/3/4): ");
            int nivelElegido = scanner.nextInt();

            if (!inicializarPartida(nivelElegido)) {
                System.out.println("Nivel no válido. Por favor, elige un nivel válido.");
                continue;
            } else {
                partida = new Partida();
                partida.numVidasAcumuladas = 1;

                while (!partida.esPartidaFinalizada() && partida.numVidasAcumuladas > 0) {
                    tablero.mostrarTablero(casillas);

                    // Mostrar el número de vidas solo si hay vidas disponibles
                    if (partida.numVidasAcumuladas > 0) {
                        System.out.println("Número de vidas: " + partida.numVidasAcumuladas);
                    }

                    System.out.print("Ingresa la fila para descubrir (1-" + tablero.getDimX() + "): ");
                    int fila = scanner.nextInt();
                    System.out.print("Ingresa la columna para descubrir (1-" + tablero.getDimY() + "): ");
                    int columna = scanner.nextInt();

                    if (fila >= 1 && fila <= tablero.getDimX() && columna >= 1 && columna <= tablero.getDimY()) {
                        Casilla casillaElegida = casillas[fila - 1][columna - 1];

                        if (!casillaElegida.isOculta()) {
                            System.out.println("¡La casilla ya ha sido descubierta!");
                        } else {
                            casillaElegida.descubrir();

                            // Verificar si la casilla contiene una mina
                            if (casillaElegida instanceof CasillaConMina) {
                                // Restar una vida si hay vidas acumuladas
                                if (partida.numVidasAcumuladas > 0) {
                                    partida.numVidasAcumuladas--;
                                    tablero.mostrarTablero(casillas);
                                    System.out.println("¡Has encontrado una mina! Te quedan " + partida.numVidasAcumuladas + " vidas.");
                                } else {
                                    break;
                                }
                            } else if (casillaElegida instanceof CasillaConVida) {
                                // Incrementar el número de vidas acumuladas
                                partida.numVidasAcumuladas++;
                                System.out.println("¡Has encontrado una vida extra! Ahora tienes " + partida.numVidasAcumuladas + " vidas.");
                            } else {
                                // Actualizar las estadísticas de la partida
                                partida.incrementarCasillasDescubiertas();
                            }
                        }
                    } else {
                        System.out.println("Selección de casilla no válida. Ingresa coordenadas válidas.");
                    }

                    if (partida.getNumCasillasDescubiertas() == tablero.getDimX() * tablero.getDimY() - tablero.getNumMinas()) {
                        System.out.println("¡Has ganado! Todas las casillas seguras han sido descubiertas.");
                        partida.setResultado(Resultado.GANADA);

                        // Calcular puntos ganados
                        int puntosGanados = partida.calcularPuntosGanados(nivelSeleccionado, tablero.getNumMinas());
                        System.out.println("Puntos obtenidos: " + puntosGanados);
                        break;
                    }
                }

                if (partida.numVidasAcumuladas <= 0) {
                    partida.setResultado(Resultado.PERDIDA);
                }

                System.out.println("Puntos obtenidos: 0");

                // Mostrar el resultado de la partida
                Resultado resultado = partida.getResultado();
                System.out.println("Resultado de la partida: " + resultado);
            }

            // Preguntar si el jugador quiere jugar de nuevo
            System.out.print("¿Deseas jugar de nuevo? Pulse 1 para jugar otra partida, para no jugar más pulse cualquier otra tecla: ");
            String respuesta = scanner.next();
            if (!respuesta.equalsIgnoreCase("1")) {
                break;
            }
        } while (true);

        scanner.close();
    }



    public void mostrarNivelesDisponibles() {
        System.out.println("Niveles disponibles:\n" +
                "1. Principiante (8x8, 10 minas)\n" +
                "2. Intermedio (16x16, 40 minas)\n" +
                "3. Experto (16x30, 99 minas)\n" +
                "4. Personalizado");
    }

    public boolean inicializarPartida(int nivelElegido) {
        Scanner scanner = new Scanner(System.in);
        if (nivelElegido == 1) {
            tablero = new Tablero(8, 8, 10, 3);
            nivelSeleccionado = Nivel.PRINCIPIANTE;
        } else if (nivelElegido == 2) {
            tablero = new Tablero(16, 16, 40, 6);
            nivelSeleccionado = Nivel.INTERMEDIO;
        } else if (nivelElegido == 3) {
            tablero = new Tablero(16, 30, 99, 10);
            nivelSeleccionado = Nivel.EXPERTO;
        } else if (nivelElegido == 4) {
            System.out.print("Ingresa el número de filas del tablero personalizado: ");
            int filas = scanner.nextInt();
            System.out.print("Ingresa el número de columnas del tablero personalizado: ");
            int columnas = scanner.nextInt();
            System.out.print("Ingresa el número de minas: ");
            int minas = scanner.nextInt();
            System.out.print("Ingresa el número de vidas: ");
            int vidas = scanner.nextInt();
            tablero = new Tablero(filas, columnas, minas, vidas);
            nivelSeleccionado = Nivel.PERSONALIZADO;
        } else {
            return false;
        }
        casillas = tablero.inicializarTablero();
        return true;
    }

}

