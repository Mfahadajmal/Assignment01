package j$.time;

import j$.time.chrono.AbstractC0019i;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes2.dex */
public final class k implements Temporal, j$.time.temporal.m, Comparable, Serializable {
    public static final k e;
    public static final k f;
    public static final k g;
    private static final k[] h = new k[24];
    private static final long serialVersionUID = 6414437269572265201L;
    private final byte a;
    private final byte b;
    private final byte c;
    private final int d;

    static {
        int i = 0;
        while (true) {
            k[] kVarArr = h;
            if (i >= kVarArr.length) {
                k kVar = kVarArr[0];
                g = kVar;
                k kVar2 = kVarArr[12];
                e = kVar;
                f = new k(23, 59, 59, 999999999);
                return;
            }
            kVarArr[i] = new k(i, 0, 0, 0);
            i++;
        }
    }

    private k(int i, int i2, int i3, int i4) {
        this.a = (byte) i;
        this.b = (byte) i2;
        this.c = (byte) i3;
        this.d = i4;
    }

    private static k J(int i, int i2, int i3, int i4) {
        return ((i2 | i3) | i4) == 0 ? h[i] : new k(i, i2, i3, i4);
    }

    public static k K(Temporal temporal) {
        Objects.a(temporal, "temporal");
        k kVar = (k) temporal.D(j$.time.temporal.k.g());
        if (kVar != null) {
            return kVar;
        }
        throw new RuntimeException("Unable to obtain LocalTime from TemporalAccessor: " + String.valueOf(temporal) + " of type " + temporal.getClass().getName());
    }

    private int L(j$.time.temporal.o oVar) {
        int i = j.a[((j$.time.temporal.a) oVar).ordinal()];
        byte b = this.b;
        int i2 = this.d;
        byte b2 = this.a;
        switch (i) {
            case 1:
                return i2;
            case 2:
                throw new RuntimeException("Invalid field 'NanoOfDay' for get() method, use getLong() instead");
            case 3:
                return i2 / 1000;
            case 4:
                throw new RuntimeException("Invalid field 'MicroOfDay' for get() method, use getLong() instead");
            case 5:
                return i2 / 1000000;
            case 6:
                return (int) (Y() / 1000000);
            case 7:
                return this.c;
            case 8:
                return Z();
            case 9:
                return b;
            case 10:
                return (b2 * 60) + b;
            case 11:
                return b2 % 12;
            case 12:
                int i3 = b2 % 12;
                if (i3 % 12 == 0) {
                    return 12;
                }
                return i3;
            case 13:
                return b2;
            case 14:
                if (b2 == 0) {
                    return 24;
                }
                return b2;
            case 15:
                return b2 / 12;
            default:
                throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
        }
    }

    public static k P(int i) {
        j$.time.temporal.a.HOUR_OF_DAY.I(i);
        return h[i];
    }

    public static k Q(long j) {
        j$.time.temporal.a.NANO_OF_DAY.I(j);
        int i = (int) (j / 3600000000000L);
        long j2 = j - (i * 3600000000000L);
        int i2 = (int) (j2 / 60000000000L);
        long j3 = j2 - (i2 * 60000000000L);
        int i3 = (int) (j3 / 1000000000);
        return J(i, i2, i3, (int) (j3 - (i3 * 1000000000)));
    }

    public static k R(long j) {
        j$.time.temporal.a.SECOND_OF_DAY.I(j);
        int i = (int) (j / 3600);
        long j2 = j - (i * 3600);
        return J(i, (int) (j2 / 60), (int) (j2 - (r0 * 60)), 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [int] */
    public static k X(ObjectInput objectInput) {
        int i;
        int i2;
        int readByte = objectInput.readByte();
        byte b = 0;
        if (readByte < 0) {
            readByte = ~readByte;
            i = 0;
        } else {
            byte readByte2 = objectInput.readByte();
            if (readByte2 < 0) {
                ?? r7 = ~readByte2;
                i2 = 0;
                b = r7;
                i = 0;
            } else {
                byte readByte3 = objectInput.readByte();
                if (readByte3 < 0) {
                    i = ~readByte3;
                    b = readByte2;
                } else {
                    int readInt = objectInput.readInt();
                    i = readByte3;
                    i2 = readInt;
                    b = readByte2;
                }
            }
            j$.time.temporal.a.HOUR_OF_DAY.I(readByte);
            j$.time.temporal.a.MINUTE_OF_HOUR.I(b);
            j$.time.temporal.a.SECOND_OF_MINUTE.I(i);
            j$.time.temporal.a.NANO_OF_SECOND.I(i2);
            return J(readByte, b, i, i2);
        }
        i2 = 0;
        j$.time.temporal.a.HOUR_OF_DAY.I(readByte);
        j$.time.temporal.a.MINUTE_OF_HOUR.I(b);
        j$.time.temporal.a.SECOND_OF_MINUTE.I(i);
        j$.time.temporal.a.NANO_OF_SECOND.I(i2);
        return J(readByte, b, i, i2);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new t((byte) 4, this);
    }

    @Override // j$.time.temporal.l
    public final Object D(j$.time.temporal.q qVar) {
        if (qVar == j$.time.temporal.k.e() || qVar == j$.time.temporal.k.k() || qVar == j$.time.temporal.k.j() || qVar == j$.time.temporal.k.h()) {
            return null;
        }
        if (qVar == j$.time.temporal.k.g()) {
            return this;
        }
        if (qVar == j$.time.temporal.k.f()) {
            return null;
        }
        return qVar == j$.time.temporal.k.i() ? ChronoUnit.NANOS : qVar.a(this);
    }

    @Override // java.lang.Comparable
    /* renamed from: I, reason: merged with bridge method [inline-methods] */
    public final int compareTo(k kVar) {
        int compare = Integer.compare(this.a, kVar.a);
        if (compare != 0) {
            return compare;
        }
        int compare2 = Integer.compare(this.b, kVar.b);
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = Integer.compare(this.c, kVar.c);
        return compare3 == 0 ? Integer.compare(this.d, kVar.d) : compare3;
    }

    public final int M() {
        return this.a;
    }

    public final int N() {
        return this.d;
    }

    public final int O() {
        return this.c;
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: S, reason: merged with bridge method [inline-methods] */
    public final k g(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch (j.b[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return V(j);
                case 2:
                    return V((j % 86400000000L) * 1000);
                case 3:
                    return V((j % 86400000) * 1000000);
                case 4:
                    return W(j);
                case 5:
                    return U(j);
                case 6:
                    return T(j);
                case 7:
                    return T((j % 2) * 12);
                default:
                    throw new RuntimeException("Unsupported unit: ".concat(String.valueOf(temporalUnit)));
            }
        }
        return (k) temporalUnit.p(this, j);
    }

    public final k T(long j) {
        if (j == 0) {
            return this;
        }
        return J(((((int) (j % 24)) + this.a) + 24) % 24, this.b, this.c, this.d);
    }

    public final k U(long j) {
        if (j == 0) {
            return this;
        }
        int i = (this.a * 60) + this.b;
        int i2 = ((((int) (j % 1440)) + i) + 1440) % 1440;
        return i == i2 ? this : J(i2 / 60, i2 % 60, this.c, this.d);
    }

    public final k V(long j) {
        if (j == 0) {
            return this;
        }
        long Y = Y();
        long j2 = (((j % 86400000000000L) + Y) + 86400000000000L) % 86400000000000L;
        return Y == j2 ? this : J((int) (j2 / 3600000000000L), (int) ((j2 / 60000000000L) % 60), (int) ((j2 / 1000000000) % 60), (int) (j2 % 1000000000));
    }

    public final k W(long j) {
        if (j == 0) {
            return this;
        }
        int i = (this.b * 60) + (this.a * 3600) + this.c;
        int i2 = ((((int) (j % 86400)) + i) + 86400) % 86400;
        return i == i2 ? this : J(i2 / 3600, (i2 / 60) % 60, i2 % 60, this.d);
    }

    public final long Y() {
        return (this.c * 1000000000) + (this.b * 60000000000L) + (this.a * 3600000000000L) + this.d;
    }

    public final int Z() {
        return (this.b * 60) + (this.a * 3600) + this.c;
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: a0, reason: merged with bridge method [inline-methods] */
    public final k e(long j, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
            aVar.I(j);
            int i = j.a[aVar.ordinal()];
            byte b = this.b;
            byte b2 = this.c;
            int i2 = this.d;
            byte b3 = this.a;
            switch (i) {
                case 1:
                    return b0((int) j);
                case 2:
                    return Q(j);
                case 3:
                    return b0(((int) j) * 1000);
                case 4:
                    return Q(j * 1000);
                case 5:
                    return b0(((int) j) * 1000000);
                case 6:
                    return Q(j * 1000000);
                case 7:
                    int i3 = (int) j;
                    if (b2 == i3) {
                        return this;
                    }
                    j$.time.temporal.a.SECOND_OF_MINUTE.I(i3);
                    return J(b3, b, i3, i2);
                case 8:
                    return W(j - Z());
                case 9:
                    int i4 = (int) j;
                    if (b == i4) {
                        return this;
                    }
                    j$.time.temporal.a.MINUTE_OF_HOUR.I(i4);
                    return J(b3, i4, b2, i2);
                case 10:
                    return U(j - ((b3 * 60) + b));
                case 11:
                    return T(j - (b3 % 12));
                case 12:
                    if (j == 12) {
                        j = 0;
                    }
                    return T(j - (b3 % 12));
                case 13:
                    int i5 = (int) j;
                    if (b3 == i5) {
                        return this;
                    }
                    j$.time.temporal.a.HOUR_OF_DAY.I(i5);
                    return J(i5, b, b2, i2);
                case 14:
                    if (j == 24) {
                        j = 0;
                    }
                    int i6 = (int) j;
                    if (b3 == i6) {
                        return this;
                    }
                    j$.time.temporal.a.HOUR_OF_DAY.I(i6);
                    return J(i6, b, b2, i2);
                case 15:
                    return T((j - (b3 / 12)) * 12);
                default:
                    throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
            }
        }
        return (k) oVar.r(this, j);
    }

    @Override // j$.time.temporal.Temporal
    public final long b(Temporal temporal, TemporalUnit temporalUnit) {
        long j;
        k K = K(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            long Y = K.Y() - Y();
            switch (j.b[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return Y;
                case 2:
                    j = 1000;
                    break;
                case 3:
                    j = 1000000;
                    break;
                case 4:
                    j = 1000000000;
                    break;
                case 5:
                    j = 60000000000L;
                    break;
                case 6:
                    j = 3600000000000L;
                    break;
                case 7:
                    j = 43200000000000L;
                    break;
                default:
                    throw new RuntimeException("Unsupported unit: ".concat(String.valueOf(temporalUnit)));
            }
            return Y / j;
        }
        return temporalUnit.n(this, K);
    }

    public final k b0(int i) {
        if (this.d == i) {
            return this;
        }
        j$.time.temporal.a.NANO_OF_SECOND.I(i);
        return J(this.a, this.b, this.c, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c0(DataOutput dataOutput) {
        int i;
        byte b = this.c;
        byte b2 = this.a;
        byte b3 = this.b;
        int i2 = this.d;
        if (i2 != 0) {
            dataOutput.writeByte(b2);
            dataOutput.writeByte(b3);
            dataOutput.writeByte(b);
            dataOutput.writeInt(i2);
            return;
        }
        if (b != 0) {
            dataOutput.writeByte(b2);
            dataOutput.writeByte(b3);
            i = ~b;
        } else if (b3 == 0) {
            i = ~b2;
        } else {
            dataOutput.writeByte(b2);
            i = ~b3;
        }
        dataOutput.writeByte(i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return this.a == kVar.a && this.b == kVar.b && this.c == kVar.c && this.d == kVar.d;
    }

    @Override // j$.time.temporal.l
    public final boolean f(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? ((j$.time.temporal.a) oVar).J() : oVar != null && oVar.p(this);
    }

    @Override // j$.time.temporal.m
    public final Temporal h(Temporal temporal) {
        return temporal.e(Y(), j$.time.temporal.a.NANO_OF_DAY);
    }

    public final int hashCode() {
        long Y = Y();
        return (int) (Y ^ (Y >>> 32));
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

    @Override // j$.time.temporal.Temporal
    public final Temporal r(g gVar) {
        boolean z = gVar instanceof k;
        Temporal temporal = gVar;
        if (!z) {
            temporal = AbstractC0019i.a(gVar, this);
        }
        return (k) temporal;
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        return j$.time.temporal.k.d(this, oVar);
    }

    public final String toString() {
        int i;
        StringBuilder sb = new StringBuilder(18);
        byte b = this.a;
        sb.append(b < 10 ? "0" : "");
        sb.append((int) b);
        byte b2 = this.b;
        sb.append(b2 < 10 ? ":0" : ":");
        sb.append((int) b2);
        byte b3 = this.c;
        int i2 = this.d;
        if (b3 > 0 || i2 > 0) {
            sb.append(b3 < 10 ? ":0" : ":");
            sb.append((int) b3);
            if (i2 > 0) {
                sb.append('.');
                int i3 = 1000000;
                if (i2 % 1000000 == 0) {
                    i = (i2 / 1000000) + 1000;
                } else {
                    if (i2 % 1000 == 0) {
                        i2 /= 1000;
                    } else {
                        i3 = 1000000000;
                    }
                    i = i2 + i3;
                }
                sb.append(Integer.toString(i).substring(1));
            }
        }
        return sb.toString();
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? oVar == j$.time.temporal.a.NANO_OF_DAY ? Y() : oVar == j$.time.temporal.a.MICRO_OF_DAY ? Y() / 1000 : L(oVar) : oVar.h(this);
    }
}
