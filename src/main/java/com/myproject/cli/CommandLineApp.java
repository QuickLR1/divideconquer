package com.myproject.cli;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class CommandLineApp {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java CommandLineApp --algo=<name> --output=<file.csv>");
            return;
        }

        String algo = null;
        String outputFile = null;

        for (String arg : args) {
            if (arg.startsWith("--algo=")) {
                algo = arg.substring("--algo=".length());
            } else if (arg.startsWith("--output=")) {
                outputFile = arg.substring("--output=".length());
            }
        }

        if (algo == null || outputFile == null) {
            System.out.println("Missing arguments! Example: --algo=deterministic --output=result.csv");
            return;
        }


        int[] data = {5, 3, 8, 2, 9};
        Arrays.sort(data);
        saveToCSV(data, outputFile);

        System.out.println("Algorithm: " + algo);
        System.out.println("Output saved to " + outputFile);
    }

    private static void saveToCSV(int[] data, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < data.length; i++) {
                writer.write(String.valueOf(data[i]));
                if (i < data.length - 1) {
                    writer.write(",");
                }
            }
            writer.write("\n");
        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
    }
}
