package com.google.firebase.components;

import com.google.common.flogger.context.ContextDataProvider;
import com.google.firebase.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OptionalProvider implements Provider {
    public volatile Provider delegate;
    public ContextDataProvider handler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public static final ContextDataProvider NOOP_HANDLER$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new ContextDataProvider((char[]) null);
    public static final Provider EMPTY_PROVIDER = new OptionalProvider$$ExternalSyntheticLambda1(0);

    public OptionalProvider(ContextDataProvider contextDataProvider, Provider provider) {
        this.handler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = contextDataProvider;
        this.delegate = provider;
    }

    @Override // com.google.firebase.inject.Provider
    public final Object get() {
        return this.delegate.get();
    }
}
