package com.google.android.libraries.performance.primes.transmitter.impl;

import com.google.android.accessibility.utils.AccessibilityEventUtils$$ExternalSyntheticLambda0;
import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.common.base.Splitter;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.devrel.primes.hashing.Hashing;
import com.google.protobuf.MessageLite;
import java.util.List;
import logs.proto.wireless.performance.mobile.BatteryMetric$BatteryStatsDiff;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$Span;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EventSanitizer {
    private static final Splitter SLASH_SPLITTER = Splitter.on('/').omitEmptyStrings();
    public static final MetricNameAccess SHM_METRIC_NAME_ACCESS = new MetricNameAccess() { // from class: com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.2
        private final /* synthetic */ int switching_field;

        public AnonymousClass2(int i) {
            r1 = i;
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public final /* synthetic */ String getConstantName(MessageLite.Builder builder) {
            int i = r1;
            if (i != 0) {
                if (i != 1) {
                    return ((PrimesTraceOuterClass$Span) ((SchedulerOptions.Builder) builder).instance).constantName_;
                }
                return ((SystemHealthProto$SystemHealthMetric) ((SystemHealthProto$PackedHistogram.Builder) builder).instance).constantEventName_;
            }
            return ((BatteryMetric$BatteryStatsDiff) ((SystemHealthProto$PackedHistogram.Builder) builder).instance).startConstantEventName_;
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public final /* synthetic */ String getCustomName(MessageLite.Builder builder) {
            int i = r1;
            if (i != 0) {
                if (i != 1) {
                    return ((PrimesTraceOuterClass$Span) ((SchedulerOptions.Builder) builder).instance).name_;
                }
                return ((SystemHealthProto$SystemHealthMetric) ((SystemHealthProto$PackedHistogram.Builder) builder).instance).customEventName_;
            }
            return ((BatteryMetric$BatteryStatsDiff) ((SystemHealthProto$PackedHistogram.Builder) builder).instance).startCustomEventName_;
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public final /* synthetic */ void setCustomName$ar$ds(MessageLite.Builder builder) {
            int i = r1;
            if (i != 0) {
                if (i != 1) {
                    SchedulerOptions.Builder builder2 = (SchedulerOptions.Builder) builder;
                    builder2.copyOnWrite();
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span = (PrimesTraceOuterClass$Span) builder2.instance;
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span2 = PrimesTraceOuterClass$Span.DEFAULT_INSTANCE;
                    primesTraceOuterClass$Span.bitField0_ &= -5;
                    primesTraceOuterClass$Span.name_ = PrimesTraceOuterClass$Span.DEFAULT_INSTANCE.name_;
                    return;
                }
                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) builder;
                builder3.copyOnWrite();
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder3.instance;
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric2 = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE;
                systemHealthProto$SystemHealthMetric.bitField0_ &= -3;
                systemHealthProto$SystemHealthMetric.customEventName_ = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE.customEventName_;
                return;
            }
            SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) builder;
            builder4.copyOnWrite();
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff = (BatteryMetric$BatteryStatsDiff) builder4.instance;
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff2 = BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE;
            batteryMetric$BatteryStatsDiff.bitField0_ &= -5;
            batteryMetric$BatteryStatsDiff.startCustomEventName_ = BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE.startCustomEventName_;
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public final /* synthetic */ void setHashedName(MessageLite.Builder builder, Long l) {
            int i = r1;
            if (i != 0) {
                if (i != 1) {
                    SchedulerOptions.Builder builder2 = (SchedulerOptions.Builder) builder;
                    if (l == null) {
                        builder2.copyOnWrite();
                        PrimesTraceOuterClass$Span primesTraceOuterClass$Span = (PrimesTraceOuterClass$Span) builder2.instance;
                        PrimesTraceOuterClass$Span primesTraceOuterClass$Span2 = PrimesTraceOuterClass$Span.DEFAULT_INSTANCE;
                        primesTraceOuterClass$Span.bitField0_ &= -3;
                        primesTraceOuterClass$Span.hashedName_ = 0L;
                        return;
                    }
                    long longValue = l.longValue();
                    builder2.copyOnWrite();
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span3 = (PrimesTraceOuterClass$Span) builder2.instance;
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span4 = PrimesTraceOuterClass$Span.DEFAULT_INSTANCE;
                    primesTraceOuterClass$Span3.bitField0_ |= 2;
                    primesTraceOuterClass$Span3.hashedName_ = longValue;
                    return;
                }
                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) builder;
                if (l != null) {
                    long longValue2 = l.longValue();
                    builder3.copyOnWrite();
                    SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder3.instance;
                    SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric2 = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE;
                    systemHealthProto$SystemHealthMetric.bitField0_ |= 1;
                    systemHealthProto$SystemHealthMetric.hashedCustomEventName_ = longValue2;
                    return;
                }
                builder3.copyOnWrite();
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric3 = (SystemHealthProto$SystemHealthMetric) builder3.instance;
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric4 = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE;
                systemHealthProto$SystemHealthMetric3.bitField0_ &= -2;
                systemHealthProto$SystemHealthMetric3.hashedCustomEventName_ = 0L;
                return;
            }
            SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) builder;
            if (l != null) {
                long longValue3 = l.longValue();
                builder4.copyOnWrite();
                BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff = (BatteryMetric$BatteryStatsDiff) builder4.instance;
                BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff2 = BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE;
                batteryMetric$BatteryStatsDiff.bitField0_ |= 2;
                batteryMetric$BatteryStatsDiff.startHashedCustomEventName_ = longValue3;
                return;
            }
            builder4.copyOnWrite();
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff3 = (BatteryMetric$BatteryStatsDiff) builder4.instance;
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff4 = BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE;
            batteryMetric$BatteryStatsDiff3.bitField0_ &= -3;
            batteryMetric$BatteryStatsDiff3.startHashedCustomEventName_ = 0L;
        }
    };
    public static final MetricNameAccess BATTERY_METRIC_NAME_ACCESS = new MetricNameAccess() { // from class: com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.2
        private final /* synthetic */ int switching_field;

        public AnonymousClass2(int i) {
            r1 = i;
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public final /* synthetic */ String getConstantName(MessageLite.Builder builder) {
            int i = r1;
            if (i != 0) {
                if (i != 1) {
                    return ((PrimesTraceOuterClass$Span) ((SchedulerOptions.Builder) builder).instance).constantName_;
                }
                return ((SystemHealthProto$SystemHealthMetric) ((SystemHealthProto$PackedHistogram.Builder) builder).instance).constantEventName_;
            }
            return ((BatteryMetric$BatteryStatsDiff) ((SystemHealthProto$PackedHistogram.Builder) builder).instance).startConstantEventName_;
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public final /* synthetic */ String getCustomName(MessageLite.Builder builder) {
            int i = r1;
            if (i != 0) {
                if (i != 1) {
                    return ((PrimesTraceOuterClass$Span) ((SchedulerOptions.Builder) builder).instance).name_;
                }
                return ((SystemHealthProto$SystemHealthMetric) ((SystemHealthProto$PackedHistogram.Builder) builder).instance).customEventName_;
            }
            return ((BatteryMetric$BatteryStatsDiff) ((SystemHealthProto$PackedHistogram.Builder) builder).instance).startCustomEventName_;
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public final /* synthetic */ void setCustomName$ar$ds(MessageLite.Builder builder) {
            int i = r1;
            if (i != 0) {
                if (i != 1) {
                    SchedulerOptions.Builder builder2 = (SchedulerOptions.Builder) builder;
                    builder2.copyOnWrite();
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span = (PrimesTraceOuterClass$Span) builder2.instance;
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span2 = PrimesTraceOuterClass$Span.DEFAULT_INSTANCE;
                    primesTraceOuterClass$Span.bitField0_ &= -5;
                    primesTraceOuterClass$Span.name_ = PrimesTraceOuterClass$Span.DEFAULT_INSTANCE.name_;
                    return;
                }
                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) builder;
                builder3.copyOnWrite();
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder3.instance;
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric2 = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE;
                systemHealthProto$SystemHealthMetric.bitField0_ &= -3;
                systemHealthProto$SystemHealthMetric.customEventName_ = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE.customEventName_;
                return;
            }
            SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) builder;
            builder4.copyOnWrite();
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff = (BatteryMetric$BatteryStatsDiff) builder4.instance;
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff2 = BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE;
            batteryMetric$BatteryStatsDiff.bitField0_ &= -5;
            batteryMetric$BatteryStatsDiff.startCustomEventName_ = BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE.startCustomEventName_;
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public final /* synthetic */ void setHashedName(MessageLite.Builder builder, Long l) {
            int i = r1;
            if (i != 0) {
                if (i != 1) {
                    SchedulerOptions.Builder builder2 = (SchedulerOptions.Builder) builder;
                    if (l == null) {
                        builder2.copyOnWrite();
                        PrimesTraceOuterClass$Span primesTraceOuterClass$Span = (PrimesTraceOuterClass$Span) builder2.instance;
                        PrimesTraceOuterClass$Span primesTraceOuterClass$Span2 = PrimesTraceOuterClass$Span.DEFAULT_INSTANCE;
                        primesTraceOuterClass$Span.bitField0_ &= -3;
                        primesTraceOuterClass$Span.hashedName_ = 0L;
                        return;
                    }
                    long longValue = l.longValue();
                    builder2.copyOnWrite();
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span3 = (PrimesTraceOuterClass$Span) builder2.instance;
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span4 = PrimesTraceOuterClass$Span.DEFAULT_INSTANCE;
                    primesTraceOuterClass$Span3.bitField0_ |= 2;
                    primesTraceOuterClass$Span3.hashedName_ = longValue;
                    return;
                }
                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) builder;
                if (l != null) {
                    long longValue2 = l.longValue();
                    builder3.copyOnWrite();
                    SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder3.instance;
                    SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric2 = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE;
                    systemHealthProto$SystemHealthMetric.bitField0_ |= 1;
                    systemHealthProto$SystemHealthMetric.hashedCustomEventName_ = longValue2;
                    return;
                }
                builder3.copyOnWrite();
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric3 = (SystemHealthProto$SystemHealthMetric) builder3.instance;
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric4 = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE;
                systemHealthProto$SystemHealthMetric3.bitField0_ &= -2;
                systemHealthProto$SystemHealthMetric3.hashedCustomEventName_ = 0L;
                return;
            }
            SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) builder;
            if (l != null) {
                long longValue3 = l.longValue();
                builder4.copyOnWrite();
                BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff = (BatteryMetric$BatteryStatsDiff) builder4.instance;
                BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff2 = BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE;
                batteryMetric$BatteryStatsDiff.bitField0_ |= 2;
                batteryMetric$BatteryStatsDiff.startHashedCustomEventName_ = longValue3;
                return;
            }
            builder4.copyOnWrite();
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff3 = (BatteryMetric$BatteryStatsDiff) builder4.instance;
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff4 = BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE;
            batteryMetric$BatteryStatsDiff3.bitField0_ &= -3;
            batteryMetric$BatteryStatsDiff3.startHashedCustomEventName_ = 0L;
        }
    };
    public static final MetricNameAccess SPAN_METRIC_NAME_ACCESS = new MetricNameAccess() { // from class: com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.2
        private final /* synthetic */ int switching_field;

        public AnonymousClass2(int i) {
            r1 = i;
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public final /* synthetic */ String getConstantName(MessageLite.Builder builder) {
            int i = r1;
            if (i != 0) {
                if (i != 1) {
                    return ((PrimesTraceOuterClass$Span) ((SchedulerOptions.Builder) builder).instance).constantName_;
                }
                return ((SystemHealthProto$SystemHealthMetric) ((SystemHealthProto$PackedHistogram.Builder) builder).instance).constantEventName_;
            }
            return ((BatteryMetric$BatteryStatsDiff) ((SystemHealthProto$PackedHistogram.Builder) builder).instance).startConstantEventName_;
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public final /* synthetic */ String getCustomName(MessageLite.Builder builder) {
            int i = r1;
            if (i != 0) {
                if (i != 1) {
                    return ((PrimesTraceOuterClass$Span) ((SchedulerOptions.Builder) builder).instance).name_;
                }
                return ((SystemHealthProto$SystemHealthMetric) ((SystemHealthProto$PackedHistogram.Builder) builder).instance).customEventName_;
            }
            return ((BatteryMetric$BatteryStatsDiff) ((SystemHealthProto$PackedHistogram.Builder) builder).instance).startCustomEventName_;
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public final /* synthetic */ void setCustomName$ar$ds(MessageLite.Builder builder) {
            int i = r1;
            if (i != 0) {
                if (i != 1) {
                    SchedulerOptions.Builder builder2 = (SchedulerOptions.Builder) builder;
                    builder2.copyOnWrite();
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span = (PrimesTraceOuterClass$Span) builder2.instance;
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span2 = PrimesTraceOuterClass$Span.DEFAULT_INSTANCE;
                    primesTraceOuterClass$Span.bitField0_ &= -5;
                    primesTraceOuterClass$Span.name_ = PrimesTraceOuterClass$Span.DEFAULT_INSTANCE.name_;
                    return;
                }
                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) builder;
                builder3.copyOnWrite();
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder3.instance;
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric2 = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE;
                systemHealthProto$SystemHealthMetric.bitField0_ &= -3;
                systemHealthProto$SystemHealthMetric.customEventName_ = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE.customEventName_;
                return;
            }
            SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) builder;
            builder4.copyOnWrite();
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff = (BatteryMetric$BatteryStatsDiff) builder4.instance;
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff2 = BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE;
            batteryMetric$BatteryStatsDiff.bitField0_ &= -5;
            batteryMetric$BatteryStatsDiff.startCustomEventName_ = BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE.startCustomEventName_;
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public final /* synthetic */ void setHashedName(MessageLite.Builder builder, Long l) {
            int i = r1;
            if (i != 0) {
                if (i != 1) {
                    SchedulerOptions.Builder builder2 = (SchedulerOptions.Builder) builder;
                    if (l == null) {
                        builder2.copyOnWrite();
                        PrimesTraceOuterClass$Span primesTraceOuterClass$Span = (PrimesTraceOuterClass$Span) builder2.instance;
                        PrimesTraceOuterClass$Span primesTraceOuterClass$Span2 = PrimesTraceOuterClass$Span.DEFAULT_INSTANCE;
                        primesTraceOuterClass$Span.bitField0_ &= -3;
                        primesTraceOuterClass$Span.hashedName_ = 0L;
                        return;
                    }
                    long longValue = l.longValue();
                    builder2.copyOnWrite();
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span3 = (PrimesTraceOuterClass$Span) builder2.instance;
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span4 = PrimesTraceOuterClass$Span.DEFAULT_INSTANCE;
                    primesTraceOuterClass$Span3.bitField0_ |= 2;
                    primesTraceOuterClass$Span3.hashedName_ = longValue;
                    return;
                }
                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) builder;
                if (l != null) {
                    long longValue2 = l.longValue();
                    builder3.copyOnWrite();
                    SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder3.instance;
                    SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric2 = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE;
                    systemHealthProto$SystemHealthMetric.bitField0_ |= 1;
                    systemHealthProto$SystemHealthMetric.hashedCustomEventName_ = longValue2;
                    return;
                }
                builder3.copyOnWrite();
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric3 = (SystemHealthProto$SystemHealthMetric) builder3.instance;
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric4 = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE;
                systemHealthProto$SystemHealthMetric3.bitField0_ &= -2;
                systemHealthProto$SystemHealthMetric3.hashedCustomEventName_ = 0L;
                return;
            }
            SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) builder;
            if (l != null) {
                long longValue3 = l.longValue();
                builder4.copyOnWrite();
                BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff = (BatteryMetric$BatteryStatsDiff) builder4.instance;
                BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff2 = BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE;
                batteryMetric$BatteryStatsDiff.bitField0_ |= 2;
                batteryMetric$BatteryStatsDiff.startHashedCustomEventName_ = longValue3;
                return;
            }
            builder4.copyOnWrite();
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff3 = (BatteryMetric$BatteryStatsDiff) builder4.instance;
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff4 = BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE;
            batteryMetric$BatteryStatsDiff3.bitField0_ &= -3;
            batteryMetric$BatteryStatsDiff3.startHashedCustomEventName_ = 0L;
        }
    };

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer$2 */
    /* loaded from: classes.dex */
    final class AnonymousClass2 implements MetricNameAccess {
        private final /* synthetic */ int switching_field;

        public AnonymousClass2(int i) {
            r1 = i;
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public final /* synthetic */ String getConstantName(MessageLite.Builder builder) {
            int i = r1;
            if (i != 0) {
                if (i != 1) {
                    return ((PrimesTraceOuterClass$Span) ((SchedulerOptions.Builder) builder).instance).constantName_;
                }
                return ((SystemHealthProto$SystemHealthMetric) ((SystemHealthProto$PackedHistogram.Builder) builder).instance).constantEventName_;
            }
            return ((BatteryMetric$BatteryStatsDiff) ((SystemHealthProto$PackedHistogram.Builder) builder).instance).startConstantEventName_;
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public final /* synthetic */ String getCustomName(MessageLite.Builder builder) {
            int i = r1;
            if (i != 0) {
                if (i != 1) {
                    return ((PrimesTraceOuterClass$Span) ((SchedulerOptions.Builder) builder).instance).name_;
                }
                return ((SystemHealthProto$SystemHealthMetric) ((SystemHealthProto$PackedHistogram.Builder) builder).instance).customEventName_;
            }
            return ((BatteryMetric$BatteryStatsDiff) ((SystemHealthProto$PackedHistogram.Builder) builder).instance).startCustomEventName_;
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public final /* synthetic */ void setCustomName$ar$ds(MessageLite.Builder builder) {
            int i = r1;
            if (i != 0) {
                if (i != 1) {
                    SchedulerOptions.Builder builder2 = (SchedulerOptions.Builder) builder;
                    builder2.copyOnWrite();
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span = (PrimesTraceOuterClass$Span) builder2.instance;
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span2 = PrimesTraceOuterClass$Span.DEFAULT_INSTANCE;
                    primesTraceOuterClass$Span.bitField0_ &= -5;
                    primesTraceOuterClass$Span.name_ = PrimesTraceOuterClass$Span.DEFAULT_INSTANCE.name_;
                    return;
                }
                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) builder;
                builder3.copyOnWrite();
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder3.instance;
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric2 = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE;
                systemHealthProto$SystemHealthMetric.bitField0_ &= -3;
                systemHealthProto$SystemHealthMetric.customEventName_ = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE.customEventName_;
                return;
            }
            SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) builder;
            builder4.copyOnWrite();
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff = (BatteryMetric$BatteryStatsDiff) builder4.instance;
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff2 = BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE;
            batteryMetric$BatteryStatsDiff.bitField0_ &= -5;
            batteryMetric$BatteryStatsDiff.startCustomEventName_ = BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE.startCustomEventName_;
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public final /* synthetic */ void setHashedName(MessageLite.Builder builder, Long l) {
            int i = r1;
            if (i != 0) {
                if (i != 1) {
                    SchedulerOptions.Builder builder2 = (SchedulerOptions.Builder) builder;
                    if (l == null) {
                        builder2.copyOnWrite();
                        PrimesTraceOuterClass$Span primesTraceOuterClass$Span = (PrimesTraceOuterClass$Span) builder2.instance;
                        PrimesTraceOuterClass$Span primesTraceOuterClass$Span2 = PrimesTraceOuterClass$Span.DEFAULT_INSTANCE;
                        primesTraceOuterClass$Span.bitField0_ &= -3;
                        primesTraceOuterClass$Span.hashedName_ = 0L;
                        return;
                    }
                    long longValue = l.longValue();
                    builder2.copyOnWrite();
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span3 = (PrimesTraceOuterClass$Span) builder2.instance;
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span4 = PrimesTraceOuterClass$Span.DEFAULT_INSTANCE;
                    primesTraceOuterClass$Span3.bitField0_ |= 2;
                    primesTraceOuterClass$Span3.hashedName_ = longValue;
                    return;
                }
                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) builder;
                if (l != null) {
                    long longValue2 = l.longValue();
                    builder3.copyOnWrite();
                    SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder3.instance;
                    SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric2 = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE;
                    systemHealthProto$SystemHealthMetric.bitField0_ |= 1;
                    systemHealthProto$SystemHealthMetric.hashedCustomEventName_ = longValue2;
                    return;
                }
                builder3.copyOnWrite();
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric3 = (SystemHealthProto$SystemHealthMetric) builder3.instance;
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric4 = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE;
                systemHealthProto$SystemHealthMetric3.bitField0_ &= -2;
                systemHealthProto$SystemHealthMetric3.hashedCustomEventName_ = 0L;
                return;
            }
            SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) builder;
            if (l != null) {
                long longValue3 = l.longValue();
                builder4.copyOnWrite();
                BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff = (BatteryMetric$BatteryStatsDiff) builder4.instance;
                BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff2 = BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE;
                batteryMetric$BatteryStatsDiff.bitField0_ |= 2;
                batteryMetric$BatteryStatsDiff.startHashedCustomEventName_ = longValue3;
                return;
            }
            builder4.copyOnWrite();
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff3 = (BatteryMetric$BatteryStatsDiff) builder4.instance;
            BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff4 = BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE;
            batteryMetric$BatteryStatsDiff3.bitField0_ &= -3;
            batteryMetric$BatteryStatsDiff3.startHashedCustomEventName_ = 0L;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface MetricNameAccess {
        String getConstantName(MessageLite.Builder builder);

        String getCustomName(MessageLite.Builder builder);

        void setCustomName$ar$ds(MessageLite.Builder builder);

        void setHashedName(MessageLite.Builder builder, Long l);
    }

    public static void ensureNoPiiName(MetricNameAccess metricNameAccess, MessageLite.Builder builder) {
        String constantName = metricNameAccess.getConstantName(builder);
        String customName = metricNameAccess.getCustomName(builder);
        if (constantName.isEmpty() && !customName.isEmpty()) {
            metricNameAccess.setHashedName(builder, Hashing.hash(customName));
        } else {
            metricNameAccess.setHashedName(builder, null);
        }
        metricNameAccess.setCustomName$ar$ds(builder);
    }

    public static List hashTokens(String str) {
        return ContextDataProvider.transform(SLASH_SPLITTER.splitToList(str), new AccessibilityEventUtils$$ExternalSyntheticLambda0(20));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x014a, code lost:
    
        if (r1 != false) goto L183;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00ce, code lost:
    
        if (r0.equals("Attempt to do a synchronize operation on a null object") == false) goto L184;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00f6, code lost:
    
        if (java.util.regex.Pattern.matches("Conflicting default method implementations .+", r0) == false) goto L184;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0127, code lost:
    
        if (java.util.regex.Pattern.matches("Method '.+' implementing interface method '.+' is not public", r0) == false) goto L184;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x008b. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final com.google.common.logging.proto2api.Logrecord$ThrowableBlockProto sanitizeThrowableBlock$ar$ds(com.google.common.logging.proto2api.Logrecord$ThrowableBlockProto r5) {
        /*
            Method dump skipped, instructions count: 460
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.sanitizeThrowableBlock$ar$ds(com.google.common.logging.proto2api.Logrecord$ThrowableBlockProto):com.google.common.logging.proto2api.Logrecord$ThrowableBlockProto");
    }
}
