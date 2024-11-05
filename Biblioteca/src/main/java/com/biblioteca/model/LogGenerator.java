package com.biblioteca.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogGenerator {
    private static LogGenerator instance; // Instância única no padrão singleton

    private LogGenerator() {
        // Construtor privado para evitar instanciar externamente
    }

    public static LogGenerator getInstance() {
        if (instance == null) {
            instance = new LogGenerator();
        }
        return instance;
        // Verifica se a instância existe, se for nula, cria uma instância  
    }

    // Método para gerar o log
    public static void generateLog(String message) throws IOException {
        Path path = Paths.get("C:/biblioteca/");

        if (!Files.exists(path)) {
            Files.createDirectory(path); //cria a pasta para armazenar o log
        }

        File log = new File("C:/biblioteca/logs.txt");

        if (!log.exists()) {
            log.createNewFile();  //cria o arquivo para armazenar as mensagens log
        }

        FileWriter fw = new FileWriter(log, true);  //
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(message);
        bw.newLine();

        bw.close();
        fw.close();
    }
}








