package com.sample.websocket.endpoint;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Random;

/**
 *一个socket 发送一条信息
 */
@ServerEndpoint("/fileupload")
public class FileUploadEndpoint {
    @OnMessage
    public String onMessage(String message,Session session) {
        System.out.println("Received : " + message);
        int random = new Random().nextInt(5);
        if(random>=3){
            throw new RuntimeException("自定义异常");
        }else{
            System.out.println("random="+random);
        }
        return message+"-"+session.getId();
    }

    @OnMessage
    public String sendBinary(ByteBuffer data,Session session) {
        System.out.println("Received : " + data);
        int random = new Random().nextInt(5);
        if(random>=3){
            throw new RuntimeException("自定义异常");
        }else{
            System.out.println("random="+random);
        }
        return session.getId();
    }

    @OnOpen
    public void myOnOpen(Session session) {
        session.getUserProperties().put("startTime",new Date());
        System.out.println("WebSocket opened: " + session.getId());
    }

    @OnClose
    public void myOnClose(CloseReason reason) {
        System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
    }

    @OnError
    public void error(Session session, Throwable t) {
        System.out.println("发生错误，请注意");
        t.printStackTrace();
    }
}