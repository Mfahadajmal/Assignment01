package j$.time;

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
public final class Instant implements Temporal, j$.time.temporal.m, Comparable<Instant>, Serializable {
    public static final Instant EPOCH = new Instant(0, 0);
    private static final long serialVersionUID = -665713676816604388L;
    private final long a;
    private final int b;

    static {
        M(-31557014167219200L, 0L);
        M(31556889864403199L, 999999999L);
    }

    private Instant(long j, int i) {
        this.a = j;
        this.b = i;
    }

    private static Instant I(long j, int i) {
        if ((i | j) == 0) {
            return EPOCH;
        }
        if (j >= -31557014167219200L && j <= 31556889864403199L) {
            return new Instant(j, i);
        }
        throw new RuntimeException("Instant exceeds minimum or maximum instant");
    }

    public static Instant J(Temporal temporal) {
        if (temporal instanceof Instant) {
            return (Instant) temporal;
        }
        Objects.a(temporal, "temporal");
        try {
            return M(temporal.z(j$.time.temporal.a.INSTANT_SECONDS), temporal.p(j$.time.temporal.a.NANO_OF_SECOND));
        } catch (C0010c e) {
            throw new RuntimeException("Unable to obtain Instant from TemporalAccessor: " + String.valueOf(temporal) + " of type " + temporal.getClass().getName(), e);
        }
    }

    public static Instant M(long j, long j2) {
        return I(j$.nio.file.attribute.a.e(j, j$.nio.file.attribute.a.c(j2, 1000000000L)), (int) j$.nio.file.attribute.a.d(j2, 1000000000L));
    }

    private Instant N(long j, long j2) {
        if ((j | j2) == 0) {
            return this;
        }
        return M(j$.nio.file.attribute.a.e(j$.nio.file.attribute.a.e(this.a, j), j2 / 1000000000), this.b + (j2 % 1000000000));
    }

    private long P(Instant instant) {
        long g = j$.nio.file.attribute.a.g(instant.a, this.a);
        long j = instant.b - this.b;
        return (g <= 0 || j >= 0) ? (g >= 0 || j <= 0) ? g : g + 1 : g - 1;
    }

    public static Instant now() {
        C0008a.b.getClass();
        return ofEpochMilli(System.currentTimeMillis());
    }

    public static Instant ofEpochMilli(long j) {
        long j2 = 1000;
        return I(j$.nio.file.attribute.a.c(j, j2), ((int) j$.nio.file.attribute.a.d(j, j2)) * 1000000);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new t((byte) 2, this);
    }

    @Override // j$.time.temporal.l
    public final Object D(j$.time.temporal.q qVar) {
        if (qVar == j$.time.temporal.k.i()) {
            return ChronoUnit.NANOS;
        }
        if (qVar == j$.time.temporal.k.e() || qVar == j$.time.temporal.k.k() || qVar == j$.time.temporal.k.j() || qVar == j$.time.temporal.k.h() || qVar == j$.time.temporal.k.f() || qVar == j$.time.temporal.k.g()) {
            return null;
        }
        return qVar.a(this);
    }

    public final long K() {
        return this.a;
    }

    public final int L() {
        return this.b;
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: O, reason: merged with bridge method [inline-methods] */
    public final Instant g(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch (e.b[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return N(0L, j);
                case 2:
                    return N(j / 1000000, (j % 1000000) * 1000);
                case 3:
                    return N(j / 1000, (j % 1000) * 1000000);
                case 4:
                    return N(j, 0L);
                case 5:
                    return N(j$.nio.file.attribute.a.f(j, 60), 0L);
                case 6:
                    return N(j$.nio.file.attribute.a.f(j, 3600), 0L);
                case 7:
                    return N(j$.nio.file.attribute.a.f(j, 43200), 0L);
                case 8:
                    return N(j$.nio.file.attribute.a.f(j, 86400), 0L);
                default:
                    throw new RuntimeException("Unsupported unit: ".concat(String.valueOf(temporalUnit)));
            }
        }
        return (Instant) temporalUnit.p(this, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void Q(DataOutput dataOutput) {
        dataOutput.writeLong(this.a);
        dataOutput.writeInt(this.b);
    }

    @Override // j$.time.temporal.Temporal
    public final long b(Temporal temporal, TemporalUnit temporalUnit) {
        Instant J2 = J(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            int i = e.b[((ChronoUnit) temporalUnit).ordinal()];
            int i2 = this.b;
            long j = this.a;
            switch (i) {
                case 1:
                    return j$.nio.file.attribute.a.e(j$.nio.file.attribute.a.f(j$.nio.file.attribute.a.g(J2.a, j), 1000000000L), J2.b - i2);
                case 2:
                    return j$.nio.file.attribute.a.e(j$.nio.file.attribute.a.f(j$.nio.file.attribute.a.g(J2.a, j), 1000000000L), J2.b - i2) / 1000;
                case 3:
                    return j$.nio.file.attribute.a.g(J2.toEpochMilli(), toEpochMilli());
                case 4:
                    return P(J2);
                case 5:
                    return P(J2) / 60;
                case 6:
                    return P(J2) / 3600;
                case 7:
                    return P(J2) / 43200;
                case 8:
                    return P(J2) / 86400;
                default:
                    throw new RuntimeException("Unsupported unit: ".concat(String.valueOf(temporalUnit)));
            }
        }
        return temporalUnit.n(this, J2);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Instant instant) {
        Instant instant2 = instant;
        int compare = Long.compare(this.a, instant2.a);
        if (compare == 0) {
            return this.b - instant2.b;
        }
        return compare;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0043, code lost:
    
        if (r7 != r2) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004d, code lost:
    
        if (r7 != r2) goto L20;
     */
    @Override // j$.time.temporal.Temporal
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final j$.time.temporal.Temporal e(long r6, j$.time.temporal.o r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof j$.time.temporal.a
            if (r0 == 0) goto L57
            r0 = r8
            j$.time.temporal.a r0 = (j$.time.temporal.a) r0
            r0.I(r6)
            int[] r1 = j$.time.e.a
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 1
            int r2 = r5.b
            long r3 = r5.a
            if (r0 == r1) goto L50
            r1 = 2
            if (r0 == r1) goto L4a
            r1 = 3
            if (r0 == r1) goto L3d
            r1 = 4
            if (r0 != r1) goto L2d
            int r8 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r8 == 0) goto L2b
            j$.time.Instant r6 = I(r6, r2)
            goto L5d
        L2b:
            r6 = r5
            goto L5d
        L2d:
            j$.time.temporal.r r6 = new j$.time.temporal.r
            java.lang.String r7 = java.lang.String.valueOf(r8)
            java.lang.String r8 = "Unsupported field: "
            java.lang.String r7 = r8.concat(r7)
            r6.<init>(r7)
            throw r6
        L3d:
            int r7 = (int) r6
            r6 = 1000000(0xf4240, float:1.401298E-39)
            int r7 = r7 * r6
            if (r7 == r2) goto L2b
        L45:
            j$.time.Instant r6 = I(r3, r7)
            goto L5d
        L4a:
            int r7 = (int) r6
            int r7 = r7 * 1000
            if (r7 == r2) goto L2b
            goto L45
        L50:
            long r0 = (long) r2
            int r8 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r8 == 0) goto L2b
            int r7 = (int) r6
            goto L45
        L57:
            j$.time.temporal.Temporal r6 = r8.r(r5, r6)
            j$.time.Instant r6 = (j$.time.Instant) r6
        L5d:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.Instant.e(long, j$.time.temporal.o):j$.time.temporal.Temporal");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Instant)) {
            return false;
        }
        Instant instant = (Instant) obj;
        return this.a == instant.a && this.b == instant.b;
    }

    @Override // j$.time.temporal.l
    public final boolean f(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? oVar == j$.time.temporal.a.INSTANT_SECONDS || oVar == j$.time.temporal.a.NANO_OF_SECOND || oVar == j$.time.temporal.a.MICRO_OF_SECOND || oVar == j$.time.temporal.a.MILLI_OF_SECOND : oVar != null && oVar.p(this);
    }

    @Override // j$.time.temporal.m
    public final Temporal h(Temporal temporal) {
        return temporal.e(this.a, j$.time.temporal.a.INSTANT_SECONDS).e(this.b, j$.time.temporal.a.NANO_OF_SECOND);
    }

    public final int hashCode() {
        long j = this.a;
        return (this.b * 51) + ((int) (j ^ (j >>> 32)));
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
        if (oVar instanceof j$.time.temporal.a) {
            int i = e.a[((j$.time.temporal.a) oVar).ordinal()];
            int i2 = this.b;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i == 4) {
                            j$.time.temporal.a.INSTANT_SECONDS.D(this.a);
                        }
                        throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
                    }
                    return i2 / 1000000;
                }
                return i2 / 1000;
            }
            return i2;
        }
        return j$.time.temporal.k.d(this, oVar).a(oVar.h(this), oVar);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal r(g gVar) {
        return (Instant) AbstractC0019i.a(gVar, this);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        return j$.time.temporal.k.d(this, oVar);
    }

    public long toEpochMilli() {
        long f;
        int i;
        int i2 = this.b;
        long j = this.a;
        if (j < 0 && i2 > 0) {
            f = j$.nio.file.attribute.a.f(j + 1, 1000);
            i = (i2 / 1000000) - 1000;
        } else {
            f = j$.nio.file.attribute.a.f(j, 1000);
            i = i2 / 1000000;
        }
        return j$.nio.file.attribute.a.e(f, i);
    }

    public final String toString() {
        return j$.time.format.a.e.a(this);
    }

    public Instant truncatedTo(TemporalUnit temporalUnit) {
        if (temporalUnit == ChronoUnit.NANOS) {
            return this;
        }
        Duration h = temporalUnit.h();
        if (h.getSeconds() <= 86400) {
            long z = h.z();
            if (86400000000000L % z == 0) {
                long j = ((this.a % 86400) * 1000000000) + this.b;
                return N(0L, (j$.nio.file.attribute.a.c(j, z) * z) - j);
            }
            throw new RuntimeException("Unit must divide into a standard day without remainder");
        }
        throw new RuntimeException("Unit is too large to be used for truncation");
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        int i;
        if (oVar instanceof j$.time.temporal.a) {
            int i2 = e.a[((j$.time.temporal.a) oVar).ordinal()];
            int i3 = this.b;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 == 4) {
                            return this.a;
                        }
                        throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
                    }
                    i = i3 / 1000000;
                } else {
                    i = i3 / 1000;
                }
                return i;
            }
            return i3;
        }
        return oVar.h(this);
    }
}
