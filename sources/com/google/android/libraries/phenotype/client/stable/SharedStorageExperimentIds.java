package com.google.android.libraries.phenotype.client.stable;

import com.google.android.gms.clearcut.ClearcutLogger;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SharedStorageExperimentIds {
    public static final /* synthetic */ int SharedStorageExperimentIds$ar$NoOp = 0;
    private static final AtomicBoolean hasSetIds = new AtomicBoolean(false);

    public static void trySetExperimentIds(final int... iArr) {
        if (hasSetIds.compareAndSet(false, true)) {
            ClearcutLogger.addProcessEventModifier(new ClearcutLogger.EventModifier() { // from class: com.google.android.libraries.phenotype.client.stable.SharedStorageExperimentIds$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.clearcut.ClearcutLogger.EventModifier
                public final ClearcutLogger.LogEventBuilder apply(ClearcutLogger.LogEventBuilder logEventBuilder) {
                    int i = SharedStorageExperimentIds.SharedStorageExperimentIds$ar$NoOp;
                    try {
                        logEventBuilder.addExperimentIds$ar$ds(iArr);
                    } catch (IllegalArgumentException unused) {
                    }
                    return logEventBuilder;
                }
            });
        }
    }
}
