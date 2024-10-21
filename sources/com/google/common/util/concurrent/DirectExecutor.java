package com.google.common.util.concurrent;

import java.util.concurrent.Executor;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class DirectExecutor implements Executor {
    private static final /* synthetic */ DirectExecutor[] $VALUES;
    public static final DirectExecutor INSTANCE;

    static {
        DirectExecutor directExecutor = new DirectExecutor();
        INSTANCE = directExecutor;
        $VALUES = new DirectExecutor[]{directExecutor};
    }

    private DirectExecutor() {
    }

    public static DirectExecutor[] values() {
        return (DirectExecutor[]) $VALUES.clone();
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "MoreExecutors.directExecutor()";
    }
}
