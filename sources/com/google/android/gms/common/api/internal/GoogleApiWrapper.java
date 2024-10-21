package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.api.GoogleApi;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleApiWrapper extends DummyGoogleApiClient {
    private final GoogleApi connectionlessApi;

    public GoogleApiWrapper(GoogleApi googleApi) {
        this.connectionlessApi = googleApi;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final BaseImplementation$ApiMethodImpl enqueue(BaseImplementation$ApiMethodImpl baseImplementation$ApiMethodImpl) {
        this.connectionlessApi.doNonListenerCall$ar$ds(0, baseImplementation$ApiMethodImpl);
        return baseImplementation$ApiMethodImpl;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Looper getLooper() {
        return this.connectionlessApi.looper;
    }
}
