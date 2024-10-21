package j$.util;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* loaded from: classes2.dex */
final class J extends z implements B {
    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        z.e(this, consumer);
    }

    @Override // j$.util.B
    public final boolean i(IntConsumer intConsumer) {
        intConsumer.getClass();
        return false;
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean m(int i) {
        return z.j(this, i);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long n() {
        return z.h(this);
    }

    @Override // j$.util.Spliterator
    public final Comparator o() {
        throw new IllegalStateException();
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean r(Consumer consumer) {
        return z.p(this, consumer);
    }

    @Override // j$.util.B
    public final void t(IntConsumer intConsumer) {
        intConsumer.getClass();
    }
}
