package com.nic.socket;

import java.net.Socket;

public class LocalPortDetector {

    public static void main(String[] args) {
        scanPort();
    }

    public static void isPortAvailable(Integer port) {
        Socket socket;
        try {
            socket = new Socket("localhost", port);
            boolean connected = socket.isConnected();
            if (connected) {
                System.out.println(">>>" + port);
            }
        } catch (Exception e) {
            System.out.println("---" + port);
        }
    }

    public static void scanPort() {
        long startMilli = System.currentTimeMillis();
        for (int i = 70; i < 100; i++) {
            isPortAvailable(i);
        }
        long endMilli = System.currentTimeMillis();
        System.out.println("time: " + (endMilli - startMilli));
    }
}
