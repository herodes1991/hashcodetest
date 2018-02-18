package com.hashcode.bbq;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {

    private BufferedReader bufferedReader;


    FileReader(String fileName) throws IOException {
        bufferedReader = Files.newBufferedReader(Paths.get(fileName));
    }

    String readOneLine() throws IOException {
        return bufferedReader.readLine();
    }

    Stream<String> readAllLines() {
        return bufferedReader.lines();
    }

}
