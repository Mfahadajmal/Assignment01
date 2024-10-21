package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RegisterListenerMethod {
    public final ListenerHolder listenerHolder;
    public final int methodKey;
    public final Feature[] requiredFeatures;
    public final boolean shouldAutoResolveMissingFeatures;
    final /* synthetic */ RegistrationMethods$Builder this$0;

    protected RegisterListenerMethod(ListenerHolder listenerHolder, Feature[] featureArr, boolean z, int i) {
        this.listenerHolder = listenerHolder;
        this.requiredFeatures = featureArr;
        this.shouldAutoResolveMissingFeatures = z;
        this.methodKey = i;
    }

    public final ListenerHolder.ListenerKey getListenerKey() {
        return this.listenerHolder.listenerKey;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void registerListener$ar$class_merging$ar$class_merging(Api$AnyClient api$AnyClient, AppLifecycleMonitor appLifecycleMonitor) {
        this.this$0.register.accept(api$AnyClient, appLifecycleMonitor);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RegisterListenerMethod(RegistrationMethods$Builder registrationMethods$Builder, ListenerHolder listenerHolder, Feature[] featureArr, boolean z, int i) {
        this(listenerHolder, featureArr, z, i);
        this.this$0 = registrationMethods$Builder;
    }
}
