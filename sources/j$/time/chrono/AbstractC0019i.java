package j$.time.chrono;

import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.util.Objects;

/* renamed from: j$.time.chrono.i, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract /* synthetic */ class AbstractC0019i {
    public static Temporal a(InterfaceC0012b interfaceC0012b, Temporal temporal) {
        return temporal.e(interfaceC0012b.A(), j$.time.temporal.a.EPOCH_DAY);
    }

    public static int b(InterfaceC0012b interfaceC0012b, InterfaceC0012b interfaceC0012b2) {
        int compare = Long.compare(interfaceC0012b.A(), interfaceC0012b2.A());
        if (compare == 0) {
            return ((AbstractC0011a) interfaceC0012b.a()).m().compareTo(interfaceC0012b2.a().m());
        }
        return compare;
    }

    public static int c(InterfaceC0015e interfaceC0015e, InterfaceC0015e interfaceC0015e2) {
        int compareTo = interfaceC0015e.d().compareTo(interfaceC0015e2.d());
        if (compareTo == 0) {
            int compareTo2 = interfaceC0015e.c().compareTo(interfaceC0015e2.c());
            if (compareTo2 == 0) {
                return ((AbstractC0011a) interfaceC0015e.a()).m().compareTo(interfaceC0015e2.a().m());
            }
            return compareTo2;
        }
        return compareTo;
    }

    public static int d(InterfaceC0021k interfaceC0021k, InterfaceC0021k interfaceC0021k2) {
        int compare = Long.compare(interfaceC0021k.F(), interfaceC0021k2.F());
        if (compare == 0) {
            int N = interfaceC0021k.c().N() - interfaceC0021k2.c().N();
            if (N == 0) {
                int compareTo = interfaceC0021k.o().compareTo(interfaceC0021k2.o());
                if (compareTo == 0) {
                    int compareTo2 = interfaceC0021k.w().m().compareTo(interfaceC0021k2.w().m());
                    if (compareTo2 == 0) {
                        return ((AbstractC0011a) interfaceC0021k.a()).m().compareTo(interfaceC0021k2.a().m());
                    }
                    return compareTo2;
                }
                return compareTo;
            }
            return N;
        }
        return compare;
    }

    public static int e(InterfaceC0021k interfaceC0021k, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            int i = AbstractC0020j.a[((j$.time.temporal.a) oVar).ordinal()];
            if (i != 1) {
                if (i != 2) {
                    return interfaceC0021k.o().p(oVar);
                }
                return interfaceC0021k.i().Q();
            }
            throw new RuntimeException("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
        }
        return j$.time.temporal.k.a(interfaceC0021k, oVar);
    }

    public static int f(o oVar, j$.time.temporal.a aVar) {
        if (aVar == j$.time.temporal.a.ERA) {
            return oVar.getValue();
        }
        return j$.time.temporal.k.a(oVar, aVar);
    }

    public static long g(o oVar, j$.time.temporal.o oVar2) {
        if (oVar2 == j$.time.temporal.a.ERA) {
            return oVar.getValue();
        }
        if (!(oVar2 instanceof j$.time.temporal.a)) {
            return oVar2.h(oVar);
        }
        throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar2)));
    }

    public static boolean h(InterfaceC0012b interfaceC0012b, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) oVar).z();
        }
        if (oVar != null && oVar.p(interfaceC0012b)) {
            return true;
        }
        return false;
    }

    public static boolean i(o oVar, j$.time.temporal.o oVar2) {
        if (oVar2 instanceof j$.time.temporal.a) {
            if (oVar2 != j$.time.temporal.a.ERA) {
                return false;
            }
            return true;
        }
        if (oVar2 == null || !oVar2.p(oVar)) {
            return false;
        }
        return true;
    }

    public static Object j(InterfaceC0012b interfaceC0012b, j$.time.temporal.q qVar) {
        if (qVar == j$.time.temporal.k.k() || qVar == j$.time.temporal.k.j() || qVar == j$.time.temporal.k.h() || qVar == j$.time.temporal.k.g()) {
            return null;
        }
        if (qVar == j$.time.temporal.k.e()) {
            return interfaceC0012b.a();
        }
        if (qVar == j$.time.temporal.k.i()) {
            return ChronoUnit.DAYS;
        }
        return qVar.a(interfaceC0012b);
    }

    public static Object k(InterfaceC0015e interfaceC0015e, j$.time.temporal.q qVar) {
        if (qVar != j$.time.temporal.k.k() && qVar != j$.time.temporal.k.j() && qVar != j$.time.temporal.k.h()) {
            if (qVar == j$.time.temporal.k.g()) {
                return interfaceC0015e.c();
            }
            if (qVar == j$.time.temporal.k.e()) {
                return interfaceC0015e.a();
            }
            if (qVar == j$.time.temporal.k.i()) {
                return ChronoUnit.NANOS;
            }
            return qVar.a(interfaceC0015e);
        }
        return null;
    }

    public static Object l(InterfaceC0021k interfaceC0021k, j$.time.temporal.q qVar) {
        return (qVar == j$.time.temporal.k.j() || qVar == j$.time.temporal.k.k()) ? interfaceC0021k.w() : qVar == j$.time.temporal.k.h() ? interfaceC0021k.i() : qVar == j$.time.temporal.k.g() ? interfaceC0021k.c() : qVar == j$.time.temporal.k.e() ? interfaceC0021k.a() : qVar == j$.time.temporal.k.i() ? ChronoUnit.NANOS : qVar.a(interfaceC0021k);
    }

    public static Object m(o oVar, j$.time.temporal.q qVar) {
        if (qVar == j$.time.temporal.k.i()) {
            return ChronoUnit.ERAS;
        }
        return j$.time.temporal.k.c(oVar, qVar);
    }

    public static long n(InterfaceC0015e interfaceC0015e, j$.time.A a) {
        Objects.a(a, "offset");
        return ((interfaceC0015e.d().A() * 86400) + interfaceC0015e.c().Z()) - a.Q();
    }

    public static long o(InterfaceC0021k interfaceC0021k) {
        return ((interfaceC0021k.d().A() * 86400) + interfaceC0021k.c().Z()) - interfaceC0021k.i().Q();
    }

    public static n p(j$.time.temporal.l lVar) {
        Objects.a(lVar, "temporal");
        n nVar = (n) lVar.D(j$.time.temporal.k.e());
        u uVar = u.d;
        if (nVar == null) {
            Objects.a(uVar, "defaultObj");
            return uVar;
        }
        return nVar;
    }
}
