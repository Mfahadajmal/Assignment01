package com.google.android.gms.clearcut;

import java.util.EnumSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum PIILevel {
    ZWIEBACK(2),
    ANDROID_ID(4),
    GAIA(8),
    ACCOUNT_NAME(16);

    public static final EnumSet deidentified;
    public static final EnumSet noRestrictions;
    public static final EnumSet zwiebackOnly;
    public final int id;

    static {
        PIILevel pIILevel = ZWIEBACK;
        noRestrictions = EnumSet.allOf(PIILevel.class);
        deidentified = EnumSet.noneOf(PIILevel.class);
        zwiebackOnly = EnumSet.of(pIILevel);
    }

    PIILevel(int i) {
        this.id = i;
    }
}
