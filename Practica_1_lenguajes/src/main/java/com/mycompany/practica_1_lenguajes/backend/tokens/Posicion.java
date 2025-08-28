/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica_1_lenguajes.backend.tokens;

/**
 *
 * @author antho
 */
public class Posicion {
    private final int LINEA;
    private final int COLUMNA;

    public Posicion(int linea, int columna) {
        this.LINEA = linea;
        this.COLUMNA = columna;
    }

    public int getLINEA() {
        return LINEA;
    }

    public int getCOLUMNA() {
        return COLUMNA;
    }

    @Override
    public String toString() {
        return "Línea " + LINEA + ", Columna " + COLUMNA;
    }

}
