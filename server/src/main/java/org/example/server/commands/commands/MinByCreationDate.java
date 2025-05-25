package org.example.server.commands.commands;

import org.example.server.commands.Command;
import org.example.common.console.*;
import org.example.common.ApplicationStatus;
import org.example.common.model.Movie;
import org.example.server.collection.*;

public class MinByCreationDate extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;


    public MinByCreationDate(TreeSetCollectionManager manager, ConsoleOutputHandler coh){
        super("min_by_creation_date", "вывести любой объект из коллекции, значение поля creationDate которого является минимальным");
        this.manager = manager;
        this.coh = coh;
    }

    @Override
    public ApplicationStatus execute(String arg){
        if (manager.getCollection().isEmpty()) {
            coh.printLine("Коллекция пуста");
        } else {
            Movie elem = manager.getElemWithMinCreationDate();
            coh.printLine(elem.toString());
        }
        return ApplicationStatus.RUNNING;
    }
}
