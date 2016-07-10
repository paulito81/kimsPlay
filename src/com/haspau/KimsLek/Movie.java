package com.haspau.KimsLek;

/**
 * Created by paul on 12.05.2015.
 */
public class Movie {

    String name;
    int movieId;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Movie(int movieId,String name) {
        super();
        this.movieId = movieId;
        this.name = name;

    }
    public Movie(String name){
        super();
        this.name = name;
    }

    public int getMovieById() {
        return movieId;
    }
    @Override
    public String toString(){
        return "Movie [id=" + movieId+ ", film: " + name +  "]";
    }

}
