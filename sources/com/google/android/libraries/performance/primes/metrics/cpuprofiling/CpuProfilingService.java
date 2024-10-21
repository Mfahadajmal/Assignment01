package com.google.android.libraries.performance.primes.metrics.cpuprofiling;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Debug;
import androidx.core.view.inputmethod.EditorInfoCompat;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.performance.primes.ConfigurationsModule$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl$$ExternalSyntheticLambda0;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.TrustedListenableFutureTask;
import com.google.protobuf.ByteString;
import dagger.Lazy;
import j$.time.Instant;
import j$.time.temporal.ChronoUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.Deflater;
import java.util.zip.DeflaterInputStream;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.CpuProfiling$CpuProfilingMetric;
import logs.proto.wireless.performance.mobile.CpuProfiling$DeviceMetadata;
import logs.proto.wireless.performance.mobile.CpuProfiling$DeviceState;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CpuProfilingService implements MetricService {
    public final Application application;
    public final SpannableUtils$NonCopyableTextSpan clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging;
    public final Lazy configsProvider;
    public final ListeningScheduledExecutorService executorService;
    public final MetricRecorder metricRecorder;
    private final Supplier schedulerSupplier;
    public final Supplier traceFileSupplier;
    private WifiManager wifi;
    public final AtomicBoolean scheduled = new AtomicBoolean(false);
    public final IntentFilter batteryIntentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class CpuCollectionEndTask implements Runnable {
        private final long actualStartTiemMs;
        private final Float batteryPercent;
        private final CpuProfiling$DeviceMetadata deviceMetadata;
        private final long stopTimeMs;
        private final File traceFile;

        public CpuCollectionEndTask(File file, CpuProfiling$DeviceMetadata cpuProfiling$DeviceMetadata, Float f, long j, long j2) {
            this.traceFile = file;
            this.deviceMetadata = cpuProfiling$DeviceMetadata;
            this.batteryPercent = f;
            this.stopTimeMs = j;
            this.actualStartTiemMs = j2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Instant truncatedTo;
            CpuProfilingService.this.scheduled.set(false);
            Debug.stopMethodTracing();
            truncatedTo = Instant.now().truncatedTo(ChronoUnit.MILLIS);
            long epochMilli = truncatedTo.toEpochMilli();
            if (epochMilli >= this.stopTimeMs + ((CpuProfilingConfigurations) CpuProfilingService.this.configsProvider.get()).getSampleDurationSkewMs()) {
                CpuProfilingService.this.scheduleNextMonitoringWindow(false);
                ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/cpuprofiling/CpuProfilingService$CpuCollectionEndTask", "run", 294, "CpuProfilingService.java")).log("Missed sample window by %d ms", epochMilli - this.stopTimeMs);
                return;
            }
            CpuProfilingService cpuProfilingService = CpuProfilingService.this;
            CpuProfiling$DeviceMetadata cpuProfiling$DeviceMetadata = this.deviceMetadata;
            Intent registerReceiver = cpuProfilingService.application.registerReceiver(null, cpuProfilingService.batteryIntentFilter);
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) cpuProfiling$DeviceMetadata.toBuilder();
            CpuProfiling$DeviceState deviceState = CpuProfilingService.this.getDeviceState(registerReceiver);
            builder.copyOnWrite();
            CpuProfiling$DeviceMetadata cpuProfiling$DeviceMetadata2 = (CpuProfiling$DeviceMetadata) builder.instance;
            deviceState.getClass();
            cpuProfiling$DeviceMetadata2.afterState_ = deviceState;
            cpuProfiling$DeviceMetadata2.bitField0_ |= 2;
            float floatValue = this.batteryPercent.floatValue() - CpuProfilingService.getBatteryPercent$ar$ds(registerReceiver);
            builder.copyOnWrite();
            CpuProfiling$DeviceMetadata cpuProfiling$DeviceMetadata3 = (CpuProfiling$DeviceMetadata) builder.instance;
            cpuProfiling$DeviceMetadata3.bitField0_ |= 4;
            cpuProfiling$DeviceMetadata3.batteryDropPercent_ = floatValue;
            CpuProfiling$DeviceMetadata cpuProfiling$DeviceMetadata4 = (CpuProfiling$DeviceMetadata) builder.build();
            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) CpuProfiling$CpuProfilingMetric.DEFAULT_INSTANCE.createBuilder();
            builder2.copyOnWrite();
            CpuProfiling$CpuProfilingMetric cpuProfiling$CpuProfilingMetric = (CpuProfiling$CpuProfilingMetric) builder2.instance;
            cpuProfiling$DeviceMetadata4.getClass();
            cpuProfiling$CpuProfilingMetric.deviceMetadata_ = cpuProfiling$DeviceMetadata4;
            cpuProfiling$CpuProfilingMetric.bitField0_ |= 2;
            if (!this.traceFile.exists()) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/cpuprofiling/CpuProfilingService$CpuCollectionEndTask", "run", 310, "CpuProfilingService.java")).log("Missing trace file");
            } else {
                long length = this.traceFile.length();
                if (length > 0 && length < ((CpuProfilingConfigurations) CpuProfilingService.this.configsProvider.get()).getMaxBufferSizeBytes()) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(this.traceFile);
                        try {
                            ByteString.Output output = new ByteString.Output((int) length);
                            try {
                                byte[] bArr = new byte[1024];
                                DeflaterInputStream deflaterInputStream = new DeflaterInputStream(fileInputStream, new Deflater(9));
                                while (true) {
                                    try {
                                        int read = deflaterInputStream.read(bArr);
                                        if (read == -1) {
                                            break;
                                        } else {
                                            output.write(bArr, 0, read);
                                        }
                                    } finally {
                                    }
                                }
                                deflaterInputStream.close();
                                ByteString byteString = output.toByteString();
                                builder2.copyOnWrite();
                                CpuProfiling$CpuProfilingMetric cpuProfiling$CpuProfilingMetric2 = (CpuProfiling$CpuProfilingMetric) builder2.instance;
                                byteString.getClass();
                                cpuProfiling$CpuProfilingMetric2.bitField0_ |= 1;
                                cpuProfiling$CpuProfilingMetric2.traceBlob_ = byteString;
                                CpuProfilingService.clearFileAndSwallowResultingExceptions(this.traceFile);
                                output.close();
                                fileInputStream.close();
                            } finally {
                            }
                        } finally {
                        }
                    } catch (IOException e) {
                        ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atSevere()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/cpuprofiling/CpuProfilingService$CpuCollectionEndTask", "run", 321, "CpuProfilingService.java")).log("Unable to read file %s", this.traceFile);
                    }
                }
            }
            double samplesPerEpoch = ((CpuProfilingConfigurations) CpuProfilingService.this.configsProvider.get()).getSamplesPerEpoch();
            builder2.copyOnWrite();
            CpuProfiling$CpuProfilingMetric cpuProfiling$CpuProfilingMetric3 = (CpuProfiling$CpuProfilingMetric) builder2.instance;
            cpuProfiling$CpuProfilingMetric3.bitField0_ |= 4;
            cpuProfiling$CpuProfilingMetric3.samplesPerEpoch_ = samplesPerEpoch;
            int sampleFrequencyMicro = ((CpuProfilingConfigurations) CpuProfilingService.this.configsProvider.get()).getSampleFrequencyMicro();
            builder2.copyOnWrite();
            CpuProfiling$CpuProfilingMetric cpuProfiling$CpuProfilingMetric4 = (CpuProfiling$CpuProfilingMetric) builder2.instance;
            cpuProfiling$CpuProfilingMetric4.bitField0_ |= 32;
            cpuProfiling$CpuProfilingMetric4.sampleFrequency_ = sampleFrequencyMicro;
            long j = epochMilli - this.actualStartTiemMs;
            if (j < 2147483647L) {
                builder2.copyOnWrite();
                CpuProfiling$CpuProfilingMetric cpuProfiling$CpuProfilingMetric5 = (CpuProfiling$CpuProfilingMetric) builder2.instance;
                cpuProfiling$CpuProfilingMetric5.bitField0_ |= 16;
                cpuProfiling$CpuProfilingMetric5.sampleDurationActual_ = (int) j;
            } else {
                builder2.copyOnWrite();
                CpuProfiling$CpuProfilingMetric cpuProfiling$CpuProfilingMetric6 = (CpuProfiling$CpuProfilingMetric) builder2.instance;
                cpuProfiling$CpuProfilingMetric6.bitField0_ |= 16;
                cpuProfiling$CpuProfilingMetric6.sampleDurationActual_ = -1;
            }
            int sampleDurationMs = ((CpuProfilingConfigurations) CpuProfilingService.this.configsProvider.get()).getSampleDurationMs();
            builder2.copyOnWrite();
            CpuProfiling$CpuProfilingMetric cpuProfiling$CpuProfilingMetric7 = (CpuProfiling$CpuProfilingMetric) builder2.instance;
            cpuProfiling$CpuProfilingMetric7.bitField0_ |= 8;
            cpuProfiling$CpuProfilingMetric7.sampleDurationScheduled_ = sampleDurationMs;
            int maxBufferSizeBytes = ((CpuProfilingConfigurations) CpuProfilingService.this.configsProvider.get()).getMaxBufferSizeBytes();
            builder2.copyOnWrite();
            CpuProfiling$CpuProfilingMetric cpuProfiling$CpuProfilingMetric8 = (CpuProfiling$CpuProfilingMetric) builder2.instance;
            cpuProfiling$CpuProfilingMetric8.bitField0_ |= 64;
            cpuProfiling$CpuProfilingMetric8.sampleBufferSize_ = maxBufferSizeBytes;
            if (((CpuProfiling$CpuProfilingMetric) builder2.instance).traceBlob_.size() > 0) {
                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE.createBuilder();
                builder3.copyOnWrite();
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder3.instance;
                CpuProfiling$CpuProfilingMetric cpuProfiling$CpuProfilingMetric9 = (CpuProfiling$CpuProfilingMetric) builder2.build();
                cpuProfiling$CpuProfilingMetric9.getClass();
                systemHealthProto$SystemHealthMetric.cpuProfilingMetric_ = cpuProfiling$CpuProfilingMetric9;
                systemHealthProto$SystemHealthMetric.bitField0_ |= 16384;
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric2 = (SystemHealthProto$SystemHealthMetric) builder3.build();
                CpuProfilingService cpuProfilingService2 = CpuProfilingService.this;
                Metric.Builder newBuilder = Metric.newBuilder();
                newBuilder.setMetric$ar$ds(systemHealthProto$SystemHealthMetric2);
                cpuProfilingService2.metricRecorder.recordMetric(newBuilder.build());
            }
            CpuProfilingService.this.scheduleNextMonitoringWindow(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CpuCollectionStartTask implements Runnable {
        private final long nextWindowMillis;

        public CpuCollectionStartTask(long j) {
            this.nextWindowMillis = j;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Instant truncatedTo;
            CpuProfilingConfigurations cpuProfilingConfigurations = (CpuProfilingConfigurations) CpuProfilingService.this.configsProvider.get();
            long sampleDurationMs = cpuProfilingConfigurations.getSampleDurationMs();
            truncatedTo = Instant.now().truncatedTo(ChronoUnit.MILLIS);
            long epochMilli = truncatedTo.toEpochMilli();
            long j = sampleDurationMs + this.nextWindowMillis;
            if (j <= epochMilli) {
                CpuProfilingService.this.scheduleNextMonitoringWindow(false);
                return;
            }
            CpuProfilingService cpuProfilingService = CpuProfilingService.this;
            Intent registerReceiver = cpuProfilingService.application.registerReceiver(null, cpuProfilingService.batteryIntentFilter);
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) CpuProfiling$DeviceMetadata.DEFAULT_INSTANCE.createBuilder();
            CpuProfiling$DeviceState deviceState = cpuProfilingService.getDeviceState(registerReceiver);
            builder.copyOnWrite();
            CpuProfiling$DeviceMetadata cpuProfiling$DeviceMetadata = (CpuProfiling$DeviceMetadata) builder.instance;
            deviceState.getClass();
            cpuProfiling$DeviceMetadata.beforeState_ = deviceState;
            cpuProfiling$DeviceMetadata.bitField0_ |= 1;
            CpuProfiling$DeviceMetadata cpuProfiling$DeviceMetadata2 = (CpuProfiling$DeviceMetadata) builder.build();
            Optional optional = (Optional) CpuProfilingService.this.traceFileSupplier.get();
            if (!optional.isPresent()) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/cpuprofiling/CpuProfilingService$CpuCollectionStartTask", "run", 208, "CpuProfilingService.java")).log("Can't create file, aborting method sampling");
                return;
            }
            File file = (File) optional.get();
            CpuProfilingService.clearFileAndSwallowResultingExceptions(file);
            Debug.startMethodTracingSampling(file.getAbsolutePath(), cpuProfilingConfigurations.getMaxBufferSizeBytes(), cpuProfilingConfigurations.getSampleFrequencyMicro());
            CpuProfilingService cpuProfilingService2 = CpuProfilingService.this;
            cpuProfilingService2.executorService.schedule(new CpuCollectionEndTask(file, cpuProfiling$DeviceMetadata2, Float.valueOf(CpuProfilingService.getBatteryPercent$ar$ds(registerReceiver)), j, epochMilli), j - epochMilli, TimeUnit.MILLISECONDS);
        }
    }

    public CpuProfilingService(MetricRecorderFactory metricRecorderFactory, Context context, ListeningScheduledExecutorService listeningScheduledExecutorService, Lazy lazy, Provider provider, SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan, Provider provider2) {
        this.metricRecorder = metricRecorderFactory.create(listeningScheduledExecutorService, lazy, provider);
        this.application = (Application) context;
        this.executorService = listeningScheduledExecutorService;
        this.configsProvider = lazy;
        this.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging = spannableUtils$NonCopyableTextSpan;
        this.traceFileSupplier = ContextDataProvider.memoize(new FrameMetricServiceImpl$$ExternalSyntheticLambda0((Object) this, context, 1));
        this.schedulerSupplier = ContextDataProvider.memoize(new ConfigurationsModule$$ExternalSyntheticLambda0(provider2, 6));
    }

    public static void clearFileAndSwallowResultingExceptions(File file) {
        try {
            if (!file.exists()) {
                return;
            }
            file.delete();
        } catch (RuntimeException e) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/cpuprofiling/CpuProfilingService", "clearFileAndSwallowResultingExceptions", 368, "CpuProfilingService.java")).log("Exception when clearing trace file.");
        }
    }

    public static final float getBatteryPercent$ar$ds(Intent intent) {
        return intent.getIntExtra("level", -1) / intent.getIntExtra("scale", -1);
    }

    public final CpuProfiling$DeviceState getDeviceState(Intent intent) {
        boolean z;
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) CpuProfiling$DeviceState.DEFAULT_INSTANCE.createBuilder();
        if (this.wifi == null) {
            this.wifi = (WifiManager) this.application.getSystemService("wifi");
        }
        boolean isWifiEnabled = this.wifi.isWifiEnabled();
        builder.copyOnWrite();
        CpuProfiling$DeviceState cpuProfiling$DeviceState = (CpuProfiling$DeviceState) builder.instance;
        cpuProfiling$DeviceState.bitField0_ |= 4;
        cpuProfiling$DeviceState.wifiOn_ = isWifiEnabled;
        boolean z2 = false;
        if (EditorInfoCompat.checkSelfPermission(this.application, "android.permission.BLUETOOTH") == 0) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null && defaultAdapter.isEnabled()) {
                z = true;
            } else {
                z = false;
            }
            builder.copyOnWrite();
            CpuProfiling$DeviceState cpuProfiling$DeviceState2 = (CpuProfiling$DeviceState) builder.instance;
            cpuProfiling$DeviceState2.bitField0_ |= 8;
            cpuProfiling$DeviceState2.bluetoothOn_ = z;
        }
        boolean isScreenOn = ProcessStats.isScreenOn(this.application);
        builder.copyOnWrite();
        CpuProfiling$DeviceState cpuProfiling$DeviceState3 = (CpuProfiling$DeviceState) builder.instance;
        cpuProfiling$DeviceState3.bitField0_ |= 1;
        cpuProfiling$DeviceState3.screenOn_ = isScreenOn;
        int intExtra = intent.getIntExtra("status", -1);
        if (intExtra == 2 || intExtra == 5) {
            z2 = true;
        }
        builder.copyOnWrite();
        CpuProfiling$DeviceState cpuProfiling$DeviceState4 = (CpuProfiling$DeviceState) builder.instance;
        cpuProfiling$DeviceState4.bitField0_ = 2 | cpuProfiling$DeviceState4.bitField0_;
        cpuProfiling$DeviceState4.charging_ = z2;
        return (CpuProfiling$DeviceState) builder.build();
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public final void onApplicationStartup() {
        this.executorService.execute(TrustedListenableFutureTask.create(new TrainingActivity$$ExternalSyntheticLambda1(this, 17), null));
    }

    public final synchronized void scheduleNextMonitoringWindow(boolean z) {
        Instant truncatedTo;
        Instant truncatedTo2;
        CpuProfilingConfigurations cpuProfilingConfigurations = (CpuProfilingConfigurations) this.configsProvider.get();
        if (cpuProfilingConfigurations.isEnabled() && this.metricRecorder.shouldCollectMetric(null) && cpuProfilingConfigurations.getMaxBufferSizeBytes() > 0) {
            cpuProfilingConfigurations.getMaxBufferSizeBytes();
            if (cpuProfilingConfigurations.getSampleDurationMs() > 0 && cpuProfilingConfigurations.getSampleFrequencyMicro() > 0 && cpuProfilingConfigurations.getSamplesPerEpoch() > 0.0d) {
                if (z) {
                    Optional optional = (Optional) this.traceFileSupplier.get();
                    if (optional.isPresent()) {
                        clearFileAndSwallowResultingExceptions((File) optional.get());
                    }
                }
                if (!this.scheduled.get()) {
                    for (int i = 0; i < 5; i++) {
                        CpuProfilingServiceScheduler cpuProfilingServiceScheduler = (CpuProfilingServiceScheduler) this.schedulerSupplier.get();
                        ThreadUtil.ensureBackgroundThread();
                        SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan = cpuProfilingServiceScheduler.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging;
                        truncatedTo = Instant.now().truncatedTo(ChronoUnit.MILLIS);
                        long epochMilli = truncatedTo.toEpochMilli();
                        Long nextWindow = cpuProfilingServiceScheduler.getNextWindow(epochMilli - (epochMilli % CpuProfilingServiceScheduler.AVERAGE_MILLIS_PER_YEAR));
                        if (nextWindow == null) {
                            break;
                        }
                        long longValue = nextWindow.longValue();
                        truncatedTo2 = Instant.now().truncatedTo(ChronoUnit.MILLIS);
                        long epochMilli2 = longValue - truncatedTo2.toEpochMilli();
                        if (epochMilli2 > 0) {
                            this.scheduled.set(true);
                            this.executorService.schedule(new CpuCollectionStartTask(nextWindow.longValue()), epochMilli2, TimeUnit.MILLISECONDS);
                            return;
                        }
                    }
                }
            }
        }
    }
}
