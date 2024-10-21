package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
final class a1 extends U0 {
    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        if (this.h == null && !this.i) {
            consumer.getClass();
            h();
            C0042a c0042a = new C0042a(7, consumer);
            this.b.t(this.d, c0042a);
            this.i = true;
            return;
        }
        do {
        } while (r(consumer));
    }

    @Override // j$.util.stream.U0
    final void j() {
        P0 p0 = new P0();
        this.h = p0;
        this.e = this.b.u(new C0042a(5, p0));
        this.f = new C0042a(6, this);
    }

    @Override // j$.util.stream.U0
    final U0 l(Spliterator spliterator) {
        return new U0(this.b, spliterator, this.a);
    }

    @Override // j$.util.Spliterator
    public final boolean r(Consumer consumer) {
        Object obj;
        consumer.getClass();
        boolean c = c();
        if (c) {
            P0 p0 = (P0) this.h;
            long j = this.g;
            if (p0.b == 0) {
                if (j < p0.a) {
                    obj = p0.d[(int) j];
                } else {
                    throw new IndexOutOfBoundsException(Long.toString(j));
                }
            } else {
                if (j < p0.f()) {
                    for (int i = 0; i <= p0.b; i++) {
                        long j2 = p0.c[i];
                        Object[] objArr = p0.e[i];
                        if (j < objArr.length + j2) {
                            obj = objArr[(int) (j - j2)];
                        }
                    }
                    throw new IndexOutOfBoundsException(Long.toString(j));
                }
                throw new IndexOutOfBoundsException(Long.toString(j));
            }
            consumer.accept(obj);
        }
        return c;
    }
}
