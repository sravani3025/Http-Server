package com.coderfromscratch.httpserver;

import com.coderfromscratch.httpserver.config.Configuration;
import com.coderfromscratch.httpserver.config.ConfigurationManager;
import com.coderfromscratch.httpserver.core.ServerListenerThread;
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

//        System.out.println("Server Starting .....");

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
            ServerListenerThread serverListenerThread = new ServerListenerThread(configuration.getPort(),
                    configuration.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // moved this to serverListenerThread class
//        try {
//            ServerSocket serverSocket = new ServerSocket(configuration.getPort());
//            Socket socket = serverSocket.accept();
//            InputStream inputStream = socket.getInputStream();
//            OutputStream outputStream = socket.getOutputStream();
//            // TODO we would read
//            // TODO we would writing
//            String html = "<html><head><title>Simple Java Http Server</title></head><body><h1>This page was served using my Simple Java Http Server</h1></body></html>";
//            final String CRLF = "\n\r"; //13 , 10 Ascii
//            String response =
//                    "HTTP/1.1.200 OK" + CRLF + // Status Line : HTTTP VERSION RESPONSE CODE RESPONSE MESSAGE
//                            "Content-Length: " + html.getBytes().length + CRLF + //HEADER
//                            CRLF +
//                            html +
//                            CRLF + CRLF;
//
//            outputStream.write(response.getBytes());
//            inputStream.close();
//            outputStream.close();
//            socket.close();
//            serverSocket.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }

    }
}
