package j$.time;

import j$.time.chrono.AbstractC0019i;
import j$.time.chrono.InterfaceC0012b;
import j$.time.chrono.InterfaceC0015e;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes2.dex */
public final class g implements Temporal, j$.time.temporal.m, InterfaceC0012b, Serializable {
    public static final g d = V(-999999999, 1, 1);
    public static final g e = V(999999999, 12, 31);
    private static final long serialVersionUID = 2942565459149668126L;
    private final int a;
    private final short b;
    private final short c;

    static {
        V(1970, 1, 1);
    }

    private g(int i, int i2, int i3) {
        this.a = i;
        this.b = (short) i2;
        this.c = (short) i3;
    }

    private static g J(int i, int i2, int i3) {
        int i4 = 28;
        if (i3 > 28) {
            if (i2 != 2) {
                if (i2 != 4 && i2 != 6 && i2 != 9 && i2 != 11) {
                    i4 = 31;
                } else {
                    i4 = 30;
                }
            } else {
                j$.time.chrono.u.d.getClass();
                if (j$.time.chrono.u.p(i)) {
                    i4 = 29;
                }
            }
            if (i3 > i4) {
                if (i3 == 29) {
                    throw new RuntimeException("Invalid date 'February 29' as '" + i + "' is not a leap year");
                }
                throw new RuntimeException("Invalid date '" + l.L(i2).name() + " " + i3 + "'");
            }
        }
        return new g(i, i2, i3);
    }

    public static g K(j$.time.temporal.l lVar) {
        Objects.a(lVar, "temporal");
        g gVar = (g) lVar.D(j$.time.temporal.k.f());
        if (gVar != null) {
            return gVar;
        }
        throw new RuntimeException("Unable to obtain LocalDate from TemporalAccessor: " + String.valueOf(lVar) + " of type " + lVar.getClass().getName());
    }

    private int L(j$.time.temporal.o oVar) {
        int i;
        int i2 = f.a[((j$.time.temporal.a) oVar).ordinal()];
        short s = this.c;
        int i3 = this.a;
        switch (i2) {
            case 1:
                return s;
            case 2:
                return N();
            case 3:
                i = (s - 1) / 7;
                break;
            case 4:
                if (i3 < 1) {
                    return 1 - i3;
                }
                return i3;
            case 5:
                return M().getValue();
            case 6:
                i = (s - 1) % 7;
                break;
            case 7:
                return ((N() - 1) % 7) + 1;
            case 8:
                throw new RuntimeException("Invalid field 'EpochDay' for get() method, use getLong() instead");
            case 9:
                return ((N() - 1) / 7) + 1;
            case 10:
                return this.b;
            case 11:
                throw new RuntimeException("Invalid field 'ProlepticMonth' for get() method, use getLong() instead");
            case 12:
                return i3;
            case 13:
                if (i3 >= 1) {
                    return 1;
                }
                return 0;
            default:
                throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
        }
        return i + 1;
    }

    private long P() {
        return ((this.a * 12) + this.b) - 1;
    }

    private long U(g gVar) {
        return (((gVar.P() * 32) + gVar.c) - ((P() * 32) + this.c)) / 32;
    }

    public static g V(int i, int i2, int i3) {
        j$.time.temporal.a.YEAR.I(i);
        j$.time.temporal.a.MONTH_OF_YEAR.I(i2);
        j$.time.temporal.a.DAY_OF_MONTH.I(i3);
        return J(i, i2, i3);
    }

    public static g W(int i, l lVar, int i2) {
        j$.time.temporal.a.YEAR.I(i);
        j$.time.temporal.a.DAY_OF_MONTH.I(i2);
        return J(i, lVar.getValue(), i2);
    }

    public static g X(long j) {
        long j2;
        j$.time.temporal.a.EPOCH_DAY.I(j);
        long j3 = 719468 + j;
        if (j3 < 0) {
            long j4 = ((j + 719469) / 146097) - 1;
            j2 = j4 * 400;
            j3 += (-j4) * 146097;
        } else {
            j2 = 0;
        }
        long j5 = ((j3 * 400) + 591) / 146097;
        long j6 = j3 - ((j5 / 400) + (((j5 / 4) + (j5 * 365)) - (j5 / 100)));
        if (j6 < 0) {
            j5--;
            j6 = j3 - ((j5 / 400) + (((j5 / 4) + (365 * j5)) - (j5 / 100)));
        }
        int i = (int) j6;
        int i2 = ((i * 5) + 2) / 153;
        return new g(j$.time.temporal.a.YEAR.D(j5 + j2 + (i2 / 10)), ((i2 + 2) % 12) + 1, (i - (((i2 * 306) + 5) / 10)) + 1);
    }

    private static g c0(int i, int i2, int i3) {
        int i4;
        if (i2 != 2) {
            if (i2 == 4 || i2 == 6 || i2 == 9 || i2 == 11) {
                i4 = 30;
            }
            return new g(i, i2, i3);
        }
        j$.time.chrono.u.d.getClass();
        i4 = j$.time.chrono.u.p((long) i) ? 29 : 28;
        i3 = Math.min(i3, i4);
        return new g(i, i2, i3);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new t((byte) 3, this);
    }

    @Override // j$.time.chrono.InterfaceC0012b
    public final long A() {
        long j = this.a;
        long j2 = this.b;
        long j3 = 365 * j;
        long j4 = (((367 * j2) - 362) / 12) + (j >= 0 ? ((j + 399) / 400) + (((3 + j) / 4) - ((99 + j) / 100)) + j3 : j3 - ((j / (-400)) + ((j / (-4)) - (j / (-100))))) + (this.c - 1);
        if (j2 > 2) {
            j4 = !S() ? j4 - 2 : j4 - 1;
        }
        return j4 - 719528;
    }

    @Override // j$.time.chrono.InterfaceC0012b
    public final InterfaceC0015e B(k kVar) {
        return i.Q(this, kVar);
    }

    @Override // j$.time.temporal.l
    public final Object D(j$.time.temporal.q qVar) {
        return qVar == j$.time.temporal.k.f() ? this : AbstractC0019i.j(this, qVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int I(g gVar) {
        int i = this.a - gVar.a;
        if (i != 0) {
            return i;
        }
        int i2 = this.b - gVar.b;
        return i2 == 0 ? this.c - gVar.c : i2;
    }

    public final EnumC0023d M() {
        return EnumC0023d.I(((int) j$.nio.file.attribute.a.d(A() + 3, 7)) + 1);
    }

    public final int N() {
        return (l.L(this.b).I(S()) + this.c) - 1;
    }

    public final int O() {
        return this.b;
    }

    public final int Q() {
        return this.a;
    }

    public final boolean R(InterfaceC0012b interfaceC0012b) {
        if (interfaceC0012b instanceof g) {
            if (I((g) interfaceC0012b) >= 0) {
                return false;
            }
            return true;
        }
        if (A() >= interfaceC0012b.A()) {
            return false;
        }
        return true;
    }

    public final boolean S() {
        j$.time.chrono.u uVar = j$.time.chrono.u.d;
        long j = this.a;
        uVar.getClass();
        return j$.time.chrono.u.p(j);
    }

    public final int T() {
        short s = this.b;
        return s != 2 ? (s == 4 || s == 6 || s == 9 || s == 11) ? 30 : 31 : S() ? 29 : 28;
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: Y, reason: merged with bridge method [inline-methods] */
    public final g g(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch (f.b[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return Z(j);
                case 2:
                    return Z(j$.nio.file.attribute.a.f(j, 7));
                case 3:
                    return a0(j);
                case 4:
                    return b0(j);
                case 5:
                    return b0(j$.nio.file.attribute.a.f(j, 10));
                case 6:
                    return b0(j$.nio.file.attribute.a.f(j, 100));
                case 7:
                    return b0(j$.nio.file.attribute.a.f(j, 1000));
                case 8:
                    j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                    return e(j$.nio.file.attribute.a.e(z(aVar), j), aVar);
                default:
                    throw new RuntimeException("Unsupported unit: ".concat(String.valueOf(temporalUnit)));
            }
        }
        return (g) temporalUnit.p(this, j);
    }

    public final g Z(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = this.c + j;
        if (j2 > 0) {
            short s = this.b;
            int i = this.a;
            if (j2 <= 28) {
                return new g(i, s, (int) j2);
            }
            if (j2 <= 59) {
                long T = T();
                if (j2 <= T) {
                    return new g(i, s, (int) j2);
                }
                if (s < 12) {
                    return new g(i, s + 1, (int) (j2 - T));
                }
                int i2 = i + 1;
                j$.time.temporal.a.YEAR.I(i2);
                return new g(i2, 1, (int) (j2 - T));
            }
        }
        return X(j$.nio.file.attribute.a.e(A(), j));
    }

    @Override // j$.time.chrono.InterfaceC0012b
    public final j$.time.chrono.n a() {
        return j$.time.chrono.u.d;
    }

    public final g a0(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = (this.a * 12) + (this.b - 1) + j;
        long j3 = 12;
        return c0(j$.time.temporal.a.YEAR.D(j$.nio.file.attribute.a.c(j2, j3)), ((int) j$.nio.file.attribute.a.d(j2, j3)) + 1, this.c);
    }

    @Override // j$.time.temporal.Temporal
    public final long b(Temporal temporal, TemporalUnit temporalUnit) {
        long A;
        long j;
        g K = K(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            switch (f.b[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return K.A() - A();
                case 2:
                    A = K.A() - A();
                    j = 7;
                    break;
                case 3:
                    return U(K);
                case 4:
                    A = U(K);
                    j = 12;
                    break;
                case 5:
                    A = U(K);
                    j = 120;
                    break;
                case 6:
                    A = U(K);
                    j = 1200;
                    break;
                case 7:
                    A = U(K);
                    j = 12000;
                    break;
                case 8:
                    j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                    return K.z(aVar) - z(aVar);
                default:
                    throw new RuntimeException("Unsupported unit: ".concat(String.valueOf(temporalUnit)));
            }
            return A / j;
        }
        return temporalUnit.n(this, K);
    }

    public final g b0(long j) {
        return j == 0 ? this : c0(j$.time.temporal.a.YEAR.D(this.a + j), this.b, this.c);
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: d0, reason: merged with bridge method [inline-methods] */
    public final g e(long j, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
            aVar.I(j);
            int i = f.a[aVar.ordinal()];
            short s = this.c;
            short s2 = this.b;
            int i2 = this.a;
            switch (i) {
                case 1:
                    int i3 = (int) j;
                    if (s == i3) {
                        return this;
                    }
                    return V(i2, s2, i3);
                case 2:
                    return f0((int) j);
                case 3:
                    return Z(j$.nio.file.attribute.a.f(j - z(j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH), 7));
                case 4:
                    if (i2 < 1) {
                        j = 1 - j;
                    }
                    return g0((int) j);
                case 5:
                    return Z(j - M().getValue());
                case 6:
                    return Z(j - z(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH));
                case 7:
                    return Z(j - z(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR));
                case 8:
                    return X(j);
                case 9:
                    return Z(j$.nio.file.attribute.a.f(j - z(j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR), 7));
                case 10:
                    int i4 = (int) j;
                    if (s2 == i4) {
                        return this;
                    }
                    j$.time.temporal.a.MONTH_OF_YEAR.I(i4);
                    return c0(i2, i4, s);
                case 11:
                    return a0(j - P());
                case 12:
                    return g0((int) j);
                case 13:
                    if (z(j$.time.temporal.a.ERA) == j) {
                        return this;
                    }
                    return g0(1 - i2);
                default:
                    throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
            }
        }
        return (g) oVar.r(this, j);
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: e0, reason: merged with bridge method [inline-methods] */
    public final g r(j$.time.temporal.m mVar) {
        return mVar instanceof g ? (g) mVar : (g) mVar.h(this);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof g) && I((g) obj) == 0;
    }

    @Override // j$.time.temporal.l
    public final boolean f(j$.time.temporal.o oVar) {
        return AbstractC0019i.h(this, oVar);
    }

    public final g f0(int i) {
        if (N() == i) {
            return this;
        }
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        int i2 = this.a;
        long j = i2;
        aVar.I(j);
        j$.time.temporal.a.DAY_OF_YEAR.I(i);
        j$.time.chrono.u.d.getClass();
        boolean p = j$.time.chrono.u.p(j);
        if (i == 366 && !p) {
            throw new RuntimeException("Invalid date 'DayOfYear 366' as '" + i2 + "' is not a leap year");
        }
        l L = l.L(((i - 1) / 31) + 1);
        if (i > (L.J(p) + L.I(p)) - 1) {
            L = L.M();
        }
        return new g(i2, L.getValue(), (i - L.I(p)) + 1);
    }

    public final g g0(int i) {
        if (this.a == i) {
            return this;
        }
        j$.time.temporal.a.YEAR.I(i);
        return c0(i, this.b, this.c);
    }

    @Override // j$.time.temporal.m
    public final Temporal h(Temporal temporal) {
        return AbstractC0019i.a(this, temporal);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void h0(DataOutput dataOutput) {
        dataOutput.writeInt(this.a);
        dataOutput.writeByte(this.b);
        dataOutput.writeByte(this.c);
    }

    @Override // j$.time.chrono.InterfaceC0012b
    public final int hashCode() {
        int i = this.a;
        return (((i << 11) + (this.b << 6)) + this.c) ^ (i & (-2048));
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
        return oVar instanceof j$.time.temporal.a ? L(oVar) : j$.time.temporal.k.a(this, oVar);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        int i;
        long j;
        long j2;
        if (oVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
            if (aVar.z()) {
                int i2 = f.a[aVar.ordinal()];
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            if (i2 != 4) {
                                return ((j$.time.temporal.a) oVar).n();
                            }
                            if (this.a <= 0) {
                                j2 = 1000000000;
                            } else {
                                j2 = 999999999;
                            }
                            return j$.time.temporal.s.j(1L, j2);
                        }
                        if (l.L(this.b) == l.FEBRUARY && !S()) {
                            j = 4;
                        } else {
                            j = 5;
                        }
                        return j$.time.temporal.s.j(1L, j);
                    }
                    if (S()) {
                        i = 366;
                    } else {
                        i = 365;
                    }
                    return j$.time.temporal.s.j(1L, i);
                }
                return j$.time.temporal.s.j(1L, T());
            }
            throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
        }
        return oVar.s(this);
    }

    @Override // j$.time.chrono.InterfaceC0012b
    public final String toString() {
        int i;
        int i2 = this.a;
        int abs = Math.abs(i2);
        StringBuilder sb = new StringBuilder(10);
        if (abs < 1000) {
            if (i2 < 0) {
                sb.append(i2 - 10000);
                i = 1;
            } else {
                sb.append(i2 + 10000);
                i = 0;
            }
            sb.deleteCharAt(i);
        } else {
            if (i2 > 9999) {
                sb.append('+');
            }
            sb.append(i2);
        }
        short s = this.b;
        sb.append(s < 10 ? "-0" : "-");
        sb.append((int) s);
        short s2 = this.c;
        sb.append(s2 < 10 ? "-0" : "-");
        sb.append((int) s2);
        return sb.toString();
    }

    @Override // j$.time.chrono.InterfaceC0012b
    public final InterfaceC0012b u(long j, TemporalUnit temporalUnit) {
        return g(-1L, (ChronoUnit) temporalUnit);
    }

    @Override // java.lang.Comparable
    /* renamed from: y, reason: merged with bridge method [inline-methods] */
    public final int compareTo(InterfaceC0012b interfaceC0012b) {
        return interfaceC0012b instanceof g ? I((g) interfaceC0012b) : AbstractC0019i.b(this, interfaceC0012b);
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? oVar == j$.time.temporal.a.EPOCH_DAY ? A() : oVar == j$.time.temporal.a.PROLEPTIC_MONTH ? P() : L(oVar) : oVar.h(this);
    }
}
