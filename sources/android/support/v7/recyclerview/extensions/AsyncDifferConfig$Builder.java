package android.support.v7.recyclerview.extensions;

import android.support.v7.app.AppCompatDelegateImpl;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AsyncDifferConfig$Builder {
    public static Executor sDiffExecutor;
    public static final Object sExecutorLock = new Object();
    public Executor mBackgroundThreadExecutor;
    public final AppCompatDelegateImpl.Api24Impl mDiffCallback$ar$class_merging;

    public AsyncDifferConfig$Builder(AppCompatDelegateImpl.Api24Impl api24Impl) {
        this.mDiffCallback$ar$class_merging = api24Impl;
    }
}
