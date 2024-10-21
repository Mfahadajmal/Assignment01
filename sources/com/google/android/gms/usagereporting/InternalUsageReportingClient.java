package com.google.android.gms.usagereporting;

import android.content.Context;
import com.google.android.gms.auth.api.AuthProxy;
import com.google.android.gms.auth.api.AuthProxyOptions;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegistrationMethods$Builder;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.service.InternalTelemetryLoggingClient$$ExternalSyntheticLambda0;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.pseudonymous.PseudonymousId;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.usagereporting.UsageReporting;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InternalUsageReportingClient extends GoogleApi {
    public InternalUsageReportingClient(Context context, AuthProxyOptions authProxyOptions) {
        super(context, AuthProxy.API$ar$class_merging$ar$class_merging$ar$class_merging, authProxyOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Task getOptInOptions() {
        TaskApiCall.Builder builder = new TaskApiCall.Builder();
        builder.TaskApiCall$Builder$ar$execute = new InternalTelemetryLoggingClient$$ExternalSyntheticLambda0(this, 6);
        builder.methodKey = 4501;
        return doRead(builder.build());
    }

    public final Task getSpatulaHeader() {
        TaskApiCall.Builder builder = new TaskApiCall.Builder();
        builder.TaskApiCall$Builder$ar$execute = new InternalTelemetryLoggingClient$$ExternalSyntheticLambda0(this, 1);
        builder.methodKey = 1520;
        return doRead(builder.build());
    }

    public final void setOptInOptionsChangedListener$ar$ds(UsageReportingApi$OptInOptionsChangedListener usageReportingApi$OptInOptionsChangedListener) {
        ListenerHolder createListenerHolder = StrictModeUtils$VmPolicyBuilderCompatS.createListenerHolder(usageReportingApi$OptInOptionsChangedListener, this.looper, UsageReportingApi$OptInOptionsChangedListener.class.getSimpleName());
        InternalUsageReportingClient$$ExternalSyntheticLambda3 internalUsageReportingClient$$ExternalSyntheticLambda3 = new InternalUsageReportingClient$$ExternalSyntheticLambda3(this, createListenerHolder, ((UsageReporting.UsageReportingOptions) this.apiOptions).listener$ar$class_merging$8aa44472_0, 0);
        InternalTelemetryLoggingClient$$ExternalSyntheticLambda0 internalTelemetryLoggingClient$$ExternalSyntheticLambda0 = new InternalTelemetryLoggingClient$$ExternalSyntheticLambda0(this, 5);
        RegistrationMethods$Builder registrationMethods$Builder = new RegistrationMethods$Builder();
        registrationMethods$Builder.register = internalUsageReportingClient$$ExternalSyntheticLambda3;
        registrationMethods$Builder.unregister = internalTelemetryLoggingClient$$ExternalSyntheticLambda0;
        registrationMethods$Builder.holder = createListenerHolder;
        registrationMethods$Builder.features = new Feature[]{Features.USAGE_AND_DIAGNOSTICS_LISTENER};
        registrationMethods$Builder.methodKey = 4507;
        doRegisterEventListener$ar$class_merging$ar$class_merging$ar$class_merging(registrationMethods$Builder.build$ar$class_merging$8fd660b1_0$ar$class_merging$ar$class_merging());
    }

    public InternalUsageReportingClient(Context context) {
        super(context, PseudonymousId.API$ar$class_merging$ar$class_merging$ar$class_merging, Api$ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public InternalUsageReportingClient(Context context, UsageReporting.UsageReportingOptions usageReportingOptions) {
        super(context, UsageReporting.API$ar$class_merging$ar$class_merging$ar$class_merging, usageReportingOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
