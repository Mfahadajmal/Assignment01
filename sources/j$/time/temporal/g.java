package j$.time.temporal;

import j$.time.Duration;

/* loaded from: classes2.dex */
enum g implements TemporalUnit {
    WEEK_BASED_YEARS("WeekBasedYears", Duration.ofSeconds(31556952)),
    QUARTER_YEARS("QuarterYears", Duration.ofSeconds(7889238));

    private final String a;
    private final Duration b;

    g(String str, Duration duration) {
        this.a = str;
        this.b = duration;
    }

    @Override // j$.time.temporal.TemporalUnit
    public final Duration h() {
        return this.b;
    }

    @Override // j$.time.temporal.TemporalUnit
    public final long n(Temporal temporal, Temporal temporal2) {
        if (temporal.getClass() != temporal2.getClass()) {
            return temporal.b(temporal2, this);
        }
        int ordinal = ordinal();
        if (ordinal == 0) {
            o oVar = h.c;
            return j$.nio.file.attribute.a.g(temporal2.z(oVar), temporal.z(oVar));
        }
        if (ordinal == 1) {
            return temporal.b(temporal2, ChronoUnit.MONTHS) / 3;
        }
        throw new IllegalStateException("Unreachable");
    }

    @Override // j$.time.temporal.TemporalUnit
    public final Temporal p(Temporal temporal, long j) {
        int ordinal = ordinal();
        if (ordinal == 0) {
            return temporal.e(j$.nio.file.attribute.a.e(temporal.p(r0), j), h.c);
        }
        if (ordinal == 1) {
            return temporal.g(j / 4, ChronoUnit.YEARS).g((j % 4) * 3, ChronoUnit.MONTHS);
        }
        throw new IllegalStateException("Unreachable");
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.a;
    }
}
