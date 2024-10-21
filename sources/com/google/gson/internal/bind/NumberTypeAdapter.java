package com.google.gson.internal.bind;

import com.google.gson.ToNumberPolicy;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.stream.JsonWriter;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NumberTypeAdapter extends TypeAdapter {
    public static final TypeAdapterFactory LAZILY_PARSED_NUMBER_FACTORY = newFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);

    private NumberTypeAdapter() {
    }

    public static TypeAdapterFactory newFactory(ToNumberStrategy toNumberStrategy) {
        return new ObjectTypeAdapter.AnonymousClass1(new NumberTypeAdapter(), 1);
    }

    @Override // com.google.gson.TypeAdapter
    public final /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
        jsonWriter.value$ar$ds$8dbd521e_0((Number) obj);
    }
}
