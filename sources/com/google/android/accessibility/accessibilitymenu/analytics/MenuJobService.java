package com.google.android.accessibility.accessibilitymenu.analytics;

import android.app.job.JobParameters;
import android.app.job.JobService;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MenuJobService extends JobService {
    public static ProcessStatsCapture menuJobAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;

    @Override // android.app.job.JobService
    public final boolean onStartJob(JobParameters jobParameters) {
        ProcessStatsCapture processStatsCapture = menuJobAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
        if (processStatsCapture != null) {
            processStatsCapture.sendAccessbilityMenuLogs();
            return false;
        }
        return false;
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
