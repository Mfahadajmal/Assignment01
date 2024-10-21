package com.google.android.libraries.consentverifier;

import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BaseProtoCollectionBasis {
    private final int javaClassNameHash;

    public BaseProtoCollectionBasis(int i) {
        this.javaClassNameHash = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof BaseProtoCollectionBasis) || this.javaClassNameHash != ((BaseProtoCollectionBasis) obj).javaClassNameHash()) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new int[]{this.javaClassNameHash});
    }

    public final int javaClassNameHash() {
        return this.javaClassNameHash;
    }

    public final String toString() {
        return "java_hash=" + this.javaClassNameHash;
    }

    public void singleCollectionBasis$ar$ds() {
    }
}
