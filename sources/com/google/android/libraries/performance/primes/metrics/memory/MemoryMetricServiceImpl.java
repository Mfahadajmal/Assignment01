package com.google.android.libraries.performance.primes.metrics.memory;

import _COROUTINE._BOUNDARY;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.os.StrictMode;
import com.google.android.accessibility.utils.AccessibilityEventUtils$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.Shutdown;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.crash.CrashMetricServiceImpl$$ExternalSyntheticLambda1;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricMonitor;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryUsageCapture;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.common.base.Optional;
import com.google.common.base.Throwables;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.io.ByteSource$AsCharSource;
import com.google.common.util.concurrent.AbstractCatchingFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.GwtFluentFutureCatchingSpecialization;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.Lazy;
import j$.nio.channels.DesugarChannels;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.MemoryMetric$AndroidMemoryStats;
import logs.proto.wireless.performance.mobile.MemoryMetric$DeviceStats;
import logs.proto.wireless.performance.mobile.MemoryMetric$MemoryStats;
import logs.proto.wireless.performance.mobile.MemoryMetric$MemoryUsageMetric;
import logs.proto.wireless.performance.mobile.ProcessProto$AndroidProcessStats;
import logs.proto.wireless.performance.mobile.ProcessProto$ProcessStats;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryMetricServiceImpl extends MemoryMetricService implements MetricService {
    public final MemoryUsageCapture capture;
    public final Lazy configsProvider;
    private final boolean enableUnifiedInit;
    public final ListeningScheduledExecutorService executorService;
    private final MemoryMetricMonitor metricMonitor;
    public final MetricRecorder metricRecorder;
    public final Shutdown shutdown;

    public MemoryMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, MemoryMetricMonitor memoryMetricMonitor, ListeningScheduledExecutorService listeningScheduledExecutorService, Lazy lazy, MemoryUsageCapture memoryUsageCapture, Shutdown shutdown, Provider provider, Executor executor, Optional optional) {
        new AtomicReference(MemoryEvent.EMPTY_SNAPSHOT);
        new ConcurrentHashMap();
        this.metricMonitor = memoryMetricMonitor;
        this.shutdown = shutdown;
        this.metricRecorder = metricRecorderFactory.create(executor, lazy, provider);
        this.executorService = listeningScheduledExecutorService;
        this.configsProvider = lazy;
        this.capture = memoryUsageCapture;
        this.enableUnifiedInit = ((Boolean) optional.or(Boolean.FALSE)).booleanValue();
    }

    public static final boolean isUnsampled$ar$ds$ar$edu(int i) {
        int i2 = MemoryMetric$MemoryUsageMetric.MemoryEventCode.UNKNOWN$ar$edu$307db262_0;
        if (i != 0) {
            if (i != i2) {
                int i3 = MemoryMetric$MemoryUsageMetric.MemoryEventCode.DELTA_OF_MEMORY$ar$edu;
                if (i != 0) {
                    if (i != i3) {
                        return true;
                    }
                    return false;
                }
                throw null;
            }
            return false;
        }
        throw null;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public final void onApplicationStartup() {
        if (this.enableUnifiedInit) {
            startMonitoring();
        }
    }

    @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricService
    public final void startMonitoring() {
        MemoryMetricMonitor.Callback callback = new MemoryMetricMonitor.Callback() { // from class: com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricServiceImpl$$ExternalSyntheticLambda5
            @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricMonitor.Callback
            public final void onEvent$ar$edu(final int i, final String str) {
                final MemoryMetricServiceImpl memoryMetricServiceImpl = MemoryMetricServiceImpl.this;
                if (memoryMetricServiceImpl.shutdown.shutdown) {
                    ContextDataProvider.immediateCancelledFuture();
                } else {
                    ContextDataProvider.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricServiceImpl$$ExternalSyntheticLambda0
                        public final /* synthetic */ boolean f$4 = true;

                        @Override // com.google.common.util.concurrent.AsyncCallable
                        public final ListenableFuture call() {
                            long samplingRatePermilleIfShouldCollect;
                            final MemoryMetricServiceImpl memoryMetricServiceImpl2 = MemoryMetricServiceImpl.this;
                            final MemoryConfigurations memoryConfigurations = (MemoryConfigurations) memoryMetricServiceImpl2.configsProvider.get();
                            final int i2 = i;
                            if (MemoryMetricServiceImpl.isUnsampled$ar$ds$ar$edu(i2)) {
                                if (true != memoryConfigurations.isEnabled()) {
                                    samplingRatePermilleIfShouldCollect = -1;
                                } else {
                                    samplingRatePermilleIfShouldCollect = 1000;
                                }
                            } else {
                                samplingRatePermilleIfShouldCollect = memoryMetricServiceImpl2.metricRecorder.samplingRatePermilleIfShouldCollect(null);
                            }
                            if (samplingRatePermilleIfShouldCollect == -1) {
                                return ImmediateFuture.NULL;
                            }
                            final String str2 = str;
                            ListenableFuture immediateFuture = ContextDataProvider.immediateFuture(null);
                            memoryConfigurations.getMetricExtensionProvider();
                            final boolean z = true;
                            final long j = samplingRatePermilleIfShouldCollect;
                            return AbstractTransformFuture.create(AbstractCatchingFuture.create(GwtFluentFutureCatchingSpecialization.from$ar$class_merging(immediateFuture), RuntimeException.class, new AccessibilityEventUtils$$ExternalSyntheticLambda0(16), memoryMetricServiceImpl2.executorService), new AsyncFunction(memoryConfigurations, z, j, i2, str2) { // from class: com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricServiceImpl$$ExternalSyntheticLambda7
                                public final /* synthetic */ MemoryConfigurations f$1;
                                public final /* synthetic */ boolean f$3 = true;
                                public final /* synthetic */ long f$4;
                                public final /* synthetic */ int f$5$ar$edu;
                                public final /* synthetic */ String f$6;

                                {
                                    this.f$4 = j;
                                    this.f$5$ar$edu = i2;
                                    this.f$6 = str2;
                                }

                                /* JADX WARN: Multi-variable type inference failed */
                                @Override // com.google.common.util.concurrent.AsyncFunction
                                public final ListenableFuture apply(Object obj) {
                                    ActivityManager.MemoryInfo memoryInfo;
                                    MemoryUsageCapture.ProcStatus procStatus;
                                    final File file;
                                    ExtensionMetric$MetricExtension extensionMetric$MetricExtension = (ExtensionMetric$MetricExtension) obj;
                                    SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE.createBuilder();
                                    MemoryMetricServiceImpl memoryMetricServiceImpl3 = MemoryMetricServiceImpl.this;
                                    MemoryUsageCapture memoryUsageCapture = memoryMetricServiceImpl3.capture;
                                    Context context = memoryUsageCapture.appContext;
                                    int myPid = Process.myPid();
                                    boolean isInForeground = memoryUsageCapture.foregroundStateCapture$ar$class_merging$ar$class_merging.isInForeground(ProcessStats.getRunningAppProcesses(context));
                                    Object obj2 = memoryUsageCapture.configsProvider.get();
                                    ThreadUtil.ensureBackgroundThread();
                                    if (((MemoryConfigurations) obj2).getCaptureMemoryInfo()) {
                                        ActivityManager.MemoryInfo memoryInfo2 = new ActivityManager.MemoryInfo();
                                        Context context2 = memoryUsageCapture.appContext;
                                        if (ProcessStats.activityManager == null) {
                                            synchronized (ProcessStats.class) {
                                                if (ProcessStats.activityManager == null) {
                                                    Object systemService = context2.getSystemService("activity");
                                                    systemService.getClass();
                                                    ProcessStats.activityManager = (ActivityManager) systemService;
                                                }
                                            }
                                        }
                                        ProcessStats.activityManager.getMemoryInfo(memoryInfo2);
                                        memoryInfo = memoryInfo2;
                                    } else {
                                        memoryInfo = null;
                                    }
                                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                                    try {
                                        try {
                                            if (((Boolean) memoryUsageCapture.readCorrectProcStatusProvider.get()).booleanValue()) {
                                                file = new File(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(myPid, "/proc/", "/status"));
                                            } else {
                                                file = new File("/proc/self/status");
                                            }
                                            ByteSource$AsCharSource byteSource$AsCharSource = new ByteSource$AsCharSource(new ContextDataProvider(file) { // from class: com.google.common.io.Files$FileByteSource
                                                private final File file;

                                                {
                                                    this.file = file;
                                                }

                                                @Override // com.google.common.flogger.context.ContextDataProvider
                                                public final byte[] read() {
                                                    boolean z2;
                                                    Closer closer = new Closer(Closer.SUPPRESSING_SUPPRESSOR$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
                                                    try {
                                                        FileInputStream fileInputStream = new FileInputStream(this.file);
                                                        closer.stack.addFirst(fileInputStream);
                                                        long size = DesugarChannels.convertMaybeLegacyFileChannelFromLibrary(fileInputStream.getChannel()).size();
                                                        if (size >= 0) {
                                                            z2 = true;
                                                        } else {
                                                            z2 = false;
                                                        }
                                                        ContextDataProvider.checkArgument(z2, "expectedSize (%s) must be non-negative", size);
                                                        if (size <= 2147483639) {
                                                            int i3 = (int) size;
                                                            byte[] bArr = new byte[i3];
                                                            int i4 = i3;
                                                            while (true) {
                                                                if (i4 > 0) {
                                                                    int i5 = i3 - i4;
                                                                    int read = fileInputStream.read(bArr, i5, i4);
                                                                    if (read == -1) {
                                                                        bArr = Arrays.copyOf(bArr, i5);
                                                                        break;
                                                                    }
                                                                    i4 -= read;
                                                                } else {
                                                                    int read2 = fileInputStream.read();
                                                                    if (read2 != -1) {
                                                                        ArrayDeque arrayDeque = new ArrayDeque(22);
                                                                        arrayDeque.add(bArr);
                                                                        arrayDeque.add(new byte[]{(byte) read2});
                                                                        bArr = ByteStreams.toByteArrayInternal(fileInputStream, arrayDeque, i3 + 1);
                                                                    }
                                                                }
                                                            }
                                                            return bArr;
                                                        }
                                                        throw new OutOfMemoryError(size + " bytes is too large to fit in a byte array");
                                                    } catch (Throwable th) {
                                                        try {
                                                            closer.thrown = th;
                                                            Throwables.throwIfInstanceOf(th, IOException.class);
                                                            Throwables.throwIfUnchecked(th);
                                                            throw new RuntimeException(th);
                                                        } finally {
                                                            closer.close();
                                                        }
                                                    }
                                                }

                                                public final String toString() {
                                                    return "Files.asByteSource(" + this.file.toString() + ")";
                                                }
                                            }, Charset.defaultCharset());
                                            String str3 = new String(byteSource$AsCharSource.this$0$ar$class_merging$412157e4_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.read(), byteSource$AsCharSource.charset);
                                            if (str3.isEmpty()) {
                                                ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/memory/MemoryUsageCapture", "procStatusFromString", 252, "MemoryUsageCapture.java")).log("Null or empty proc status");
                                                procStatus = null;
                                            } else {
                                                procStatus = new MemoryUsageCapture.ProcStatus();
                                                procStatus.rssHwmKb = MemoryUsageCapture.tryParseLong(MemoryUsageCapture.ProcStatus.PROCFS_RSS_HIGH_WATERMARK_IN_KILOBYTES, str3);
                                                procStatus.totalRssKb = MemoryUsageCapture.tryParseLong(MemoryUsageCapture.ProcStatus.PROCFS_RSS_IN_KILOBYTES, str3);
                                                procStatus.anonRssKb = MemoryUsageCapture.tryParseLong(MemoryUsageCapture.ProcStatus.PROCFS_ANON_RSS_IN_KILOBYTES, str3);
                                                procStatus.swapKb = MemoryUsageCapture.tryParseLong(MemoryUsageCapture.ProcStatus.PROCFS_SWAP_IN_KILOBYTES, str3);
                                                procStatus.vmSizeKb = MemoryUsageCapture.tryParseLong(MemoryUsageCapture.ProcStatus.PROCFS_VM_SIZE_IN_KILOBYTES, str3);
                                            }
                                        } catch (IOException e) {
                                            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/memory/MemoryUsageCapture", "getProcStatus", 289, "MemoryUsageCapture.java")).log("Error reading proc status");
                                            StrictMode.setThreadPolicy(allowThreadDiskReads);
                                            procStatus = null;
                                        }
                                        SchedulerOptions.Builder builder2 = (SchedulerOptions.Builder) MemoryMetric$MemoryUsageMetric.DEFAULT_INSTANCE.createBuilder();
                                        SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) MemoryMetric$MemoryStats.DEFAULT_INSTANCE.createBuilder();
                                        SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) MemoryMetric$AndroidMemoryStats.DEFAULT_INSTANCE.createBuilder();
                                        if (memoryInfo != null) {
                                            long j2 = memoryInfo.availMem >> 10;
                                            builder4.copyOnWrite();
                                            MemoryMetric$AndroidMemoryStats memoryMetric$AndroidMemoryStats = (MemoryMetric$AndroidMemoryStats) builder4.instance;
                                            memoryMetric$AndroidMemoryStats.bitField0_ |= 131072;
                                            memoryMetric$AndroidMemoryStats.availableMemoryKb_ = (int) j2;
                                            long j3 = memoryInfo.totalMem >> 20;
                                            builder4.copyOnWrite();
                                            MemoryMetric$AndroidMemoryStats memoryMetric$AndroidMemoryStats2 = (MemoryMetric$AndroidMemoryStats) builder4.instance;
                                            memoryMetric$AndroidMemoryStats2.bitField0_ |= 262144;
                                            memoryMetric$AndroidMemoryStats2.totalMemoryMb_ = (int) j3;
                                        }
                                        if (procStatus != null) {
                                            Long l = procStatus.rssHwmKb;
                                            if (l != null) {
                                                long longValue = l.longValue();
                                                builder4.copyOnWrite();
                                                MemoryMetric$AndroidMemoryStats memoryMetric$AndroidMemoryStats3 = (MemoryMetric$AndroidMemoryStats) builder4.instance;
                                                memoryMetric$AndroidMemoryStats3.bitField0_ |= 524288;
                                                memoryMetric$AndroidMemoryStats3.rssHwmKb_ = longValue;
                                            }
                                            Long l2 = procStatus.totalRssKb;
                                            if (l2 != null) {
                                                long longValue2 = l2.longValue();
                                                builder4.copyOnWrite();
                                                MemoryMetric$AndroidMemoryStats memoryMetric$AndroidMemoryStats4 = (MemoryMetric$AndroidMemoryStats) builder4.instance;
                                                memoryMetric$AndroidMemoryStats4.bitField0_ |= 1048576;
                                                memoryMetric$AndroidMemoryStats4.totalRssKb_ = longValue2;
                                            }
                                            Long l3 = procStatus.anonRssKb;
                                            if (l3 != null) {
                                                long longValue3 = l3.longValue();
                                                builder4.copyOnWrite();
                                                MemoryMetric$AndroidMemoryStats memoryMetric$AndroidMemoryStats5 = (MemoryMetric$AndroidMemoryStats) builder4.instance;
                                                memoryMetric$AndroidMemoryStats5.bitField0_ |= 2097152;
                                                memoryMetric$AndroidMemoryStats5.anonRssKb_ = longValue3;
                                            }
                                            Long l4 = procStatus.swapKb;
                                            if (l4 != null) {
                                                long longValue4 = l4.longValue();
                                                builder4.copyOnWrite();
                                                MemoryMetric$AndroidMemoryStats memoryMetric$AndroidMemoryStats6 = (MemoryMetric$AndroidMemoryStats) builder4.instance;
                                                memoryMetric$AndroidMemoryStats6.bitField0_ |= 4194304;
                                                memoryMetric$AndroidMemoryStats6.swapKb_ = longValue4;
                                            }
                                            Long l5 = procStatus.vmSizeKb;
                                            if (l5 != null) {
                                                long longValue5 = l5.longValue();
                                                builder4.copyOnWrite();
                                                MemoryMetric$AndroidMemoryStats memoryMetric$AndroidMemoryStats7 = (MemoryMetric$AndroidMemoryStats) builder4.instance;
                                                memoryMetric$AndroidMemoryStats7.bitField0_ |= 8388608;
                                                memoryMetric$AndroidMemoryStats7.vmSizeKb_ = longValue5;
                                            }
                                        }
                                        String str4 = this.f$6;
                                        int i3 = this.f$5$ar$edu;
                                        MemoryMetric$AndroidMemoryStats memoryMetric$AndroidMemoryStats8 = (MemoryMetric$AndroidMemoryStats) builder4.build();
                                        builder3.copyOnWrite();
                                        MemoryMetric$MemoryStats memoryMetric$MemoryStats = (MemoryMetric$MemoryStats) builder3.instance;
                                        memoryMetric$AndroidMemoryStats8.getClass();
                                        memoryMetric$MemoryStats.androidMemoryStats_ = memoryMetric$AndroidMemoryStats8;
                                        memoryMetric$MemoryStats.bitField0_ |= 1;
                                        builder2.copyOnWrite();
                                        MemoryMetric$MemoryUsageMetric memoryMetric$MemoryUsageMetric = (MemoryMetric$MemoryUsageMetric) builder2.instance;
                                        MemoryMetric$MemoryStats memoryMetric$MemoryStats2 = (MemoryMetric$MemoryStats) builder3.build();
                                        memoryMetric$MemoryStats2.getClass();
                                        memoryMetric$MemoryUsageMetric.memoryStats_ = memoryMetric$MemoryStats2;
                                        memoryMetric$MemoryUsageMetric.bitField0_ |= 1;
                                        SystemHealthProto$PackedHistogram.Builder builder5 = (SystemHealthProto$PackedHistogram.Builder) ProcessProto$ProcessStats.DEFAULT_INSTANCE.createBuilder();
                                        ProcessProto$AndroidProcessStats processStatsWithImportanceMetric$ar$ds = ProcessStatsCapture.getProcessStatsWithImportanceMetric$ar$ds(null, isInForeground, ProcessStats.getRunningAppProcesses((Context) memoryUsageCapture.processStatsCapture.ProcessStatsCapture$ar$context));
                                        builder5.copyOnWrite();
                                        ProcessProto$ProcessStats processProto$ProcessStats = (ProcessProto$ProcessStats) builder5.instance;
                                        processStatsWithImportanceMetric$ar$ds.getClass();
                                        processProto$ProcessStats.androidProcessStats_ = processStatsWithImportanceMetric$ar$ds;
                                        processProto$ProcessStats.bitField0_ |= 1;
                                        builder2.copyOnWrite();
                                        MemoryMetric$MemoryUsageMetric memoryMetric$MemoryUsageMetric2 = (MemoryMetric$MemoryUsageMetric) builder2.instance;
                                        ProcessProto$ProcessStats processProto$ProcessStats2 = (ProcessProto$ProcessStats) builder5.build();
                                        processProto$ProcessStats2.getClass();
                                        memoryMetric$MemoryUsageMetric2.processStats_ = processProto$ProcessStats2;
                                        memoryMetric$MemoryUsageMetric2.bitField0_ |= 2;
                                        SystemHealthProto$PackedHistogram.Builder builder6 = (SystemHealthProto$PackedHistogram.Builder) MemoryMetric$DeviceStats.DEFAULT_INSTANCE.createBuilder();
                                        boolean isScreenOn = ProcessStats.isScreenOn(memoryUsageCapture.appContext);
                                        builder6.copyOnWrite();
                                        MemoryMetric$DeviceStats memoryMetric$DeviceStats = (MemoryMetric$DeviceStats) builder6.instance;
                                        memoryMetric$DeviceStats.bitField0_ |= 1;
                                        memoryMetric$DeviceStats.isScreenOn_ = isScreenOn;
                                        builder2.copyOnWrite();
                                        MemoryMetric$MemoryUsageMetric memoryMetric$MemoryUsageMetric3 = (MemoryMetric$MemoryUsageMetric) builder2.instance;
                                        MemoryMetric$DeviceStats memoryMetric$DeviceStats2 = (MemoryMetric$DeviceStats) builder6.build();
                                        memoryMetric$DeviceStats2.getClass();
                                        memoryMetric$MemoryUsageMetric3.deviceStats_ = memoryMetric$DeviceStats2;
                                        memoryMetric$MemoryUsageMetric3.bitField0_ |= 8;
                                        builder2.copyOnWrite();
                                        MemoryMetric$MemoryUsageMetric memoryMetric$MemoryUsageMetric4 = (MemoryMetric$MemoryUsageMetric) builder2.instance;
                                        if (i3 != 0) {
                                            memoryMetric$MemoryUsageMetric4.memoryEventCode_ = i3 - 1;
                                            memoryMetric$MemoryUsageMetric4.bitField0_ |= 4;
                                            if (str4 != null) {
                                                builder2.copyOnWrite();
                                                MemoryMetric$MemoryUsageMetric memoryMetric$MemoryUsageMetric5 = (MemoryMetric$MemoryUsageMetric) builder2.instance;
                                                memoryMetric$MemoryUsageMetric5.bitField0_ |= 16;
                                                memoryMetric$MemoryUsageMetric5.activityName_ = str4;
                                            }
                                            long j4 = this.f$4;
                                            MemoryMetric$MemoryUsageMetric memoryMetric$MemoryUsageMetric6 = (MemoryMetric$MemoryUsageMetric) builder2.build();
                                            builder.copyOnWrite();
                                            SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder.instance;
                                            memoryMetric$MemoryUsageMetric6.getClass();
                                            systemHealthProto$SystemHealthMetric.memoryUsageMetric_ = memoryMetric$MemoryUsageMetric6;
                                            systemHealthProto$SystemHealthMetric.bitField0_ |= 8;
                                            SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric2 = (SystemHealthProto$SystemHealthMetric) builder.build();
                                            Metric.Builder newBuilder = Metric.newBuilder();
                                            newBuilder.customEventName = null;
                                            newBuilder.setIsEventNameConstant$ar$ds(true);
                                            newBuilder.sampleRatePermille = Long.valueOf(j4);
                                            newBuilder.setMetric$ar$ds(systemHealthProto$SystemHealthMetric2);
                                            newBuilder.metricExtension = extensionMetric$MetricExtension;
                                            if (MemoryMetricServiceImpl.isUnsampled$ar$ds$ar$edu(i3)) {
                                                newBuilder.setIsUnsampled$ar$ds(true);
                                            }
                                            return memoryMetricServiceImpl3.metricRecorder.recordMetric(newBuilder.build());
                                        }
                                        throw null;
                                    } finally {
                                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                                    }
                                }
                            }, memoryMetricServiceImpl2.executorService);
                        }
                    }, memoryMetricServiceImpl.executorService);
                }
            }
        };
        MemoryMetricMonitor memoryMetricMonitor = this.metricMonitor;
        memoryMetricMonitor.callback = callback;
        if (memoryMetricMonitor.started.getAndSet(true)) {
            return;
        }
        ContextDataProvider.submitAsync(new CrashMetricServiceImpl$$ExternalSyntheticLambda1(memoryMetricMonitor, 3), memoryMetricMonitor.deferredExecutor);
    }
}
