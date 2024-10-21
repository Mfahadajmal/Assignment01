package com.google.android.gms.clearcut.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.widget.MainSwitchBar;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import j$.util.DesugarCollections;
import java.util.Arrays;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatchedLogErrorParcelable extends AbstractSafeParcelable {
    public static final Parcelable.Creator<BatchedLogErrorParcelable> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(16);
    public final List logErrors;

    public BatchedLogErrorParcelable(List list) {
        this.logErrors = DesugarCollections.unmodifiableList(list);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BatchedLogErrorParcelable)) {
            return false;
        }
        return this.logErrors.equals(((BatchedLogErrorParcelable) obj).logErrors);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.logErrors});
    }

    public final String toString() {
        return "BatchedLogErrorParcelable[LogErrors: " + this.logErrors + "]";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        List list = this.logErrors;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedList$ar$ds(parcel, 1, list);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
