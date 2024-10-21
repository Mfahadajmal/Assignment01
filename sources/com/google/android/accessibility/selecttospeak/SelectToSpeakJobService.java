package com.google.android.accessibility.selecttospeak;

import android.app.job.JobParameters;
import android.app.job.JobService;
import com.google.android.accessibility.selecttospeak.logging.SelectToSpeakLogSender;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SelectToSpeakJobService extends JobService {
    @Override // android.app.job.JobService
    public final boolean onStartJob(JobParameters jobParameters) {
        LogUtils.e("SelectToSpeakJobService", "In onStartJob for job creation", new Object[0]);
        SelectToSpeakLogSender.sendSelectToSpeakLogs(this);
        return false;
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        LogUtils.v("SelectToSpeakJobService", "In onStopJob for job creation", new Object[0]);
        return false;
    }
}
