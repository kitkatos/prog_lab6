package org.example.server.commands.commands;

import org.example.server.commands.Command;
import org.example.common.console.*;
import org.example.common.ApplicationStatus;
import org.example.common.model.Movie;
import org.example.server.collection.*;
import org.example.server.file.FileWriter;
import org.example.server.file.ParserXML;

import java.io.IOException;
import java.util.TreeSet;

public class Save extends Command {
    CollectionManager collection;
    ConsoleOutputHandler console;
    ParserXML parser;
    FileWriter writer;
    public Save(CollectionManager collection, ConsoleOutputHandler console, ParserXML parser, FileWriter writer){
        super("save", "сохранить коллекцию в файл");
        this.collection = collection;
        this.console = console;
        this.parser = parser;
        this.writer = writer;
    }
    @Override
    public ApplicationStatus execute(String arg) throws Exception{
        try {
            if (arg.isEmpty()) {
                console.printError("Аргумент не указан");
                return ApplicationStatus.ERROR;
            }
            console.printLine("Выполнение сохранения");
            console.printLine(arg);
            TreeSet<Movie> movieCollection = collection.getCollection();
            console.printLine("Коллекция получена");
            String xmlLine = parser.convertCollectionToXMLString(movieCollection);
            writer.writeToFile(xmlLine, arg);
            console.printLine("Коллекция сохранена");
            return ApplicationStatus.RUNNING;
        } catch (IOException e) {
            console.printError("Ошибка FileWriter: " + e.getMessage());
            return ApplicationStatus.ERROR;
        }
    }
}
