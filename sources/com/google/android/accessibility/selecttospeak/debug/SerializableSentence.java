package com.google.android.accessibility.selecttospeak.debug;

import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SerializableSentence implements Serializable {
    private final List sentenceBounds;
    private final List words;

    public SerializableSentence(List list, List list2) {
        this.words = list;
        this.sentenceBounds = list2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SerializableSentence)) {
            return false;
        }
        SerializableSentence serializableSentence = (SerializableSentence) obj;
        if (Intrinsics.areEqual(this.words, serializableSentence.words) && Intrinsics.areEqual(this.sentenceBounds, serializableSentence.sentenceBounds)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (this.words.hashCode() * 31) + this.sentenceBounds.hashCode();
    }

    public final String toString() {
        return "SerializableSentence(words=" + this.words + ", sentenceBounds=" + this.sentenceBounds + ")";
    }
}
