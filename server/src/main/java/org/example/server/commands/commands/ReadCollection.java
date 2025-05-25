package org.example.server.commands.commands;

import org.example.server.commands.Command;
import org.example.server.commands.CommandManager;
import org.example.server.commands.Command;
import org.example.common.console.*;
import org.example.common.ApplicationStatus;
import org.example.common.model.Movie;
import org.example.server.collection.*;
import org.example.common.console.movieInput.*;
import org.example.server.file.FileReader;
import org.example.server.file.ParserXML;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

public class ReadCollection extends Command {
    CollectionManager collection;
    ConsoleOutputHandler console;
    ParserXML parser;
    FileReader reader;
    public ReadCollection(CollectionManager collection, ConsoleOutputHandler console, ParserXML parser, FileReader reader){
        super("read", "прочитать коллекцию из файл");
        this.collection = collection;
        this.console = console;
        this.parser = parser;
        this.reader = reader;
    }
    @Override
    public ApplicationStatus execute(String arg) throws Exception{
        try {
            if (arg.isEmpty()) {
                console.printError("Аргумент не указан");
                return ApplicationStatus.ERROR;
            }
            List<String> file = reader.readFile(arg);
            TreeSet<Movie> xmlMovie = parser.getCollectionFromXML(file);
            console.printLine("Коллекция прочитана");
            for (Movie movie : xmlMovie) {
                collection.addElem(movie);
                console.printLine("Элеммент добавлен");
            }
            return ApplicationStatus.RUNNING;
        } catch (IOException e) {
            console.printError("Ошибка работы FileReader: " + e.getMessage());
            return ApplicationStatus.ERROR;
        }
    }
}
