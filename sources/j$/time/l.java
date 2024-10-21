package j$.time;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import j$.time.chrono.AbstractC0011a;
import j$.time.chrono.AbstractC0019i;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class l implements j$.time.temporal.l, j$.time.temporal.m {
    public static final l APRIL;
    public static final l AUGUST;
    public static final l DECEMBER;
    public static final l FEBRUARY;
    public static final l JANUARY;
    public static final l JULY;
    public static final l JUNE;
    public static final l MARCH;
    public static final l MAY;
    public static final l NOVEMBER;
    public static final l OCTOBER;
    public static final l SEPTEMBER;
    private static final l[] a;
    private static final /* synthetic */ l[] b;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.Enum, j$.time.l] */
    /* JADX WARN: Type inference failed for: r13v1, types: [java.lang.Enum, j$.time.l] */
    /* JADX WARN: Type inference failed for: r14v1, types: [java.lang.Enum, j$.time.l] */
    /* JADX WARN: Type inference failed for: r15v1, types: [java.lang.Enum, j$.time.l] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Enum, j$.time.l] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Enum, j$.time.l] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Enum, j$.time.l] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.Enum, j$.time.l] */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.Enum, j$.time.l] */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.lang.Enum, j$.time.l] */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.lang.Enum, j$.time.l] */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.lang.Enum, j$.time.l] */
    static {
        ?? r12 = new Enum("JANUARY", 0);
        JANUARY = r12;
        ?? r13 = new Enum("FEBRUARY", 1);
        FEBRUARY = r13;
        ?? r14 = new Enum("MARCH", 2);
        MARCH = r14;
        ?? r15 = new Enum("APRIL", 3);
        APRIL = r15;
        ?? r9 = new Enum("MAY", 4);
        MAY = r9;
        ?? r8 = new Enum("JUNE", 5);
        JUNE = r8;
        ?? r7 = new Enum("JULY", 6);
        JULY = r7;
        ?? r6 = new Enum("AUGUST", 7);
        AUGUST = r6;
        ?? r5 = new Enum("SEPTEMBER", 8);
        SEPTEMBER = r5;
        ?? r4 = new Enum("OCTOBER", 9);
        OCTOBER = r4;
        ?? r3 = new Enum("NOVEMBER", 10);
        NOVEMBER = r3;
        ?? r2 = new Enum("DECEMBER", 11);
        DECEMBER = r2;
        b = new l[]{r12, r13, r14, r15, r9, r8, r7, r6, r5, r4, r3, r2};
        a = values();
    }

    public static l L(int i) {
        if (i >= 1 && i <= 12) {
            return a[i - 1];
        }
        throw new RuntimeException("Invalid value for MonthOfYear: " + i);
    }

    public static l valueOf(String str) {
        return (l) Enum.valueOf(l.class, str);
    }

    public static l[] values() {
        return (l[]) b.clone();
    }

    @Override // j$.time.temporal.l
    public final Object D(j$.time.temporal.q qVar) {
        return qVar == j$.time.temporal.k.e() ? j$.time.chrono.u.d : qVar == j$.time.temporal.k.i() ? ChronoUnit.MONTHS : j$.time.temporal.k.c(this, qVar);
    }

    public final int I(boolean z) {
        switch (ordinal()) {
            case 0:
                return 1;
            case 1:
                return 32;
            case 2:
                return (z ? 1 : 0) + 60;
            case 3:
                return (z ? 1 : 0) + 91;
            case 4:
                return (z ? 1 : 0) + BrailleInputEvent.CMD_BRAILLE_DISPLAY_SETTINGS;
            case 5:
                return (z ? 1 : 0) + 152;
            case 6:
                return (z ? 1 : 0) + 182;
            case 7:
                return (z ? 1 : 0) + 213;
            case 8:
                return (z ? 1 : 0) + 244;
            case 9:
                return (z ? 1 : 0) + 274;
            case 10:
                return (z ? 1 : 0) + 305;
            default:
                return (z ? 1 : 0) + 335;
        }
    }

    public final int J(boolean z) {
        int ordinal = ordinal();
        return ordinal != 1 ? (ordinal == 3 || ordinal == 5 || ordinal == 8 || ordinal == 10) ? 30 : 31 : z ? 29 : 28;
    }

    public final int K() {
        int ordinal = ordinal();
        if (ordinal != 1) {
            return (ordinal == 3 || ordinal == 5 || ordinal == 8 || ordinal == 10) ? 30 : 31;
        }
        return 29;
    }

    public final l M() {
        return a[((((int) 1) + 12) + ordinal()) % 12];
    }

    @Override // j$.time.temporal.l
    public final boolean f(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? oVar == j$.time.temporal.a.MONTH_OF_YEAR : oVar != null && oVar.p(this);
    }

    public final int getValue() {
        return ordinal() + 1;
    }

    @Override // j$.time.temporal.m
    public final Temporal h(Temporal temporal) {
        if (((AbstractC0011a) AbstractC0019i.p(temporal)).equals(j$.time.chrono.u.d)) {
            return temporal.e(getValue(), j$.time.temporal.a.MONTH_OF_YEAR);
        }
        throw new RuntimeException("Adjustment only supported on ISO date-time");
    }

    @Override // j$.time.temporal.l
    public final int p(j$.time.temporal.o oVar) {
        return oVar == j$.time.temporal.a.MONTH_OF_YEAR ? getValue() : j$.time.temporal.k.a(this, oVar);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        return oVar == j$.time.temporal.a.MONTH_OF_YEAR ? oVar.n() : j$.time.temporal.k.d(this, oVar);
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        if (oVar == j$.time.temporal.a.MONTH_OF_YEAR) {
            return getValue();
        }
        if (!(oVar instanceof j$.time.temporal.a)) {
            return oVar.h(this);
        }
        throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
    }
}
