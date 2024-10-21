package com.google.android.play.core.splitcompat;

import java.util.concurrent.ThreadFactory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BackgroundExecutor$1 implements ThreadFactory {
    private final /* synthetic */ int switching_field;

    public BackgroundExecutor$1(int i) {
        this.switching_field = i;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        if (this.switching_field != 0) {
            return new Thread(runnable, "ProcessStablePhenotypeFlag");
        }
        return new Thread(runnable, "SplitCompatBackgroundThread");
    }
}
