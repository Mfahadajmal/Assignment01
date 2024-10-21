package com.google.android.libraries.performance.primes;

import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesThreadsConfigurations {
    private final boolean enableDeferredTasks;
    public final int primesMetricExecutorPoolSize;
    private final int primesMetricExecutorPriority;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public boolean enableDeferredTasks;
        public int primesMetricExecutorPoolSize;
        public int primesMetricExecutorPriority;
        public byte set$0;

        public Builder() {
        }

        public final PrimesThreadsConfigurations autoBuild() {
            if (this.set$0 != 7) {
                StringBuilder sb = new StringBuilder();
                if ((this.set$0 & 1) == 0) {
                    sb.append(" primesMetricExecutorPriority");
                }
                if ((this.set$0 & 2) == 0) {
                    sb.append(" primesMetricExecutorPoolSize");
                }
                if ((this.set$0 & 4) == 0) {
                    sb.append(" enableDeferredTasks");
                }
                throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
            }
            return new PrimesThreadsConfigurations(this.primesMetricExecutorPriority, this.primesMetricExecutorPoolSize, this.enableDeferredTasks);
        }

        public final PrimesThreadsConfigurations build() {
            boolean z;
            PrimesThreadsConfigurations autoBuild = autoBuild();
            if (autoBuild.primesMetricExecutorPoolSize > 0) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkState(z, "Thread pool size must be less than or equal to %s", 2);
            return autoBuild;
        }

        public Builder(byte[] bArr) {
            this();
        }
    }

    public PrimesThreadsConfigurations() {
    }

    public static Builder newBuilder() {
        Builder builder = new Builder(null);
        builder.primesMetricExecutorPriority = 11;
        builder.primesMetricExecutorPoolSize = 2;
        builder.enableDeferredTasks = true;
        builder.set$0 = (byte) 7;
        return builder;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PrimesThreadsConfigurations) {
            PrimesThreadsConfigurations primesThreadsConfigurations = (PrimesThreadsConfigurations) obj;
            if (this.primesMetricExecutorPriority == primesThreadsConfigurations.getPrimesMetricExecutorPriority() && this.primesMetricExecutorPoolSize == primesThreadsConfigurations.getPrimesMetricExecutorPoolSize() && this.enableDeferredTasks == primesThreadsConfigurations.getEnableDeferredTasks()) {
                return true;
            }
        }
        return false;
    }

    public final boolean getEnableDeferredTasks() {
        return this.enableDeferredTasks;
    }

    public final int getPrimesMetricExecutorPoolSize() {
        return this.primesMetricExecutorPoolSize;
    }

    public final int getPrimesMetricExecutorPriority() {
        return this.primesMetricExecutorPriority;
    }

    public final int hashCode() {
        int i;
        if (true != this.enableDeferredTasks) {
            i = 1237;
        } else {
            i = 1231;
        }
        return i ^ ((((this.primesMetricExecutorPriority ^ (-721379959)) * 1000003) ^ this.primesMetricExecutorPoolSize) * 1000003);
    }

    public final String toString() {
        return "PrimesThreadsConfigurations{primesExecutorService=null, primesMetricExecutorPriority=" + this.primesMetricExecutorPriority + ", primesMetricExecutorPoolSize=" + this.primesMetricExecutorPoolSize + ", enableDeferredTasks=" + this.enableDeferredTasks + "}";
    }

    public PrimesThreadsConfigurations(int i, int i2, boolean z) {
        this();
        this.primesMetricExecutorPriority = i;
        this.primesMetricExecutorPoolSize = i2;
        this.enableDeferredTasks = z;
    }
}
