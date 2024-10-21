package androidx.work.impl.utils;

import java.util.WeakHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WakeLocksHolder {
    public static final WakeLocksHolder INSTANCE = new WakeLocksHolder();
    public static final WeakHashMap wakeLocks = new WeakHashMap();

    private WakeLocksHolder() {
    }
}
