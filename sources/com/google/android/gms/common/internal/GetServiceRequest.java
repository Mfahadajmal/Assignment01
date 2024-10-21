package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.FeatureCreator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GetServiceRequest extends AbstractSafeParcelable {
    public IBinder accountAccessorBinder;
    public String attributionTag;
    public int binderPropagationProtocol;
    public String callingPackage;
    public Feature[] clientApiFeatures;
    public int clientLibraryVersion;
    public Account clientRequestedAccount;
    public Feature[] clientRequiredFeatures;
    public Bundle extraArgs;
    public boolean requestingConnectionInfo;
    public boolean requestingTelemetryConfiguration;
    public Scope[] scopes;
    public final int serviceId;
    public final int version;
    public static final Parcelable.Creator<GetServiceRequest> CREATOR = new FeatureCreator(11);
    public static final Scope[] EMPTY_SCOPES = new Scope[0];
    public static final Feature[] EMPTY_FEATURES = new Feature[0];

    public GetServiceRequest(int i, int i2, int i3, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account, Feature[] featureArr, Feature[] featureArr2, boolean z, int i4, boolean z2, String str2) {
        IAccountAccessor iAccountAccessor$Stub$Proxy;
        scopeArr = scopeArr == null ? EMPTY_SCOPES : scopeArr;
        bundle = bundle == null ? new Bundle() : bundle;
        featureArr = featureArr == null ? EMPTY_FEATURES : featureArr;
        featureArr2 = featureArr2 == null ? EMPTY_FEATURES : featureArr2;
        this.version = i;
        this.serviceId = i2;
        this.clientLibraryVersion = i3;
        if ("com.google.android.gms".equals(str)) {
            this.callingPackage = "com.google.android.gms";
        } else {
            this.callingPackage = str;
        }
        if (i < 2) {
            Account account2 = null;
            if (iBinder != null) {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
                if (queryLocalInterface instanceof IAccountAccessor) {
                    iAccountAccessor$Stub$Proxy = (IAccountAccessor) queryLocalInterface;
                } else {
                    iAccountAccessor$Stub$Proxy = new IAccountAccessor$Stub$Proxy(iBinder);
                }
                if (iAccountAccessor$Stub$Proxy != null) {
                    long clearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        try {
                            account2 = iAccountAccessor$Stub$Proxy.getAccount();
                        } catch (RemoteException unused) {
                            Log.w("AccountAccessor", "Remote account accessor probably died");
                        }
                    } finally {
                        Binder.restoreCallingIdentity(clearCallingIdentity);
                    }
                }
            }
            this.clientRequestedAccount = account2;
        } else {
            this.accountAccessorBinder = iBinder;
            this.clientRequestedAccount = account;
        }
        this.scopes = scopeArr;
        this.extraArgs = bundle;
        this.clientRequiredFeatures = featureArr;
        this.clientApiFeatures = featureArr2;
        this.requestingConnectionInfo = z;
        this.binderPropagationProtocol = i4;
        this.requestingTelemetryConfiguration = z2;
        this.attributionTag = str2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        FeatureCreator.writeToParcel(this, parcel, i);
    }
}
