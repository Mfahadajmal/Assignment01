package com.google.android.gms.common.internal.service;

import com.google.android.gms.auth.api.internal.AuthClientImpl;
import com.google.android.gms.auth.api.internal.IAuthCallbacks;
import com.google.android.gms.auth.api.internal.IAuthService;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.phenotype.internal.IGetStorageInfoCallbacks;
import com.google.android.gms.phenotype.internal.IPhenotypeCallbacks;
import com.google.android.gms.phenotype.internal.IPhenotypeService;
import com.google.android.gms.phenotype.internal.PhenotypeClientImpl;
import com.google.android.gms.pseudonymous.internal.IPseudonymousIdCallbacks;
import com.google.android.gms.pseudonymous.internal.IPseudonymousIdService;
import com.google.android.gms.pseudonymous.internal.PseudonymousIdClientImpl;
import com.google.android.gms.usagereporting.InternalUsageReportingClient;
import com.google.android.gms.usagereporting.UsageReporting;
import com.google.android.gms.usagereporting.internal.IUsageReportingCallbacks;
import com.google.android.gms.usagereporting.internal.IUsageReportingService;
import com.google.android.gms.usagereporting.internal.OptInOptionsResultImpl;
import com.google.android.gms.usagereporting.internal.UsageReportingClientImpl;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class InternalTelemetryLoggingClient$$ExternalSyntheticLambda0 implements RemoteCall {
    public final /* synthetic */ Object InternalTelemetryLoggingClient$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ InternalTelemetryLoggingClient$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.InternalTelemetryLoggingClient$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object obj, Object obj2) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                final AppLifecycleMonitor appLifecycleMonitor = (AppLifecycleMonitor) obj2;
                                ((IUsageReportingService) ((UsageReportingClientImpl) obj).getService()).getOptInOptions(new IUsageReportingCallbacks.Stub() { // from class: com.google.android.gms.usagereporting.InternalUsageReportingClient.1
                                    public AnonymousClass1() {
                                        super(null);
                                    }

                                    @Override // com.google.android.gms.usagereporting.internal.IUsageReportingCallbacks.Stub
                                    public final void onGetOptInOptions(Status status, UsageReportingOptInOptions usageReportingOptInOptions) {
                                        StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, new OnDeviceTextDetectionLoadLogEvent(new OptInOptionsResultImpl(Status.RESULT_SUCCESS, usageReportingOptInOptions)), AppLifecycleMonitor.this);
                                    }
                                });
                                return;
                            } else {
                                Object obj3 = this.InternalTelemetryLoggingClient$$ExternalSyntheticLambda0$ar$f$0;
                                final InternalUsageReportingClient internalUsageReportingClient = (InternalUsageReportingClient) obj3;
                                final AppLifecycleMonitor appLifecycleMonitor2 = (AppLifecycleMonitor) obj2;
                                ((UsageReportingClientImpl) obj).setOptInOptionsChangedListenerConnectionless$ar$class_merging(((UsageReporting.UsageReportingOptions) ((GoogleApi) obj3).apiOptions).listener$ar$class_merging$8aa44472_0, null, new UsageReporting.TaskResultHolder(appLifecycleMonitor2) { // from class: com.google.android.gms.usagereporting.InternalUsageReportingClient.8
                                    public AnonymousClass8(final AppLifecycleMonitor appLifecycleMonitor22) {
                                        super(appLifecycleMonitor22);
                                    }

                                    @Override // com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder
                                    public final /* bridge */ /* synthetic */ void setResult$ar$ds() {
                                        ((UsageReporting.UsageReportingOptions) InternalUsageReportingClient.this.apiOptions).listener$ar$class_merging$8aa44472_0 = null;
                                        this.completionSource$ar$class_merging$ar$class_merging.setResult(true);
                                    }
                                });
                                return;
                            }
                        }
                        final AppLifecycleMonitor appLifecycleMonitor3 = (AppLifecycleMonitor) obj2;
                        ((IPseudonymousIdService) ((PseudonymousIdClientImpl) obj).getService()).getToken(new IPseudonymousIdCallbacks.Stub() { // from class: com.google.android.gms.pseudonymous.PseudonymousIdClient$1
                            {
                                super(null);
                            }

                            @Override // com.google.android.gms.pseudonymous.internal.IPseudonymousIdCallbacks.Stub
                            public final void onTokenRetrieved(Status status, PseudonymousIdToken pseudonymousIdToken) {
                                StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, pseudonymousIdToken, AppLifecycleMonitor.this);
                            }
                        });
                        return;
                    }
                    ((IPhenotypeService) ((PhenotypeClientImpl) obj).getService()).commitToConfiguration(new IPhenotypeCallbacks.Stub((AppLifecycleMonitor) obj2), (String) this.InternalTelemetryLoggingClient$$ExternalSyntheticLambda0$ar$f$0);
                    return;
                }
                ((IPhenotypeService) ((PhenotypeClientImpl) obj).getService()).getStorageInfo(new IGetStorageInfoCallbacks.Stub((AppLifecycleMonitor) obj2));
                return;
            }
            final AppLifecycleMonitor appLifecycleMonitor4 = (AppLifecycleMonitor) obj2;
            ((IAuthService) ((AuthClientImpl) obj).getService()).getSpatulaHeader(new IAuthCallbacks.Stub() { // from class: com.google.android.gms.auth.api.proxy.internal.InternalProxyClient$3
                {
                    super(null);
                }

                @Override // com.google.android.gms.auth.api.internal.IAuthCallbacks.Stub
                public final void handleSpatulaHeader(String str) {
                    Status status;
                    if (str != null) {
                        status = Status.RESULT_SUCCESS;
                    } else {
                        status = new Status(3006);
                    }
                    StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, str, AppLifecycleMonitor.this);
                }
            });
            return;
        }
        ((IClientTelemetryService) ((TelemetryLoggingClientImpl) obj).getService()).recordData((TelemetryData) this.InternalTelemetryLoggingClient$$ExternalSyntheticLambda0$ar$f$0);
        ((AppLifecycleMonitor) obj2).setResult(null);
    }
}
