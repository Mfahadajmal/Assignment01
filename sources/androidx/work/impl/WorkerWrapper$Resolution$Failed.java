package androidx.work.impl;

import android.support.v7.app.AppCompatDelegateImpl;
import androidx.work.ListenableWorker$Result$Failure;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkerWrapper$Resolution$Failed extends WorkManagerImplExtKt {
    public final AppCompatDelegateImpl.Api33Impl result$ar$class_merging$ar$class_merging;

    public WorkerWrapper$Resolution$Failed() {
        this(null);
    }

    public /* synthetic */ WorkerWrapper$Resolution$Failed(byte[] bArr) {
        this.result$ar$class_merging$ar$class_merging = new ListenableWorker$Result$Failure();
    }
}
