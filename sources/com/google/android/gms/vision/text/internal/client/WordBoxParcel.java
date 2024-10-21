package com.google.android.gms.vision.text.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ServiceDumpCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WordBoxParcel extends AbstractSafeParcelable {
    public static final Parcelable.Creator<WordBoxParcel> CREATOR = new ServiceDumpCreator(16);
    public final BoundingBoxParcel box;
    public final float confidence;
    public final boolean hasSpaceAfter;
    public final String language;
    public final BoundingBoxParcel origImageBox;
    public final SymbolBoxParcel[] symbols;
    public final String utf8String;

    public WordBoxParcel(SymbolBoxParcel[] symbolBoxParcelArr, BoundingBoxParcel boundingBoxParcel, BoundingBoxParcel boundingBoxParcel2, String str, float f, String str2, boolean z) {
        this.symbols = symbolBoxParcelArr;
        this.box = boundingBoxParcel;
        this.origImageBox = boundingBoxParcel2;
        this.utf8String = str;
        this.confidence = f;
        this.language = str2;
        this.hasSpaceAfter = z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedArray$ar$ds(parcel, 2, this.symbols, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 3, this.box, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 4, this.origImageBox, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 5, this.utf8String);
        StrictModeUtils$VmPolicyBuilderCompatS.writeFloat(parcel, 6, this.confidence);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 7, this.language);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 8, this.hasSpaceAfter);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
