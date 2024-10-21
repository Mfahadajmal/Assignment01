package j$.time.chrono;

import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import j$.util.Objects;
import java.io.Serializable;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: j$.time.chrono.d, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract class AbstractC0014d implements InterfaceC0012b, Temporal, j$.time.temporal.m, Serializable {
    private static final long serialVersionUID = 6282433883239719096L;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InterfaceC0012b I(n nVar, Temporal temporal) {
        InterfaceC0012b interfaceC0012b = (InterfaceC0012b) temporal;
        AbstractC0011a abstractC0011a = (AbstractC0011a) nVar;
        if (abstractC0011a.equals(interfaceC0012b.a())) {
            return interfaceC0012b;
        }
        throw new ClassCastException("Chronology mismatch, expected: " + abstractC0011a.m() + ", actual: " + interfaceC0012b.a().m());
    }

    private long K(InterfaceC0012b interfaceC0012b) {
        if (a().q(j$.time.temporal.a.MONTH_OF_YEAR).d() == 12) {
            j$.time.temporal.a aVar = j$.time.temporal.a.PROLEPTIC_MONTH;
            long z = z(aVar) * 32;
            j$.time.temporal.a aVar2 = j$.time.temporal.a.DAY_OF_MONTH;
            return (((interfaceC0012b.z(aVar) * 32) + interfaceC0012b.p(aVar2)) - (z + j$.time.temporal.k.a(this, aVar2))) / 32;
        }
        throw new IllegalStateException("ChronoLocalDateImpl only supports Chronologies with 12 months per year");
    }

    @Override // j$.time.chrono.InterfaceC0012b
    public long A() {
        return z(j$.time.temporal.a.EPOCH_DAY);
    }

    @Override // j$.time.chrono.InterfaceC0012b
    public InterfaceC0015e B(j$.time.k kVar) {
        return C0017g.J(this, kVar);
    }

    @Override // j$.time.temporal.l
    public final /* synthetic */ Object D(j$.time.temporal.q qVar) {
        return AbstractC0019i.j(this, qVar);
    }

    public o J() {
        return a().E(j$.time.temporal.k.a(this, j$.time.temporal.a.ERA));
    }

    abstract InterfaceC0012b L(long j);

    abstract InterfaceC0012b M(long j);

    abstract InterfaceC0012b N(long j);

    @Override // j$.time.temporal.Temporal
    /* renamed from: O, reason: merged with bridge method [inline-methods] */
    public InterfaceC0012b r(j$.time.temporal.m mVar) {
        return I(a(), mVar.h(this));
    }

    @Override // j$.time.chrono.InterfaceC0012b, j$.time.temporal.Temporal
    public long b(Temporal temporal, TemporalUnit temporalUnit) {
        Objects.a(temporal, "endExclusive");
        InterfaceC0012b H = a().H(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            switch (AbstractC0013c.a[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return H.A() - A();
                case 2:
                    return (H.A() - A()) / 7;
                case 3:
                    return K(H);
                case 4:
                    return K(H) / 12;
                case 5:
                    return K(H) / 120;
                case 6:
                    return K(H) / 1200;
                case 7:
                    return K(H) / 12000;
                case 8:
                    j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                    return H.z(aVar) - z(aVar);
                default:
                    throw new RuntimeException("Unsupported unit: ".concat(String.valueOf(temporalUnit)));
            }
        }
        Objects.a(temporalUnit, "unit");
        return temporalUnit.n(this, H);
    }

    @Override // j$.time.temporal.Temporal
    public InterfaceC0012b e(long j, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
        }
        return I(a(), oVar.r(this, j));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof InterfaceC0012b) && AbstractC0019i.b(this, (InterfaceC0012b) obj) == 0) {
            return true;
        }
        return false;
    }

    @Override // j$.time.chrono.InterfaceC0012b, j$.time.temporal.l
    public /* synthetic */ boolean f(j$.time.temporal.o oVar) {
        return AbstractC0019i.h(this, oVar);
    }

    @Override // j$.time.temporal.Temporal
    public InterfaceC0012b g(long j, TemporalUnit temporalUnit) {
        boolean z = temporalUnit instanceof ChronoUnit;
        if (!z) {
            if (!z) {
                return I(a(), temporalUnit.p(this, j));
            }
            throw new RuntimeException("Unsupported unit: ".concat(String.valueOf(temporalUnit)));
        }
        switch (AbstractC0013c.a[((ChronoUnit) temporalUnit).ordinal()]) {
            case 1:
                return L(j);
            case 2:
                return L(j$.nio.file.attribute.a.f(j, 7));
            case 3:
                return M(j);
            case 4:
                return N(j);
            case 5:
                return N(j$.nio.file.attribute.a.f(j, 10));
            case 6:
                return N(j$.nio.file.attribute.a.f(j, 100));
            case 7:
                return N(j$.nio.file.attribute.a.f(j, 1000));
            case 8:
                j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                return e(j$.nio.file.attribute.a.e(z(aVar), j), (j$.time.temporal.o) aVar);
            default:
                throw new RuntimeException("Unsupported unit: ".concat(String.valueOf(temporalUnit)));
        }
    }

    @Override // j$.time.temporal.m
    public final /* synthetic */ Temporal h(Temporal temporal) {
        return AbstractC0019i.a(this, temporal);
    }

    @Override // j$.time.chrono.InterfaceC0012b
    public int hashCode() {
        long A = A();
        return ((AbstractC0011a) a()).hashCode() ^ ((int) (A ^ (A >>> 32)));
    }

    @Override // j$.time.temporal.l
    public final /* synthetic */ int p(j$.time.temporal.o oVar) {
        return j$.time.temporal.k.a(this, oVar);
    }

    @Override // j$.time.temporal.l
    public /* synthetic */ j$.time.temporal.s s(j$.time.temporal.o oVar) {
        return j$.time.temporal.k.d(this, oVar);
    }

    @Override // j$.time.chrono.InterfaceC0012b
    public String toString() {
        String str;
        long z = z(j$.time.temporal.a.YEAR_OF_ERA);
        long z2 = z(j$.time.temporal.a.MONTH_OF_YEAR);
        long z3 = z(j$.time.temporal.a.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder(30);
        sb.append(((AbstractC0011a) a()).m());
        sb.append(" ");
        sb.append(J());
        sb.append(" ");
        sb.append(z);
        String str2 = "-";
        if (z2 >= 10) {
            str = "-";
        } else {
            str = "-0";
        }
        sb.append(str);
        sb.append(z2);
        if (z3 < 10) {
            str2 = "-0";
        }
        sb.append(str2);
        sb.append(z3);
        return sb.toString();
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: u, reason: merged with bridge method [inline-methods] */
    public InterfaceC0012b n(long j, TemporalUnit temporalUnit) {
        return I(a(), j$.time.temporal.k.b(this, j, temporalUnit));
    }

    @Override // java.lang.Comparable
    /* renamed from: y */
    public final /* synthetic */ int compareTo(InterfaceC0012b interfaceC0012b) {
        return AbstractC0019i.b(this, interfaceC0012b);
    }
}
