package br.com.lucaskano.api.communication;

import com.google.inject.ImplementedBy;
import br.com.lucaskano.api.communication.impl.ApiServerImpl;
import br.com.lucaskano.api.exception.ApiException;

@ImplementedBy(ApiServerImpl.class)
public interface ApiServer extends Runnable{
    void execute() throws ApiException;
}
