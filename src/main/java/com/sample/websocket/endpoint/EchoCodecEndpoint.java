package com.sample.websocket.endpoint;

import com.sample.websocket.codec.MessageDecoder;
import com.sample.websocket.codec.MessageEncoder;
import com.sample.websocket.codec.Person;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 *对socket信息加解密
 */
@ServerEndpoint(
        value="/codec",
        decoders = {
        MessageDecoder.class},
        encoders = {
                MessageEncoder.class
        }
)
public class EchoCodecEndpoint {
    @OnMessage
    public Person onMessage(Person person, Session session) {
        if (person.getName().equals("john")) {
            person.setName("Mr. John");
        }
        try {
            session.getBasicRemote().sendObject(person);
            System.out.println("sent ");
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return person;
    }

    @OnOpen
    public void myOnOpen(Session session) {
        System.out.println("WebSocket opened: " + session.getId());
    }

    @OnClose
    public void myOnClose(CloseReason reason) {
        System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
    }
}