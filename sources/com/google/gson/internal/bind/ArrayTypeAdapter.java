package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.stream.JsonWriter;
import java.lang.reflect.Array;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArrayTypeAdapter extends TypeAdapter {
    public static final TypeAdapterFactory FACTORY = new JsonAdapterAnnotationTypeAdapterFactory.DummyTypeAdapterFactory(1);
    private final TypeAdapter componentTypeAdapter;

    public ArrayTypeAdapter(Gson gson, TypeAdapter typeAdapter, Class cls) {
        this.componentTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, cls);
    }

    @Override // com.google.gson.TypeAdapter
    public final void write(JsonWriter jsonWriter, Object obj) {
        if (obj == null) {
            jsonWriter.nullValue$ar$ds();
            return;
        }
        jsonWriter.beginArray$ar$ds();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.componentTypeAdapter.write(jsonWriter, Array.get(obj, i));
        }
        jsonWriter.endArray$ar$ds();
    }
}
