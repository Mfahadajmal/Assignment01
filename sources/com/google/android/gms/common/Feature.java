package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects$ToStringHelper;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Feature extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Feature> CREATOR = new FeatureCreator(0);
    public final String name;

    @Deprecated
    public final int oldVersion;
    private final long version;

    public Feature(String str, int i, long j) {
        this.name = str;
        this.oldVersion = i;
        this.version = j;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Feature) {
            Feature feature = (Feature) obj;
            String str = this.name;
            if (((str != null && str.equals(feature.name)) || (this.name == null && feature.name == null)) && getVersion() == feature.getVersion()) {
                return true;
            }
        }
        return false;
    }

    public final long getVersion() {
        long j = this.version;
        if (j == -1) {
            return this.oldVersion;
        }
        return j;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.name, Long.valueOf(getVersion())});
    }

    public final String toString() {
        Objects$ToStringHelper objects$ToStringHelper = new Objects$ToStringHelper(this);
        objects$ToStringHelper.add$ar$ds$bdea682c_0("name", this.name);
        objects$ToStringHelper.add$ar$ds$bdea682c_0("version", Long.valueOf(getVersion()));
        return objects$ToStringHelper.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        String str = this.name;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 1, str);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, this.oldVersion);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 3, getVersion());
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    public Feature(String str, long j) {
        this.name = str;
        this.version = j;
        this.oldVersion = -1;
    }
}
