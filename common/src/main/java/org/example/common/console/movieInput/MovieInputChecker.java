package org.example.common.console.movieInput;

import org.example.common.console.ConsoleOutputHandler;
import org.example.common.console.exception.EmptyInputException;
import org.example.common.console.exception.OutOfRangeException;
import org.example.common.console.exception.*;

public class MovieInputChecker {
    private final ConsoleOutputHandler coh;
    public MovieInputChecker(ConsoleOutputHandler coh) {
        this.coh = coh;
    }

    public <T> T readAndValidate(ThrowingSupplier<String> reader, ThrowingFunction<String, T> validator){
        while (true) {
            try {
                String input = reader.get();
                return validator.apply(input);
            } catch (EmptyInputException | OutOfRangeException | IllegalArgumentException e) {
                coh.printError(e.getMessage());
            } catch (Exception e) {
                coh.printError("Неизвестная ошибка: " + e.getMessage());
            }

        }
    }
}
