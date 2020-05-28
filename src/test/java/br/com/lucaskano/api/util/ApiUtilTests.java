package br.com.lucaskano.api.util;

import br.com.lucaskano.api.ApiTests;
import br.com.lucaskano.api.exception.ApiException;
import br.com.lucaskano.api.utils.ApiUtil;
import org.junit.Assert;
import org.junit.Test;

public class ApiUtilTests extends ApiTests {

    @Test
    public void checkSeparatorPositionTest() throws ApiException {
        Assert.assertEquals(1, ApiUtil.checkSeparatorPosition("0:234567"));
        Assert.assertEquals(2, ApiUtil.checkSeparatorPosition("01:34567"));
        Assert.assertEquals(3, ApiUtil.checkSeparatorPosition("012:4567"));
        Assert.assertEquals(4, ApiUtil.checkSeparatorPosition("0123:567"));
        Assert.assertEquals(5, ApiUtil.checkSeparatorPosition("01234:67"));
        Assert.assertEquals(6, ApiUtil.checkSeparatorPosition("012345:7"));
    }

    @Test(expected = ApiException.class)
    public void checkSeparatorAtFirstPositionTest() throws ApiException {
        ApiUtil.checkSeparatorPosition(":1234567");
    }

    @Test(expected = ApiException.class)
    public void checkSeparatorAtLastPositionTest() throws ApiException {
        ApiUtil.checkSeparatorPosition("0123456:");
    }

    @Test
    public void successGetFirstLetterTest() throws ApiException {
        Assert.assertEquals("O", String.valueOf(ApiUtil.getFirstLetter("Os Normais")));
        Assert.assertEquals("o", String.valueOf(ApiUtil.getFirstLetter("os normais")));
        Assert.assertEquals("L", String.valueOf(ApiUtil.getFirstLetter("Lagoa Azul")));
        Assert.assertEquals("1", String.valueOf(ApiUtil.getFirstLetter("1234567")));
    }

    @Test(expected = ApiException.class)
    public void successGetFirstLetterExceptionNullTest() throws ApiException {
        ApiUtil.getFirstLetter(null);
    }

    @Test(expected = ApiException.class)
    public void successGetFirstLetterExceptionBlankTest() throws ApiException {
        ApiUtil.getFirstLetter("");
    }

}
