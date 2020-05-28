package br.com.lucaskano.api.communication.parser;

import br.com.lucaskano.api.communication.parser.impl.ApiClientParserImpl;
import br.com.lucaskano.api.dto.ApiRequest;
import br.com.lucaskano.api.dto.ApiResponse;
import br.com.lucaskano.api.exception.ApiException;
import br.com.lucaskano.api.external.imdb.model.ImdbResponse;
import com.google.inject.ImplementedBy;

import java.util.Optional;

@ImplementedBy(ApiClientParserImpl.class)
public interface ApiClientParser {

    Optional<ApiRequest> createRequest(Optional<String> optRequestContent) throws ApiException;
    Optional<ApiResponse> createResponse(Optional<ImdbResponse> optImdbResponse) throws ApiException;
}
