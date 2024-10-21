package com.google.android.gms.usagereporting;

import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.phenotype.PhenotypeClient;
import com.google.android.gms.phenotype.internal.IFlagUpdateListener;
import com.google.android.gms.phenotype.internal.IPhenotypeService;
import com.google.android.gms.phenotype.internal.PhenotypeClientImpl;
import com.google.android.gms.usagereporting.UsageReporting;
import com.google.android.gms.usagereporting.internal.IUsageReportingOptInOptionsChangedListener;
import com.google.android.gms.usagereporting.internal.UsageReportingClientImpl;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class InternalUsageReportingClient$$ExternalSyntheticLambda3 implements RemoteCall {
    public final /* synthetic */ GoogleApi InternalUsageReportingClient$$ExternalSyntheticLambda3$ar$f$0;
    public final /* synthetic */ Object InternalUsageReportingClient$$ExternalSyntheticLambda3$ar$f$2$ar$class_merging$8aa44472_0;
    public final /* synthetic */ ListenerHolder f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ InternalUsageReportingClient$$ExternalSyntheticLambda3(PhenotypeClient phenotypeClient, String str, ListenerHolder listenerHolder, int i) {
        this.switching_field = i;
        this.InternalUsageReportingClient$$ExternalSyntheticLambda3$ar$f$0 = phenotypeClient;
        this.InternalUsageReportingClient$$ExternalSyntheticLambda3$ar$f$2$ar$class_merging$8aa44472_0 = str;
        this.f$1 = listenerHolder;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object obj, Object obj2) {
        if (this.switching_field != 0) {
            ((IPhenotypeService) ((PhenotypeClientImpl) obj).getService()).registerFlagUpdateListener((String) this.InternalUsageReportingClient$$ExternalSyntheticLambda3$ar$f$2$ar$class_merging$8aa44472_0, new IFlagUpdateListener.Stub(this.f$1));
        } else {
            final IUsageReportingOptInOptionsChangedListener.Stub stub = new IUsageReportingOptInOptionsChangedListener.Stub(this.f$1);
            final InternalUsageReportingClient internalUsageReportingClient = (InternalUsageReportingClient) this.InternalUsageReportingClient$$ExternalSyntheticLambda3$ar$f$0;
            final AppLifecycleMonitor appLifecycleMonitor = (AppLifecycleMonitor) obj2;
            ((UsageReportingClientImpl) obj).setOptInOptionsChangedListenerConnectionless$ar$class_merging((IUsageReportingOptInOptionsChangedListener.Stub) this.InternalUsageReportingClient$$ExternalSyntheticLambda3$ar$f$2$ar$class_merging$8aa44472_0, stub, new UsageReporting.TaskResultHolder(appLifecycleMonitor) { // from class: com.google.android.gms.usagereporting.InternalUsageReportingClient.7
                final /* synthetic */ InternalUsageReportingClient this$0;
                final /* synthetic */ IUsageReportingOptInOptionsChangedListener.Stub val$newListener$ar$class_merging;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass7(final InternalUsageReportingClient internalUsageReportingClient2, final AppLifecycleMonitor appLifecycleMonitor2, final IUsageReportingOptInOptionsChangedListener.Stub stub2) {
                    super(appLifecycleMonitor2);
                    r3 = stub2;
                    r1 = internalUsageReportingClient2;
                }

                @Override // com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder
                public final /* bridge */ /* synthetic */ void setResult$ar$ds() {
                    ((UsageReporting.UsageReportingOptions) r1.apiOptions).listener$ar$class_merging$8aa44472_0 = r3;
                    this.completionSource$ar$class_merging$ar$class_merging.setResult(null);
                }
            });
        }
    }

    public /* synthetic */ InternalUsageReportingClient$$ExternalSyntheticLambda3(InternalUsageReportingClient internalUsageReportingClient, ListenerHolder listenerHolder, IUsageReportingOptInOptionsChangedListener.Stub stub, int i) {
        this.switching_field = i;
        this.InternalUsageReportingClient$$ExternalSyntheticLambda3$ar$f$0 = internalUsageReportingClient;
        this.f$1 = listenerHolder;
        this.InternalUsageReportingClient$$ExternalSyntheticLambda3$ar$f$2$ar$class_merging$8aa44472_0 = stub;
    }
}
