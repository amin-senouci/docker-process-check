package com.example.simplerestapis.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ExecuteShellComand {


    public static List<String> run(String cmd) throws Exception {

        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command("bash", "-c", cmd);

        Process process = processBuilder.start();

        List<String> output = new ArrayList<>();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            output.add(line);
        }

        int exitVal = process.waitFor();
        if (exitVal == 0) {
            System.out.println("Success!");
            System.out.println(output);
            return output;
        } else {
            throw new Exception("The process name that you choose doesn't exist. Please check the syntaxe/name: " + cmd);
        }

    }
}