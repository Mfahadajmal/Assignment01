package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkbackCompositorFlagsImpl implements TalkbackCompositorFlags {
    public static final ProcessStablePhenotypeFlag enablePeriodAsSeparator;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        enablePeriodAsSeparator = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("TalkbackCompositor__enable_period_as_separator", false, "com.google.android.marvin.talkback", ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK"), true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.TalkbackCompositorFlags
    public final boolean enablePeriodAsSeparator(Context context) {
        return ((Boolean) enablePeriodAsSeparator.get(context)).booleanValue();
    }
}
