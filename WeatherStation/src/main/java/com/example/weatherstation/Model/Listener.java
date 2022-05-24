package com.example.weatherstation.Model;

import com.example.weatherstation.DaoDB.TempDao;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import static org.hibernate.query.criteria.internal.ValueHandlerFactory.isNumeric;

public class Listener extends Thread {

    static List<WebSocketSession> sessions;
    double temperature = 0.00;
    TempDao dao = new TempDao();
    int counter = 0;


    public Listener (List<WebSocketSession> sessions){
        this.sessions = sessions;
        this.start();
    }

    @Override
    public void run() {
        int port = 1312;
        try(ServerSocket serverSocket = new ServerSocket(port)){
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;

            DateFormat dateFormatter = new SimpleDateFormat("HH:mm");
            Date date = dateFormatter .parse("00:00");
            Date date_reset = dateFormatter .parse("00:01");

            while((message = in.readLine()) != null) {
                if (!message.trim().isEmpty()) {
                    synchronized (sessions) {
                        for (WebSocketSession webSocketSession : sessions) {
                            webSocketSession.sendMessage(new TextMessage(message));
                        }
                    }

                    try {
                        temperature = Double.parseDouble(message);
                    } catch (Exception e) {
                        System.out.println("Cant find double");
                        e.printStackTrace();
                    }

                    LocalTime now = LocalTime.now();
                    if (now.getMinute() == date.getMinutes() && counter == 0) {
                        System.out.println("temperature added " + temperature);
                        dao.add_new_data(temperature);
                        counter++;
                    } else if (now.getMinute() == date_reset.getMinutes() && counter == 1) {
                        counter = 0;
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
