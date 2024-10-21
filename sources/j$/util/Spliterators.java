package j$.util;

import java.util.Iterator;

/* loaded from: classes2.dex */
public final class Spliterators {
    private static final Spliterator a = null;

    private static void a(int i, int i2, int i3) {
        if (i2 <= i3) {
            if (i2 < 0) {
                throw new ArrayIndexOutOfBoundsException(i2);
            }
            if (i3 > i) {
                throw new ArrayIndexOutOfBoundsException(i3);
            }
            return;
        }
        throw new ArrayIndexOutOfBoundsException("origin(" + i2 + ") > fence(" + i3 + ")");
    }

    public static v b(A a2) {
        a2.getClass();
        return new F(a2);
    }

    public static x c(B b) {
        b.getClass();
        return new E(b);
    }

    public static Iterator d(Spliterator spliterator) {
        spliterator.getClass();
        return new D(spliterator);
    }

    public static A e(double[] dArr, int i, int i2) {
        dArr.getClass();
        a(dArr.length, i, i2);
        return new H(dArr, i, i2, 1040);
    }

    public static B f(int[] iArr, int i, int i2) {
        iArr.getClass();
        a(iArr.length, i, i2);
        return new M(iArr, i, i2, 1040);
    }

    public static Spliterator g(Object[] objArr, int i, int i2, int i3) {
        objArr.getClass();
        a(objArr.length, i, i2);
        return new G(objArr, i, i2, i3);
    }

    public static <T> Spliterator<T> spliterator(java.util.Collection<? extends T> collection, int i) {
        collection.getClass();
        return new N(collection, i);
    }
}
