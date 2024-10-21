package com.google.android.libraries.performance.primes.metrics.core;

import com.google.common.base.Supplier;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GlobalConfigurations {
    private final Supplier componentNameSupplier;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Supplier componentNameSupplier;
        public byte set$0;

        public Builder() {
        }

        public final GlobalConfigurations build() {
            if (this.set$0 != 3) {
                StringBuilder sb = new StringBuilder();
                if ((this.set$0 & 1) == 0) {
                    sb.append(" pauseTimersWhenSleeping");
                }
                if ((this.set$0 & 2) == 0) {
                    sb.append(" pauseStartupMeasuresWhenSleeping");
                }
                throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
            }
            return new GlobalConfigurations(this.componentNameSupplier);
        }

        public Builder(byte[] bArr) {
            this();
        }
    }

    public GlobalConfigurations() {
    }

    public static Builder newBuilder() {
        Builder builder = new Builder(null);
        builder.set$0 = (byte) 3;
        return builder;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GlobalConfigurations) {
            GlobalConfigurations globalConfigurations = (GlobalConfigurations) obj;
            Supplier supplier = this.componentNameSupplier;
            if (supplier != null ? supplier.equals(globalConfigurations.getComponentNameSupplier()) : globalConfigurations.getComponentNameSupplier() == null) {
                return true;
            }
        }
        return false;
    }

    public final Supplier getComponentNameSupplier() {
        return this.componentNameSupplier;
    }

    public final int hashCode() {
        int hashCode;
        Supplier supplier = this.componentNameSupplier;
        if (supplier == null) {
            hashCode = 0;
        } else {
            hashCode = supplier.hashCode();
        }
        return ((((hashCode ^ 1000003) * (-721379959)) ^ 1237) * 1000003) ^ 1237;
    }

    public final String toString() {
        return "GlobalConfigurations{componentNameSupplier=" + String.valueOf(this.componentNameSupplier) + ", extensionProvider=null, pauseTimersWhenSleeping=false, pauseStartupMeasuresWhenSleeping=false}";
    }

    public GlobalConfigurations(Supplier supplier) {
        this();
        this.componentNameSupplier = supplier;
    }
}
