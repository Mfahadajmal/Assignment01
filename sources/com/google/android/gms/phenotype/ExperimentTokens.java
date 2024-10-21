package com.google.android.gms.phenotype;

import _COROUTINE._BOUNDARY;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.AdditionalConsentConfigCreator;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExperimentTokens extends AbstractSafeParcelable {
    public final byte[][] additionalDirectExperimentTokens;
    public final byte[][] alwaysCrossExperimentTokens;
    public final byte[] directExperimentToken;
    public final byte[][] externalExperimentTokens;
    public final byte[][] gaiaCrossExperimentTokens;
    public final int[] genericDimensions;
    public final byte[][] otherCrossExperimentTokens;
    public final byte[][] pseudonymousCrossExperimentTokens;
    public final String user;
    public final int[] weakExperimentIds;
    public static final byte[][] EMPTY_BYTES = new byte[0];
    public static final Parcelable.Creator<ExperimentTokens> CREATOR = new AdditionalConsentConfigCreator(15);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface CrossExtractor {
        byte[][] extract(ExperimentTokens experimentTokens);
    }

    public ExperimentTokens(String str, byte[] bArr, byte[][] bArr2, byte[][] bArr3, byte[][] bArr4, byte[][] bArr5, int[] iArr, byte[][] bArr6, int[] iArr2, byte[][] bArr7) {
        this.user = str;
        this.directExperimentToken = bArr;
        this.gaiaCrossExperimentTokens = bArr2;
        this.pseudonymousCrossExperimentTokens = bArr3;
        this.alwaysCrossExperimentTokens = bArr4;
        this.otherCrossExperimentTokens = bArr5;
        this.weakExperimentIds = iArr;
        this.additionalDirectExperimentTokens = bArr6;
        this.genericDimensions = iArr2;
        this.externalExperimentTokens = bArr7;
    }

    private final Set allDirectTokens() {
        ArrayList arrayList = new ArrayList();
        byte[][] bArr = this.additionalDirectExperimentTokens;
        if (bArr != null) {
            Collections.addAll(arrayList, bArr);
        }
        byte[] bArr2 = this.directExperimentToken;
        if (bArr2 != null) {
            arrayList.add(bArr2);
        }
        return asSet((byte[][]) arrayList.toArray(new byte[0]));
    }

    private static Set asSet(int[] iArr) {
        int length;
        if (iArr != null && (length = iArr.length) != 0) {
            HashSet newHashSetWithExpectedSize = ContextDataProvider.newHashSetWithExpectedSize(length);
            for (int i : iArr) {
                newHashSetWithExpectedSize.add(Integer.valueOf(i));
            }
            return newHashSetWithExpectedSize;
        }
        return Collections.emptySet();
    }

    public static byte[][] collectCross(List list, CrossExtractor crossExtractor) {
        byte[][] extract;
        byte[][] extract2;
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            ExperimentTokens experimentTokens = (ExperimentTokens) it.next();
            if (experimentTokens != null && (extract2 = crossExtractor.extract(experimentTokens)) != null) {
                i += extract2.length;
            }
        }
        byte[][] bArr = new byte[i];
        Iterator it2 = list.iterator();
        int i2 = 0;
        while (it2.hasNext()) {
            ExperimentTokens experimentTokens2 = (ExperimentTokens) it2.next();
            if (experimentTokens2 != null && (extract = crossExtractor.extract(experimentTokens2)) != null) {
                for (byte[] bArr2 : extract) {
                    if (bArr2 != null) {
                        bArr[i2] = bArr2;
                        i2++;
                    }
                }
            }
        }
        return bArr;
    }

    private static List sortGenericDimensions(int[] iArr) {
        if (iArr == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(iArr.length >> 1);
        for (int i = 0; i < iArr.length; i += 2) {
            arrayList.add(new GenericDimension(iArr[i], iArr[i + 1]));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ExperimentTokens) {
            ExperimentTokens experimentTokens = (ExperimentTokens) obj;
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.user, experimentTokens.user) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(allDirectTokens(), experimentTokens.allDirectTokens()) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(asSet(this.gaiaCrossExperimentTokens), asSet(experimentTokens.gaiaCrossExperimentTokens)) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(asSet(this.pseudonymousCrossExperimentTokens), asSet(experimentTokens.pseudonymousCrossExperimentTokens)) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(asSet(this.alwaysCrossExperimentTokens), asSet(experimentTokens.alwaysCrossExperimentTokens)) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(asSet(this.otherCrossExperimentTokens), asSet(experimentTokens.otherCrossExperimentTokens)) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(asSet(this.weakExperimentIds), asSet(experimentTokens.weakExperimentIds)) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(sortGenericDimensions(this.genericDimensions), sortGenericDimensions(experimentTokens.genericDimensions)) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(asSet(this.externalExperimentTokens), asSet(experimentTokens.externalExperimentTokens))) {
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ExperimentTokens");
        sb.append("(");
        String str = this.user;
        sb.append(str == null ? "null" : _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "'", "'"));
        sb.append(", direct==");
        byte[] bArr = this.directExperimentToken;
        if (bArr == null) {
            sb.append("null");
        } else {
            sb.append("'");
            sb.append(Base64.encodeToString(bArr, 3));
            sb.append("'");
        }
        sb.append(", ");
        toString(sb, "GAIA=", this.gaiaCrossExperimentTokens);
        sb.append(", ");
        toString(sb, "PSEUDO=", this.pseudonymousCrossExperimentTokens);
        sb.append(", ");
        toString(sb, "ALWAYS=", this.alwaysCrossExperimentTokens);
        sb.append(", ");
        toString(sb, "OTHER=", this.otherCrossExperimentTokens);
        sb.append(", weak=");
        sb.append(Arrays.toString(this.weakExperimentIds));
        sb.append(", ");
        toString(sb, "directs=", this.additionalDirectExperimentTokens);
        sb.append(", genDims=");
        sb.append(Arrays.toString(sortGenericDimensions(this.genericDimensions).toArray()));
        sb.append(", ");
        toString(sb, "external=", this.externalExperimentTokens);
        sb.append(")");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, this.user);
        StrictModeUtils$VmPolicyBuilderCompatS.writeByteArray$ar$ds(parcel, 3, this.directExperimentToken);
        StrictModeUtils$VmPolicyBuilderCompatS.writeByteArrayArray$ar$ds(parcel, 4, this.gaiaCrossExperimentTokens);
        StrictModeUtils$VmPolicyBuilderCompatS.writeByteArrayArray$ar$ds(parcel, 5, this.pseudonymousCrossExperimentTokens);
        StrictModeUtils$VmPolicyBuilderCompatS.writeByteArrayArray$ar$ds(parcel, 6, this.alwaysCrossExperimentTokens);
        StrictModeUtils$VmPolicyBuilderCompatS.writeByteArrayArray$ar$ds(parcel, 7, this.otherCrossExperimentTokens);
        StrictModeUtils$VmPolicyBuilderCompatS.writeIntArray$ar$ds(parcel, 8, this.weakExperimentIds);
        StrictModeUtils$VmPolicyBuilderCompatS.writeByteArrayArray$ar$ds(parcel, 9, this.additionalDirectExperimentTokens);
        StrictModeUtils$VmPolicyBuilderCompatS.writeIntArray$ar$ds(parcel, 10, this.genericDimensions);
        StrictModeUtils$VmPolicyBuilderCompatS.writeByteArrayArray$ar$ds(parcel, 11, this.externalExperimentTokens);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    private static Set asSet(byte[][] bArr) {
        int length;
        if (bArr != null && (length = bArr.length) != 0) {
            HashSet newHashSetWithExpectedSize = ContextDataProvider.newHashSetWithExpectedSize(length);
            for (byte[] bArr2 : bArr) {
                StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(bArr2);
                newHashSetWithExpectedSize.add(Base64.encodeToString(bArr2, 3));
            }
            return newHashSetWithExpectedSize;
        }
        return Collections.emptySet();
    }

    private static void toString(StringBuilder sb, String str, byte[][] bArr) {
        sb.append(str);
        sb.append("=");
        if (bArr == null) {
            sb.append("null");
            return;
        }
        sb.append("(");
        boolean z = true;
        int i = 0;
        while (i < bArr.length) {
            byte[] bArr2 = bArr[i];
            if (!z) {
                sb.append(", ");
            }
            sb.append("'");
            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(bArr2);
            sb.append(Base64.encodeToString(bArr2, 3));
            sb.append("'");
            i++;
            z = false;
        }
        sb.append(")");
    }
}
