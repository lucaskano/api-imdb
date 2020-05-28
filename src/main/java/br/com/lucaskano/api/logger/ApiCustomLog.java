package br.com.lucaskano.api.logger;

import com.google.inject.ImplementedBy;
import br.com.lucaskano.api.enumeration.EnumApiException;
import br.com.lucaskano.api.logger.impl.ApiCustomLogImpl;

@ImplementedBy(ApiCustomLogImpl.class)
public interface ApiCustomLog {

    void info(String message);
    void error(String message);
    void error(EnumApiException error);
    void error(EnumApiException error, String detail);
}
