package org.example.server.commands.commands;

import org.example.server.commands.Command;
import org.example.common.console.*;
import org.example.common.ApplicationStatus;
import org.example.common.model.Movie;
import org.example.server.collection.*;
import org.example.common.console.movieInput.*;

public class AddIfMax extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;
    private final MovieFactory factory;


    public AddIfMax(TreeSetCollectionManager manager, ConsoleOutputHandler coh, MovieFactory factory){
        super("add_if_max", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.manager = manager;
        this.coh = coh;
        this.factory = factory;

    }

    @Override
    public ApplicationStatus execute(String arg) {
        Movie movie = factory.createMovie();
        boolean res = manager.addElemIfMax(movie);
        if (res){
            coh.printLine("Введенный элемент оказался наибольшим и добавлен в коллекцию");
        } else {
            coh.printLine("Введенный элемент не оказался наибольшим и не добавлен в коллекцию");
        }
        return ApplicationStatus.RUNNING;
    }
}

