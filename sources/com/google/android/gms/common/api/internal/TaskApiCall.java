package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TaskApiCall {
    public final boolean autoResolveMissingFeatures;
    public final Feature[] features;
    public final int methodKey;
    final /* synthetic */ Builder this$0;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public static Builder singleton$ar$class_merging;
        public Object TaskApiCall$Builder$ar$execute;
        public Object TaskApiCall$Builder$ar$features;
        public boolean autoResolveMissingFeatures;
        public int methodKey;

        public Builder() {
            this.autoResolveMissingFeatures = true;
            this.methodKey = 0;
        }

        public static Builder getInstance$ar$class_merging() {
            Builder builder = singleton$ar$class_merging;
            if (builder != null) {
                return builder;
            }
            throw new IllegalStateException("You forget to call init()!");
        }

        public final TaskApiCall build() {
            boolean z;
            if (this.TaskApiCall$Builder$ar$execute != null) {
                z = true;
            } else {
                z = false;
            }
            StrictModeUtils$VmPolicyBuilderCompatS.checkArgument(z, "execute parameter required");
            return new TaskApiCall(this, (Feature[]) this.TaskApiCall$Builder$ar$features, this.autoResolveMissingFeatures, this.methodKey);
        }

        public Builder(byte[] bArr) {
            this.methodKey = 1;
        }
    }

    protected TaskApiCall(Feature[] featureArr, boolean z, int i) {
        this.features = featureArr;
        boolean z2 = false;
        if (featureArr != null && z) {
            z2 = true;
        }
        this.autoResolveMissingFeatures = z2;
        this.methodKey = i;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TaskApiCall(Builder builder, Feature[] featureArr, boolean z, int i) {
        this(featureArr, z, i);
        this.this$0 = builder;
    }
}
