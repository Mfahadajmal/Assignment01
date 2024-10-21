package org.chromium.net.impl;

import java.util.concurrent.Executor;
import org.chromium.net.NetworkQualityThroughputListener;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VersionSafeCallbacks$NetworkQualityThroughputListenerWrapper extends NetworkQualityThroughputListener {
    private final NetworkQualityThroughputListener mWrappedListener;

    public VersionSafeCallbacks$NetworkQualityThroughputListenerWrapper(NetworkQualityThroughputListener networkQualityThroughputListener) {
        super(networkQualityThroughputListener.getExecutor());
        this.mWrappedListener = networkQualityThroughputListener;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof VersionSafeCallbacks$NetworkQualityThroughputListenerWrapper)) {
            return this.mWrappedListener.equals(((VersionSafeCallbacks$NetworkQualityThroughputListenerWrapper) obj).mWrappedListener);
        }
        return false;
    }

    @Override // org.chromium.net.NetworkQualityThroughputListener
    public final Executor getExecutor() {
        return this.mWrappedListener.getExecutor();
    }

    public final int hashCode() {
        return this.mWrappedListener.hashCode();
    }

    @Override // org.chromium.net.NetworkQualityThroughputListener
    public final void onThroughputObservation(int i, long j, int i2) {
        this.mWrappedListener.onThroughputObservation(i, j, i2);
    }
}
