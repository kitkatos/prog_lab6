package org.example.server.collection;

import org.example.common.model.Movie;

import java.util.Comparator;
import java.util.Date;
import java.util.TreeSet;


public class TreeSetCollectionManager implements CollectionManager{
    private TreeSet<Movie> collection;
    private final Date creationDate;

    // setCollection()
    public TreeSetCollectionManager(TreeSet<Movie> collection){ // poamotret
        this.collection = collection;
        this.creationDate = new Date();

    }


    public String getInfoAboutCollection() {
        return "Тип данных: " + collection.getClass().getName() + "\n"
                + "Дата инициализации: " + creationDate + "\n"
                + "Количество элементов: " + collection.size();
    }

    @Override
    public Movie getElemById(int id) {
        return collection.stream()
            .filter(movie -> movie.getId() == id)
            .findAny().orElse(null);
    }

    @Override
    public String getCollectionType(){
        return collection.getClass().getName();
    }

    @Override
    public Date getCreationDate(){
        return creationDate;
    }

    @Override
    public int getCollectionSize(){
        return collection.size();
    }

    @Override
    public void addElem(Movie movie){
        collection.add(movie);
    }
    
    @Override
    public void updateElemById(int id, Movie newMovie) {
        // я предполагаю, что в новом фильме уже задан верный айдишник
        collection.stream().filter(movie -> movie.getId() == id)
            .findAny().ifPresent(movie -> {
                collection.remove(movie);
                collection.add(newMovie);
            });
    }

    @Override
    public void removeElemById(int id) {
        Movie movie = getElemById(id);
        if (movie != null) {
            collection.remove(movie);
        }
    }

    @Override
    public void deleteAllElem(){
        collection.clear();
    }

    @Override
    public TreeSet<Movie> getCollection(){
        return collection;
    }

    @Override
    public boolean addElemIfMax(Movie maxMovie){
        boolean ans =  collection.stream()
            .filter(m -> m.compareTo(maxMovie) > 0).findAny().isPresent();
        if (ans) {
            collection.add(maxMovie);
        }
        return ans;
    }

    @Override
    public int removeGreaterElements(Movie movie){
        int before = getCollectionSize();
        collection.stream().filter(m -> m.compareTo(movie) > 0)
            .forEach(m -> collection.remove(m));
        int after = getCollectionSize();
        return before - after;
    }

    @Override
    public Movie getElemWithMinCreationDate() {
        return collection.stream()
                .min(Comparator.comparing(Movie::getCreationDate))
                .orElse(null);
    }

    @Override
    public Movie getElemWithMaxId() {
        return collection.stream()
                .max(Comparator.comparing(Movie::getId))
                .orElse(null);
    }

    public TreeSet<Movie> getElemsWithMatchName(String filter){
        TreeSet<Movie> result = new TreeSet<>(collection.comparator());
        collection.stream()
            .filter(m -> m.getName().startsWith(filter))
            .forEach(m -> result.add(m));
        return result;
    }
}
