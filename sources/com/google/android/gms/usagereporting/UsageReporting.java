package com.google.android.gms.usagereporting;

import android.content.Context;
import android.os.Looper;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.usagereporting.internal.IUsageReportingOptInOptionsChangedListener;
import com.google.android.gms.usagereporting.internal.UsageReportingClientImpl;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UsageReporting {

    @Deprecated
    public static final OptionalMethod API$ar$class_merging$ar$class_merging$ar$class_merging;
    private static final SpannableUtils$IdentifierSpan CLIENT_BUILDER$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private static final SpannableUtils$IdentifierSpan CLIENT_KEY$ar$class_merging$ar$class_merging$ar$class_merging;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class TaskResultHolder implements BaseImplementation$ResultHolder {
        public final AppLifecycleMonitor completionSource$ar$class_merging$ar$class_merging;

        public TaskResultHolder(AppLifecycleMonitor appLifecycleMonitor) {
            this.completionSource$ar$class_merging$ar$class_merging = appLifecycleMonitor;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class UsageReportingOptions implements Api$ApiOptions {
        public IUsageReportingOptInOptionsChangedListener.Stub listener$ar$class_merging$8aa44472_0 = null;

        public final boolean equals(Object obj) {
            return obj instanceof UsageReportingOptions;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{getClass()});
        }
    }

    static {
        SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan = new SpannableUtils$IdentifierSpan((byte[]) null, (char[]) null);
        CLIENT_KEY$ar$class_merging$ar$class_merging$ar$class_merging = spannableUtils$IdentifierSpan;
        SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan2 = new SpannableUtils$IdentifierSpan() { // from class: com.google.android.gms.usagereporting.UsageReporting.1
            @Override // com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan
            public final /* synthetic */ Api$Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new UsageReportingClientImpl(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
            }
        };
        CLIENT_BUILDER$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = spannableUtils$IdentifierSpan2;
        API$ar$class_merging$ar$class_merging$ar$class_merging = new OptionalMethod("UsageReporting.API", spannableUtils$IdentifierSpan2, spannableUtils$IdentifierSpan, (char[]) null);
    }
}
