package com.google.android.libraries.phenotype.client;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeContextTestMode {
    private static final Object LOCK = new Object();
    public static volatile boolean contextRead = false;
    public static volatile FirstFlagReadHere contextReadStackTrace = null;
    private static volatile boolean testModeRead = false;
    public static volatile FirstFlagReadHere testModeReadStackTrace;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FirstFlagReadHere extends Exception {
    }

    public static void testMode$ar$ds() {
        synchronized (LOCK) {
        }
    }
}
