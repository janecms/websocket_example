package com.sample.websocket.codec;

import java.io.StringReader;
 
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import javax.xml.bind.*;
 
  
public class MessageDecoder implements Decoder.Text<Person> {
 
    @Override
    public Person decode(String s) {
        System.out.println("Incoming XML " + s);
        Person person = null;
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Person.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(s);
            person = (Person) unmarshaller.unmarshal(reader);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return person;
    }
 
    @Override
    public boolean willDecode(String s) {
          
        return (s != null);
    }
 
    @Override
    public void init(EndpointConfig endpointConfig) {
        // do nothing.
    }
 
    @Override
    public void destroy() {
        // do nothing.
    }
}