package com.presence.adapter;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.presence.model.RobloxPresenceStatus;

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
