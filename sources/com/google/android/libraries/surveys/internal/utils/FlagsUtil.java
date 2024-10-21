package com.google.android.libraries.surveys.internal.utils;

import android.content.Context;
import com.google.android.libraries.processinit.CurrentProcess;
import googledata.experiments.mobile.surveys_android.features.Devops;
import googledata.experiments.mobile.surveys_android.features.HatsQuestionnaireBranching;
import googledata.experiments.mobile.surveys_android.features.HatsV1M17Bugfixes;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FlagsUtil {
    public static final Object CONTEXT_LOCK = new Object();
    public static volatile CurrentProcess flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public static volatile Context phenotypeContext;

    public static boolean isBranchingEnabled() {
        if (!isFeatureEnabled(HatsQuestionnaireBranching.enableQuestionnaireBranching(phenotypeContext)) && !isFeatureEnabled(HatsQuestionnaireBranching.enableRatingQuestionnaireBranching(phenotypeContext))) {
            return false;
        }
        return true;
    }

    public static boolean isBugfixEnabled(boolean z) {
        if (Devops.forceDisableAllFlags(phenotypeContext)) {
            return false;
        }
        return z;
    }

    public static boolean isFeatureEnabled(boolean z) {
        if (Devops.forceDisableAllFlags(phenotypeContext)) {
            return false;
        }
        return z;
    }

    public static boolean useSurveyStore() {
        if (phenotypeContext == null) {
            return false;
        }
        return HatsV1M17Bugfixes.INSTANCE.get().sendEventUsingSurveyStore(phenotypeContext);
    }
}
