package com.google.android.apps.aicore.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InferenceEventTraceResultParcelableCreator implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        long j6 = 0;
        long j7 = 0;
        long j8 = 0;
        long j9 = 0;
        long j10 = 0;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        boolean z = false;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        byte[] bArr = null;
        float f = 0.0f;
        double d = 0.0d;
        double d2 = 0.0d;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readInt = parcel.readInt();
            switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt)) {
                case 1:
                    i = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 2:
                    i2 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 3:
                    i3 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 4:
                    i4 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 5:
                    j = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt);
                    break;
                case 6:
                    i5 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 7:
                    i6 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 8:
                    i7 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 9:
                    j2 = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt);
                    break;
                case 10:
                    j3 = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt);
                    break;
                case 11:
                    j4 = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt);
                    break;
                case 12:
                    j5 = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt);
                    break;
                case 13:
                    j6 = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt);
                    break;
                case 14:
                    j7 = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt);
                    break;
                case 15:
                    i8 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 16:
                    i9 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 17:
                    i10 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 18:
                    i11 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 19:
                    z = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt);
                    break;
                case 20:
                    i12 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 21:
                    j8 = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt);
                    break;
                case 22:
                    d = StrictModeUtils$VmPolicyBuilderCompatS.readDouble(parcel, readInt);
                    break;
                case 23:
                    d2 = StrictModeUtils$VmPolicyBuilderCompatS.readDouble(parcel, readInt);
                    break;
                case 24:
                    i13 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 25:
                    f = StrictModeUtils$VmPolicyBuilderCompatS.readFloat(parcel, readInt);
                    break;
                case 26:
                    bArr = StrictModeUtils$VmPolicyBuilderCompatS.createByteArray(parcel, readInt);
                    break;
                case 27:
                    i14 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 28:
                    i15 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 29:
                    j9 = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt);
                    break;
                case 30:
                    j10 = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt);
                    break;
                default:
                    StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt);
                    break;
            }
        }
        StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader);
        return new InferenceEventTraceResult(i, i2, i3, i4, j, i5, i6, i7, j2, j3, j4, j5, j6, j7, i8, i9, i10, i11, z, i12, j8, d, d2, i13, f, bArr, i14, i15, j9, j10);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new InferenceEventTraceResult[i];
    }
}
