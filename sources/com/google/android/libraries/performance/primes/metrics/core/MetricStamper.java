package com.google.android.libraries.performance.primes.metrics.core;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.libraries.performance.primes.ConfigurationsModule$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ExecutionList;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.SystemHealthProto$ApplicationInfo;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricStamper {
    public final Context application;
    public final String applicationPackage;
    public final Supplier componentNameSupplier;
    public final ExecutionList.RunnableExecutorPair dataPartitionSize$ar$class_merging;
    public final int hardwareVariant$ar$edu;
    public final Provider readCorrectProcStatusProvider;
    public final String shortProcessName;
    public final Supplier totalDiskSizeKbSupplier;
    public final String versionName;

    public MetricStamper(Context context, Optional optional, String str, Provider provider) {
        Supplier supplier;
        this.application = context;
        this.readCorrectProcStatusProvider = provider;
        this.applicationPackage = context.getPackageName();
        ActivityManager activityManager = ProcessStats.activityManager;
        this.shortProcessName = ProcessStats.getShortProcessName(context.getPackageName(), ProcessStats.getCurrentProcessName());
        if (optional.isPresent()) {
            supplier = ((GlobalConfigurations) optional.get()).getComponentNameSupplier();
        } else {
            supplier = null;
        }
        this.componentNameSupplier = supplier;
        this.versionName = str;
        PackageManager packageManager = context.getPackageManager();
        int i = SystemHealthProto$ApplicationInfo.HardwareVariant.PHONE_OR_TABLET$ar$edu;
        if (packageManager.hasSystemFeature("android.hardware.type.watch")) {
            i = SystemHealthProto$ApplicationInfo.HardwareVariant.WATCH$ar$edu;
        } else if (packageManager.hasSystemFeature("android.software.leanback")) {
            i = SystemHealthProto$ApplicationInfo.HardwareVariant.LEANBACK$ar$edu;
        }
        this.hardwareVariant$ar$edu = packageManager.hasSystemFeature("android.hardware.type.automotive") ? SystemHealthProto$ApplicationInfo.HardwareVariant.AUTOMOTIVE$ar$edu : i;
        this.dataPartitionSize$ar$class_merging = new ExecutionList.RunnableExecutorPair(context, (byte[]) null);
        this.totalDiskSizeKbSupplier = ContextDataProvider.memoize(new ConfigurationsModule$$ExternalSyntheticLambda0(this, 5));
    }
}
