/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica_1_lenguajes.backend.json;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class ConfigLoader {

    public Config cargarConfig() {
        Gson gson = new Gson();
        try (InputStream input = getClass().getResourceAsStream("/config.json"); Reader reader = new InputStreamReader(input, "UTF-8")) {

            return gson.fromJson(reader, Config.class);

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

}
