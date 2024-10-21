package j$.time;

import j$.time.chrono.AbstractC0019i;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;

/* loaded from: classes2.dex */
public final class r implements Temporal, j$.time.temporal.m, Comparable, Serializable {
    private static final long serialVersionUID = 7264499704384272492L;
    private final k a;
    private final A b;

    static {
        k kVar = k.e;
        A a = A.g;
        kVar.getClass();
        I(kVar, a);
        k kVar2 = k.f;
        A a2 = A.f;
        kVar2.getClass();
        I(kVar2, a2);
    }

    private r(k kVar, A a) {
        Objects.a(kVar, "time");
        this.a = kVar;
        Objects.a(a, "offset");
        this.b = a;
    }

    public static r I(k kVar, A a) {
        return new r(kVar, a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static r K(ObjectInput objectInput) {
        return new r(k.X(objectInput), A.V(objectInput));
    }

    private long L() {
        return this.a.Y() - (this.b.Q() * 1000000000);
    }

    private r M(k kVar, A a) {
        return (this.a == kVar && this.b.equals(a)) ? this : new r(kVar, a);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new t((byte) 9, this);
    }

    @Override // j$.time.temporal.l
    public final Object D(j$.time.temporal.q qVar) {
        if (qVar == j$.time.temporal.k.h() || qVar == j$.time.temporal.k.j()) {
            return this.b;
        }
        if (((qVar == j$.time.temporal.k.k()) || (qVar == j$.time.temporal.k.e())) || qVar == j$.time.temporal.k.f()) {
            return null;
        }
        return qVar == j$.time.temporal.k.g() ? this.a : qVar == j$.time.temporal.k.i() ? ChronoUnit.NANOS : qVar.a(this);
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: J, reason: merged with bridge method [inline-methods] */
    public final r g(long j, TemporalUnit temporalUnit) {
        return temporalUnit instanceof ChronoUnit ? M(this.a.g(j, temporalUnit), this.b) : (r) temporalUnit.p(this, j);
    }

    @Override // j$.time.temporal.Temporal
    public final long b(Temporal temporal, TemporalUnit temporalUnit) {
        r rVar;
        long j;
        if (temporal instanceof r) {
            rVar = (r) temporal;
        } else {
            try {
                rVar = new r(k.K(temporal), A.P(temporal));
            } catch (C0010c e) {
                throw new RuntimeException("Unable to obtain OffsetTime from TemporalAccessor: " + String.valueOf(temporal) + " of type " + temporal.getClass().getName(), e);
            }
        }
        if (temporalUnit instanceof ChronoUnit) {
            long L = rVar.L() - L();
            switch (q.a[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return L;
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
            return L / j;
        }
        return temporalUnit.n(this, rVar);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        int compare;
        r rVar = (r) obj;
        boolean equals = this.b.equals(rVar.b);
        k kVar = this.a;
        k kVar2 = rVar.a;
        if (equals || (compare = Long.compare(L(), rVar.L())) == 0) {
            return kVar.compareTo(kVar2);
        }
        return compare;
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal e(long j, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = j$.time.temporal.a.OFFSET_SECONDS;
            k kVar = this.a;
            if (oVar == aVar) {
                return M(kVar, A.T(((j$.time.temporal.a) oVar).D(j)));
            }
            return M(kVar.e(j, oVar), this.b);
        }
        return (r) oVar.r(this, j);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return this.a.equals(rVar.a) && this.b.equals(rVar.b);
    }

    @Override // j$.time.temporal.l
    public final boolean f(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? ((j$.time.temporal.a) oVar).J() || oVar == j$.time.temporal.a.OFFSET_SECONDS : oVar != null && oVar.p(this);
    }

    @Override // j$.time.temporal.m
    public final Temporal h(Temporal temporal) {
        return temporal.e(this.a.Y(), j$.time.temporal.a.NANO_OF_DAY).e(this.b.Q(), j$.time.temporal.a.OFFSET_SECONDS);
    }

    public final int hashCode() {
        return this.a.hashCode() ^ this.b.hashCode();
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
        return j$.time.temporal.k.a(this, oVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // j$.time.temporal.Temporal
    public final Temporal r(g gVar) {
        if (gVar instanceof k) {
            return M((k) gVar, this.b);
        }
        if (gVar instanceof A) {
            return M(this.a, (A) gVar);
        }
        boolean z = gVar instanceof r;
        Temporal temporal = gVar;
        if (!z) {
            temporal = AbstractC0019i.a(gVar, this);
        }
        return (r) temporal;
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            if (oVar == j$.time.temporal.a.OFFSET_SECONDS) {
                return ((j$.time.temporal.a) oVar).n();
            }
            k kVar = this.a;
            kVar.getClass();
            return j$.time.temporal.k.d(kVar, oVar);
        }
        return oVar.s(this);
    }

    public final String toString() {
        return this.a.toString() + this.b.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeExternal(ObjectOutput objectOutput) {
        this.a.c0(objectOutput);
        this.b.W(objectOutput);
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? oVar == j$.time.temporal.a.OFFSET_SECONDS ? this.b.Q() : this.a.z(oVar) : oVar.h(this);
    }
}
