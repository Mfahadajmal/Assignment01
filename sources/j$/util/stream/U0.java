package j$.util.stream;

import j$.util.Spliterator;
import java.util.Comparator;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/* loaded from: classes2.dex */
abstract class U0 implements Spliterator {
    final boolean a;
    final AbstractC0044b b;
    private Supplier c;
    Spliterator d;
    H0 e;
    BooleanSupplier f;
    long g;
    AbstractC0052f h;
    boolean i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public U0(AbstractC0044b abstractC0044b, Spliterator spliterator, boolean z) {
        this.b = abstractC0044b;
        this.c = null;
        this.d = spliterator;
        this.a = z;
    }

    private boolean e() {
        while (this.h.f() == 0) {
            if (this.e.l() || !this.f.getAsBoolean()) {
                if (this.i) {
                    return false;
                }
                this.e.c();
                this.i = true;
            }
        }
        return true;
    }

    @Override // j$.util.Spliterator
    public final int a() {
        h();
        int s = S0.s(this.b.h()) & S0.f;
        if ((s & 64) != 0) {
            return (s & (-16449)) | (this.d.a() & 16448);
        }
        return s;
    }

    @Override // j$.util.Spliterator
    public Spliterator b() {
        if (!this.a || this.h != null || this.i) {
            return null;
        }
        h();
        Spliterator b = this.d.b();
        if (b == null) {
            return null;
        }
        return l(b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean c() {
        AbstractC0052f abstractC0052f = this.h;
        if (abstractC0052f == null) {
            if (this.i) {
                return false;
            }
            h();
            j();
            this.g = 0L;
            this.e.e(this.d.n());
            return e();
        }
        long j = this.g + 1;
        this.g = j;
        boolean z = j < abstractC0052f.f();
        if (z) {
            return z;
        }
        this.g = 0L;
        this.h.clear();
        return e();
    }

    @Override // j$.util.Spliterator
    public final long d() {
        h();
        return this.d.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void h() {
        if (this.d == null) {
            this.d = (Spliterator) this.c.get();
            this.c = null;
        }
    }

    abstract void j();

    abstract U0 l(Spliterator spliterator);

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean m(int i) {
        return j$.util.z.j(this, i);
    }

    @Override // j$.util.Spliterator
    public final long n() {
        h();
        if (S0.SIZED.p(this.b.h())) {
            return this.d.n();
        }
        return -1L;
    }

    @Override // j$.util.Spliterator
    public final Comparator o() {
        if (j$.util.z.j(this, 4)) {
            return null;
        }
        throw new IllegalStateException();
    }

    public final String toString() {
        return String.format("%s[%s]", getClass().getName(), this.d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public U0(AbstractC0044b abstractC0044b, Supplier supplier, boolean z) {
        this.b = abstractC0044b;
        this.c = supplier;
        this.d = null;
        this.a = z;
    }
}
