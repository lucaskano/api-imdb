package br.com.lucaskano.api.validation;

import br.com.lucaskano.api.enumeration.EnumApiException;
import br.com.lucaskano.api.exception.ApiException;
import org.apache.commons.lang3.StringUtils;

public class InputValidate extends CommunicationValidate {

    /**
     *  Validates input information
     * */
    public static void inputValidate(String input) throws ApiException {
        if(StringUtils.isNotBlank(input)){
            separatorValidate(input);
        } else {
            throw new ApiException(EnumApiException.INVALID_INPUT_VALIDATION);
        }
    }

}
