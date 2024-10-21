package com.google.android.gms.googlehelp;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.AdditionalConsentConfigCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ND4CSettings extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<ND4CSettings> CREATOR = new AdditionalConsentConfigCreator(7);
    String countryCode;
    boolean enabled;

    public ND4CSettings(boolean z, String str) {
        this.enabled = z;
        this.countryCode = str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 2, this.enabled);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 3, this.countryCode);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    public ND4CSettings() {
        this(true, "");
    }
}
