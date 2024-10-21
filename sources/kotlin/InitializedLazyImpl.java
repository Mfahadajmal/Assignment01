package kotlin;

import java.io.Serializable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InitializedLazyImpl implements Lazy, Serializable {
    private final Object value;

    public InitializedLazyImpl(Object obj) {
        this.value = obj;
    }

    @Override // kotlin.Lazy
    public final Object getValue() {
        return this.value;
    }

    @Override // kotlin.Lazy
    public final boolean isInitialized() {
        throw null;
    }

    public final String toString() {
        return String.valueOf(this.value);
    }
}
