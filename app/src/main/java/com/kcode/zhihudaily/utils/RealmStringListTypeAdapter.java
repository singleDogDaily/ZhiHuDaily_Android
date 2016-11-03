package com.kcode.zhihudaily.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.kcode.zhihudaily.bean.RealmString;

import java.io.IOException;

import io.realm.RealmList;

/**
 * Created by caik on 2016/11/3.
 */

public class RealmStringListTypeAdapter extends TypeAdapter<RealmList<RealmString>> {
    public static final TypeAdapter<RealmList<RealmString>> INSTANCE =
            new RealmStringListTypeAdapter().nullSafe();

    private RealmStringListTypeAdapter() { }

    @Override public void write(JsonWriter out, RealmList<RealmString> src) throws IOException {
        out.beginArray();
        for(RealmString realmString : src) { out.value(realmString.value); }
        out.endArray();
    }

    @Override public RealmList<RealmString> read(JsonReader in) throws IOException {
        RealmList<RealmString> realmStrings = new RealmList<>();
        in.beginArray();
        while (in.hasNext()) {
            if(in.peek() == JsonToken.NULL) {
                in.nextNull();
            } else {
                RealmString realmString = new RealmString();
                realmString.value = in.nextString();
                realmStrings.add(realmString);
            }
        }
        in.endArray();
        return realmStrings;
    }
}
