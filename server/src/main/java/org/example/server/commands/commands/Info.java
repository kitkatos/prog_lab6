package org.example.server.commands.commands;

import org.example.server.commands.Command;
import org.example.common.console.*;
import org.example.common.ApplicationStatus;
import org.example.server.collection.*;

public class Info extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;

    public Info(TreeSetCollectionManager manager, ConsoleOutputHandler coh){
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.manager = manager;
        this.coh = coh;
    }

    @Override
    public ApplicationStatus execute(String arg){
        coh.printLine(manager.getInfoAboutCollection()  );
        return ApplicationStatus.RUNNING;
    }
}
