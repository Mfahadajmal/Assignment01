package com.google.android.accessibility.utils;

import android.content.Context;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda3;
import com.google.android.gms.usagereporting.InternalUsageReportingClient;
import com.google.android.gms.usagereporting.UsageReporting;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AnalyticsCommon {
    private final ExecutorService backgroundExecutor = Executors.newSingleThreadExecutor();
    private final InternalUsageReportingClient usageReportingClient$ar$class_merging;

    public AnalyticsCommon(Context context) {
        this.usageReportingClient$ar$class_merging = new InternalUsageReportingClient(context.getApplicationContext(), new UsageReporting.UsageReportingOptions());
    }

    public final void doInBackground(Object obj) {
        this.backgroundExecutor.execute(new ListMenuManager$$ExternalSyntheticLambda3(this, obj, 5));
    }

    public final OnDeviceTextDetectionLoadLogEvent getOptInOptions$ar$class_merging$ar$class_merging$ar$class_merging() {
        try {
            return (OnDeviceTextDetectionLoadLogEvent) SpannableUtils$NonCopyableTextSpan.await(this.usageReportingClient$ar$class_merging.getOptInOptions(), 5L, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException unused) {
            return null;
        }
    }

    public abstract void sendLog(Object obj);
}
