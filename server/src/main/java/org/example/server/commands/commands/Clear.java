package org.example.server.commands.commands;

import org.example.server.commands.Command;
import org.example.common.console.*;
import org.example.common.ApplicationStatus;
import org.example.server.collection.*;

public class Clear extends Command {
    CollectionManager collection;
    ConsoleOutputHandler console;
    public Clear(CollectionManager collection, ConsoleOutputHandler console){
        super("clear", "Очистить коллекцию");
        this.collection = collection;
        this.console = console;
    }
    @Override
    public ApplicationStatus execute(String arg){
        if (collection.getCollection().size() == 0 ) {
            console.printLine("Коллекция уже пуста");
        } else {
            collection.deleteAllElem();
            console.printLine("Коллекция успешно очищена");
        }
        return ApplicationStatus.RUNNING;
    }
}
