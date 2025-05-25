package org.example.common;

import java.io.Serializable;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommandName implements Serializable {
    HELP("help"),
    INFO("info"),
    SHOW("show"),
    ADD("add"),
    UPDATE("update"),
    REMOVE_BY_ID("remove_by_id"),
    CLEAR("clear"),
    EXIT("exit"),
    ADD_IF_MAX("add_if_max"),
    REMOVE_GREATER("remove_greater"),
    MIN_BY_CREATION_DATE("min_by_creation_date"),
    MAX_BY_ID("max_by_id"),
    FILTER_WITH_NAME("filter_with_name"),
    HISTORY("history"),
    SAVE("save"),
    EXECUTE_SCRIPT("execute_script"),
    READ_COLLECTION("read");

    private final String name;

    public static CommandName getCommandFromString(String name) throws CommandNotFoundException{
        return Arrays.stream(CommandName.values())
                .filter(cmd -> cmd.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new CommandNotFoundException(""));
    }
}
