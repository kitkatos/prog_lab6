package org.example.server.file;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileWriter {
    public void writeToFile(String line, String fileName) throws IOException {
            byte[] byteLine = line.getBytes(StandardCharsets.UTF_8);
            try (
                    FileOutputStream fos = new FileOutputStream(fileName);
                    BufferedOutputStream bos = new BufferedOutputStream(fos, byteLine.length);
            ) {

                bos.write(byteLine);
                bos.flush();
            }

    }
}
