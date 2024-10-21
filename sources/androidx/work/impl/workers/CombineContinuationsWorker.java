package androidx.work.impl.workers;

import android.content.Context;
import android.support.v7.app.AppCompatDelegateImpl;
import androidx.work.ListenableWorker$Result$Success;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CombineContinuationsWorker extends Worker {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CombineContinuationsWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        context.getClass();
        workerParameters.getClass();
    }

    @Override // androidx.work.Worker
    public final AppCompatDelegateImpl.Api33Impl doWork$ar$class_merging$ar$class_merging() {
        return new ListenableWorker$Result$Success(getInputData());
    }
}
