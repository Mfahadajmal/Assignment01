package kotlin.time;

import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class DurationUnit {
    private static final /* synthetic */ DurationUnit[] $VALUES;
    public static final DurationUnit DAYS;
    public static final DurationUnit HOURS;
    public static final DurationUnit MICROSECONDS;
    public static final DurationUnit MILLISECONDS;
    public static final DurationUnit MINUTES;
    public static final DurationUnit NANOSECONDS;
    public static final DurationUnit SECONDS;
    public final TimeUnit timeUnit;

    static {
        DurationUnit durationUnit = new DurationUnit("NANOSECONDS", 0, TimeUnit.NANOSECONDS);
        NANOSECONDS = durationUnit;
        DurationUnit durationUnit2 = new DurationUnit("MICROSECONDS", 1, TimeUnit.MICROSECONDS);
        MICROSECONDS = durationUnit2;
        DurationUnit durationUnit3 = new DurationUnit("MILLISECONDS", 2, TimeUnit.MILLISECONDS);
        MILLISECONDS = durationUnit3;
        DurationUnit durationUnit4 = new DurationUnit("SECONDS", 3, TimeUnit.SECONDS);
        SECONDS = durationUnit4;
        DurationUnit durationUnit5 = new DurationUnit("MINUTES", 4, TimeUnit.MINUTES);
        MINUTES = durationUnit5;
        DurationUnit durationUnit6 = new DurationUnit("HOURS", 5, TimeUnit.HOURS);
        HOURS = durationUnit6;
        DurationUnit durationUnit7 = new DurationUnit("DAYS", 6, TimeUnit.DAYS);
        DAYS = durationUnit7;
        DurationUnit[] durationUnitArr = {durationUnit, durationUnit2, durationUnit3, durationUnit4, durationUnit5, durationUnit6, durationUnit7};
        $VALUES = durationUnitArr;
        OnDevicePoseDetectionLogEvent.enumEntries$ar$class_merging(durationUnitArr);
    }

    private DurationUnit(String str, int i, TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public static DurationUnit[] values() {
        return (DurationUnit[]) $VALUES.clone();
    }
}
