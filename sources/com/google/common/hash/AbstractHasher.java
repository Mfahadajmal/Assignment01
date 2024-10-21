package com.google.common.hash;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class AbstractHasher implements Hasher {
    @Override // com.google.common.hash.Hasher
    public final Hasher putBytes(byte[] bArr) {
        return putBytes$ar$ds(bArr, bArr.length);
    }

    public Hasher putBytes$ar$ds(byte[] bArr, int i) {
        throw null;
    }
}
