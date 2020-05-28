package br.com.lucaskano.api.external.imdb.parser.impl;

import br.com.lucaskano.api.external.imdb.model.ImdbMovie;
import br.com.lucaskano.api.external.imdb.model.ImdbResponse;
import br.com.lucaskano.api.external.imdb.parser.ImdbParser;
import br.com.lucaskano.api.logger.ApiCustomLog;
import com.google.inject.Inject;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImdbParserImpl implements ImdbParser {

    private final ApiCustomLog logger;

    @Inject
    public ImdbParserImpl(ApiCustomLog logger){
        this.logger = logger;
    }

    @Override
    public Optional<ImdbResponse> parserImdbContent(Document document) {
        logger.info("Parsing IMDB response...");
        Elements resultText = document.select("td.result_text");
        if(!resultText.isEmpty()){
            List<ImdbMovie> list = new ArrayList<>();
            resultText.forEach(elem -> {
                Elements otherElement = elem.select("a");
                String movieLink = otherElement.attr("href");
                String movieIdentifier = movieLink.replace("/title/", "").trim();
                int length = movieIdentifier.indexOf("/?");
                movieIdentifier = movieIdentifier.substring(0, length);
                String movieTitle = elem.select("[href]").text();
                ImdbMovie movie = new ImdbMovie(movieTitle, movieIdentifier, movieLink);
                if(!list.contains(movie)){
                    list.add(movie);
                }
            });
            logger.info("Parse completed");
            return Optional.of(new ImdbResponse(list));
        }
        return Optional.empty();
    }
}
