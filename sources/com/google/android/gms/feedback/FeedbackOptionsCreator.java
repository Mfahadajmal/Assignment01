package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FeedbackOptionsCreator implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
        String str = null;
        Bundle bundle = null;
        String str2 = null;
        ApplicationErrorReport applicationErrorReport = null;
        String str3 = null;
        BitmapTeleporter bitmapTeleporter = null;
        String str4 = null;
        ArrayList arrayList = null;
        ThemeSettings themeSettings = null;
        LogOptions logOptions = null;
        Bitmap bitmap = null;
        String str5 = null;
        String str6 = null;
        AdditionalConsentConfig additionalConsentConfig = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        long j = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readInt = parcel.readInt();
            switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt)) {
                case 2:
                    str = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                    break;
                case 3:
                    bundle = StrictModeUtils$VmPolicyBuilderCompatS.createBundle(parcel, readInt);
                    break;
                case 4:
                default:
                    StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt);
                    break;
                case 5:
                    str2 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                    break;
                case 6:
                    applicationErrorReport = (ApplicationErrorReport) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt, ApplicationErrorReport.CREATOR);
                    break;
                case 7:
                    str3 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                    break;
                case 8:
                    bitmapTeleporter = (BitmapTeleporter) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt, BitmapTeleporter.CREATOR);
                    break;
                case 9:
                    str4 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                    break;
                case 10:
                    arrayList = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt, FileTeleporter.CREATOR);
                    break;
                case 11:
                    z = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt);
                    break;
                case 12:
                    themeSettings = (ThemeSettings) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt, ThemeSettings.CREATOR);
                    break;
                case 13:
                    logOptions = (LogOptions) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt, LogOptions.CREATOR);
                    break;
                case 14:
                    z2 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt);
                    break;
                case 15:
                    bitmap = (Bitmap) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt, Bitmap.CREATOR);
                    break;
                case 16:
                    str5 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                    break;
                case 17:
                    z3 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt);
                    break;
                case 18:
                    j = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt);
                    break;
                case 19:
                    z4 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt);
                    break;
                case 20:
                    str6 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                    break;
                case 21:
                    additionalConsentConfig = (AdditionalConsentConfig) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt, AdditionalConsentConfig.CREATOR);
                    break;
            }
        }
        StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader);
        return new FeedbackOptions(str, bundle, str2, applicationErrorReport, str3, bitmapTeleporter, str4, arrayList, z, themeSettings, logOptions, z2, bitmap, str5, z3, j, z4, str6, additionalConsentConfig);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new FeedbackOptions[i];
    }
}
