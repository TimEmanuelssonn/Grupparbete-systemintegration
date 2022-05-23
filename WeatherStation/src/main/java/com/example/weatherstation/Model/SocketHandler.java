package com.example.weatherstation.Model;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SocketHandler extends TextWebSocketHandler {

    //Thread safe list
    List <WebSocketSession>sessions = new CopyOnWriteArrayList<>();
    Listener li = new Listener(sessions);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Socket has been added!");
        sessions.add(session);
        System.out.println(sessions.size());
    }
}
