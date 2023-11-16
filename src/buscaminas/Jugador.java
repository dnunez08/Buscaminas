/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.awt.Image;

public class Jugador {
    private String nombre;
    private Image foto;
    private int puntosTotales;
    private int partidasGanadas;
    private int vidas;

    public Jugador(String nombre, Image foto) {
        this.nombre = nombre;
        this.foto = foto;
        this.puntosTotales = 0;
        this.partidasGanadas = 0;
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
    
    public int getVidas() {
        return vidas;
    }

    public void agregarVida() {
        // Incrementa el número de vidas en 1.
        vidas++;
    }

    public void mostrarVidas() {
        System.out.println("Vidas restantes: " + vidas);
    }

    public boolean equals(Object o){
        if(this==o)return true;
        if(o==null)return false;
        if(getClass()!=o.getClass()) return false;
        Jugador j = (Jugador) o;
        return (this.nombre.equals(j.nombre) &&
                this.partidasGanadas == j.partidasGanadas &&
                this.puntosTotales == j.puntosTotales);
    }
}

