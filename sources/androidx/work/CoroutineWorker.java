package androidx.work;

import android.content.Context;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.logging.schema.OnDeviceObjectLoadLogEvent;
import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class CoroutineWorker extends ListenableWorker {
    private final CoroutineDispatcher coroutineContext;
    private final WorkerParameters params;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class DeprecatedDispatcher extends CoroutineDispatcher {
        public static final DeprecatedDispatcher INSTANCE = new DeprecatedDispatcher();
        private static final CoroutineDispatcher dispatcher = Dispatchers.Default;

        private DeprecatedDispatcher() {
        }

        @Override // kotlinx.coroutines.CoroutineDispatcher
        public final void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
            coroutineContext.getClass();
            runnable.getClass();
            dispatcher.dispatch(coroutineContext, runnable);
        }

        @Override // kotlinx.coroutines.CoroutineDispatcher
        public final boolean isDispatchNeeded(CoroutineContext coroutineContext) {
            coroutineContext.getClass();
            return dispatcher.isDispatchNeeded(coroutineContext);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        context.getClass();
        workerParameters.getClass();
        this.params = workerParameters;
        this.coroutineContext = DeprecatedDispatcher.INSTANCE;
    }

    public abstract Object doWork(Continuation continuation);

    @Override // androidx.work.ListenableWorker
    public final ListenableFuture getForegroundInfoAsync() {
        return AnimatedVectorDrawableCompat.Api23Impl.launchFuture$default$ar$ds(OnDeviceObjectLoadLogEvent.plus((CoroutineContext.Element) this.coroutineContext, (CoroutineContext) ScannerAutoZoomEvent.PredictedArea.Job$default$ar$class_merging$ar$ds()), new CoroutineWorker$getForegroundInfoAsync$1(this, (Continuation) null, 0));
    }

    @Override // androidx.work.ListenableWorker
    public final ListenableFuture startWork() {
        CoroutineContext coroutineContext;
        if (!Intrinsics.areEqual(this.coroutineContext, DeprecatedDispatcher.INSTANCE)) {
            coroutineContext = this.coroutineContext;
        } else {
            coroutineContext = this.params.mWorkerContext;
        }
        coroutineContext.getClass();
        return AnimatedVectorDrawableCompat.Api23Impl.launchFuture$default$ar$ds(coroutineContext.plus(ScannerAutoZoomEvent.PredictedArea.Job$default$ar$class_merging$ar$ds()), new CoroutineWorker$getForegroundInfoAsync$1(this, (Continuation) null, 2, (byte[]) null));
    }
}
