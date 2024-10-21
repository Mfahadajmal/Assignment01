package com.google.android.libraries.performance.primes.flogger;

import com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService;
import logs.proto.wireless.performance.mobile.SystemHealthProto$DebugLogs;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface RecentLogs {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Snapshot {
        public final SystemHealthProto$DebugLogs debugLogs;

        public Snapshot() {
        }

        public final SystemHealthProto$DebugLogs debugLogs() {
            return this.debugLogs;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Snapshot) {
                return this.debugLogs.equals(((Snapshot) obj).debugLogs());
            }
            return false;
        }

        public final int hashCode() {
            return this.debugLogs.hashCode() ^ 1000003;
        }

        public final String toString() {
            return "Snapshot{debugLogs=" + this.debugLogs.toString() + "}";
        }

        public Snapshot(SystemHealthProto$DebugLogs systemHealthProto$DebugLogs) {
            this();
            if (systemHealthProto$DebugLogs == null) {
                throw new NullPointerException("Null debugLogs");
            }
            this.debugLogs = systemHealthProto$DebugLogs;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TimestampCollection {
        public static final TimestampCollection EMPTY = new TimestampCollection(new BatteryMetricService[0]);
        final BatteryMetricService[] buffers$ar$class_merging$ar$class_merging$ar$class_merging;
        final int[] timestamps = new int[0];

        public TimestampCollection(BatteryMetricService[] batteryMetricServiceArr) {
            this.buffers$ar$class_merging$ar$class_merging$ar$class_merging = batteryMetricServiceArr;
        }
    }

    TimestampCollection getTimestamp();
}
