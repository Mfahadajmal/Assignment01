package com.google.android.gms.vision.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ServiceDumpCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FrameMetadataParcel extends AbstractSafeParcelable {
    public static final Parcelable.Creator<FrameMetadataParcel> CREATOR = new ServiceDumpCreator(11);
    public int height;
    public int id;
    public int rotation;
    public long timestampMillis;
    public int width;

    public FrameMetadataParcel() {
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, this.width);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, this.height);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 4, this.id);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 5, this.timestampMillis);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 6, this.rotation);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    public FrameMetadataParcel(int i, int i2, int i3, long j, int i4) {
        this.width = i;
        this.height = i2;
        this.id = i3;
        this.timestampMillis = j;
        this.rotation = i4;
    }
}
