package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
final class Q extends T implements H {
    @Override // j$.util.stream.J
    public final Object b(int i) {
        return new double[i];
    }

    @Override // j$.util.stream.K
    public final /* synthetic */ void forEach(Consumer consumer) {
        G.m(this, consumer);
    }

    @Override // j$.util.stream.K
    public final /* synthetic */ void k(Object[] objArr, int i) {
        G.k(this, (Double[]) objArr, i);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [j$.util.C, j$.util.stream.g0] */
    @Override // j$.util.stream.K
    public final j$.util.C spliterator() {
        return new AbstractC0055g0(this);
    }

    @Override // j$.util.stream.K
    public final Spliterator spliterator() {
        return new AbstractC0055g0(this);
    }
}
