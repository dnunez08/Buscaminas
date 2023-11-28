/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.awt.Image;

public class Partida implements Serializable{
    private int numCasillasDescubiertas;
    private int puntos;
    private Resultado resultado;
    private Tablero tablero;
    private Casilla[][] casillas;
    private Nivel nivelSeleccionado;
    private int numVidasAcumuladas;
    private ArrayList<Jugador> listaJugadores;



    public Partida() {
        this.numCasillasDescubiertas = 0;
        this.puntos = 0;
        this.listaJugadores= new ArrayList<>();
    }

    public int getNumCasillasDescubiertas() {
        return numCasillasDescubiertas;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public int getPuntos() {return puntos;}

    public void incrementarCasillasDescubiertas() {
        numCasillasDescubiertas++;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public boolean esPartidaFinalizada() {
        return resultado != null;
    }

    public Tablero getTablero() { return tablero; }

    public Nivel getNivelSeleccionado() { return nivelSeleccionado; }

    public Jugador crearJugador(Scanner scanner) {
        System.out.print("Ingresa tu nombre de jugador: ");
        String nombreJugador = scanner.nextLine();
        Image fotoJugador = cargarImagen(scanner);
        Jugador nuevoJugador = new Jugador(nombreJugador, fotoJugador);
        // Cargar la lista actual de jugadores
        listaJugadores = Jugador.cargarJugadores();
        // Agregar el nuevo jugador a la lista
        if(!listaJugadores.contains(nuevoJugador)) {
            listaJugadores.add(nuevoJugador);
        }
        // Guardar la lista actualizada de jugadores
        Jugador.guardarJugadores(listaJugadores);
        return nuevoJugador;
    }

    public Image cargarImagen(Scanner scanner) {
        System.out.print("¿Quieres ingresar una foto de perfil? (Si/No): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("Si")) {
            System.out.print("Ingresa la URL de tu foto de perfil: ");
            String urlFoto = scanner.nextLine();
            if (urlFoto == null || urlFoto.isEmpty()) {
                System.out.println("No se ingresó una URL válida.");
                return null;
            }

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

    public void mostrarPerfil(Jugador jugador){
        Scanner scanner = new Scanner(System.in);
        System.out.print("¿Deseas ver tu perfil? Pulsa 1 para verlo, si no pulsa cualquier otra tecla: \n");
        String respuesta = scanner.next();
        if (respuesta.equalsIgnoreCase("1")) {
            System.out.println("*********************");
            System.out.println("PERFIL DE JUGADOR");
            if(jugador.getFoto() != null){
                System.out.println(jugador.getFoto());
            }
            System.out.println("Nombre: " + jugador.getNombre() + "\n" +
                    "Partidas Jugadas: " +jugador.getPartidasJugadas() + "\n" +
                    "Puntos Totales: " + jugador.getPuntosTotales() + "\n" +
                    "Partidas Ganadas: " + jugador.getPartidasGanadas() + "\n" +
                    "*********************\n");
        }
    }

    public void jugarPartida(Jugador jugador) {
        Scanner scanner = new Scanner(System.in);
        Partida partida;
        mostrarPerfil(jugador);
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

                    if (partida.getNumCasillasDescubiertas() == tablero.getDimX() * tablero.getDimY() - tablero.getNumMinas() - tablero.getNumVidas()) {
                        System.out.println("¡Has ganado! Todas las casillas seguras han sido descubiertas.");
                        partida.setResultado(Resultado.GANADA);
                        jugador.setPartidasGanadas(jugador.getPartidasGanadas() + 1);
                        // Calcular puntos ganados
                        int puntosGanados = partida.calcularPuntosGanados(nivelSeleccionado, tablero.getNumMinas());
                        System.out.println("Puntos obtenidos: " + puntosGanados);
                        jugador.setPuntosTotales(jugador.getPuntosTotales() + puntosGanados);
                        // Guardar el jugador actualizado
                        listaJugadores = Jugador.cargarJugadores();
                        listaJugadores.remove(jugador); // Eliminar jugador existente
                        listaJugadores.add(jugador);
                        Jugador.guardarJugadores(listaJugadores);
                        break;
                    }
                }
                if (partida.numVidasAcumuladas <= 0) {
                    partida.setResultado(Resultado.PERDIDA);

                    System.out.println("Puntos obtenidos: 0");
                }
                // Mostrar el resultado de la partida
                Resultado resultado = partida.getResultado();
                System.out.println("Resultado de la partida: " + resultado);

                if(partida.esPartidaFinalizada()){
                    jugador.setPartidasJugadas(jugador.getPartidasJugadas() + 1);
                    listaJugadores = Jugador.cargarJugadores();
                    listaJugadores.remove(jugador); // Eliminar jugador existente
                    listaJugadores.add(jugador);
                    Jugador.guardarJugadores(listaJugadores);
                }

            }

            // Preguntar si el jugador quiere jugar de nuevo
            System.out.print("¿Deseas jugar de nuevo? Pulsa 1 para jugar otra partida, para no jugar más pulsa cualquier otra tecla: ");
            String respuesta = scanner.next();
            if (!respuesta.equalsIgnoreCase("1")) {
                break;
            }
        } while (true);

        // Guardar la partida
        partida.guardarPartida("partida_guardada.bin");

        // Cerrar el Scanner
        scanner.close();
    }

    public void mostrarNivelesDisponibles() {
        System.out.println("Niveles disponibles:\n" +
                "1. Principiante (8x8, 10 minas, 3 vidas)\n" +
                "2. Intermedio (16x16, 40 minas, 6 vidas)\n" +
                "3. Experto (16x30, 99 minas, 10 vidas)\n" +
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

    public void guardarPartida(String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(this);  // Guarda la instancia actual de Partida en el archivo binario
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Partida cargarPartida(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (Partida) ois.readObject();  // Carga la instancia de Partida desde el archivo binario
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Jugador cargarOCrearJugador(Scanner scanner) {
        System.out.print("¿Ya tienes un jugador existente? (Si/No): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("Si")) {
            System.out.print("Ingresa el nombre del jugador existente: ");
            String nombreJugadorExistente = scanner.nextLine();
            // Cargar la lista de jugadores desde el archivo
            listaJugadores = Jugador.cargarJugadores();
            // Buscar el jugador existente por nombre
            for (Jugador jugadorExistente : listaJugadores) {
                if (jugadorExistente.getNombre().equalsIgnoreCase(nombreJugadorExistente)) {
                    System.out.println("¡Bienvenido de nuevo, " + jugadorExistente.getNombre() + "!");
                    return jugadorExistente;
                }
            }
            // Imprimir mensaje si no se encontró el jugador existente
            System.out.println("No se encontró el jugador existente. Creando un nuevo jugador.");
            return crearJugador(scanner);
        } else {
            return crearJugador(scanner);
        }
    }

}