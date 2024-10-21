package com.google.android.play.core.splitinstall;

import java.util.concurrent.atomic.AtomicReference;
import kotlinx.coroutines.scheduling.WorkQueue;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class SplitCompatInterceptorProvider implements DownloadedStateInterceptor$Provider {
    private static final /* synthetic */ SplitCompatInterceptorProvider[] $VALUES;
    public static final SplitCompatInterceptorProvider INSTANCE;
    public static final AtomicReference interceptor;

    static {
        SplitCompatInterceptorProvider splitCompatInterceptorProvider = new SplitCompatInterceptorProvider();
        INSTANCE = splitCompatInterceptorProvider;
        $VALUES = new SplitCompatInterceptorProvider[]{splitCompatInterceptorProvider};
        interceptor = new AtomicReference(null);
    }

    private SplitCompatInterceptorProvider() {
    }

    public static SplitCompatInterceptorProvider[] values() {
        return (SplitCompatInterceptorProvider[]) $VALUES.clone();
    }

    @Override // com.google.android.play.core.splitinstall.DownloadedStateInterceptor$Provider
    public final WorkQueue getInterceptor$ar$class_merging$ar$class_merging() {
        return (WorkQueue) interceptor.get();
    }
}
