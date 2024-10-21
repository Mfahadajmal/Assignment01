package com.google.common.base;

import _COROUTINE._BOUNDARY;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Stopwatch {
    public boolean isRunning;
    private long startTick;
    private final Ticker ticker;

    /* compiled from: PG */
    /* renamed from: com.google.common.base.Stopwatch$1, reason: invalid class name */
    /* loaded from: classes.dex */
    final /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$util$concurrent$TimeUnit;

        static {
            int[] iArr = new int[TimeUnit.values().length];
            $SwitchMap$java$util$concurrent$TimeUnit = iArr;
            try {
                iArr[TimeUnit.NANOSECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MICROSECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MILLISECONDS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public Stopwatch() {
        this.ticker = Ticker.SYSTEM_TICKER;
    }

    public static Stopwatch createStarted(Ticker ticker) {
        Stopwatch stopwatch = new Stopwatch(ticker);
        stopwatch.start$ar$ds$db96ddcc_0();
        return stopwatch;
    }

    private final long elapsedNanos() {
        if (this.isRunning) {
            return this.ticker.read() - this.startTick;
        }
        return 0L;
    }

    public final long elapsed(TimeUnit timeUnit) {
        return timeUnit.convert(elapsedNanos(), TimeUnit.NANOSECONDS);
    }

    public final void reset$ar$ds() {
        this.isRunning = false;
    }

    public final void start$ar$ds$db96ddcc_0() {
        ContextDataProvider.checkState(!this.isRunning, "This stopwatch is already running.");
        this.isRunning = true;
        this.startTick = this.ticker.read();
    }

    public final String toString() {
        TimeUnit timeUnit;
        String str;
        long elapsedNanos = elapsedNanos();
        if (TimeUnit.DAYS.convert(elapsedNanos, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.DAYS;
        } else if (TimeUnit.HOURS.convert(elapsedNanos, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.HOURS;
        } else if (TimeUnit.MINUTES.convert(elapsedNanos, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.MINUTES;
        } else if (TimeUnit.SECONDS.convert(elapsedNanos, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.SECONDS;
        } else if (TimeUnit.MILLISECONDS.convert(elapsedNanos, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        } else if (TimeUnit.MICROSECONDS.convert(elapsedNanos, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.MICROSECONDS;
        } else {
            timeUnit = TimeUnit.NANOSECONDS;
        }
        String format = String.format(Locale.ROOT, "%.4g", Double.valueOf(elapsedNanos / TimeUnit.NANOSECONDS.convert(1L, timeUnit)));
        switch (AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[timeUnit.ordinal()]) {
            case 1:
                str = "ns";
                break;
            case 2:
                str = "μs";
                break;
            case 3:
                str = "ms";
                break;
            case 4:
                str = "s";
                break;
            case 5:
                str = "min";
                break;
            case 6:
                str = "h";
                break;
            case 7:
                str = "d";
                break;
            default:
                throw new AssertionError();
        }
        return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_6(str, format, " ");
    }

    public Stopwatch(Ticker ticker) {
        ticker.getClass();
        this.ticker = ticker;
    }
}
