package org.example.server.commands.commands;

import org.example.server.commands.Command;
import org.example.common.console.*;
import org.example.common.ApplicationStatus;
import org.example.common.model.Movie;
import org.example.server.collection.*;
import org.example.common.console.movieInput.*;
import org.example.server.commands.CommandManager;

public class Update extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;
    private final CommandManager commands;
    private final MovieFactory factory;


    public Update(TreeSetCollectionManager manager, ConsoleOutputHandler coh, CommandManager commands, MovieFactory factory){
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.manager = manager;
        this.coh = coh;
        this.commands = commands;
        this.factory = factory;
    }

    @Override
    public ApplicationStatus execute(String arg){
        try {
            if (arg.isEmpty()) {
                coh.printError("Аргумент не указан");
                return ApplicationStatus.ERROR;
            }
            int intArg = Integer.parseInt(arg);
            if (manager.getElemById(intArg) != null) {
                commands.executeCommand(new ExecuteArgs("remove_by_id", arg));
                Movie movie = factory.createMovieWithId(intArg);
                manager.addElem(movie);
                coh.printLine(String.format("Элемент с id=%s успешно обновлен", arg));
            } else {
                coh.printLine(String.format("Элемент с id=%s не сущенствует", arg));
            }
            return ApplicationStatus.RUNNING;
        } catch (NumberFormatException e){
            coh.printError(e.getMessage());
            return ApplicationStatus.ERROR;

        }
    }
}
