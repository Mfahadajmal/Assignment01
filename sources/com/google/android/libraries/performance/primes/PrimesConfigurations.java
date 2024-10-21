package com.google.android.libraries.performance.primes;

import com.google.common.base.Absent;
import com.google.common.base.Optional;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesConfigurations {
    private final Optional applicationExitConfigurationsProvider;
    private final Optional batteryConfigurationsProvider;
    private final Optional cpuProfilingConfigurationsProvider;
    private final Optional crashConfigurationsProvider;
    private final Optional globalConfigurationsProvider;
    private final Optional jankConfigurationsProvider;
    private final Optional memoryConfigurationsProvider;
    private final Provider metricTransmittersProvider;
    private final Optional monitorAllActivitiesProvider;
    private final Optional networkConfigurationsProvider;
    private final Optional storageConfigurationsProvider;
    private final Optional tikTokTraceConfigurationsProvider;
    private final Optional timerConfigurationsProvider;
    private final Optional traceConfigurationsProvider;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private Optional applicationExitConfigurationsProvider;
        private Optional batteryConfigurationsProvider;
        private Optional cpuProfilingConfigurationsProvider;
        private Optional crashConfigurationsProvider;
        private Optional globalConfigurationsProvider;
        private Optional jankConfigurationsProvider;
        private Optional memoryConfigurationsProvider;
        private Provider metricTransmittersProvider;
        private Optional monitorAllActivitiesProvider;
        private Optional networkConfigurationsProvider;
        private Optional storageConfigurationsProvider;
        private Optional tikTokTraceConfigurationsProvider;
        private Optional timerConfigurationsProvider;
        private Optional traceConfigurationsProvider;

        public Builder() {
        }

        public final PrimesConfigurations build() {
            Provider provider = this.metricTransmittersProvider;
            if (provider != null) {
                return new PrimesConfigurations(provider, this.globalConfigurationsProvider, this.memoryConfigurationsProvider, this.timerConfigurationsProvider, this.crashConfigurationsProvider, this.applicationExitConfigurationsProvider, this.networkConfigurationsProvider, this.storageConfigurationsProvider, this.jankConfigurationsProvider, this.monitorAllActivitiesProvider, this.tikTokTraceConfigurationsProvider, this.traceConfigurationsProvider, this.batteryConfigurationsProvider, this.cpuProfilingConfigurationsProvider, null);
            }
            throw new IllegalStateException("Missing required properties: metricTransmittersProvider");
        }

        public final void setBatteryConfigurationsProvider$ar$ds(Provider provider) {
            this.batteryConfigurationsProvider = Optional.of(provider);
        }

        public final void setCrashConfigurationsProvider$ar$ds(Provider provider) {
            this.crashConfigurationsProvider = Optional.of(provider);
        }

        public final void setGlobalConfigurationsProvider$ar$ds(Provider provider) {
            this.globalConfigurationsProvider = Optional.of(provider);
        }

        public final void setMemoryConfigurationsProvider$ar$ds(Provider provider) {
            this.memoryConfigurationsProvider = Optional.of(provider);
        }

        public final Builder setMetricTransmitterProvider(Provider provider) {
            return setMetricTransmittersProvider(new PrimesConfigurations$Builder$$ExternalSyntheticLambda0(provider, 0));
        }

        public final Builder setMetricTransmittersProvider(Provider provider) {
            this.metricTransmittersProvider = provider;
            return this;
        }

        public final void setNetworkConfigurationsProvider$ar$ds(Provider provider) {
            this.networkConfigurationsProvider = Optional.of(provider);
        }

        public final void setStorageConfigurationsProvider$ar$ds(Provider provider) {
            this.storageConfigurationsProvider = Optional.of(provider);
        }

        public final void setTimerConfigurationsProvider$ar$ds(Provider provider) {
            this.timerConfigurationsProvider = Optional.of(provider);
        }

        public Builder(byte[] bArr) {
            this();
            Absent absent = Absent.INSTANCE;
            this.globalConfigurationsProvider = absent;
            this.memoryConfigurationsProvider = absent;
            this.timerConfigurationsProvider = absent;
            this.crashConfigurationsProvider = absent;
            this.applicationExitConfigurationsProvider = absent;
            this.networkConfigurationsProvider = absent;
            this.storageConfigurationsProvider = absent;
            this.jankConfigurationsProvider = absent;
            this.monitorAllActivitiesProvider = absent;
            this.tikTokTraceConfigurationsProvider = absent;
            this.traceConfigurationsProvider = absent;
            this.batteryConfigurationsProvider = absent;
            this.cpuProfilingConfigurationsProvider = absent;
        }
    }

    public PrimesConfigurations() {
    }

    public final Optional applicationExitConfigurationsProvider() {
        return this.applicationExitConfigurationsProvider;
    }

    public final Optional batteryConfigurationsProvider() {
        return this.batteryConfigurationsProvider;
    }

    public final Optional cpuProfilingConfigurationsProvider() {
        return this.cpuProfilingConfigurationsProvider;
    }

    public final Optional crashConfigurationsProvider() {
        return this.crashConfigurationsProvider;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PrimesConfigurations) {
            PrimesConfigurations primesConfigurations = (PrimesConfigurations) obj;
            if (this.metricTransmittersProvider.equals(primesConfigurations.metricTransmittersProvider()) && this.globalConfigurationsProvider.equals(primesConfigurations.globalConfigurationsProvider()) && this.memoryConfigurationsProvider.equals(primesConfigurations.memoryConfigurationsProvider()) && this.timerConfigurationsProvider.equals(primesConfigurations.timerConfigurationsProvider()) && this.crashConfigurationsProvider.equals(primesConfigurations.crashConfigurationsProvider()) && this.applicationExitConfigurationsProvider.equals(primesConfigurations.applicationExitConfigurationsProvider()) && this.networkConfigurationsProvider.equals(primesConfigurations.networkConfigurationsProvider()) && this.storageConfigurationsProvider.equals(primesConfigurations.storageConfigurationsProvider()) && this.jankConfigurationsProvider.equals(primesConfigurations.jankConfigurationsProvider()) && this.monitorAllActivitiesProvider.equals(primesConfigurations.monitorAllActivitiesProvider()) && this.tikTokTraceConfigurationsProvider.equals(primesConfigurations.tikTokTraceConfigurationsProvider()) && this.traceConfigurationsProvider.equals(primesConfigurations.traceConfigurationsProvider()) && this.batteryConfigurationsProvider.equals(primesConfigurations.batteryConfigurationsProvider()) && this.cpuProfilingConfigurationsProvider.equals(primesConfigurations.cpuProfilingConfigurationsProvider())) {
                return true;
            }
        }
        return false;
    }

    public final Optional globalConfigurationsProvider() {
        return this.globalConfigurationsProvider;
    }

    public final int hashCode() {
        return ((((((((((((((((((((((((((this.metricTransmittersProvider.hashCode() ^ 1000003) * 1000003) ^ this.globalConfigurationsProvider.hashCode()) * 1000003) ^ this.memoryConfigurationsProvider.hashCode()) * 1000003) ^ this.timerConfigurationsProvider.hashCode()) * 1000003) ^ this.crashConfigurationsProvider.hashCode()) * 1000003) ^ 2040732332) * 1000003) ^ this.networkConfigurationsProvider.hashCode()) * 1000003) ^ this.storageConfigurationsProvider.hashCode()) * 1000003) ^ 2040732332) * 1000003) ^ 2040732332) * 1000003) ^ 2040732332) * 1000003) ^ 2040732332) * 1000003) ^ this.batteryConfigurationsProvider.hashCode()) * 1000003) ^ 2040732332;
    }

    public final Optional jankConfigurationsProvider() {
        return this.jankConfigurationsProvider;
    }

    public final Optional memoryConfigurationsProvider() {
        return this.memoryConfigurationsProvider;
    }

    public final Provider metricTransmittersProvider() {
        return this.metricTransmittersProvider;
    }

    public final Optional monitorAllActivitiesProvider() {
        return this.monitorAllActivitiesProvider;
    }

    public final Optional networkConfigurationsProvider() {
        return this.networkConfigurationsProvider;
    }

    public final Optional storageConfigurationsProvider() {
        return this.storageConfigurationsProvider;
    }

    public final Optional tikTokTraceConfigurationsProvider() {
        return this.tikTokTraceConfigurationsProvider;
    }

    public final Optional timerConfigurationsProvider() {
        return this.timerConfigurationsProvider;
    }

    public final String toString() {
        return "PrimesConfigurations{metricTransmittersProvider=" + this.metricTransmittersProvider.toString() + ", globalConfigurationsProvider=" + this.globalConfigurationsProvider.toString() + ", memoryConfigurationsProvider=" + this.memoryConfigurationsProvider.toString() + ", timerConfigurationsProvider=" + this.timerConfigurationsProvider.toString() + ", crashConfigurationsProvider=" + this.crashConfigurationsProvider.toString() + ", applicationExitConfigurationsProvider=Optional.absent(), networkConfigurationsProvider=" + this.networkConfigurationsProvider.toString() + ", storageConfigurationsProvider=" + this.storageConfigurationsProvider.toString() + ", jankConfigurationsProvider=Optional.absent(), monitorAllActivitiesProvider=Optional.absent(), tikTokTraceConfigurationsProvider=Optional.absent(), traceConfigurationsProvider=Optional.absent(), batteryConfigurationsProvider=" + this.batteryConfigurationsProvider.toString() + ", cpuProfilingConfigurationsProvider=Optional.absent()}";
    }

    public final Optional traceConfigurationsProvider() {
        return this.traceConfigurationsProvider;
    }

    public PrimesConfigurations(Provider provider, Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, Optional optional6, Optional optional7, Optional optional8, Optional optional9, Optional optional10, Optional optional11, Optional optional12, Optional optional13) {
        this();
        this.metricTransmittersProvider = provider;
        if (optional == null) {
            throw new NullPointerException("Null globalConfigurationsProvider");
        }
        this.globalConfigurationsProvider = optional;
        if (optional2 != null) {
            this.memoryConfigurationsProvider = optional2;
            if (optional3 != null) {
                this.timerConfigurationsProvider = optional3;
                if (optional4 != null) {
                    this.crashConfigurationsProvider = optional4;
                    if (optional5 != null) {
                        this.applicationExitConfigurationsProvider = optional5;
                        if (optional6 != null) {
                            this.networkConfigurationsProvider = optional6;
                            if (optional7 != null) {
                                this.storageConfigurationsProvider = optional7;
                                if (optional8 != null) {
                                    this.jankConfigurationsProvider = optional8;
                                    if (optional9 != null) {
                                        this.monitorAllActivitiesProvider = optional9;
                                        if (optional10 != null) {
                                            this.tikTokTraceConfigurationsProvider = optional10;
                                            if (optional11 != null) {
                                                this.traceConfigurationsProvider = optional11;
                                                if (optional12 != null) {
                                                    this.batteryConfigurationsProvider = optional12;
                                                    if (optional13 != null) {
                                                        this.cpuProfilingConfigurationsProvider = optional13;
                                                        return;
                                                    }
                                                    throw new NullPointerException("Null cpuProfilingConfigurationsProvider");
                                                }
                                                throw new NullPointerException("Null batteryConfigurationsProvider");
                                            }
                                            throw new NullPointerException("Null traceConfigurationsProvider");
                                        }
                                        throw new NullPointerException("Null tikTokTraceConfigurationsProvider");
                                    }
                                    throw new NullPointerException("Null monitorAllActivitiesProvider");
                                }
                                throw new NullPointerException("Null jankConfigurationsProvider");
                            }
                            throw new NullPointerException("Null storageConfigurationsProvider");
                        }
                        throw new NullPointerException("Null networkConfigurationsProvider");
                    }
                    throw new NullPointerException("Null applicationExitConfigurationsProvider");
                }
                throw new NullPointerException("Null crashConfigurationsProvider");
            }
            throw new NullPointerException("Null timerConfigurationsProvider");
        }
        throw new NullPointerException("Null memoryConfigurationsProvider");
    }

    public PrimesConfigurations(Provider provider, Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, Optional optional6, Optional optional7, Optional optional8, Optional optional9, Optional optional10, Optional optional11, Optional optional12, Optional optional13, byte[] bArr) {
        this(provider, optional, optional2, optional3, optional4, optional5, optional6, optional7, optional8, optional9, optional10, optional11, optional12, optional13);
    }
}
