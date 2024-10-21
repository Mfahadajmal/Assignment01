package com.google.android.gms.pseudonymous;

import _COROUTINE._BOUNDARY;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.AdditionalConsentConfigCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PseudonymousIdToken extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PseudonymousIdToken> CREATOR = new AdditionalConsentConfigCreator(20);
    public final String value;

    public PseudonymousIdToken(String str) {
        this.value = str;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof PseudonymousIdToken) {
            return _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.value, ((PseudonymousIdToken) obj).value);
        }
        return false;
    }

    public final int hashCode() {
        return this.value.hashCode();
    }

    public final String toString() {
        return "PseudonymousIdToken[" + this.value + "]";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        String str = this.value;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, str);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
