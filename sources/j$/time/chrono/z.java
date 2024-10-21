package j$.time.chrono;

import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

/* loaded from: classes2.dex */
public final class z extends AbstractC0014d {
    static final j$.time.g d = j$.time.g.V(1873, 1, 1);
    private static final long serialVersionUID = -305327627230580483L;
    private final transient j$.time.g a;
    private transient A b;
    private transient int c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public z(j$.time.g gVar) {
        if (!gVar.R(d)) {
            A m = A.m(gVar);
            this.b = m;
            this.c = (gVar.Q() - m.t().Q()) + 1;
            this.a = gVar;
            return;
        }
        throw new RuntimeException("JapaneseDate before Meiji 6 is not supported");
    }

    private z Q(j$.time.g gVar) {
        return gVar.equals(this.a) ? this : new z(gVar);
    }

    private z R(A a, int i) {
        x.d.getClass();
        if (a instanceof A) {
            int Q = (a.t().Q() + i) - 1;
            if (i == 1 || (Q >= -999999999 && Q <= 999999999 && Q >= a.t().Q() && a == A.m(j$.time.g.V(Q, 1, 1)))) {
                return Q(this.a.g0(Q));
            }
            throw new RuntimeException("Invalid yearOfEra value");
        }
        throw new ClassCastException("Era must be JapaneseEra");
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new G((byte) 4, this);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.chrono.InterfaceC0012b
    public final long A() {
        return this.a.A();
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.chrono.InterfaceC0012b
    public final InterfaceC0015e B(j$.time.k kVar) {
        return C0017g.J(this, kVar);
    }

    @Override // j$.time.chrono.AbstractC0014d
    public final o J() {
        return this.b;
    }

    @Override // j$.time.chrono.AbstractC0014d
    final InterfaceC0012b L(long j) {
        return Q(this.a.Z(j));
    }

    @Override // j$.time.chrono.AbstractC0014d
    final InterfaceC0012b M(long j) {
        return Q(this.a.a0(j));
    }

    @Override // j$.time.chrono.AbstractC0014d
    final InterfaceC0012b N(long j) {
        return Q(this.a.b0(j));
    }

    @Override // j$.time.chrono.AbstractC0014d
    /* renamed from: O */
    public final InterfaceC0012b r(j$.time.temporal.m mVar) {
        return (z) super.r(mVar);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.temporal.Temporal
    /* renamed from: P, reason: merged with bridge method [inline-methods] */
    public final z e(long j, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
            if (z(aVar) == j) {
                return this;
            }
            int[] iArr = y.a;
            int i = iArr[aVar.ordinal()];
            j$.time.g gVar = this.a;
            if (i == 3 || i == 8 || i == 9) {
                int a = x.d.q(aVar).a(j, aVar);
                int i2 = iArr[aVar.ordinal()];
                if (i2 != 3) {
                    if (i2 != 8) {
                        if (i2 == 9) {
                            return Q(gVar.g0(a));
                        }
                    } else {
                        return R(A.C(a), this.c);
                    }
                } else {
                    return R(this.b, a);
                }
            }
            return Q(gVar.e(j, oVar));
        }
        return (z) super.e(j, oVar);
    }

    @Override // j$.time.chrono.InterfaceC0012b
    public final n a() {
        return x.d;
    }

    @Override // j$.time.chrono.AbstractC0014d
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof z) {
            return this.a.equals(((z) obj).a);
        }
        return false;
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.chrono.InterfaceC0012b, j$.time.temporal.l
    public final boolean f(j$.time.temporal.o oVar) {
        if (oVar == j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH || oVar == j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR || oVar == j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH || oVar == j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR) {
            return false;
        }
        return oVar instanceof j$.time.temporal.a ? ((j$.time.temporal.a) oVar).z() : oVar != null && oVar.p(this);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.chrono.InterfaceC0012b, j$.time.temporal.Temporal
    public final InterfaceC0012b g(long j, TemporalUnit temporalUnit) {
        return (z) super.g(j, temporalUnit);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.chrono.InterfaceC0012b
    public final int hashCode() {
        x.d.getClass();
        return this.a.hashCode() ^ (-688086063);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.temporal.Temporal
    public final Temporal n(long j, ChronoUnit chronoUnit) {
        return (z) super.n(j, chronoUnit);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.temporal.Temporal
    public final Temporal r(j$.time.g gVar) {
        return (z) super.r(gVar);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        int T;
        long j;
        int i;
        if (oVar instanceof j$.time.temporal.a) {
            if (f(oVar)) {
                j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
                int i2 = y.a[aVar.ordinal()];
                j$.time.g gVar = this.a;
                if (i2 != 1) {
                    A a = this.b;
                    if (i2 != 2) {
                        if (i2 != 3) {
                            return x.d.q(aVar);
                        }
                        int Q = a.t().Q();
                        A x = a.x();
                        if (x != null) {
                            i = (x.t().Q() - Q) + 1;
                        } else {
                            i = 999999999 - Q;
                        }
                        j = i;
                        return j$.time.temporal.s.j(1L, j);
                    }
                    A x2 = a.x();
                    if (x2 != null && x2.t().Q() == gVar.Q()) {
                        T = x2.t().N() - 1;
                    } else if (gVar.S()) {
                        T = 366;
                    } else {
                        T = 365;
                    }
                    if (this.c == 1) {
                        T -= a.t().N() - 1;
                    }
                } else {
                    T = gVar.T();
                }
                j = T;
                return j$.time.temporal.s.j(1L, j);
            }
            throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
        }
        return oVar.s(this);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.chrono.InterfaceC0012b
    /* renamed from: u */
    public final InterfaceC0012b n(long j, TemporalUnit temporalUnit) {
        return (z) super.n(j, temporalUnit);
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            int i = y.a[((j$.time.temporal.a) oVar).ordinal()];
            int i2 = this.c;
            A a = this.b;
            j$.time.g gVar = this.a;
            switch (i) {
                case 2:
                    if (i2 == 1) {
                        return (gVar.N() - a.t().N()) + 1;
                    }
                    return gVar.N();
                case 3:
                    return i2;
                case 4:
                case 5:
                case 6:
                case 7:
                    throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
                case 8:
                    return a.getValue();
                default:
                    return gVar.z(oVar);
            }
        }
        return oVar.h(this);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.temporal.Temporal
    public final Temporal g(long j, TemporalUnit temporalUnit) {
        return (z) super.g(j, temporalUnit);
    }
}
