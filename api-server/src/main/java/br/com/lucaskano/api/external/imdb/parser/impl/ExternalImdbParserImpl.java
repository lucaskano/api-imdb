package br.com.lucaskano.api.external.imdb.parser.impl;

import br.com.lucaskano.api.external.imdb.model.ExternalImdbResponse;
import br.com.lucaskano.api.external.imdb.model.Movie;
import br.com.lucaskano.api.external.imdb.parser.ExternalImdbParser;
import br.com.lucaskano.api.logger.ApiCustomLog;
import com.google.inject.Inject;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExternalImdbParserImpl implements ExternalImdbParser {

    private final ApiCustomLog logger;

    @Inject
    public ExternalImdbParserImpl(ApiCustomLog logger){
        this.logger = logger;
    }

    @Override
    public Optional<ExternalImdbResponse> parseExternalContent(Document document) {
        logger.info("Parsing IMDB response...");
        Elements resultText = document.select("td.result_text");
        if(!resultText.isEmpty()){
            List<Movie> list = new ArrayList<>();
            resultText.forEach(elem -> {
                Elements otherElement = elem.select("a");
                String movieLink = otherElement.attr("href");
                String movieIdentifier = movieLink.replace("/title/", "").trim();
                int length = movieIdentifier.indexOf("/?");
                movieIdentifier = movieIdentifier.substring(0, length);
                String movieTitle = elem.select("[href]").text();
                Movie movie = new Movie(movieTitle, movieIdentifier, movieLink);
                if(!list.contains(movie)){
                    list.add(movie);
                }
            });
            logger.info("Parse completed");
            return Optional.of(new ExternalImdbResponse(list));
        }
        return Optional.empty();
    }
}
