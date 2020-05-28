package br.com.lucaskano.api;

import br.com.lucaskano.api.logger.ApiCustomLog;
import br.com.lucaskano.api.logger.impl.ApiCustomLogImpl;
import com.google.inject.AbstractModule;

public class ApiModule extends AbstractModule {

    public ApiModule(){

    }

    @Override
    protected void configure() {
        bind(ApiCustomLog.class).to(ApiCustomLogImpl.class);
    }
}
