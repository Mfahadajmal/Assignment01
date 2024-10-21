package j$.time.temporal;

import j$.time.Duration;

/* loaded from: classes2.dex */
public enum ChronoUnit implements TemporalUnit {
    NANOS("Nanos", Duration.p(1)),
    MICROS("Micros", Duration.p(1000)),
    MILLIS("Millis", Duration.p(1000000)),
    SECONDS("Seconds", Duration.ofSeconds(1)),
    MINUTES("Minutes", Duration.ofSeconds(60)),
    HOURS("Hours", Duration.ofSeconds(3600)),
    HALF_DAYS("HalfDays", Duration.ofSeconds(43200)),
    DAYS("Days", Duration.ofSeconds(86400)),
    WEEKS("Weeks", Duration.ofSeconds(604800)),
    MONTHS("Months", Duration.ofSeconds(2629746)),
    YEARS("Years", Duration.ofSeconds(31556952)),
    DECADES("Decades", Duration.ofSeconds(315569520)),
    CENTURIES("Centuries", Duration.ofSeconds(3155695200L)),
    MILLENNIA("Millennia", Duration.ofSeconds(31556952000L)),
    ERAS("Eras", Duration.ofSeconds(31556952000000000L)),
    FOREVER("Forever", Duration.r(Long.MAX_VALUE, 999999999));

    private final String a;
    private final Duration b;

    ChronoUnit(String str, Duration duration) {
        this.a = str;
        this.b = duration;
    }

    @Override // j$.time.temporal.TemporalUnit
    public final Duration h() {
        return this.b;
    }

    @Override // j$.time.temporal.TemporalUnit
    public final long n(Temporal temporal, Temporal temporal2) {
        return temporal.b(temporal2, this);
    }

    @Override // j$.time.temporal.TemporalUnit
    public final Temporal p(Temporal temporal, long j) {
        return temporal.g(j, this);
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.a;
    }
}
