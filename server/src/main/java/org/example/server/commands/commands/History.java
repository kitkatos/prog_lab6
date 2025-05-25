package org.example.server.commands.commands;

import org.example.server.commands.Command;
import org.example.server.commands.CommandManager;
import org.example.common.console.*;
import org.example.common.ApplicationStatus;
import org.example.server.collection.*;

import java.util.ArrayDeque;

public class History extends Command {
    CollectionManager collection;
    ConsoleOutputHandler console;
    CommandManager manager;
    public History(CollectionManager collection, ConsoleOutputHandler console, CommandManager manager){
        super("history", "вывести последние 8 команд (без их аргументов)");
        this.collection = collection;
        this.console = console;
        this.manager = manager;
    }
    @Override
    public ApplicationStatus execute(String arg){
        ArrayDeque<String> history = manager.getHistory();
        int historySize = history.size();
        if (historySize == 0) {
            console.printLine("В истории нет команд");
        } else {
            console.printLine(String.format("В истории %d команд", historySize));
            for (String command : history) {
                console.printLine(command);
            }
        }
        return ApplicationStatus.RUNNING;
    }
}
