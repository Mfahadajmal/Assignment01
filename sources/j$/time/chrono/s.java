package j$.time.chrono;

import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;

/* loaded from: classes2.dex */
public final class s extends AbstractC0014d {
    private static final long serialVersionUID = -5207853542612002020L;
    private final transient q a;
    private final transient int b;
    private final transient int c;
    private final transient int d;

    private s(q qVar, int i, int i2, int i3) {
        qVar.D(i, i2, i3);
        this.a = qVar;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    private int P() {
        return this.a.z(this.b, this.c) + this.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static s Q(q qVar, int i, int i2, int i3) {
        return new s(qVar, i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static s R(q qVar, long j) {
        return new s(qVar, j);
    }

    private s U(int i, int i2, int i3) {
        q qVar = this.a;
        int J2 = qVar.J(i, i2);
        if (i3 > J2) {
            i3 = J2;
        }
        return new s(qVar, i, i2, i3);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new G((byte) 6, this);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.chrono.InterfaceC0012b
    public final long A() {
        return this.a.D(this.b, this.c, this.d);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.chrono.InterfaceC0012b
    public final InterfaceC0015e B(j$.time.k kVar) {
        return C0017g.J(this, kVar);
    }

    @Override // j$.time.chrono.AbstractC0014d
    public final o J() {
        return t.AH;
    }

    @Override // j$.time.chrono.AbstractC0014d
    final InterfaceC0012b N(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = this.b + ((int) j);
        int i = (int) j2;
        if (j2 == i) {
            return U(i, this.c, this.d);
        }
        throw new ArithmeticException();
    }

    @Override // j$.time.chrono.AbstractC0014d
    /* renamed from: O */
    public final InterfaceC0012b r(j$.time.temporal.m mVar) {
        return (s) super.r(mVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // j$.time.chrono.AbstractC0014d
    /* renamed from: S, reason: merged with bridge method [inline-methods] */
    public final s L(long j) {
        return new s(this.a, A() + j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // j$.time.chrono.AbstractC0014d
    /* renamed from: T, reason: merged with bridge method [inline-methods] */
    public final s M(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = (this.b * 12) + (this.c - 1) + j;
        return U(this.a.r(j$.nio.file.attribute.a.c(j2, 12L)), ((int) j$.nio.file.attribute.a.d(j2, 12L)) + 1, this.d);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.temporal.Temporal
    /* renamed from: V, reason: merged with bridge method [inline-methods] */
    public final s e(long j, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
            q qVar = this.a;
            qVar.q(aVar).b(j, aVar);
            int i = (int) j;
            int i2 = r.a[aVar.ordinal()];
            int i3 = this.d;
            int i4 = this.c;
            int i5 = this.b;
            switch (i2) {
                case 1:
                    return U(i5, i4, i);
                case 2:
                    return L(Math.min(i, qVar.K(i5)) - P());
                case 3:
                    return L((j - z(j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH)) * 7);
                case 4:
                    return L(j - (((int) j$.nio.file.attribute.a.d(A() + 3, 7)) + 1));
                case 5:
                    return L(j - z(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH));
                case 6:
                    return L(j - z(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR));
                case 7:
                    return new s(qVar, j);
                case 8:
                    return L((j - z(j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR)) * 7);
                case 9:
                    return U(i5, i, i3);
                case 10:
                    return M(j - (((i5 * 12) + i4) - 1));
                case 11:
                    if (i5 < 1) {
                        i = 1 - i;
                    }
                    return U(i, i4, i3);
                case 12:
                    return U(i, i4, i3);
                case 13:
                    return U(1 - i5, i4, i3);
                default:
                    throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
            }
        }
        return (s) super.e(j, oVar);
    }

    @Override // j$.time.chrono.InterfaceC0012b
    public final n a() {
        return this.a;
    }

    @Override // j$.time.chrono.AbstractC0014d
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return this.b == sVar.b && this.c == sVar.c && this.d == sVar.d && this.a.equals(sVar.a);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.chrono.InterfaceC0012b, j$.time.temporal.Temporal
    public final InterfaceC0012b g(long j, TemporalUnit temporalUnit) {
        return (s) super.g(j, temporalUnit);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.chrono.InterfaceC0012b
    public final int hashCode() {
        int hashCode = this.a.m().hashCode();
        int i = this.b;
        return (hashCode ^ (i & (-2048))) ^ (((i << 11) + (this.c << 6)) + this.d);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.temporal.Temporal
    public final Temporal n(long j, ChronoUnit chronoUnit) {
        return (s) super.n(j, chronoUnit);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.temporal.Temporal
    public final Temporal r(j$.time.g gVar) {
        return (s) super.r(gVar);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        int J2;
        long j;
        if (oVar instanceof j$.time.temporal.a) {
            if (AbstractC0019i.h(this, oVar)) {
                j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
                int i = r.a[aVar.ordinal()];
                int i2 = this.b;
                q qVar = this.a;
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            return qVar.q(aVar);
                        }
                        j = 5;
                        return j$.time.temporal.s.j(1L, j);
                    }
                    J2 = qVar.K(i2);
                } else {
                    J2 = qVar.J(i2, this.c);
                }
                j = J2;
                return j$.time.temporal.s.j(1L, j);
            }
            throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
        }
        return oVar.s(this);
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.chrono.InterfaceC0012b
    /* renamed from: u */
    public final InterfaceC0012b n(long j, TemporalUnit temporalUnit) {
        return (s) super.n(j, temporalUnit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeExternal(ObjectOutput objectOutput) {
        objectOutput.writeObject(this.a);
        objectOutput.writeInt(j$.time.temporal.k.a(this, j$.time.temporal.a.YEAR));
        objectOutput.writeByte(j$.time.temporal.k.a(this, j$.time.temporal.a.MONTH_OF_YEAR));
        objectOutput.writeByte(j$.time.temporal.k.a(this, j$.time.temporal.a.DAY_OF_MONTH));
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            int i = r.a[((j$.time.temporal.a) oVar).ordinal()];
            int i2 = this.c;
            int i3 = 1;
            int i4 = this.d;
            int i5 = this.b;
            switch (i) {
                case 1:
                    return i4;
                case 2:
                    return P();
                case 3:
                    return ((i4 - 1) / 7) + 1;
                case 4:
                    return ((int) j$.nio.file.attribute.a.d(A() + 3, 7)) + 1;
                case 5:
                    return ((i4 - 1) % 7) + 1;
                case 6:
                    return ((P() - 1) % 7) + 1;
                case 7:
                    return A();
                case 8:
                    return ((P() - 1) / 7) + 1;
                case 9:
                    return i2;
                case 10:
                    return ((i5 * 12) + i2) - 1;
                case 11:
                    return i5;
                case 12:
                    return i5;
                case 13:
                    if (i5 <= 1) {
                        i3 = 0;
                    }
                    return i3;
                default:
                    throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
            }
        }
        return oVar.h(this);
    }

    private s(q qVar, long j) {
        int[] I = qVar.I((int) j);
        this.a = qVar;
        this.b = I[0];
        this.c = I[1];
        this.d = I[2];
    }

    @Override // j$.time.chrono.AbstractC0014d, j$.time.temporal.Temporal
    public final Temporal g(long j, TemporalUnit temporalUnit) {
        return (s) super.g(j, temporalUnit);
    }
}
