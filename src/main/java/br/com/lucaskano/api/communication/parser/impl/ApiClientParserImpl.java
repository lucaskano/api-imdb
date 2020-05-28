package br.com.lucaskano.api.communication.parser.impl;

import br.com.lucaskano.api.communication.parser.ApiClientParser;
import br.com.lucaskano.api.dto.ApiRequest;
import br.com.lucaskano.api.dto.ApiResponse;
import br.com.lucaskano.api.enumeration.EnumApiException;
import br.com.lucaskano.api.exception.ApiException;
import br.com.lucaskano.api.external.imdb.model.ImdbMovie;
import br.com.lucaskano.api.external.imdb.model.ImdbResponse;
import br.com.lucaskano.api.utils.ApiConfig;
import br.com.lucaskano.api.utils.ApiUtil;

import java.util.Optional;

public class ApiClientParserImpl implements ApiClientParser {

    @Override
    public Optional<ApiRequest> createRequest(Optional<String> optRequestContent) throws ApiException {
        try {
            int position = ApiUtil.checkSeparatorPosition(optRequestContent.get());
            int length = Integer.parseInt(optRequestContent.get().substring(0, position));
            String content = optRequestContent.get().substring(position+1, (length + position + 1));
            return Optional.of(new ApiRequest(length, content.toLowerCase()));
        } catch (Exception e){
            throw new ApiException(EnumApiException.PARSER_REQUEST_ERROR);
        }
    }

    @Override
    public Optional<ApiResponse> createResponse(Optional<ImdbResponse> optImdbResponse) throws ApiException {
        if(!optImdbResponse.isEmpty()) {
            String outputContent = "";
            for(ImdbMovie movie : optImdbResponse.get().getMovies()){
                outputContent = outputContent.concat(
                        movie.getTitle().concat(String.valueOf(ApiConfig.getTitleSeparator())));
            }
            return Optional.of(new ApiResponse(outputContent.length(), outputContent));
        } else {
            throw new ApiException(EnumApiException.PARSER_RESPONSE_ERROR);
        }
    }
}
