package kotlin.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AbstractCoroutineContextKey implements CoroutineContext.Key {
    private final Function1 safeCast;
    private final CoroutineContext.Key topmostKey;

    public AbstractCoroutineContextKey(CoroutineContext.Key key, Function1 function1) {
        this.safeCast = function1;
        this.topmostKey = key instanceof AbstractCoroutineContextKey ? ((AbstractCoroutineContextKey) key).topmostKey : key;
    }

    public final boolean isSubKey$kotlin_stdlib(CoroutineContext.Key key) {
        if (key != this && this.topmostKey != key) {
            return false;
        }
        return true;
    }

    public final CoroutineContext.Element tryCast$kotlin_stdlib(CoroutineContext.Element element) {
        return (CoroutineContext.Element) this.safeCast.invoke(element);
    }
}
