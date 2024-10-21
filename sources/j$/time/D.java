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
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public final class D implements Temporal, InterfaceC0021k, Serializable {
    private static final long serialVersionUID = -6260982410461394882L;
    private final i a;
    private final A b;
    private final z c;

    private D(i iVar, z zVar, A a) {
        this.a = iVar;
        this.b = a;
        this.c = zVar;
    }

    public static D I(Temporal temporal) {
        if (temporal instanceof D) {
            return (D) temporal;
        }
        try {
            z I = z.I(temporal);
            j$.time.temporal.a aVar = j$.time.temporal.a.INSTANT_SECONDS;
            if (temporal.f(aVar)) {
                return h(temporal.z(aVar), temporal.p(j$.time.temporal.a.NANO_OF_SECOND), I);
            }
            return K(i.Q(g.K(temporal), k.K(temporal)), I, null);
        } catch (C0010c e) {
            throw new RuntimeException("Unable to obtain ZonedDateTime from TemporalAccessor: " + String.valueOf(temporal) + " of type " + temporal.getClass().getName(), e);
        }
    }

    public static D J(Instant instant, z zVar) {
        Objects.a(instant, "instant");
        return h(instant.K(), instant.L(), zVar);
    }

    public static D K(i iVar, z zVar, A a) {
        Objects.a(iVar, "localDateTime");
        Objects.a(zVar, "zone");
        if (zVar instanceof A) {
            return new D(iVar, zVar, (A) zVar);
        }
        j$.time.zone.e J2 = zVar.J();
        List g = J2.g(iVar);
        if (g.size() == 1) {
            a = (A) g.get(0);
        } else if (g.size() == 0) {
            j$.time.zone.b f = J2.f(iVar);
            iVar = iVar.T(f.p().getSeconds());
            a = f.r();
        } else if (a == null || !g.contains(a)) {
            a = (A) g.get(0);
            Objects.a(a, "offset");
        }
        return new D(iVar, zVar, a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static D M(ObjectInput objectInput) {
        i iVar = i.c;
        g gVar = g.d;
        i Q = i.Q(g.V(objectInput.readInt(), objectInput.readByte(), objectInput.readByte()), k.X(objectInput));
        A V = A.V(objectInput);
        z zVar = (z) t.a(objectInput);
        Objects.a(zVar, "zone");
        if ((zVar instanceof A) && !V.equals(zVar)) {
            throw new IllegalArgumentException("ZoneId must match ZoneOffset");
        }
        return new D(Q, zVar, V);
    }

    private static D h(long j, int i, z zVar) {
        A d = zVar.J().d(Instant.M(j, i));
        return new D(i.R(j, i, d), zVar, d);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new t((byte) 6, this);
    }

    @Override // j$.time.temporal.l
    public final Object D(j$.time.temporal.q qVar) {
        if (qVar == j$.time.temporal.k.f()) {
            return this.a.V();
        }
        return AbstractC0019i.l(this, qVar);
    }

    @Override // j$.time.chrono.InterfaceC0021k
    public final /* synthetic */ long F() {
        return AbstractC0019i.o(this);
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: L, reason: merged with bridge method [inline-methods] */
    public final D g(long j, TemporalUnit temporalUnit) {
        boolean z;
        if (temporalUnit instanceof ChronoUnit) {
            ChronoUnit chronoUnit = (ChronoUnit) temporalUnit;
            if (chronoUnit.compareTo(ChronoUnit.DAYS) >= 0 && chronoUnit != ChronoUnit.FOREVER) {
                z = true;
            } else {
                z = false;
            }
            A a = this.b;
            z zVar = this.c;
            i g = this.a.g(j, temporalUnit);
            if (z) {
                return K(g, zVar, a);
            }
            Objects.a(g, "localDateTime");
            Objects.a(a, "offset");
            Objects.a(zVar, "zone");
            if (zVar.J().g(g).contains(a)) {
                return new D(g, zVar, a);
            }
            return h(AbstractC0019i.n(g, a), g.K(), zVar);
        }
        return (D) temporalUnit.p(this, j);
    }

    public final i N() {
        return this.a;
    }

    @Override // j$.time.chrono.InterfaceC0021k
    /* renamed from: O, reason: merged with bridge method [inline-methods] */
    public final D j(z zVar) {
        Objects.a(zVar, "zone");
        if (this.c.equals(zVar)) {
            return this;
        }
        i iVar = this.a;
        iVar.getClass();
        return h(AbstractC0019i.n(iVar, this.b), iVar.K(), zVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void P(DataOutput dataOutput) {
        this.a.Z(dataOutput);
        this.b.W(dataOutput);
        this.c.N((ObjectOutput) dataOutput);
    }

    @Override // j$.time.chrono.InterfaceC0021k
    public final j$.time.chrono.n a() {
        return ((g) d()).a();
    }

    @Override // j$.time.temporal.Temporal
    public final long b(Temporal temporal, TemporalUnit temporalUnit) {
        D I = I(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            D j = I.j(this.c);
            ChronoUnit chronoUnit = (ChronoUnit) temporalUnit;
            int compareTo = chronoUnit.compareTo(ChronoUnit.DAYS);
            i iVar = this.a;
            i iVar2 = j.a;
            if (compareTo >= 0 && chronoUnit != ChronoUnit.FOREVER) {
                return iVar.b(iVar2, temporalUnit);
            }
            return p.I(iVar, this.b).b(p.I(iVar2, j.b), temporalUnit);
        }
        return temporalUnit.n(this, I);
    }

    @Override // j$.time.chrono.InterfaceC0021k
    public final k c() {
        return this.a.c();
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return AbstractC0019i.d(this, (InterfaceC0021k) obj);
    }

    @Override // j$.time.chrono.InterfaceC0021k
    public final InterfaceC0012b d() {
        return this.a.V();
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal e(long j, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
            int i = C.a[aVar.ordinal()];
            i iVar = this.a;
            z zVar = this.c;
            if (i != 1) {
                A a = this.b;
                if (i != 2) {
                    return K(iVar.e(j, oVar), zVar, a);
                }
                A T = A.T(aVar.D(j));
                if (!T.equals(a) && zVar.J().g(iVar).contains(T)) {
                    return new D(iVar, zVar, T);
                }
                return this;
            }
            return h(j, iVar.K(), zVar);
        }
        return (D) oVar.r(this, j);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof D)) {
            return false;
        }
        D d = (D) obj;
        return this.a.equals(d.a) && this.b.equals(d.b) && this.c.equals(d.c);
    }

    @Override // j$.time.temporal.l
    public final boolean f(j$.time.temporal.o oVar) {
        return (oVar instanceof j$.time.temporal.a) || (oVar != null && oVar.p(this));
    }

    public final int hashCode() {
        return (this.a.hashCode() ^ this.b.hashCode()) ^ Integer.rotateLeft(this.c.hashCode(), 3);
    }

    @Override // j$.time.chrono.InterfaceC0021k
    public final A i() {
        return this.b;
    }

    @Override // j$.time.chrono.InterfaceC0021k
    public final InterfaceC0021k k(z zVar) {
        Objects.a(zVar, "zone");
        if (this.c.equals(zVar)) {
            return this;
        }
        return K(this.a, zVar, this.b);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal n(long j, ChronoUnit chronoUnit) {
        if (j == Long.MIN_VALUE) {
            return g(Long.MAX_VALUE, chronoUnit).g(1L, chronoUnit);
        }
        return g(-j, chronoUnit);
    }

    @Override // j$.time.chrono.InterfaceC0021k
    public final InterfaceC0015e o() {
        return this.a;
    }

    @Override // j$.time.temporal.l
    public final int p(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            int i = C.a[((j$.time.temporal.a) oVar).ordinal()];
            if (i != 1) {
                if (i != 2) {
                    return this.a.p(oVar);
                }
                return this.b.Q();
            }
            throw new RuntimeException("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
        }
        return AbstractC0019i.e(this, oVar);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal r(g gVar) {
        return K(i.Q(gVar, this.a.c()), this.c, this.b);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? (oVar == j$.time.temporal.a.INSTANT_SECONDS || oVar == j$.time.temporal.a.OFFSET_SECONDS) ? ((j$.time.temporal.a) oVar).n() : this.a.s(oVar) : oVar.s(this);
    }

    public final String toString() {
        String iVar = this.a.toString();
        A a = this.b;
        String str = iVar + a.toString();
        z zVar = this.c;
        if (a == zVar) {
            return str;
        }
        return str + "[" + zVar.toString() + "]";
    }

    @Override // j$.time.chrono.InterfaceC0021k
    public final z w() {
        return this.c;
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            int i = C.a[((j$.time.temporal.a) oVar).ordinal()];
            if (i != 1) {
                if (i != 2) {
                    return this.a.z(oVar);
                }
                return this.b.Q();
            }
            return AbstractC0019i.o(this);
        }
        return oVar.h(this);
    }
}
