/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;


import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.Serializable;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Jugador implements Serializable {
    private String nombre;
    private Image foto;
    private int puntosTotales;
    private int partidasGanadas;
    private int partidasJugadas;
    private int vidas;

    public Jugador(String nombre, Image foto) {
        this.nombre = nombre;
        this.foto = foto;
        this.puntosTotales = 0;
        this.partidasGanadas = 0;
        this.partidasJugadas = 0;
        this.vidas = 1;
    }

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Image getFoto() { return foto; }

    public void setFoto(Image foto) { this.foto = foto; }
    
    public int getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(int puntosTotales) {
        this.puntosTotales = puntosTotales;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public int getPartidasJugadas() {return partidasJugadas;}

    public void setPartidasJugadas(int partidasJugadas) {this.partidasJugadas = partidasJugadas;}

    public int getVidas() {
        return vidas;
    }

    public void agregarVida() {vidas++;}


    @Override
    public String toString() {
        return "Jugador: "  + nombre  +
                ", Partidas Jugadas: " + partidasJugadas +
                ", Puntos Totales: " + puntosTotales +
                ", Partidas Ganadas: " + partidasGanadas;
    }

    public boolean equals(Object o){
        if(this==o)return true;
        if(o==null)return false;
        if(getClass()!=o.getClass()) return false;
        Jugador j = (Jugador) o;
        return (this.nombre.equals(j.nombre));
    }

    public static ArrayList<Jugador> cargarJugadores() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("jugadores.dat"))) {
            ArrayList<Jugador> jugadores = (ArrayList<Jugador>) ois.readObject();
            // Validar y limpiar la lista de jugadores
            jugadores.removeIf(jugador -> jugador.getNombre() == null || jugador.getNombre().isEmpty());
            return jugadores;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de jugadores no encontrado. Creando una nueva lista.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar la lista de jugadores: " + e.getMessage());
        }
        return new ArrayList<>();
    }


    public static void guardarJugadores(ArrayList<Jugador> nuevosJugadores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("jugadores.dat"))) {
            // Borrar el contenido existente del archivo
            oos.reset();
            // Escribir el nuevo ArrayList en el archivo
            oos.writeObject(nuevosJugadores);
            System.out.println("Jugadores guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los jugadores: " + e.getMessage());
        }
    }


}