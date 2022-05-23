package com.example.weatherstation.Model;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Listener extends Thread {

    static List<WebSocketSession> sessions;

    public Listener (List<WebSocketSession> sessions){
        this.sessions = sessions;
        this.start();
    }

    @Override
    public void run() {
        int port = 1312;
        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server is listening on port " + port);
            System.out.println(InetAddress.getLocalHost());
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;

            while((message = in.readLine()) != null) {
                System.out.println(message);
                if(!message.trim().isEmpty()) {
                    synchronized (sessions){
                        for (WebSocketSession webSocketSession : sessions) {
                            webSocketSession.sendMessage(new TextMessage(message));
                        }
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
