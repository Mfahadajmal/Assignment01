package com.google.android.libraries.performance.primes.transmitter.clearcut;

import android.content.Context;
import android.util.Base64;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda9;
import com.google.android.accessibility.utils.AccessibilityEventUtils$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.apps.aicore.client.api.internal.AiCoreClientImpl$$ExternalSyntheticLambda2;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.clearcut.PIILevel;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.usagereporting.InternalUsageReportingClient;
import com.google.android.gms.usagereporting.UsageReporting;
import com.google.android.gms.usagereporting.UsageReportingApi$OptInOptionsChangedListener;
import com.google.android.libraries.consentverifier.BaseProtoCollectionBasis;
import com.google.android.libraries.consentverifier.logging.CollectionBasisLogVerifier;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.transmitter.MetricSnapshot;
import com.google.android.libraries.performance.primes.transmitter.MetricSnapshotTransmitter;
import com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer;
import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.common.base.Supplier;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.logging.proto2api.Logrecord$ThrowableBlockProto;
import com.google.common.logging.proto2api.Logrecord$ThrowableProto;
import com.google.common.util.concurrent.AbstractCatchingFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.GwtFluentFutureCatchingSpecialization;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics$LogEvent;
import googledata.experiments.mobile.primes_android.features.MetricTransmitterFeature;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import logs.proto.wireless.performance.mobile.BatteryMetric$BatteryStatsDiff;
import logs.proto.wireless.performance.mobile.BatteryMetric$BatteryUsageMetric;
import logs.proto.wireless.performance.mobile.NetworkMetric$NetworkEventUsage;
import logs.proto.wireless.performance.mobile.NetworkMetric$NetworkUsageMetric;
import logs.proto.wireless.performance.mobile.NetworkMetric$ServerNetworkEventUsage;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$PrimesTrace;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$Span;
import logs.proto.wireless.performance.mobile.SystemHealthProto$CrashMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackageMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ClearcutMetricSnapshotTransmitter implements MetricSnapshotTransmitter {
    public static final Supplier PROTO_COLLECTION_BASIS = ContextDataProvider.memoize(new PrimesController$$ExternalSyntheticLambda9(13));
    private volatile CheckboxChecker checkboxChecker;
    public volatile ClearcutLogger clearcutLogger;
    public volatile ClearcutLogger deidentifiedClearcutLogger;
    private volatile EventSanitizer eventSanitizer;
    private final Supplier isUserAMonkeyOrRunningInTestHarness = ContextDataProvider.memoize(new PrimesController$$ExternalSyntheticLambda9(12));

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.libraries.performance.primes.transmitter.MetricSnapshotTransmitter
    public final ListenableFuture transmit(final Context context, MetricSnapshot metricSnapshot) {
        Object fromFieldSetType;
        ListenableFuture create;
        String str;
        Logrecord$ThrowableProto.ThrowableGraph throwableGraph;
        GeneratedMessageLite.GeneratedExtension checkIsLite = GeneratedMessageLite.checkIsLite(ClearcutMetricSnapshot.clearcutMetricSnapshot);
        metricSnapshot.verifyExtensionContainingType(checkIsLite);
        ContextDataProvider.checkArgument(metricSnapshot.extensions.hasField$ar$class_merging(checkIsLite.descriptor), (Object) "ClearcutMetricSnapshotTransmitter received a snapshot without the expected extension.");
        if (this.eventSanitizer == null) {
            synchronized (this) {
                if (this.eventSanitizer == null) {
                    this.eventSanitizer = new EventSanitizer();
                }
            }
        }
        SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = metricSnapshot.systemHealthMetric_;
        if (systemHealthProto$SystemHealthMetric == null) {
            systemHealthProto$SystemHealthMetric = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE;
        }
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) systemHealthProto$SystemHealthMetric.toBuilder();
        EventSanitizer.ensureNoPiiName(EventSanitizer.SHM_METRIC_NAME_ACCESS, builder);
        BatteryMetric$BatteryUsageMetric batteryMetric$BatteryUsageMetric = ((SystemHealthProto$SystemHealthMetric) builder.instance).batteryUsageMetric_;
        if (batteryMetric$BatteryUsageMetric == null) {
            batteryMetric$BatteryUsageMetric = BatteryMetric$BatteryUsageMetric.DEFAULT_INSTANCE;
        }
        if ((batteryMetric$BatteryUsageMetric.bitField0_ & 1) != 0) {
            BatteryMetric$BatteryUsageMetric batteryMetric$BatteryUsageMetric2 = ((SystemHealthProto$SystemHealthMetric) builder.instance).batteryUsageMetric_;
            if (batteryMetric$BatteryUsageMetric2 == null) {
                batteryMetric$BatteryUsageMetric2 = BatteryMetric$BatteryUsageMetric.DEFAULT_INSTANCE;
            }
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff = batteryMetric$BatteryUsageMetric2.batteryStatsDiff_;
            if (batteryMetric$BatteryStatsDiff == null) {
                batteryMetric$BatteryStatsDiff = BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE;
            }
            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) batteryMetric$BatteryStatsDiff.toBuilder();
            EventSanitizer.ensureNoPiiName(EventSanitizer.BATTERY_METRIC_NAME_ACCESS, builder2);
            BatteryMetric$BatteryUsageMetric batteryMetric$BatteryUsageMetric3 = ((SystemHealthProto$SystemHealthMetric) builder.instance).batteryUsageMetric_;
            if (batteryMetric$BatteryUsageMetric3 == null) {
                batteryMetric$BatteryUsageMetric3 = BatteryMetric$BatteryUsageMetric.DEFAULT_INSTANCE;
            }
            SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) batteryMetric$BatteryUsageMetric3.toBuilder();
            builder3.copyOnWrite();
            BatteryMetric$BatteryUsageMetric batteryMetric$BatteryUsageMetric4 = (BatteryMetric$BatteryUsageMetric) builder3.instance;
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff2 = (BatteryMetric$BatteryStatsDiff) builder2.build();
            batteryMetric$BatteryStatsDiff2.getClass();
            batteryMetric$BatteryUsageMetric4.batteryStatsDiff_ = batteryMetric$BatteryStatsDiff2;
            batteryMetric$BatteryUsageMetric4.bitField0_ |= 1;
            builder.copyOnWrite();
            SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric2 = (SystemHealthProto$SystemHealthMetric) builder.instance;
            BatteryMetric$BatteryUsageMetric batteryMetric$BatteryUsageMetric5 = (BatteryMetric$BatteryUsageMetric) builder3.build();
            batteryMetric$BatteryUsageMetric5.getClass();
            systemHealthProto$SystemHealthMetric2.batteryUsageMetric_ = batteryMetric$BatteryUsageMetric5;
            systemHealthProto$SystemHealthMetric2.bitField0_ |= 256;
        }
        SystemHealthProto$CrashMetric systemHealthProto$CrashMetric = ((SystemHealthProto$SystemHealthMetric) builder.instance).crashMetric_;
        if (systemHealthProto$CrashMetric == null) {
            systemHealthProto$CrashMetric = SystemHealthProto$CrashMetric.DEFAULT_INSTANCE;
        }
        if ((systemHealthProto$CrashMetric.bitField0_ & 256) != 0) {
            SystemHealthProto$CrashMetric systemHealthProto$CrashMetric2 = ((SystemHealthProto$SystemHealthMetric) builder.instance).crashMetric_;
            if (systemHealthProto$CrashMetric2 == null) {
                systemHealthProto$CrashMetric2 = SystemHealthProto$CrashMetric.DEFAULT_INSTANCE;
            }
            Logrecord$ThrowableProto logrecord$ThrowableProto = systemHealthProto$CrashMetric2.exception_;
            if (logrecord$ThrowableProto == null) {
                logrecord$ThrowableProto = Logrecord$ThrowableProto.DEFAULT_INSTANCE;
            }
            SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) logrecord$ThrowableProto.toBuilder();
            Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto = ((Logrecord$ThrowableProto) builder4.instance).outermost_;
            if (logrecord$ThrowableBlockProto == null) {
                logrecord$ThrowableBlockProto = Logrecord$ThrowableBlockProto.DEFAULT_INSTANCE;
            }
            Logrecord$ThrowableBlockProto sanitizeThrowableBlock$ar$ds = EventSanitizer.sanitizeThrowableBlock$ar$ds(logrecord$ThrowableBlockProto);
            builder4.copyOnWrite();
            Logrecord$ThrowableProto logrecord$ThrowableProto2 = (Logrecord$ThrowableProto) builder4.instance;
            sanitizeThrowableBlock$ar$ds.getClass();
            logrecord$ThrowableProto2.outermost_ = sanitizeThrowableBlock$ar$ds;
            logrecord$ThrowableProto2.bitField0_ |= 1;
            List unmodifiableList = Collections.unmodifiableList(((Logrecord$ThrowableProto) builder4.instance).causes_);
            builder4.copyOnWrite();
            ((Logrecord$ThrowableProto) builder4.instance).causes_ = Logrecord$ThrowableProto.emptyProtobufList();
            Iterator it = unmodifiableList.iterator();
            while (it.hasNext()) {
                builder4.addCauses$ar$ds(EventSanitizer.sanitizeThrowableBlock$ar$ds((Logrecord$ThrowableBlockProto) it.next()));
            }
            Logrecord$ThrowableProto logrecord$ThrowableProto3 = (Logrecord$ThrowableProto) builder4.instance;
            if (logrecord$ThrowableProto3.kindCase_ == 4) {
                throwableGraph = (Logrecord$ThrowableProto.ThrowableGraph) logrecord$ThrowableProto3.kind_;
            } else {
                throwableGraph = Logrecord$ThrowableProto.ThrowableGraph.DEFAULT_INSTANCE;
            }
            Internal.ProtobufList<Logrecord$ThrowableProto.ThrowableNode> protobufList = throwableGraph.nodes_;
            SystemHealthProto$PackedHistogram.Builder builder5 = (SystemHealthProto$PackedHistogram.Builder) Logrecord$ThrowableProto.ThrowableGraph.DEFAULT_INSTANCE.createBuilder();
            for (Logrecord$ThrowableProto.ThrowableNode throwableNode : protobufList) {
                Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto2 = throwableNode.throwableInfo_;
                if (logrecord$ThrowableBlockProto2 == null) {
                    logrecord$ThrowableBlockProto2 = Logrecord$ThrowableBlockProto.DEFAULT_INSTANCE;
                }
                if ((logrecord$ThrowableBlockProto2.bitField0_ & 2) != 0) {
                    SystemHealthProto$PackedHistogram.Builder builder6 = (SystemHealthProto$PackedHistogram.Builder) throwableNode.toBuilder();
                    Logrecord$ThrowableBlockProto sanitizeThrowableBlock$ar$ds2 = EventSanitizer.sanitizeThrowableBlock$ar$ds(logrecord$ThrowableBlockProto2);
                    builder6.copyOnWrite();
                    Logrecord$ThrowableProto.ThrowableNode throwableNode2 = (Logrecord$ThrowableProto.ThrowableNode) builder6.instance;
                    sanitizeThrowableBlock$ar$ds2.getClass();
                    throwableNode2.throwableInfo_ = sanitizeThrowableBlock$ar$ds2;
                    throwableNode2.bitField0_ |= 1;
                    throwableNode = (Logrecord$ThrowableProto.ThrowableNode) builder6.build();
                }
                builder5.addNodes$ar$ds$aafd927b_0(throwableNode);
            }
            Logrecord$ThrowableProto.ThrowableGraph throwableGraph2 = (Logrecord$ThrowableProto.ThrowableGraph) builder5.build();
            builder4.copyOnWrite();
            Logrecord$ThrowableProto logrecord$ThrowableProto4 = (Logrecord$ThrowableProto) builder4.instance;
            throwableGraph2.getClass();
            logrecord$ThrowableProto4.kind_ = throwableGraph2;
            logrecord$ThrowableProto4.kindCase_ = 4;
            SystemHealthProto$CrashMetric systemHealthProto$CrashMetric3 = ((SystemHealthProto$SystemHealthMetric) builder.instance).crashMetric_;
            if (systemHealthProto$CrashMetric3 == null) {
                systemHealthProto$CrashMetric3 = SystemHealthProto$CrashMetric.DEFAULT_INSTANCE;
            }
            SystemHealthProto$PackedHistogram.Builder builder7 = (SystemHealthProto$PackedHistogram.Builder) systemHealthProto$CrashMetric3.toBuilder();
            Logrecord$ThrowableProto logrecord$ThrowableProto5 = (Logrecord$ThrowableProto) builder4.build();
            builder7.copyOnWrite();
            SystemHealthProto$CrashMetric systemHealthProto$CrashMetric4 = (SystemHealthProto$CrashMetric) builder7.instance;
            logrecord$ThrowableProto5.getClass();
            systemHealthProto$CrashMetric4.exception_ = logrecord$ThrowableProto5;
            systemHealthProto$CrashMetric4.bitField0_ |= 256;
            SystemHealthProto$CrashMetric systemHealthProto$CrashMetric5 = (SystemHealthProto$CrashMetric) builder7.build();
            builder.copyOnWrite();
            SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric3 = (SystemHealthProto$SystemHealthMetric) builder.instance;
            systemHealthProto$CrashMetric5.getClass();
            systemHealthProto$SystemHealthMetric3.crashMetric_ = systemHealthProto$CrashMetric5;
            systemHealthProto$SystemHealthMetric3.bitField0_ |= 64;
        }
        SystemHealthProto$PackageMetric systemHealthProto$PackageMetric = ((SystemHealthProto$SystemHealthMetric) builder.instance).packageMetric_;
        if (systemHealthProto$PackageMetric == null) {
            systemHealthProto$PackageMetric = SystemHealthProto$PackageMetric.DEFAULT_INSTANCE;
        }
        boolean z = false;
        if (systemHealthProto$PackageMetric.dirStats_.size() != 0) {
            SystemHealthProto$PackageMetric systemHealthProto$PackageMetric2 = ((SystemHealthProto$SystemHealthMetric) builder.instance).packageMetric_;
            if (systemHealthProto$PackageMetric2 == null) {
                systemHealthProto$PackageMetric2 = SystemHealthProto$PackageMetric.DEFAULT_INSTANCE;
            }
            SystemHealthProto$PackedHistogram.Builder builder8 = (SystemHealthProto$PackedHistogram.Builder) systemHealthProto$PackageMetric2.toBuilder();
            for (int i = 0; i < ((SystemHealthProto$PackageMetric) builder8.instance).dirStats_.size(); i++) {
                SystemHealthProto$PackedHistogram.Builder builder9 = (SystemHealthProto$PackedHistogram.Builder) ((SystemHealthProto$PackageMetric.DirStats) ((SystemHealthProto$PackageMetric) builder8.instance).dirStats_.get(i)).toBuilder();
                if (!((SystemHealthProto$PackageMetric.DirStats) builder9.instance).dirPath_.isEmpty()) {
                    builder9.copyOnWrite();
                    ((SystemHealthProto$PackageMetric.DirStats) builder9.instance).hashedDirPath_ = SystemHealthProto$PackageMetric.DirStats.emptyLongList();
                    List hashTokens = EventSanitizer.hashTokens(((SystemHealthProto$PackageMetric.DirStats) builder9.instance).dirPath_);
                    builder9.copyOnWrite();
                    SystemHealthProto$PackageMetric.DirStats dirStats = (SystemHealthProto$PackageMetric.DirStats) builder9.instance;
                    Internal.LongList longList = dirStats.hashedDirPath_;
                    if (!longList.isModifiable()) {
                        dirStats.hashedDirPath_ = GeneratedMessageLite.mutableCopy(longList);
                    }
                    AbstractMessageLite.addAll(hashTokens, dirStats.hashedDirPath_);
                }
                builder9.copyOnWrite();
                SystemHealthProto$PackageMetric.DirStats dirStats2 = (SystemHealthProto$PackageMetric.DirStats) builder9.instance;
                dirStats2.bitField0_ &= -2;
                dirStats2.dirPath_ = SystemHealthProto$PackageMetric.DirStats.DEFAULT_INSTANCE.dirPath_;
                builder8.copyOnWrite();
                SystemHealthProto$PackageMetric systemHealthProto$PackageMetric3 = (SystemHealthProto$PackageMetric) builder8.instance;
                SystemHealthProto$PackageMetric.DirStats dirStats3 = (SystemHealthProto$PackageMetric.DirStats) builder9.build();
                dirStats3.getClass();
                Internal.ProtobufList protobufList2 = systemHealthProto$PackageMetric3.dirStats_;
                if (!protobufList2.isModifiable()) {
                    systemHealthProto$PackageMetric3.dirStats_ = GeneratedMessageLite.mutableCopy(protobufList2);
                }
                systemHealthProto$PackageMetric3.dirStats_.set(i, dirStats3);
            }
            builder.copyOnWrite();
            SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric4 = (SystemHealthProto$SystemHealthMetric) builder.instance;
            SystemHealthProto$PackageMetric systemHealthProto$PackageMetric4 = (SystemHealthProto$PackageMetric) builder8.build();
            systemHealthProto$PackageMetric4.getClass();
            systemHealthProto$SystemHealthMetric4.packageMetric_ = systemHealthProto$PackageMetric4;
            systemHealthProto$SystemHealthMetric4.bitField0_ |= BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
        }
        NetworkMetric$NetworkUsageMetric networkMetric$NetworkUsageMetric = ((SystemHealthProto$SystemHealthMetric) builder.instance).networkUsageMetric_;
        if (networkMetric$NetworkUsageMetric == null) {
            networkMetric$NetworkUsageMetric = NetworkMetric$NetworkUsageMetric.DEFAULT_INSTANCE;
        }
        if (networkMetric$NetworkUsageMetric.networkEventUsage_.size() != 0) {
            NetworkMetric$NetworkUsageMetric networkMetric$NetworkUsageMetric2 = ((SystemHealthProto$SystemHealthMetric) builder.instance).networkUsageMetric_;
            if (networkMetric$NetworkUsageMetric2 == null) {
                networkMetric$NetworkUsageMetric2 = NetworkMetric$NetworkUsageMetric.DEFAULT_INSTANCE;
            }
            SystemHealthProto$PackedHistogram.Builder builder10 = (SystemHealthProto$PackedHistogram.Builder) networkMetric$NetworkUsageMetric2.toBuilder();
            for (int i2 = 0; i2 < ((NetworkMetric$NetworkUsageMetric) builder10.instance).networkEventUsage_.size(); i2++) {
                SystemHealthProto$PackedHistogram.Builder builder11 = (SystemHealthProto$PackedHistogram.Builder) ((NetworkMetric$NetworkEventUsage) ((NetworkMetric$NetworkUsageMetric) builder10.instance).networkEventUsage_.get(i2)).toBuilder();
                if (!((NetworkMetric$NetworkEventUsage) builder11.instance).rpcPath_.isEmpty()) {
                    builder11.copyOnWrite();
                    ((NetworkMetric$NetworkEventUsage) builder11.instance).hashedRpcPath_ = NetworkMetric$NetworkEventUsage.emptyLongList();
                    List hashTokens2 = EventSanitizer.hashTokens(((NetworkMetric$NetworkEventUsage) builder11.instance).rpcPath_);
                    builder11.copyOnWrite();
                    NetworkMetric$NetworkEventUsage networkMetric$NetworkEventUsage = (NetworkMetric$NetworkEventUsage) builder11.instance;
                    Internal.LongList longList2 = networkMetric$NetworkEventUsage.hashedRpcPath_;
                    if (!longList2.isModifiable()) {
                        networkMetric$NetworkEventUsage.hashedRpcPath_ = GeneratedMessageLite.mutableCopy(longList2);
                    }
                    AbstractMessageLite.addAll(hashTokens2, networkMetric$NetworkEventUsage.hashedRpcPath_);
                }
                builder11.copyOnWrite();
                NetworkMetric$NetworkEventUsage networkMetric$NetworkEventUsage2 = (NetworkMetric$NetworkEventUsage) builder11.instance;
                networkMetric$NetworkEventUsage2.bitField0_ &= -524289;
                networkMetric$NetworkEventUsage2.rpcPath_ = NetworkMetric$NetworkEventUsage.DEFAULT_INSTANCE.rpcPath_;
                builder10.setNetworkEventUsage$ar$ds$ar$class_merging$ar$class_merging(i2, builder11);
            }
            for (int i3 = 0; i3 < ((NetworkMetric$NetworkUsageMetric) builder10.instance).serverNetworkEventUsage_.size(); i3++) {
                SystemHealthProto$PackedHistogram.Builder builder12 = (SystemHealthProto$PackedHistogram.Builder) ((NetworkMetric$ServerNetworkEventUsage) ((NetworkMetric$NetworkUsageMetric) builder10.instance).serverNetworkEventUsage_.get(i3)).toBuilder();
                if (!((NetworkMetric$ServerNetworkEventUsage) builder12.instance).rpcPath_.isEmpty()) {
                    builder12.copyOnWrite();
                    ((NetworkMetric$ServerNetworkEventUsage) builder12.instance).hashedRpcPath_ = NetworkMetric$ServerNetworkEventUsage.emptyLongList();
                    List hashTokens3 = EventSanitizer.hashTokens(((NetworkMetric$ServerNetworkEventUsage) builder12.instance).rpcPath_);
                    builder12.copyOnWrite();
                    NetworkMetric$ServerNetworkEventUsage networkMetric$ServerNetworkEventUsage = (NetworkMetric$ServerNetworkEventUsage) builder12.instance;
                    Internal.LongList longList3 = networkMetric$ServerNetworkEventUsage.hashedRpcPath_;
                    if (!longList3.isModifiable()) {
                        networkMetric$ServerNetworkEventUsage.hashedRpcPath_ = GeneratedMessageLite.mutableCopy(longList3);
                    }
                    AbstractMessageLite.addAll(hashTokens3, networkMetric$ServerNetworkEventUsage.hashedRpcPath_);
                }
                builder12.copyOnWrite();
                NetworkMetric$ServerNetworkEventUsage networkMetric$ServerNetworkEventUsage2 = (NetworkMetric$ServerNetworkEventUsage) builder12.instance;
                networkMetric$ServerNetworkEventUsage2.bitField0_ &= -2;
                networkMetric$ServerNetworkEventUsage2.rpcPath_ = NetworkMetric$ServerNetworkEventUsage.DEFAULT_INSTANCE.rpcPath_;
                builder10.copyOnWrite();
                NetworkMetric$NetworkUsageMetric networkMetric$NetworkUsageMetric3 = (NetworkMetric$NetworkUsageMetric) builder10.instance;
                NetworkMetric$ServerNetworkEventUsage networkMetric$ServerNetworkEventUsage3 = (NetworkMetric$ServerNetworkEventUsage) builder12.build();
                networkMetric$ServerNetworkEventUsage3.getClass();
                Internal.ProtobufList protobufList3 = networkMetric$NetworkUsageMetric3.serverNetworkEventUsage_;
                if (!protobufList3.isModifiable()) {
                    networkMetric$NetworkUsageMetric3.serverNetworkEventUsage_ = GeneratedMessageLite.mutableCopy(protobufList3);
                }
                networkMetric$NetworkUsageMetric3.serverNetworkEventUsage_.set(i3, networkMetric$ServerNetworkEventUsage3);
            }
            builder.copyOnWrite();
            SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric5 = (SystemHealthProto$SystemHealthMetric) builder.instance;
            NetworkMetric$NetworkUsageMetric networkMetric$NetworkUsageMetric4 = (NetworkMetric$NetworkUsageMetric) builder10.build();
            networkMetric$NetworkUsageMetric4.getClass();
            systemHealthProto$SystemHealthMetric5.networkUsageMetric_ = networkMetric$NetworkUsageMetric4;
            systemHealthProto$SystemHealthMetric5.bitField0_ |= 32;
        }
        PrimesTraceOuterClass$PrimesTrace primesTraceOuterClass$PrimesTrace = ((SystemHealthProto$SystemHealthMetric) builder.instance).primesTrace_;
        if (primesTraceOuterClass$PrimesTrace == null) {
            primesTraceOuterClass$PrimesTrace = PrimesTraceOuterClass$PrimesTrace.DEFAULT_INSTANCE;
        }
        if (primesTraceOuterClass$PrimesTrace.spans_.size() != 0) {
            PrimesTraceOuterClass$PrimesTrace primesTraceOuterClass$PrimesTrace2 = ((SystemHealthProto$SystemHealthMetric) builder.instance).primesTrace_;
            if (primesTraceOuterClass$PrimesTrace2 == null) {
                primesTraceOuterClass$PrimesTrace2 = PrimesTraceOuterClass$PrimesTrace.DEFAULT_INSTANCE;
            }
            SystemHealthProto$PackedHistogram.Builder builder13 = (SystemHealthProto$PackedHistogram.Builder) primesTraceOuterClass$PrimesTrace2.toBuilder();
            for (int i4 = 0; i4 < ((PrimesTraceOuterClass$PrimesTrace) builder13.instance).spans_.size(); i4++) {
                SchedulerOptions.Builder builder14 = (SchedulerOptions.Builder) ((PrimesTraceOuterClass$Span) ((PrimesTraceOuterClass$PrimesTrace) builder13.instance).spans_.get(i4)).toBuilder();
                EventSanitizer.ensureNoPiiName(EventSanitizer.SPAN_METRIC_NAME_ACCESS, builder14);
                builder13.setSpans$ar$ds$ar$class_merging$ar$class_merging(i4, builder14);
            }
            builder.copyOnWrite();
            SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric6 = (SystemHealthProto$SystemHealthMetric) builder.instance;
            PrimesTraceOuterClass$PrimesTrace primesTraceOuterClass$PrimesTrace3 = (PrimesTraceOuterClass$PrimesTrace) builder13.build();
            primesTraceOuterClass$PrimesTrace3.getClass();
            systemHealthProto$SystemHealthMetric6.primesTrace_ = primesTraceOuterClass$PrimesTrace3;
            systemHealthProto$SystemHealthMetric6.bitField0_ |= 2048;
        }
        final SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric7 = (SystemHealthProto$SystemHealthMetric) builder.build();
        if (((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFinest()).isEnabled()) {
            int i5 = systemHealthProto$SystemHealthMetric7.bitField0_;
            if ((4194304 & i5) == 0) {
                str = null;
            } else {
                str = "primes stats";
            }
            int i6 = i5 & 32;
            int i7 = i5 & 16;
            int i8 = i5 & 8;
            int i9 = i5 & 256;
            int i10 = i5 & 64;
            int i11 = i5 & 1024;
            int i12 = i5 & BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
            int i13 = i5 & 2048;
            if (i6 != 0) {
                str = "network metric";
            }
            if (i7 != 0) {
                str = "timer metric";
            }
            if (i8 != 0) {
                str = "memory metric";
            }
            if (i9 != 0) {
                str = "battery metric";
            }
            if (i10 != 0) {
                str = "crash metric";
            }
            if (i11 != 0) {
                str = "jank metric";
            }
            if (i12 != 0) {
                str = "package metric";
            }
            if (i13 != 0) {
                str = "trace";
            }
            GoogleLogger.Api api = (GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFinest()).withInjectedLogSite("com/google/android/libraries/performance/primes/transmitter/clearcut/ClearcutMetricSnapshotTransmitter", "logSystemHealthMetric", 219, "ClearcutMetricSnapshotTransmitter.java");
            if (str == null) {
                str = "unknown";
            }
            api.log("Sending Primes %s: %s", str, systemHealthProto$SystemHealthMetric7);
        }
        if (!((Boolean) this.isUserAMonkeyOrRunningInTestHarness.get()).booleanValue()) {
            GeneratedMessageLite.GeneratedExtension checkIsLite2 = GeneratedMessageLite.checkIsLite(ClearcutMetricSnapshot.clearcutMetricSnapshot);
            metricSnapshot.verifyExtensionContainingType(checkIsLite2);
            Object field$ar$class_merging = metricSnapshot.extensions.getField$ar$class_merging(checkIsLite2.descriptor);
            if (field$ar$class_merging == null) {
                fromFieldSetType = checkIsLite2.defaultValue;
            } else {
                fromFieldSetType = checkIsLite2.fromFieldSetType(field$ar$class_merging);
            }
            final ClearcutMetricSnapshot clearcutMetricSnapshot = (ClearcutMetricSnapshot) fromFieldSetType;
            if (((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFinest()).isEnabled()) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFinest()).withInjectedLogSite("com/google/android/libraries/performance/primes/transmitter/clearcut/ClearcutMetricSnapshotTransmitter", "transmit", BrailleInputEvent.CMD_SWITCH_TO_NEXT_INPUT_LANGUAGE, "ClearcutMetricSnapshotTransmitter.java")).log("%s", Base64.encodeToString(systemHealthProto$SystemHealthMetric7.toByteArray(), 2));
            }
            boolean z2 = clearcutMetricSnapshot.requireCheckbox_;
            if ((systemHealthProto$SystemHealthMetric7.bitField0_ & 64) != 0) {
                z = true;
            }
            final CheckboxChecker checkboxChecker = this.checkboxChecker;
            if (checkboxChecker == null) {
                synchronized (this) {
                    checkboxChecker = this.checkboxChecker;
                    if (checkboxChecker == null) {
                        checkboxChecker = new CheckboxChecker();
                        this.checkboxChecker = checkboxChecker;
                    }
                }
            }
            boolean z3 = !z;
            if (!z2) {
                create = ContextDataProvider.immediateFuture(true);
            } else {
                Boolean bool = (Boolean) checkboxChecker.checkboxEnabled.get();
                if (bool != null) {
                    create = ContextDataProvider.immediateFuture(bool);
                } else {
                    InternalUsageReportingClient internalUsageReportingClient = checkboxChecker.usageReportingClient$ar$class_merging;
                    if (internalUsageReportingClient == null) {
                        synchronized (checkboxChecker) {
                            internalUsageReportingClient = checkboxChecker.usageReportingClient$ar$class_merging;
                            if (internalUsageReportingClient == null) {
                                internalUsageReportingClient = new InternalUsageReportingClient(context, new UsageReporting.UsageReportingOptions());
                                checkboxChecker.usageReportingClient$ar$class_merging = internalUsageReportingClient;
                            }
                        }
                    }
                    if (z3 && !checkboxChecker.usageReportingOptInOptionsChangedListenerAttached.getAndSet(true)) {
                        internalUsageReportingClient.setOptInOptionsChangedListener$ar$ds(new UsageReportingApi$OptInOptionsChangedListener() { // from class: com.google.android.libraries.performance.primes.transmitter.clearcut.CheckboxChecker$$ExternalSyntheticLambda0
                            @Override // com.google.android.gms.usagereporting.UsageReportingApi$OptInOptionsChangedListener
                            public final void onOptInOptionsChanged() {
                                CheckboxChecker.this.checkboxEnabled.set(null);
                            }
                        });
                    }
                    create = AbstractCatchingFuture.create(AbstractTransformFuture.create(GwtFluentFutureCatchingSpecialization.from$ar$class_merging(SpannableUtils$NonCopyableTextSpan.toListenableFuture(internalUsageReportingClient.getOptInOptions())), TracePropagation.propagateFunction(new AiCoreClientImpl$$ExternalSyntheticLambda2(checkboxChecker, 3)), DirectExecutor.INSTANCE), Throwable.class, new AccessibilityEventUtils$$ExternalSyntheticLambda0(19), DirectExecutor.INSTANCE);
                }
            }
            return AbstractTransformFuture.create(create, new AsyncFunction() { // from class: com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricSnapshotTransmitter$$ExternalSyntheticLambda2
                @Override // com.google.common.util.concurrent.AsyncFunction
                public final ListenableFuture apply(Object obj) {
                    ClearcutLogger clearcutLogger;
                    if (((Boolean) obj).booleanValue()) {
                        ClearcutMetricSnapshot clearcutMetricSnapshot2 = clearcutMetricSnapshot;
                        Context context2 = context;
                        ClearcutMetricSnapshotTransmitter clearcutMetricSnapshotTransmitter = ClearcutMetricSnapshotTransmitter.this;
                        String str2 = clearcutMetricSnapshot2.logSource_;
                        if (clearcutMetricSnapshot2.anonymous_) {
                            clearcutLogger = clearcutMetricSnapshotTransmitter.deidentifiedClearcutLogger;
                            if (clearcutLogger == null) {
                                synchronized (clearcutMetricSnapshotTransmitter) {
                                    clearcutLogger = clearcutMetricSnapshotTransmitter.deidentifiedClearcutLogger;
                                    if (clearcutLogger == null) {
                                        ClearcutLogger build = new SnackbarManager(context2, str2, null).setPiiLevelSet$ar$class_merging$ar$class_merging$ar$class_merging(PIILevel.deidentified).build();
                                        clearcutMetricSnapshotTransmitter.deidentifiedClearcutLogger = build;
                                        clearcutLogger = build;
                                    }
                                }
                            }
                        } else {
                            clearcutLogger = clearcutMetricSnapshotTransmitter.clearcutLogger;
                            if (clearcutLogger == null) {
                                synchronized (clearcutMetricSnapshotTransmitter) {
                                    clearcutLogger = clearcutMetricSnapshotTransmitter.clearcutLogger;
                                    if (clearcutLogger == null) {
                                        ClearcutLogger build2 = new SnackbarManager(context2, str2, null).build();
                                        clearcutMetricSnapshotTransmitter.clearcutLogger = build2;
                                        clearcutLogger = build2;
                                    }
                                }
                            }
                        }
                        ClearcutLogger.LogEventBuilder newEvent = clearcutLogger.newEvent(systemHealthProto$SystemHealthMetric7);
                        if (MetricTransmitterFeature.INSTANCE.get().enableDelphiCollectionBasisLogVerifier(context2)) {
                            newEvent.logVerifier$ar$class_merging = CollectionBasisLogVerifier.newInstance$ar$class_merging$30765897_0(context2, (BaseProtoCollectionBasis) ClearcutMetricSnapshotTransmitter.PROTO_COLLECTION_BASIS.get());
                        }
                        if (!clearcutMetricSnapshot2.anonymous_) {
                            String str3 = clearcutMetricSnapshot2.zwiebackCookieOverride_;
                            if (!ContextDataProvider.stringIsNullOrEmpty(str3)) {
                                if (!newEvent.logger.isDeidentified()) {
                                    SchedulerOptions.Builder builder15 = newEvent.logEvent$ar$class_merging$ar$class_merging;
                                    builder15.copyOnWrite();
                                    ClientAnalytics$LogEvent clientAnalytics$LogEvent = (ClientAnalytics$LogEvent) builder15.instance;
                                    ClientAnalytics$LogEvent clientAnalytics$LogEvent2 = ClientAnalytics$LogEvent.DEFAULT_INSTANCE;
                                    str3.getClass();
                                    clientAnalytics$LogEvent.bitField0_ |= 16777216;
                                    clientAnalytics$LogEvent.zwiebackCookieOverride_ = str3;
                                } else {
                                    throw new IllegalStateException("setZwiebackCookieOverride forbidden on deidentified logger");
                                }
                            }
                            if ((clearcutMetricSnapshot2.bitField0_ & 2) != 0) {
                                String str4 = clearcutMetricSnapshot2.mendelPackageName_;
                                if (!newEvent.logger.isDeidentified()) {
                                    if (newEvent.mendelPackages == null) {
                                        newEvent.mendelPackages = new ArrayList();
                                    }
                                    newEvent.mendelPackages.add(str4);
                                } else {
                                    throw new IllegalArgumentException("addMendelPackage forbidden on deidentified logger");
                                }
                            }
                            if ((clearcutMetricSnapshot2.bitField0_ & 16) != 0) {
                                String str5 = clearcutMetricSnapshot2.uploadAccountName_;
                                if (newEvent.logger.piiLevelSet.contains(PIILevel.ACCOUNT_NAME)) {
                                    newEvent.uploadAccountName = str5;
                                } else {
                                    throw new IllegalStateException("setUploadAccountName forbidden on deidentified logger");
                                }
                            }
                            Internal.IntList intList = clearcutMetricSnapshot2.experimentIds_;
                            if (!intList.isEmpty()) {
                                Object[] array = intList.toArray();
                                int length = array.length;
                                int[] iArr = new int[length];
                                for (int i14 = 0; i14 < length; i14++) {
                                    Object obj2 = array[i14];
                                    obj2.getClass();
                                    iArr[i14] = ((Number) obj2).intValue();
                                }
                                newEvent.addExperimentIds$ar$ds(iArr);
                            }
                        }
                        return SpannableUtils$NonCopyableTextSpan.toListenableFuture(PendingResultUtil.toVoidTask(newEvent.logAsync()));
                    }
                    return ImmediateFuture.NULL;
                }
            }, DirectExecutor.INSTANCE);
        }
        return ImmediateFuture.NULL;
    }
}
