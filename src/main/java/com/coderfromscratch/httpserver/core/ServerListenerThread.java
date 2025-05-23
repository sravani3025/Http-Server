package com.coderfromscratch.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);

    private int port;
    private String webroot;
    private ServerSocket serverSocket;


    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {

            Socket socket = serverSocket.accept();
            LOGGER.info("* Connection accepted: "+ socket.getInetAddress() );
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // TODO we would read

            // TODO we would writing

            String html = "<html><head><title>Simple Java Http Server</title></head><body><h1>This page was served using my Simple Java Http Server</h1></body></html>";

            final String CRLF = "\n\r"; //13 , 10 Ascii

            String response =
                    "HTTP/1.1.200 OK" + CRLF + // Status Line : HTTTP VERSION RESPONSE CODE RESPONSE MESSAGE
                            "Content-Length: " + html.getBytes().length + CRLF + //HEADER
                            CRLF +
                            html +
                            CRLF + CRLF;

            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
