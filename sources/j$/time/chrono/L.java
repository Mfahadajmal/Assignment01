package j$.time.chrono;

import j$.time.temporal.Temporal;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class L implements o {
    public static final L BE;
    public static final L BEFORE_BE;
    private static final /* synthetic */ L[] a;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Enum, j$.time.chrono.L] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Enum, j$.time.chrono.L] */
    static {
        ?? r2 = new Enum("BEFORE_BE", 0);
        BEFORE_BE = r2;
        ?? r3 = new Enum("BE", 1);
        BE = r3;
        a = new L[]{r2, r3};
    }

    public static L valueOf(String str) {
        return (L) Enum.valueOf(L.class, str);
    }

    public static L[] values() {
        return (L[]) a.clone();
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
        return ordinal();
    }

    @Override // j$.time.temporal.m
    public final Temporal h(Temporal temporal) {
        return temporal.e(getValue(), j$.time.temporal.a.ERA);
    }

    @Override // j$.time.temporal.l
    public final /* synthetic */ int p(j$.time.temporal.o oVar) {
        return AbstractC0019i.f(this, (j$.time.temporal.a) oVar);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        return j$.time.temporal.k.d(this, oVar);
    }

    @Override // j$.time.temporal.l
    public final /* synthetic */ long z(j$.time.temporal.o oVar) {
        return AbstractC0019i.g(this, oVar);
    }
}