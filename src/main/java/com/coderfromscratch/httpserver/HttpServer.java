package com.coderfromscratch.httpserver;

import com.coderfromscratch.httpserver.code.ServerListenerThread;
import com.coderfromscratch.httpserver.config.Configuration;
import com.coderfromscratch.httpserver.config.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Driver Class for Http Server
 **/
public class HttpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {

        LOGGER.info("Server Starting...");

       // System.out.println("Server Starting .....");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");

        Configuration configuration = ConfigurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("Using port: " + configuration.getPort());
        LOGGER.info("Using Webroot: " + configuration.getWebroot());

//        System.out.println("Using port: " + configuration.getPort());
//        System.out.println("Using Webroot: " + configuration.getWebroot());

        // we need our server to do 2 things like it needs to handle tcp connections
        // and it needs to understand http protocol

        // 3 restrictions on our sever
        // 1 . one connection at a time
        // 2 . not going to undertsand
        // 3.

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(configuration.getPort(), configuration.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
