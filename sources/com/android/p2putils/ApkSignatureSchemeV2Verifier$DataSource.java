package com.android.p2putils;

import java.security.MessageDigest;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ApkSignatureSchemeV2Verifier$DataSource {
    void feedIntoMessageDigests(MessageDigest[] messageDigestArr, long j, int i);

    long size();
}
