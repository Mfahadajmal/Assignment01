package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.FeatureCreator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Scope extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<Scope> CREATOR = new FeatureCreator(5);
    final int mVersionCode;
    public final String scopeUri;

    public Scope(int i, String str) {
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotEmpty$ar$ds$c11d1227_0(str, "scopeUri must not be null or empty");
        this.mVersionCode = i;
        this.scopeUri = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scope)) {
            return false;
        }
        return this.scopeUri.equals(((Scope) obj).scopeUri);
    }

    public final int hashCode() {
        return this.scopeUri.hashCode();
    }

    public final String toString() {
        return this.scopeUri;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1, this.mVersionCode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, this.scopeUri);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    public Scope(String str) {
        this(1, str);
    }
}
