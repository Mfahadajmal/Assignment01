package com.google.mlkit.shared.devtools;

import android.os.SystemClock;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.common.sdkinternal.CloseGuard$Factory;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DevTimer implements Closeable {
    public static final Map timers = new HashMap();
    private int counts;
    private long lastRecordUs;
    private long startUs;
    private final String tag;
    private double totalUs;
    private long minUs = 2147483647L;
    private long maxUs = -2147483648L;

    public DevTimer(String str) {
        ContextDataProvider.checkArgument(true, (Object) "resetPeriod should be greater than logPeriod");
        this.tag = str;
    }

    public static long elapsedRealtimeMicros() {
        return SystemClock.elapsedRealtimeNanos() / 1000;
    }

    private final void reset() {
        this.counts = 0;
        this.totalUs = 0.0d;
        this.startUs = 0L;
        this.minUs = 2147483647L;
        this.maxUs = -2147483648L;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        boolean z;
        if (this.startUs != 0) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "Did you forget to call start()?");
        recordWithStartUs(this.startUs);
    }

    public void recordDurationUs(long j) {
        long elapsedRealtimeMicros = elapsedRealtimeMicros();
        long j2 = this.lastRecordUs;
        if (j2 != 0 && elapsedRealtimeMicros - j2 >= 1000000) {
            reset();
        }
        this.lastRecordUs = elapsedRealtimeMicros;
        this.counts++;
        this.totalUs += j;
        this.minUs = Math.min(this.minUs, j);
        this.maxUs = Math.max(this.maxUs, j);
        if (this.counts % 50 == 0) {
            String.format(Locale.US, "[%s] cur=%dus, counts=%d, min=%dus, max=%dus, avg=%dus", this.tag, Long.valueOf(j), Integer.valueOf(this.counts), Long.valueOf(this.minUs), Long.valueOf(this.maxUs), Integer.valueOf((int) (this.totalUs / this.counts)));
            CloseGuard$Factory.getInstance$ar$ds$cb56d710_0();
        }
        if (this.counts % 500 == 0) {
            reset();
        }
    }

    public void recordWithStartUs(long j) {
        recordDurationUs(elapsedRealtimeMicros() - j);
    }

    public DevTimer start() {
        this.startUs = elapsedRealtimeMicros();
        return this;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NoopTimer extends DevTimer {
        public static final NoopTimer SINGLETON = new NoopTimer();

        private NoopTimer() {
            super("unusedTag");
        }

        @Override // com.google.mlkit.shared.devtools.DevTimer, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
        }

        @Override // com.google.mlkit.shared.devtools.DevTimer
        public final /* bridge */ /* synthetic */ DevTimer start() {
            return this;
        }

        @Override // com.google.mlkit.shared.devtools.DevTimer
        public final void recordDurationUs(long j) {
        }

        @Override // com.google.mlkit.shared.devtools.DevTimer
        public final void recordWithStartUs(long j) {
        }
    }
}
