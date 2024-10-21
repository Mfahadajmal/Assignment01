package com.google.android.gms.common.stats;

import android.content.Context;
import android.content.ServiceConnection;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.NoSuchElementException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConnectionTracker {
    public static volatile ConnectionTracker instance;
    public static final Object singletonLock = new Object();
    public final ConcurrentHashMap connectionMap = new ConcurrentHashMap();

    private static void unbindServiceUnwrapped(Context context, ServiceConnection serviceConnection) {
        try {
            context.unbindService(serviceConnection);
        } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException unused) {
        }
    }

    public final void unbindService(Context context, ServiceConnection serviceConnection) {
        unbindServiceUnwrapped(context, serviceConnection);
    }
}
