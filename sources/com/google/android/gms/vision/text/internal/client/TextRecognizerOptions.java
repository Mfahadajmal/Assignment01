package com.google.android.gms.vision.text.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ServiceDumpCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextRecognizerOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<TextRecognizerOptions> CREATOR = new ServiceDumpCreator(15);
    public final String customModelsDir;

    public TextRecognizerOptions(String str) {
        this.customModelsDir = str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        String str = this.customModelsDir;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, str);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    public TextRecognizerOptions() {
        this(null);
    }
}
