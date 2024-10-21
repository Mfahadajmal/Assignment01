package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/* loaded from: classes2.dex */
abstract class A extends AbstractC0044b implements IntStream {
    @Override // j$.util.stream.IntStream
    public final Stream boxed() {
        return new C0089y(this, new C0072p(3));
    }

    @Override // j$.util.stream.AbstractC0044b
    final K e(AbstractC0044b abstractC0044b, Spliterator spliterator, boolean z, IntFunction intFunction) {
        long f = abstractC0044b.f(spliterator);
        if (f >= 0 && spliterator.m(16384)) {
            if (f < 2147483639) {
                int[] iArr = new int[(int) f];
                new C0059i0(spliterator, abstractC0044b, iArr).invoke();
                return new Z(iArr);
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        I i = (I) new P(abstractC0044b, spliterator, new C0058i(11), new C0058i(12)).invoke();
        if (z) {
            return G.p(i);
        }
        return i;
    }

    @Override // j$.util.stream.AbstractC0044b
    final boolean g(Spliterator spliterator, final H0 h0) {
        IntConsumer intConsumer;
        boolean l;
        if (spliterator instanceof j$.util.B) {
            j$.util.B b = (j$.util.B) spliterator;
            if (h0 instanceof IntConsumer) {
                intConsumer = (IntConsumer) h0;
            } else if (!f1.a) {
                h0.getClass();
                intConsumer = new IntConsumer() { // from class: j$.util.stream.w
                    @Override // java.util.function.IntConsumer
                    public final void accept(int i) {
                        H0.this.accept(i);
                    }

                    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer2) {
                        return j$.nio.file.attribute.a.b(this, intConsumer2);
                    }
                };
            } else {
                f1.a(AbstractC0044b.class, "using IntStream.adapt(Sink<Integer> s)");
                throw null;
            }
            do {
                l = h0.l();
                if (l) {
                    break;
                }
            } while (b.q(intConsumer));
            return l;
        }
        if (f1.a) {
            f1.a(AbstractC0044b.class, "using IntStream.adapt(Spliterator<Integer> s)");
            throw null;
        }
        throw new UnsupportedOperationException("IntStream.adapt(Spliterator<Integer> s)");
    }

    /* JADX WARN: Type inference failed for: r5v4, types: [j$.util.stream.F, j$.util.stream.Z] */
    @Override // j$.util.stream.AbstractC0044b
    final F l(long j, IntFunction intFunction) {
        if (j >= 0 && j < 2147483639) {
            return new Z(j);
        }
        return new C0045b0();
    }

    @Override // j$.util.stream.AbstractC0044b
    final Spliterator s(AbstractC0044b abstractC0044b, Supplier supplier, boolean z) {
        return new U0(abstractC0044b, supplier, z);
    }

    @Override // j$.util.stream.IntStream
    public final int sum() {
        return ((Integer) c(new C0075q0(T0.INT_VALUE, new C0072p(4), 2))).intValue();
    }

    @Override // j$.util.stream.IntStream
    public final int[] toArray() {
        return (int[]) G.p((I) d(new C0072p(2))).d();
    }
}
