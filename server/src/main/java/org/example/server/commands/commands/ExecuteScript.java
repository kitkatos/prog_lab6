package org.example.server.commands.commands;

import org.example.server.commands.Command;
import org.example.server.commands.CommandManager;
import org.example.common.console.*;
import org.example.common.ApplicationStatus;
import org.example.server.collection.*;
import org.example.server.file.FileReader;

import java.io.IOException;
import java.util.List;

public class ExecuteScript extends Command {
    CollectionManager collection;
    ConsoleOutputHandler console;
    FileReader reader;
    CommandManager commandManager;
    ConsoleParser parser;
    public ExecuteScript(CollectionManager collection, ConsoleOutputHandler console, FileReader reader, CommandManager commandManager, ConsoleParser parser){
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.collection = collection;
        this.console = console;
        this.reader = reader;
        this.commandManager = commandManager;
        this.parser = parser;
    }
    @Override
    public ApplicationStatus execute(String arg){
        try {
            if (arg.isEmpty()) {
                console.printError("Аргумент не указан");
                return ApplicationStatus.ERROR;
            }
            List<String> script = reader.readFile(arg);
            for (String line : script) {
                ExecuteArgs args = parser.getRequest(line);
                console.printLine("Выполнена команда " + line);
                ApplicationStatus status = commandManager.executeCommand(args);
                if (status == ApplicationStatus.EXIT) {
                    return ApplicationStatus.EXIT;
                }
            }
            return ApplicationStatus.RUNNING;
        } catch (IOException e) {
            console.printError("Ошибка FileReader: " + e.getMessage());
            return ApplicationStatus.ERROR;
        }
    }
}
