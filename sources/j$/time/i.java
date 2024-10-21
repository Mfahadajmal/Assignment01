package j$.time;

import j$.time.chrono.AbstractC0019i;
import j$.time.chrono.InterfaceC0012b;
import j$.time.chrono.InterfaceC0015e;
import j$.time.chrono.InterfaceC0021k;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes2.dex */
public final class i implements Temporal, j$.time.temporal.m, InterfaceC0015e, Serializable {
    public static final i c = Q(g.d, k.e);
    public static final i d = Q(g.e, k.f);
    private static final long serialVersionUID = 6207766400415563566L;
    private final g a;
    private final k b;

    private i(g gVar, k kVar) {
        this.a = gVar;
        this.b = kVar;
    }

    private int I(i iVar) {
        int I = this.a.I(iVar.a);
        if (I == 0) {
            return this.b.compareTo(iVar.b);
        }
        return I;
    }

    public static i J(Temporal temporal) {
        if (temporal instanceof i) {
            return (i) temporal;
        }
        if (temporal instanceof D) {
            return ((D) temporal).N();
        }
        if (temporal instanceof p) {
            return ((p) temporal).M();
        }
        try {
            return new i(g.K(temporal), k.K(temporal));
        } catch (C0010c e) {
            throw new RuntimeException("Unable to obtain LocalDateTime from TemporalAccessor: " + String.valueOf(temporal) + " of type " + temporal.getClass().getName(), e);
        }
    }

    public static i P(int i) {
        return new i(g.V(i, 12, 31), k.P(0));
    }

    public static i Q(g gVar, k kVar) {
        Objects.a(gVar, "date");
        Objects.a(kVar, "time");
        return new i(gVar, kVar);
    }

    public static i R(long j, int i, A a) {
        Objects.a(a, "offset");
        long j2 = i;
        j$.time.temporal.a.NANO_OF_SECOND.I(j2);
        return new i(g.X(j$.nio.file.attribute.a.c(j + a.Q(), 86400)), k.Q((((int) j$.nio.file.attribute.a.d(r5, r7)) * 1000000000) + j2));
    }

    private i U(g gVar, long j, long j2, long j3, long j4) {
        long j5 = j | j2 | j3 | j4;
        k kVar = this.b;
        if (j5 == 0) {
            return Y(gVar, kVar);
        }
        long j6 = j / 24;
        long j7 = j6 + (j2 / 1440) + (j3 / 86400) + (j4 / 86400000000000L);
        long j8 = 1;
        long j9 = ((j % 24) * 3600000000000L) + ((j2 % 1440) * 60000000000L) + ((j3 % 86400) * 1000000000) + (j4 % 86400000000000L);
        long Y = kVar.Y();
        long j10 = (j9 * j8) + Y;
        long c2 = j$.nio.file.attribute.a.c(j10, 86400000000000L) + (j7 * j8);
        long d2 = j$.nio.file.attribute.a.d(j10, 86400000000000L);
        if (d2 != Y) {
            kVar = k.Q(d2);
        }
        return Y(gVar.Z(c2), kVar);
    }

    private i Y(g gVar, k kVar) {
        return (this.a == gVar && this.b == kVar) ? this : new i(gVar, kVar);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new t((byte) 5, this);
    }

    @Override // j$.time.temporal.l
    public final Object D(j$.time.temporal.q qVar) {
        return qVar == j$.time.temporal.k.f() ? this.a : AbstractC0019i.k(this, qVar);
    }

    public final int K() {
        return this.b.N();
    }

    public final int L() {
        return this.b.O();
    }

    public final int M() {
        return this.a.Q();
    }

    public final boolean N(i iVar) {
        if (iVar instanceof i) {
            if (I(iVar) <= 0) {
                return false;
            }
            return true;
        }
        long A = this.a.A();
        long A2 = iVar.a.A();
        if (A <= A2 && (A != A2 || this.b.Y() <= iVar.b.Y())) {
            return false;
        }
        return true;
    }

    public final boolean O(i iVar) {
        if (iVar instanceof i) {
            if (I(iVar) >= 0) {
                return false;
            }
            return true;
        }
        long A = this.a.A();
        long A2 = iVar.a.A();
        if (A >= A2 && (A != A2 || this.b.Y() >= iVar.b.Y())) {
            return false;
        }
        return true;
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: S, reason: merged with bridge method [inline-methods] */
    public final i g(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            int i = h.a[((ChronoUnit) temporalUnit).ordinal()];
            k kVar = this.b;
            g gVar = this.a;
            switch (i) {
                case 1:
                    return U(this.a, 0L, 0L, 0L, j);
                case 2:
                    i Y = Y(gVar.Z(j / 86400000000L), kVar);
                    return Y.U(Y.a, 0L, 0L, 0L, (j % 86400000000L) * 1000);
                case 3:
                    i Y2 = Y(gVar.Z(j / 86400000), kVar);
                    return Y2.U(Y2.a, 0L, 0L, 0L, (j % 86400000) * 1000000);
                case 4:
                    return T(j);
                case 5:
                    return U(this.a, 0L, j, 0L, 0L);
                case 6:
                    return U(this.a, j, 0L, 0L, 0L);
                case 7:
                    i Y3 = Y(gVar.Z(j / 256), kVar);
                    return Y3.U(Y3.a, (j % 256) * 12, 0L, 0L, 0L);
                default:
                    return Y(gVar.g(j, temporalUnit), kVar);
            }
        }
        return (i) temporalUnit.p(this, j);
    }

    public final i T(long j) {
        return U(this.a, 0L, 0L, j, 0L);
    }

    public final g V() {
        return this.a;
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: W, reason: merged with bridge method [inline-methods] */
    public final i e(long j, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            boolean J2 = ((j$.time.temporal.a) oVar).J();
            k kVar = this.b;
            g gVar = this.a;
            if (J2) {
                return Y(gVar, kVar.e(j, oVar));
            }
            return Y(gVar.e(j, oVar), kVar);
        }
        return (i) oVar.r(this, j);
    }

    public final i X(g gVar) {
        return Y(gVar, this.b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void Z(DataOutput dataOutput) {
        this.a.h0(dataOutput);
        this.b.c0(dataOutput);
    }

    @Override // j$.time.chrono.InterfaceC0015e
    public final j$.time.chrono.n a() {
        return ((g) d()).a();
    }

    @Override // j$.time.temporal.Temporal
    public final long b(Temporal temporal, TemporalUnit temporalUnit) {
        boolean z;
        g gVar;
        long j;
        long j2;
        long j3;
        i J2 = J(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            if (((ChronoUnit) temporalUnit).compareTo(ChronoUnit.DAYS) < 0) {
                z = true;
            } else {
                z = false;
            }
            k kVar = this.b;
            g gVar2 = this.a;
            if (z) {
                g gVar3 = J2.a;
                gVar2.getClass();
                long A = gVar3.A() - gVar2.A();
                k kVar2 = J2.b;
                if (A == 0) {
                    return kVar.b(kVar2, temporalUnit);
                }
                long Y = kVar2.Y() - kVar.Y();
                if (A > 0) {
                    j = A - 1;
                    j2 = Y + 86400000000000L;
                } else {
                    j = A + 1;
                    j2 = Y - 86400000000000L;
                }
                switch (h.a[((ChronoUnit) temporalUnit).ordinal()]) {
                    case 1:
                        j = j$.nio.file.attribute.a.f(j, 86400000000000L);
                        break;
                    case 2:
                        j = j$.nio.file.attribute.a.f(j, 86400000000L);
                        j3 = 1000;
                        j2 /= j3;
                        break;
                    case 3:
                        j = j$.nio.file.attribute.a.f(j, 86400000L);
                        j3 = 1000000;
                        j2 /= j3;
                        break;
                    case 4:
                        j = j$.nio.file.attribute.a.f(j, 86400);
                        j3 = 1000000000;
                        j2 /= j3;
                        break;
                    case 5:
                        j = j$.nio.file.attribute.a.f(j, 1440);
                        j3 = 60000000000L;
                        j2 /= j3;
                        break;
                    case 6:
                        j = j$.nio.file.attribute.a.f(j, 24);
                        j3 = 3600000000000L;
                        j2 /= j3;
                        break;
                    case 7:
                        j = j$.nio.file.attribute.a.f(j, 2);
                        j3 = 43200000000000L;
                        j2 /= j3;
                        break;
                }
                return j$.nio.file.attribute.a.e(j, j2);
            }
            g gVar4 = J2.a;
            gVar4.getClass();
            boolean z2 = gVar2 instanceof g;
            k kVar3 = J2.b;
            if (!z2 ? gVar4.A() > gVar2.A() : gVar4.I(gVar2) > 0) {
                if (kVar3.compareTo(kVar) < 0) {
                    gVar = gVar4.Z(-1L);
                    return gVar2.b(gVar, temporalUnit);
                }
            }
            boolean R = gVar4.R(gVar2);
            gVar = gVar4;
            if (R) {
                gVar = gVar4;
                if (kVar3.compareTo(kVar) > 0) {
                    gVar = gVar4.Z(1L);
                }
            }
            return gVar2.b(gVar, temporalUnit);
        }
        return temporalUnit.n(this, J2);
    }

    @Override // j$.time.chrono.InterfaceC0015e
    public final k c() {
        return this.b;
    }

    @Override // j$.time.chrono.InterfaceC0015e
    public final InterfaceC0012b d() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return this.a.equals(iVar.a) && this.b.equals(iVar.b);
    }

    @Override // j$.time.temporal.l
    public final boolean f(j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return oVar != null && oVar.p(this);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
        return aVar.z() || aVar.J();
    }

    @Override // j$.time.temporal.m
    public final Temporal h(Temporal temporal) {
        return temporal.e(((g) d()).A(), j$.time.temporal.a.EPOCH_DAY).e(c().Y(), j$.time.temporal.a.NANO_OF_DAY);
    }

    public final int hashCode() {
        return this.a.hashCode() ^ this.b.hashCode();
    }

    @Override // java.lang.Comparable
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public final int compareTo(InterfaceC0015e interfaceC0015e) {
        return interfaceC0015e instanceof i ? I((i) interfaceC0015e) : AbstractC0019i.c(this, interfaceC0015e);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal n(long j, ChronoUnit chronoUnit) {
        if (j == Long.MIN_VALUE) {
            return g(Long.MAX_VALUE, chronoUnit).g(1L, chronoUnit);
        }
        return g(-j, chronoUnit);
    }

    @Override // j$.time.temporal.l
    public final int p(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? ((j$.time.temporal.a) oVar).J() ? this.b.p(oVar) : this.a.p(oVar) : j$.time.temporal.k.a(this, oVar);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal r(g gVar) {
        return Y(gVar, this.b);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            if (((j$.time.temporal.a) oVar).J()) {
                k kVar = this.b;
                kVar.getClass();
                return j$.time.temporal.k.d(kVar, oVar);
            }
            return this.a.s(oVar);
        }
        return oVar.s(this);
    }

    public final String toString() {
        return this.a.toString() + "T" + this.b.toString();
    }

    @Override // j$.time.chrono.InterfaceC0015e
    public final InterfaceC0021k v(A a) {
        return D.K(this, a, null);
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? ((j$.time.temporal.a) oVar).J() ? this.b.z(oVar) : this.a.z(oVar) : oVar.h(this);
    }
}
