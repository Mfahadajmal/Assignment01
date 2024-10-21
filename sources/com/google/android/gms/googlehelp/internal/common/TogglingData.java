package com.google.android.gms.googlehelp.internal.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.AdditionalConsentConfigCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TogglingData extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<TogglingData> CREATOR = new AdditionalConsentConfigCreator(10);
    String callingPackageName;
    String pipFileName;
    String pipTitle;

    private TogglingData() {
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, this.callingPackageName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 3, this.pipFileName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 4, this.pipTitle);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    public TogglingData(String str, String str2, String str3) {
        this.callingPackageName = str;
        this.pipFileName = str2;
        this.pipTitle = str3;
    }
}
