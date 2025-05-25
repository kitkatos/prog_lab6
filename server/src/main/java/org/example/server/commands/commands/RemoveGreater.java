package org.example.server.commands.commands;

import org.example.server.commands.Command;
import org.example.common.console.*;
import org.example.common.ApplicationStatus;
import org.example.common.model.Movie;
import org.example.server.collection.*;
import org.example.common.console.movieInput.*;

public class RemoveGreater extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;
    private final MovieFactory factory;


    public RemoveGreater(TreeSetCollectionManager manager, ConsoleOutputHandler coh, MovieFactory factory){
        super("remove_greater", "удалить из коллекции все элементы, превышающие заданный");
        this.manager = manager;
        this.coh = coh;
        this.factory = factory;
    }

    @Override
    public ApplicationStatus execute(String arg){
        Movie movie = factory.createMovie();
        int removedCount = manager.removeGreaterElements(movie);
        coh.printLine(String.format("Было удалено %d элемнетов, привышающих значение заданного ", removedCount));
        return ApplicationStatus.RUNNING;
    }
}
