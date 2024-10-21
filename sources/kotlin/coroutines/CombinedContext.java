package kotlin.coroutines;

import com.google.mlkit.logging.schema.OnDeviceObjectLoadLogEvent;
import java.io.Serializable;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$IntRef;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CombinedContext implements CoroutineContext, Serializable {
    private final CoroutineContext.Element element;
    private final CoroutineContext left;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Serialized implements Serializable {
        private static final long serialVersionUID = 0;
        private final CoroutineContext[] elements;

        public Serialized(CoroutineContext[] coroutineContextArr) {
            this.elements = coroutineContextArr;
        }

        private final Object readResolve() {
            CoroutineContext coroutineContext = EmptyCoroutineContext.INSTANCE;
            int i = 0;
            while (true) {
                CoroutineContext[] coroutineContextArr = this.elements;
                if (i < coroutineContextArr.length) {
                    coroutineContext = coroutineContext.plus(coroutineContextArr[i]);
                    i++;
                } else {
                    return coroutineContext;
                }
            }
        }
    }

    public CombinedContext(CoroutineContext coroutineContext, CoroutineContext.Element element) {
        this.left = coroutineContext;
        this.element = element;
    }

    private final boolean contains(CoroutineContext.Element element) {
        return Intrinsics.areEqual(get(element.getKey()), element);
    }

    private final int size() {
        int i = 2;
        CombinedContext combinedContext = this;
        while (true) {
            CoroutineContext coroutineContext = combinedContext.left;
            if (coroutineContext instanceof CombinedContext) {
                combinedContext = (CombinedContext) coroutineContext;
            } else {
                combinedContext = null;
            }
            if (combinedContext == null) {
                return i;
            }
            i++;
        }
    }

    private final Object writeReplace() {
        int size = size();
        final CoroutineContext[] coroutineContextArr = new CoroutineContext[size];
        final Ref$IntRef ref$IntRef = new Ref$IntRef();
        fold(Unit.INSTANCE, new Function2() { // from class: kotlin.coroutines.CombinedContext$writeReplace$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                CoroutineContext.Element element = (CoroutineContext.Element) obj2;
                ((Unit) obj).getClass();
                element.getClass();
                Ref$IntRef ref$IntRef2 = ref$IntRef;
                int i = ref$IntRef2.element;
                ref$IntRef2.element = i + 1;
                coroutineContextArr[i] = element;
                return Unit.INSTANCE;
            }
        });
        if (ref$IntRef.element == size) {
            return new Serialized(coroutineContextArr);
        }
        throw new IllegalStateException("Check failed.");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CombinedContext) {
            CombinedContext combinedContext = (CombinedContext) obj;
            if (combinedContext.size() == size()) {
                CombinedContext combinedContext2 = this;
                while (true) {
                    if (!combinedContext.contains(combinedContext2.element)) {
                        break;
                    }
                    CoroutineContext coroutineContext = combinedContext2.left;
                    if (coroutineContext instanceof CombinedContext) {
                        combinedContext2 = (CombinedContext) coroutineContext;
                    } else if (combinedContext.contains((CoroutineContext.Element) coroutineContext)) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final Object fold(Object obj, Function2 function2) {
        return function2.invoke(this.left.fold(obj, function2), this.element);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext.Element get(CoroutineContext.Key key) {
        CombinedContext combinedContext = this;
        while (true) {
            CoroutineContext.Element element = combinedContext.element.get(key);
            if (element != null) {
                return element;
            }
            CoroutineContext coroutineContext = combinedContext.left;
            if (coroutineContext instanceof CombinedContext) {
                combinedContext = (CombinedContext) coroutineContext;
            } else {
                return coroutineContext.get(key);
            }
        }
    }

    public final int hashCode() {
        return this.left.hashCode() + this.element.hashCode();
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext minusKey(CoroutineContext.Key key) {
        if (this.element.get(key) != null) {
            return this.left;
        }
        CoroutineContext minusKey = this.left.minusKey(key);
        if (minusKey != this.left) {
            if (minusKey == EmptyCoroutineContext.INSTANCE) {
                return this.element;
            }
            return new CombinedContext(minusKey, this.element);
        }
        return this;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext plus(CoroutineContext coroutineContext) {
        return OnDeviceObjectLoadLogEvent.plus(this, coroutineContext);
    }

    public final String toString() {
        return "[" + fold("", CombinedContext$toString$1.INSTANCE) + "]";
    }
}
