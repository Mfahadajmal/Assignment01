package androidx.work;

import android.content.Context;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.widget.AppCompatSpinner;
import androidx.room.SharedSQLiteStatement$stmt$2;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Worker extends ListenableWorker {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Worker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        context.getClass();
        workerParameters.getClass();
    }

    public abstract AppCompatDelegateImpl.Api33Impl doWork$ar$class_merging$ar$class_merging();

    @Override // androidx.work.ListenableWorker
    public final ListenableFuture getForegroundInfoAsync() {
        return AppCompatSpinner.Api23Impl.future(getBackgroundExecutor(), new SharedSQLiteStatement$stmt$2(this, 3));
    }

    @Override // androidx.work.ListenableWorker
    public final ListenableFuture startWork() {
        return AppCompatSpinner.Api23Impl.future(getBackgroundExecutor(), new SharedSQLiteStatement$stmt$2(this, 4));
    }
}
