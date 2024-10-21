package com.google.android.gms.auth.api.proxy;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.widget.MainSwitchBar;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProxyResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ProxyResponse> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(13);
    public final byte[] body;
    public final int googlePlayServicesStatusCode;
    final Bundle headers;
    public final PendingIntent recoveryAction;
    public final int statusCode;
    final int versionCode;

    public ProxyResponse(int i, int i2, PendingIntent pendingIntent, int i3, Bundle bundle, byte[] bArr) {
        this.versionCode = i;
        this.googlePlayServicesStatusCode = i2;
        this.statusCode = i3;
        this.headers = bundle;
        this.body = bArr;
        this.recoveryAction = pendingIntent;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1, this.googlePlayServicesStatusCode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 2, this.recoveryAction, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, this.statusCode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBundle$ar$ds(parcel, 4, this.headers);
        StrictModeUtils$VmPolicyBuilderCompatS.writeByteArray$ar$ds(parcel, 5, this.body);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1000, this.versionCode);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
