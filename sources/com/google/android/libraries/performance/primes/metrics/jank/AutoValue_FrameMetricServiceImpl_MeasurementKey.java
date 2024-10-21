package com.google.android.libraries.performance.primes.metrics.jank;

import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class AutoValue_FrameMetricServiceImpl_MeasurementKey extends FrameMetricServiceImpl.MeasurementKey {
    public final NoPiiString noPiiEventName;

    public AutoValue_FrameMetricServiceImpl_MeasurementKey(NoPiiString noPiiString) {
        this.noPiiEventName = noPiiString;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl.MeasurementKey
    public final NoPiiString noPiiEventName() {
        return this.noPiiEventName;
    }

    public final String toString() {
        return "MeasurementKey{rawStringEventName=null, noPiiEventName=" + this.noPiiEventName.value + ", isActivity=true}";
    }

    @Override // com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl.MeasurementKey
    public final void isActivity$ar$ds() {
    }
}
