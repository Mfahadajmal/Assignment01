package j$.time.chrono;

import j$.time.temporal.Temporal;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class t implements o {
    public static final t AH;
    private static final /* synthetic */ t[] a;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Enum, j$.time.chrono.t] */
    static {
        ?? r1 = new Enum("AH", 0);
        AH = r1;
        a = new t[]{r1};
    }

    public static t valueOf(String str) {
        return (t) Enum.valueOf(t.class, str);
    }

    public static t[] values() {
        return (t[]) a.clone();
    }

    @Override // j$.time.temporal.l
    public final /* synthetic */ Object D(j$.time.temporal.q qVar) {
        return AbstractC0019i.m(this, qVar);
    }

    @Override // j$.time.temporal.l
    public final /* synthetic */ boolean f(j$.time.temporal.o oVar) {
        return AbstractC0019i.i(this, oVar);
    }

    @Override // j$.time.chrono.o
    public final int getValue() {
        return 1;
    }

    @Override // j$.time.temporal.m
    public final Temporal h(Temporal temporal) {
        return temporal.e(1, j$.time.temporal.a.ERA);
    }

    @Override // j$.time.temporal.l
    public final /* synthetic */ int p(j$.time.temporal.o oVar) {
        return AbstractC0019i.f(this, (j$.time.temporal.a) oVar);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        if (oVar == j$.time.temporal.a.ERA) {
            return j$.time.temporal.s.j(1L, 1L);
        }
        return j$.time.temporal.k.d(this, oVar);
    }

    @Override // j$.time.temporal.l
    public final /* synthetic */ long z(j$.time.temporal.o oVar) {
        return AbstractC0019i.g(this, oVar);
    }
}
