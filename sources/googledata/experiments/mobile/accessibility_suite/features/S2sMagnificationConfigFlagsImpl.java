package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2sMagnificationConfigFlagsImpl implements S2sMagnificationConfigFlags {
    public static final ProcessStablePhenotypeFlag embeddedDisplay;
    public static final ProcessStablePhenotypeFlag inflateOnDemand;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        ImmutableSet of = ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK");
        embeddedDisplay = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("S2sMagnificationConfig__embedded_display", true, "com.google.android.marvin.talkback", of, true, false);
        inflateOnDemand = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("S2sMagnificationConfig__inflate_on_demand", true, "com.google.android.marvin.talkback", of, true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.S2sMagnificationConfigFlags
    public final boolean embeddedDisplay(Context context) {
        return ((Boolean) embeddedDisplay.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.S2sMagnificationConfigFlags
    public final boolean inflateOnDemand(Context context) {
        return ((Boolean) inflateOnDemand.get(context)).booleanValue();
    }
}
