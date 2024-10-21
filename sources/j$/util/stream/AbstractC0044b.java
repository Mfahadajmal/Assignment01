package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: j$.util.stream.b, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract class AbstractC0044b implements InterfaceC0056h {
    private final AbstractC0044b a;
    private final AbstractC0044b b;
    protected final int c;
    private AbstractC0044b d;
    private int e;
    private int f;
    private Spliterator g;
    private boolean h;
    private boolean i;
    private boolean j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractC0044b(Spliterator spliterator, int i, boolean z) {
        this.b = null;
        this.g = spliterator;
        this.a = this;
        int i2 = S0.g & i;
        this.c = i2;
        this.f = (~(i2 << 1)) & S0.l;
        this.e = 0;
        this.j = z;
    }

    private Spliterator q(int i) {
        int i2;
        int i3;
        AbstractC0044b abstractC0044b = this.a;
        Spliterator spliterator = abstractC0044b.g;
        if (spliterator != null) {
            abstractC0044b.g = null;
            if (abstractC0044b.j && abstractC0044b.i) {
                AbstractC0044b abstractC0044b2 = abstractC0044b.d;
                int i4 = 1;
                while (abstractC0044b != this) {
                    int i5 = abstractC0044b2.c;
                    if (abstractC0044b2.o()) {
                        if (S0.SHORT_CIRCUIT.p(i5)) {
                            i5 &= ~S0.s;
                        }
                        spliterator = abstractC0044b2.n(abstractC0044b, spliterator);
                        if (spliterator.m(64)) {
                            i2 = (~S0.r) & i5;
                            i3 = S0.q;
                        } else {
                            i2 = (~S0.q) & i5;
                            i3 = S0.r;
                        }
                        i5 = i2 | i3;
                        i4 = 0;
                    }
                    abstractC0044b2.e = i4;
                    abstractC0044b2.f = S0.h(i5, abstractC0044b.f);
                    i4++;
                    AbstractC0044b abstractC0044b3 = abstractC0044b2;
                    abstractC0044b2 = abstractC0044b2.d;
                    abstractC0044b = abstractC0044b3;
                }
            }
            if (i != 0) {
                this.f = S0.h(i, this.f);
            }
            return spliterator;
        }
        throw new IllegalStateException("source already consumed or closed");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(Spliterator spliterator, H0 h0) {
        h0.getClass();
        if (!S0.SHORT_CIRCUIT.p(this.f)) {
            h0.e(spliterator.n());
            spliterator.forEachRemaining(h0);
            h0.c();
        } else {
            AbstractC0044b abstractC0044b = this;
            while (abstractC0044b.e > 0) {
                abstractC0044b = abstractC0044b.b;
            }
            h0.e(spliterator.n());
            abstractC0044b.g(spliterator, h0);
            h0.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final K b(Spliterator spliterator, boolean z, IntFunction intFunction) {
        if (this.a.j) {
            return e(this, spliterator, z, intFunction);
        }
        F l = l(f(spliterator), intFunction);
        t(spliterator, l);
        return l.s();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object c(c1 c1Var) {
        if (!this.h) {
            this.h = true;
            if (this.a.j) {
                return c1Var.b(this, q(c1Var.d()));
            }
            return c1Var.a(this, q(c1Var.d()));
        }
        throw new IllegalStateException("stream has already been operated upon or closed");
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        this.h = true;
        this.g = null;
        this.a.getClass();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final K d(IntFunction intFunction) {
        AbstractC0044b abstractC0044b;
        if (!this.h) {
            this.h = true;
            if (this.a.j && (abstractC0044b = this.b) != null && o()) {
                this.e = 0;
                return m(abstractC0044b, abstractC0044b.q(0), intFunction);
            }
            return b(q(0), true, intFunction);
        }
        throw new IllegalStateException("stream has already been operated upon or closed");
    }

    abstract K e(AbstractC0044b abstractC0044b, Spliterator spliterator, boolean z, IntFunction intFunction);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long f(Spliterator spliterator) {
        if (S0.SIZED.p(this.f)) {
            return spliterator.n();
        }
        return -1L;
    }

    abstract boolean g(Spliterator spliterator, H0 h0);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int h() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean i() {
        return S0.ORDERED.p(this.f);
    }

    public final boolean j() {
        return this.a.j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Spliterator k() {
        return q(0);
    }

    abstract F l(long j, IntFunction intFunction);

    /* JADX INFO: Access modifiers changed from: package-private */
    public K m(AbstractC0044b abstractC0044b, Spliterator spliterator, IntFunction intFunction) {
        throw new UnsupportedOperationException("Parallel evaluation is not supported");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Spliterator n(AbstractC0044b abstractC0044b, Spliterator spliterator) {
        return m(abstractC0044b, spliterator, new C0058i(14)).spliterator();
    }

    abstract boolean o();

    abstract H0 p(int i, H0 h0);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Spliterator r() {
        AbstractC0044b abstractC0044b = this.a;
        if (this != abstractC0044b) {
            throw new IllegalStateException();
        }
        if (this.h) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        this.h = true;
        Spliterator spliterator = abstractC0044b.g;
        if (spliterator == null) {
            throw new IllegalStateException("source already consumed or closed");
        }
        abstractC0044b.g = null;
        return spliterator;
    }

    abstract Spliterator s(AbstractC0044b abstractC0044b, Supplier supplier, boolean z);

    @Override // j$.util.stream.InterfaceC0056h
    public final Spliterator spliterator() {
        if (!this.h) {
            this.h = true;
            AbstractC0044b abstractC0044b = this.a;
            if (this == abstractC0044b) {
                Spliterator spliterator = abstractC0044b.g;
                if (spliterator != null) {
                    abstractC0044b.g = null;
                    return spliterator;
                }
                throw new IllegalStateException("source already consumed or closed");
            }
            return s(this, new C0042a(0, this), abstractC0044b.j);
        }
        throw new IllegalStateException("stream has already been operated upon or closed");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final H0 t(Spliterator spliterator, H0 h0) {
        h0.getClass();
        a(spliterator, u(h0));
        return h0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final H0 u(H0 h0) {
        h0.getClass();
        AbstractC0044b abstractC0044b = this;
        while (abstractC0044b.e > 0) {
            AbstractC0044b abstractC0044b2 = abstractC0044b.b;
            h0 = abstractC0044b.p(abstractC0044b2.f, h0);
            abstractC0044b = abstractC0044b2;
        }
        return h0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Spliterator v(Spliterator spliterator) {
        if (this.e == 0) {
            return spliterator;
        }
        return s(this, new C0042a(8, spliterator), this.a.j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractC0044b(AbstractC0044b abstractC0044b, int i) {
        if (abstractC0044b.h) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        abstractC0044b.h = true;
        abstractC0044b.d = this;
        this.b = abstractC0044b;
        this.c = S0.h & i;
        this.f = S0.h(i, abstractC0044b.f);
        AbstractC0044b abstractC0044b2 = abstractC0044b.a;
        this.a = abstractC0044b2;
        if (o()) {
            abstractC0044b2.i = true;
        }
        this.e = abstractC0044b.e + 1;
    }
}
