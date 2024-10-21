package j$.time;

import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* renamed from: j$.time.d, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class EnumC0023d implements j$.time.temporal.l, j$.time.temporal.m {
    public static final EnumC0023d FRIDAY;
    public static final EnumC0023d MONDAY;
    public static final EnumC0023d SATURDAY;
    public static final EnumC0023d SUNDAY;
    public static final EnumC0023d THURSDAY;
    public static final EnumC0023d TUESDAY;
    public static final EnumC0023d WEDNESDAY;
    private static final EnumC0023d[] a;
    private static final /* synthetic */ EnumC0023d[] b;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.Enum, j$.time.d] */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.lang.Enum, j$.time.d] */
    /* JADX WARN: Type inference failed for: r12v1, types: [java.lang.Enum, j$.time.d] */
    /* JADX WARN: Type inference failed for: r13v1, types: [java.lang.Enum, j$.time.d] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.Enum, j$.time.d] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.Enum, j$.time.d] */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.lang.Enum, j$.time.d] */
    static {
        ?? r7 = new Enum("MONDAY", 0);
        MONDAY = r7;
        ?? r8 = new Enum("TUESDAY", 1);
        TUESDAY = r8;
        ?? r9 = new Enum("WEDNESDAY", 2);
        WEDNESDAY = r9;
        ?? r10 = new Enum("THURSDAY", 3);
        THURSDAY = r10;
        ?? r11 = new Enum("FRIDAY", 4);
        FRIDAY = r11;
        ?? r12 = new Enum("SATURDAY", 5);
        SATURDAY = r12;
        ?? r13 = new Enum("SUNDAY", 6);
        SUNDAY = r13;
        b = new EnumC0023d[]{r7, r8, r9, r10, r11, r12, r13};
        a = values();
    }

    public static EnumC0023d I(int i) {
        if (i >= 1 && i <= 7) {
            return a[i - 1];
        }
        throw new RuntimeException("Invalid value for DayOfWeek: " + i);
    }

    public static EnumC0023d valueOf(String str) {
        return (EnumC0023d) Enum.valueOf(EnumC0023d.class, str);
    }

    public static EnumC0023d[] values() {
        return (EnumC0023d[]) b.clone();
    }

    @Override // j$.time.temporal.l
    public final Object D(j$.time.temporal.q qVar) {
        return qVar == j$.time.temporal.k.i() ? ChronoUnit.DAYS : j$.time.temporal.k.c(this, qVar);
    }

    @Override // j$.time.temporal.l
    public final boolean f(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? oVar == j$.time.temporal.a.DAY_OF_WEEK : oVar != null && oVar.p(this);
    }

    public final int getValue() {
        return ordinal() + 1;
    }

    @Override // j$.time.temporal.m
    public final Temporal h(Temporal temporal) {
        return temporal.e(getValue(), j$.time.temporal.a.DAY_OF_WEEK);
    }

    @Override // j$.time.temporal.l
    public final int p(j$.time.temporal.o oVar) {
        return oVar == j$.time.temporal.a.DAY_OF_WEEK ? getValue() : j$.time.temporal.k.a(this, oVar);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        return oVar == j$.time.temporal.a.DAY_OF_WEEK ? oVar.n() : j$.time.temporal.k.d(this, oVar);
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        if (oVar == j$.time.temporal.a.DAY_OF_WEEK) {
            return getValue();
        }
        if (!(oVar instanceof j$.time.temporal.a)) {
            return oVar.h(this);
        }
        throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
    }
}
