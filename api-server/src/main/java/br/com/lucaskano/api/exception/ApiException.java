package br.com.lucaskano.api.exception;

import br.com.lucaskano.api.enumeration.EnumApiException;

public class ApiException extends Exception{

    public ApiException(EnumApiException enumImdbException){
        super(enumImdbException.getCode() + " - " + enumImdbException.getMessage());
    }

    public ApiException(String errorMessage){
        super(errorMessage);
    }

}
