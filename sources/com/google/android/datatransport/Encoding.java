package com.google.android.datatransport;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Encoding {
    private final String name;

    public Encoding(String str) {
        this.name = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Encoding)) {
            return false;
        }
        return this.name.equals(((Encoding) obj).name);
    }

    public final int hashCode() {
        return this.name.hashCode() ^ 1000003;
    }

    public final String toString() {
        return "Encoding{name=\"" + this.name + "\"}";
    }
}
