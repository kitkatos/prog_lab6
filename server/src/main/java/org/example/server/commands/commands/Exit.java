package org.example.server.commands.commands;

import org.example.server.commands.Command;
import org.example.common.console.*;
import org.example.common.ApplicationStatus;

public class Exit extends Command {
    private final ConsoleOutputHandler coh;

    public Exit(ConsoleOutputHandler coh){
        super("exit", "завершить программу (без сохранения в файл)");
        this.coh = coh;
    }

    @Override
    public ApplicationStatus execute(String arg){
        coh.printLine("Завершение работы");
        return ApplicationStatus.EXIT;
    }
}
