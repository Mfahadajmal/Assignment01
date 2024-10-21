package com.google.android.libraries.performance.primes.metrics.battery;

import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.common.base.Splitter;
import com.google.common.flogger.GoogleLogger;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.regex.Pattern;
import logs.proto.wireless.performance.mobile.BatteryMetric$HashedString;
import logs.proto.wireless.performance.mobile.BatteryMetric$Timer;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HashingNameSanitizer {
    private static final Splitter SLASH_SPLITTER = Splitter.on('/');
    private static final Pattern SYSTEM_TASK_PATTERN = Pattern.compile("^(\\*[a-z]+\\*).*");
    final ConcurrentHashMap hashHashMap = new ConcurrentHashMap();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    enum NameType {
        WAKELOCK,
        SYNC,
        JOB,
        PROCESS,
        SENSOR
    }

    static String sanitizeSyncName(String str) {
        List splitToList = SLASH_SPLITTER.splitToList(str);
        if (splitToList.size() != 3) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/battery/HashingNameSanitizer", "sanitizeSyncName", 56, "HashingNameSanitizer.java")).log("malformed sync name: %s", str);
            return "MALFORMED";
        }
        return (String) splitToList.get(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final logs.proto.wireless.performance.mobile.BatteryMetric$Timer hashRawTimerName(com.google.android.libraries.performance.primes.metrics.battery.HashingNameSanitizer.NameType r14, logs.proto.wireless.performance.mobile.BatteryMetric$Timer r15) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.performance.primes.metrics.battery.HashingNameSanitizer.hashRawTimerName(com.google.android.libraries.performance.primes.metrics.battery.HashingNameSanitizer$NameType, logs.proto.wireless.performance.mobile.BatteryMetric$Timer):logs.proto.wireless.performance.mobile.BatteryMetric$Timer");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final BatteryMetric$Timer sanitizeHashedTimerName$ar$ds(BatteryMetric$Timer batteryMetric$Timer) {
        BatteryMetric$HashedString batteryMetric$HashedString = batteryMetric$Timer.name_;
        if (batteryMetric$HashedString == null) {
            batteryMetric$HashedString = BatteryMetric$HashedString.DEFAULT_INSTANCE;
        }
        if ((batteryMetric$HashedString.bitField0_ & 1) != 0) {
            BatteryMetric$HashedString batteryMetric$HashedString2 = batteryMetric$Timer.name_;
            if (batteryMetric$HashedString2 == null) {
                batteryMetric$HashedString2 = BatteryMetric$HashedString.DEFAULT_INSTANCE;
            }
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) batteryMetric$HashedString2.toBuilder();
            Long l = (Long) this.hashHashMap.get(Long.valueOf(((BatteryMetric$HashedString) builder.instance).hash_));
            l.getClass();
            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) batteryMetric$Timer.toBuilder();
            long longValue = l.longValue();
            builder.copyOnWrite();
            BatteryMetric$HashedString batteryMetric$HashedString3 = (BatteryMetric$HashedString) builder.instance;
            batteryMetric$HashedString3.bitField0_ |= 1;
            batteryMetric$HashedString3.hash_ = longValue;
            builder2.copyOnWrite();
            BatteryMetric$Timer batteryMetric$Timer2 = (BatteryMetric$Timer) builder2.instance;
            BatteryMetric$HashedString batteryMetric$HashedString4 = (BatteryMetric$HashedString) builder.build();
            batteryMetric$HashedString4.getClass();
            batteryMetric$Timer2.name_ = batteryMetric$HashedString4;
            batteryMetric$Timer2.bitField0_ |= 4;
            return (BatteryMetric$Timer) builder2.build();
        }
        return batteryMetric$Timer;
    }
}
