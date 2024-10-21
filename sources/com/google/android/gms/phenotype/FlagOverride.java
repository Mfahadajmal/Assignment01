package com.google.android.gms.phenotype;

import _COROUTINE._BOUNDARY;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.AdditionalConsentConfigCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FlagOverride extends AbstractSafeParcelable {
    public static final Parcelable.Creator<FlagOverride> CREATOR = new AdditionalConsentConfigCreator(17);
    public final boolean committed;
    public final String configurationName;
    public final Flag flag;
    public final String userName;

    public FlagOverride(String str, String str2, Flag flag, boolean z) {
        this.configurationName = str;
        this.userName = str2;
        this.flag = flag;
        this.committed = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void appendToString(StringBuilder sb) {
        sb.append("FlagOverride(");
        sb.append(this.configurationName);
        sb.append(", ");
        sb.append(this.userName);
        sb.append(", ");
        this.flag.appendToString(sb);
        sb.append(", ");
        sb.append(this.committed);
        sb.append(")");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlagOverride)) {
            return false;
        }
        FlagOverride flagOverride = (FlagOverride) obj;
        if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.configurationName, flagOverride.configurationName) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.userName, flagOverride.userName) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.flag, flagOverride.flag) && this.committed == flagOverride.committed) {
            return true;
        }
        return false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        appendToString(sb);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, this.configurationName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 3, this.userName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 4, this.flag, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 5, this.committed);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
