package com.google.android.libraries.mdi.download.workmanager.workers;

import android.content.Context;
import android.util.Log;
import androidx.work.ListenableWorker;
import androidx.work.ListenableWorker$Result$Failure;
import androidx.work.WorkerParameters;
import com.google.android.accessibility.utils.AccessibilityEventUtils$$ExternalSyntheticLambda0;
import com.google.android.libraries.mdi.download.MobileDataDownload;
import com.google.android.libraries.performance.primes.metrics.network.NetworkMetricServiceImpl$$ExternalSyntheticLambda3;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PeriodicWorker extends ListenableWorker {
    private final Executor controlExecutor;
    public final MobileDataDownload mobileDataDownload;

    public PeriodicWorker(Context context, WorkerParameters workerParameters, MobileDataDownload mobileDataDownload, ListeningExecutorService listeningExecutorService) {
        super(context, workerParameters);
        this.mobileDataDownload = mobileDataDownload;
        this.controlExecutor = listeningExecutorService;
    }

    @Override // androidx.work.ListenableWorker
    public final ListenableFuture startWork() {
        String string = getInputData().getString("MDD_TASK_TAG_KEY");
        if (string == null) {
            Log.e("MddPeriodicWorker", "can't find MDD task tag");
            return ContextDataProvider.immediateFuture(new ListenableWorker$Result$Failure());
        }
        NetworkMetricServiceImpl$$ExternalSyntheticLambda3 networkMetricServiceImpl$$ExternalSyntheticLambda3 = new NetworkMetricServiceImpl$$ExternalSyntheticLambda3(this, string, 1);
        return ContextDataProvider.transform(ContextDataProvider.submitAsync(TracePropagation.propagateAsyncCallable(networkMetricServiceImpl$$ExternalSyntheticLambda3), this.controlExecutor), new AccessibilityEventUtils$$ExternalSyntheticLambda0(12), this.controlExecutor);
    }
}
