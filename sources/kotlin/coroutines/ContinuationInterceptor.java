package kotlin.coroutines;

import kotlin.coroutines.CoroutineContext;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ContinuationInterceptor extends CoroutineContext.Element {
    public static final Key Key = Key.$$INSTANCE;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Key implements CoroutineContext.Key {
        public static final /* synthetic */ Key $$INSTANCE$ar$class_merging$e5be0816_0 = new Key();
        public static final /* synthetic */ Key $$INSTANCE$ar$class_merging = new Key();
        static final /* synthetic */ Key $$INSTANCE = new Key();
    }

    Continuation interceptContinuation(Continuation continuation);

    void releaseInterceptedContinuation(Continuation continuation);
}
