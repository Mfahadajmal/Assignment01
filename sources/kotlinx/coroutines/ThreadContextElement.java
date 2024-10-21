package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ThreadContextElement extends CoroutineContext.Element {
    void restoreThreadContext$ar$ds(Object obj);

    Object updateThreadContext(CoroutineContext coroutineContext);
}
