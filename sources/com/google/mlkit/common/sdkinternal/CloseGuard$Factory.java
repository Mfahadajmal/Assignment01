package com.google.mlkit.common.sdkinternal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CloseGuard$Factory {
    private static CloseGuard$Factory instance$ar$class_merging$4d1a1486_0;

    public static synchronized void getInstance$ar$ds$cb56d710_0() {
        synchronized (CloseGuard$Factory.class) {
            if (instance$ar$class_merging$4d1a1486_0 == null) {
                instance$ar$class_merging$4d1a1486_0 = new CloseGuard$Factory();
            }
        }
    }
}
