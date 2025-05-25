package org.example.server.commands.commands;

import org.example.server.commands.Command;
import org.example.common.console.*;
import org.example.common.ApplicationStatus;
import org.example.common.model.Movie;
import org.example.server.collection.*;
import java.util.TreeSet;

public class Show extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;

    public Show(TreeSetCollectionManager manager, ConsoleOutputHandler coh){
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.manager = manager;
        this.coh = coh;
    }

    @Override
    public ApplicationStatus execute(String arg){
        TreeSet<Movie> collection = manager.getCollection();
        if (collection.isEmpty()) {
            coh.printLine("Коллеккция пуста");
        } else {
            for (Movie movie : collection) {
                String strMovie = movie.toString();
                coh.printLine(strMovie);
            }
            coh.printLine("Список элементов коллекции отображен");
        }
        return ApplicationStatus.RUNNING;
    }
}
