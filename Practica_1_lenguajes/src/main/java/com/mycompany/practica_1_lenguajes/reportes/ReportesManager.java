/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica_1_lenguajes.reportes;

import com.mycompany.practica_1_lenguajes.backend.tokens.Token;
import com.mycompany.practica_1_lenguajes.frontend.Reportes;
import java.util.ArrayList;

/**
 *
 * @author antho
 */
public class ReportesManager {
    
    private ArrayList<Token> tokensValidos = new ArrayList<>();
    private ArrayList<Token> tokensErrores = new ArrayList<>();
    private ArrayList<Token> todos;
    
    public ReportesManager(ArrayList<Token> tokens) {
        int i = 0;
        this.todos = tokens;
        while (i < tokens.size()) {
            if (tokens.get(i).getTipo().name().equals("ERROR")) {
                tokensErrores.add(tokens.get(i));
            } else {
                tokensValidos.add(tokens.get(i));
            }
            i++;
        }
    }
    
    public void abrirReportes() {
        Reportes repo = new Reportes();
        repo.llenarTokens(tokensValidos, tokensErrores, todos);
        repo.setVisible(true);
    }
    
}
