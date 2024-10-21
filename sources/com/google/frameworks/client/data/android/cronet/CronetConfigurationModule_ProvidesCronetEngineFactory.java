package com.google.frameworks.client.data.android.cronet;

import android.content.Context;
import android.os.StrictMode;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory_Factory;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitInfoCaptureImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricServiceImpl;
import com.google.android.play.core.splitinstall.SplitInstallModule_ProvideContextFactory;
import com.google.apps.tiktok.tracing.SpanEndSignal;
import com.google.apps.tiktok.tracing.Tracer;
import com.google.common.base.Optional;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.frameworks.client.data.android.cronet.CronetConfigurations;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import dagger.internal.SetFactory;
import java.io.File;
import java.util.Set;
import java.util.concurrent.Executor;
import javax.inject.Provider;
import org.chromium.net.ExperimentalCronetEngine;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CronetConfigurationModule_ProvidesCronetEngineFactory implements Factory {
    private final Provider configOptionalProvider;
    private final Provider contextProvider;
    private final Provider enableLocalTrustProvider;
    private final Provider googleDigestsProvider;
    private final Provider networkQualityRttListenersProvider;
    private final Provider networkQualityThroughputListenersProvider;
    private final Provider pinsProvider;
    private final Provider quicUrlsProvider;
    private final /* synthetic */ int switching_field;

    public CronetConfigurationModule_ProvidesCronetEngineFactory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, int i) {
        this.switching_field = i;
        this.contextProvider = provider;
        this.quicUrlsProvider = provider2;
        this.pinsProvider = provider3;
        this.configOptionalProvider = provider4;
        this.enableLocalTrustProvider = provider5;
        this.googleDigestsProvider = provider6;
        this.networkQualityRttListenersProvider = provider7;
        this.networkQualityThroughputListenersProvider = provider8;
    }

    @Override // javax.inject.Provider
    public final /* synthetic */ Object get() {
        ExperimentalCronetEngine build;
        int i;
        if (this.switching_field != 0) {
            Object obj = ((InstanceFactory) this.networkQualityRttListenersProvider).instance;
            MetricRecorderFactory metricRecorderFactory = ((MetricRecorderFactory_Factory) this.pinsProvider).get();
            Context context = (Context) obj;
            Executor executor = (Executor) this.networkQualityThroughputListenersProvider.get();
            Provider provider = this.enableLocalTrustProvider;
            return new ApplicationExitMetricServiceImpl(metricRecorderFactory, context, executor, ((ApplicationExitInfoCaptureImpl_Factory) this.googleDigestsProvider).get(), this.quicUrlsProvider, DoubleCheck.lazy(provider), this.configOptionalProvider, this.contextProvider);
        }
        Object obj2 = ((InstanceFactory) this.pinsProvider).instance;
        Provider provider2 = this.quicUrlsProvider;
        Context context2 = ((SplitInstallModule_ProvideContextFactory) this.contextProvider).get();
        Set set = ((SetFactory) provider2).get();
        Set set2 = (Set) obj2;
        Optional optional = (Optional) ((InstanceFactory) this.configOptionalProvider).instance;
        Optional optional2 = (Optional) ((InstanceFactory) this.enableLocalTrustProvider).instance;
        Provider provider3 = this.networkQualityThroughputListenersProvider;
        Provider provider4 = this.networkQualityRttListenersProvider;
        Provider provider5 = this.googleDigestsProvider;
        SpanEndSignal beginSpan = Tracer.beginSpan("CronetConfigurationModule#provideCronetEngine");
        try {
            StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
            try {
                try {
                    ExperimentalCronetEngine.Builder createBuilderWithDefaultCacheSettings = ContextDataProvider.createBuilderWithDefaultCacheSettings(context2, set, set2, optional, optional2, provider5);
                    StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
                    if (optional.isPresent()) {
                        CronetConfigurations.CronetConfig cronetConfig = (CronetConfigurations.CronetConfig) optional.get();
                        if (cronetConfig.enableCertificateCache()) {
                            if (cronetConfig.enableHttpCache()) {
                                i = 3;
                            } else {
                                i = 2;
                            }
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                            File file = new File(context2.getCacheDir(), cronetConfig.storagePath());
                            file.mkdirs();
                            if (file.isDirectory()) {
                                createBuilderWithDefaultCacheSettings.setStoragePath(file.getAbsolutePath());
                                createBuilderWithDefaultCacheSettings.enableHttpCache(i, cronetConfig.diskCacheSizeBytes());
                            }
                        }
                        ContextDataProvider.applyNonDiskCacheSettings(optional, createBuilderWithDefaultCacheSettings);
                    }
                    ContextDataProvider.applyNetworkQualityBuilderSettings(optional, createBuilderWithDefaultCacheSettings);
                    build = createBuilderWithDefaultCacheSettings.build();
                    ContextDataProvider.applyNetworkQualitySettings(optional, build, provider4, provider3);
                } finally {
                    StrictMode.setThreadPolicy(threadPolicy);
                }
            } catch (IllegalStateException e) {
                if (optional.isPresent() && ((CronetConfigurations.CronetConfig) optional.get()).enableInMemoryFallbackCache()) {
                    StrictMode.setThreadPolicy(threadPolicy);
                    ExperimentalCronetEngine.Builder createBuilderWithDefaultCacheSettings2 = ContextDataProvider.createBuilderWithDefaultCacheSettings(context2, set, set2, optional, optional2, provider5);
                    StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
                    ContextDataProvider.applyNonDiskCacheSettings(optional, createBuilderWithDefaultCacheSettings2);
                    ContextDataProvider.applyNetworkQualityBuilderSettings(optional, createBuilderWithDefaultCacheSettings2);
                    build = createBuilderWithDefaultCacheSettings2.build();
                    ContextDataProvider.applyNetworkQualitySettings(optional, build, provider4, provider3);
                } else {
                    throw e;
                }
            }
            beginSpan.close();
            build.getClass();
            return build;
        } finally {
        }
    }

    public CronetConfigurationModule_ProvidesCronetEngineFactory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, int i, byte[] bArr) {
        this.switching_field = i;
        this.pinsProvider = provider;
        this.networkQualityRttListenersProvider = provider2;
        this.networkQualityThroughputListenersProvider = provider3;
        this.googleDigestsProvider = provider4;
        this.quicUrlsProvider = provider5;
        this.enableLocalTrustProvider = provider6;
        this.configOptionalProvider = provider7;
        this.contextProvider = provider8;
    }
}
