package br.com.lucaskano.api.validation;

import br.com.lucaskano.api.enumeration.EnumApiException;
import br.com.lucaskano.api.exception.ApiException;
import br.com.lucaskano.api.utils.ApiUtil;
import org.apache.commons.lang3.StringUtils;

public class CommunicationValidate {

    /**
     * Validates the separator rule
     * */
    public static void separatorRule(String content) throws ApiException {
        if(StringUtils.isNotBlank(content)) {
            checkValidLength(content);
        } else {
            throw new ApiException(EnumApiException.INVALID_SEPARATOR_VALIDATION);
        }
    }

    /**
     * Checks if the length is greater than zero and valid
     * (the length value must be the same as the query or load).
     * */
    private static void checkValidLength(String content) throws ApiException {
        int position = ApiUtil.checkSeparatorPosition(content);
        try {
            int size = Integer.parseInt(content.substring(0, position).trim());
            int lengthLastPart = content.substring(position+1).trim().length();
            if ( size <= 0
                    || lengthLastPart <= 0
                    || (lengthLastPart > 0 && size != lengthLastPart)) {
                throw new ApiException(EnumApiException.INVALID_LENGTH_VALIDATION);
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException ex){
            throw new ApiException(EnumApiException.INVALID_LENGTH_VALIDATION);
        }
    }

}
