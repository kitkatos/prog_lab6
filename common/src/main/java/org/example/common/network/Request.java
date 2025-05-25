package org.example.common.network;

import org.example.common.CommandName;
import org.example.common.model.Movie;
import lombok.Getter;

import java.io.Serializable;
import java.util.TreeSet;

@Getter
public class Request extends NetworkObject implements Serializable {
    private final CommandName commandName;
    private Integer intArg;
    private String strArg;
    private TreeSet<Movie> movies = new TreeSet<>();

    public Request(String commandName) {
        this.commandName = CommandName.getCommandFromString(commandName);
    }

    public Request addNumberArg(Integer arg) {
        this.intArg = arg;
        return this;
    }

    public Request addStringArg(String arg) {
        strArg = arg;
        return this;
    }

    public Request addwithMovie(Movie movie) {
        this.movies.add(movie);
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s, %d, %s, %s", commandName.toString(), intArg, strArg, movies.toString());
    }
}
