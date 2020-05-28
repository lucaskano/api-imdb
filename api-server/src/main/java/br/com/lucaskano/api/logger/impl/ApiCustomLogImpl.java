package br.com.lucaskano.api.logger.impl;

import com.google.inject.Inject;
import br.com.lucaskano.api.enumeration.EnumApiException;
import br.com.lucaskano.api.logger.ApiCustomLog;

import java.util.logging.Logger;

public class ApiCustomLogImpl implements ApiCustomLog {

    private final Logger logger;

    @Inject
    public ApiCustomLogImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void error(String message) {
        logger.severe(message);
    }

    @Override
    public void error(EnumApiException error) {
        logger.severe(error.getCodeAndMessage());
    }

    @Override
    public void error(EnumApiException error, String detail) {
        logger.severe(error.getCodeAndMessage() + " -- " + detail);
    }
}
