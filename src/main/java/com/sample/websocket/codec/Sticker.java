package com.sample.websocket.codec;

import com.google.gson.annotations.SerializedName;

public class Sticker {
    private String action;
    private int x;
    private int y;
    @SerializedName("sticker")
    private String image;

    public Sticker() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Sticker{" +
                "x=" + x +
                ", y=" + y +
                ", image='" + image + '\'' +
                '}';
    }
}