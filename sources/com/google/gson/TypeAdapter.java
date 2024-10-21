package com.google.gson;

import com.google.gson.stream.JsonWriter;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class TypeAdapter {

    /* compiled from: PG */
    /* renamed from: com.google.gson.TypeAdapter$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends TypeAdapter {
        public AnonymousClass1() {
        }

        @Override // com.google.gson.TypeAdapter
        public final void write(JsonWriter jsonWriter, Object obj) {
            if (obj == null) {
                jsonWriter.nullValue$ar$ds();
            } else {
                TypeAdapter.this.write(jsonWriter, obj);
            }
        }
    }

    public abstract void write(JsonWriter jsonWriter, Object obj);
}
