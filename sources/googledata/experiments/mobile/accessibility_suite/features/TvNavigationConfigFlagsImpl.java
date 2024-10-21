package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;
import com.google.protos.experiments.proto.TypedFeatures$StringListParam;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TvNavigationConfigFlagsImpl implements TvNavigationConfigFlags {
    public static final ProcessStablePhenotypeFlag handlerThreadPriority;
    public static final ProcessStablePhenotypeFlag keyEventTimeoutMillis;
    public static final ProcessStablePhenotypeFlag letSystemHandleDpadCenterWhenFocusNotInSyncNew;
    public static final ProcessStablePhenotypeFlag packagesDpadAllowlist;
    public static final ProcessStablePhenotypeFlag packagesDpadBlocklist;
    public static final ProcessStablePhenotypeFlag useHandlerThread;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        ImmutableSet of = ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK");
        handlerThreadPriority = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$64c97a1b_0("TvNavigationConfig__handler_thread_priority", -10L, "com.google.android.marvin.talkback", of, true, false);
        keyEventTimeoutMillis = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$64c97a1b_0("TvNavigationConfig__key_event_timeout_millis", 1500L, "com.google.android.marvin.talkback", of, true, false);
        letSystemHandleDpadCenterWhenFocusNotInSyncNew = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("TvNavigationConfig__let_system_handle_dpad_center_when_focus_not_in_sync_new", false, "com.google.android.marvin.talkback", of, true, false);
        int i = 4;
        packagesDpadAllowlist = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$f7eda825_0("TvNavigationConfig__packages_dpad_allowlist", new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(i), "Cg1jb20uY2x0di5jbHR2Cg1jb20uY2x0di5mYXN0ChFjb20ubmV0ZmxpeC5uaW5qYQoJY29tLnNsaW5nChNjb20uc2xpbmcuYWlydHZtaW5pChVjb20uc2xpbmcuYWlydHZwbGF5ZXIKEWJlLnB4LnN0YnR2Y2xpZW50ChB0di5wbHV0by5hbmRyb2lk", "com.google.android.marvin.talkback", of, true, false);
        packagesDpadBlocklist = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$f7eda825_0("TvNavigationConfig__packages_dpad_blocklist", new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(i), "", "com.google.android.marvin.talkback", of, true, false);
        useHandlerThread = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("TvNavigationConfig__use_handler_thread", true, "com.google.android.marvin.talkback", of, true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.TvNavigationConfigFlags
    public final long handlerThreadPriority(Context context) {
        return ((Long) handlerThreadPriority.get(context)).longValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.TvNavigationConfigFlags
    public final long keyEventTimeoutMillis(Context context) {
        return ((Long) keyEventTimeoutMillis.get(context)).longValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.TvNavigationConfigFlags
    public final boolean letSystemHandleDpadCenterWhenFocusNotInSyncNew(Context context) {
        return ((Boolean) letSystemHandleDpadCenterWhenFocusNotInSyncNew.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.TvNavigationConfigFlags
    public final TypedFeatures$StringListParam packagesDpadAllowlist(Context context) {
        return (TypedFeatures$StringListParam) packagesDpadAllowlist.get(context);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.TvNavigationConfigFlags
    public final TypedFeatures$StringListParam packagesDpadBlocklist(Context context) {
        return (TypedFeatures$StringListParam) packagesDpadBlocklist.get(context);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.TvNavigationConfigFlags
    public final boolean useHandlerThread(Context context) {
        return ((Boolean) useHandlerThread.get(context)).booleanValue();
    }
}
