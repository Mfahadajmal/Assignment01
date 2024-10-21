package com.google.android.libraries.performance.primes.metrics.crash;

import android.os.SystemClock;
import com.google.common.base.Supplier;
import javax.inject.Provider;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class EarlyCrashLoopMonitor {
    public final Provider flags;
    public final Supplier processNameSupplier;
    public final long startTimeMs = SystemClock.uptimeMillis();
    public final Supplier storageDirSupplier;

    public EarlyCrashLoopMonitor(Supplier supplier, Supplier supplier2, Provider provider) {
        this.storageDirSupplier = supplier;
        this.processNameSupplier = supplier2;
        this.flags = provider;
    }
}
