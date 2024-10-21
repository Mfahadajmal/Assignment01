package com.google.mlkit.vision.text.aidls;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ServiceDumpRequestCreator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextParcel extends AbstractSafeParcelable {
    public static final Parcelable.Creator<TextParcel> CREATOR = new ServiceDumpRequestCreator(9);
    public final String text;
    public final List textBlocks;

    public TextParcel(String str, List list) {
        this.text = str;
        this.textBlocks = list;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        String str = this.text;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 1, str);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedList$ar$ds(parcel, 2, this.textBlocks);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
