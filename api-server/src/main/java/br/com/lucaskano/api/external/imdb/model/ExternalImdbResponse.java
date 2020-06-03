package br.com.lucaskano.api.external.imdb.model;

import java.util.List;
import java.util.Objects;

public class ExternalImdbResponse {

    private List<Movie> movies;

    public ExternalImdbResponse(){

    }

    public ExternalImdbResponse(List<Movie> movies){
        this.movies = movies;
    }

    public List<Movie> getMovies(){
        return movies;
    }

    public void setMovies(List<Movie> movies){
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExternalImdbResponse that = (ExternalImdbResponse) o;
        return Objects.equals(movies, that.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movies);
    }

    @Override
    public String toString() {
        return "Imdb{" + "movies=" + movies +
                '}';
    }
}
