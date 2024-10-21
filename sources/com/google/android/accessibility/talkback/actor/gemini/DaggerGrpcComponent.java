package com.google.android.accessibility.talkback.actor.gemini;

import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.clock.ClockModule_ClockFactory$InstanceHolder;
import com.google.android.libraries.performance.primes.ConfigurationsModule_ProvideApplicationExitConfigurationsFactory;
import com.google.android.libraries.performance.primes.ConfigurationsModule_ProvideSharedPreferencesFactory;
import com.google.android.libraries.performance.primes.DeferrableExecutor_Factory;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleTracker_Callbacks_Factory;
import com.google.android.play.core.splitinstall.SplitInstallModule_ProvideContextFactory;
import com.google.common.base.Absent;
import com.google.frameworks.client.data.android.auth.SpatulaHeaderDaggerModule_SpatulaProviderFactory;
import com.google.frameworks.client.data.android.auth.SpatulaStrategy_Factory;
import com.google.frameworks.client.data.android.cronet.CronetConfigurationModule_ProvideCommonSocialQuicHintsFactory$InstanceHolder;
import com.google.frameworks.client.data.android.cronet.CronetConfigurationModule_ProvidesCronetEngineFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;
import dagger.internal.SetFactory;
import dagger.internal.SingleCheck;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DaggerGrpcComponent {
    public static final Provider ABSENT_GUAVA_OPTIONAL_PROVIDER = InstanceFactory.create(Absent.INSTANCE);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GrpcComponentImpl {
        private Provider channelConfigProvider;
        private Provider channelProvider;
        private Provider clockProvider = SingleCheck.provider(ClockModule_ClockFactory$InstanceHolder.INSTANCE$ar$class_merging$b417509_0);
        private Provider enablePublicKeyPinningBypassForLocalTrustAnchorsOptionalOfBooleanProvider;
        private Provider internalCronetOptionalOfSetOfByteArrayProvider;
        private Provider optionalOfCronetConfigProvider;
        public Provider provideArateaServiceFutureStubProvider;
        private Provider provideContextProvider;
        private Provider provideProxyClientProvider;
        private Provider provideTransportProvider;
        private Provider providesCronetEngineProvider;
        private Provider providesCronetEngineProvider2;
        private Provider setOfQuicHintProvider;
        private Provider spatulaProvider;
        private Provider spatulaStrategyProvider;

        public GrpcComponentImpl(ApplicationModule applicationModule) {
            this.provideContextProvider = new SplitInstallModule_ProvideContextFactory(applicationModule, 1);
            Factory factory = SetFactory.EMPTY_FACTORY;
            SetFactory.Builder builder = new SetFactory.Builder(0, 1);
            builder.addCollectionProvider$ar$ds(CronetConfigurationModule_ProvideCommonSocialQuicHintsFactory$InstanceHolder.INSTANCE$ar$class_merging$72bd58ae_0);
            this.setOfQuicHintProvider = builder.build();
            this.optionalOfCronetConfigProvider = DaggerGrpcComponent.ABSENT_GUAVA_OPTIONAL_PROVIDER;
            Provider provider = DaggerGrpcComponent.ABSENT_GUAVA_OPTIONAL_PROVIDER;
            this.enablePublicKeyPinningBypassForLocalTrustAnchorsOptionalOfBooleanProvider = provider;
            this.internalCronetOptionalOfSetOfByteArrayProvider = provider;
            Provider provider2 = this.provideContextProvider;
            Provider provider3 = this.setOfQuicHintProvider;
            Factory factory2 = SetFactory.EMPTY_FACTORY;
            Provider provider4 = DoubleCheck.provider(new CronetConfigurationModule_ProvidesCronetEngineFactory(provider2, provider3, factory2, this.optionalOfCronetConfigProvider, this.enablePublicKeyPinningBypassForLocalTrustAnchorsOptionalOfBooleanProvider, this.internalCronetOptionalOfSetOfByteArrayProvider, factory2, factory2, 0));
            this.providesCronetEngineProvider = provider4;
            Factory factory3 = SetFactory.EMPTY_FACTORY;
            Provider provider5 = DoubleCheck.provider(new ConfigurationsModule_ProvideSharedPreferencesFactory(provider4, factory3, 15));
            this.providesCronetEngineProvider2 = provider5;
            Provider provider6 = DoubleCheck.provider(new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(provider5, 1));
            this.provideTransportProvider = provider6;
            Provider provider7 = this.provideContextProvider;
            Provider provider8 = DoubleCheck.provider(new DeferrableExecutor_Factory((javax.inject.Provider) provider7, (javax.inject.Provider) this.clockProvider, (javax.inject.Provider) provider6, 1, (byte[]) null));
            this.channelConfigProvider = provider8;
            this.channelProvider = DoubleCheck.provider(new ConfigurationsModule_ProvideSharedPreferencesFactory(provider8, factory3, 14));
            Provider provider9 = DoubleCheck.provider(new AppLifecycleTracker_Callbacks_Factory(provider7, 20));
            this.provideProxyClientProvider = provider9;
            Provider provider10 = DoubleCheck.provider(new SpatulaHeaderDaggerModule_SpatulaProviderFactory(provider9));
            this.spatulaProvider = provider10;
            SpatulaStrategy_Factory spatulaStrategy_Factory = new SpatulaStrategy_Factory(provider10);
            this.spatulaStrategyProvider = spatulaStrategy_Factory;
            this.provideArateaServiceFutureStubProvider = DoubleCheck.provider(new ConfigurationsModule_ProvideSharedPreferencesFactory(this.channelProvider, spatulaStrategy_Factory, 1));
        }
    }
}
