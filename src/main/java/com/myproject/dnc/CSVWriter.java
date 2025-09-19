package com.myproject.dnc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CSVWriter {
    private final String path;

    public CSVWriter(String path) {
        this.path = path;
    }
    public void writeHeader(String header)throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, false))) {
            pw.println(header);
        }
    }
    public void append(String line) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            pw.println(line);
        }
    }
}
