package br.com.lucaskano.api;

import br.com.lucaskano.api.communication.ApiServer;
import br.com.lucaskano.api.communication.impl.ApiServerImpl;
import br.com.lucaskano.api.exception.ApiException;
import br.com.lucaskano.api.logger.ApiCustomLog;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class ApiApplication {

    @Inject
    private static Injector injector;

    public static void main(String[] args) throws ApiException {
        injector = Guice.createInjector(new ApiModule());
        ApiServer apiServer = new ApiServerImpl(
                injector.getInstance(ApiCustomLog.class)
        );
        apiServer.execute();
    }
}
