package br.com.lucaskano.api.external.imdb.model;

import java.util.Objects;

public class Movie {

    private String title;

    private String identifier;

    private String url;

    public Movie(){

    }

    public Movie(String title, String identifier, String url){
        this.title = title;
        this.identifier = identifier;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) &&
                Objects.equals(identifier, movie.identifier) &&
                Objects.equals(url, movie.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, identifier, url);
    }
}
