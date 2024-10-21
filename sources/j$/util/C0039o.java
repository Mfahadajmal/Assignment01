package j$.util;

import java.util.Comparator;
import java.util.function.Consumer;

/* renamed from: j$.util.o, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0039o implements Spliterator {
    final Spliterator a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0039o(Spliterator spliterator) {
        this.a = spliterator;
    }

    @Override // j$.util.Spliterator
    public final int a() {
        return this.a.a();
    }

    @Override // j$.util.Spliterator
    public final Spliterator b() {
        Spliterator b = this.a.b();
        if (b == null) {
            return null;
        }
        return new C0039o(b);
    }

    @Override // j$.util.Spliterator
    public final long d() {
        return this.a.d();
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        consumer.getClass();
        this.a.forEachRemaining(new C0037m(consumer));
    }

    @Override // j$.util.Spliterator
    public final boolean m(int i) {
        return this.a.m(i);
    }

    @Override // j$.util.Spliterator
    public final long n() {
        return this.a.n();
    }

    @Override // j$.util.Spliterator
    public final Comparator o() {
        return this.a.o();
    }

    @Override // j$.util.Spliterator
    public final boolean r(Consumer consumer) {
        consumer.getClass();
        return this.a.r(new C0037m(consumer));
    }
}
