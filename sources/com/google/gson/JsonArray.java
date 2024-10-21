package com.google.gson;

import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JsonArray extends JsonElement implements Iterable {
    private final ArrayList elements = new ArrayList();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof JsonArray) && ((JsonArray) obj).elements.equals(this.elements)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.elements.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.elements.iterator();
    }
}
