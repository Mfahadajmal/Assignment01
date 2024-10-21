package com.google.mlkit.vision.text.aidls;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ServiceDumpRequestCreator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextElementParcel extends AbstractSafeParcelable {
    public static final Parcelable.Creator<TextElementParcel> CREATOR = new ServiceDumpRequestCreator(7);
    public final float angle;
    public final Rect boundingBox;
    public final float confidence;
    public final List cornerPoints;
    public final String recognizedLanguage;
    public final String text;
    public final List textSymbols;

    public TextElementParcel(String str, Rect rect, List list, String str2, float f, float f2, List list2) {
        this.text = str;
        this.boundingBox = rect;
        this.cornerPoints = list;
        this.recognizedLanguage = str2;
        this.confidence = f;
        this.angle = f2;
        this.textSymbols = list2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        String str = this.text;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 1, str);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 2, this.boundingBox, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedList$ar$ds(parcel, 3, this.cornerPoints);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 4, this.recognizedLanguage);
        StrictModeUtils$VmPolicyBuilderCompatS.writeFloat(parcel, 5, this.confidence);
        StrictModeUtils$VmPolicyBuilderCompatS.writeFloat(parcel, 6, this.angle);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedList$ar$ds(parcel, 7, this.textSymbols);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
