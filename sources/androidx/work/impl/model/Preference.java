package androidx.work.impl.model;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Preference {
    public final String key;
    public final Long value;

    public Preference(String str, Long l) {
        this.key = str;
        this.value = l;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Preference)) {
            return false;
        }
        Preference preference = (Preference) obj;
        if (Intrinsics.areEqual(this.key, preference.key) && Intrinsics.areEqual(this.value, preference.value)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (this.key.hashCode() * 31) + this.value.hashCode();
    }

    public final String toString() {
        return "Preference(key=" + this.key + ", value=" + this.value + ')';
    }
}
