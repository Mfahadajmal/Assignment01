package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Job extends CoroutineContext.Element {
    public static final ContinuationInterceptor.Key Key$ar$class_merging$e5be0816_0 = ContinuationInterceptor.Key.$$INSTANCE$ar$class_merging$e5be0816_0;

    ChildHandle attachChild$ar$class_merging(JobSupport jobSupport);

    void cancel(CancellationException cancellationException);

    CancellationException getCancellationException();

    Job getParent();

    DisposableHandle invokeOnCompletion(boolean z, boolean z2, Function1 function1);

    void invokeOnCompletion$ar$ds(Function1 function1);

    boolean isActive();

    boolean isCancelled();

    Object join(Continuation continuation);

    void start$ar$ds$f3a3431a_0();
}
