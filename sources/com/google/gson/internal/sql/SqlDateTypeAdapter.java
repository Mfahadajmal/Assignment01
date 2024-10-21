package com.google.gson.internal.sql;

import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.stream.JsonWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SqlDateTypeAdapter extends TypeAdapter {
    static final TypeAdapterFactory FACTORY = new JsonAdapterAnnotationTypeAdapterFactory.DummyTypeAdapterFactory(3);
    private final DateFormat format = new SimpleDateFormat("MMM d, yyyy");

    @Override // com.google.gson.TypeAdapter
    public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
        String format;
        Date date = (Date) obj;
        if (date == null) {
            jsonWriter.nullValue$ar$ds();
            return;
        }
        synchronized (this) {
            format = this.format.format((java.util.Date) date);
        }
        jsonWriter.value$ar$ds$1248a0e6_0(format);
    }
}
