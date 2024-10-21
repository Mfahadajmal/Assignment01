package kotlinx.coroutines;

import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.ContinuationInterceptor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class YieldContext extends AbstractCoroutineContextElement {
    public static final ContinuationInterceptor.Key Key$ar$class_merging$c55a358e_0 = new ContinuationInterceptor.Key();
    public boolean dispatcherWasUnconfined;

    public YieldContext() {
        super(Key$ar$class_merging$c55a358e_0);
    }
}
