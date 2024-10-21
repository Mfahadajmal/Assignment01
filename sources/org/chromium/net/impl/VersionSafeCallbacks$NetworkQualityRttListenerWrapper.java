package org.chromium.net.impl;

import java.util.concurrent.Executor;
import org.chromium.net.NetworkQualityRttListener;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VersionSafeCallbacks$NetworkQualityRttListenerWrapper extends NetworkQualityRttListener {
    private final NetworkQualityRttListener mWrappedListener;

    public VersionSafeCallbacks$NetworkQualityRttListenerWrapper(NetworkQualityRttListener networkQualityRttListener) {
        super(networkQualityRttListener.getExecutor());
        this.mWrappedListener = networkQualityRttListener;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof VersionSafeCallbacks$NetworkQualityRttListenerWrapper)) {
            return this.mWrappedListener.equals(((VersionSafeCallbacks$NetworkQualityRttListenerWrapper) obj).mWrappedListener);
        }
        return false;
    }

    @Override // org.chromium.net.NetworkQualityRttListener
    public final Executor getExecutor() {
        return this.mWrappedListener.getExecutor();
    }

    public final int hashCode() {
        return this.mWrappedListener.hashCode();
    }

    @Override // org.chromium.net.NetworkQualityRttListener
    public final void onRttObservation(int i, long j, int i2) {
        this.mWrappedListener.onRttObservation(i, j, i2);
    }
}
