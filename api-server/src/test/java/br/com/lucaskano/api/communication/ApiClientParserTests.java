package br.com.lucaskano.api.communication;

import br.com.lucaskano.api.ApiTests;
import br.com.lucaskano.api.communication.parser.ApiClientParser;
import br.com.lucaskano.api.dto.ApiResponse;
import br.com.lucaskano.api.dummydata.DummyDataForTests;
import br.com.lucaskano.api.exception.ApiException;
import br.com.lucaskano.api.external.imdb.model.ImdbMovie;
import br.com.lucaskano.api.external.imdb.model.ImdbResponse;
import br.com.lucaskano.api.utils.ApiConfig;
import com.google.inject.Inject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class ApiClientParserTests extends ApiTests {

    @Inject
    private ApiClientParser clientParser;

    @Test
    public void successCreateRequestTest() throws ApiException {
        Assert.assertEquals("7", String.valueOf(clientParser.createRequest(
                Optional.ofNullable("7:CORINGA")).get().getLength()));
        Assert.assertEquals("7", String.valueOf(clientParser.createRequest(
                Optional.ofNullable("7:CORinga")).get().getLength()));
        Assert.assertEquals("27", String.valueOf(clientParser.createRequest(
                Optional.ofNullable("27:Era Uma Vez em... Hollywood")).get().getLength()));
        Assert.assertEquals("21", String.valueOf(clientParser.createRequest(
                Optional.ofNullable("21:Um Sonho de Liberdade")).get().getLength()));
        Assert.assertEquals("10", String.valueOf(clientParser.createRequest(
                Optional.ofNullable("30:Batman: O Cavaleiro das Trevas")).get().getLength()));
    }

    @Test(expected = ApiException.class)
    public void createRequestExceptionEmptyTest() throws ApiException {
        clientParser.createRequest(Optional.empty());
    }

    @Test(expected = ApiException.class)
    public void createRequestExceptionWithoutLengthTest() throws ApiException {
        clientParser.createRequest(Optional.ofNullable(":CORINGA"));
    }

    @Test(expected = ApiException.class)
    public void createRequestExceptionWithoutLengthAlsoTest() throws ApiException {
        clientParser.createRequest(Optional.ofNullable("CORINGA"));
    }

    @Test(expected = ApiException.class)
    public void createRequestExceptionWithoutContentTest() throws ApiException {
        clientParser.createRequest(Optional.ofNullable("4:"));
    }

    @Test
    public void successCreateResponseTest() throws ApiException {
        ImdbResponse imdbResponse = DummyDataForTests.createImdbResponse();
        Optional<ImdbResponse> optImdbResponse = Optional.of(imdbResponse);
        Optional<ApiResponse> opt = clientParser.createResponse(optImdbResponse);
        StringBuilder expected = new StringBuilder();
        for(ImdbMovie imdbMovie : imdbResponse.getMovies()){
            expected.append(imdbMovie.getTitle()).append(ApiConfig.getTitleSeparator());
        }
        Assert.assertEquals(String.valueOf(expected.length()), opt.get().getLength().toString());
        Assert.assertEquals(expected.toString(), opt.get().getContent());
    }

    @Test
    public void successCreateOtherResponseTest() throws ApiException {
        ImdbResponse imdbResponse = DummyDataForTests.createImdbResponse();
        Optional<ImdbResponse> optImdbResponse = Optional.ofNullable(imdbResponse);
        Optional<ApiResponse> opt = clientParser.createResponse(optImdbResponse);
        Assert.assertNotEquals("CORINGA\nRESGATE\n".length(), opt.get().getLength().toString());
        Assert.assertNotEquals("CORINGA\nRESGATE\n", opt.get().getContent());
    }

    @Test(expected = ApiException.class)
    public void createResponseExceptionEmptyTest() throws ApiException {
        Optional<ApiResponse> opt = clientParser.createResponse(Optional.empty());
    }

}
