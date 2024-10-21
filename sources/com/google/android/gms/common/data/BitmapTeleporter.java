package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.FeatureCreator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import java.io.File;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BitmapTeleporter extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<BitmapTeleporter> CREATOR = new FeatureCreator(7);
    ParcelFileDescriptor mFileDescriptor;
    final int mType;
    final int mVersionCode;
    public File tempDir;

    public BitmapTeleporter(int i, ParcelFileDescriptor parcelFileDescriptor, int i2) {
        this.mVersionCode = i;
        this.mFileDescriptor = parcelFileDescriptor;
        this.mType = i2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        if (this.mFileDescriptor != null) {
            int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
            StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1, this.mVersionCode);
            StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 2, this.mFileDescriptor, i | 1);
            StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, this.mType);
            StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
            this.mFileDescriptor = null;
            return;
        }
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(null);
        throw null;
    }
}
