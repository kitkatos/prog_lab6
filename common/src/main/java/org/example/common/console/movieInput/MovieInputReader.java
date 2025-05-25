package org.example.common.console.movieInput;

import org.example.common.console.exception.EmptyInputException;
import org.example.common.console.ConsoleInputHandler;
import org.example.common.console.ConsoleOutputHandler;

public class MovieInputReader {
    private final ConsoleInputHandler cih;
    private final ConsoleOutputHandler coh;

    public MovieInputReader(ConsoleInputHandler cih, ConsoleOutputHandler coh) {
        this.cih = cih;
        this.coh = coh;
    }

    public String readName() throws EmptyInputException{
        coh.printString("Введите название фильма:");
        return readNonEmptyInput();
    }

    public String readOscarsCount() throws EmptyInputException{
        coh.printString("Введите количество полученных Оскаров:");
        return readNonEmptyInput();
    }

    public String readUsaBoxOffice() throws EmptyInputException{
        coh.printString("Введите сумму сборов в США:");
        return readNonEmptyInput();
    }

    public String readGenre(){
        coh.printString("Введите жанр фильма (ACTION, DRAMA, MUSICAL, THRILLER, FANTASY) или оставьте пустым:");
        return readEmptyInput();
    }

    public String readMpaaRating(){
        coh.printString("Введите рейтинг MPAA (G, PG, R, NC_17) или оставьте пустым:");
        return readEmptyInput();
    }

    public String readCoordinateX() throws EmptyInputException{
        coh.printString("Введите координату X:");
        return readNonEmptyInput();
    }

    public String readCoordinateY() throws EmptyInputException{
        coh.printString("Введите координату Y:");
        return readNonEmptyInput();
    }

    public String PersonIsEmpty() throws EmptyInputException{
        coh.printLine("Хотите, чтобы поле Режисер осталось пустым? введите yes или no");
        return readNonEmptyInput();
    }


    public String readDirectorName() throws EmptyInputException{
        coh.printString("Введите имя режиссера:");
        return readNonEmptyInput();
    }

    public String readDirectorBirthday() throws EmptyInputException{
        coh.printString("Введите день рождения режиссера (формат: yyyy-MM-dd):");
        return readNonEmptyInput();
    }

    public String readDirectorHeight() throws EmptyInputException{
        coh.printString("Введите рост режиссера:");
        return readNonEmptyInput();
    }

    public String readDirectorWeight() throws EmptyInputException{
        coh.printString("Введите вес режиссера:");
        return readNonEmptyInput();
    }

    public String readDirectorPassportID() throws EmptyInputException{
        coh.printString("Введите паспортный ID режиссера:");
        return readNonEmptyInput();
    }

    private String readNonEmptyInput() throws EmptyInputException{
        String input = cih.readTrimLine();
        if (input.isEmpty()) {
            throw new EmptyInputException("Поле не может быть пустым. Введите корректные данные");
        } else {
            return input;
        }
    }

    private String readEmptyInput(){
        return cih.readTrimLine();
    }
}

