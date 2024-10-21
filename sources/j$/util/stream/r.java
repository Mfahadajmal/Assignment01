package j$.util.stream;

import j$.util.Optional;

/* loaded from: classes2.dex */
final class r extends AbstractC0077s {
    static final C0074q c;

    static {
        T0 t0 = T0.REFERENCE;
        c = new C0074q(true, t0, Optional.empty(), new C0072p(1), new C0058i(8));
        new C0074q(false, t0, Optional.empty(), new C0072p(1), new C0058i(8));
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        if (this.a) {
            return Optional.of(this.b);
        }
        return null;
    }
}
