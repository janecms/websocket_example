package com.sample.websocket.handler;
import com.google.gson.JsonObject;
import com.sample.websocket.model.Device;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ApplicationScoped;
import javax.websocket.Session;
@ApplicationScoped
public class DeviceSessionHandler {
    private int deviceId = 0;
    private final Set<Session> sessions = new HashSet<>();
    private final Set<Device> devices = new HashSet<>();

    public void addSession(Session session) {
        sessions.add(session);
        for (Device device : devices) {
            JsonObject addMessage = createAddMessage(device);
            sendToSession(session, addMessage);
        }
    }



    public void addDevice(Device device) {
        device.setId(deviceId);
        devices.add(device);
        deviceId++;
        JsonObject addMessage = createAddMessage(device);
        sendToAllConnectedSessions(addMessage);
    }
    public void removeSession(Session session) {
        sessions.remove(session);
    }

    public List<Device> getDevices() {
        return new ArrayList<>(devices);
    }

    public void removeDevice(int id) {
        Device device = getDeviceById(id);
        if (device != null) {
            devices.remove(device);
            JsonObject removeMessage =new JsonObject();
            removeMessage.addProperty("action", "remove");
            removeMessage.addProperty("id", id);
            sendToAllConnectedSessions(removeMessage);
        }
    }

    public void toggleDevice(int id) {
//        JsonProvider provider = JsonProvider.provider();
        Device device = getDeviceById(id);
        if (device != null) {
            if ("On".equals(device.getStatus())) {
                device.setStatus("Off");
            } else {
                device.setStatus("On");
            }
            JsonObject updateDevMessage =new JsonObject();
            updateDevMessage.addProperty("action", "toggle");
            updateDevMessage.addProperty("id", device.getId());
            updateDevMessage.addProperty("status", device.getStatus());
            sendToAllConnectedSessions(updateDevMessage);
        }
    }

    private Device getDeviceById(int id) {
        for (Device device : devices) {
            if (device.getId() == id) {
                return device;
            }
        }
        return null;
    }

    private JsonObject createAddMessage(Device device) {
//        JsonProvider provider = JsonProvider.provider();
        JsonObject jo =new JsonObject();
        jo.addProperty("action", "add");
        jo.addProperty("id", device.getId());
        jo.addProperty("name", device.getName());
        jo.addProperty("type",device.getType());
        jo.addProperty("status", device.getStatus());
        jo.addProperty("description",device.getDescription());
        return jo;
    }

    private void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            ex.printStackTrace();
        }
    }

}
