package com.google.android.libraries.performance.primes.transmitter.clearcut;

import com.google.android.gms.usagereporting.InternalUsageReportingClient;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CheckboxChecker {
    public volatile InternalUsageReportingClient usageReportingClient$ar$class_merging;
    public final AtomicReference checkboxEnabled = new AtomicReference();
    public final AtomicBoolean usageReportingOptInOptionsChangedListenerAttached = new AtomicBoolean(false);
}
