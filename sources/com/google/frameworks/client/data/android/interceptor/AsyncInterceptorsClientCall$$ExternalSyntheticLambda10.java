package com.google.frameworks.client.data.android.interceptor;

import com.google.common.util.concurrent.AbstractFuture;
import com.google.firebase.components.EventBus$$ExternalSyntheticLambda0;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import org.chromium.net.impl.JavaUploadDataSinkBase;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AsyncInterceptorsClientCall$$ExternalSyntheticLambda10 implements Executor {
    public final /* synthetic */ Object AsyncInterceptorsClientCall$$ExternalSyntheticLambda10$ar$f$0;
    public final /* synthetic */ Object AsyncInterceptorsClientCall$$ExternalSyntheticLambda10$ar$f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ AsyncInterceptorsClientCall$$ExternalSyntheticLambda10(AsyncInterceptorsClientCall asyncInterceptorsClientCall, Executor executor, int i) {
        this.switching_field = i;
        this.AsyncInterceptorsClientCall$$ExternalSyntheticLambda10$ar$f$0 = asyncInterceptorsClientCall;
        this.AsyncInterceptorsClientCall$$ExternalSyntheticLambda10$ar$f$1 = executor;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.concurrent.Executor, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.concurrent.Executor, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.concurrent.Executor, java.lang.Object] */
    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                try {
                    this.AsyncInterceptorsClientCall$$ExternalSyntheticLambda10$ar$f$0.execute(runnable);
                    return;
                } catch (RejectedExecutionException e) {
                    ((JavaUploadDataSinkBase) this.AsyncInterceptorsClientCall$$ExternalSyntheticLambda10$ar$f$1).processUploadError(e);
                    return;
                }
            }
            try {
                this.AsyncInterceptorsClientCall$$ExternalSyntheticLambda10$ar$f$1.execute(runnable);
                return;
            } catch (RejectedExecutionException e2) {
                ((AbstractFuture) this.AsyncInterceptorsClientCall$$ExternalSyntheticLambda10$ar$f$0).setException(e2);
                return;
            }
        }
        this.AsyncInterceptorsClientCall$$ExternalSyntheticLambda10$ar$f$1.execute(new EventBus$$ExternalSyntheticLambda0(this.AsyncInterceptorsClientCall$$ExternalSyntheticLambda10$ar$f$0, runnable, 7, null));
    }

    public AsyncInterceptorsClientCall$$ExternalSyntheticLambda10(Object obj, Object obj2, int i) {
        this.switching_field = i;
        this.AsyncInterceptorsClientCall$$ExternalSyntheticLambda10$ar$f$1 = obj;
        this.AsyncInterceptorsClientCall$$ExternalSyntheticLambda10$ar$f$0 = obj2;
    }
}
