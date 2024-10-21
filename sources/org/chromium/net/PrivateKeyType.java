package org.chromium.net;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: PG */
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes.dex */
public @interface PrivateKeyType {
    public static final int ECDSA = 2;
    public static final int INVALID = 255;
    public static final int RSA = 0;
}
