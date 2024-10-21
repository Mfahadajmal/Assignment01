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
public final class p implements Temporal, j$.time.temporal.m, Comparable, Serializable {
    private static final long serialVersionUID = 2287754244819255394L;
    private final i a;
    private final A b;

    static {
        i iVar = i.c;
        A a = A.g;
        iVar.getClass();
        I(iVar, a);
        i iVar2 = i.d;
        A a2 = A.f;
        iVar2.getClass();
        I(iVar2, a2);
    }

    private p(i iVar, A a) {
        Objects.a(iVar, "dateTime");
        this.a = iVar;
        Objects.a(a, "offset");
        this.b = a;
    }

    public static p I(i iVar, A a) {
        return new p(iVar, a);
    }

    public static p J(Instant instant, z zVar) {
        Objects.a(instant, "instant");
        Objects.a(zVar, "zone");
        A d = zVar.J().d(instant);
        return new p(i.R(instant.K(), instant.L(), d), d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static p L(ObjectInput objectInput) {
        i iVar = i.c;
        g gVar = g.d;
        return new p(i.Q(g.V(objectInput.readInt(), objectInput.readByte(), objectInput.readByte()), k.X(objectInput)), A.V(objectInput));
    }

    private p N(i iVar, A a) {
        return (this.a == iVar && this.b.equals(a)) ? this : new p(iVar, a);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new t((byte) 10, this);
    }

    @Override // j$.time.temporal.l
    public final Object D(j$.time.temporal.q qVar) {
        if (qVar != j$.time.temporal.k.h() && qVar != j$.time.temporal.k.j()) {
            if (qVar == j$.time.temporal.k.k()) {
                return null;
            }
            j$.time.temporal.q f = j$.time.temporal.k.f();
            i iVar = this.a;
            if (qVar == f) {
                return iVar.V();
            }
            if (qVar == j$.time.temporal.k.g()) {
                return iVar.c();
            }
            if (qVar == j$.time.temporal.k.e()) {
                return j$.time.chrono.u.d;
            }
            if (qVar == j$.time.temporal.k.i()) {
                return ChronoUnit.NANOS;
            }
            return qVar.a(this);
        }
        return this.b;
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: K, reason: merged with bridge method [inline-methods] */
    public final p g(long j, TemporalUnit temporalUnit) {
        return temporalUnit instanceof ChronoUnit ? N(this.a.g(j, temporalUnit), this.b) : (p) temporalUnit.p(this, j);
    }

    public final i M() {
        return this.a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v16, types: [j$.time.p] */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    @Override // j$.time.temporal.Temporal
    public final long b(Temporal temporal, TemporalUnit temporalUnit) {
        if (temporal instanceof p) {
            temporal = (p) temporal;
        } else {
            try {
                A P = A.P(temporal);
                g gVar = (g) temporal.D(j$.time.temporal.k.f());
                k kVar = (k) temporal.D(j$.time.temporal.k.g());
                if (gVar != null && kVar != null) {
                    temporal = new p(i.Q(gVar, kVar), P);
                } else {
                    temporal = J(Instant.J(temporal), P);
                }
            } catch (C0010c e) {
                throw new RuntimeException("Unable to obtain OffsetDateTime from TemporalAccessor: " + String.valueOf(temporal) + " of type " + temporal.getClass().getName(), e);
            }
        }
        if (temporalUnit instanceof ChronoUnit) {
            A a = temporal.b;
            A a2 = this.b;
            p pVar = temporal;
            if (!a2.equals(a)) {
                pVar = new p(temporal.a.T(a2.Q() - a.Q()), a2);
            }
            return this.a.b(pVar.a, temporalUnit);
        }
        return temporalUnit.n(this, temporal);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        int compare;
        p pVar = (p) obj;
        A a = pVar.b;
        A a2 = this.b;
        boolean equals = a2.equals(a);
        i iVar = pVar.a;
        i iVar2 = this.a;
        if (equals) {
            compare = iVar2.compareTo(iVar);
        } else {
            iVar2.getClass();
            long n = AbstractC0019i.n(iVar2, a2);
            iVar.getClass();
            compare = Long.compare(n, AbstractC0019i.n(iVar, pVar.b));
            if (compare == 0) {
                compare = iVar2.c().N() - iVar.c().N();
            }
        }
        if (compare == 0) {
            return iVar2.compareTo(iVar);
        }
        return compare;
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal e(long j, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
            int i = o.a[aVar.ordinal()];
            A a = this.b;
            i iVar = this.a;
            if (i != 1) {
                if (i != 2) {
                    return N(iVar.e(j, oVar), a);
                }
                return N(iVar, A.T(aVar.D(j)));
            }
            return J(Instant.M(j, iVar.K()), a);
        }
        return (p) oVar.r(this, j);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return this.a.equals(pVar.a) && this.b.equals(pVar.b);
    }

    @Override // j$.time.temporal.l
    public final boolean f(j$.time.temporal.o oVar) {
        return (oVar instanceof j$.time.temporal.a) || (oVar != null && oVar.p(this));
    }

    @Override // j$.time.temporal.m
    public final Temporal h(Temporal temporal) {
        j$.time.temporal.a aVar = j$.time.temporal.a.EPOCH_DAY;
        i iVar = this.a;
        return temporal.e(iVar.V().A(), aVar).e(iVar.c().Y(), j$.time.temporal.a.NANO_OF_DAY).e(this.b.Q(), j$.time.temporal.a.OFFSET_SECONDS);
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
        if (oVar instanceof j$.time.temporal.a) {
            int i = o.a[((j$.time.temporal.a) oVar).ordinal()];
            if (i != 1) {
                if (i != 2) {
                    return this.a.p(oVar);
                }
                return this.b.Q();
            }
            throw new RuntimeException("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
        }
        return j$.time.temporal.k.a(this, oVar);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal r(g gVar) {
        return N(this.a.X(gVar), this.b);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? (oVar == j$.time.temporal.a.INSTANT_SECONDS || oVar == j$.time.temporal.a.OFFSET_SECONDS) ? ((j$.time.temporal.a) oVar).n() : this.a.s(oVar) : oVar.s(this);
    }

    public final String toString() {
        return this.a.toString() + this.b.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeExternal(ObjectOutput objectOutput) {
        this.a.Z(objectOutput);
        this.b.W(objectOutput);
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            int i = o.a[((j$.time.temporal.a) oVar).ordinal()];
            A a = this.b;
            i iVar = this.a;
            if (i != 1) {
                if (i != 2) {
                    return iVar.z(oVar);
                }
                return a.Q();
            }
            iVar.getClass();
            return AbstractC0019i.n(iVar, a);
        }
        return oVar.h(this);
    }
}
