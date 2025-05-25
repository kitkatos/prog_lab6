package org.example.server.commands.commands;

import org.example.server.commands.Command;
import org.example.common.console.*;
import org.example.common.ApplicationStatus;
import org.example.server.commands.CommandManager;

import java.util.Map;

public class Help extends Command {
    private final ConsoleOutputHandler coh;
    private final CommandManager commandManager;

    public Help(ConsoleOutputHandler coh, CommandManager commandManager){
        super("help", "вывести справку по доступным командам");
        this.coh = coh;
        this.commandManager = commandManager;
    }

    @Override
    public ApplicationStatus execute(String arg){
        Map<String, Command> commandMap = commandManager.getCommandMap();
        for (Command command : commandMap.values()) {
            String res = command.toString();
            coh.printLine(res);
        }
        coh.printLine("Список команд отображен");
        return ApplicationStatus.RUNNING;
    }
}
