package com.google.android.libraries.surveys.internal.config;

import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.mlkit.common.model.RemoteModelManager;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SurveyConfigProvider {
    public static final SurveyConfigProvider instance = new SurveyConfigProvider();
    public boolean feedback1pEnabled;
    public RemoteModelManager.RemoteModelManagerRegistration networkCallerProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public StatsStorage pseudonymousIdProvider$ar$class_merging$ar$class_merging$ar$class_merging;

    public final String getSconeApiEndpoint() {
        if (this.feedback1pEnabled) {
            return "feedback-pa.googleapis.com";
        }
        return "scone-pa.googleapis.com";
    }
}
