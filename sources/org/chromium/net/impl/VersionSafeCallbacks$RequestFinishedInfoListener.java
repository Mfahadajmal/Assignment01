package org.chromium.net.impl;

import java.util.concurrent.Executor;
import org.chromium.net.RequestFinishedInfo;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VersionSafeCallbacks$RequestFinishedInfoListener extends RequestFinishedInfo.Listener {
    private final RequestFinishedInfo.Listener mWrappedListener;

    public VersionSafeCallbacks$RequestFinishedInfoListener(RequestFinishedInfo.Listener listener) {
        super(listener.getExecutor());
        this.mWrappedListener = listener;
    }

    @Override // org.chromium.net.RequestFinishedInfo.Listener
    public final Executor getExecutor() {
        return this.mWrappedListener.getExecutor();
    }

    @Override // org.chromium.net.RequestFinishedInfo.Listener
    public final void onRequestFinished(RequestFinishedInfo requestFinishedInfo) {
        this.mWrappedListener.onRequestFinished(requestFinishedInfo);
    }
}
