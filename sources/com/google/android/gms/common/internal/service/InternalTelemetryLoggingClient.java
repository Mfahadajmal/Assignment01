package com.google.android.gms.common.internal.service;

import android.content.Context;
import android.os.Looper;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.gms.clienttelemetry.Features;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import com.google.android.gms.tasks.Task;
import io.grpc.okhttp.internal.OptionalMethod;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InternalTelemetryLoggingClient extends GoogleApi implements TelemetryLoggingClient {
    private static final OptionalMethod API$ar$class_merging$ar$class_merging$ar$class_merging;
    private static final SpannableUtils$IdentifierSpan CLIENT_KEY$ar$class_merging$ar$class_merging$ar$class_merging;
    private static final SpannableUtils$IdentifierSpan clientBuilder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    static {
        SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan = new SpannableUtils$IdentifierSpan((byte[]) null, (char[]) null);
        CLIENT_KEY$ar$class_merging$ar$class_merging$ar$class_merging = spannableUtils$IdentifierSpan;
        SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan2 = new SpannableUtils$IdentifierSpan() { // from class: com.google.android.gms.common.internal.service.InternalTelemetryLoggingClient.1
            @Override // com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan
            public final /* synthetic */ Api$Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
                return new TelemetryLoggingClientImpl(context, looper, clientSettings, (TelemetryLoggingOptions) obj, connectionCallbacks, onConnectionFailedListener);
            }
        };
        clientBuilder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = spannableUtils$IdentifierSpan2;
        API$ar$class_merging$ar$class_merging$ar$class_merging = new OptionalMethod("ClientTelemetry.API", spannableUtils$IdentifierSpan2, spannableUtils$IdentifierSpan, (char[]) null);
    }

    public InternalTelemetryLoggingClient(Context context, TelemetryLoggingOptions telemetryLoggingOptions) {
        super(context, API$ar$class_merging$ar$class_merging$ar$class_merging, telemetryLoggingOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Override // com.google.android.gms.common.internal.TelemetryLoggingClient
    public final Task log(TelemetryData telemetryData) {
        TaskApiCall.Builder builder = new TaskApiCall.Builder();
        builder.TaskApiCall$Builder$ar$features = new Feature[]{Features.CLIENT_TELEMETRY};
        builder.autoResolveMissingFeatures = false;
        builder.TaskApiCall$Builder$ar$execute = new InternalTelemetryLoggingClient$$ExternalSyntheticLambda0(telemetryData, 0);
        return doBestEffortWrite(builder.build());
    }
}
