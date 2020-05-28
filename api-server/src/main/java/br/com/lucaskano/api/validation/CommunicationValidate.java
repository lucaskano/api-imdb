package br.com.lucaskano.api.validation;

import br.com.lucaskano.api.enumeration.EnumApiException;
import br.com.lucaskano.api.exception.ApiException;
import br.com.lucaskano.api.utils.ApiUtil;
import org.apache.commons.lang3.StringUtils;

public class CommunicationValidate {

    /**
     * Responsible for apply separator validation rule.
     * The rule is the same for INPUT or OUTPUT.
     *
     * */
    public static void separatorValidate(String content) throws ApiException {
        if(StringUtils.isNotBlank(content)) {
            verifyValidLength(content);
        } else {
            throw new ApiException(EnumApiException.INVALID_SEPARATOR_VALIDATION);
        }
    }

    /**
     * Verify if the length is more than zero value and if the length is a
     * valid value (length number should be the same of query or payload length).
     *
     * */
    private static void verifyValidLength(String content) throws ApiException {
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
