package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class G implements c1 {
    public static void c() {
        throw new IllegalStateException("called wrong accept method");
    }

    public static void e(F0 f0, Double d) {
        if (!f1.a) {
            f0.accept(d.doubleValue());
        } else {
            f1.a(f0.getClass(), "{0} calling Sink.OfDouble.accept(Double)");
            throw null;
        }
    }

    public static void g(G0 g0, Integer num) {
        if (!f1.a) {
            g0.accept(num.intValue());
        } else {
            f1.a(g0.getClass(), "{0} calling Sink.OfInt.accept(Integer)");
            throw null;
        }
    }

    public static void i() {
        throw new IllegalStateException("called wrong accept method");
    }

    public static Object[] j(J j, IntFunction intFunction) {
        if (!f1.a) {
            if (j.f() < 2147483639) {
                Object[] objArr = (Object[]) intFunction.apply((int) j.f());
                j.k(objArr, 0);
                return objArr;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        f1.a(j.getClass(), "{0} calling Node.OfPrimitive.asArray");
        throw null;
    }

    public static void k(H h, Double[] dArr, int i) {
        if (f1.a) {
            f1.a(h.getClass(), "{0} calling Node.OfDouble.copyInto(Double[], int)");
            throw null;
        }
        double[] dArr2 = (double[]) h.d();
        for (int i2 = 0; i2 < dArr2.length; i2++) {
            dArr[i + i2] = Double.valueOf(dArr2[i2]);
        }
    }

    public static void l(I i, Integer[] numArr, int i2) {
        if (!f1.a) {
            int[] iArr = (int[]) i.d();
            for (int i3 = 0; i3 < iArr.length; i3++) {
                numArr[i2 + i3] = Integer.valueOf(iArr[i3]);
            }
            return;
        }
        f1.a(i.getClass(), "{0} calling Node.OfInt.copyInto(Integer[], int)");
        throw null;
    }

    public static void m(H h, Consumer consumer) {
        if (consumer instanceof DoubleConsumer) {
            h.g((DoubleConsumer) consumer);
        } else {
            if (f1.a) {
                f1.a(h.getClass(), "{0} calling Node.OfLong.forEachRemaining(Consumer)");
                throw null;
            }
            ((j$.util.A) h.spliterator()).forEachRemaining(consumer);
        }
    }

    public static void n(I i, Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            i.g((IntConsumer) consumer);
        } else if (!f1.a) {
            ((j$.util.B) i.spliterator()).forEachRemaining(consumer);
        } else {
            f1.a(i.getClass(), "{0} calling Node.OfInt.forEachRemaining(Consumer)");
            throw null;
        }
    }

    public static K o(K k, IntFunction intFunction) {
        if (k.m() > 0) {
            long f = k.f();
            if (f < 2147483639) {
                Object[] objArr = (Object[]) intFunction.apply((int) f);
                new C0069n0(k, objArr, 1).invoke();
                return new M(objArr);
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        return k;
    }

    public static I p(I i) {
        if (i.m() > 0) {
            long f = i.f();
            if (f < 2147483639) {
                int[] iArr = new int[(int) f];
                new C0069n0(i, iArr, 0).invoke();
                return new Z(iArr);
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        return i;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [j$.util.stream.IntStream, j$.util.stream.b] */
    public static IntStream q(j$.util.B b) {
        S0 s0 = S0.DISTINCT;
        int a = b.a();
        int i = a & 4;
        int i2 = S0.f;
        if (i != 0) {
            b.o();
        }
        return new AbstractC0044b(b, a & i2, false);
    }

    public static Stream s(Spliterator spliterator, boolean z) {
        int i;
        spliterator.getClass();
        S0 s0 = S0.DISTINCT;
        int a = spliterator.a();
        int i2 = a & 4;
        int i3 = S0.f;
        if (i2 != 0 && spliterator.o() != null) {
            i = a & i3 & (-5);
        } else {
            i = a & i3;
        }
        return new C0068n(spliterator, i, z);
    }

    @Override // j$.util.stream.c1
    public Object a(AbstractC0044b abstractC0044b, Spliterator spliterator) {
        InterfaceC0088x0 r = r();
        abstractC0044b.t(spliterator, r);
        return r.get();
    }

    @Override // j$.util.stream.c1
    public Object b(AbstractC0044b abstractC0044b, Spliterator spliterator) {
        return ((InterfaceC0088x0) new C0092z0(this, abstractC0044b, spliterator).invoke()).get();
    }

    @Override // j$.util.stream.c1
    public /* synthetic */ int d() {
        return 0;
    }

    public abstract InterfaceC0088x0 r();
}
