package com.google.gson;

import com.google.gson.internal.LinkedTreeMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JsonObject extends JsonElement {
    public final LinkedTreeMap members = new LinkedTreeMap(LinkedTreeMap.NATURAL_ORDER, false);

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof JsonObject) && ((JsonObject) obj).members.equals(this.members)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.members.hashCode();
    }
}
