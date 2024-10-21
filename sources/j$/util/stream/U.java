package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* loaded from: classes2.dex */
final class U extends L {
    @Override // j$.util.stream.K
    public final void forEach(Consumer consumer) {
        this.a.forEach(consumer);
        this.b.forEach(consumer);
    }

    @Override // j$.util.stream.K
    public final Object[] i(IntFunction intFunction) {
        long f = f();
        if (f >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object[] objArr = (Object[]) intFunction.apply((int) f);
        k(objArr, 0);
        return objArr;
    }

    @Override // j$.util.stream.K
    public final void k(Object[] objArr, int i) {
        objArr.getClass();
        K k = this.a;
        k.k(objArr, i);
        this.b.k(objArr, i + ((int) k.f()));
    }

    @Override // j$.util.stream.K
    public final Spliterator spliterator() {
        return new AbstractC0055g0(this);
    }

    public final String toString() {
        return f() < 32 ? String.format("ConcNode[%s.%s]", this.a, this.b) : String.format("ConcNode[size=%d]", Long.valueOf(f()));
    }
}
