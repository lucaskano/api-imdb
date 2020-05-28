package br.com.lucaskano.api.external.imdb.communication.impl;

import br.com.lucaskano.api.enumeration.EnumApiException;
import br.com.lucaskano.api.exception.ApiException;
import br.com.lucaskano.api.external.imdb.communication.ImdbCommunication;
import br.com.lucaskano.api.external.imdb.model.ImdbResponse;
import br.com.lucaskano.api.external.imdb.parser.ImdbParser;
import br.com.lucaskano.api.logger.ApiCustomLog;
import br.com.lucaskano.api.utils.ApiUtil;
import com.google.inject.Inject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Optional;

public class ImdbCommunicationImpl implements ImdbCommunication {

    private static final String IMDB_HOST = "https://www.imdb.com";
    private static final String IMDB_URL_SEARCH = IMDB_HOST + "/find?q=$MOVIE_TITLE&s=tt&ref_=fn_al_tt_mr";

    private final ApiCustomLog logger;
    private final ImdbParser imdbParser;

    @Inject
    public ImdbCommunicationImpl(ApiCustomLog logger, ImdbParser imdbParser) {
        this.logger = logger;
        this.imdbParser = imdbParser;
    }

    @Override
    public Optional<ImdbResponse> searchByMovieTitle(String title) throws ApiException {
        try {
            logger.info("Calling the partner for search : " + title);
            return this.callImdb(title);
        } catch (Exception e){
            logger.error(EnumApiException.PARTNER_MOVIE_NOT_FOUND, e.getMessage());
            throw new ApiException(EnumApiException.PARTNER_MOVIE_NOT_FOUND);
        }
    }

    /**
     * Call for the IMDB to get movies.
     *
     * */
    private Optional<ImdbResponse> callImdb(String title) throws ApiException {
        try {
            String IMDB_TITLE_IDENTIFIER = "$MOVIE_TITLE";
            String url = IMDB_URL_SEARCH.replace(IMDB_TITLE_IDENTIFIER, ApiUtil.encodeValue(title));
            logger.info("URL : " + url);
            int IMDB_TIMEOUT = 5000;
            Connection connection = Jsoup
                    .connect(url)
                    .timeout(IMDB_TIMEOUT);
            return imdbParser.parserImdbContent(connection.get());
        } catch (IOException e) {
            logger.error(EnumApiException.PARTNER_CALL_ERROR, e.getMessage());
            throw new ApiException(EnumApiException.PARTNER_CALL_ERROR);
        }
    }


}
