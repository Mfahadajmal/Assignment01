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
public final class w implements Temporal, j$.time.temporal.m, Comparable, Serializable {
    public static final /* synthetic */ int b = 0;
    private static final long serialVersionUID = -23038383694477807L;
    private final int a;

    static {
        j$.time.format.o oVar = new j$.time.format.o();
        oVar.l(j$.time.temporal.a.YEAR, 4, 10, j$.time.format.v.EXCEEDS_PAD);
        oVar.v();
    }

    private w(int i) {
        this.a = i;
    }

    public static w I(int i) {
        j$.time.temporal.a.YEAR.I(i);
        return new w(i);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new t((byte) 11, this);
    }

    @Override // j$.time.temporal.l
    public final Object D(j$.time.temporal.q qVar) {
        return qVar == j$.time.temporal.k.e() ? j$.time.chrono.u.d : qVar == j$.time.temporal.k.i() ? ChronoUnit.YEARS : j$.time.temporal.k.c(this, qVar);
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: J, reason: merged with bridge method [inline-methods] */
    public final w g(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            int i = v.b[((ChronoUnit) temporalUnit).ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i == 5) {
                                j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                                return e(j$.nio.file.attribute.a.e(z(aVar), j), aVar);
                            }
                            throw new RuntimeException("Unsupported unit: ".concat(String.valueOf(temporalUnit)));
                        }
                        return K(j$.nio.file.attribute.a.f(j, 1000));
                    }
                    return K(j$.nio.file.attribute.a.f(j, 100));
                }
                return K(j$.nio.file.attribute.a.f(j, 10));
            }
            return K(j);
        }
        return (w) temporalUnit.p(this, j);
    }

    public final w K(long j) {
        return j == 0 ? this : I(j$.time.temporal.a.YEAR.D(this.a + j));
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: L, reason: merged with bridge method [inline-methods] */
    public final w e(long j, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
            aVar.I(j);
            int i = v.a[aVar.ordinal()];
            int i2 = this.a;
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        if (z(j$.time.temporal.a.ERA) == j) {
                            return this;
                        }
                        return I(1 - i2);
                    }
                    throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
                }
                return I((int) j);
            }
            if (i2 < 1) {
                j = 1 - j;
            }
            return I((int) j);
        }
        return (w) oVar.r(this, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void M(DataOutput dataOutput) {
        dataOutput.writeInt(this.a);
    }

    @Override // j$.time.temporal.Temporal
    public final long b(Temporal temporal, TemporalUnit temporalUnit) {
        w I;
        if (temporal instanceof w) {
            I = (w) temporal;
        } else {
            Objects.a(temporal, "temporal");
            try {
                if (!j$.time.chrono.u.d.equals(AbstractC0019i.p(temporal))) {
                    temporal = g.K(temporal);
                }
                I = I(temporal.p(j$.time.temporal.a.YEAR));
            } catch (C0010c e) {
                throw new RuntimeException("Unable to obtain Year from TemporalAccessor: " + String.valueOf(temporal) + " of type " + temporal.getClass().getName(), e);
            }
        }
        if (temporalUnit instanceof ChronoUnit) {
            long j = I.a - this.a;
            int i = v.b[((ChronoUnit) temporalUnit).ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i == 5) {
                                j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                                return I.z(aVar) - z(aVar);
                            }
                            throw new RuntimeException("Unsupported unit: ".concat(String.valueOf(temporalUnit)));
                        }
                        return j / 1000;
                    }
                    return j / 100;
                }
                return j / 10;
            }
            return j;
        }
        return temporalUnit.n(this, I);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.a - ((w) obj).a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof w) {
            return this.a == ((w) obj).a;
        }
        return false;
    }

    @Override // j$.time.temporal.l
    public final boolean f(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? oVar == j$.time.temporal.a.YEAR || oVar == j$.time.temporal.a.YEAR_OF_ERA || oVar == j$.time.temporal.a.ERA : oVar != null && oVar.p(this);
    }

    @Override // j$.time.temporal.m
    public final Temporal h(Temporal temporal) {
        if (((AbstractC0011a) AbstractC0019i.p(temporal)).equals(j$.time.chrono.u.d)) {
            return temporal.e(this.a, j$.time.temporal.a.YEAR);
        }
        throw new RuntimeException("Adjustment only supported on ISO date-time");
    }

    public final int hashCode() {
        return this.a;
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
        return (w) AbstractC0019i.a(gVar, this);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        if (oVar == j$.time.temporal.a.YEAR_OF_ERA) {
            return j$.time.temporal.s.j(1L, this.a <= 0 ? 1000000000L : 999999999L);
        }
        return j$.time.temporal.k.d(this, oVar);
    }

    public final String toString() {
        return Integer.toString(this.a);
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            int i = v.a[((j$.time.temporal.a) oVar).ordinal()];
            int i2 = this.a;
            int i3 = 1;
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        if (i2 < 1) {
                            i3 = 0;
                        }
                        return i3;
                    }
                    throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
                }
                return i2;
            }
            if (i2 < 1) {
                i2 = 1 - i2;
            }
            return i2;
        }
        return oVar.h(this);
    }
}
