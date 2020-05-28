package br.com.lucaskano.api.dummydata;

import br.com.lucaskano.api.external.imdb.model.ImdbMovie;
import br.com.lucaskano.api.external.imdb.model.ImdbResponse;

import java.util.ArrayList;
import java.util.List;

public class DummyDataForTests {

    public static ImdbResponse createImdbResponse(){
        ImdbResponse imdbResponse = new ImdbResponse();
        imdbResponse.setMovies(createListImdbMovie());
        return imdbResponse;
    }

    public static List<ImdbMovie> createListImdbMovie(){
        List<ImdbMovie> listMovies = new ArrayList<>();
        listMovies.add(new ImdbMovie("PARASITA", "tt6751668", null));
        listMovies.add(new ImdbMovie("VINGADORES: ULTIMATO", "tt4154796", null));
        listMovies.add(new ImdbMovie("CORINGA", "tt7286456", null));
        return listMovies;
    }

}
