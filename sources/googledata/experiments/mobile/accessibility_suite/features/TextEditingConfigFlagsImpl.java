package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextEditingConfigFlagsImpl implements TextEditingConfigFlags {
    public static final ProcessStablePhenotypeFlag activeSpellCheck;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        activeSpellCheck = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("TextEditingConfig__active_spell_check", true, "com.google.android.marvin.talkback", ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK"), true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.TextEditingConfigFlags
    public final boolean activeSpellCheck(Context context) {
        return ((Boolean) activeSpellCheck.get(context)).booleanValue();
    }
}
