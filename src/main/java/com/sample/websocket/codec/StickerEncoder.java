package com.sample.websocket.codec;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.io.Writer;

public class StickerEncoder implements Encoder.TextStream<Sticker> {

  @Override
  public void encode(Sticker sticker, Writer writer) throws EncodeException, IOException {
    /*
    JsonProvider provider = JsonProvider.provider();
    JsonObject jsonSticker = provider.createObjectBuilder()
            .add("action", "add")
            .add("x", sticker.getX())
            .add("y", sticker.getY())
            .add("sticker", sticker.getImage())
            .build();
    try (JsonWriter jsonWriter = provider.createWriter(writer)) {
      jsonWriter.write(jsonSticker);
    }*/
      sticker.setAction("add");
      Gson gson = new Gson();
      String jsonString=gson.toJson(sticker);
      writer.write(jsonString);
  }

  @Override
  public void init(EndpointConfig ec) {
  }

  @Override
  public void destroy() {
  }
}