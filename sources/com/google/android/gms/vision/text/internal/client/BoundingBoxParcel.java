package com.google.android.gms.vision.text.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ServiceDumpCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BoundingBoxParcel extends AbstractSafeParcelable {
    public static final Parcelable.Creator<BoundingBoxParcel> CREATOR = new ServiceDumpCreator(12);
    public final float angleDegrees;
    public final int height;
    public final int left;
    public final int top;
    public final int width;

    public BoundingBoxParcel(int i, int i2, int i3, int i4, float f) {
        this.left = i;
        this.top = i2;
        this.width = i3;
        this.height = i4;
        this.angleDegrees = f;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, this.left);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, this.top);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 4, this.width);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 5, this.height);
        StrictModeUtils$VmPolicyBuilderCompatS.writeFloat(parcel, 6, this.angleDegrees);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
