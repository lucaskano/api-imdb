package br.com.lucaskano.api.validation;

import br.com.lucaskano.api.enumeration.EnumApiException;
import br.com.lucaskano.api.exception.ApiException;
import org.apache.commons.lang3.StringUtils;

public class InputCommunicationValidate extends CommunicationValidate {

    /**
     * Check if the INPUT formation is valid.
     * */
    public static void inputValidate(String input) throws ApiException {
        if(StringUtils.isNotBlank(input)){
            separatorValidate(input);
        } else {
            throw new ApiException(EnumApiException.INVALID_INPUT_VALIDATION);
        }
    }

}
