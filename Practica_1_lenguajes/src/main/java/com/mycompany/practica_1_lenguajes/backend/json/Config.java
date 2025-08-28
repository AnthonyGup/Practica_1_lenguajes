/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica_1_lenguajes.backend.json;

import java.util.List;

/**
 *
 * @author antho
 */
public class Config {

    private List<String> palabrasReservadas;
    private List<String> operadores;
    private List<String> puntuacion;
    private List<String> agrupacion;
    private String comentarioLinea;
    private String comentarioBloqueInicio;
    private String comentarioBloqueFin;

    // Getters
    public List<String> getPalabrasReservadas() {
        return palabrasReservadas;
    }

    public List<String> getOperadores() {
        return operadores;
    }

    public String getComentarioLinea() {
        return comentarioLinea;
    }

    public String getComentarioBloqueInicio() {
        return comentarioBloqueInicio;
    }

    public String getComentarioBloqueFin() {
        return comentarioBloqueFin;
    }

    public List<String> getPuntuacion() {
        return puntuacion;
    }

    public List<String> getAgrupacion() {
        return agrupacion;
    }

    // Setters
    public void setPalabrasReservadas(List<String> palabrasReservadas) {
        this.palabrasReservadas = palabrasReservadas;
    }

    public void setOperadores(List<String> operadores) {
        this.operadores = operadores;
    }

    public void setComentarioLinea(String comentarioLinea) {
        this.comentarioLinea = comentarioLinea;
    }

    public void setComentarioBloqueInicio(String comentarioBloqueInicio) {
        this.comentarioBloqueInicio = comentarioBloqueInicio;
    }

    public void setComentarioBloqueFin(String comentarioBloqueFin) {
        this.comentarioBloqueFin = comentarioBloqueFin;
    }

    public void setPuntuacion(List<String> puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setAgrupacion(List<String> agrupacion) {
        this.agrupacion = agrupacion;
    }
    
}
