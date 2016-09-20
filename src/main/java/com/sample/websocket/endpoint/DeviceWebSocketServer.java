package com.sample.websocket.endpoint;
import com.google.gson.Gson;
import com.sample.websocket.handler.DeviceSessionHandler;
import com.sample.websocket.model.Device;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.StringReader;

@ApplicationScoped
@ServerEndpoint("/actions")
public class DeviceWebSocketServer {
    @Inject
    private DeviceSessionHandler sessionHandler=new DeviceSessionHandler();
    @OnOpen
    public void open(Session session) {
        sessionHandler.addSession(session);
    }

    @OnClose
    public void close(Session session) {
        sessionHandler.removeSession(session);
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        Gson gson = new Gson();
        Device d = gson.fromJson(new StringReader(message), Device.class);

        if ("add".equals(d.getAction())) {
            d.setStatus("Off");
            sessionHandler.addDevice(d);
        }

        if ("remove".equals(d.getAction())) {
            int id = (int) d.getId();
            sessionHandler.removeDevice(id);
        }

        if ("toggle".equals(d.getAction())) {
            int id = (int) d.getId();
            sessionHandler.toggleDevice(id);
        }
    }
}
