package org.example.server.commands;

import org.example.common.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Command {
    private final String name;
    private final String description;

    public ApplicationStatus execute(String arg) throws Exception{
        return ApplicationStatus.RUNNING;
    }

    @Override
    public String toString(){
        return getName() + ": " + getDescription();
    }
}
