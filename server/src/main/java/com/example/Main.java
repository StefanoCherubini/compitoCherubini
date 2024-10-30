package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = new ServerSocket(3000);

        System.out.println("Server connesso");
        
        while (true) {
            Socket socket = serverSocket.accept();
            Biglietti b = new Biglietti(100);
            System.out.println("Client connesso");
            Gestore gs = new Gestore(socket, b);
            gs.start();
        }
    }
}