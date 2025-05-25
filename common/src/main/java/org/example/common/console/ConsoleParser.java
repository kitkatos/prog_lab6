package org.example.common.console;

public class ConsoleParser {
    public ExecuteArgs getRequest(String trimInput){
        String validatedInput = validate(trimInput);
        if (validatedInput.isEmpty()) {
            return new ExecuteArgs("", "");
        } else {
            String[] args = validatedInput.split("\\s+");
            String commandName = args[0];
            String argument = args.length > 1 ? args[1] : "";
            return new ExecuteArgs(commandName, argument);
        }
    }

    private String validate(String trimInput){
        if (trimInput == null || trimInput.trim().isEmpty()) {
            return "";
        }

        String regex = "^[a-zA-Z_]+(\\s+[\\w!а-яА-Я@#$%^&*()+_=№;:?.,]+)?$";

        if (trimInput.matches(regex)){
            return trimInput.toLowerCase();
        } else {
            return "";
        }
    }
}
