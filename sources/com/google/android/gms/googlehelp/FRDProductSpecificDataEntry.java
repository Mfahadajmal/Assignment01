package com.google.android.gms.googlehelp;

import _COROUTINE._BOUNDARY;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.AdditionalConsentConfigCreator;
import java.util.Arrays;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FRDProductSpecificDataEntry extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<FRDProductSpecificDataEntry> CREATOR = new AdditionalConsentConfigCreator(5);
    final Boolean boolValue;
    final byte[][] bytesValues;
    final List dateTimeValues;
    final List enumValueIdentifiers;
    final int frdIdentifier;
    final int frdType;
    final List intValues;
    final List stringValues;

    public FRDProductSpecificDataEntry(int i, int i2, List list, List list2, List list3, List list4, byte[][] bArr, boolean z) {
        this.frdIdentifier = i;
        this.frdType = i2;
        this.enumValueIdentifiers = list;
        this.intValues = list2;
        this.stringValues = list3;
        this.dateTimeValues = list4;
        this.bytesValues = bArr;
        this.boolValue = Boolean.valueOf(z);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FRDProductSpecificDataEntry)) {
            return false;
        }
        FRDProductSpecificDataEntry fRDProductSpecificDataEntry = (FRDProductSpecificDataEntry) obj;
        if (this.frdIdentifier == fRDProductSpecificDataEntry.frdIdentifier && this.frdType == fRDProductSpecificDataEntry.frdType && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.enumValueIdentifiers, fRDProductSpecificDataEntry.enumValueIdentifiers) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.intValues, fRDProductSpecificDataEntry.intValues) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.stringValues, fRDProductSpecificDataEntry.stringValues) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.dateTimeValues, fRDProductSpecificDataEntry.dateTimeValues) && Arrays.equals(this.bytesValues, fRDProductSpecificDataEntry.bytesValues) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.boolValue, fRDProductSpecificDataEntry.boolValue)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.frdIdentifier), Integer.valueOf(this.frdType), this.enumValueIdentifiers, this.intValues, this.stringValues, this.dateTimeValues, Integer.valueOf(Arrays.deepHashCode(this.bytesValues)), this.boolValue});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, this.frdIdentifier);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, this.frdType);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringList$ar$ds(parcel, 4, this.enumValueIdentifiers);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLongList$ar$ds(parcel, 5, this.intValues);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringList$ar$ds(parcel, 6, this.stringValues);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLongList$ar$ds(parcel, 7, this.dateTimeValues);
        StrictModeUtils$VmPolicyBuilderCompatS.writeByteArrayArray$ar$ds(parcel, 8, this.bytesValues);
        StrictModeUtils$VmPolicyBuilderCompatS.writeHeader(parcel, 9, 4);
        parcel.writeInt(this.boolValue.booleanValue() ? 1 : 0);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
