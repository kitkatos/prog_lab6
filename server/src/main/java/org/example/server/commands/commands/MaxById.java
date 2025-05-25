package org.example.server.commands.commands;

import org.example.server.commands.Command;
import org.example.common.console.*;
import org.example.common.ApplicationStatus;
import org.example.common.model.Movie;
import org.example.server.collection.*;

public class MaxById extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;


    public MaxById(TreeSetCollectionManager manager, ConsoleOutputHandler coh){
        super("max_by_id", "вывести любой объект из коллекции, значение поля id которого является максимальным");
        this.manager = manager;
        this.coh = coh;
    }

    @Override
    public ApplicationStatus execute(String arg){
        if (manager.getCollectionSize() == 0) {
            coh.printLine("Коллекция пуста");
        } else {
            Movie elem = manager.getElemWithMaxId();
            coh.printLine(elem.toString());
        }
        return ApplicationStatus.RUNNING;
    }
}
