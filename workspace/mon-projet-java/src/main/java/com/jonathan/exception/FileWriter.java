package com.jonathan.exception;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {

    public static void saveDataFinally() {
        try (FileOutputStream fos =   new FileOutputStream("save.data", true)){
            fos.write("ARTGTA".getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        saveDataFinally();
    }
}
