package com.google.android.gms.phenotype;

import _COROUTINE._BOUNDARY;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.AdditionalConsentConfigCreator;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Flag extends AbstractSafeParcelable implements Comparable<Flag> {
    public static final Parcelable.Creator<Flag> CREATOR = new AdditionalConsentConfigCreator(16);
    public final boolean booleanValue;
    public final byte[] bytesValue;
    public final double doubleValue;
    public final int flagStorageType;
    public final int flagValueType;
    public final long longValue;
    public final String name;
    public final String stringValue;

    public Flag(String str, long j, boolean z, double d, String str2, byte[] bArr, int i, int i2) {
        this.name = str;
        this.longValue = j;
        this.booleanValue = z;
        this.doubleValue = d;
        this.stringValue = str2;
        this.bytesValue = bArr;
        this.flagValueType = i;
        this.flagStorageType = i2;
    }

    private static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        if (i != i2) {
            return 1;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void appendToString(StringBuilder sb) {
        sb.append("Flag(");
        sb.append(this.name);
        sb.append(", ");
        int i = this.flagValueType;
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i == 5) {
                            sb.append("'");
                            byte[] bArr = this.bytesValue;
                            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(bArr);
                            sb.append(Base64.encodeToString(bArr, 3));
                            sb.append("'");
                        } else {
                            throw new AssertionError("Invalid type: " + this.name + ", " + i);
                        }
                    } else {
                        sb.append("'");
                        String str = this.stringValue;
                        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(str);
                        sb.append(str);
                        sb.append("'");
                    }
                } else {
                    sb.append(this.doubleValue);
                }
            } else {
                sb.append(this.booleanValue);
            }
        } else {
            sb.append(this.longValue);
        }
        sb.append(", ");
        sb.append(this.flagValueType);
        sb.append(", ");
        sb.append(this.flagStorageType);
        sb.append(")");
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x008f, code lost:
    
        if (r0 != false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x009c, code lost:
    
        if (r4 == r6) goto L45;
     */
    @Override // java.lang.Comparable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* bridge */ /* synthetic */ int compareTo(com.google.android.gms.phenotype.Flag r9) {
        /*
            r8 = this;
            com.google.android.gms.phenotype.Flag r9 = (com.google.android.gms.phenotype.Flag) r9
            java.lang.String r0 = r9.name
            java.lang.String r1 = r8.name
            int r0 = r1.compareTo(r0)
            if (r0 == 0) goto Le
            goto La0
        Le:
            int r0 = r8.flagValueType
            int r1 = r9.flagValueType
            int r1 = compare(r0, r1)
            if (r1 == 0) goto L1b
        L18:
            r0 = r1
            goto La0
        L1b:
            r1 = -1
            r2 = 0
            r3 = 1
            if (r0 == r3) goto L92
            r4 = 2
            if (r0 == r4) goto L87
            r4 = 3
            if (r0 == r4) goto L7e
            r4 = 4
            if (r0 == r4) goto L6c
            r4 = 5
            if (r0 != r4) goto L60
            byte[] r0 = r8.bytesValue
            byte[] r4 = r9.bytesValue
            if (r0 != r4) goto L33
            goto L72
        L33:
            if (r0 != 0) goto L36
            goto L18
        L36:
            if (r4 != 0) goto L3a
            goto L9f
        L3a:
            byte[] r0 = r8.bytesValue
            int r0 = r0.length
            byte[] r1 = r9.bytesValue
            int r1 = r1.length
            int r0 = java.lang.Math.min(r0, r1)
            if (r2 >= r0) goto L55
            byte[] r0 = r8.bytesValue
            r0 = r0[r2]
            byte[] r1 = r9.bytesValue
            r1 = r1[r2]
            int r0 = r0 - r1
            if (r0 == 0) goto L52
            goto La0
        L52:
            int r2 = r2 + 1
            goto L3a
        L55:
            byte[] r0 = r8.bytesValue
            int r0 = r0.length
            byte[] r9 = r9.bytesValue
            int r9 = r9.length
            int r0 = compare(r0, r9)
            goto La0
        L60:
            java.lang.AssertionError r9 = new java.lang.AssertionError
            java.lang.String r1 = "Invalid enum value: "
            java.lang.String r0 = _COROUTINE._BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(r0, r1)
            r9.<init>(r0)
            throw r9
        L6c:
            java.lang.String r0 = r8.stringValue
            java.lang.String r9 = r9.stringValue
            if (r0 != r9) goto L73
        L72:
            goto L8d
        L73:
            if (r0 != 0) goto L76
            goto L18
        L76:
            if (r9 != 0) goto L79
            goto L91
        L79:
            int r0 = r0.compareTo(r9)
            goto La0
        L7e:
            double r0 = r8.doubleValue
            double r2 = r9.doubleValue
            int r0 = java.lang.Double.compare(r0, r2)
            goto La0
        L87:
            boolean r0 = r8.booleanValue
            boolean r9 = r9.booleanValue
            if (r0 != r9) goto L8f
        L8d:
            r0 = r2
            goto La0
        L8f:
            if (r0 == 0) goto L18
        L91:
            goto L9f
        L92:
            long r4 = r8.longValue
            long r6 = r9.longValue
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 >= 0) goto L9c
            goto L18
        L9c:
            if (r9 != 0) goto L9f
            goto L72
        L9f:
            r0 = r3
        La0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.phenotype.Flag.compareTo(java.lang.Object):int");
    }

    public final boolean equals(Object obj) {
        int i;
        if (obj instanceof Flag) {
            Flag flag = (Flag) obj;
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.name, flag.name) && (i = this.flagValueType) == flag.flagValueType && this.flagStorageType == flag.flagStorageType) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i == 5) {
                                    return Arrays.equals(this.bytesValue, flag.bytesValue);
                                }
                                throw new AssertionError(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Invalid enum value: "));
                            }
                            return _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.stringValue, flag.stringValue);
                        }
                        if (this.doubleValue != flag.doubleValue) {
                            return false;
                        }
                        return true;
                    }
                    if (this.booleanValue != flag.booleanValue) {
                        return false;
                    }
                    return true;
                }
                if (this.longValue == flag.longValue) {
                    return true;
                }
            }
        }
        return false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        appendToString(sb);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        String str = this.name;
        if (!AdditionalConsentConfigCreator.isDefault(str)) {
            StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, str);
        }
        long j = this.longValue;
        if (j != 0) {
            StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 3, j);
        }
        if (this.booleanValue) {
            StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 4, true);
        }
        double d = this.doubleValue;
        if (d != 0.0d) {
            StrictModeUtils$VmPolicyBuilderCompatS.writeDouble(parcel, 5, d);
        }
        String str2 = this.stringValue;
        if (!AdditionalConsentConfigCreator.isDefault(str2)) {
            StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 6, str2);
        }
        byte[] bArr = this.bytesValue;
        if (!AdditionalConsentConfigCreator.isDefault(bArr)) {
            StrictModeUtils$VmPolicyBuilderCompatS.writeByteArray$ar$ds(parcel, 7, bArr);
        }
        int i2 = this.flagValueType;
        if (!AdditionalConsentConfigCreator.isDefault(i2)) {
            StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 8, i2);
        }
        int i3 = this.flagStorageType;
        if (!AdditionalConsentConfigCreator.isDefault(i3)) {
            StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 9, i3);
        }
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
