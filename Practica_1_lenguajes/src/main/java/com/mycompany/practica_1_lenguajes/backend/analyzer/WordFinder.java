/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica_1_lenguajes.backend.analyzer;

import com.mycompany.practica_1_lenguajes.backend.TextPainter;

/**
 *
 * @author antho
 */
public class WordFinder {
    
    private final TextPainter PAINTER;
    
    public WordFinder(TextPainter painter) {
        this.PAINTER = painter;
    }
    
    public void buscarPalabra(String archivo, String palabraBuscada) {
        String texto = archivo.replace("\r\n", "\n");
        int i = 0;
        String lexema;
        while (i < texto.length()) {
            if (texto.charAt(i) == palabraBuscada.charAt(0)) {
                int j = 0;
                int inicio = i;
                while (j < palabraBuscada.length() && (texto.charAt(i) == palabraBuscada.charAt(j))) {
                    i++;
                    j++;
                }

                lexema = texto.substring(inicio, i);

                if (palabraBuscada.equals(lexema)) {
                    PAINTER.pintarFondo(inicio, lexema.length());
                }
            }
            i++;
        }
    }
    
}
