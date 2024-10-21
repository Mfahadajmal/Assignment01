package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.AdditionalConsentConfigCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GenericDimension extends AbstractSafeParcelable implements Comparable<GenericDimension> {
    public static final Parcelable.Creator<GenericDimension> CREATOR = new AdditionalConsentConfigCreator(19);
    public final int instance;
    public final int namespace;

    public GenericDimension(int i, int i2) {
        this.namespace = i;
        this.instance = i2;
    }

    @Override // java.lang.Comparable
    public final int compareTo(GenericDimension genericDimension) {
        int i = this.namespace;
        int i2 = genericDimension.namespace;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        int i3 = this.instance;
        int i4 = genericDimension.instance;
        if (i3 < i4) {
            return -1;
        }
        return i3 > i4 ? 1 : 0;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof GenericDimension) || compareTo((GenericDimension) obj) != 0) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return (this.namespace * 31) + this.instance;
    }

    public final String toString() {
        return "GenericDimension(" + this.namespace + ", " + this.instance + ")";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1, this.namespace);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, this.instance);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
