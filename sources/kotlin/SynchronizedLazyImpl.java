package kotlin;

import java.io.Serializable;
import kotlin.jvm.functions.Function0;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SynchronizedLazyImpl implements Lazy, Serializable {
    private Function0 initializer;
    private volatile Object _value = UNINITIALIZED_VALUE.INSTANCE;
    private final Object lock = this;

    public /* synthetic */ SynchronizedLazyImpl(Function0 function0) {
        this.initializer = function0;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    @Override // kotlin.Lazy
    public final Object getValue() {
        Object obj;
        Object obj2 = this._value;
        if (obj2 != UNINITIALIZED_VALUE.INSTANCE) {
            return obj2;
        }
        synchronized (this.lock) {
            obj = this._value;
            if (obj == UNINITIALIZED_VALUE.INSTANCE) {
                Function0 function0 = this.initializer;
                function0.getClass();
                obj = function0.invoke();
                this._value = obj;
                this.initializer = null;
            }
        }
        return obj;
    }

    @Override // kotlin.Lazy
    public final boolean isInitialized() {
        if (this._value != UNINITIALIZED_VALUE.INSTANCE) {
            return true;
        }
        return false;
    }

    public final String toString() {
        if (isInitialized()) {
            return String.valueOf(getValue());
        }
        return "Lazy value not initialized yet.";
    }
}
