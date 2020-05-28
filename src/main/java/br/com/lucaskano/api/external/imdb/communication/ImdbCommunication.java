package br.com.lucaskano.api.external.imdb.communication;

import br.com.lucaskano.api.exception.ApiException;
import br.com.lucaskano.api.external.imdb.communication.impl.ImdbCommunicationImpl;
import br.com.lucaskano.api.external.imdb.model.ImdbResponse;
import com.google.inject.ImplementedBy;

import java.util.Optional;

@ImplementedBy(ImdbCommunicationImpl.class)
public interface ImdbCommunication {
    Optional<ImdbResponse> searchByMovieTitle(String title) throws ApiException;
}
