package com.google.android.libraries.performance.primes.transmitter.clearcut;

import android.content.Context;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService$$ExternalSyntheticLambda1;
import com.google.android.libraries.performance.primes.ConfigurationsModule$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.transmitter.AccountProvider;
import com.google.android.libraries.performance.primes.transmitter.ExperimentsProvider;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import com.google.android.libraries.performance.primes.transmitter.ZwiebackCookieOverrideProvider;
import com.google.android.libraries.phenotype.client.stable.PhenotypeAccountStore$$ExternalSyntheticLambda4;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Internal;
import logs.proto.wireless.performance.mobile.SystemHealthProto$CrashMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$HistogramBucket;
import logs.proto.wireless.performance.mobile.SystemHealthProto$JankMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClearcutMetricTransmitter implements MetricTransmitter {
    public final Context context;
    private final boolean lifeboatEnabled;
    private final ClearcutMetricSnapshotBuilder snapshotBuilder;
    public final ClearcutMetricSnapshotTransmitter snapshotTransmitter;
    private final Supplier usePackedHistogramEncodingInClearcut;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public boolean anonymous;
        public Context context;
        public String logSource;
        private AccountProvider accountProvider = AccountProvider.NOOP_PROVIDER;
        private ExperimentsProvider experimentsProvider = ExperimentsProvider.NOOP_PROVIDER;
        private ZwiebackCookieOverrideProvider zwiebackCookieOverrideProvider = ZwiebackCookieOverrideProvider.NOOP_PROVIDER;

        public final ClearcutMetricTransmitter build() {
            return new ClearcutMetricTransmitter(this.context, Optional.of(false), new ClearcutMetricSnapshotBuilder(this.context.getPackageName(), this.logSource, this.accountProvider, this.experimentsProvider, this.zwiebackCookieOverrideProvider, this.anonymous), new ClearcutMetricSnapshotTransmitter());
        }
    }

    public ClearcutMetricTransmitter(Context context, Optional optional, ClearcutMetricSnapshotBuilder clearcutMetricSnapshotBuilder, ClearcutMetricSnapshotTransmitter clearcutMetricSnapshotTransmitter) {
        this.context = context;
        this.usePackedHistogramEncodingInClearcut = ContextDataProvider.memoize(new ConfigurationsModule$$ExternalSyntheticLambda0(context, 10));
        this.lifeboatEnabled = ((Boolean) optional.or((Object) false)).booleanValue();
        this.snapshotBuilder = clearcutMetricSnapshotBuilder;
        this.snapshotTransmitter = clearcutMetricSnapshotTransmitter;
    }

    @Override // com.google.android.libraries.performance.primes.transmitter.MetricTransmitter
    public final MetricTransmitter.Priority getTransmitterPriority() {
        return new MetricTransmitter.Priority(9);
    }

    @Override // com.google.android.libraries.performance.primes.transmitter.MetricTransmitter
    public final ListenableFuture send(SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric) {
        int i;
        int i2 = 1;
        if (this.lifeboatEnabled) {
            SystemHealthProto$CrashMetric systemHealthProto$CrashMetric = systemHealthProto$SystemHealthMetric.crashMetric_;
            if (systemHealthProto$CrashMetric == null) {
                systemHealthProto$CrashMetric = SystemHealthProto$CrashMetric.DEFAULT_INSTANCE;
            }
            if ((systemHealthProto$CrashMetric.bitField0_ & 1) != 0) {
                return AbstractTransformFuture.create(this.snapshotBuilder.buildExtension(), new PhenotypeAccountStore$$ExternalSyntheticLambda4(this, systemHealthProto$SystemHealthMetric, i2), DirectExecutor.INSTANCE);
            }
        }
        if ((systemHealthProto$SystemHealthMetric.bitField0_ & 1024) != 0 && ((Boolean) this.usePackedHistogramEncodingInClearcut.get()).booleanValue()) {
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) systemHealthProto$SystemHealthMetric.toBuilder();
            SystemHealthProto$JankMetric systemHealthProto$JankMetric = systemHealthProto$SystemHealthMetric.jankMetric_;
            if (systemHealthProto$JankMetric == null) {
                systemHealthProto$JankMetric = SystemHealthProto$JankMetric.DEFAULT_INSTANCE;
            }
            Internal.ProtobufList<SystemHealthProto$HistogramBucket> protobufList = systemHealthProto$JankMetric.frameTimeHistogram_;
            if (!protobufList.isEmpty()) {
                SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$PackedHistogram.DEFAULT_INSTANCE.createBuilder();
                SystemHealthProto$HistogramBucket systemHealthProto$HistogramBucket = null;
                for (SystemHealthProto$HistogramBucket systemHealthProto$HistogramBucket2 : protobufList) {
                    if (systemHealthProto$HistogramBucket != null && (i = systemHealthProto$HistogramBucket.max_ + 1) != systemHealthProto$HistogramBucket2.min_) {
                        builder2.addPopulation$ar$ds(0);
                        builder2.addLowerBound$ar$ds(i);
                    }
                    builder2.addPopulation$ar$ds(systemHealthProto$HistogramBucket2.count_);
                    builder2.addLowerBound$ar$ds(systemHealthProto$HistogramBucket2.min_);
                    systemHealthProto$HistogramBucket = systemHealthProto$HistogramBucket2;
                }
                if (systemHealthProto$HistogramBucket != null && (systemHealthProto$HistogramBucket.bitField0_ & 4) != 0) {
                    int i3 = systemHealthProto$HistogramBucket.max_ + 1;
                    builder2.addPopulation$ar$ds(0);
                    builder2.addLowerBound$ar$ds(i3);
                }
                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) systemHealthProto$JankMetric.toBuilder();
                builder3.copyOnWrite();
                ((SystemHealthProto$JankMetric) builder3.instance).frameTimeHistogram_ = SystemHealthProto$JankMetric.emptyProtobufList();
                builder3.copyOnWrite();
                SystemHealthProto$JankMetric systemHealthProto$JankMetric2 = (SystemHealthProto$JankMetric) builder3.instance;
                SystemHealthProto$PackedHistogram systemHealthProto$PackedHistogram = (SystemHealthProto$PackedHistogram) builder2.build();
                systemHealthProto$PackedHistogram.getClass();
                systemHealthProto$JankMetric2.packedFrameTimeHistogram_ = systemHealthProto$PackedHistogram;
                systemHealthProto$JankMetric2.bitField0_ |= BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
                systemHealthProto$JankMetric = (SystemHealthProto$JankMetric) builder3.build();
            }
            builder.copyOnWrite();
            SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric2 = (SystemHealthProto$SystemHealthMetric) builder.instance;
            systemHealthProto$JankMetric.getClass();
            systemHealthProto$SystemHealthMetric2.jankMetric_ = systemHealthProto$JankMetric;
            systemHealthProto$SystemHealthMetric2.bitField0_ |= 1024;
            systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder.build();
        }
        return AbstractTransformFuture.create(this.snapshotBuilder.buildExtension(), new AiCoreBaseService$$ExternalSyntheticLambda1(this, systemHealthProto$SystemHealthMetric, 2), DirectExecutor.INSTANCE);
    }
}
