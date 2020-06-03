package br.com.lucaskano.api.validation;

import br.com.lucaskano.api.enumeration.EnumApiException;
import br.com.lucaskano.api.exception.ApiException;
import org.apache.commons.lang3.StringUtils;

public class OutputValidate extends CommunicationValidate {

    /**
     * Validates output information
     * */
    public static void outputValidate(String output) throws ApiException {
        if(StringUtils.isNotBlank(output)){
            separatorValidate(output);
        } else {
            throw new ApiException(EnumApiException.INVALID_OUTPUT_VALIDATION);
        }
    }

}
