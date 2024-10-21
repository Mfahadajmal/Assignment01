package com.google.android.gms.common.moduleinstall.internal;

import _COROUTINE._BOUNDARY;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.util.SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1;
import com.google.android.gms.common.FeatureCreator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApiFeatureRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ApiFeatureRequest> CREATOR = new FeatureCreator(20);
    public static final Comparator FEATURE_COMPARATOR = new SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1(9);
    public final List apiFeatures;
    public final String callingPackage;
    public final String featureRequestSessionId;
    public final boolean isUrgent;

    public ApiFeatureRequest(List list, boolean z, String str, String str2) {
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(list);
        this.apiFeatures = list;
        this.isUrgent = z;
        this.featureRequestSessionId = str;
        this.callingPackage = str2;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ApiFeatureRequest)) {
            return false;
        }
        ApiFeatureRequest apiFeatureRequest = (ApiFeatureRequest) obj;
        if (this.isUrgent != apiFeatureRequest.isUrgent || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.apiFeatures, apiFeatureRequest.apiFeatures) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.featureRequestSessionId, apiFeatureRequest.featureRequestSessionId) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.callingPackage, apiFeatureRequest.callingPackage)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.isUrgent), this.apiFeatures, this.featureRequestSessionId, this.callingPackage});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        List list = this.apiFeatures;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedList$ar$ds(parcel, 1, list);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 2, this.isUrgent);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 3, this.featureRequestSessionId);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 4, this.callingPackage);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
