package com.google.android.gms.phenotype;

import _COROUTINE._BOUNDARY;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.AdditionalConsentConfigCreator;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Configuration extends AbstractSafeParcelable implements Comparable<Configuration> {
    public static final Parcelable.Creator<Configuration> CREATOR = new AdditionalConsentConfigCreator(12);
    public final String[] deleteFlags;
    public final Map flagMap = new TreeMap();
    public final int flagType;
    public final Flag[] flags;

    public Configuration(int i, Flag[] flagArr, String[] strArr) {
        this.flagType = i;
        this.flags = flagArr;
        for (Flag flag : flagArr) {
            this.flagMap.put(flag.name, flag);
        }
        this.deleteFlags = strArr;
        if (strArr != null) {
            Arrays.sort(strArr);
        }
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Configuration configuration) {
        return this.flagType - configuration.flagType;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Configuration) {
            Configuration configuration = (Configuration) obj;
            if (this.flagType == configuration.flagType && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.flagMap, configuration.flagMap) && Arrays.equals(this.deleteFlags, configuration.deleteFlags)) {
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Configuration(");
        sb.append(this.flagType);
        sb.append(", (");
        Iterator it = this.flagMap.values().iterator();
        while (it.hasNext()) {
            sb.append((Flag) it.next());
            sb.append(", ");
        }
        sb.append("), (");
        String[] strArr = this.deleteFlags;
        if (strArr != null) {
            for (String str : strArr) {
                sb.append(str);
                sb.append(", ");
            }
        } else {
            sb.append("null");
        }
        sb.append("))");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, this.flagType);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedArray$ar$ds(parcel, 3, this.flags, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringArray$ar$ds(parcel, 4, this.deleteFlags);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
