/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica_1_lenguajes.backend.tokens;

import java.util.ArrayList;

/**
 *
 * @author antho
 */
public class PositionGenerator {

    public Posicion calcularPosicion(int indice, ArrayList<Integer> saltosDeLinea) {
        int linea = 0;

        if (saltosDeLinea == null || saltosDeLinea.isEmpty()) {
            return new Posicion(1, indice);
        }

        // Buscar la última línea cuyo salto de línea está antes del índice
        for (int i = 0; i < saltosDeLinea.size(); i++) {
            if (indice > saltosDeLinea.get(i)) {
                linea = i;
            } else {
                break;
            }
        }

        int columna = indice - saltosDeLinea.get(linea);
        return new Posicion(linea + 1, columna);
    }
}
