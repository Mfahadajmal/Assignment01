package com.google.android.apps.aicore.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.widget.MainSwitchBar;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageDescriptionResult extends AbstractSafeParcelable implements SafeParcelable {
    public static final Parcelable.Creator<ImageDescriptionResult> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(7);
    public final List descriptions;
    public final InferenceEventTraceResult inferenceEventTraceResult;

    public ImageDescriptionResult(List list, InferenceEventTraceResult inferenceEventTraceResult) {
        this.descriptions = list;
        this.inferenceEventTraceResult = inferenceEventTraceResult;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        List list = this.descriptions;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringList$ar$ds(parcel, 1, list);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 2, this.inferenceEventTraceResult, i);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
