package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.ToNumberPolicy;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ObjectTypeAdapter extends TypeAdapter {
    public static final TypeAdapterFactory DOUBLE_FACTORY = new AnonymousClass1(ToNumberPolicy.DOUBLE, 0);
    private final Gson gson;

    /* compiled from: PG */
    /* renamed from: com.google.gson.internal.bind.ObjectTypeAdapter$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements TypeAdapterFactory {
        final /* synthetic */ Object ObjectTypeAdapter$1$ar$val$toNumberStrategy;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(Object obj, int i) {
            this.switching_field = i;
            this.ObjectTypeAdapter$1$ar$val$toNumberStrategy = obj;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public final TypeAdapter create(Gson gson, TypeToken typeToken) {
            if (this.switching_field != 0) {
                if (typeToken.rawType != Number.class) {
                    return null;
                }
                return (TypeAdapter) this.ObjectTypeAdapter$1$ar$val$toNumberStrategy;
            }
            if (typeToken.rawType != Object.class) {
                return null;
            }
            return new ObjectTypeAdapter(gson);
        }
    }

    public ObjectTypeAdapter(Gson gson) {
        this.gson = gson;
    }

    @Override // com.google.gson.TypeAdapter
    public final void write(JsonWriter jsonWriter, Object obj) {
        if (obj == null) {
            jsonWriter.nullValue$ar$ds();
            return;
        }
        TypeAdapter adapter = this.gson.getAdapter(obj.getClass());
        if (adapter instanceof ObjectTypeAdapter) {
            jsonWriter.beginObject$ar$ds();
            jsonWriter.endObject$ar$ds();
        } else {
            adapter.write(jsonWriter, obj);
        }
    }
}
