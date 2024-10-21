package kotlin;

import _COROUTINE._BOUNDARY;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.functions.Function0;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SafePublicationLazyImpl implements Lazy, Serializable {
    private static final AtomicReferenceFieldUpdater valueUpdater = AtomicReferenceFieldUpdater.newUpdater(SafePublicationLazyImpl.class, Object.class, "_value");
    private volatile Object _value = UNINITIALIZED_VALUE.INSTANCE;
    private volatile Function0 initializer;

    public SafePublicationLazyImpl(Function0 function0) {
        this.initializer = function0;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    @Override // kotlin.Lazy
    public final Object getValue() {
        Object obj = this._value;
        if (obj != UNINITIALIZED_VALUE.INSTANCE) {
            return obj;
        }
        Function0 function0 = this.initializer;
        if (function0 != null) {
            Object invoke = function0.invoke();
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_13(valueUpdater, this, UNINITIALIZED_VALUE.INSTANCE, invoke)) {
                this.initializer = null;
                return invoke;
            }
        }
        return this._value;
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
