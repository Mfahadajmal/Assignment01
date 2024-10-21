package com.google.android.gms.vision.text.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ServiceDumpCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SymbolBoxParcel extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SymbolBoxParcel> CREATOR = new ServiceDumpCreator(14);

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel));
    }
}
