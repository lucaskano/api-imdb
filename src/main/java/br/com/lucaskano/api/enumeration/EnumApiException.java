package br.com.lucaskano.api.enumeration;

public enum EnumApiException {

    // GENERIC PROJECT ERROR
    FATAL_ERROR("ERR-0000", "IMDB-MOVIES", "ERROR", "FATAL ERROR", "Fatal error"),
    GENERIC_ERROR("ERR-0001", "IMDB-MOVIES", "GENERIC ERROR", "GENERIC ERROR", "Generic error"),

    // SOCKET
    SOCKET_GENERATE_ERROR("ERR-1000", "IMDB-MOVIES", "ERROR", "SERVER SOCKET GENERATE", "Socket generate error"),
    SOCKET_ACCEPT_ERROR("ERR-1001", "IMDB-MOVIES", "ERROR", "SOCKET ACCEPT", "Socket accept error"),
    SOCKET_NOT_FOUND_FOR_ACCEPT("ERR-1002", "IMDB-MOVIES", "NOT_FOUND", "SOCKET", "Socket not found for accept"),
    SOCKET_NOT_FOUND("ERR-1003", "IMDB-MOVIES", "NOT_FOUND", "SOCKET", "Socket not found"),

    // SERVER
    SERVER_RUN_IO_ERROR("ERR-2000", "IMDB-MOVIES", "ERROR", "IO", "IO error"),

    // VALIDATION
    INVALID_INPUT_VALIDATION("ERR-3000", "IMDB-MOVIES", "VALIDATION", "INPUT", "Invalid input"),
    INVALID_OUTPUT_VALIDATION("ERR-3001", "IMDB-MOVIES", "VALIDATION", "OUTPUT", "Invalid output"),
    INVALID_LENGTH_VALIDATION("ERR-3002", "IMDB-MOVIES", "VALIDATION", "LENGHT", "Invalid length"),
    INVALID_SEPARATOR_VALIDATION("ERR-3003", "IMDB-MOVIES", "VALIDATION", "SEPARATOR", "Separator validation error"),

    // MODEL
    PARSER_REQUEST_ERROR("ERR-4000", "IMDB-MOVIES", "ERROR", "PARSER REQUEST", "Request error"),
    PARSER_RESPONSE_ERROR("ERR-4001", "IMDB-MOVIES", "ERROR", "PARSER RESPONSE", "Response error"),

    // PARTNER
    PARTNER_CALL_ERROR("ERR-5000", "IMDB-MOVIES", "ERROR", "PARTNER CALL", "Error calling the partner"),
    PARTNER_URL_ERROR("ERR-5001", "IMDB-MOVIES", "ERROR", "PARTNER URL", "Error defining the partner URL"),
    PARTNER_MAPPER_ERROR("ERR-5002", "IMDB-MOVIES", "ERROR", "PARTNER MAPPER", "Mapper error"),
    PARTNER_MOVIE_NOT_FOUND("ERR-5003", "IMDB-MOVIES", "NOT_FOUND", "MOVIE", "Movie not found"),

    // ENCODER
    URL_ENCODER_ERROR("ERR-6000", "IMDB-MOVIES", "ERROR", "URL", "URL encoder error");

    private String code;
    private String origin;
    private String type;
    private String subType;
    private String message;

    EnumApiException(String code, String origin, String type, String subType, String message){
        this.code = code;
        this.origin = origin;
        this.type = type;
        this.subType = subType;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getOrigin() {
        return origin;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static EnumApiException getEnumImdbExceptionByCode(String code){
        for (EnumApiException element : EnumApiException.values()){
            if(code.equals(element.getCode())){
                return element;
            }
        }
        return EnumApiException.GENERIC_ERROR;
    }

    public String getCodeAndMessage() {
        return this.getCode() + " - " + this.getMessage();
    }
}
