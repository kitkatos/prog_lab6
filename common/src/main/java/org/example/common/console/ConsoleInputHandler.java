package org.example.common.console;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleInputHandler {
    private Scanner scanner;

    public ConsoleInputHandler() {
        resetScanner();  // Инициализация при создании
    }

    public String readTrimLine() {
        try {
            if (scanner.hasNextLine()) {
                return scanner.nextLine().trim();
            } else {
                return null;
                }
        } catch (IllegalStateException e) {
            return null;
        }
    }

    private void resetScanner() {
        if (scanner != null) {
            scanner.close();
        }
        scanner = new Scanner(System.in);
    }
}
