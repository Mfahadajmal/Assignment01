package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsV1M17BugfixesFlagsImpl implements HatsV1M17BugfixesFlags {
    public static final ProcessStablePhenotypeFlag openTextLanscapeIssueFix;
    public static final ProcessStablePhenotypeFlag sendEventUsingSurveyStore;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        openTextLanscapeIssueFix = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45386559", true, "com.google.android.libraries.surveys", regularImmutableSet, true, false);
        sendEventUsingSurveyStore = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("45381779", true, "com.google.android.libraries.surveys", regularImmutableSet, true, false);
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M17BugfixesFlags
    public final boolean openTextLanscapeIssueFix(Context context) {
        return ((Boolean) openTextLanscapeIssueFix.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.surveys_android.features.HatsV1M17BugfixesFlags
    public final boolean sendEventUsingSurveyStore(Context context) {
        return ((Boolean) sendEventUsingSurveyStore.get(context)).booleanValue();
    }
}
