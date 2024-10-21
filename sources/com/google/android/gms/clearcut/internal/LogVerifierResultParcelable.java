package com.google.android.gms.clearcut.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.widget.MainSwitchBar;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LogVerifierResultParcelable extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LogVerifierResultParcelable> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(19);
    public final boolean passed;

    public LogVerifierResultParcelable(boolean z) {
        this.passed = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof LogVerifierResultParcelable) && this.passed == ((LogVerifierResultParcelable) obj).passed) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.passed)});
    }

    public final String toString() {
        return "LogVerifierResultParcelable[" + this.passed + "]";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 1, this.passed);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
