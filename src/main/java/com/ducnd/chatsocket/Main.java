package com.ducnd.chatsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@EnableScheduling
@EnableAsync(proxyTargetClass = true)
@EnableCaching
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private static ServerSocket serverSocket;

    public static void main(String[] args) {

//        SpringApplication.run(Main.class, args);
        initSocket(5000);
    }

    private static void initSocket(int thePort) {
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
