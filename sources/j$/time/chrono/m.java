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

/* loaded from: classes2.dex */
final class m implements InterfaceC0021k, Serializable {
    private static final long serialVersionUID = -5261813987200935591L;
    private final transient C0017g a;
    private final transient j$.time.A b;
    private final transient j$.time.z c;

    private m(j$.time.z zVar, j$.time.A a, C0017g c0017g) {
        Objects.a(c0017g, "dateTime");
        this.a = c0017g;
        Objects.a(a, "offset");
        this.b = a;
        Objects.a(zVar, "zone");
        this.c = zVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0053, code lost:
    
        if (r2.contains(r7) != false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static j$.time.chrono.InterfaceC0021k I(j$.time.z r6, j$.time.A r7, j$.time.chrono.C0017g r8) {
        /*
            java.lang.String r0 = "localDateTime"
            j$.util.Objects.a(r8, r0)
            java.lang.String r0 = "zone"
            j$.util.Objects.a(r6, r0)
            boolean r0 = r6 instanceof j$.time.A
            if (r0 == 0) goto L17
            j$.time.chrono.m r7 = new j$.time.chrono.m
            r0 = r6
            j$.time.A r0 = (j$.time.A) r0
            r7.<init>(r6, r0, r8)
            return r7
        L17:
            j$.time.zone.e r0 = r6.J()
            j$.time.i r1 = j$.time.i.J(r8)
            java.util.List r2 = r0.g(r1)
            int r3 = r2.size()
            r4 = 1
            r5 = 0
            if (r3 != r4) goto L32
        L2b:
            java.lang.Object r7 = r2.get(r5)
            j$.time.A r7 = (j$.time.A) r7
            goto L55
        L32:
            int r3 = r2.size()
            if (r3 != 0) goto L4d
            j$.time.zone.b r7 = r0.f(r1)
            j$.time.Duration r0 = r7.p()
            long r0 = r0.getSeconds()
            j$.time.chrono.g r8 = r8.L(r0)
            j$.time.A r7 = r7.r()
            goto L55
        L4d:
            if (r7 == 0) goto L2b
            boolean r0 = r2.contains(r7)
            if (r0 == 0) goto L2b
        L55:
            java.lang.String r0 = "offset"
            j$.util.Objects.a(r7, r0)
            j$.time.chrono.m r0 = new j$.time.chrono.m
            r0.<init>(r6, r7, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.chrono.m.I(j$.time.z, j$.time.A, j$.time.chrono.g):j$.time.chrono.k");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static m J(n nVar, Instant instant, j$.time.z zVar) {
        j$.time.A d = zVar.J().d(instant);
        Objects.a(d, "offset");
        return new m(zVar, d, (C0017g) nVar.G(j$.time.i.R(instant.K(), instant.L(), d)));
    }

    static m h(n nVar, Temporal temporal) {
        m mVar = (m) temporal;
        AbstractC0011a abstractC0011a = (AbstractC0011a) nVar;
        if (abstractC0011a.equals(mVar.a())) {
            return mVar;
        }
        throw new ClassCastException("Chronology mismatch, required: " + abstractC0011a.m() + ", actual: " + mVar.a().m());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new G((byte) 3, this);
    }

    @Override // j$.time.temporal.l
    public final /* synthetic */ Object D(j$.time.temporal.q qVar) {
        return AbstractC0019i.l(this, qVar);
    }

    @Override // j$.time.chrono.InterfaceC0021k
    public final /* synthetic */ long F() {
        return AbstractC0019i.o(this);
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: K, reason: merged with bridge method [inline-methods] */
    public final InterfaceC0021k g(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            return h(a(), this.a.g(j, temporalUnit).h(this));
        }
        return h(a(), temporalUnit.p(this, j));
    }

    @Override // j$.time.chrono.InterfaceC0021k
    public final n a() {
        return d().a();
    }

    @Override // j$.time.temporal.Temporal
    public final long b(Temporal temporal, TemporalUnit temporalUnit) {
        Objects.a(temporal, "endExclusive");
        InterfaceC0021k C = a().C(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            return this.a.b(C.j(this.b).o(), temporalUnit);
        }
        Objects.a(temporalUnit, "unit");
        return temporalUnit.n(this, C);
    }

    @Override // j$.time.chrono.InterfaceC0021k
    public final j$.time.k c() {
        return ((C0017g) o()).c();
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return AbstractC0019i.d(this, (InterfaceC0021k) obj);
    }

    @Override // j$.time.chrono.InterfaceC0021k
    public final InterfaceC0012b d() {
        return ((C0017g) o()).d();
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal e(long j, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
            int i = AbstractC0022l.a[aVar.ordinal()];
            if (i != 1) {
                j$.time.z zVar = this.c;
                C0017g c0017g = this.a;
                if (i != 2) {
                    return I(zVar, this.b, c0017g.e(j, oVar));
                }
                return J(a(), c0017g.N(j$.time.A.T(aVar.D(j))), zVar);
            }
            return g(j - AbstractC0019i.o(this), ChronoUnit.SECONDS);
        }
        return h(a(), oVar.r(this, j));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof InterfaceC0021k) && AbstractC0019i.d(this, (InterfaceC0021k) obj) == 0) {
            return true;
        }
        return false;
    }

    @Override // j$.time.temporal.l
    public final boolean f(j$.time.temporal.o oVar) {
        return (oVar instanceof j$.time.temporal.a) || (oVar != null && oVar.p(this));
    }

    public final int hashCode() {
        return (this.a.hashCode() ^ this.b.hashCode()) ^ Integer.rotateLeft(this.c.hashCode(), 3);
    }

    @Override // j$.time.chrono.InterfaceC0021k
    public final j$.time.A i() {
        return this.b;
    }

    @Override // j$.time.chrono.InterfaceC0021k
    public final InterfaceC0021k j(j$.time.z zVar) {
        Objects.a(zVar, "zone");
        if (this.c.equals(zVar)) {
            return this;
        }
        return J(a(), this.a.N(this.b), zVar);
    }

    @Override // j$.time.chrono.InterfaceC0021k
    public final InterfaceC0021k k(j$.time.z zVar) {
        return I(zVar, this.b, this.a);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal n(long j, ChronoUnit chronoUnit) {
        return h(a(), j$.time.temporal.k.b(this, j, chronoUnit));
    }

    @Override // j$.time.chrono.InterfaceC0021k
    public final InterfaceC0015e o() {
        return this.a;
    }

    @Override // j$.time.temporal.l
    public final /* synthetic */ int p(j$.time.temporal.o oVar) {
        return AbstractC0019i.e(this, oVar);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal r(j$.time.g gVar) {
        return h(a(), gVar.h(this));
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            if (oVar != j$.time.temporal.a.INSTANT_SECONDS && oVar != j$.time.temporal.a.OFFSET_SECONDS) {
                return ((C0017g) o()).s(oVar);
            }
            return ((j$.time.temporal.a) oVar).n();
        }
        return oVar.s(this);
    }

    public final String toString() {
        String c0017g = this.a.toString();
        j$.time.A a = this.b;
        String str = c0017g + a.toString();
        j$.time.z zVar = this.c;
        if (a == zVar) {
            return str;
        }
        return str + "[" + zVar.toString() + "]";
    }

    @Override // j$.time.chrono.InterfaceC0021k
    public final j$.time.z w() {
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeExternal(ObjectOutput objectOutput) {
        objectOutput.writeObject(this.a);
        objectOutput.writeObject(this.b);
        objectOutput.writeObject(this.c);
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            int i = AbstractC0020j.a[((j$.time.temporal.a) oVar).ordinal()];
            if (i != 1) {
                if (i != 2) {
                    return ((C0017g) o()).z(oVar);
                }
                return i().Q();
            }
            return F();
        }
        return oVar.h(this);
    }
}
