package br.com.lucaskano.api.communication.impl;

import br.com.lucaskano.api.communication.handler.ApiClientHandler;
import br.com.lucaskano.api.communication.handler.impl.ApiClientHandlerImpl;
import br.com.lucaskano.api.communication.parser.impl.ApiClientParserImpl;
import br.com.lucaskano.api.external.imdb.communication.impl.ImdbCommunicationImpl;
import br.com.lucaskano.api.external.imdb.parser.impl.ImdbParserImpl;
import com.google.inject.Inject;
import br.com.lucaskano.api.communication.ApiServer;
import br.com.lucaskano.api.enumeration.EnumApiException;
import br.com.lucaskano.api.exception.ApiException;
import br.com.lucaskano.api.logger.ApiCustomLog;
import br.com.lucaskano.api.utils.ApiConfig;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ApiServerImpl implements ApiServer {

    private Socket connection;
    private final ApiCustomLog logger;

    @Inject
    public ApiServerImpl(ApiCustomLog logger){
        this.logger = logger;
    }

    @Inject
    public ApiServerImpl(Socket connection, ApiCustomLog logger){
        this.connection = connection;
        this.logger = logger;
    }

    @Override
    public void execute() throws ApiException {
        try{
            int counter = 0;
            ServerSocket serverSocket = this.getServerSocketAlreadyConfigurated();
            while(true){
                Socket connection = serverSocket.accept();
                logger.info("New client connected - Client IP: " + connection.getInetAddress().getHostAddress());
                Runnable runnable = new ApiServerImpl(connection, logger);
                Thread thread = new Thread(runnable);
                thread.start();
            }
        } catch (Exception ex){
            logger.error(EnumApiException.FATAL_ERROR, "FATAL ERROR: " + ex.getMessage());
            throw new ApiException(EnumApiException.FATAL_ERROR);
        }
    }

    @Override
    public void run() {
        BufferedReader input = null;
        BufferedOutputStream output = null;
        try {
            input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            output = new BufferedOutputStream(connection.getOutputStream());
            ApiClientHandler handler = new ApiClientHandlerImpl(
                    new ApiClientParserImpl(),
                    new ImdbCommunicationImpl(logger, new ImdbParserImpl(logger)),
                    logger);
            handler.processAndReply(connection, input, output);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(EnumApiException.SERVER_RUN_IO_ERROR, e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(EnumApiException.SERVER_RUN_IO_ERROR,
                        "ON CLOSING : " + e.getMessage());
            }
        }
    }

    /**
     * Get the server socket already configurated
     *
     * */
    private ServerSocket getServerSocketAlreadyConfigurated() throws ApiException {
        try {
            ServerSocket serverSocket = new ServerSocket(ApiConfig.getPortNumber());
            serverSocket.setReuseAddress(ApiConfig.isAllowedAddressReuse());
            return serverSocket;
        } catch (Exception e) {
            logger.error(EnumApiException.GENERIC_ERROR, e.getMessage());
            throw new ApiException(EnumApiException.SOCKET_GENERATE_ERROR);
        }
    }
}
