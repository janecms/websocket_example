package com.sample.websocket.endpoint;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ServerEndpoint("/multiple")
public class MultipleMessageEndpoint {

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // Send the first message to the client
        session.getBasicRemote().sendText("This is the first server message on "+sdf.format(new Date()));
        // Send 3 messages to the client every 5 seconds
        int sentMessages = 0;
        while (sentMessages < 3) {
            Thread.sleep(5000);
            session.getBasicRemote().
                    sendText("This is an intermediate server message. Count: " + sentMessages+" on "+sdf.format(new Date()));
            sentMessages++;
        }
        // Send a final message to the client
        session.getBasicRemote().sendText("This is the last server message on "+sdf.format(new Date()));
    }

    @OnOpen
    public void onOpen() {
        System.out.println("Client connected");
    }

    @OnClose
    public void onClose() {
        System.out.println("Connection closed");
    }
}
