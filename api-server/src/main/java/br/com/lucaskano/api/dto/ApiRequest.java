package br.com.lucaskano.api.dto;

public class ApiRequest extends ApiDTO {

    public ApiRequest() {
        super();
    }

    public ApiRequest(Integer length, String content) {
        super(length, content);
    }

}
