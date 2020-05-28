package br.com.lucaskano.api.communication.handler.impl;

import br.com.lucaskano.api.communication.handler.ApiClientHandler;
import br.com.lucaskano.api.communication.parser.ApiClientParser;
import br.com.lucaskano.api.dto.ApiRequest;
import br.com.lucaskano.api.dto.ApiResponse;
import br.com.lucaskano.api.enumeration.EnumApiException;
import br.com.lucaskano.api.exception.ApiException;
import br.com.lucaskano.api.external.imdb.communication.ImdbCommunication;
import br.com.lucaskano.api.external.imdb.model.ImdbResponse;
import br.com.lucaskano.api.logger.ApiCustomLog;
import br.com.lucaskano.api.utils.ApiConfig;
import br.com.lucaskano.api.validation.InputCommunicationValidate;
import br.com.lucaskano.api.validation.OutputCommunicationValidate;
import com.google.inject.Inject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

public class ApiClientHandlerImpl implements ApiClientHandler {

    private final ApiClientParser clientParser;
    private final ImdbCommunication imdbCommunication;
    private final ApiCustomLog logger;

    @Inject
    public ApiClientHandlerImpl(ApiClientParser clientParser,
                             ImdbCommunication imdbCommunication,
                             ApiCustomLog logger) {
        this.clientParser = clientParser;
        this.imdbCommunication = imdbCommunication;
        this.logger = logger;
    }

    @Override
    public void processAndReply(Socket connection, BufferedReader input, BufferedOutputStream output) throws IOException {
        String content = "";
        while ((content = input.readLine()) != null) {
            logger.info("Sent from the client " + connection.getInetAddress().getHostAddress() + ": '" + content + "'");
            try {
                try {
                    // validating input data
                    InputCommunicationValidate.inputValidate(content);
                    // identify query from input data
                    Optional<ApiRequest> optRequest = this.identifyRequest(content);
                    // call to the partner
                    Optional<ImdbResponse> optResponse = this.requestExternalPartner(optRequest);
                    // convert to response object format
                    content = this.parseExternalPartnerRespone(optResponse);
                    // validating output data
                    OutputCommunicationValidate.outputValidate(content);

                } catch (ApiException ex) {
                    logger.error("The content response will be a error message");
                    content = ex.getMessage().length() + String.valueOf(ApiConfig.getSeparator()) + ex.getMessage();
                }
            } catch (Exception ex) {
                logger.error(EnumApiException.FATAL_ERROR, ex.getMessage());
            }
            // send the content to client
            logger.info("Sending response to " + connection.getInetAddress().getHostAddress() + " : " + content);
            output.write((content + (char) 13 + (char) 10).getBytes());
            output.flush();
        }
    }

    /**
     * Identify query from input data
     *
     * */
    private Optional<ApiRequest> identifyRequest(String content) throws ApiException {
        Optional<ApiRequest> optRequest = clientParser.createRequest(Optional.ofNullable(content));
        if(optRequest.isEmpty()){
            logger.error(EnumApiException.PARTNER_CALL_ERROR, "IS EMPTY");
            throw new ApiException(EnumApiException.PARTNER_CALL_ERROR);
        }
        return optRequest;
    }

    /**
     * Request to external partner (Imdb).
     *
     * */
    private Optional<ImdbResponse> requestExternalPartner(Optional<ApiRequest> optRequest) throws ApiException {
        Optional<ImdbResponse> optResponse = imdbCommunication.searchByMovieTitle(optRequest.get().getContent());
        if(optResponse.isEmpty()){
            logger.error(EnumApiException.PARTNER_MOVIE_NOT_FOUND, "MOVIE NOT FOUND");
            throw new ApiException(EnumApiException.PARTNER_MOVIE_NOT_FOUND);
        }
        return optResponse;
    }

    /**
     * Parse the response from external partner (Imdb).
     *
     * */
    private String parseExternalPartnerRespone(Optional<ImdbResponse> optResponse) throws ApiException {
        Optional<ApiResponse> optApiMoviesResponse = clientParser.createResponse(optResponse);
        if(optApiMoviesResponse.isEmpty()){
            logger.error(EnumApiException.PARTNER_CALL_ERROR, "IS EMPTY");
            throw new ApiException(EnumApiException.PARTNER_CALL_ERROR);
        }
        return optApiMoviesResponse.get().getLength()
                + String.valueOf(ApiConfig.getSeparator())
                + optApiMoviesResponse.get().getContent();
    }
}
