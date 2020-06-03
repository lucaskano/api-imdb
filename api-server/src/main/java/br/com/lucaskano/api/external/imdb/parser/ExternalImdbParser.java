package br.com.lucaskano.api.external.imdb.parser;

import br.com.lucaskano.api.external.imdb.model.ExternalImdbResponse;
import br.com.lucaskano.api.external.imdb.parser.impl.ExternalImdbParserImpl;
import com.google.inject.ImplementedBy;
import org.jsoup.nodes.Document;

import java.util.Optional;

@ImplementedBy(ExternalImdbParserImpl.class)
public interface ExternalImdbParser {
    Optional<ExternalImdbResponse> parseExternalContent(Document document);
}
