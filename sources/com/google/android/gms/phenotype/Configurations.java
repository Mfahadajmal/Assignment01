package com.google.android.gms.phenotype;

import _COROUTINE._BOUNDARY;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.AdditionalConsentConfigCreator;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Configurations extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Configurations> CREATOR = new AdditionalConsentConfigCreator(13);
    public final Map configurationMap = new TreeMap();
    public final long configurationVersion;
    public final Configuration[] configurations;
    public final byte[] experimentToken;
    public final boolean isDelta;
    public final String serverToken;
    public final String snapshotToken;

    public Configurations(String str, String str2, Configuration[] configurationArr, boolean z, byte[] bArr, long j) {
        this.snapshotToken = str;
        this.serverToken = str2;
        this.configurations = configurationArr;
        this.isDelta = z;
        this.experimentToken = bArr;
        this.configurationVersion = j;
        for (Configuration configuration : configurationArr) {
            this.configurationMap.put(Integer.valueOf(configuration.flagType), configuration);
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Configurations) {
            Configurations configurations = (Configurations) obj;
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.snapshotToken, configurations.snapshotToken) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.serverToken, configurations.serverToken) && this.configurationMap.equals(configurations.configurationMap) && this.isDelta == configurations.isDelta && Arrays.equals(this.experimentToken, configurations.experimentToken) && this.configurationVersion == configurations.configurationVersion) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.snapshotToken, this.serverToken, this.configurationMap, Boolean.valueOf(this.isDelta), this.experimentToken, Long.valueOf(this.configurationVersion)});
    }

    public final String toString() {
        String encodeToString;
        StringBuilder sb = new StringBuilder("Configurations('");
        sb.append(this.snapshotToken);
        sb.append("', '");
        sb.append(this.serverToken);
        sb.append("', (");
        Iterator it = this.configurationMap.values().iterator();
        while (it.hasNext()) {
            sb.append((Configuration) it.next());
            sb.append(", ");
        }
        sb.append("), ");
        sb.append(this.isDelta);
        sb.append(", ");
        byte[] bArr = this.experimentToken;
        if (bArr == null) {
            encodeToString = "null";
        } else {
            encodeToString = Base64.encodeToString(bArr, 3);
        }
        sb.append(encodeToString);
        sb.append(", ");
        sb.append(this.configurationVersion);
        sb.append(')');
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, this.snapshotToken);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 3, this.serverToken);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedArray$ar$ds(parcel, 4, this.configurations, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 5, this.isDelta);
        StrictModeUtils$VmPolicyBuilderCompatS.writeByteArray$ar$ds(parcel, 6, this.experimentToken);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 7, this.configurationVersion);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
