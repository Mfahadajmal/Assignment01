package com.google.mlkit.logging.schema;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DurationStats {
    public Object DurationStats$ar$avgMs;
    public Object DurationStats$ar$firstQuartileMs;
    public Object DurationStats$ar$maxMs;
    public Object DurationStats$ar$medianMs;
    public Object DurationStats$ar$minMs;
    public Object DurationStats$ar$thirdQuartileMs;

    public DurationStats() {
    }

    public DurationStats(DurationStats durationStats) {
        this.DurationStats$ar$maxMs = durationStats.DurationStats$ar$maxMs;
        this.DurationStats$ar$minMs = durationStats.DurationStats$ar$minMs;
        this.DurationStats$ar$avgMs = durationStats.DurationStats$ar$avgMs;
        this.DurationStats$ar$firstQuartileMs = durationStats.DurationStats$ar$firstQuartileMs;
        this.DurationStats$ar$medianMs = durationStats.DurationStats$ar$medianMs;
        this.DurationStats$ar$thirdQuartileMs = durationStats.DurationStats$ar$thirdQuartileMs;
    }
}
