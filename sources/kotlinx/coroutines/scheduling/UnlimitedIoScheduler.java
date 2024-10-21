package kotlinx.coroutines.scheduling;

import com.google.mlkit.logging.schema.SubjectSegmentationOptionalModuleLogEvent;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.internal.LimitedDispatcher;

/* compiled from: PG */
/* loaded from: classes.dex */
final class UnlimitedIoScheduler extends CoroutineDispatcher {
    public static final UnlimitedIoScheduler INSTANCE = new UnlimitedIoScheduler();

    private UnlimitedIoScheduler() {
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        DefaultScheduler.INSTANCE.dispatchWithContext$kotlinx_coroutines_core$ar$class_merging(runnable, TasksKt.BlockingContext$ar$class_merging, false);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatchYield(CoroutineContext coroutineContext, Runnable runnable) {
        DefaultScheduler.INSTANCE.dispatchWithContext$kotlinx_coroutines_core$ar$class_merging(runnable, TasksKt.BlockingContext$ar$class_merging, true);
    }

    public final CoroutineDispatcher limitedParallelism(int i) {
        SubjectSegmentationOptionalModuleLogEvent.checkParallelism(i);
        if (i >= TasksKt.MAX_POOL_SIZE) {
            return this;
        }
        SubjectSegmentationOptionalModuleLogEvent.checkParallelism(i);
        return new LimitedDispatcher(this, i);
    }
}
