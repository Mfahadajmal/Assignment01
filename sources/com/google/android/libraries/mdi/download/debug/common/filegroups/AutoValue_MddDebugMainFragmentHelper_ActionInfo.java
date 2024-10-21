package com.google.android.libraries.mdi.download.debug.common.filegroups;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.feedback.ServiceDumpCreator;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugMainFragmentHelper;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AutoValue_MddDebugMainFragmentHelper_ActionInfo extends MddDebugMainFragmentHelper.ActionInfo {
    public static final Parcelable.Creator<AutoValue_MddDebugMainFragmentHelper_ActionInfo> CREATOR = new ServiceDumpCreator(17);

    public AutoValue_MddDebugMainFragmentHelper_ActionInfo(String str, String str2) {
        super(str, str2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.action);
        if (this.tag == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(this.tag);
        }
    }
}
