package j$.util.stream;

import j$.util.OptionalDouble;
import j$.util.Spliterator;
import java.util.function.DoubleConsumer;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;

/* loaded from: classes2.dex */
final class B0 extends AbstractC0044b implements DoubleStream {
    final /* synthetic */ ToDoubleFunction k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public B0(C0 c0, int i, ToDoubleFunction toDoubleFunction) {
        super(c0, i);
        this.k = toDoubleFunction;
    }

    @Override // j$.util.stream.AbstractC0044b
    final K e(AbstractC0044b abstractC0044b, Spliterator spliterator, boolean z, IntFunction intFunction) {
        long f = abstractC0044b.f(spliterator);
        if (f >= 0 && spliterator.m(16384)) {
            if (f < 2147483639) {
                double[] dArr = new double[(int) f];
                new C0057h0(spliterator, abstractC0044b, dArr).invoke();
                return new V(dArr);
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        H h = (H) new P(abstractC0044b, spliterator, new C0058i(9), new C0058i(10)).invoke();
        if (z && h.m() > 0) {
            long f2 = h.f();
            if (f2 < 2147483639) {
                double[] dArr2 = new double[(int) f2];
                new C0069n0(h, dArr2, 0).invoke();
                return new V(dArr2);
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        return h;
    }

    @Override // j$.util.stream.AbstractC0044b
    final boolean g(Spliterator spliterator, final H0 h0) {
        DoubleConsumer doubleConsumer;
        boolean l;
        if (spliterator instanceof j$.util.A) {
            j$.util.A a = (j$.util.A) spliterator;
            if (h0 instanceof DoubleConsumer) {
                doubleConsumer = (DoubleConsumer) h0;
            } else if (!f1.a) {
                h0.getClass();
                doubleConsumer = new DoubleConsumer() { // from class: j$.util.stream.o
                    @Override // java.util.function.DoubleConsumer
                    public final void accept(double d) {
                        H0.this.accept(d);
                    }

                    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer2) {
                        return j$.nio.file.attribute.a.a(this, doubleConsumer2);
                    }
                };
            } else {
                f1.a(AbstractC0044b.class, "using DoubleStream.adapt(Sink<Double> s)");
                throw null;
            }
            do {
                l = h0.l();
                if (l) {
                    break;
                }
            } while (a.q(doubleConsumer));
            return l;
        }
        if (f1.a) {
            f1.a(AbstractC0044b.class, "using DoubleStream.adapt(Spliterator<Double> s)");
            throw null;
        }
        throw new UnsupportedOperationException("DoubleStream.adapt(Spliterator<Double> s)");
    }

    /* JADX WARN: Type inference failed for: r5v4, types: [j$.util.stream.V, j$.util.stream.F] */
    @Override // j$.util.stream.AbstractC0044b
    final F l(long j, IntFunction intFunction) {
        if (j >= 0 && j < 2147483639) {
            return new V(j);
        }
        return new X();
    }

    @Override // j$.util.stream.DoubleStream
    public final OptionalDouble max() {
        return (OptionalDouble) c(new C0075q0(T0.DOUBLE_VALUE, new C0072p(0), 0));
    }

    @Override // j$.util.stream.AbstractC0044b
    final boolean o() {
        return false;
    }

    @Override // j$.util.stream.AbstractC0044b
    final H0 p(int i, H0 h0) {
        return new C0066m(this, h0, 4);
    }

    @Override // j$.util.stream.AbstractC0044b
    final Spliterator s(AbstractC0044b abstractC0044b, Supplier supplier, boolean z) {
        return new U0(abstractC0044b, supplier, z);
    }
}
