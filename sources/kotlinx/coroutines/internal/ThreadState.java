package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.ThreadContextElement;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ThreadState {
    public final CoroutineContext context;
    public final ThreadContextElement[] elements;
    public int i;
    public final Object[] values;

    public ThreadState(CoroutineContext coroutineContext, int i) {
        this.context = coroutineContext;
        this.values = new Object[i];
        this.elements = new ThreadContextElement[i];
    }
}
