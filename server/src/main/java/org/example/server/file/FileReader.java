package org.example.server.file;

import java.io.IOException;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader{
    public List<String> readFile(String fileName) throws IOException {
        Scanner scanner = new Scanner(new File(fileName));
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()){
            lines.add(scanner.nextLine());
        }
        scanner.close();
        return lines;
    }

}
