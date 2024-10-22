package com.presence.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.presence.model.roblox.presence.RobloxPresenceStatus;
import java.io.IOException;

public class RobloxPresenceStatusAdapter extends TypeAdapter<RobloxPresenceStatus> {
  @Override
  public void write(JsonWriter out, RobloxPresenceStatus value) throws IOException {
    out.value(value.toString());
  }

  @Override
  public RobloxPresenceStatus read(JsonReader in) throws IOException {
    return RobloxPresenceStatus.getByApiCode((short) in.nextInt());
  }
}
