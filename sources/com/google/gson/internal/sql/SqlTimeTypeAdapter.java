package com.google.gson.internal.sql;

import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.stream.JsonWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SqlTimeTypeAdapter extends TypeAdapter {
    static final TypeAdapterFactory FACTORY = new JsonAdapterAnnotationTypeAdapterFactory.DummyTypeAdapterFactory(4);
    private final DateFormat format = new SimpleDateFormat("hh:mm:ss a");

    @Override // com.google.gson.TypeAdapter
    public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
        String format;
        Time time = (Time) obj;
        if (time == null) {
            jsonWriter.nullValue$ar$ds();
            return;
        }
        synchronized (this) {
            format = this.format.format((Date) time);
        }
        jsonWriter.value$ar$ds$1248a0e6_0(format);
    }
}
