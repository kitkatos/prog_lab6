package org.example.common.console;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class ConsoleOutputHandler {
    // Основной UTF-8 вывод
    private final PrintStream utf8Out;
    private final PrintStream utf8Err;

    // Фолбэк-потоки (на случай ошибки)
    private final PrintStream fallbackOut;
    private final PrintStream fallbackErr;

    public ConsoleOutputHandler() {
        this.fallbackOut = System.out;
        this.fallbackErr = System.err;

        // Инициализируем UTF-8 потоки
        PrintStream utf8OutTemp;
        PrintStream utf8ErrTemp;
        try {
            utf8OutTemp = new PrintStream(System.out, true, StandardCharsets.UTF_8.name());
            utf8ErrTemp = new PrintStream(System.err, true, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            // Если UTF-8 не поддерживается, используем фолбэк
            utf8OutTemp = fallbackOut;
            utf8ErrTemp = fallbackErr;
        }
        this.utf8Out = utf8OutTemp;
        this.utf8Err = utf8ErrTemp;
    }

    public void printLine(String str) {
        try {
            utf8Out.println(str);
        } catch (Exception e) {
            fallbackOut.println(str);
        }
    }

    public void printString(String str) {
        try {
            utf8Out.print(str);
        } catch (Exception e) {
            fallbackOut.print(str);
        }
    }

    public void printError(String str) {
        try {
            utf8Err.println("ERROR: " + str);
        } catch (Exception e) {
            fallbackErr.println("ERROR: " + str);
        }
    }
}