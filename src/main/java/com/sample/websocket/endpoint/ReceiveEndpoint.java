package com.sample.websocket.endpoint;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Set;

@ServerEndpoint("/config")
public class ReceiveEndpoint {
   @OnMessage
   public void textMessage(Session session, String msg) {
      System.out.println("Text message: " + msg);
      Set<Session> openSessions = session.getOpenSessions();
      System.out.println(" session.OpenSessions.size111111111111111=" + openSessions.size());
   }
   @OnMessage
   public void binaryMessage(Session session, ByteBuffer msg) {
      System.out.println("Binary message: " + msg.toString());
   }
   @OnMessage
   public void pongMessage(Session session, PongMessage msg) {
      System.out.println("Pong message: " +  msg.getApplicationData().toString());
   }

   @OnClose
   public void nClose(Session session, CloseReason reason) {
      System.out.println("Pong message: " +  reason.getReasonPhrase()+"-->"+reason.getCloseCode());
   }

   @OnError
   public void onError(Throwable t){
      t.printStackTrace();
   }
   @OnOpen
   public void onOpen(Session session){
      System.out.println("session.getId()=" + session.getId());

      Set<Session> openSessions = session.getOpenSessions();
      System.out.println(" session.OpenSessions.size=" + openSessions.size());
      session.getUserProperties().put("userName","Guest"+session.getId());

      Iterator<Session> iterator = openSessions.iterator();
      System.out.println("open session list===========");
      while(iterator.hasNext()){
         Session s = iterator.next();
         String sid = s.getId();
         System.out.println("session.id==>"+ sid);
         Object user = s.getUserProperties().get("userName");
         System.out.println("session."+sid+"username==>"+user);
      }
      System.out.println();
      System.out.println();

      WebSocketContainer wsc = session.getContainer();
//      wsc.setDefaultMaxBinaryMessageBufferSize(81920);
      System.out.println("WebSocketContainer Info==========");
      System.out.println("webSocketContainer=" + wsc);
      System.out.println("defaultAsyncTimeout->"+wsc.getDefaultAsyncSendTimeout());
      System.out.println("defaultMaxBinaryMessageBufferSize-->"+wsc.getDefaultMaxBinaryMessageBufferSize());
      System.out.println("defaultMaxSessionIdleTimeout-->"+wsc.getDefaultMaxSessionIdleTimeout());

      System.out.println("installedExtensions.size==>" + wsc.getInstalledExtensions().size());
      System.out.println();
      System.out.println();
      System.out.println("session parameter");
      System.out.println("session.getMaxBinaryMessageBufferSize()==>"+session.getMaxBinaryMessageBufferSize());
      System.out.println("session.getMaxIdleTimeout()==>"+session.getMaxIdleTimeout());
      System.out.println("session.getNegotiatedSubprotocol()==>"+session.getNegotiatedSubprotocol());
      System.out.println("session.getProtocolVersion()==>"+session.getProtocolVersion());
      System.out.println("session.getRequestURI()==>" + session.getRequestURI());
      System.out.println();
      System.out.println();
      Set<MessageHandler> messageHandlers = session.getMessageHandlers();
      Iterator<MessageHandler> messageHandlerIterator = messageHandlers.iterator();
      System.out.println("MessageHandlers");
      while(messageHandlerIterator.hasNext()){
         MessageHandler handler = messageHandlerIterator.next();
         System.out.println(handler.toString());
      }
      System.out.println("session.getUserPrincipal().getName()==>"+session.getUserPrincipal());
   }
}