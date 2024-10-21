package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PendingResultUtil {
    private static final StrictModeUtils$VmPolicyBuilderCompatS DEFAULT_STATUS_CONVERTER$ar$class_merging$ar$class_merging$ar$class_merging = new StrictModeUtils$VmPolicyBuilderCompatS();

    public static Task toVoidTask(final PendingResult pendingResult) {
        final StrictModeUtils$VmPolicyBuilderCompatS strictModeUtils$VmPolicyBuilderCompatS = new StrictModeUtils$VmPolicyBuilderCompatS();
        final StrictModeUtils$VmPolicyBuilderCompatS strictModeUtils$VmPolicyBuilderCompatS2 = DEFAULT_STATUS_CONVERTER$ar$class_merging$ar$class_merging$ar$class_merging;
        final AppLifecycleMonitor appLifecycleMonitor = new AppLifecycleMonitor((short[]) null);
        pendingResult.addStatusListener(new PendingResult.StatusListener() { // from class: com.google.android.gms.common.internal.PendingResultUtil.2
            @Override // com.google.android.gms.common.api.PendingResult.StatusListener
            public final void onComplete(Status status) {
                if (status.isSuccess()) {
                    PendingResult.this.await$ar$ds(TimeUnit.MILLISECONDS);
                    appLifecycleMonitor.setResult(null);
                } else {
                    appLifecycleMonitor.setException(StrictModeUtils$VmPolicyBuilderCompatS.fromStatus(status));
                }
            }
        });
        return (Task) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker;
    }
}
