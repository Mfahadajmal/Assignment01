package com.google.android.gsf;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GservicesDelegateSupplier {
    private static GservicesQueryCachingDelegate sDelegate$ar$class_merging;

    public static synchronized GservicesQueryCachingDelegate get$ar$class_merging$6aa2c5a8_0() {
        GservicesQueryCachingDelegate gservicesQueryCachingDelegate;
        synchronized (GservicesDelegateSupplier.class) {
            if (sDelegate$ar$class_merging == null) {
                init$ar$class_merging(new GservicesQueryCachingDelegate());
            }
            gservicesQueryCachingDelegate = sDelegate$ar$class_merging;
        }
        return gservicesQueryCachingDelegate;
    }

    public static synchronized void init$ar$class_merging(GservicesQueryCachingDelegate gservicesQueryCachingDelegate) {
        synchronized (GservicesDelegateSupplier.class) {
            if (sDelegate$ar$class_merging == null) {
                sDelegate$ar$class_merging = gservicesQueryCachingDelegate;
            } else {
                throw new IllegalStateException("init() already called");
            }
        }
    }
}
