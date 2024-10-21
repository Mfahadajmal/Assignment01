package j$.time.temporal;

import j$.util.Objects;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class k {
    static final q a = new p(0);
    static final q b = new p(1);
    static final q c = new p(2);
    static final q d = new p(3);
    static final q e = new p(4);
    static final q f = new p(5);
    static final q g = new p(6);

    public static int a(l lVar, o oVar) {
        s s = lVar.s(oVar);
        if (s.h()) {
            long z = lVar.z(oVar);
            if (s.i(z)) {
                return (int) z;
            }
            throw new RuntimeException("Invalid value for " + String.valueOf(oVar) + " (valid values " + String.valueOf(s) + "): " + z);
        }
        throw new RuntimeException("Invalid field " + String.valueOf(oVar) + " for get() method, use getLong() instead");
    }

    public static Temporal b(Temporal temporal, long j, TemporalUnit temporalUnit) {
        long j2;
        if (j == Long.MIN_VALUE) {
            temporal = temporal.g(Long.MAX_VALUE, temporalUnit);
            j2 = 1;
        } else {
            j2 = -j;
        }
        return temporal.g(j2, temporalUnit);
    }

    public static Object c(l lVar, q qVar) {
        if (qVar == a || qVar == b || qVar == c) {
            return null;
        }
        return qVar.a(lVar);
    }

    public static s d(l lVar, o oVar) {
        if (oVar instanceof a) {
            if (lVar.f(oVar)) {
                return ((a) oVar).n();
            }
            throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
        }
        Objects.a(oVar, "field");
        return oVar.s(lVar);
    }

    public static q e() {
        return b;
    }

    public static q f() {
        return f;
    }

    public static q g() {
        return g;
    }

    public static q h() {
        return d;
    }

    public static q i() {
        return c;
    }

    public static q j() {
        return e;
    }

    public static q k() {
        return a;
    }
}
