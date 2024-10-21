package j$.util;

import java.util.Comparator;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
final class L extends z implements Spliterator {
    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        consumer.getClass();
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
    public final boolean r(Consumer consumer) {
        consumer.getClass();
        return false;
    }
}
