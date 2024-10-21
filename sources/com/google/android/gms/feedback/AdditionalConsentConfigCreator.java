package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.googlehelp.FRDProductSpecificDataEntry;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.InProductHelp;
import com.google.android.gms.googlehelp.ND4CSettings;
import com.google.android.gms.googlehelp.OfflineSuggestion;
import com.google.android.gms.googlehelp.internal.common.OverflowMenuItem;
import com.google.android.gms.googlehelp.internal.common.TogglingData;
import com.google.android.gms.googlehelp.trails.TrailsInteraction;
import com.google.android.gms.phenotype.Configuration;
import com.google.android.gms.phenotype.Configurations;
import com.google.android.gms.phenotype.DogfoodsToken;
import com.google.android.gms.phenotype.ExperimentTokens;
import com.google.android.gms.phenotype.Flag;
import com.google.android.gms.phenotype.FlagOverride;
import com.google.android.gms.phenotype.FlagOverrides;
import com.google.android.gms.phenotype.GenericDimension;
import com.google.android.gms.pseudonymous.PseudonymousIdToken;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AdditionalConsentConfigCreator implements Parcelable.Creator {
    private final /* synthetic */ int switching_field;

    public AdditionalConsentConfigCreator(int i) {
        this.switching_field = i;
    }

    public static final ErrorReport createFromParcel$ar$ds$d4e79b08_0(Parcel parcel) {
        int validateObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
        ApplicationErrorReport applicationErrorReport = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        String[] strArr = null;
        String[] strArr2 = null;
        String[] strArr3 = null;
        String str14 = null;
        String str15 = null;
        byte[] bArr = null;
        String str16 = null;
        String str17 = null;
        String str18 = null;
        Bundle bundle = null;
        String str19 = null;
        String str20 = null;
        String str21 = null;
        String str22 = null;
        String str23 = null;
        String str24 = null;
        String str25 = null;
        String str26 = null;
        String str27 = null;
        BitmapTeleporter bitmapTeleporter = null;
        String str28 = null;
        FileTeleporter[] fileTeleporterArr = null;
        String[] strArr4 = null;
        String str29 = null;
        ThemeSettings themeSettings = null;
        LogOptions logOptions = null;
        String str30 = null;
        Bundle bundle2 = null;
        ArrayList arrayList = null;
        Bitmap bitmap = null;
        String str31 = null;
        ArrayList arrayList2 = null;
        String[] strArr5 = null;
        String[] strArr6 = null;
        String[] strArr7 = null;
        String str32 = null;
        AdditionalConsentConfig additionalConsentConfig = null;
        ServiceDump[] serviceDumpArr = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        boolean z = false;
        int i7 = 0;
        int i8 = 0;
        boolean z2 = false;
        int i9 = 0;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        int i10 = 0;
        int i11 = 0;
        boolean z6 = false;
        boolean z7 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = StrictModeUtils$VmPolicyBuilderCompatS.readHeader(parcel);
            switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readHeader)) {
                case 2:
                    applicationErrorReport = (ApplicationErrorReport) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readHeader, ApplicationErrorReport.CREATOR);
                    break;
                case 3:
                    str = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 4:
                    i = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readHeader);
                    break;
                case 5:
                    str2 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 6:
                    str3 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 7:
                    str4 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 8:
                    str5 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 9:
                    str6 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 10:
                    str7 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 11:
                    str8 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 12:
                    i2 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readHeader);
                    break;
                case 13:
                    str9 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 14:
                    str10 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 15:
                    str11 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 16:
                    str12 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 17:
                    str13 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 18:
                    strArr = StrictModeUtils$VmPolicyBuilderCompatS.createStringArray(parcel, readHeader);
                    break;
                case 19:
                    strArr2 = StrictModeUtils$VmPolicyBuilderCompatS.createStringArray(parcel, readHeader);
                    break;
                case 20:
                    strArr3 = StrictModeUtils$VmPolicyBuilderCompatS.createStringArray(parcel, readHeader);
                    break;
                case 21:
                    str14 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 22:
                    str15 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 23:
                    bArr = StrictModeUtils$VmPolicyBuilderCompatS.createByteArray(parcel, readHeader);
                    break;
                case 24:
                    i3 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readHeader);
                    break;
                case 25:
                    i4 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readHeader);
                    break;
                case 26:
                    i5 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readHeader);
                    break;
                case 27:
                    i6 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readHeader);
                    break;
                case 28:
                    str16 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 29:
                    str17 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 30:
                    str18 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 31:
                    bundle = StrictModeUtils$VmPolicyBuilderCompatS.createBundle(parcel, readHeader);
                    break;
                case 32:
                    z = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readHeader);
                    break;
                case 33:
                    i7 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readHeader);
                    break;
                case 34:
                    i8 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readHeader);
                    break;
                case 35:
                    z2 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readHeader);
                    break;
                case 36:
                    str19 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 37:
                    str20 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 38:
                    i9 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readHeader);
                    break;
                case 39:
                    str21 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 40:
                    str22 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 41:
                    str23 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 42:
                    str24 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 43:
                    str25 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 44:
                    str26 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 45:
                    str27 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 46:
                    bitmapTeleporter = (BitmapTeleporter) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readHeader, BitmapTeleporter.CREATOR);
                    break;
                case 47:
                    str28 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 48:
                    fileTeleporterArr = (FileTeleporter[]) StrictModeUtils$VmPolicyBuilderCompatS.createTypedArray(parcel, readHeader, FileTeleporter.CREATOR);
                    break;
                case 49:
                    strArr4 = StrictModeUtils$VmPolicyBuilderCompatS.createStringArray(parcel, readHeader);
                    break;
                case 50:
                    z3 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readHeader);
                    break;
                case 51:
                    str29 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 52:
                    themeSettings = (ThemeSettings) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readHeader, ThemeSettings.CREATOR);
                    break;
                case 53:
                    logOptions = (LogOptions) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readHeader, LogOptions.CREATOR);
                    break;
                case 54:
                    str30 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 55:
                    z4 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readHeader);
                    break;
                case 56:
                    bundle2 = StrictModeUtils$VmPolicyBuilderCompatS.createBundle(parcel, readHeader);
                    break;
                case 57:
                    arrayList = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readHeader, RectF.CREATOR);
                    break;
                case 58:
                    z5 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readHeader);
                    break;
                case 59:
                    bitmap = (Bitmap) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readHeader, Bitmap.CREATOR);
                    break;
                case 60:
                    str31 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 61:
                    arrayList2 = StrictModeUtils$VmPolicyBuilderCompatS.createStringList(parcel, readHeader);
                    break;
                case 62:
                    i10 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readHeader);
                    break;
                case 63:
                    i11 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readHeader);
                    break;
                case 64:
                    strArr5 = StrictModeUtils$VmPolicyBuilderCompatS.createStringArray(parcel, readHeader);
                    break;
                case 65:
                    strArr6 = StrictModeUtils$VmPolicyBuilderCompatS.createStringArray(parcel, readHeader);
                    break;
                case 66:
                    strArr7 = StrictModeUtils$VmPolicyBuilderCompatS.createStringArray(parcel, readHeader);
                    break;
                case 67:
                    z6 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readHeader);
                    break;
                case 68:
                    z7 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readHeader);
                    break;
                case 69:
                    str32 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readHeader);
                    break;
                case 70:
                    additionalConsentConfig = (AdditionalConsentConfig) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readHeader, AdditionalConsentConfig.CREATOR);
                    break;
                case 71:
                    serviceDumpArr = (ServiceDump[]) StrictModeUtils$VmPolicyBuilderCompatS.createTypedArray(parcel, readHeader, ServiceDump.CREATOR);
                    break;
                default:
                    StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader);
        return new ErrorReport(applicationErrorReport, str, i, str2, str3, str4, str5, str6, str7, str8, i2, str9, str10, str11, str12, str13, strArr, strArr2, strArr3, str14, str15, bArr, i3, i4, i5, i6, str16, str17, str18, bundle, z, i7, i8, z2, str19, str20, i9, str21, str22, str23, str24, str25, str26, str27, bitmapTeleporter, str28, fileTeleporterArr, strArr4, z3, str29, themeSettings, logOptions, str30, z4, bundle2, arrayList, z5, bitmap, str31, arrayList2, i10, i11, strArr5, strArr6, strArr7, z6, z7, str32, additionalConsentConfig, serviceDumpArr);
    }

    public static boolean isDefault(int i) {
        return i == 0;
    }

    public static void writeToParcel(InProductHelp inProductHelp, Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 1, inProductHelp.googleHelp, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, inProductHelp.searchQuery);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 3, inProductHelp.contentUrl);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 4, inProductHelp.openingMode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 5, inProductHelp.symptom);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 6, inProductHelp.channel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 7, inProductHelp.base64StartingFlow);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z;
        long j = 0;
        int i = 0;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        int i4 = 0;
        boolean z3 = false;
        ParcelFileDescriptor parcelFileDescriptor = null;
        String str = null;
        ArrayList arrayList = null;
        String str2 = null;
        byte[] bArr = null;
        Flag[] flagArr = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        switch (this.switching_field) {
            case 0:
                int validateObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                String str8 = null;
                String str9 = null;
                String str10 = null;
                String str11 = null;
                String str12 = null;
                Bundle bundle = null;
                while (parcel.dataPosition() < validateObjectHeader) {
                    int readInt = parcel.readInt();
                    switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt)) {
                        case 2:
                            str8 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                            break;
                        case 3:
                            str9 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                            break;
                        case 4:
                            str10 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                            break;
                        case 5:
                            str11 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                            break;
                        case 6:
                            str12 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                            break;
                        case 7:
                            bundle = StrictModeUtils$VmPolicyBuilderCompatS.createBundle(parcel, readInt);
                            break;
                        default:
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt);
                            break;
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader);
                return new AdditionalConsentConfig(str8, str9, str10, str11, str12, bundle);
            case 1:
                return createFromParcel$ar$ds$d4e79b08_0(parcel);
            case 2:
                int validateObjectHeader2 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                String str13 = null;
                String str14 = null;
                while (parcel.dataPosition() < validateObjectHeader2) {
                    int readInt2 = parcel.readInt();
                    int fieldId = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt2);
                    if (fieldId != 2) {
                        if (fieldId != 3) {
                            if (fieldId != 4) {
                                StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt2);
                            } else {
                                str14 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt2);
                            }
                        } else {
                            str13 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt2);
                        }
                    } else {
                        parcelFileDescriptor = (ParcelFileDescriptor) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt2, ParcelFileDescriptor.CREATOR);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader2);
                return new FileTeleporter(parcelFileDescriptor, str13, str14);
            case 3:
                int validateObjectHeader3 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                boolean z4 = false;
                boolean z5 = false;
                boolean z6 = false;
                boolean z7 = false;
                String str15 = null;
                ServiceDumpRequest[] serviceDumpRequestArr = null;
                while (parcel.dataPosition() < validateObjectHeader3) {
                    int readInt3 = parcel.readInt();
                    switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt3)) {
                        case 2:
                            str15 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt3);
                            break;
                        case 3:
                            z4 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt3);
                            break;
                        case 4:
                            z5 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt3);
                            break;
                        case 5:
                            z6 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt3);
                            break;
                        case 6:
                            z7 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt3);
                            break;
                        case 7:
                            serviceDumpRequestArr = (ServiceDumpRequest[]) StrictModeUtils$VmPolicyBuilderCompatS.createTypedArray(parcel, readInt3, ServiceDumpRequest.CREATOR);
                            break;
                        default:
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt3);
                            break;
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader3);
                return new LogOptions(str15, z4, z5, z6, z7, serviceDumpRequestArr);
            case 4:
                int validateObjectHeader4 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                int i5 = 0;
                while (parcel.dataPosition() < validateObjectHeader4) {
                    int readInt4 = parcel.readInt();
                    int fieldId2 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt4);
                    if (fieldId2 != 2) {
                        if (fieldId2 != 3) {
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt4);
                        } else {
                            i5 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt4);
                        }
                    } else {
                        i = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt4);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader4);
                return new ThemeSettings(i, i5);
            case 5:
                int validateObjectHeader5 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                int i6 = 0;
                int i7 = 0;
                Boolean bool = null;
                ArrayList arrayList2 = null;
                ArrayList arrayList3 = null;
                ArrayList arrayList4 = null;
                ArrayList arrayList5 = null;
                byte[][] bArr2 = null;
                while (parcel.dataPosition() < validateObjectHeader5) {
                    int readInt5 = parcel.readInt();
                    switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt5)) {
                        case 2:
                            i6 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt5);
                            break;
                        case 3:
                            i7 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt5);
                            break;
                        case 4:
                            arrayList2 = StrictModeUtils$VmPolicyBuilderCompatS.createStringList(parcel, readInt5);
                            break;
                        case 5:
                            arrayList3 = StrictModeUtils$VmPolicyBuilderCompatS.createLongList(parcel, readInt5);
                            break;
                        case 6:
                            arrayList4 = StrictModeUtils$VmPolicyBuilderCompatS.createStringList(parcel, readInt5);
                            break;
                        case 7:
                            arrayList5 = StrictModeUtils$VmPolicyBuilderCompatS.createLongList(parcel, readInt5);
                            break;
                        case 8:
                            bArr2 = StrictModeUtils$VmPolicyBuilderCompatS.createByteArrayArray(parcel, readInt5);
                            break;
                        case 9:
                            int readSize = StrictModeUtils$VmPolicyBuilderCompatS.readSize(parcel, readInt5);
                            if (readSize == 0) {
                                bool = null;
                                break;
                            } else {
                                StrictModeUtils$VmPolicyBuilderCompatS.enforceSize$ar$ds(parcel, readSize, 4);
                                if (parcel.readInt() != 0) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                bool = Boolean.valueOf(z);
                                break;
                            }
                        default:
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt5);
                            break;
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader5);
                return new FRDProductSpecificDataEntry(i6, i7, arrayList2, arrayList3, arrayList4, arrayList5, bArr2, bool.booleanValue());
            case 6:
                int validateObjectHeader6 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                int i8 = 0;
                int i9 = 0;
                GoogleHelp googleHelp = null;
                String str16 = null;
                String str17 = null;
                String str18 = null;
                String str19 = null;
                while (parcel.dataPosition() < validateObjectHeader6) {
                    int readInt6 = parcel.readInt();
                    switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt6)) {
                        case 1:
                            googleHelp = (GoogleHelp) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt6, GoogleHelp.CREATOR);
                            break;
                        case 2:
                            str16 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt6);
                            break;
                        case 3:
                            str17 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt6);
                            break;
                        case 4:
                            i8 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt6);
                            break;
                        case 5:
                            str18 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt6);
                            break;
                        case 6:
                            i9 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt6);
                            break;
                        case 7:
                            str19 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt6);
                            break;
                        default:
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt6);
                            break;
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader6);
                return new InProductHelp(googleHelp, str16, str17, i8, str18, i9, str19);
            case 7:
                int validateObjectHeader7 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                while (parcel.dataPosition() < validateObjectHeader7) {
                    int readInt7 = parcel.readInt();
                    int fieldId3 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt7);
                    if (fieldId3 != 2) {
                        if (fieldId3 != 3) {
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt7);
                        } else {
                            str7 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt7);
                        }
                    } else {
                        z3 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt7);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader7);
                return new ND4CSettings(z3, str7);
            case 8:
                int validateObjectHeader8 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                String str20 = null;
                String str21 = null;
                String str22 = null;
                while (parcel.dataPosition() < validateObjectHeader8) {
                    int readInt8 = parcel.readInt();
                    int fieldId4 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt8);
                    if (fieldId4 != 2) {
                        if (fieldId4 != 3) {
                            if (fieldId4 != 4) {
                                if (fieldId4 != 5) {
                                    StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt8);
                                } else {
                                    str21 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt8);
                                }
                            } else {
                                str22 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt8);
                            }
                        } else {
                            str20 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt8);
                        }
                    } else {
                        str6 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt8);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader8);
                return new OfflineSuggestion(str6, str20, str21, str22);
            case 9:
                int validateObjectHeader9 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                Intent intent = null;
                while (parcel.dataPosition() < validateObjectHeader9) {
                    int readInt9 = parcel.readInt();
                    int fieldId5 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt9);
                    if (fieldId5 != 2) {
                        if (fieldId5 != 3) {
                            if (fieldId5 != 4) {
                                StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt9);
                            } else {
                                intent = (Intent) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt9, Intent.CREATOR);
                            }
                        } else {
                            str5 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt9);
                        }
                    } else {
                        i4 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt9);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader9);
                return new OverflowMenuItem(i4, str5, intent);
            case 10:
                int validateObjectHeader10 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                String str23 = null;
                String str24 = null;
                while (parcel.dataPosition() < validateObjectHeader10) {
                    int readInt10 = parcel.readInt();
                    int fieldId6 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt10);
                    if (fieldId6 != 2) {
                        if (fieldId6 != 3) {
                            if (fieldId6 != 4) {
                                StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt10);
                            } else {
                                str24 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt10);
                            }
                        } else {
                            str23 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt10);
                        }
                    } else {
                        str4 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt10);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader10);
                return new TogglingData(str4, str23, str24);
            case 11:
                int validateObjectHeader11 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                String str25 = "";
                while (parcel.dataPosition() < validateObjectHeader11) {
                    int readInt11 = parcel.readInt();
                    int fieldId7 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt11);
                    if (fieldId7 != 1) {
                        if (fieldId7 != 2) {
                            if (fieldId7 != 3) {
                                StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt11);
                            } else {
                                str25 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt11);
                            }
                        } else {
                            j = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt11);
                        }
                    } else {
                        str3 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt11);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader11);
                return new TrailsInteraction(str3, j, str25);
            case 12:
                int validateObjectHeader12 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                String[] strArr = null;
                while (parcel.dataPosition() < validateObjectHeader12) {
                    int readInt12 = parcel.readInt();
                    int fieldId8 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt12);
                    if (fieldId8 != 2) {
                        if (fieldId8 != 3) {
                            if (fieldId8 != 4) {
                                StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt12);
                            } else {
                                strArr = StrictModeUtils$VmPolicyBuilderCompatS.createStringArray(parcel, readInt12);
                            }
                        } else {
                            flagArr = (Flag[]) StrictModeUtils$VmPolicyBuilderCompatS.createTypedArray(parcel, readInt12, Flag.CREATOR);
                        }
                    } else {
                        i3 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt12);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader12);
                return new Configuration(i3, flagArr, strArr);
            case 13:
                int validateObjectHeader13 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                long j2 = 0;
                boolean z8 = false;
                String str26 = null;
                String str27 = null;
                Configuration[] configurationArr = null;
                byte[] bArr3 = null;
                while (parcel.dataPosition() < validateObjectHeader13) {
                    int readInt13 = parcel.readInt();
                    switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt13)) {
                        case 2:
                            str26 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt13);
                            break;
                        case 3:
                            str27 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt13);
                            break;
                        case 4:
                            configurationArr = (Configuration[]) StrictModeUtils$VmPolicyBuilderCompatS.createTypedArray(parcel, readInt13, Configuration.CREATOR);
                            break;
                        case 5:
                            z8 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt13);
                            break;
                        case 6:
                            bArr3 = StrictModeUtils$VmPolicyBuilderCompatS.createByteArray(parcel, readInt13);
                            break;
                        case 7:
                            j2 = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt13);
                            break;
                        default:
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt13);
                            break;
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader13);
                return new Configurations(str26, str27, configurationArr, z8, bArr3, j2);
            case 14:
                int validateObjectHeader14 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                while (parcel.dataPosition() < validateObjectHeader14) {
                    int readInt14 = parcel.readInt();
                    if (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt14) != 2) {
                        StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt14);
                    } else {
                        bArr = StrictModeUtils$VmPolicyBuilderCompatS.createByteArray(parcel, readInt14);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader14);
                return new DogfoodsToken(bArr);
            case 15:
                int validateObjectHeader15 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                String str28 = null;
                byte[] bArr4 = null;
                byte[][] bArr5 = null;
                byte[][] bArr6 = null;
                byte[][] bArr7 = null;
                byte[][] bArr8 = null;
                int[] iArr = null;
                byte[][] bArr9 = null;
                int[] iArr2 = null;
                byte[][] bArr10 = null;
                while (parcel.dataPosition() < validateObjectHeader15) {
                    int readInt15 = parcel.readInt();
                    switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt15)) {
                        case 2:
                            str28 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt15);
                            break;
                        case 3:
                            bArr4 = StrictModeUtils$VmPolicyBuilderCompatS.createByteArray(parcel, readInt15);
                            break;
                        case 4:
                            bArr5 = StrictModeUtils$VmPolicyBuilderCompatS.createByteArrayArray(parcel, readInt15);
                            break;
                        case 5:
                            bArr6 = StrictModeUtils$VmPolicyBuilderCompatS.createByteArrayArray(parcel, readInt15);
                            break;
                        case 6:
                            bArr7 = StrictModeUtils$VmPolicyBuilderCompatS.createByteArrayArray(parcel, readInt15);
                            break;
                        case 7:
                            bArr8 = StrictModeUtils$VmPolicyBuilderCompatS.createByteArrayArray(parcel, readInt15);
                            break;
                        case 8:
                            iArr = StrictModeUtils$VmPolicyBuilderCompatS.createIntArray(parcel, readInt15);
                            break;
                        case 9:
                            bArr9 = StrictModeUtils$VmPolicyBuilderCompatS.createByteArrayArray(parcel, readInt15);
                            break;
                        case 10:
                            iArr2 = StrictModeUtils$VmPolicyBuilderCompatS.createIntArray(parcel, readInt15);
                            break;
                        case 11:
                            bArr10 = StrictModeUtils$VmPolicyBuilderCompatS.createByteArrayArray(parcel, readInt15);
                            break;
                        default:
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt15);
                            break;
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader15);
                return new ExperimentTokens(str28, bArr4, bArr5, bArr6, bArr7, bArr8, iArr, bArr9, iArr2, bArr10);
            case 16:
                int validateObjectHeader16 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                double d = 0.0d;
                long j3 = 0;
                boolean z9 = false;
                int i10 = 0;
                int i11 = 0;
                String str29 = null;
                String str30 = null;
                byte[] bArr11 = null;
                while (parcel.dataPosition() < validateObjectHeader16) {
                    int readInt16 = parcel.readInt();
                    switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt16)) {
                        case 2:
                            str29 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt16);
                            break;
                        case 3:
                            j3 = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt16);
                            break;
                        case 4:
                            z9 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt16);
                            break;
                        case 5:
                            d = StrictModeUtils$VmPolicyBuilderCompatS.readDouble(parcel, readInt16);
                            break;
                        case 6:
                            str30 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt16);
                            break;
                        case 7:
                            bArr11 = StrictModeUtils$VmPolicyBuilderCompatS.createByteArray(parcel, readInt16);
                            break;
                        case 8:
                            i10 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt16);
                            break;
                        case 9:
                            i11 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt16);
                            break;
                        default:
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt16);
                            break;
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader16);
                return new Flag(str29, j3, z9, d, str30, bArr11, i10, i11);
            case 17:
                int validateObjectHeader17 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                String str31 = null;
                Flag flag = null;
                while (parcel.dataPosition() < validateObjectHeader17) {
                    int readInt17 = parcel.readInt();
                    int fieldId9 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt17);
                    if (fieldId9 != 2) {
                        if (fieldId9 != 3) {
                            if (fieldId9 != 4) {
                                if (fieldId9 != 5) {
                                    StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt17);
                                } else {
                                    z2 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt17);
                                }
                            } else {
                                flag = (Flag) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt17, Flag.CREATOR);
                            }
                        } else {
                            str31 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt17);
                        }
                    } else {
                        str2 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt17);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader17);
                return new FlagOverride(str2, str31, flag, z2);
            case 18:
                int validateObjectHeader18 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                while (parcel.dataPosition() < validateObjectHeader18) {
                    int readInt18 = parcel.readInt();
                    if (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt18) != 2) {
                        StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt18);
                    } else {
                        arrayList = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt18, FlagOverride.CREATOR);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader18);
                return new FlagOverrides(arrayList);
            case 19:
                int validateObjectHeader19 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                int i12 = 0;
                while (parcel.dataPosition() < validateObjectHeader19) {
                    int readInt19 = parcel.readInt();
                    int fieldId10 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt19);
                    if (fieldId10 != 1) {
                        if (fieldId10 != 2) {
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt19);
                        } else {
                            i12 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt19);
                        }
                    } else {
                        i2 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt19);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader19);
                return new GenericDimension(i2, i12);
            default:
                int validateObjectHeader20 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                while (parcel.dataPosition() < validateObjectHeader20) {
                    int readInt20 = parcel.readInt();
                    if (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt20) != 2) {
                        StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt20);
                    } else {
                        str = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt20);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader20);
                return new PseudonymousIdToken(str);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        switch (this.switching_field) {
            case 0:
                return new AdditionalConsentConfig[i];
            case 1:
                return new ErrorReport[i];
            case 2:
                return new FileTeleporter[i];
            case 3:
                return new LogOptions[i];
            case 4:
                return new ThemeSettings[i];
            case 5:
                return new FRDProductSpecificDataEntry[i];
            case 6:
                return new InProductHelp[i];
            case 7:
                return new ND4CSettings[i];
            case 8:
                return new OfflineSuggestion[i];
            case 9:
                return new OverflowMenuItem[i];
            case 10:
                return new TogglingData[i];
            case 11:
                return new TrailsInteraction[i];
            case 12:
                return new Configuration[i];
            case 13:
                return new Configurations[i];
            case 14:
                return new DogfoodsToken[i];
            case 15:
                return new ExperimentTokens[i];
            case 16:
                return new Flag[i];
            case 17:
                return new FlagOverride[i];
            case 18:
                return new FlagOverrides[i];
            case 19:
                return new GenericDimension[i];
            default:
                return new PseudonymousIdToken[i];
        }
    }

    public static boolean isDefault(Object obj) {
        return obj == null;
    }
}
