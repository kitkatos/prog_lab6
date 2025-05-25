package org.example.server.collection;

import org.example.common.model.Movie;

import java.util.Date;
import java.util.TreeSet;

// INTERFACE SEGREGATION
// ABSTRACT CLASS

public interface CollectionManager {
    Movie getElemById(int id);

    String getCollectionType();
    Date getCreationDate();
    int getCollectionSize();
    void addElem(Movie movie);
    void updateElemById(int id, Movie newMovie);
    void removeElemById(int id);
    void deleteAllElem();
    TreeSet<Movie> getCollection();
    boolean addElemIfMax(Movie movie);
    int removeGreaterElements(Movie movie);
    Movie getElemWithMinCreationDate();
    Movie getElemWithMaxId();
    public TreeSet<Movie> getElemsWithMatchName(String filter);
}
