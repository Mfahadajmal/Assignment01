package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import java.util.Collections;
import java.util.WeakHashMap;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class GoogleApiClient {

    /* compiled from: PG */
    @Deprecated
    /* loaded from: classes.dex */
    public interface ConnectionCallbacks extends com.google.android.gms.common.api.internal.ConnectionCallbacks {
    }

    /* compiled from: PG */
    @Deprecated
    /* loaded from: classes.dex */
    public interface OnConnectionFailedListener extends com.google.android.gms.common.api.internal.OnConnectionFailedListener {
    }

    static {
        Collections.newSetFromMap(new WeakHashMap());
    }

    public BaseImplementation$ApiMethodImpl enqueue(BaseImplementation$ApiMethodImpl baseImplementation$ApiMethodImpl) {
        throw null;
    }

    public Looper getLooper() {
        throw null;
    }
}
