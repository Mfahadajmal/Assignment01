package com.google.android.gms.common;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleCertificatesLookupQuery extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GoogleCertificatesLookupQuery> CREATOR = new FeatureCreator(2);
    public final boolean allowTestKeys;
    private final Context callingContext;
    public final String callingPackage;
    public final boolean ignoreTestKeysOverride;
    public final boolean includeHashesInErrorMessage;
    public final boolean isChimeraPackage;

    public GoogleCertificatesLookupQuery(String str, boolean z, boolean z2, IBinder iBinder, boolean z3, boolean z4) {
        IObjectWrapper proxy;
        this.callingPackage = str;
        this.allowTestKeys = z;
        this.ignoreTestKeysOverride = z2;
        if (iBinder == null) {
            proxy = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            if (queryLocalInterface instanceof IObjectWrapper) {
                proxy = (IObjectWrapper) queryLocalInterface;
            } else {
                proxy = new IObjectWrapper.Stub.Proxy(iBinder);
            }
        }
        this.callingContext = (Context) IObjectWrapper.Stub.unwrap(proxy);
        this.isChimeraPackage = z3;
        this.includeHashesInErrorMessage = z4;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        String str = this.callingPackage;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 1, str);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 2, this.allowTestKeys);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 3, this.ignoreTestKeysOverride);
        StrictModeUtils$VmPolicyBuilderCompatS.writeIBinder$ar$ds(parcel, 4, new IObjectWrapper.Stub(this.callingContext));
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 5, this.isChimeraPackage);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 6, this.includeHashesInErrorMessage);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
