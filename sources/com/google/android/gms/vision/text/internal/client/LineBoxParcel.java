package com.google.android.gms.vision.text.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ServiceDumpCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LineBoxParcel extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LineBoxParcel> CREATOR = new ServiceDumpCreator(13);
    public final BoundingBoxParcel baselineBox;
    public final int blockId;
    public final BoundingBoxParcel box;
    public final float confidence;
    public final boolean isVertical;
    public final String language;
    public final int orderWithinBlock;
    public final BoundingBoxParcel origImageBox;
    public final int trackId;
    public final String utf8String;
    public final WordBoxParcel[] words;

    public LineBoxParcel(WordBoxParcel[] wordBoxParcelArr, BoundingBoxParcel boundingBoxParcel, BoundingBoxParcel boundingBoxParcel2, BoundingBoxParcel boundingBoxParcel3, String str, float f, String str2, int i, boolean z, int i2, int i3) {
        this.words = wordBoxParcelArr;
        this.box = boundingBoxParcel;
        this.origImageBox = boundingBoxParcel2;
        this.baselineBox = boundingBoxParcel3;
        this.utf8String = str;
        this.confidence = f;
        this.language = str2;
        this.trackId = i;
        this.isVertical = z;
        this.blockId = i2;
        this.orderWithinBlock = i3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedArray$ar$ds(parcel, 2, this.words, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 3, this.box, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 4, this.origImageBox, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 5, this.baselineBox, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 6, this.utf8String);
        StrictModeUtils$VmPolicyBuilderCompatS.writeFloat(parcel, 7, this.confidence);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 8, this.language);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 9, this.trackId);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 10, this.isVertical);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 11, this.blockId);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 12, this.orderWithinBlock);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
