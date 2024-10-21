package com.google.common.flogger.backend.android;

import com.google.common.flogger.backend.Metadata;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.flogger.context.NoOpContextDataProvider;
import com.google.common.flogger.context.Tags;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ProxyContextDataProvider extends ContextDataProvider {
    public static final ProxyContextDataProvider INSTANCE = new ProxyContextDataProvider(NoOpContextDataProvider.NO_OP_INSTANCE);
    private final AtomicReference delegate;

    public ProxyContextDataProvider(ContextDataProvider contextDataProvider) {
        this.delegate = new AtomicReference(contextDataProvider);
    }

    @Override // com.google.common.flogger.context.ContextDataProvider
    public final Metadata getMetadata() {
        return ((ContextDataProvider) this.delegate.get()).getMetadata();
    }

    @Override // com.google.common.flogger.context.ContextDataProvider
    public final Tags getTags() {
        return ((ContextDataProvider) this.delegate.get()).getTags();
    }

    @Override // com.google.common.flogger.context.ContextDataProvider
    public final void shouldForceLogging$ar$ds(String str, Level level, boolean z) {
        ((ContextDataProvider) this.delegate.get()).shouldForceLogging$ar$ds(str, level, z);
    }
}
