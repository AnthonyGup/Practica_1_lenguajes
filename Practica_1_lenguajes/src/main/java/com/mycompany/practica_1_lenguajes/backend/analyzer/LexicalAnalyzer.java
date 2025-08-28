/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica_1_lenguajes.backend.analyzer;

import com.mycompany.practica_1_lenguajes.backend.TextPainter;
import com.mycompany.practica_1_lenguajes.backend.json.Config;
import com.mycompany.practica_1_lenguajes.backend.tokens.Posicion;
import com.mycompany.practica_1_lenguajes.backend.tokens.PositionGenerator;
import com.mycompany.practica_1_lenguajes.backend.tokens.TipoToken;
import com.mycompany.practica_1_lenguajes.backend.tokens.Token;
import java.util.ArrayList;

/**
 *
 * @author antho
 */
public class LexicalAnalyzer {

    private final Config CONFIG;
    private ArrayList<Token> TOKENS;
    
    public LexicalAnalyzer(Config config) {
        this.CONFIG = config;
    }

    /**
     * Analiza el texto caracter por caracter hasta encontrar alguno de los
 TOKENS validos
     *
     * @param archivo
     * @param textPane
     */
    public void analizar(String archivo, javax.swing.JTextPane textPane) {
        TOKENS = new ArrayList<>();
        
        //Normaliza el texto, pasa los saltos de linea de windows a los otros
        String texto = archivo.replace("\r\n", "\n");

        int i = 0;

        while (i < texto.length()) {

            String tipo = "ERROR";
            String lexema = "";

            char actual = texto.charAt(i);
            
            //Si no hay nada escrito continua al siguiente caracter
            if (Character.isWhitespace(actual)) {
                i++;
                continue;
            }

            int inicio = i;

            if (Character.isLetter(actual)) {

                while (i < texto.length() && !Character.isWhitespace(texto.charAt(i)) && (texto.charAt(i) != '\n')) {
                    i++;
                }

                lexema = texto.substring(inicio, i);

                if (isPalabraReservada(lexema)) {
                    tipo = "PALABRAS_RESERVADAS";
                } else if (isIdentificador(lexema)) {
                    tipo = "IDENTIFICADOR";
                }

            } else if (Character.isDigit(actual)) {
                while (i < texto.length() && !Character.isWhitespace(texto.charAt(i))) {
                    i++;
                }

                lexema = texto.substring(inicio, i);

                if (isNumero(lexema)) {
                    tipo = "NUMERO";
                } else if (isDecimal(lexema)) {
                    tipo = "DECIMAL";
                }
            } else if (actual == '"') {
                i++;
                while (i < texto.length() && texto.charAt(i) != '"') {
                    i++;
                }

                if (i < texto.length()) {
                    i++;

                    lexema = texto.substring(inicio, i);

                    if (isCadena(lexema)) {
                        tipo = "CADENA";
                    }
                }
            } else if (actual == '/') {
                if (i < texto.length() - 1) {
                    switch (texto.charAt(i + 1)) {
                        case '/' -> {
                            i += 2;
                            while (i < texto.length() && texto.charAt(i) != '\n') {
                                i++;
                            }   lexema = texto.substring(inicio, i);
                            tipo = "COMENTARIO_LINEA";
                        }
                        case '*' -> {
                            i += 2;
                            while (i < texto.length() - 1 && !(texto.charAt(i) == '*' && texto.charAt(i + 1) == '/')) {
                                i++;
                            }   //Ver esto
                            if (i < texto.length() - 1 && texto.charAt(i) == '*' && texto.charAt(i + 1) == '/') {
                                i += 2; // avanzar completamente sobre el cierre
                            }   lexema = texto.substring(inicio, i);
                            if (isComentarioBloque(lexema)) {
                                tipo = "COMENTARIO_BLOQUE";
                            }
                        }
                        default -> {
                            lexema = "" + actual;
                            tipo = "OPERADOR";
                        }
                    } 
                } else {
                    lexema = "" + actual;
                    tipo = "OPERADOR";
                }
            } else {
                lexema = "" + actual;
                if (isPuntuacion(lexema)) {
                    tipo = "PUNTUACION";
                } else if (isOperador(lexema)) {
                    tipo = "OPERADOR";
                } else if (isAgrupacion(lexema)) {
                    tipo = "AGRUPACION";
                }
            }
            PositionGenerator generadorPosicion = new PositionGenerator();
            Posicion positon = generadorPosicion.calcularPosicion(inicio, evaluarSplits(texto));
            Token token = new Token(TipoToken.valueOf(tipo), lexema, inicio, positon);
            TOKENS.add(token);
            TextPainter painter = new TextPainter(textPane);
            painter.pintarToken(token);
            i++;
        }
    }

    /**
     * Revisa si existe este lexema en las palabras reservadas
     *
     * @param token el lexema evaluado
     * @return true si es una palabra reservada
     */
    private boolean isPalabraReservada(String token) {
        return CONFIG.getPalabrasReservadas().contains(token);
    }

    private boolean isPuntuacion(String lexema) {
        return CONFIG.getPuntuacion().contains(lexema);
    }

    private boolean isOperador(String lexema) {
        return CONFIG.getOperadores().contains(lexema);
    }

    private boolean isAgrupacion(String lexema) {
        return CONFIG.getAgrupacion().contains(lexema);
    }

    /**
     * Evalua si el lexema es un identificador, no puede ser Palabras Reservadas
     *
     * @param lexema La cadena de caracteres a evaluar
     * @return true si es validado como identificador y false si no lo es
     */
    private boolean isIdentificador(String lexema) {
        int i = 0;

        //Se asegura de que el lexema no sea una palabra reservada
        if (isPalabraReservada(lexema)) {
            return false;
        }

        while (i < lexema.length()) {
            char actual = lexema.charAt(i);
            if (!Character.isDigit(actual) && !Character.isLetter(actual)) {
                return false;
            }
            i++;
        }

        return true;
    }

    /**
     * Evalua que el lexema proporcionado sea una cadena de digitos sin otro
     * caracter
     *
     * @param lexema la cadena de caracteres a evaluar
     * @return true si solo contiene digitos, false si contiene algo mas que
     * digitos
     */
    private boolean isNumero(String lexema) {
        int i = 0;

        while (i < lexema.length()) {
            char actual = lexema.charAt(i);
            if (!Character.isDigit(actual)) {
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * Evalua que texto sea un numero o mas seguido de un punto seguido de un
     * numero o mas
     *
     * @param lexema La cadena de texto que deseamos evaluar
     * @return true si es un digito de la forma que buscamos
     */
    private boolean isDecimal(String lexema) {
        int i = 0;
        int contadorPuntos = 0;

        while (i < lexema.length()) {
            char actual = lexema.charAt(i);

            if (!Character.isDigit(actual) && (actual != '.')) {
                return false;
            }

            if (actual == '.') {
                contadorPuntos++;
                if (contadorPuntos > 1) {
                    return false;
                }
            }
            i++;
        }

        //Evalua que no comience o termine solo con punto
        if (lexema.startsWith(".") || lexema.endsWith(".")) {
            return false;
        }

        return true;
    }

    private boolean isCadena(String lexema) {

        int inicio = 0;
        int i = 0;

        while (i < lexema.length() && lexema.charAt(i) != '"') {
            i++;
        }

        if (lexema.charAt(inicio) == '"' && lexema.charAt(i) == '"') {
            return true;
        }

        return false;
    }

    private boolean isComentarioBloque(String lexema) {
        return lexema.charAt(0) == '/' && lexema.charAt(1) == '*' && lexema.charAt(lexema.length() - 2) == '*'
                && lexema.charAt(lexema.length() - 1) == '/';
    }

    private ArrayList<Integer> evaluarSplits(String texto) {
        ArrayList<Integer> saltoLinea = new ArrayList<>();
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == '\n') {
                saltoLinea.add(i);
            }
        }
        return saltoLinea;
    }

    public ArrayList<Token> getTOKENS() {
        return TOKENS;
    }

}
