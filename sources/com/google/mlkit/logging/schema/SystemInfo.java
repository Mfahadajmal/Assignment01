package com.google.mlkit.logging.schema;

import com.google.android.libraries.performance.primes.ConfigurationsModule_ProvideSharedPreferencesFactory;
import com.google.android.libraries.performance.primes.DeferrableExecutor_Factory;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleTracker_Callbacks_Factory;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture_Factory;
import com.google.android.play.core.splitinstall.SplitInstallModule;
import com.google.android.play.core.splitinstall.SplitInstallModule_ProvideContextFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemInfo {
    public Object SystemInfo$ar$appId;
    public Object SystemInfo$ar$appVersion;
    public Object SystemInfo$ar$buildLevel;
    public Object SystemInfo$ar$isClearcutClient;
    public Object SystemInfo$ar$isJsonLogging;
    public Object SystemInfo$ar$isStandaloneMlkit;
    public Object SystemInfo$ar$languages;
    public Object SystemInfo$ar$mlSdkInstanceId;
    public Object SystemInfo$ar$mlSdkVersion;
    public Object SystemInfo$ar$optionalModuleVersion;
    public Object SystemInfo$ar$tfliteSchemaVersion;

    public SystemInfo() {
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v1, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v0, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v0, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v2, types: [javax.inject.Provider, java.lang.Object] */
    public SystemInfo(SplitInstallModule splitInstallModule) {
        SplitInstallModule_ProvideContextFactory splitInstallModule_ProvideContextFactory = new SplitInstallModule_ProvideContextFactory(splitInstallModule, 0);
        this.SystemInfo$ar$isJsonLogging = splitInstallModule_ProvideContextFactory;
        this.SystemInfo$ar$mlSdkVersion = DoubleCheck.provider(new AppLifecycleTracker_Callbacks_Factory(splitInstallModule_ProvideContextFactory, 18));
        this.SystemInfo$ar$optionalModuleVersion = DoubleCheck.provider(new AppLifecycleTracker_Callbacks_Factory(splitInstallModule, 17));
        ?? r2 = this.SystemInfo$ar$isJsonLogging;
        this.SystemInfo$ar$appVersion = DoubleCheck.provider(new AppLifecycleTracker_Callbacks_Factory(r2, 14));
        Provider provider = DoubleCheck.provider(new AppLifecycleTracker_Callbacks_Factory(r2, 19));
        this.SystemInfo$ar$tfliteSchemaVersion = provider;
        ?? r4 = this.SystemInfo$ar$mlSdkVersion;
        ?? r5 = this.SystemInfo$ar$optionalModuleVersion;
        ?? r0 = this.SystemInfo$ar$appVersion;
        this.SystemInfo$ar$mlSdkInstanceId = DoubleCheck.provider(new ProcessStatsCapture_Factory((javax.inject.Provider) r4, (javax.inject.Provider) r5, (javax.inject.Provider) r0, provider, 4, (int[]) null));
        Provider provider2 = DoubleCheck.provider(new AppLifecycleTracker_Callbacks_Factory(r2, 16));
        this.SystemInfo$ar$buildLevel = provider2;
        AppLifecycleTracker_Callbacks_Factory appLifecycleTracker_Callbacks_Factory = new AppLifecycleTracker_Callbacks_Factory(provider2, 15);
        this.SystemInfo$ar$isStandaloneMlkit = appLifecycleTracker_Callbacks_Factory;
        Provider provider3 = DoubleCheck.provider(new ProcessStatsCapture_Factory((javax.inject.Provider) r2, provider2, (javax.inject.Provider) r0, appLifecycleTracker_Callbacks_Factory, 5, (boolean[]) null));
        this.SystemInfo$ar$languages = provider3;
        Provider provider4 = DoubleCheck.provider(new DeferrableExecutor_Factory((javax.inject.Provider) this.SystemInfo$ar$mlSdkInstanceId, provider3, provider2, 6, (float[]) null));
        this.SystemInfo$ar$isClearcutClient = provider4;
        this.SystemInfo$ar$appId = DoubleCheck.provider(new ConfigurationsModule_ProvideSharedPreferencesFactory(splitInstallModule, provider4, 13));
    }

    public SystemInfo(SystemInfo systemInfo) {
        this.SystemInfo$ar$appId = systemInfo.SystemInfo$ar$appId;
        this.SystemInfo$ar$appVersion = systemInfo.SystemInfo$ar$appVersion;
        this.SystemInfo$ar$mlSdkVersion = systemInfo.SystemInfo$ar$mlSdkVersion;
        this.SystemInfo$ar$tfliteSchemaVersion = systemInfo.SystemInfo$ar$tfliteSchemaVersion;
        this.SystemInfo$ar$languages = systemInfo.SystemInfo$ar$languages;
        this.SystemInfo$ar$mlSdkInstanceId = systemInfo.SystemInfo$ar$mlSdkInstanceId;
        this.SystemInfo$ar$isClearcutClient = systemInfo.SystemInfo$ar$isClearcutClient;
        this.SystemInfo$ar$isStandaloneMlkit = systemInfo.SystemInfo$ar$isStandaloneMlkit;
        this.SystemInfo$ar$isJsonLogging = systemInfo.SystemInfo$ar$isJsonLogging;
        this.SystemInfo$ar$buildLevel = systemInfo.SystemInfo$ar$buildLevel;
        this.SystemInfo$ar$optionalModuleVersion = systemInfo.SystemInfo$ar$optionalModuleVersion;
    }
}
