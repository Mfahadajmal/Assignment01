package com.google.android.datatransport.runtime;

import android.content.Context;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TransportRuntime {
    public static volatile TransportRuntime instance;
    public final Context context;

    public TransportRuntime(Context context) {
        this.context = context;
    }
}
