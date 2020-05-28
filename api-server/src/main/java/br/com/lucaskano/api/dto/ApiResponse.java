package br.com.lucaskano.api.dto;

public class ApiResponse extends ApiDTO {

    public ApiResponse() {
        super();
    }

    public ApiResponse(Integer length, String content) {
        super(length, content);
    }

}
