package br.com.lucaskano.api.external.imdb.communication;

import br.com.lucaskano.api.exception.ApiException;
import br.com.lucaskano.api.external.imdb.communication.impl.ExternalCommunicationImpl;
import br.com.lucaskano.api.external.imdb.model.ExternalImdbResponse;
import com.google.inject.ImplementedBy;

import java.util.Optional;

@ImplementedBy(ExternalCommunicationImpl.class)
public interface ExternalCommunication {
    Optional<ExternalImdbResponse> searchMovieByTitle(String title) throws ApiException;
}
