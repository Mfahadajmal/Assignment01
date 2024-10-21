package com.google.android.gms.clearcut.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.widget.MainSwitchBar;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import j$.util.Objects;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LogErrorParcelable extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LogErrorParcelable> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(18);
    public final int clearcutStatusCode;
    public int errorCount;
    public final String logSourceName;

    public LogErrorParcelable(String str, int i, int i2) {
        this.logSourceName = str;
        this.clearcutStatusCode = i;
        this.errorCount = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LogErrorParcelable)) {
            return false;
        }
        LogErrorParcelable logErrorParcelable = (LogErrorParcelable) obj;
        if (Objects.equals(this.logSourceName, logErrorParcelable.logSourceName) && this.clearcutStatusCode == logErrorParcelable.clearcutStatusCode && this.errorCount == logErrorParcelable.errorCount) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.logSourceName, Integer.valueOf(this.clearcutStatusCode), Integer.valueOf(this.errorCount)});
    }

    public final String toString() {
        return "LogErrorParcelable[LogSourceName: " + this.logSourceName + ", ClearcutStatusCode: " + this.clearcutStatusCode + ", ErrorCount: " + this.errorCount + "]";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 1, this.logSourceName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, this.clearcutStatusCode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, this.errorCount);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
