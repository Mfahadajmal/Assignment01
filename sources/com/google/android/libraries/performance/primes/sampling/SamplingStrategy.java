package com.google.android.libraries.performance.primes.sampling;

import android.os.SystemClock;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.Random;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class SamplingStrategy {
    private final SystemHealthProto$SamplingParameters samplingParameters;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class BasicSamplingStrategy extends SamplingStrategy {
        private final boolean shouldSample;

        public BasicSamplingStrategy(SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters, boolean z) {
            super(systemHealthProto$SamplingParameters);
            this.shouldSample = z;
        }

        private final SystemHealthProto$SamplingParameters samplingParametersIfEnabled(Long l) {
            if (this.shouldSample) {
                return getSamplingParameters(l);
            }
            return getSamplingOffParameters();
        }

        @Override // com.google.android.libraries.performance.primes.sampling.SamplingStrategy
        public final boolean getMetricServiceEnabled() {
            return this.shouldSample;
        }

        @Override // com.google.android.libraries.performance.primes.sampling.SamplingStrategy
        public final SystemHealthProto$SamplingParameters getSamplingParametersIfShouldRecord(Long l) {
            return samplingParametersIfEnabled(l);
        }

        @Override // com.google.android.libraries.performance.primes.sampling.SamplingStrategy
        public final long getSamplingRatePermilleIfShouldSample(String str) {
            SystemHealthProto$SamplingParameters samplingParametersIfEnabled = samplingParametersIfEnabled(null);
            if (samplingParametersIfEnabled.equals(SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE)) {
                return 1000L;
            }
            return samplingParametersIfEnabled.sampleRatePermille_;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class DynamicEventProbabilitySamplingStrategy extends SamplingStrategy {
        private final long baseProbability;
        private final SpannableUtils$NonCopyableTextSpan clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging;
        private final ApproximateHistogram histogram;
        private final Random random;

        public DynamicEventProbabilitySamplingStrategy(SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters, Random random, ApproximateHistogram approximateHistogram, SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan) {
            super(systemHealthProto$SamplingParameters);
            this.random = random;
            this.baseProbability = systemHealthProto$SamplingParameters.sampleRatePermille_;
            this.histogram = approximateHistogram;
            this.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging = spannableUtils$NonCopyableTextSpan;
        }

        @Override // com.google.android.libraries.performance.primes.sampling.SamplingStrategy
        public final boolean getMetricServiceEnabled() {
            if (this.baseProbability > 0) {
                return true;
            }
            return false;
        }

        @Override // com.google.android.libraries.performance.primes.sampling.SamplingStrategy
        public final SystemHealthProto$SamplingParameters getSamplingParametersIfShouldRecord(Long l) {
            if (getMetricServiceEnabled()) {
                return getSamplingParameters(l);
            }
            return getSamplingOffParameters();
        }

        @Override // com.google.android.libraries.performance.primes.sampling.SamplingStrategy
        public final long getSamplingRatePermilleIfShouldSample(String str) {
            long elapsedRealtime;
            long sqrt;
            if (ContextDataProvider.stringIsNullOrEmpty(str)) {
                sqrt = this.baseProbability;
            } else {
                ApproximateHistogram approximateHistogram = this.histogram;
                elapsedRealtime = SystemClock.elapsedRealtime();
                long j = elapsedRealtime - approximateHistogram.lastScaleDownTimeMillis;
                char c = 0;
                if (j >= 14400000) {
                    long j2 = j / 14400000;
                    long max = Math.max(j2, 15L);
                    for (int i = 0; i < 256; i++) {
                        short[] sArr = approximateHistogram.histogramForHighBits;
                        int i2 = (int) max;
                        int i3 = sArr[i] >> i2;
                        sArr[i] = (short) i3;
                        approximateHistogram.histogramForNextBits[i] = (short) (i3 >> i2);
                    }
                    approximateHistogram.lastScaleDownTimeMillis += j2 * 14400000;
                }
                int hashCode = str.hashCode() * approximateHistogram.multiplier;
                if (!str.isEmpty()) {
                    c = str.charAt(0);
                }
                int i4 = hashCode >>> 24;
                int length = (hashCode >>> 16) + str.length();
                short[] sArr2 = approximateHistogram.histogramForHighBits;
                int i5 = (i4 + c) & PrivateKeyType.INVALID;
                short s = sArr2[i5];
                short[] sArr3 = approximateHistogram.histogramForNextBits;
                int i6 = length & PrivateKeyType.INVALID;
                int min = Math.min((int) s, (int) sArr3[i6]);
                int i7 = min + 1;
                short min2 = (short) Math.min(32767, i7);
                short[] sArr4 = approximateHistogram.histogramForHighBits;
                if (sArr4[i5] == min) {
                    sArr4[i5] = min2;
                }
                short[] sArr5 = approximateHistogram.histogramForNextBits;
                if (sArr5[i6] == min) {
                    sArr5[i6] = min2;
                }
                sqrt = (int) (this.baseProbability / (i7 < 50 ? Math.sqrt(i7) : i7));
            }
            if (this.random.nextDouble() * 1000.0d < sqrt) {
                return sqrt;
            }
            return -1L;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Factory {
        static final SamplingStrategy DEFAULT_UNKNOWN_SAMPLING_STRATEGY = new BasicSamplingStrategy(SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE, true);
        private final ApproximateHistogram approximateHistogram;
        private final SpannableUtils$NonCopyableTextSpan clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging;
        private final Random random;

        public Factory(Random random, ApproximateHistogram approximateHistogram, SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan) {
            this.random = random;
            this.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging = spannableUtils$NonCopyableTextSpan;
            this.approximateHistogram = approximateHistogram;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final SamplingStrategy create(SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters) {
            int forNumber$ar$edu$fde10cc9_0 = SystemHealthProto$SamplingParameters.SamplingStrategy.forNumber$ar$edu$fde10cc9_0(systemHealthProto$SamplingParameters.samplingStrategy_);
            if (forNumber$ar$edu$fde10cc9_0 == 0) {
                forNumber$ar$edu$fde10cc9_0 = SystemHealthProto$SamplingParameters.SamplingStrategy.UNKNOWN$ar$edu$b47db991_0;
            }
            if (forNumber$ar$edu$fde10cc9_0 != 0) {
                int i = forNumber$ar$edu$fde10cc9_0 - 1;
                boolean z = false;
                if (i != 1) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i == 5) {
                                systemHealthProto$SamplingParameters = SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE;
                            }
                            return new BasicSamplingStrategy(systemHealthProto$SamplingParameters, true);
                        }
                        return new DynamicEventProbabilitySamplingStrategy(systemHealthProto$SamplingParameters, this.random, this.approximateHistogram, this.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging);
                    }
                    if (this.random.nextDouble() * 1000.0d < systemHealthProto$SamplingParameters.sampleRatePermille_) {
                        z = true;
                    }
                    return new BasicSamplingStrategy(systemHealthProto$SamplingParameters, z);
                }
                if (systemHealthProto$SamplingParameters.sampleRatePermille_ == 1000) {
                    z = true;
                }
                return new BasicSamplingStrategy(systemHealthProto$SamplingParameters, z);
            }
            throw null;
        }
    }

    public SamplingStrategy(SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters) {
        this.samplingParameters = systemHealthProto$SamplingParameters;
    }

    public abstract boolean getMetricServiceEnabled();

    public final SystemHealthProto$SamplingParameters getSamplingOffParameters() {
        SystemHealthProto$PackedHistogram.Builder builder = getSamplingParameters(null).toBuilder();
        builder.copyOnWrite();
        SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters = (SystemHealthProto$SamplingParameters) builder.instance;
        systemHealthProto$SamplingParameters.bitField0_ |= 2;
        systemHealthProto$SamplingParameters.sampleRatePermille_ = -1L;
        return (SystemHealthProto$SamplingParameters) builder.build();
    }

    final SystemHealthProto$SamplingParameters getSamplingParameters(Long l) {
        int forNumber$ar$edu$fde10cc9_0 = SystemHealthProto$SamplingParameters.SamplingStrategy.forNumber$ar$edu$fde10cc9_0(this.samplingParameters.samplingStrategy_);
        if (forNumber$ar$edu$fde10cc9_0 == 0) {
            forNumber$ar$edu$fde10cc9_0 = SystemHealthProto$SamplingParameters.SamplingStrategy.UNKNOWN$ar$edu$b47db991_0;
        }
        if (forNumber$ar$edu$fde10cc9_0 == SystemHealthProto$SamplingParameters.SamplingStrategy.SAMPLING_STRATEGY_DYNAMIC_EVENT_PROBABILITY$ar$edu && l != null) {
            if (l.longValue() != this.samplingParameters.sampleRatePermille_) {
                SystemHealthProto$PackedHistogram.Builder createBuilder = SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE.createBuilder();
                int forNumber$ar$edu$fde10cc9_02 = SystemHealthProto$SamplingParameters.SamplingStrategy.forNumber$ar$edu$fde10cc9_0(this.samplingParameters.samplingStrategy_);
                if (forNumber$ar$edu$fde10cc9_02 == 0) {
                    forNumber$ar$edu$fde10cc9_02 = SystemHealthProto$SamplingParameters.SamplingStrategy.UNKNOWN$ar$edu$b47db991_0;
                }
                createBuilder.copyOnWrite();
                SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters = (SystemHealthProto$SamplingParameters) createBuilder.instance;
                if (forNumber$ar$edu$fde10cc9_02 != 0) {
                    systemHealthProto$SamplingParameters.samplingStrategy_ = forNumber$ar$edu$fde10cc9_02 - 1;
                    systemHealthProto$SamplingParameters.bitField0_ |= 4;
                    long longValue = l.longValue();
                    createBuilder.copyOnWrite();
                    SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters2 = (SystemHealthProto$SamplingParameters) createBuilder.instance;
                    systemHealthProto$SamplingParameters2.bitField0_ |= 2;
                    systemHealthProto$SamplingParameters2.sampleRatePermille_ = longValue;
                    return (SystemHealthProto$SamplingParameters) createBuilder.build();
                }
                throw null;
            }
        }
        return this.samplingParameters;
    }

    public abstract SystemHealthProto$SamplingParameters getSamplingParametersIfShouldRecord(Long l);

    public abstract long getSamplingRatePermilleIfShouldSample(String str);
}
