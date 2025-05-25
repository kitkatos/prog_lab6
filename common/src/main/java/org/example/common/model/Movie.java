package org.example.common.model;

import lombok.Getter;
import lombok.Setter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Comparable;

public class Movie implements Validatable, Comparable<Movie>{
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    @Getter @Setter
    private int id;
    private static int nextId = 1;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private Coordinates coordinates;
    @Getter @Setter
    private Date creationDate;
    @Getter @Setter
    private long oscarsCount;
    @Getter @Setter
    private Long usaBoxOffice;
    @Getter @Setter
    private MovieGenre genre;
    @Getter @Setter
    private MpaaRating mpaaRating;
    @Getter @Setter
    private Person director;

    public Movie(int id, String name, Coordinates coordinates, long oscarsCount,
                 Long usaBoxOffice, MovieGenre genre, MpaaRating mpaaRating, Person director){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.oscarsCount = oscarsCount;
        this.usaBoxOffice = usaBoxOffice;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.director = director;
    }

    public Movie(String name, Coordinates coordinates, long oscarsCount,
                 Long usaBoxOffice, MovieGenre genre, MpaaRating mpaaRating, Person director){
        this.id = nextId++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.oscarsCount = oscarsCount;
        this.usaBoxOffice = usaBoxOffice;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.director = director;
    }


    @Override
    public String toString(){
        return String.format(
            "Movie{id=%s, name='%s', coordinates=%s, creationDate=%s, oscarsCount=%s, usaBoxOffice=%s%s%s%s}",
            id, name, coordinates, DATE_FORMAT.format(creationDate), oscarsCount, usaBoxOffice,
            (genre != null ? ", genre=" + genre : ""),
            (mpaaRating != null ? ", mpaaRating=" + mpaaRating : ""),
            (director != null ? ", director=" + director : "")
        );
    }

    @Override
    public boolean validate(){
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null) return false;
        if (oscarsCount == 0) return false;
        if (usaBoxOffice == 0) return false;
        if (genre == null) return false;
        if (mpaaRating == null) return false;
        return director != null;

    }

    @Override
    public int compareTo(Movie movie){
        int res = Long.compare(this.oscarsCount, movie.oscarsCount);

        if (res == 0) {
            return Long.compare(this.usaBoxOffice, movie.usaBoxOffice);
        }

        return res;
        /*
            this > movie 1
            this = movie 0
            this < movie -1
         */
    }
}