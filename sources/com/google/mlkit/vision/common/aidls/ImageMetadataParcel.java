package com.google.mlkit.vision.common.aidls;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ServiceDumpRequestCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageMetadataParcel extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ImageMetadataParcel> CREATOR = new ServiceDumpRequestCreator(5);
    public final int height;
    public final int imageFormat;
    public final int rotation;
    public final long timestampMs;
    public final int width;

    public ImageMetadataParcel(int i, int i2, int i3, int i4, long j) {
        this.imageFormat = i;
        this.width = i2;
        this.height = i3;
        this.rotation = i4;
        this.timestampMs = j;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = this.imageFormat;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1, i2);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, this.width);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, this.height);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 4, this.rotation);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 5, this.timestampMs);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
