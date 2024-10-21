package com.google.android.gms.googlehelp;

import android.accounts.Account;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ErrorReport;
import com.google.android.gms.feedback.ThemeSettings;
import com.google.android.gms.googlehelp.internal.common.OverflowMenuItem;
import com.google.android.gms.googlehelp.internal.common.TogglingData;
import com.google.android.gms.googlehelp.trails.TrailsInteraction;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleHelpCreator implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
        String str = null;
        Account account = null;
        Bundle bundle = null;
        String str2 = null;
        String str3 = null;
        Bitmap bitmap = null;
        ArrayList arrayList = null;
        Bundle bundle2 = null;
        Bitmap bitmap2 = null;
        byte[] bArr = null;
        String str4 = null;
        Uri uri = null;
        ArrayList arrayList2 = null;
        ThemeSettings themeSettings = null;
        ArrayList arrayList3 = null;
        ErrorReport errorReport = null;
        TogglingData togglingData = null;
        PendingIntent pendingIntent = null;
        String str5 = null;
        String str6 = null;
        ND4CSettings nD4CSettings = null;
        ArrayList arrayList4 = null;
        String str7 = null;
        ArrayList arrayList5 = null;
        String str8 = null;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        boolean z3 = false;
        int i5 = 0;
        int i6 = 0;
        boolean z4 = false;
        boolean z5 = false;
        int i7 = 0;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        int i8 = 0;
        int i9 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readInt = parcel.readInt();
            switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt)) {
                case 1:
                    i = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 2:
                    str = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                    break;
                case 3:
                    account = (Account) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt, Account.CREATOR);
                    break;
                case 4:
                    bundle = StrictModeUtils$VmPolicyBuilderCompatS.createBundle(parcel, readInt);
                    break;
                case 5:
                    z = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt);
                    break;
                case 6:
                    z2 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt);
                    break;
                case 7:
                    arrayList = StrictModeUtils$VmPolicyBuilderCompatS.createStringList(parcel, readInt);
                    break;
                case 8:
                case 9:
                case 12:
                case 13:
                case 24:
                case 26:
                case 27:
                case 29:
                case 30:
                default:
                    StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt);
                    break;
                case 10:
                    bundle2 = StrictModeUtils$VmPolicyBuilderCompatS.createBundle(parcel, readInt);
                    break;
                case 11:
                    bitmap2 = (Bitmap) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt, Bitmap.CREATOR);
                    break;
                case 14:
                    str4 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                    break;
                case 15:
                    uri = (Uri) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt, Uri.CREATOR);
                    break;
                case 16:
                    arrayList2 = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt, OverflowMenuItem.CREATOR);
                    break;
                case 17:
                    i4 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 18:
                    arrayList3 = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt, OfflineSuggestion.CREATOR);
                    break;
                case 19:
                    bArr = StrictModeUtils$VmPolicyBuilderCompatS.createByteArray(parcel, readInt);
                    break;
                case 20:
                    i2 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 21:
                    i3 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 22:
                    z3 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt);
                    break;
                case 23:
                    errorReport = (ErrorReport) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt, ErrorReport.CREATOR);
                    break;
                case 25:
                    themeSettings = (ThemeSettings) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt, ThemeSettings.CREATOR);
                    break;
                case 28:
                    str2 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                    break;
                case 31:
                    togglingData = (TogglingData) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt, TogglingData.CREATOR);
                    break;
                case 32:
                    i5 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 33:
                    pendingIntent = (PendingIntent) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt, PendingIntent.CREATOR);
                    break;
                case 34:
                    str3 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                    break;
                case 35:
                    bitmap = (Bitmap) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt, Bitmap.CREATOR);
                    break;
                case 36:
                    i6 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 37:
                    z4 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt);
                    break;
                case 38:
                    z5 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt);
                    break;
                case 39:
                    i7 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 40:
                    str5 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                    break;
                case 41:
                    z6 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt);
                    break;
                case 42:
                    str6 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                    break;
                case 43:
                    z7 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt);
                    break;
                case 44:
                    nD4CSettings = (ND4CSettings) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt, ND4CSettings.CREATOR);
                    break;
                case 45:
                    z8 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt);
                    break;
                case 46:
                    arrayList4 = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt, FRDProductSpecificDataEntry.CREATOR);
                    break;
                case 47:
                    str7 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                    break;
                case 48:
                    i8 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 49:
                    i9 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                    break;
                case 50:
                    arrayList5 = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt, TrailsInteraction.CREATOR);
                    break;
                case 51:
                    str8 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                    break;
            }
        }
        StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader);
        return new GoogleHelp(i, str, account, bundle, str2, str3, bitmap, z, z2, arrayList, bundle2, bitmap2, bArr, i2, i3, str4, uri, arrayList2, i4, themeSettings, arrayList3, z3, errorReport, togglingData, i5, pendingIntent, i6, z4, z5, i7, str5, z6, str6, z7, nD4CSettings, z8, arrayList4, str7, i8, i9, arrayList5, str8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new GoogleHelp[i];
    }
}
