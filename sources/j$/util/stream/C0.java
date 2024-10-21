package j$.util.stream;

import j$.util.Optional;
import j$.util.Spliterator;
import j$.util.stream.Collector;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

/* loaded from: classes2.dex */
abstract class C0 extends AbstractC0044b implements Stream {
    @Override // j$.util.stream.Stream
    public final boolean anyMatch(Predicate predicate) {
        C c = C.ANY;
        predicate.getClass();
        c.getClass();
        return ((Boolean) c(new D(T0.REFERENCE, c, new C0060j(1, c, predicate)))).booleanValue();
    }

    @Override // j$.util.stream.Stream
    public final Object collect(Collector collector) {
        Object c;
        if (j() && collector.a().contains(Collector.Characteristics.CONCURRENT) && (!i() || collector.a().contains(Collector.Characteristics.UNORDERED))) {
            c = collector.d().get();
            forEach(new C0060j(4, collector.b(), c));
        } else {
            collector.getClass();
            Supplier d = collector.d();
            BiConsumer b = collector.b();
            c = c(new C0078s0(T0.REFERENCE, collector.c(), b, d, collector));
        }
        if (!collector.a().contains(Collector.Characteristics.IDENTITY_FINISH)) {
            return collector.e().apply(c);
        }
        return c;
    }

    @Override // j$.util.stream.Stream
    public final Stream distinct() {
        return new C0068n(this, S0.m | S0.r);
    }

    @Override // j$.util.stream.AbstractC0044b
    final K e(AbstractC0044b abstractC0044b, Spliterator spliterator, boolean z, IntFunction intFunction) {
        long f = abstractC0044b.f(spliterator);
        if (f >= 0 && spliterator.m(16384)) {
            if (f < 2147483639) {
                Object[] objArr = (Object[]) intFunction.apply((int) f);
                new C0061j0(spliterator, abstractC0044b, objArr).invoke();
                return new M(objArr);
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        K k = (K) new P(abstractC0044b, spliterator, new C0042a(2, intFunction), new C0058i(13)).invoke();
        if (z) {
            return G.o(k, intFunction);
        }
        return k;
    }

    @Override // j$.util.stream.Stream
    public final Stream filter(Predicate predicate) {
        predicate.getClass();
        return new C0089y(this, S0.r, predicate, 1);
    }

    @Override // j$.util.stream.Stream
    public final Optional findFirst() {
        return (Optional) c(r.c);
    }

    @Override // j$.util.stream.Stream
    public void forEach(Consumer consumer) {
        consumer.getClass();
        c(new C0081u(consumer));
    }

    @Override // j$.util.stream.AbstractC0044b
    final boolean g(Spliterator spliterator, H0 h0) {
        boolean l;
        do {
            l = h0.l();
            if (l) {
                break;
            }
        } while (spliterator.r(h0));
        return l;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [j$.util.stream.F, j$.util.stream.M] */
    @Override // j$.util.stream.AbstractC0044b
    final F l(long j, IntFunction intFunction) {
        if (j >= 0 && j < 2147483639) {
            return new M(j, intFunction);
        }
        return new C0065l0();
    }

    @Override // j$.util.stream.Stream
    public final Stream map(Function function) {
        function.getClass();
        return new C0089y(this, S0.o | S0.n, function, 2);
    }

    @Override // j$.util.stream.Stream
    public final DoubleStream mapToDouble(ToDoubleFunction toDoubleFunction) {
        toDoubleFunction.getClass();
        return new B0(this, S0.o | S0.n, toDoubleFunction);
    }

    @Override // j$.util.stream.Stream
    public final IntStream mapToInt(ToIntFunction toIntFunction) {
        toIntFunction.getClass();
        return new A0(this, S0.o | S0.n, toIntFunction);
    }

    @Override // j$.util.stream.Stream
    public final Optional min(Comparator comparator) {
        comparator.getClass();
        return (Optional) c(new C0075q0(T0.REFERENCE, new j$.util.function.a(comparator), 1));
    }

    @Override // j$.util.stream.AbstractC0044b
    final Spliterator s(AbstractC0044b abstractC0044b, Supplier supplier, boolean z) {
        return new U0(abstractC0044b, supplier, z);
    }

    @Override // j$.util.stream.Stream
    public final Object[] toArray(IntFunction intFunction) {
        return G.o(d(intFunction), intFunction).i(intFunction);
    }
}
