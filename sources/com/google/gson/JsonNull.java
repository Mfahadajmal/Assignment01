package com.google.gson;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JsonNull extends JsonElement {
    @Deprecated
    public JsonNull() {
    }

    public final boolean equals(Object obj) {
        return obj instanceof JsonNull;
    }

    public final int hashCode() {
        return JsonNull.class.hashCode();
    }
}
