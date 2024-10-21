package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* loaded from: classes2.dex */
final class X0 extends U0 implements j$.util.A {
    @Override // j$.util.stream.U0, j$.util.Spliterator
    public final Spliterator b() {
        return (j$.util.A) super.b();
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.util.z.c(this, consumer);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [j$.util.stream.f, j$.util.stream.O0, java.util.function.DoubleConsumer] */
    @Override // j$.util.stream.U0
    final void j() {
        ?? o0 = new O0();
        this.h = o0;
        this.e = this.b.u(new W0(o0, 0));
        this.f = new C0042a(3, this);
    }

    @Override // j$.util.C
    /* renamed from: k */
    public final void t(DoubleConsumer doubleConsumer) {
        if (this.h == null && !this.i) {
            doubleConsumer.getClass();
            h();
            W0 w0 = new W0(doubleConsumer, 1);
            this.b.t(this.d, w0);
            this.i = true;
            return;
        }
        do {
        } while (q(doubleConsumer));
    }

    @Override // j$.util.stream.U0
    final U0 l(Spliterator spliterator) {
        return new U0(this.b, spliterator, this.a);
    }

    @Override // j$.util.C
    public final boolean q(DoubleConsumer doubleConsumer) {
        double d;
        doubleConsumer.getClass();
        boolean c = c();
        if (c) {
            K0 k0 = (K0) this.h;
            long j = this.g;
            int r = k0.r(j);
            if (k0.b == 0 && r == 0) {
                d = ((double[]) k0.d)[(int) j];
            } else {
                d = ((double[][]) k0.e)[r][(int) (j - k0.c[r])];
            }
            doubleConsumer.accept(d);
        }
        return c;
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean r(Consumer consumer) {
        return j$.util.z.l(this, consumer);
    }
}
