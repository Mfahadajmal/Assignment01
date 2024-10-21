package com.google.android.gms.googlehelp.internal.common;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.AdditionalConsentConfigCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OverflowMenuItem extends AbstractSafeParcelable {
    public static final Parcelable.Creator<OverflowMenuItem> CREATOR = new AdditionalConsentConfigCreator(9);
    final int id;
    final Intent intent;
    final String title;

    public OverflowMenuItem(int i, String str, Intent intent) {
        this.id = i;
        this.title = str;
        this.intent = intent;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, this.id);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 3, this.title);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 4, this.intent, i);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
