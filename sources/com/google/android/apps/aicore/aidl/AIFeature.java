package com.google.android.apps.aicore.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.widget.MainSwitchBar;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AIFeature extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AIFeature> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(5);
    public final int id;
    public final String modelName;
    public final String name;
    public final int type;
    public final int variant;

    public AIFeature(String str, String str2, int i, int i2, int i3) {
        this.name = str;
        this.modelName = str2;
        this.type = i;
        this.variant = i2;
        this.id = i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AIFeature)) {
            return false;
        }
        AIFeature aIFeature = (AIFeature) obj;
        if (this.type == aIFeature.type && this.variant == aIFeature.variant && Objects.equals(this.name, aIFeature.name) && this.id == aIFeature.id) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.name, Integer.valueOf(this.type), Integer.valueOf(this.variant), Integer.valueOf(this.id));
    }

    public final String toString() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("name", this.name);
        stringHelper.addHolder$ar$ds("modelName", this.modelName);
        return stringHelper.add("type", this.type).add("variant", this.variant).add("id", this.id).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        String str = this.name;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 1, str);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, this.modelName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, this.type);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 4, this.variant);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 5, this.id);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
