package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.AdditionalConsentConfigCreator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FlagOverrides extends AbstractSafeParcelable {
    public static final Parcelable.Creator<FlagOverrides> CREATOR = new AdditionalConsentConfigCreator(18);
    public final List overrides;

    public FlagOverrides(List list) {
        this.overrides = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlagOverrides)) {
            return false;
        }
        return this.overrides.equals(((FlagOverrides) obj).overrides);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FlagOverrides(");
        boolean z = true;
        for (FlagOverride flagOverride : this.overrides) {
            if (!z) {
                sb.append(", ");
            }
            flagOverride.appendToString(sb);
            z = false;
        }
        sb.append(")");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedList$ar$ds(parcel, 2, this.overrides);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
