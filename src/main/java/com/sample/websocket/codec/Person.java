package com.sample.websocket.codec;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by zhaoguoyu on 2016/9/19.
 */
@XmlRootElement
public class Person {
    String name;
    String surname;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
