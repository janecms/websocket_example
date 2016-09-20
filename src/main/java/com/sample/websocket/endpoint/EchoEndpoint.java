package com.sample.websocket.endpoint;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
/**
 *一个socket 发送一条信息
 */
@ServerEndpoint("/echo")
public class EchoEndpoint {
    @OnMessage
    public String onMessage(String message,Session session) {
        System.out.println("Received : "+ message);
        return message+"-"+session.getId();
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