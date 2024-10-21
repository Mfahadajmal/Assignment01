package j$.time.chrono;

import j$.time.temporal.Temporal;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes2.dex */
public final class A implements o, Serializable {
    public static final A d;
    private static final A[] e;
    private static final long serialVersionUID = 1466499369062886794L;
    private final transient int a;
    private final transient j$.time.g b;
    private final transient String c;

    static {
        A a = new A(-1, j$.time.g.V(1868, 1, 1), "Meiji");
        d = a;
        A a2 = new A(0, j$.time.g.V(1912, 7, 30), "Taisho");
        A a3 = new A(1, j$.time.g.V(1926, 12, 25), "Showa");
        A a4 = new A(2, j$.time.g.V(1989, 1, 8), "Heisei");
        A a5 = new A(3, j$.time.g.V(2019, 5, 1), "Reiwa");
        e = r8;
        A[] aArr = {a, a2, a3, a4, a5};
    }

    private A(int i, j$.time.g gVar, String str) {
        this.a = i;
        this.b = gVar;
        this.c = str;
    }

    public static A C(int i) {
        int i2 = i + 1;
        if (i2 >= 0) {
            A[] aArr = e;
            if (i2 < aArr.length) {
                return aArr[i2];
            }
        }
        throw new RuntimeException("Invalid era: " + i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long E() {
        int i;
        long f = j$.time.temporal.a.DAY_OF_YEAR.n().f();
        for (A a : e) {
            if (a.b.S()) {
                i = 366;
            } else {
                i = 365;
            }
            f = Math.min(f, (i - a.b.N()) + 1);
            if (a.x() != null) {
                f = Math.min(f, a.x().b.N() - 1);
            }
        }
        return f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long G() {
        int Q = 1000000000 - q().b.Q();
        A[] aArr = e;
        int Q2 = aArr[0].b.Q();
        for (int i = 1; i < aArr.length; i++) {
            A a = aArr[i];
            Q = Math.min(Q, (a.b.Q() - Q2) + 1);
            Q2 = a.b.Q();
        }
        return Q;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static A m(j$.time.g gVar) {
        if (!gVar.R(z.d)) {
            A[] aArr = e;
            for (int length = aArr.length - 1; length >= 0; length--) {
                A a = aArr[length];
                if (gVar.compareTo(a.b) >= 0) {
                    return a;
                }
            }
            return null;
        }
        throw new RuntimeException("JapaneseDate before Meiji 6 are not supported");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static A q() {
        return e[r0.length - 1];
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new G((byte) 5, this);
    }

    @Override // j$.time.temporal.l
    public final /* synthetic */ Object D(j$.time.temporal.q qVar) {
        return AbstractC0019i.m(this, qVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void H(DataOutput dataOutput) {
        dataOutput.writeByte(this.a);
    }

    @Override // j$.time.temporal.l
    public final /* synthetic */ boolean f(j$.time.temporal.o oVar) {
        return AbstractC0019i.i(this, oVar);
    }

    @Override // j$.time.chrono.o
    public final int getValue() {
        return this.a;
    }

    @Override // j$.time.temporal.m
    public final Temporal h(Temporal temporal) {
        return temporal.e(getValue(), j$.time.temporal.a.ERA);
    }

    @Override // j$.time.temporal.l
    public final /* synthetic */ int p(j$.time.temporal.o oVar) {
        return AbstractC0019i.f(this, (j$.time.temporal.a) oVar);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
        if (oVar == aVar) {
            return x.d.q(aVar);
        }
        return j$.time.temporal.k.d(this, oVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final j$.time.g t() {
        return this.b;
    }

    public final String toString() {
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final A x() {
        if (this == q()) {
            return null;
        }
        return C(this.a + 1);
    }

    @Override // j$.time.temporal.l
    public final /* synthetic */ long z(j$.time.temporal.o oVar) {
        return AbstractC0019i.g(this, oVar);
    }
}
