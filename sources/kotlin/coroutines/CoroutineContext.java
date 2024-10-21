package kotlin.coroutines;

import kotlin.jvm.functions.Function2;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface CoroutineContext {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Element extends CoroutineContext {
        @Override // kotlin.coroutines.CoroutineContext
        Element get(Key key);

        Key getKey();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Key {
    }

    Object fold(Object obj, Function2 function2);

    Element get(Key key);

    CoroutineContext minusKey(Key key);

    CoroutineContext plus(CoroutineContext coroutineContext);
}
