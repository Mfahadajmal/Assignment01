package j$.util.stream;

import j$.util.Spliterator;

/* loaded from: classes2.dex */
final class D implements c1 {
    final C a;
    final C0060j b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public D(T0 t0, C c, C0060j c0060j) {
        this.a = c;
        this.b = c0060j;
    }

    @Override // j$.util.stream.c1
    public final Object a(AbstractC0044b abstractC0044b, Spliterator spliterator) {
        B b = (B) this.b.get();
        abstractC0044b.t(spliterator, b);
        return Boolean.valueOf(b.b);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // j$.util.stream.c1
    public final Object b(AbstractC0044b abstractC0044b, Spliterator spliterator) {
        return (Boolean) new E(this, abstractC0044b, spliterator).invoke();
    }

    @Override // j$.util.stream.c1
    public final int d() {
        return S0.s | S0.p;
    }
}
