package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface MemoryFeatureFlags {
    boolean memoizeConfigsProvider(Context context);

    SystemHealthProto$SamplingParameters memorySamplingParameters(Context context);

    long periodicMemoryCollectionPeriodMs(Context context);

    boolean readCorrectProcStatus(Context context);
}
