package com.google.android.gms.feedback;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ThemeSettings extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ThemeSettings> CREATOR = new AdditionalConsentConfigCreator(4);
    int primaryColor;
    public int themeId;

    public ThemeSettings(int i, int i2) {
        this.themeId = i;
        this.primaryColor = i2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, this.themeId);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, this.primaryColor);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    public ThemeSettings() {
        this(3, 0);
    }
}
