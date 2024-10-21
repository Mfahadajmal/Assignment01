package com.google.android.libraries.performance.primes.metrics.memory;

import android.content.Context;
import android.os.Debug;
import com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda9;
import com.google.android.libraries.performance.primes.ConfigurationsModule$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.search.mdi.aratea.proto.FeatureName;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryUsageCapture {
    public static final /* synthetic */ int MemoryUsageCapture$ar$NoOp = 0;
    private static Supplier otherPssGetter = ContextDataProvider.memoize(new PrimesController$$ExternalSyntheticLambda9(11));
    public final Context appContext;
    public final Provider configsProvider;
    public final OptionalMethod foregroundStateCapture$ar$class_merging$ar$class_merging;
    public final ProcessStatsCapture processStatsCapture;
    public final Provider readCorrectProcStatusProvider;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ProcStatus {
        Long anonRssKb;
        Long rssHwmKb;
        Long swapKb;
        Long totalRssKb;
        Long vmSizeKb;
        static final Pattern PROCFS_RSS_HIGH_WATERMARK_IN_KILOBYTES = Pattern.compile("VmHWM:\\s*(\\d+)\\s*kB");
        static final Pattern PROCFS_RSS_IN_KILOBYTES = Pattern.compile("VmRSS:\\s*(\\d+)\\s*kB");
        static final Pattern PROCFS_ANON_RSS_IN_KILOBYTES = Pattern.compile("RssAnon:\\s*(\\d+)\\s*kB");
        static final Pattern PROCFS_SWAP_IN_KILOBYTES = Pattern.compile("VmSwap:\\s*(\\d+)\\s*kB");
        static final Pattern PROCFS_VM_SIZE_IN_KILOBYTES = Pattern.compile("VmSize:\\s*(\\d+)\\s*kB");
    }

    public MemoryUsageCapture(final Provider provider, Context context, Provider provider2, final Provider provider3, ProcessStatsCapture processStatsCapture, OptionalMethod optionalMethod) {
        this.processStatsCapture = processStatsCapture;
        this.foregroundStateCapture$ar$class_merging$ar$class_merging = optionalMethod;
        provider.getClass();
        final Supplier memoize = ContextDataProvider.memoize(new ConfigurationsModule$$ExternalSyntheticLambda0(provider, 8));
        this.configsProvider = new Provider() { // from class: com.google.android.libraries.performance.primes.metrics.memory.MemoryUsageCapture$$ExternalSyntheticLambda1
            @Override // javax.inject.Provider
            public final Object get() {
                Object obj;
                int i = MemoryUsageCapture.MemoryUsageCapture$ar$NoOp;
                if (((Boolean) Provider.this.get()).booleanValue()) {
                    obj = memoize.get();
                } else {
                    obj = provider.get();
                }
                return (MemoryConfigurations) obj;
            }
        };
        this.appContext = context;
        this.readCorrectProcStatusProvider = provider2;
    }

    public static /* synthetic */ Optional lambda$static$0() {
        try {
            return Optional.of(Debug.MemoryInfo.class.getDeclaredMethod("getOtherPss", Integer.TYPE));
        } catch (Error e) {
            e = e;
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/memory/MemoryUsageCapture", "lambda$static$0", ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_FINGERPRINT_GESTURE$ar$edu, "MemoryUsageCapture.java")).log("MemoryInfo.getOtherPss(which) failure");
            return Absent.INSTANCE;
        } catch (NoSuchMethodException e2) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withCause(e2)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/memory/MemoryUsageCapture", "lambda$static$0", FeatureName.AI_WALLPAPER_SAMSUNG$ar$edu, "MemoryUsageCapture.java")).log("MemoryInfo.getOtherPss(which) not found");
            return Absent.INSTANCE;
        } catch (Exception e3) {
            e = e3;
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/memory/MemoryUsageCapture", "lambda$static$0", ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_FINGERPRINT_GESTURE$ar$edu, "MemoryUsageCapture.java")).log("MemoryInfo.getOtherPss(which) failure");
            return Absent.INSTANCE;
        }
    }

    public static Long tryParseLong(Pattern pattern, String str) {
        Matcher matcher = pattern.matcher(str);
        try {
            if (!matcher.find()) {
                return null;
            }
            return Long.valueOf(Long.parseLong((String) ContextDataProvider.verifyNotNull(matcher.group(1))));
        } catch (NumberFormatException unused) {
            return null;
        }
    }
}
