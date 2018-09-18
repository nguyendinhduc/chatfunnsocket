package com.ducnd.chatsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class SocketManager implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(SocketManager.class);
    private ServerSocket serverSocket;

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        int thePort = event.getEmbeddedServletContainer().getPort();
        LOG.info("debug onApplicationEvent port: " + thePort);
        LOG.debug("debug onApplicationEvent port: " + thePort);
//        initSocket(thePort);
    }

    private void initSocket(int thePort) {
        if (serverSocket != null) {
            return;
        }
        try {
            serverSocket = new ServerSocket(thePort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    socket.close();
                    LOG.info("initSocket close socket............");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
