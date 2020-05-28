package br.com.lucaskano.api.validation;

import br.com.lucaskano.api.ApiTests;
import br.com.lucaskano.api.exception.ApiException;
import org.junit.Test;

public class CommunicationValidateTests extends ApiTests {

    @Test
    public void successSeparatorTest() throws ApiException {
        CommunicationValidate.separatorValidate("8:12345678");
        CommunicationValidate.separatorValidate("10:EXTRACTION");
        CommunicationValidate.separatorValidate("10:ExTrACTION");
        CommunicationValidate.separatorValidate("13:the old guard");
        CommunicationValidate.separatorValidate("20:Vingadores: Ultimato");
        CommunicationValidate.separatorValidate("17:Avengers: Endgame");
        CommunicationValidate.separatorValidate("15:Arremesso Final");
    }

    @Test(expected = ApiException.class)
    public void blankSeparatorTest() throws ApiException {
        CommunicationValidate.separatorValidate("");
    }

    @Test(expected = ApiException.class)
    public void lengthZeroTest() throws ApiException {
        CommunicationValidate.separatorValidate("0:Vingadores: Ultimato");
    }

    @Test(expected = ApiException.class)
    public void higherLengthTest() throws ApiException {
        CommunicationValidate.separatorValidate("200:Vingadores: Ultimato");
    }

    @Test(expected = ApiException.class)
    public void nullContentTest() throws ApiException {
        CommunicationValidate.separatorValidate("null");
    }

    @Test(expected = ApiException.class)
    public void newLineTest() throws ApiException {
        CommunicationValidate.separatorValidate("\\n");
    }

    @Test(expected = ApiException.class)
    public void withoutIntegerLengthTest() throws ApiException {
        CommunicationValidate.separatorValidate("aaa:bbb");
    }

    @Test(expected = ApiException.class)
    public void withoutLengthWithContentTest() throws ApiException {
        CommunicationValidate.separatorValidate(":bbb");
    }

    @Test(expected = ApiException.class)
    public void withoutContentWithLengthTest() throws ApiException {
        CommunicationValidate.separatorValidate("7:");
    }

    @Test(expected = ApiException.class)
    public void justSeparatorTest() throws ApiException {
        CommunicationValidate.separatorValidate(":");
    }

    @Test(expected = ApiException.class)
    public void withoutSeparatorButNotBlankTest() throws ApiException {
        CommunicationValidate.separatorValidate("something");
    }
}
