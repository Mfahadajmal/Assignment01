package com.google.android.gms.auth.account.data;

import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IGetTokenWithDetailsCallback extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Stub extends BaseStub implements IGetTokenWithDetailsCallback {
        final /* synthetic */ AppLifecycleMonitor val$completionSource$ar$class_merging$ar$class_merging;

        public Stub() {
            super("com.google.android.gms.auth.account.data.IGetTokenWithDetailsCallback");
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            if (i == 2) {
                Status status = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                Bundle bundle = (Bundle) Codecs.createParcelable(parcel, Bundle.CREATOR);
                Codecs.enforceNoDataAvail(parcel);
                onResponse(status, bundle);
                return true;
            }
            return false;
        }

        public final void onResponse(Status status, Bundle bundle) {
            if (!StrictModeUtils$VmPolicyBuilderCompatS.trySetResultOrApiException$ar$class_merging$ar$class_merging(status, bundle, this.val$completionSource$ar$class_merging$ar$class_merging)) {
                InternalGoogleAuthServiceClient.logger$ar$class_merging$adff595e_0$ar$class_merging.w("The task is already complete.", new Object[0]);
            }
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Stub(AppLifecycleMonitor appLifecycleMonitor) {
            this();
            this.val$completionSource$ar$class_merging$ar$class_merging = appLifecycleMonitor;
        }
    }
}
