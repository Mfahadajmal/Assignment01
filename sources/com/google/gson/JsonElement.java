package com.google.gson;

import com.google.gson.stream.JsonWriter;
import com.google.mlkit.common.internal.model.CustomRemoteModelManager;
import java.io.IOException;
import java.io.StringWriter;

/* compiled from: PG */
/* loaded from: classes.dex */
public class JsonElement {
    @Deprecated
    public JsonElement() {
    }

    public final String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(stringWriter);
            jsonWriter.setStrictness$ar$edu(1);
            CustomRemoteModelManager.write(this, jsonWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
