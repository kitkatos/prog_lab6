package org.example.server.commands.commands;

import org.example.server.commands.Command;
import org.example.common.console.*;
import org.example.common.ApplicationStatus;
import org.example.common.model.Movie;
import org.example.server.collection.*;
import java.util.TreeSet;

public class FilterWithName extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;


    public FilterWithName(TreeSetCollectionManager manager, ConsoleOutputHandler coh){
        super("filter_starts_with_name", "вывести элементы, значение поля name которых начинается с заданной подстроки");
        this.manager = manager;
        this.coh = coh;
    }

    @Override
    public ApplicationStatus execute(String arg){
        if (arg.isEmpty()) {
            coh.printError("Аргумент не указан");
            return ApplicationStatus.ERROR;
        }
        TreeSet<Movie> res = manager.getElemsWithMatchName(arg);
        int resSize = res.size();
        if (manager.getCollectionSize() == 0) {
            coh.printLine("Коллекция пуста");
        } else if(resSize == 0) {
            coh.printLine("Нет совпадение");
        } else {
            coh.printLine(String.format("Найдено %d совпадений", resSize));
            for (Movie elem : res) {
                coh.printLine(elem.toString());
            }
        }
        return ApplicationStatus.RUNNING;
    }
}
