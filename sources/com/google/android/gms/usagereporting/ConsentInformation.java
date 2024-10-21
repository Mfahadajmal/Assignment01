package com.google.android.gms.usagereporting;

import _COROUTINE._BOUNDARY;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ServiceDumpCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConsentInformation extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ConsentInformation> CREATOR;
    private final List accountInformation;
    public final boolean isAnonymous;
    public final boolean zwiebackKeyed;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AccountConsentInformation extends AbstractSafeParcelable {
        public static final Parcelable.Creator<AccountConsentInformation> CREATOR = new ServiceDumpCreator(6);
        public final byte[] accountConsents;
        public final String accountName;
        public final List whitelists;

        public AccountConsentInformation(String str, byte[] bArr, List list) {
            ArrayList arrayList;
            this.accountName = str;
            this.accountConsents = bArr;
            if (list == null) {
                arrayList = new ArrayList(0);
            } else {
                arrayList = new ArrayList(list);
            }
            this.whitelists = arrayList;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AccountConsentInformation)) {
                return false;
            }
            AccountConsentInformation accountConsentInformation = (AccountConsentInformation) obj;
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.accountName, accountConsentInformation.accountName) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.accountConsents, accountConsentInformation.accountConsents) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.whitelists, accountConsentInformation.whitelists)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.accountName, this.accountConsents, this.whitelists});
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            String str = this.accountName;
            int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
            StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 1, str);
            StrictModeUtils$VmPolicyBuilderCompatS.writeByteArray$ar$ds(parcel, 2, this.accountConsents);
            ArrayList arrayList = new ArrayList(this.whitelists);
            int beginVariableData = StrictModeUtils$VmPolicyBuilderCompatS.beginVariableData(parcel, 3);
            int size = arrayList.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeInt(((Integer) arrayList.get(i2)).intValue());
            }
            StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginVariableData);
            StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
        }
    }

    static {
        new ConsentInformation(null, false, false);
        CREATOR = new ServiceDumpCreator(7);
    }

    public ConsentInformation(List list, boolean z, boolean z2) {
        ArrayList arrayList;
        if (list == null) {
            arrayList = new ArrayList(0);
        } else {
            arrayList = new ArrayList(list);
        }
        this.accountInformation = arrayList;
        this.zwiebackKeyed = z;
        this.isAnonymous = z2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConsentInformation)) {
            return false;
        }
        ConsentInformation consentInformation = (ConsentInformation) obj;
        if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.accountInformation, consentInformation.accountInformation) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(Boolean.valueOf(this.zwiebackKeyed), Boolean.valueOf(consentInformation.zwiebackKeyed))) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.accountInformation, Boolean.valueOf(this.zwiebackKeyed)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedList$ar$ds(parcel, 1, new ArrayList(this.accountInformation));
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 2, this.zwiebackKeyed);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 3, this.isAnonymous);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
