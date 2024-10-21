package com.google.android.gms.common;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GoogleCertificates;
import com.google.android.gms.common.internal.ICertData;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleCertificatesQuery extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GoogleCertificatesQuery> CREATOR = new FeatureCreator(4);
    public final boolean allowTestKeys;
    private final ICertData.Stub callingCertificate$ar$class_merging;
    public final String callingPackage;
    public final boolean ignoreTestKeysOverride;

    public GoogleCertificatesQuery(String str, ICertData.Stub stub, boolean z, boolean z2) {
        this.callingPackage = str;
        this.callingCertificate$ar$class_merging = stub;
        this.allowTestKeys = z;
        this.ignoreTestKeysOverride = z2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        String str = this.callingPackage;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 1, str);
        ICertData.Stub stub = this.callingCertificate$ar$class_merging;
        if (stub == null) {
            Log.w("GoogleCertificatesQuery", "certificate binder is null");
            stub = null;
        }
        StrictModeUtils$VmPolicyBuilderCompatS.writeIBinder$ar$ds(parcel, 2, stub);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 3, this.allowTestKeys);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 4, this.ignoreTestKeysOverride);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    public GoogleCertificatesQuery(String str, IBinder iBinder, boolean z, boolean z2) {
        ICertData proxy;
        this.callingPackage = str;
        GoogleCertificates.FullCertData fullCertData = null;
        if (iBinder != null) {
            try {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ICertData");
                if (queryLocalInterface instanceof ICertData) {
                    proxy = (ICertData) queryLocalInterface;
                } else {
                    proxy = new ICertData.Stub.Proxy(iBinder);
                }
                IObjectWrapper bytesWrapped = proxy.getBytesWrapped();
                byte[] bArr = bytesWrapped == null ? null : (byte[]) IObjectWrapper.Stub.unwrap(bytesWrapped);
                if (bArr != null) {
                    fullCertData = new GoogleCertificates.FullCertData(bArr);
                } else {
                    Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
                }
            } catch (RemoteException e) {
                Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", e);
            }
        }
        this.callingCertificate$ar$class_merging = fullCertData;
        this.allowTestKeys = z;
        this.ignoreTestKeysOverride = z2;
    }
}
