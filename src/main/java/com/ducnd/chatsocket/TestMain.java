//package com.ducnd.chatsocket;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.net.Socket;
//
//public class TestMain {
//    private static final Logger LOG = LoggerFactory.getLogger(TestMain.class);
//    public static void main(String[] args) {
//        LOG.info("run main class TestMain");
//        try {
//            Socket socket = new Socket("https://chatfunnsocket.herokuapp.com", 5000);
//            System.out.println("Connected");
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
