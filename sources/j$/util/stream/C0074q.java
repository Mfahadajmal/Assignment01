package j$.util.stream;

import j$.util.Optional;
import j$.util.Spliterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: j$.util.stream.q, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0074q implements c1 {
    final int a;
    final Optional b;
    final C0072p c;
    final C0058i d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0074q(boolean z, T0 t0, Optional optional, C0072p c0072p, C0058i c0058i) {
        this.a = (z ? 0 : S0.p) | S0.s;
        this.b = optional;
        this.c = c0072p;
        this.d = c0058i;
    }

    @Override // j$.util.stream.c1
    public final Object a(AbstractC0044b abstractC0044b, Spliterator spliterator) {
        d1 d1Var = (d1) this.d.get();
        abstractC0044b.t(spliterator, d1Var);
        Object obj = d1Var.get();
        if (obj == null) {
            return this.b;
        }
        return obj;
    }

    @Override // j$.util.stream.c1
    public final Object b(AbstractC0044b abstractC0044b, Spliterator spliterator) {
        return new C0079t(this, S0.ORDERED.p(abstractC0044b.h()), abstractC0044b, spliterator).invoke();
    }

    @Override // j$.util.stream.c1
    public final int d() {
        return this.a;
    }
}
