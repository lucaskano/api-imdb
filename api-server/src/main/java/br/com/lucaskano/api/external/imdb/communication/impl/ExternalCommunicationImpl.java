package br.com.lucaskano.api.external.imdb.communication.impl;

import br.com.lucaskano.api.enumeration.EnumApiException;
import br.com.lucaskano.api.exception.ApiException;
import br.com.lucaskano.api.external.imdb.communication.ExternalCommunication;
import br.com.lucaskano.api.external.imdb.model.ExternalImdbResponse;
import br.com.lucaskano.api.external.imdb.parser.ExternalImdbParser;
import br.com.lucaskano.api.logger.ApiCustomLog;
import br.com.lucaskano.api.utils.ApiUtil;
import com.google.inject.Inject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Optional;

public class ExternalCommunicationImpl implements ExternalCommunication {

    private static final String HOST = "https://www.imdb.com";
    private static final String URL = HOST + "/find?q=$MOVIE_TITLE&s=tt&ref_=fn_al_tt_mr";

    private final ApiCustomLog logger;
    private final ExternalImdbParser externalImdbParser;

    @Inject
    public ExternalCommunicationImpl(ApiCustomLog logger, ExternalImdbParser imdbParser) {
        this.logger = logger;
        this.externalImdbParser = imdbParser;
    }

    @Override
    public Optional<ExternalImdbResponse> searchMovieByTitle(String title) throws ApiException {
        try {
            logger.info("Calling the pattern to perform the search : " + title);
            return this.requestImdb(title);
        } catch (Exception e){
            logger.error(EnumApiException.PARTNER_MOVIE_NOT_FOUND, e.getMessage());
            throw new ApiException(EnumApiException.PARTNER_MOVIE_NOT_FOUND);
        }
    }

    /**
     * Request IMDB to GET Movies.
     * */
    private Optional<ImdbResponse> requestImdb(String title) throws ApiException {
        try {
            String IMDB_TITLE_IDENTIFIER = "$MOVIE_TITLE";
            String url = URL.replace(IMDB_TITLE_IDENTIFIER, ApiUtil.encodeValue(title));
            logger.info("URL : " + url);
            int IMDB_TIMEOUT = 5000;
            Connection connection = Jsoup
                    .connect(url)
                    .timeout(IMDB_TIMEOUT);
            return ExternalImdbParser.parserImdbContent(connection.get());
        } catch (IOException e) {
            logger.error(EnumApiException.PARTNER_CALL_ERROR, e.getMessage());
            throw new ApiException(EnumApiException.PARTNER_CALL_ERROR);
        }
    }


}
