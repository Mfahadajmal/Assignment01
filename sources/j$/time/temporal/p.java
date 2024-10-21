package j$.time.temporal;

import j$.time.A;
import j$.time.z;

/* loaded from: classes2.dex */
final class p implements q {
    public final /* synthetic */ int a;

    public /* synthetic */ p(int i) {
        this.a = i;
    }

    @Override // j$.time.temporal.q
    public final Object a(l lVar) {
        switch (this.a) {
            case 0:
                return (z) lVar.D(k.a);
            case 1:
                return (j$.time.chrono.n) lVar.D(k.b);
            case 2:
                return (TemporalUnit) lVar.D(k.c);
            case 3:
                a aVar = a.OFFSET_SECONDS;
                if (lVar.f(aVar)) {
                    return A.T(lVar.p(aVar));
                }
                return null;
            case 4:
                z zVar = (z) lVar.D(k.a);
                if (zVar == null) {
                    return (z) lVar.D(k.d);
                }
                return zVar;
            case 5:
                a aVar2 = a.EPOCH_DAY;
                if (lVar.f(aVar2)) {
                    return j$.time.g.X(lVar.z(aVar2));
                }
                return null;
            default:
                a aVar3 = a.NANO_OF_DAY;
                if (lVar.f(aVar3)) {
                    return j$.time.k.Q(lVar.z(aVar3));
                }
                return null;
        }
    }

    public final String toString() {
        switch (this.a) {
            case 0:
                return "ZoneId";
            case 1:
                return "Chronology";
            case 2:
                return "Precision";
            case 3:
                return "ZoneOffset";
            case 4:
                return "Zone";
            case 5:
                return "LocalDate";
            default:
                return "LocalTime";
        }
    }
}
