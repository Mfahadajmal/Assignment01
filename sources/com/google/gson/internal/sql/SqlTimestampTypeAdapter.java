package com.google.gson.internal.sql;

import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.stream.JsonWriter;
import java.sql.Timestamp;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SqlTimestampTypeAdapter extends TypeAdapter {
    static final TypeAdapterFactory FACTORY = new JsonAdapterAnnotationTypeAdapterFactory.DummyTypeAdapterFactory(5);
    private final TypeAdapter dateTypeAdapter;

    public SqlTimestampTypeAdapter(TypeAdapter typeAdapter) {
        this.dateTypeAdapter = typeAdapter;
    }

    @Override // com.google.gson.TypeAdapter
    public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
        this.dateTypeAdapter.write(jsonWriter, (Timestamp) obj);
    }
}
