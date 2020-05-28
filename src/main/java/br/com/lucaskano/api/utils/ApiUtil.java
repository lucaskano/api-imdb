package br.com.lucaskano.api.utils;

import br.com.lucaskano.api.enumeration.EnumApiException;
import br.com.lucaskano.api.exception.ApiException;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ApiUtil {

    public static int checkSeparatorPosition(String content) throws ApiException {
        if(content.indexOf(ApiConfig.getSeparator(), 0) <= 0
                || content.indexOf(ApiConfig.getSeparator(), 0) == (content.length()-1)) {
            throw new ApiException(EnumApiException.INVALID_SEPARATOR_VALIDATION);
        } else {
            return content.indexOf(ApiConfig.getSeparator(), 0);
        }
    }

    public static char getFirstLetter(String string) throws ApiException {
        if(StringUtils.isNotBlank(string)){
            return string.charAt(0);
        }
        throw new ApiException(EnumApiException.GENERIC_ERROR);
    }

    public static String encodeValue(String value) throws ApiException {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new ApiException(EnumApiException.URL_ENCODER_ERROR);
        }
    }
}
