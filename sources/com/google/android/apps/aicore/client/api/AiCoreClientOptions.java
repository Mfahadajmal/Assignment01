package com.google.android.apps.aicore.client.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AiCoreClientOptions {
    public final Executor callbackExecutor;
    public final Context context;
    public final ExecutorService workerExecutor;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Executor callbackExecutor;
        public Context context;
        public byte set$0;
        public ExecutorService workerExecutor;

        public Builder() {
        }

        public Builder(byte[] bArr) {
            this();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MainThreadExecutor implements Executor {
        public static final MainThreadExecutor INSTANCE = new MainThreadExecutor();
        private final Handler handler = new Handler(Looper.getMainLooper());

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            this.handler.post(runnable);
        }
    }

    public AiCoreClientOptions() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AiCoreClientOptions) {
            AiCoreClientOptions aiCoreClientOptions = (AiCoreClientOptions) obj;
            if (this.context.equals(aiCoreClientOptions.getContext()) && this.workerExecutor.equals(aiCoreClientOptions.getWorkerExecutor()) && this.callbackExecutor.equals(aiCoreClientOptions.getCallbackExecutor())) {
                return true;
            }
        }
        return false;
    }

    public final Executor getCallbackExecutor() {
        return this.callbackExecutor;
    }

    public final Context getContext() {
        return this.context;
    }

    public final ExecutorService getWorkerExecutor() {
        return this.workerExecutor;
    }

    public final int hashCode() {
        return ((((((((this.context.hashCode() ^ 1000003) * 1000003) ^ this.workerExecutor.hashCode()) * 1000003) ^ this.callbackExecutor.hashCode()) * 1000003) ^ 1237) * 1000003) ^ 1237;
    }

    public final String toString() {
        Executor executor = this.callbackExecutor;
        ExecutorService executorService = this.workerExecutor;
        return "AiCoreClientOptions{context=" + String.valueOf(this.context) + ", workerExecutor=" + String.valueOf(executorService) + ", callbackExecutor=" + String.valueOf(executor) + ", bindImportantEnabled=false, bindWaivePriorityEnabled=false}";
    }

    public AiCoreClientOptions(Context context, ExecutorService executorService, Executor executor) {
        this();
        this.context = context;
        this.workerExecutor = executorService;
        this.callbackExecutor = executor;
    }
}
