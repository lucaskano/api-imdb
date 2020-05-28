package br.com.lucaskano.api.communication.handler;

import com.google.inject.ImplementedBy;
import br.com.lucaskano.api.communication.handler.impl.ApiClientHandlerImpl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

@ImplementedBy(ApiClientHandlerImpl.class)
public interface ApiClientHandler {
    void processAndReply(Socket connection, BufferedReader input, BufferedOutputStream output) throws IOException;
}
