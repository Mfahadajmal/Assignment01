package j$.time.chrono;

import j$.time.Instant;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: j$.time.chrono.g, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0017g implements InterfaceC0015e, Temporal, j$.time.temporal.m, Serializable {
    private static final long serialVersionUID = 4556003607393004514L;
    private final transient InterfaceC0012b a;
    private final transient j$.time.k b;

    private C0017g(InterfaceC0012b interfaceC0012b, j$.time.k kVar) {
        Objects.a(kVar, "time");
        this.a = interfaceC0012b;
        this.b = kVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static C0017g I(n nVar, Temporal temporal) {
        C0017g c0017g = (C0017g) temporal;
        AbstractC0011a abstractC0011a = (AbstractC0011a) nVar;
        if (abstractC0011a.equals(c0017g.a.a())) {
            return c0017g;
        }
        throw new ClassCastException("Chronology mismatch, required: " + abstractC0011a.m() + ", actual: " + c0017g.a.a().m());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static C0017g J(InterfaceC0012b interfaceC0012b, j$.time.k kVar) {
        return new C0017g(interfaceC0012b, kVar);
    }

    private C0017g M(InterfaceC0012b interfaceC0012b, long j, long j2, long j3, long j4) {
        long j5 = j | j2 | j3 | j4;
        j$.time.k kVar = this.b;
        if (j5 == 0) {
            return P(interfaceC0012b, kVar);
        }
        long j6 = j2 / 1440;
        long j7 = j / 24;
        long j8 = (j2 % 1440) * 60000000000L;
        long j9 = ((j % 24) * 3600000000000L) + j8 + ((j3 % 86400) * 1000000000) + (j4 % 86400000000000L);
        long Y = kVar.Y();
        long j10 = j9 + Y;
        long c = j$.nio.file.attribute.a.c(j10, 86400000000000L) + j7 + j6 + (j3 / 86400) + (j4 / 86400000000000L);
        long d = j$.nio.file.attribute.a.d(j10, 86400000000000L);
        if (d != Y) {
            kVar = j$.time.k.Q(d);
        }
        return P(interfaceC0012b.g(c, (TemporalUnit) ChronoUnit.DAYS), kVar);
    }

    private C0017g P(Temporal temporal, j$.time.k kVar) {
        InterfaceC0012b interfaceC0012b = this.a;
        return (interfaceC0012b == temporal && this.b == kVar) ? this : new C0017g(AbstractC0014d.I(interfaceC0012b.a(), temporal), kVar);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new G((byte) 2, this);
    }

    @Override // j$.time.temporal.l
    public final /* synthetic */ Object D(j$.time.temporal.q qVar) {
        return AbstractC0019i.k(this, qVar);
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: K, reason: merged with bridge method [inline-methods] */
    public final C0017g g(long j, TemporalUnit temporalUnit) {
        boolean z = temporalUnit instanceof ChronoUnit;
        InterfaceC0012b interfaceC0012b = this.a;
        if (z) {
            int i = AbstractC0016f.a[((ChronoUnit) temporalUnit).ordinal()];
            j$.time.k kVar = this.b;
            switch (i) {
                case 1:
                    return M(this.a, 0L, 0L, 0L, j);
                case 2:
                    C0017g P = P(interfaceC0012b.g(j / 86400000000L, (TemporalUnit) ChronoUnit.DAYS), kVar);
                    return P.M(P.a, 0L, 0L, 0L, (j % 86400000000L) * 1000);
                case 3:
                    C0017g P2 = P(interfaceC0012b.g(j / 86400000, (TemporalUnit) ChronoUnit.DAYS), kVar);
                    return P2.M(P2.a, 0L, 0L, 0L, (j % 86400000) * 1000000);
                case 4:
                    return L(j);
                case 5:
                    return M(this.a, 0L, j, 0L, 0L);
                case 6:
                    return M(this.a, j, 0L, 0L, 0L);
                case 7:
                    C0017g P3 = P(interfaceC0012b.g(j / 256, (TemporalUnit) ChronoUnit.DAYS), kVar);
                    return P3.M(P3.a, (j % 256) * 12, 0L, 0L, 0L);
                default:
                    return P(interfaceC0012b.g(j, temporalUnit), kVar);
            }
        }
        return I(interfaceC0012b.a(), temporalUnit.p(this, j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final C0017g L(long j) {
        return M(this.a, 0L, 0L, j, 0L);
    }

    public final Instant N(j$.time.A a) {
        return Instant.M(AbstractC0019i.n(this, a), this.b.N());
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: O, reason: merged with bridge method [inline-methods] */
    public final C0017g e(long j, j$.time.temporal.o oVar) {
        boolean z = oVar instanceof j$.time.temporal.a;
        InterfaceC0012b interfaceC0012b = this.a;
        if (z) {
            boolean J2 = ((j$.time.temporal.a) oVar).J();
            j$.time.k kVar = this.b;
            if (J2) {
                return P(interfaceC0012b, kVar.e(j, oVar));
            }
            return P(interfaceC0012b.e(j, oVar), kVar);
        }
        return I(interfaceC0012b.a(), oVar.r(this, j));
    }

    @Override // j$.time.chrono.InterfaceC0015e
    public final n a() {
        return this.a.a();
    }

    @Override // j$.time.temporal.Temporal
    public final long b(Temporal temporal, TemporalUnit temporalUnit) {
        boolean z;
        long j;
        Objects.a(temporal, "endExclusive");
        InterfaceC0012b interfaceC0012b = this.a;
        InterfaceC0015e G = interfaceC0012b.a().G(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            ChronoUnit chronoUnit = ChronoUnit.DAYS;
            if (((ChronoUnit) temporalUnit).compareTo(chronoUnit) < 0) {
                z = true;
            } else {
                z = false;
            }
            j$.time.k kVar = this.b;
            if (z) {
                j$.time.temporal.a aVar = j$.time.temporal.a.EPOCH_DAY;
                long z2 = G.z(aVar) - interfaceC0012b.z(aVar);
                switch (AbstractC0016f.a[((ChronoUnit) temporalUnit).ordinal()]) {
                    case 1:
                        j = 86400000000000L;
                        z2 = j$.nio.file.attribute.a.f(z2, j);
                        break;
                    case 2:
                        j = 86400000000L;
                        z2 = j$.nio.file.attribute.a.f(z2, j);
                        break;
                    case 3:
                        j = 86400000;
                        z2 = j$.nio.file.attribute.a.f(z2, j);
                        break;
                    case 4:
                        z2 = j$.nio.file.attribute.a.f(z2, 86400);
                        break;
                    case 5:
                        z2 = j$.nio.file.attribute.a.f(z2, 1440);
                        break;
                    case 6:
                        z2 = j$.nio.file.attribute.a.f(z2, 24);
                        break;
                    case 7:
                        z2 = j$.nio.file.attribute.a.f(z2, 2);
                        break;
                }
                return j$.nio.file.attribute.a.e(z2, kVar.b(G.c(), temporalUnit));
            }
            InterfaceC0012b d = G.d();
            if (G.c().compareTo(kVar) < 0) {
                d = d.n(1L, chronoUnit);
            }
            return interfaceC0012b.b(d, temporalUnit);
        }
        Objects.a(temporalUnit, "unit");
        return temporalUnit.n(this, G);
    }

    @Override // j$.time.chrono.InterfaceC0015e
    public final j$.time.k c() {
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
        if ((obj instanceof InterfaceC0015e) && AbstractC0019i.c(this, (InterfaceC0015e) obj) == 0) {
            return true;
        }
        return false;
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
        return temporal.e(d().A(), j$.time.temporal.a.EPOCH_DAY).e(c().Y(), j$.time.temporal.a.NANO_OF_DAY);
    }

    public final int hashCode() {
        return this.a.hashCode() ^ this.b.hashCode();
    }

    @Override // java.lang.Comparable
    /* renamed from: l */
    public final /* synthetic */ int compareTo(InterfaceC0015e interfaceC0015e) {
        return AbstractC0019i.c(this, interfaceC0015e);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal n(long j, ChronoUnit chronoUnit) {
        return I(this.a.a(), j$.time.temporal.k.b(this, j, chronoUnit));
    }

    @Override // j$.time.temporal.l
    public final int p(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? ((j$.time.temporal.a) oVar).J() ? this.b.p(oVar) : this.a.p(oVar) : s(oVar).a(z(oVar), oVar);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal r(j$.time.g gVar) {
        return P(gVar, this.b);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            if (((j$.time.temporal.a) oVar).J()) {
                j$.time.k kVar = this.b;
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
    public final InterfaceC0021k v(j$.time.A a) {
        return m.I(a, null, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeExternal(ObjectOutput objectOutput) {
        objectOutput.writeObject(this.a);
        objectOutput.writeObject(this.b);
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? ((j$.time.temporal.a) oVar).J() ? this.b.z(oVar) : this.a.z(oVar) : oVar.h(this);
    }
}
