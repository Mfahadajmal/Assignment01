package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import com.google.mlkit.common.internal.model.CustomRemoteModelManager;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TreeTypeAdapter extends SerializationDelegatingTypeAdapter {
    private final CustomRemoteModelManager context$ar$class_merging$ar$class_merging = new CustomRemoteModelManager(null);
    private volatile TypeAdapter delegate;
    final Gson gson;
    private final boolean nullSafe;
    private final JsonSerializer serializer;
    private final TypeAdapterFactory skipPastForGetDelegateAdapter;
    private final TypeToken typeToken;

    public TreeTypeAdapter(JsonSerializer jsonSerializer, Gson gson, TypeToken typeToken, TypeAdapterFactory typeAdapterFactory, boolean z) {
        this.serializer = jsonSerializer;
        this.gson = gson;
        this.typeToken = typeToken;
        this.skipPastForGetDelegateAdapter = typeAdapterFactory;
        this.nullSafe = z;
    }

    private final TypeAdapter delegate() {
        TypeAdapter typeAdapter = this.delegate;
        if (typeAdapter != null) {
            return typeAdapter;
        }
        TypeAdapter delegateAdapter = this.gson.getDelegateAdapter(this.skipPastForGetDelegateAdapter, this.typeToken);
        this.delegate = delegateAdapter;
        return delegateAdapter;
    }

    @Override // com.google.gson.internal.bind.SerializationDelegatingTypeAdapter
    public final TypeAdapter getSerializationDelegate() {
        if (this.serializer != null) {
            return this;
        }
        return delegate();
    }

    @Override // com.google.gson.TypeAdapter
    public final void write(JsonWriter jsonWriter, Object obj) {
        JsonSerializer jsonSerializer = this.serializer;
        if (jsonSerializer == null) {
            delegate().write(jsonWriter, obj);
        } else if (this.nullSafe && obj == null) {
            jsonWriter.nullValue$ar$ds();
        } else {
            CustomRemoteModelManager.write(jsonSerializer.serialize$ar$class_merging$ar$ds(), jsonWriter);
        }
    }
}
