package com.google.android.libraries.performance.primes;

import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService;
import com.google.android.libraries.performance.primes.metrics.jank.DisplayStats;
import com.google.common.collect.ImmutableSet;
import com.google.frameworks.client.data.android.cronet.CronetConfigurations;
import dagger.internal.Factory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Shutdown_Factory implements Factory {
    private final /* synthetic */ int switching_field;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class InstanceHolder {
        public static final Shutdown_Factory INSTANCE = new Shutdown_Factory(0);
    }

    public Shutdown_Factory(int i) {
        this.switching_field = i;
    }

    @Override // javax.inject.Provider
    public final /* synthetic */ Object get() {
        char[] cArr = null;
        switch (this.switching_field) {
            case 0:
                return new Shutdown();
            case 1:
                return new SpannableUtils$NonCopyableTextSpan();
            case 2:
                return new BatteryMetricService(cArr);
            case 3:
                return new BatteryMetricService(cArr);
            case 4:
                return new BatteryMetricService(cArr);
            case 5:
                return new DisplayStats();
            case 6:
                return true;
            default:
                ImmutableSet immutableSet = CronetConfigurations.DEFAULT_QUIC_HINTS;
                immutableSet.getClass();
                return immutableSet;
        }
    }
}
