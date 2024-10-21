package com.google.android.accessibility.selecttospeak;

import com.google.common.base.Supplier;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class PrimesController$$ExternalSyntheticLambda9 implements Supplier {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ PrimesController$$ExternalSyntheticLambda9(int i) {
        this.switching_field = i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0022, code lost:
    
        if (r0.importance >= 400) goto L14;
     */
    @Override // com.google.common.base.Supplier
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object get() {
        /*
            r6 = this;
            int r0 = r6.switching_field
            java.lang.String r1 = "AICore service disconnected"
            r2 = 6
            r3 = 1
            r4 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r4)
            switch(r0) {
                case 0: goto L8d;
                case 1: goto L8a;
                case 2: goto L87;
                case 3: goto L82;
                case 4: goto L7d;
                case 5: goto L78;
                case 6: goto L77;
                case 7: goto L77;
                case 8: goto L77;
                case 9: goto L72;
                case 10: goto L6c;
                case 11: goto L67;
                case 12: goto L4c;
                case 13: goto L46;
                case 14: goto L38;
                case 15: goto L33;
                case 16: goto L14;
                default: goto Le;
            }
        Le:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            throw r0
        L14:
            android.app.ActivityManager$RunningAppProcessInfo r0 = new android.app.ActivityManager$RunningAppProcessInfo
            r0.<init>()
            android.app.ActivityManager.getMyMemoryState(r0)     // Catch: java.lang.RuntimeException -> L25
            int r1 = r0.importance
            int r0 = r0.importance
            r1 = 400(0x190, float:5.6E-43)
            if (r0 < r1) goto L2d
            goto L2e
        L25:
            r0 = move-exception
            java.lang.String r1 = "PhenotypeProcessReaper"
            java.lang.String r2 = "Failed to retrieve memory state, not killing process."
            android.util.Log.w(r1, r2, r0)
        L2d:
            r3 = r4
        L2e:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)
            return r0
        L33:
            com.google.common.base.Supplier r0 = com.google.android.libraries.phenotype.client.lockdown.resources.FlagExemptionResource.exemptions
            com.google.common.collect.EmptyImmutableSetMultimap r0 = com.google.common.collect.EmptyImmutableSetMultimap.INSTANCE
            return r0
        L38:
            com.google.android.play.core.splitcompat.BackgroundExecutor$1 r0 = new com.google.android.play.core.splitcompat.BackgroundExecutor$1
            r0.<init>(r3)
            java.util.concurrent.ScheduledExecutorService r0 = java.util.concurrent.Executors.newSingleThreadScheduledExecutor(r0)
            com.google.common.util.concurrent.ListeningScheduledExecutorService r0 = com.google.common.flogger.context.ContextDataProvider.listeningDecorator(r0)
            return r0
        L46:
            logs.proto.wireless.performance.mobile.SystemHealthMetricCollectionBasisHelper$SystemHealthMetric r0 = new logs.proto.wireless.performance.mobile.SystemHealthMetricCollectionBasisHelper$SystemHealthMetric
            r0.<init>()
            return r0
        L4c:
            boolean r0 = android.app.ActivityManager.isUserAMonkey()
            if (r0 == 0) goto L53
            goto L62
        L53:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 29
            if (r0 >= r1) goto L5e
            boolean r3 = android.app.ActivityManager.isRunningInTestHarness()
            goto L62
        L5e:
            boolean r3 = org.chromium.base.PathUtils$$ExternalSyntheticApiModelOutline0.m288m()
        L62:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)
            return r0
        L67:
            com.google.common.base.Optional r0 = com.google.android.libraries.performance.primes.metrics.memory.MemoryUsageCapture.lambda$static$0()
            return r0
        L6c:
            com.google.android.libraries.mdi.download.debug.dagger.MddDebugListFragment r0 = new com.google.android.libraries.mdi.download.debug.dagger.MddDebugListFragment
            r0.<init>()
            return r0
        L72:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)
            return r0
        L77:
            return r5
        L78:
            com.google.android.apps.aicore.client.api.AiCoreException r0 = com.google.android.apps.aicore.client.api.AiCoreException.newDownloadError(r2, r1)
            return r0
        L7d:
            com.google.android.apps.aicore.client.api.AiCoreException r0 = com.google.android.apps.aicore.client.api.AiCoreException.newPreparationError(r2, r1)
            return r0
        L82:
            com.google.android.apps.aicore.client.api.AiCoreException r0 = com.google.android.apps.aicore.client.api.AiCoreException.newPreparationError(r2, r1)
            return r0
        L87:
            com.google.android.libraries.performance.primes.NoPiiString r0 = com.google.android.accessibility.talkback.PrimesController.COMPONENT_NAME
            return r0
        L8a:
            com.google.android.libraries.performance.primes.NoPiiString r0 = com.google.android.accessibility.accessibilitymenu.PrimesController.COMPONENT_NAME
            return r0
        L8d:
            com.google.android.libraries.performance.primes.NoPiiString r0 = com.google.android.accessibility.selecttospeak.PrimesController.COMPONENT_NAME
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda9.get():java.lang.Object");
    }
}
