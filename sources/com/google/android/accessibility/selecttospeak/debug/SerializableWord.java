package com.google.android.accessibility.selecttospeak.debug;

import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SerializableWord implements Serializable {
    private final List bound;
    private final int wordTextLength;

    public SerializableWord(List list, int i) {
        this.bound = list;
        this.wordTextLength = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SerializableWord)) {
            return false;
        }
        SerializableWord serializableWord = (SerializableWord) obj;
        if (Intrinsics.areEqual(this.bound, serializableWord.bound) && this.wordTextLength == serializableWord.wordTextLength) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (this.bound.hashCode() * 31) + this.wordTextLength;
    }

    public final String toString() {
        return "SerializableWord(bound=" + this.bound + ", wordTextLength=" + this.wordTextLength + ")";
    }
}
