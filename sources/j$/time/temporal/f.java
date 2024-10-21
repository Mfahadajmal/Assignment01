package j$.time.temporal;

import j$.time.EnumC0023d;
import j$.time.chrono.AbstractC0011a;
import j$.time.chrono.AbstractC0019i;
import j$.time.chrono.u;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
abstract class f implements o {
    public static final f DAY_OF_QUARTER;
    public static final f QUARTER_OF_YEAR;
    public static final f WEEK_BASED_YEAR;
    public static final f WEEK_OF_WEEK_BASED_YEAR;
    private static final int[] a;
    private static final /* synthetic */ f[] b;

    static {
        f fVar = new f() { // from class: j$.time.temporal.b
            @Override // j$.time.temporal.o
            public final long h(l lVar) {
                int[] iArr;
                int i;
                if (p(lVar)) {
                    int p = lVar.p(a.DAY_OF_YEAR);
                    int p2 = lVar.p(a.MONTH_OF_YEAR);
                    long z = lVar.z(a.YEAR);
                    iArr = f.a;
                    int i2 = (p2 - 1) / 3;
                    u.d.getClass();
                    if (u.p(z)) {
                        i = 4;
                    } else {
                        i = 0;
                    }
                    return p - iArr[i2 + i];
                }
                throw new RuntimeException("Unsupported field: DayOfQuarter");
            }

            @Override // j$.time.temporal.o
            public final s n() {
                return s.k(90L, 92L);
            }

            @Override // j$.time.temporal.o
            public final boolean p(l lVar) {
                if (lVar.f(a.DAY_OF_YEAR) && lVar.f(a.MONTH_OF_YEAR) && lVar.f(a.YEAR)) {
                    o oVar = h.a;
                    if (((AbstractC0011a) AbstractC0019i.p(lVar)).equals(u.d)) {
                        return true;
                    }
                }
                return false;
            }

            @Override // j$.time.temporal.o
            public final Temporal r(Temporal temporal, long j) {
                long h = h(temporal);
                n().b(j, this);
                a aVar = a.DAY_OF_YEAR;
                return temporal.e((j - h) + temporal.z(aVar), aVar);
            }

            @Override // j$.time.temporal.o
            public final s s(l lVar) {
                if (p(lVar)) {
                    long z = lVar.z(f.QUARTER_OF_YEAR);
                    if (z == 1) {
                        long z2 = lVar.z(a.YEAR);
                        u.d.getClass();
                        if (u.p(z2)) {
                            return s.j(1L, 91L);
                        }
                        return s.j(1L, 90L);
                    }
                    if (z == 2) {
                        return s.j(1L, 91L);
                    }
                    if (z != 3 && z != 4) {
                        return n();
                    }
                    return s.j(1L, 92L);
                }
                throw new RuntimeException("Unsupported field: DayOfQuarter");
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "DayOfQuarter";
            }
        };
        DAY_OF_QUARTER = fVar;
        f fVar2 = new f() { // from class: j$.time.temporal.c
            @Override // j$.time.temporal.o
            public final long h(l lVar) {
                if (p(lVar)) {
                    return (lVar.z(a.MONTH_OF_YEAR) + 2) / 3;
                }
                throw new RuntimeException("Unsupported field: QuarterOfYear");
            }

            @Override // j$.time.temporal.o
            public final s n() {
                return s.j(1L, 4L);
            }

            @Override // j$.time.temporal.o
            public final boolean p(l lVar) {
                if (lVar.f(a.MONTH_OF_YEAR)) {
                    o oVar = h.a;
                    if (((AbstractC0011a) AbstractC0019i.p(lVar)).equals(u.d)) {
                        return true;
                    }
                }
                return false;
            }

            @Override // j$.time.temporal.o
            public final Temporal r(Temporal temporal, long j) {
                long h = h(temporal);
                n().b(j, this);
                a aVar = a.MONTH_OF_YEAR;
                return temporal.e(((j - h) * 3) + temporal.z(aVar), aVar);
            }

            @Override // j$.time.temporal.o
            public final s s(l lVar) {
                if (p(lVar)) {
                    return n();
                }
                throw new RuntimeException("Unsupported field: QuarterOfYear");
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "QuarterOfYear";
            }
        };
        QUARTER_OF_YEAR = fVar2;
        f fVar3 = new f() { // from class: j$.time.temporal.d
            @Override // j$.time.temporal.o
            public final long h(l lVar) {
                if (p(lVar)) {
                    return f.I(j$.time.g.K(lVar));
                }
                throw new RuntimeException("Unsupported field: WeekOfWeekBasedYear");
            }

            @Override // j$.time.temporal.o
            public final s n() {
                return s.k(52L, 53L);
            }

            @Override // j$.time.temporal.o
            public final boolean p(l lVar) {
                if (lVar.f(a.EPOCH_DAY)) {
                    o oVar = h.a;
                    if (((AbstractC0011a) AbstractC0019i.p(lVar)).equals(u.d)) {
                        return true;
                    }
                }
                return false;
            }

            @Override // j$.time.temporal.o
            public final Temporal r(Temporal temporal, long j) {
                n().b(j, this);
                return temporal.g(j$.nio.file.attribute.a.g(j, h(temporal)), ChronoUnit.WEEKS);
            }

            @Override // j$.time.temporal.o
            public final s s(l lVar) {
                if (p(lVar)) {
                    return f.L(j$.time.g.K(lVar));
                }
                throw new RuntimeException("Unsupported field: WeekOfWeekBasedYear");
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "WeekOfWeekBasedYear";
            }
        };
        WEEK_OF_WEEK_BASED_YEAR = fVar3;
        f fVar4 = new f() { // from class: j$.time.temporal.e
            @Override // j$.time.temporal.o
            public final long h(l lVar) {
                int M;
                if (p(lVar)) {
                    M = f.M(j$.time.g.K(lVar));
                    return M;
                }
                throw new RuntimeException("Unsupported field: WeekBasedYear");
            }

            @Override // j$.time.temporal.o
            public final s n() {
                return a.YEAR.n();
            }

            @Override // j$.time.temporal.o
            public final boolean p(l lVar) {
                if (lVar.f(a.EPOCH_DAY)) {
                    o oVar = h.a;
                    if (((AbstractC0011a) AbstractC0019i.p(lVar)).equals(u.d)) {
                        return true;
                    }
                }
                return false;
            }

            @Override // j$.time.temporal.o
            public final Temporal r(Temporal temporal, long j) {
                int N;
                if (p(temporal)) {
                    int a2 = a.YEAR.n().a(j, f.WEEK_BASED_YEAR);
                    j$.time.g K = j$.time.g.K(temporal);
                    int p = K.p(a.DAY_OF_WEEK);
                    int I = f.I(K);
                    if (I == 53) {
                        N = f.N(a2);
                        if (N == 52) {
                            I = 52;
                        }
                    }
                    return temporal.r(j$.time.g.V(a2, 1, 4).Z(((I - 1) * 7) + (p - r6.p(r0))));
                }
                throw new RuntimeException("Unsupported field: WeekBasedYear");
            }

            @Override // j$.time.temporal.o
            public final s s(l lVar) {
                if (p(lVar)) {
                    return n();
                }
                throw new RuntimeException("Unsupported field: WeekBasedYear");
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "WeekBasedYear";
            }
        };
        WEEK_BASED_YEAR = fVar4;
        b = new f[]{fVar, fVar2, fVar3, fVar4};
        a = new int[]{0, 90, 181, 273, 0, 91, 182, 274};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int I(j$.time.g gVar) {
        int ordinal = gVar.M().ordinal();
        int i = 1;
        int N = gVar.N() - 1;
        int i2 = (3 - ordinal) + N;
        int i3 = i2 - ((i2 / 7) * 7);
        int i4 = i3 - 3;
        if (i4 < -3) {
            i4 = i3 + 4;
        }
        if (N < i4) {
            return (int) s.j(1L, N(M(gVar.f0(180).b0(-1L)))).d();
        }
        int i5 = ((N - i4) / 7) + 1;
        if (i5 != 53 || i4 == -3 || (i4 == -2 && gVar.S())) {
            i = i5;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static s L(j$.time.g gVar) {
        return s.j(1L, N(M(gVar)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int M(j$.time.g gVar) {
        int Q = gVar.Q();
        int N = gVar.N();
        if (N <= 3) {
            return N - gVar.M().ordinal() < -2 ? Q - 1 : Q;
        }
        if (N >= 363) {
            return ((N - 363) - (gVar.S() ? 1 : 0)) - gVar.M().ordinal() >= 0 ? Q + 1 : Q;
        }
        return Q;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int N(int i) {
        j$.time.g V = j$.time.g.V(i, 1, 1);
        if (V.M() != EnumC0023d.THURSDAY) {
            return (V.M() == EnumC0023d.WEDNESDAY && V.S()) ? 53 : 52;
        }
        return 53;
    }

    public static f valueOf(String str) {
        return (f) Enum.valueOf(f.class, str);
    }

    public static f[] values() {
        return (f[]) b.clone();
    }

    @Override // j$.time.temporal.o
    public final boolean z() {
        return true;
    }
}
