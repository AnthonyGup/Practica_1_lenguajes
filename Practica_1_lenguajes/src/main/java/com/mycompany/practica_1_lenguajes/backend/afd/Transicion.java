/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica_1_lenguajes.backend.afd;

/**
 *
 * @author antho
 */
public class Transicion {
    public final String origen;
    public final String destino;
    public final char simbolo;

    public Transicion(String origen, String destino, char simbolo) {
        this.origen = origen;
        this.destino = destino;
        this.simbolo = simbolo;
    }

}
