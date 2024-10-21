package j$.time;

import j$.time.chrono.AbstractC0011a;
import j$.time.chrono.AbstractC0019i;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes2.dex */
public final class y implements Temporal, j$.time.temporal.m, Comparable, Serializable {
    public static final /* synthetic */ int c = 0;
    private static final long serialVersionUID = 4183400860270640070L;
    private final int a;
    private final int b;

    static {
        j$.time.format.o oVar = new j$.time.format.o();
        oVar.l(j$.time.temporal.a.YEAR, 4, 10, j$.time.format.v.EXCEEDS_PAD);
        oVar.e('-');
        oVar.k(j$.time.temporal.a.MONTH_OF_YEAR, 2);
        oVar.v();
    }

    private y(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    private long I() {
        return ((this.a * 12) + this.b) - 1;
    }

    public static y J(int i, int i2) {
        j$.time.temporal.a.YEAR.I(i);
        j$.time.temporal.a.MONTH_OF_YEAR.I(i2);
        return new y(i, i2);
    }

    private y N(int i, int i2) {
        return (this.a == i && this.b == i2) ? this : new y(i, i2);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new t((byte) 12, this);
    }

    @Override // j$.time.temporal.l
    public final Object D(j$.time.temporal.q qVar) {
        return qVar == j$.time.temporal.k.e() ? j$.time.chrono.u.d : qVar == j$.time.temporal.k.i() ? ChronoUnit.MONTHS : j$.time.temporal.k.c(this, qVar);
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: K, reason: merged with bridge method [inline-methods] */
    public final y g(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch (x.b[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return L(j);
                case 2:
                    return M(j);
                case 3:
                    return M(j$.nio.file.attribute.a.f(j, 10));
                case 4:
                    return M(j$.nio.file.attribute.a.f(j, 100));
                case 5:
                    return M(j$.nio.file.attribute.a.f(j, 1000));
                case 6:
                    j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                    return e(j$.nio.file.attribute.a.e(z(aVar), j), aVar);
                default:
                    throw new RuntimeException("Unsupported unit: ".concat(String.valueOf(temporalUnit)));
            }
        }
        return (y) temporalUnit.p(this, j);
    }

    public final y L(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = (this.a * 12) + (this.b - 1) + j;
        long j3 = 12;
        return N(j$.time.temporal.a.YEAR.D(j$.nio.file.attribute.a.c(j2, j3)), ((int) j$.nio.file.attribute.a.d(j2, j3)) + 1);
    }

    public final y M(long j) {
        return j == 0 ? this : N(j$.time.temporal.a.YEAR.D(this.a + j), this.b);
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: O, reason: merged with bridge method [inline-methods] */
    public final y e(long j, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
            aVar.I(j);
            int i = x.a[aVar.ordinal()];
            int i2 = this.a;
            if (i != 1) {
                if (i != 2) {
                    int i3 = this.b;
                    if (i != 3) {
                        if (i != 4) {
                            if (i == 5) {
                                if (z(j$.time.temporal.a.ERA) == j) {
                                    return this;
                                }
                                int i4 = 1 - i2;
                                j$.time.temporal.a.YEAR.I(i4);
                                return N(i4, i3);
                            }
                            throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
                        }
                        int i5 = (int) j;
                        j$.time.temporal.a.YEAR.I(i5);
                        return N(i5, i3);
                    }
                    if (i2 < 1) {
                        j = 1 - j;
                    }
                    int i6 = (int) j;
                    j$.time.temporal.a.YEAR.I(i6);
                    return N(i6, i3);
                }
                return L(j - I());
            }
            int i7 = (int) j;
            j$.time.temporal.a.MONTH_OF_YEAR.I(i7);
            return N(i2, i7);
        }
        return (y) oVar.r(this, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void P(DataOutput dataOutput) {
        dataOutput.writeInt(this.a);
        dataOutput.writeByte(this.b);
    }

    @Override // j$.time.temporal.Temporal
    public final long b(Temporal temporal, TemporalUnit temporalUnit) {
        y J2;
        if (temporal instanceof y) {
            J2 = (y) temporal;
        } else {
            Objects.a(temporal, "temporal");
            try {
                if (!j$.time.chrono.u.d.equals(AbstractC0019i.p(temporal))) {
                    temporal = g.K(temporal);
                }
                J2 = J(temporal.p(j$.time.temporal.a.YEAR), temporal.p(j$.time.temporal.a.MONTH_OF_YEAR));
            } catch (C0010c e) {
                throw new RuntimeException("Unable to obtain YearMonth from TemporalAccessor: " + String.valueOf(temporal) + " of type " + temporal.getClass().getName(), e);
            }
        }
        if (temporalUnit instanceof ChronoUnit) {
            long I = J2.I() - I();
            switch (x.b[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return I;
                case 2:
                    return I / 12;
                case 3:
                    return I / 120;
                case 4:
                    return I / 1200;
                case 5:
                    return I / 12000;
                case 6:
                    j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                    return J2.z(aVar) - z(aVar);
                default:
                    throw new RuntimeException("Unsupported unit: ".concat(String.valueOf(temporalUnit)));
            }
        }
        return temporalUnit.n(this, J2);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        y yVar = (y) obj;
        int i = this.a - yVar.a;
        if (i == 0) {
            return this.b - yVar.b;
        }
        return i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof y)) {
            return false;
        }
        y yVar = (y) obj;
        return this.a == yVar.a && this.b == yVar.b;
    }

    @Override // j$.time.temporal.l
    public final boolean f(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? oVar == j$.time.temporal.a.YEAR || oVar == j$.time.temporal.a.MONTH_OF_YEAR || oVar == j$.time.temporal.a.PROLEPTIC_MONTH || oVar == j$.time.temporal.a.YEAR_OF_ERA || oVar == j$.time.temporal.a.ERA : oVar != null && oVar.p(this);
    }

    @Override // j$.time.temporal.m
    public final Temporal h(Temporal temporal) {
        if (((AbstractC0011a) AbstractC0019i.p(temporal)).equals(j$.time.chrono.u.d)) {
            return temporal.e(I(), j$.time.temporal.a.PROLEPTIC_MONTH);
        }
        throw new RuntimeException("Adjustment only supported on ISO date-time");
    }

    public final int hashCode() {
        return (this.b << 27) ^ this.a;
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
        return s(oVar).a(z(oVar), oVar);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal r(g gVar) {
        return (y) AbstractC0019i.a(gVar, this);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        if (oVar == j$.time.temporal.a.YEAR_OF_ERA) {
            return j$.time.temporal.s.j(1L, this.a <= 0 ? 1000000000L : 999999999L);
        }
        return j$.time.temporal.k.d(this, oVar);
    }

    public final String toString() {
        int i;
        int i2 = this.a;
        int abs = Math.abs(i2);
        StringBuilder sb = new StringBuilder(9);
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
            sb.append(i2);
        }
        int i3 = this.b;
        sb.append(i3 < 10 ? "-0" : "-");
        sb.append(i3);
        return sb.toString();
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            int i = x.a[((j$.time.temporal.a) oVar).ordinal()];
            int i2 = 1;
            if (i != 1) {
                if (i != 2) {
                    int i3 = this.a;
                    if (i != 3) {
                        if (i != 4) {
                            if (i == 5) {
                                if (i3 < 1) {
                                    i2 = 0;
                                }
                                return i2;
                            }
                            throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
                        }
                        return i3;
                    }
                    if (i3 < 1) {
                        i3 = 1 - i3;
                    }
                    return i3;
                }
                return I();
            }
            return this.b;
        }
        return oVar.h(this);
    }
}
