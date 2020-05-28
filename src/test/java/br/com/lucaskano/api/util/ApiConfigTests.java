package br.com.lucaskano.api.util;

import br.com.lucaskano.api.ApiTests;
import br.com.lucaskano.api.utils.ApiConfig;
import org.junit.Assert;
import org.junit.Test;

public class ApiConfigTests extends ApiTests {

    @Test
    public void getSeparatorTest() {
        Assert.assertEquals(":", String.valueOf(ApiConfig.getSeparator()));
    }

    @Test
    public void getTitleSeparatorTest() {
        Assert.assertEquals("\\n", ApiConfig.getTitleSeparator());
    }

    @Test
    public void getTitleSeparatorNullTest() {
        Assert.assertNotEquals(null, ApiConfig.getTitleSeparator());
    }

    @Test
    public void getPortNumberTest() {
        Assert.assertEquals(32000, ApiConfig.getPortNumber());
    }

    @Test
    public void getPortNumberNullTest() {
        Assert.assertNotEquals (null, ApiConfig.getPortNumber());
    }

    @Test
    public void isAllowedAddressReuseTest(){
        Assert.assertTrue(ApiConfig.isAllowedAddressReuse());
    }

}
