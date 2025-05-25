package org.example.server.commands.commands;

import org.example.server.commands.Command;
import org.example.common.console.*;
import org.example.common.ApplicationStatus;
import org.example.server.collection.*;

public class RemoveById extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;


    public RemoveById(TreeSetCollectionManager manager, ConsoleOutputHandler coh){
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.manager = manager;
        this.coh = coh;
    }

    @Override
    public ApplicationStatus execute(String arg){
        try {
            if (arg.isEmpty()) {
                coh.printError("Аргумент не указан");
                return ApplicationStatus.ERROR;
            }
            int intArg = Integer.parseInt(arg);
            if (manager.getElemById(intArg) != null){
                manager.removeElemById(intArg);
                coh.printLine(String.format("Элемент с id=%s успешно заменен", arg));
            } else {
                coh.printLine(String.format("Элемента с id=%s не существует", arg));
            }
            return ApplicationStatus.RUNNING;
        } catch (NumberFormatException e){
            coh.printError(e.getMessage());
            return ApplicationStatus.ERROR;
        }
    }
}
