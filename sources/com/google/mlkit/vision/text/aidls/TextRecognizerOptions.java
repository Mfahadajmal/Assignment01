package com.google.mlkit.vision.text.aidls;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ServiceDumpRequestCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextRecognizerOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<TextRecognizerOptions> CREATOR = new ServiceDumpRequestCreator(10);
    public final String configLabel;
    public final int detectionTypeValue;
    public final boolean enableLowLatencyInBackground;
    public final boolean isMLKit;
    public final String languageHint;
    public final String libraryNameForLogging;
    public final String modelPath;

    public TextRecognizerOptions(String str, String str2, String str3, boolean z, int i, String str4, boolean z2) {
        this.configLabel = str;
        this.libraryNameForLogging = str2;
        this.modelPath = str3;
        this.languageHint = str4;
        this.detectionTypeValue = i;
        this.isMLKit = z;
        this.enableLowLatencyInBackground = z2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        String str = this.configLabel;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 1, str);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, this.libraryNameForLogging);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 3, this.modelPath);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 4, this.isMLKit);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 5, this.detectionTypeValue);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 6, this.languageHint);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 7, this.enableLowLatencyInBackground);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
