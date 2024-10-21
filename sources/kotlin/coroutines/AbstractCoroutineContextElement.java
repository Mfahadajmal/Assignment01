package kotlin.coroutines;

import com.google.mlkit.logging.schema.OnDeviceObjectLoadLogEvent;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AbstractCoroutineContextElement implements CoroutineContext.Element {
    private final CoroutineContext.Key key;

    public AbstractCoroutineContextElement(CoroutineContext.Key key) {
        this.key = key;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final Object fold(Object obj, Function2 function2) {
        return OnDeviceObjectLoadLogEvent.fold(this, obj, function2);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public CoroutineContext.Element get(CoroutineContext.Key key) {
        return OnDeviceObjectLoadLogEvent.get(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public final CoroutineContext.Key getKey() {
        return this.key;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key key) {
        return OnDeviceObjectLoadLogEvent.minusKey(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext plus(CoroutineContext coroutineContext) {
        return OnDeviceObjectLoadLogEvent.plus((CoroutineContext.Element) this, coroutineContext);
    }
}
