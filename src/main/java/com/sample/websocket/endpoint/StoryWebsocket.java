package com.sample.websocket.endpoint;
import com.google.gson.JsonObject;
import com.sample.websocket.codec.Sticker;
import com.sample.websocket.codec.StickerDecoder;
import com.sample.websocket.codec.StickerEncoder;

import java.io.*;
import java.util.*;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
@ServerEndpoint(
        value = "/story/notifications",
        encoders = {StickerEncoder.class},
        decoders = {StickerDecoder.class})
public class StoryWebsocket {
    private static final List<Sticker> stickers = Collections.synchronizedList(new LinkedList<Sticker>());
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void onMessage(Session session, Sticker sticker) {
        stickers.add(sticker);
        System.out.println("invoke onMessage!!!"+sticker);
        for (Session openSession : sessions) {
            try {
                openSession.getBasicRemote().sendObject(sticker);
            } catch (IOException | EncodeException ex) {
                sessions.remove(openSession);
            }
        }
    }
    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        sessions.add(session);
        for (Sticker sticker : stickers) {
            session.getBasicRemote().sendObject(sticker);
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        sessions.remove(session);
    }
}
