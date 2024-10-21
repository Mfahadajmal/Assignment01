package kotlinx.coroutines.flow.internal;

import kotlin.coroutines.Continuation;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractSharedFlowSlot {
    public abstract boolean allocateLocked(Object obj);

    public abstract Continuation[] freeLocked(Object obj);
}
