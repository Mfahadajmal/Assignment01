package com.google.android.gms.clearcut.internal;

import com.google.android.gms.auth.api.internal.AuthClientImpl;
import com.google.android.gms.auth.api.internal.IAuthCallbacks;
import com.google.android.gms.auth.api.internal.IAuthService;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import com.google.android.gms.auth.api.proxy.ProxyResponse;
import com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.common.moduleinstall.internal.ApiFeatureRequest;
import com.google.android.gms.common.moduleinstall.internal.IModuleInstallCallbacks;
import com.google.android.gms.common.moduleinstall.internal.IModuleInstallService;
import com.google.android.gms.common.moduleinstall.internal.ModuleInstallClientImpl;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.phenotype.internal.IPhenotypeCallbacks;
import com.google.android.gms.phenotype.internal.IPhenotypeService;
import com.google.android.gms.phenotype.internal.PhenotypeClientImpl;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ClearcutLoggerApiImpl$$ExternalSyntheticLambda2 implements RemoteCall {
    public final /* synthetic */ Object ClearcutLoggerApiImpl$$ExternalSyntheticLambda2$ar$f$0;
    public final /* synthetic */ Object ClearcutLoggerApiImpl$$ExternalSyntheticLambda2$ar$f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ ClearcutLoggerApiImpl$$ExternalSyntheticLambda2(GoogleApi googleApi, AbstractSafeParcelable abstractSafeParcelable, int i) {
        this.switching_field = i;
        this.ClearcutLoggerApiImpl$$ExternalSyntheticLambda2$ar$f$0 = googleApi;
        this.ClearcutLoggerApiImpl$$ExternalSyntheticLambda2$ar$f$1 = abstractSafeParcelable;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object obj, Object obj2) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        IPhenotypeCallbacks.Stub stub = new IPhenotypeCallbacks.Stub((AppLifecycleMonitor) obj2);
                        ((IPhenotypeService) ((PhenotypeClientImpl) obj).getService()).commitToConfiguration(stub, "CURRENT:" + ((String) this.ClearcutLoggerApiImpl$$ExternalSyntheticLambda2$ar$f$0) + ":" + ((String) this.ClearcutLoggerApiImpl$$ExternalSyntheticLambda2$ar$f$1));
                        return;
                    }
                    ((IPhenotypeService) ((PhenotypeClientImpl) obj).getService()).getConfigurationSnapshotWithToken$ar$ds(new IPhenotypeCallbacks.Stub((AppLifecycleMonitor) obj2), (String) this.ClearcutLoggerApiImpl$$ExternalSyntheticLambda2$ar$f$1, (String) this.ClearcutLoggerApiImpl$$ExternalSyntheticLambda2$ar$f$0);
                    return;
                }
                final AppLifecycleMonitor appLifecycleMonitor = (AppLifecycleMonitor) obj2;
                ((IModuleInstallService) ((ModuleInstallClientImpl) obj).getService()).installModules(new IModuleInstallCallbacks.Stub() { // from class: com.google.android.gms.common.moduleinstall.internal.InternalModuleInstallClient.4
                    public AnonymousClass4() {
                        super(null);
                    }

                    @Override // com.google.android.gms.common.moduleinstall.internal.IModuleInstallCallbacks.Stub
                    public final void onModuleInstallResponse(Status status, ModuleInstallResponse moduleInstallResponse) {
                        StrictModeUtils$VmPolicyBuilderCompatS.trySetResultOrApiException$ar$class_merging$ar$class_merging(status, moduleInstallResponse, AppLifecycleMonitor.this);
                    }
                }, (ApiFeatureRequest) this.ClearcutLoggerApiImpl$$ExternalSyntheticLambda2$ar$f$1, null);
                return;
            }
            final AppLifecycleMonitor appLifecycleMonitor2 = (AppLifecycleMonitor) obj2;
            ((IAuthService) ((AuthClientImpl) obj).getService()).performProxyRequest(new IAuthCallbacks.Stub() { // from class: com.google.android.gms.auth.api.proxy.internal.InternalProxyClient$1
                {
                    super(null);
                }

                @Override // com.google.android.gms.auth.api.internal.IAuthCallbacks.Stub
                public final void handleProxyResponse(ProxyResponse proxyResponse) {
                    StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(new Status(proxyResponse.googlePlayServicesStatusCode), proxyResponse, AppLifecycleMonitor.this);
                }
            }, (ProxyRequest) this.ClearcutLoggerApiImpl$$ExternalSyntheticLambda2$ar$f$1);
            return;
        }
        final AppLifecycleMonitor appLifecycleMonitor3 = (AppLifecycleMonitor) obj2;
        ((IClearcutLoggerService) ((ClearcutLoggerClientImpl) obj).getService()).logError(new IClearcutLoggerCallbacks.Stub() { // from class: com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl.3
            public AnonymousClass3() {
                super(null);
            }

            @Override // com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks.Stub
            public final void onLogError(Status status) {
                AppLifecycleMonitor.this.setResult(status);
            }
        }, (BatchedLogErrorParcelable) this.ClearcutLoggerApiImpl$$ExternalSyntheticLambda2$ar$f$1);
    }

    public /* synthetic */ ClearcutLoggerApiImpl$$ExternalSyntheticLambda2(String str, int i) {
        this.switching_field = i;
        this.ClearcutLoggerApiImpl$$ExternalSyntheticLambda2$ar$f$1 = str;
        this.ClearcutLoggerApiImpl$$ExternalSyntheticLambda2$ar$f$0 = "";
    }

    public /* synthetic */ ClearcutLoggerApiImpl$$ExternalSyntheticLambda2(String str, String str2, int i) {
        this.switching_field = i;
        this.ClearcutLoggerApiImpl$$ExternalSyntheticLambda2$ar$f$1 = str;
        this.ClearcutLoggerApiImpl$$ExternalSyntheticLambda2$ar$f$0 = str2;
    }
}
