/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica_1_lenguajes.backend.tokens;

/**
 *
 * @author antho
 */
public class Token {

    private TipoToken tipo;
    private String lexema;
    private int numCaracter;
    private Posicion posicion;

    public Token(TipoToken tipo, String lexema, int numCaraacter, Posicion posicion) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.numCaracter = numCaraacter;
        this.posicion = posicion;
    }

    public TipoToken getTipo() {
        return tipo;
    }

    public String getLexema() {
        return lexema;
    }

    public int getNumCaracter() {
        return numCaracter;
    }

    public Posicion getPosicion() {
        return posicion;
    }
}