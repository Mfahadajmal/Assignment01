package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Delay {
    DisposableHandle invokeOnTimeout(long j, Runnable runnable, CoroutineContext coroutineContext);

    void scheduleResumeAfterDelay$ar$class_merging$3a912d96_0(long j, CancellableContinuationImpl cancellableContinuationImpl);
}
