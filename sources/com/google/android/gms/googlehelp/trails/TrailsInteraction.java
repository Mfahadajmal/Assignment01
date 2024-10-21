package com.google.android.gms.googlehelp.trails;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.AdditionalConsentConfigCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TrailsInteraction extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<TrailsInteraction> CREATOR = new AdditionalConsentConfigCreator(11);
    public final String eventName;
    public final String eventType;
    public final long timestampMs;

    public TrailsInteraction(String str, long j, String str2) {
        this.eventName = str;
        this.timestampMs = j;
        this.eventType = str2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 1, this.eventName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 2, this.timestampMs);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 3, this.eventType);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
