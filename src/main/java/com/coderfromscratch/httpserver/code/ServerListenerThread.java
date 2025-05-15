package com.coderfromscratch.httpserver.code;

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
    private String webRoot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webRoot) throws IOException {
        this.port = port;
        this.webRoot = webRoot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {

            while (serverSocket.isBound() && !serverSocket.isClosed())  {
                Socket socket = serverSocket.accept();

                LOGGER.info(" +Connection accepted" + socket.getInetAddress());

                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                // TODO we would read

                // TODO we would write

                String html = "<html><head><title>Java HTTP Server</title></head><body><h1>This page was using my simple Java Http Server</h1></body></html>";

                final String CRLF = "\n\r"; //carriage return and line feed  13,10

                String response =
                        "HTTP/1.1 200 OK" + CRLF +  // Status Line  : HTTP VERSION  RESPONSE_CODE RESPONSE_MESSAGE
                                "Content-Length: " + html.getBytes().length + CRLF +  // HEADER
                                CRLF +
                                html +
                                CRLF + CRLF;

                outputStream.write(response.getBytes());

                inputStream.close();
                outputStream.close();
                socket.close();
            }
            //  serverSocket.close(); ToDO: handle close

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
