package org.example.server.commands;


import org.example.common.ApplicationStatus;
import org.example.common.console.ConsoleOutputHandler;
import org.example.common.console.ExecuteArgs;

import java.util.ArrayDeque;

import java.util.Map;

public class CommandManager {
    private Map<String, Command> commandMap;
    private ArrayDeque<String> history;
    private ConsoleOutputHandler coh;
    public CommandManager(Map<String, Command> commandMap, ArrayDeque<String> history, ConsoleOutputHandler coh) {
        this.commandMap = commandMap;
        this.history = history;
        this.coh = coh;
    }
    public void addCommand(Command command){
        commandMap.put(command.getName(), command);
    }
    public ApplicationStatus executeCommand(ExecuteArgs args){
        try {
            Command command = commandMap.get(args.command());
            if (command != null) {
                addHistory(args.command());
            }
            return command.execute(args.arg());
        } catch (NullPointerException e) {
            coh.printError( "Команды или аргумента не существует: " + e.getMessage() );
            return ApplicationStatus.ERROR;
        } catch (Exception e){
            coh.printError("Ошибка выполнения команды: " + e.getMessage());
            return ApplicationStatus.ERROR;
        }
    }
    public Map<String, Command> getCommandMap(){
        return commandMap;
    }
    public void addHistory(String name) {
        if (history.size() == 8) {
            history.pollFirst();
        }
        history.addLast(name);}
    public ArrayDeque<String> getHistory(){
        return history;
    }
}
