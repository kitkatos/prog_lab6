package org.example.common.network;

import org.example.common.ApplicationStatus;
import org.example.common.model.Movie;
import lombok.Getter;

import java.io.Serializable;
import java.util.TreeSet;

@Getter
public class Response extends NetworkObject implements Serializable {
    private final ApplicationStatus status;
    private final String ans;
    private TreeSet<Movie> movies = new TreeSet<>();

    public Response(ApplicationStatus status, String ans) {
        this.status = status;
        this.ans = ans;
    }

    public Response addMovie(Movie movie) {
        movies.add(movie);
        return this;
    }

}
