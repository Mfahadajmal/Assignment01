package com.google.android.gms.clearcut.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.widget.MainSwitchBar;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DataCollectionIdentifierParcelable extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DataCollectionIdentifierParcelable> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(17);
    public final int collectionId;
    public final int initiatorCategoryValue;
    public final int useCaseIdValue;

    public DataCollectionIdentifierParcelable(int i, int i2, int i3) {
        this.useCaseIdValue = i;
        this.collectionId = i2;
        this.initiatorCategoryValue = i3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = this.useCaseIdValue;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1, i2);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, this.collectionId);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, this.initiatorCategoryValue);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
