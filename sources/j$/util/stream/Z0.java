package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* loaded from: classes2.dex */
final class Z0 extends U0 implements j$.util.B {
    @Override // j$.util.stream.U0, j$.util.Spliterator
    public final Spliterator b() {
        return (j$.util.B) super.b();
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.util.z.e(this, consumer);
    }

    @Override // j$.util.C
    /* renamed from: i */
    public final boolean q(IntConsumer intConsumer) {
        int i;
        intConsumer.getClass();
        boolean c = c();
        if (c) {
            M0 m0 = (M0) this.h;
            long j = this.g;
            int r = m0.r(j);
            if (m0.b == 0 && r == 0) {
                i = ((int[]) m0.d)[(int) j];
            } else {
                i = ((int[][]) m0.e)[r][(int) (j - m0.c[r])];
            }
            intConsumer.accept(i);
        }
        return c;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [j$.util.stream.f, java.util.function.IntConsumer, j$.util.stream.O0] */
    @Override // j$.util.stream.U0
    final void j() {
        ?? o0 = new O0();
        this.h = o0;
        this.e = this.b.u(new Y0(o0, 0));
        this.f = new C0042a(4, this);
    }

    @Override // j$.util.stream.U0
    final U0 l(Spliterator spliterator) {
        return new U0(this.b, spliterator, this.a);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean r(Consumer consumer) {
        return j$.util.z.p(this, consumer);
    }

    @Override // j$.util.C
    public final void t(IntConsumer intConsumer) {
        if (this.h == null && !this.i) {
            intConsumer.getClass();
            h();
            Y0 y0 = new Y0(intConsumer, 1);
            this.b.t(this.d, y0);
            this.i = true;
            return;
        }
        do {
        } while (q(intConsumer));
    }
}
