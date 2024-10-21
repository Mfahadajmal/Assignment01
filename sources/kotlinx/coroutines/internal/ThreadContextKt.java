package kotlinx.coroutines.internal;

import kotlin.coroutines.CombinedContext$toString$1;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.ThreadContextElement;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ThreadContextKt {
    public static final Symbol NO_THREAD_ELEMENTS = new Symbol("NO_THREAD_ELEMENTS");
    private static final Function2 countAll = CombinedContext$toString$1.INSTANCE$ar$class_merging$9a0af526_0;
    private static final Function2 findOne = CombinedContext$toString$1.INSTANCE$ar$class_merging$f8339cea_0;
    private static final Function2 updateState = CombinedContext$toString$1.INSTANCE$ar$class_merging$77c6765b_0;

    public static final void restoreThreadContext(CoroutineContext coroutineContext, Object obj) {
        if (obj != NO_THREAD_ELEMENTS) {
            if (obj instanceof ThreadState) {
                ThreadState threadState = (ThreadState) obj;
                int length = threadState.elements.length - 1;
                if (length < 0) {
                    return;
                }
                while (true) {
                    int i = length - 1;
                    ThreadContextElement threadContextElement = threadState.elements[length];
                    threadContextElement.getClass();
                    threadContextElement.restoreThreadContext$ar$ds(threadState.values[length]);
                    if (i >= 0) {
                        length = i;
                    } else {
                        return;
                    }
                }
            } else {
                Object fold = coroutineContext.fold(null, findOne);
                fold.getClass();
                ((ThreadContextElement) fold).restoreThreadContext$ar$ds(obj);
            }
        }
    }

    public static final Object threadContextElements(CoroutineContext coroutineContext) {
        Object fold = coroutineContext.fold(0, countAll);
        fold.getClass();
        return fold;
    }

    public static final Object updateThreadContext(CoroutineContext coroutineContext, Object obj) {
        if (obj == null) {
            obj = threadContextElements(coroutineContext);
        }
        if (obj == 0) {
            return NO_THREAD_ELEMENTS;
        }
        if (obj instanceof Integer) {
            return coroutineContext.fold(new ThreadState(coroutineContext, ((Number) obj).intValue()), updateState);
        }
        return ((ThreadContextElement) obj).updateThreadContext(coroutineContext);
    }
}
