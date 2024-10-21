package com.google.android.libraries.performance.primes.persistent;

import android.content.Context;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import dagger.Lazy;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PersistentStorage {
    public final Object PersistentStorage$ar$application;
    public final Object PersistentStorage$ar$sharedPreferences;

    public PersistentStorage(Context context, Provider provider) {
        this.PersistentStorage$ar$application = context;
        this.PersistentStorage$ar$sharedPreferences = provider;
    }

    public PersistentStorage(ProcessStatsCapture processStatsCapture, Lazy lazy) {
        this.PersistentStorage$ar$application = processStatsCapture;
        this.PersistentStorage$ar$sharedPreferences = lazy;
    }
}
