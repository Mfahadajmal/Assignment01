package j$.time;

import j$.time.chrono.AbstractC0011a;
import j$.time.chrono.AbstractC0019i;
import j$.time.temporal.Temporal;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes2.dex */
public final class n implements j$.time.temporal.l, j$.time.temporal.m, Comparable, Serializable {
    private static final long serialVersionUID = -939150713474957432L;
    private final int a;
    private final int b;

    static {
        j$.time.format.o oVar = new j$.time.format.o();
        oVar.f("--");
        oVar.k(j$.time.temporal.a.MONTH_OF_YEAR, 2);
        oVar.e('-');
        oVar.k(j$.time.temporal.a.DAY_OF_MONTH, 2);
        oVar.v();
    }

    private n(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static n I(ObjectInput objectInput) {
        byte readByte = objectInput.readByte();
        byte readByte2 = objectInput.readByte();
        l L = l.L(readByte);
        Objects.a(L, "month");
        j$.time.temporal.a.DAY_OF_MONTH.I(readByte2);
        if (readByte2 <= L.K()) {
            return new n(L.getValue(), readByte2);
        }
        throw new RuntimeException("Illegal value for DayOfMonth field, value " + ((int) readByte2) + " is not valid for month " + L.name());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new t((byte) 13, this);
    }

    @Override // j$.time.temporal.l
    public final Object D(j$.time.temporal.q qVar) {
        return qVar == j$.time.temporal.k.e() ? j$.time.chrono.u.d : j$.time.temporal.k.c(this, qVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void J(DataOutput dataOutput) {
        dataOutput.writeByte(this.a);
        dataOutput.writeByte(this.b);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        n nVar = (n) obj;
        int i = this.a - nVar.a;
        if (i == 0) {
            return this.b - nVar.b;
        }
        return i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return this.a == nVar.a && this.b == nVar.b;
    }

    @Override // j$.time.temporal.l
    public final boolean f(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? oVar == j$.time.temporal.a.MONTH_OF_YEAR || oVar == j$.time.temporal.a.DAY_OF_MONTH : oVar != null && oVar.p(this);
    }

    @Override // j$.time.temporal.m
    public final Temporal h(Temporal temporal) {
        if (((AbstractC0011a) AbstractC0019i.p(temporal)).equals(j$.time.chrono.u.d)) {
            Temporal e = temporal.e(this.a, j$.time.temporal.a.MONTH_OF_YEAR);
            j$.time.temporal.a aVar = j$.time.temporal.a.DAY_OF_MONTH;
            return e.e(Math.min(e.s(aVar).d(), this.b), aVar);
        }
        throw new RuntimeException("Adjustment only supported on ISO date-time");
    }

    public final int hashCode() {
        return (this.a << 6) + this.b;
    }

    @Override // j$.time.temporal.l
    public final int p(j$.time.temporal.o oVar) {
        return s(oVar).a(z(oVar), oVar);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        int i;
        if (oVar == j$.time.temporal.a.MONTH_OF_YEAR) {
            return oVar.n();
        }
        if (oVar == j$.time.temporal.a.DAY_OF_MONTH) {
            int ordinal = l.L(this.a).ordinal();
            if (ordinal != 1) {
                if (ordinal != 3 && ordinal != 5 && ordinal != 8 && ordinal != 10) {
                    i = 31;
                } else {
                    i = 30;
                }
            } else {
                i = 28;
            }
            return j$.time.temporal.s.k(i, l.L(r5).K());
        }
        return j$.time.temporal.k.d(this, oVar);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(10);
        sb.append("--");
        int i = this.a;
        sb.append(i < 10 ? "0" : "");
        sb.append(i);
        int i2 = this.b;
        sb.append(i2 < 10 ? "-0" : "-");
        sb.append(i2);
        return sb.toString();
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        int i;
        if (oVar instanceof j$.time.temporal.a) {
            int i2 = m.a[((j$.time.temporal.a) oVar).ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    i = this.a;
                } else {
                    throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
                }
            } else {
                i = this.b;
            }
            return i;
        }
        return oVar.h(this);
    }
}
