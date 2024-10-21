package com.google.android.libraries.storage.protostore;

import android.net.Uri;
import com.google.android.libraries.performance.primes.metrics.jank.DisplayStats;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.protobuf.MessageLite;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProtoDataStoreConfig {
    public final Optional handler;
    public final ImmutableList migrations;
    public final MessageLite schema;
    public final Uri uri;
    public final boolean useGeneratedExtensionRegistry;
    public final DisplayStats variantConfig$ar$class_merging$ba00fa32_0$ar$class_merging;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Optional handler;
        public ImmutableList migrations;
        public MessageLite schema;
        public byte set$0;
        public Uri uri;
        public boolean useGeneratedExtensionRegistry;
        public DisplayStats variantConfig$ar$class_merging$ba00fa32_0$ar$class_merging;

        public Builder() {
        }

        public final void setEnableTracing$ar$ds() {
            this.set$0 = (byte) (this.set$0 | 2);
        }

        public Builder(byte[] bArr) {
            this();
            this.handler = Absent.INSTANCE;
        }
    }

    public ProtoDataStoreConfig() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ProtoDataStoreConfig) {
            ProtoDataStoreConfig protoDataStoreConfig = (ProtoDataStoreConfig) obj;
            if (this.uri.equals(protoDataStoreConfig.uri()) && this.schema.equals(protoDataStoreConfig.schema()) && this.handler.equals(protoDataStoreConfig.handler()) && ContextDataProvider.equalsImpl(this.migrations, protoDataStoreConfig.migrations()) && this.variantConfig$ar$class_merging$ba00fa32_0$ar$class_merging.equals(protoDataStoreConfig.variantConfig$ar$class_merging$ar$class_merging()) && this.useGeneratedExtensionRegistry == protoDataStoreConfig.useGeneratedExtensionRegistry()) {
                return true;
            }
        }
        return false;
    }

    public final Optional handler() {
        return this.handler;
    }

    public final int hashCode() {
        int i;
        int hashCode = ((((((((this.uri.hashCode() ^ 1000003) * 1000003) ^ this.schema.hashCode()) * 1000003) ^ this.handler.hashCode()) * 1000003) ^ this.migrations.hashCode()) * 1000003) ^ this.variantConfig$ar$class_merging$ba00fa32_0$ar$class_merging.hashCode();
        if (true != this.useGeneratedExtensionRegistry) {
            i = 1237;
        } else {
            i = 1231;
        }
        return (((hashCode * 1000003) ^ i) * 1000003) ^ 1237;
    }

    public final ImmutableList migrations() {
        return this.migrations;
    }

    public final MessageLite schema() {
        return this.schema;
    }

    public final String toString() {
        DisplayStats displayStats = this.variantConfig$ar$class_merging$ba00fa32_0$ar$class_merging;
        ImmutableList immutableList = this.migrations;
        Optional optional = this.handler;
        MessageLite messageLite = this.schema;
        return "ProtoDataStoreConfig{uri=" + String.valueOf(this.uri) + ", schema=" + String.valueOf(messageLite) + ", handler=" + String.valueOf(optional) + ", migrations=" + String.valueOf(immutableList) + ", variantConfig=" + String.valueOf(displayStats) + ", useGeneratedExtensionRegistry=" + this.useGeneratedExtensionRegistry + ", enableTracing=false}";
    }

    public final Uri uri() {
        return this.uri;
    }

    public final boolean useGeneratedExtensionRegistry() {
        return this.useGeneratedExtensionRegistry;
    }

    public final DisplayStats variantConfig$ar$class_merging$ar$class_merging() {
        return this.variantConfig$ar$class_merging$ba00fa32_0$ar$class_merging;
    }

    public ProtoDataStoreConfig(Uri uri, MessageLite messageLite, Optional optional, ImmutableList immutableList, DisplayStats displayStats, boolean z) {
        this();
        this.uri = uri;
        this.schema = messageLite;
        this.handler = optional;
        this.migrations = immutableList;
        this.variantConfig$ar$class_merging$ba00fa32_0$ar$class_merging = displayStats;
        this.useGeneratedExtensionRegistry = z;
    }
}
