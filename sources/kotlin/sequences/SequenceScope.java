package kotlin.sequences;

import java.util.Iterator;
import kotlin.coroutines.Continuation;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class SequenceScope {
    public abstract Object yield(Object obj, Continuation continuation);

    public abstract Object yieldAll(Iterator it, Continuation continuation);
}
