package j$.time.chrono;

import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

/* loaded from: classes2.dex */
public final class E extends AbstractC0014d {
    private static final long serialVersionUID = 1300372329181994526L;
    private final transient j$.time.g a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public E(j$.time.g gVar) {
        Objects.a(gVar, "isoDate");
        this.a = gVar;
    }

    private int P() {
        return this.a.Q() - 1911;
    }

    private E R(j$.time.g gVar) {
        return gVar.equals(this.a) ? this : new E(gVar);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new G((byte) 7, this);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.chrono.InterfaceC0012b
    public final long A() {
        return this.a.A();
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.chrono.InterfaceC0012b
    public final InterfaceC0015e B(j$.time.k kVar) {
        return C0017g.J(this, kVar);
    }

    @Override // j$.time.chrono.AbstractC0014d
    public final o J() {
        if (P() >= 1) {
            return F.ROC;
        }
        return F.BEFORE_ROC;
    }

    @Override // j$.time.chrono.AbstractC0014d
    final InterfaceC0012b L(long j) {
        return R(this.a.Z(j));
    }

    @Override // j$.time.chrono.AbstractC0014d
    final InterfaceC0012b M(long j) {
        return R(this.a.a0(j));
    }

    @Override // j$.time.chrono.AbstractC0014d
    final InterfaceC0012b N(long j) {
        return R(this.a.b0(j));
    }

    @Override // j$.time.chrono.AbstractC0014d
    /* renamed from: O */
    public final InterfaceC0012b r(j$.time.temporal.m mVar) {
        return (E) super.r(mVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0024, code lost:
    
        if (r2 != 7) goto L20;
     */
    @Override // j$.time.chrono.AbstractC0014d, j$.time.temporal.Temporal
    /* renamed from: Q, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final j$.time.chrono.E e(long r9, j$.time.temporal.o r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof j$.time.temporal.a
            if (r0 == 0) goto L9a
            r0 = r11
            j$.time.temporal.a r0 = (j$.time.temporal.a) r0
            long r1 = r8.z(r0)
            int r3 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r3 != 0) goto L10
            return r8
        L10:
            int[] r1 = j$.time.chrono.D.a
            int r2 = r0.ordinal()
            r2 = r1[r2]
            j$.time.g r3 = r8.a
            r4 = 7
            r5 = 6
            r6 = 4
            if (r2 == r6) goto L4c
            r7 = 5
            if (r2 == r7) goto L27
            if (r2 == r5) goto L4c
            if (r2 == r4) goto L4c
            goto L62
        L27:
            j$.time.chrono.C r11 = j$.time.chrono.C.d
            j$.time.temporal.s r11 = r11.q(r0)
            r11.b(r9, r0)
            int r11 = r8.P()
            long r0 = (long) r11
            r4 = 12
            long r0 = r0 * r4
            int r11 = r3.O()
            long r4 = (long) r11
            long r0 = r0 + r4
            r4 = 1
            long r0 = r0 - r4
            long r9 = r9 - r0
            j$.time.g r9 = r3.a0(r9)
            j$.time.chrono.E r9 = r8.R(r9)
            return r9
        L4c:
            j$.time.chrono.C r2 = j$.time.chrono.C.d
            j$.time.temporal.s r2 = r2.q(r0)
            int r2 = r2.a(r9, r0)
            int r0 = r0.ordinal()
            r0 = r1[r0]
            if (r0 == r6) goto L85
            if (r0 == r5) goto L7a
            if (r0 == r4) goto L6b
        L62:
            j$.time.g r9 = r3.e(r9, r11)
            j$.time.chrono.E r9 = r8.R(r9)
            return r9
        L6b:
            int r9 = r8.P()
            int r9 = 1912 - r9
            j$.time.g r9 = r3.g0(r9)
            j$.time.chrono.E r9 = r8.R(r9)
            return r9
        L7a:
            int r2 = r2 + 1911
            j$.time.g r9 = r3.g0(r2)
            j$.time.chrono.E r9 = r8.R(r9)
            return r9
        L85:
            int r9 = r8.P()
            r10 = 1
            if (r9 < r10) goto L8f
            int r2 = r2 + 1911
            goto L91
        L8f:
            int r2 = 1912 - r2
        L91:
            j$.time.g r9 = r3.g0(r2)
            j$.time.chrono.E r9 = r8.R(r9)
            return r9
        L9a:
            j$.time.chrono.b r9 = super.e(r9, r11)
            j$.time.chrono.E r9 = (j$.time.chrono.E) r9
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.chrono.E.e(long, j$.time.temporal.o):j$.time.chrono.E");
    }

    @Override // j$.time.chrono.InterfaceC0012b
    public final n a() {
        return C.d;
    }

    @Override // j$.time.chrono.AbstractC0014d
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof E) {
            return this.a.equals(((E) obj).a);
        }
        return false;
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.chrono.InterfaceC0012b, j$.time.temporal.Temporal
    public final InterfaceC0012b g(long j, TemporalUnit temporalUnit) {
        return (E) super.g(j, temporalUnit);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.chrono.InterfaceC0012b
    public final int hashCode() {
        C.d.getClass();
        return this.a.hashCode() ^ (-1990173233);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.temporal.Temporal
    public final Temporal n(long j, ChronoUnit chronoUnit) {
        return (E) super.n(j, chronoUnit);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.temporal.Temporal
    public final Temporal r(j$.time.g gVar) {
        return (E) super.r(gVar);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        long d;
        if (oVar instanceof j$.time.temporal.a) {
            if (AbstractC0019i.h(this, oVar)) {
                j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
                int i = D.a[aVar.ordinal()];
                if (i != 1 && i != 2 && i != 3) {
                    if (i != 4) {
                        return C.d.q(aVar);
                    }
                    j$.time.temporal.s n = j$.time.temporal.a.YEAR.n();
                    if (P() <= 0) {
                        d = (-n.e()) + 1912;
                    } else {
                        d = n.d() - 1911;
                    }
                    return j$.time.temporal.s.j(1L, d);
                }
                return this.a.s(oVar);
            }
            throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
        }
        return oVar.s(this);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.chrono.InterfaceC0012b
    /* renamed from: u */
    public final InterfaceC0012b n(long j, TemporalUnit temporalUnit) {
        return (E) super.n(j, temporalUnit);
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            int i = D.a[((j$.time.temporal.a) oVar).ordinal()];
            int i2 = 1;
            if (i != 4) {
                j$.time.g gVar = this.a;
                if (i != 5) {
                    if (i != 6) {
                        if (i != 7) {
                            return gVar.z(oVar);
                        }
                        if (P() < 1) {
                            i2 = 0;
                        }
                        return i2;
                    }
                    return P();
                }
                return ((P() * 12) + gVar.O()) - 1;
            }
            int P = P();
            if (P < 1) {
                P = 1 - P;
            }
            return P;
        }
        return oVar.h(this);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.temporal.Temporal
    public final Temporal g(long j, TemporalUnit temporalUnit) {
        return (E) super.g(j, temporalUnit);
    }
}
