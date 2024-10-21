package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Wrappers {
    private static final Wrappers wrappers = new Wrappers();
    private AppLifecycleMonitor packageManagerWrapper$ar$class_merging$ar$class_merging$ar$class_merging = null;

    public final synchronized AppLifecycleMonitor getPackageManagerWrapper$ar$class_merging$ar$class_merging$ar$class_merging(Context context) {
        if (this.packageManagerWrapper$ar$class_merging$ar$class_merging$ar$class_merging == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.packageManagerWrapper$ar$class_merging$ar$class_merging$ar$class_merging = new AppLifecycleMonitor(context);
        }
        return this.packageManagerWrapper$ar$class_merging$ar$class_merging$ar$class_merging;
    }
}
