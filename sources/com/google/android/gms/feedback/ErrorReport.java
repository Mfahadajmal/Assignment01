package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ErrorReport extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<ErrorReport> CREATOR = new AdditionalConsentConfigCreator(1);
    public String account;
    public AdditionalConsentConfig additionalConsentConfig;
    public String anrStackTraces;
    public ApplicationErrorReport applicationErrorReport;
    public BitmapTeleporter bitmapTeleporter;
    public String board;
    public String brand;
    public String buildFingerprint;
    public String buildId;
    public String buildType;
    public String categoryTag;
    public Bundle classificationSignals;
    public String codename;

    @Deprecated
    public String color;
    public String configuratorTriggerId;
    public String[] contentCaptureServiceDump;
    public String description;
    public String device;
    public boolean enableDynamicColor;
    public String[] eventLog;
    public String exceptionClassName;
    public String exceptionMessage;
    public boolean excludePii;
    public boolean feedbackRedirectionEnabled;
    public FileTeleporter[] fileTeleporterList;
    public List highlightBounds;
    public String incremental;
    public boolean isCtlAllowed;
    public boolean isSilentSend;
    public String launcher;
    public String localeString;
    public LogOptions logOptions;
    public String[] mainFullLog;
    public String model;
    public int networkMcc;
    public int networkMnc;
    public String networkName;
    public int networkType;
    public int noExperimentTokensReason;
    public int packageVersion;
    public String packageVersionName;
    public List phenotypeExperimentTokens;
    public int phoneType;
    public String product;
    public Bundle psdBundle;
    public String[] psdFilePaths;
    public boolean psdHasNoPii;
    public String release;
    public int reportSource;
    public String[] runningApplications;
    public String screenshot;
    public Bitmap screenshotBitmap;
    public byte[] screenshotBytes;
    public int screenshotHeight;
    public String screenshotPath;
    public int screenshotWidth;
    public int sdkInt;
    public ServiceDump[] serviceDumps;
    public String sessionId;
    public String stackTrace;
    public String submittingPackageName;

    @Deprecated
    public String suggestionSessionId;
    public boolean suggestionShown;
    public String[] systemFullLog;
    public String[] systemLog;
    public ThemeSettings themeSettings;
    public String throwClassName;
    public String throwFileName;
    public int throwLineNumber;
    public String throwMethodName;

    public ErrorReport() {
        this.applicationErrorReport = new ApplicationErrorReport();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 2, this.applicationErrorReport, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 3, this.description);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 4, this.packageVersion);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 5, this.packageVersionName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 6, this.device);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 7, this.buildId);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 8, this.buildType);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 9, this.model);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 10, this.product);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 11, this.buildFingerprint);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 12, this.sdkInt);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 13, this.release);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 14, this.incremental);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 15, this.codename);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 16, this.board);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 17, this.brand);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringArray$ar$ds(parcel, 18, this.runningApplications);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringArray$ar$ds(parcel, 19, this.systemLog);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringArray$ar$ds(parcel, 20, this.eventLog);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 21, this.anrStackTraces);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 22, this.screenshot);
        StrictModeUtils$VmPolicyBuilderCompatS.writeByteArray$ar$ds(parcel, 23, this.screenshotBytes);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 24, this.screenshotHeight);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 25, this.screenshotWidth);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 26, this.phoneType);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 27, this.networkType);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 28, this.networkName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 29, this.account);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 30, this.localeString);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBundle$ar$ds(parcel, 31, this.psdBundle);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 32, this.isSilentSend);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 33, this.networkMcc);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 34, this.networkMnc);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 35, this.isCtlAllowed);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 36, this.exceptionClassName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 37, this.throwFileName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 38, this.throwLineNumber);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 39, this.throwClassName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 40, this.throwMethodName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 41, this.stackTrace);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 42, this.exceptionMessage);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 43, this.categoryTag);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 44, this.color);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 45, this.submittingPackageName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 46, this.bitmapTeleporter, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 47, this.screenshotPath);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedArray$ar$ds(parcel, 48, this.fileTeleporterList, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringArray$ar$ds(parcel, 49, this.psdFilePaths);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 50, this.excludePii);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 51, this.launcher);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 52, this.themeSettings, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 53, this.logOptions, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 54, this.suggestionSessionId);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 55, this.suggestionShown);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBundle$ar$ds(parcel, 56, this.classificationSignals);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedList$ar$ds(parcel, 57, this.highlightBounds);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 58, this.psdHasNoPii);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 59, this.screenshotBitmap, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 60, this.sessionId);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringList$ar$ds(parcel, 61, this.phenotypeExperimentTokens);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 62, this.noExperimentTokensReason);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 63, this.reportSource);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringArray$ar$ds(parcel, 64, this.systemFullLog);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringArray$ar$ds(parcel, 65, this.mainFullLog);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringArray$ar$ds(parcel, 66, this.contentCaptureServiceDump);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 67, this.enableDynamicColor);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 68, this.feedbackRedirectionEnabled);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 69, this.configuratorTriggerId);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 70, this.additionalConsentConfig, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedArray$ar$ds(parcel, 71, this.serviceDumps, i);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    public ErrorReport(ApplicationErrorReport applicationErrorReport, String str, int i, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, String str9, String str10, String str11, String str12, String str13, String[] strArr, String[] strArr2, String[] strArr3, String str14, String str15, byte[] bArr, int i3, int i4, int i5, int i6, String str16, String str17, String str18, Bundle bundle, boolean z, int i7, int i8, boolean z2, String str19, String str20, int i9, String str21, String str22, String str23, String str24, String str25, String str26, String str27, BitmapTeleporter bitmapTeleporter, String str28, FileTeleporter[] fileTeleporterArr, String[] strArr4, boolean z3, String str29, ThemeSettings themeSettings, LogOptions logOptions, String str30, boolean z4, Bundle bundle2, List list, boolean z5, Bitmap bitmap, String str31, List list2, int i10, int i11, String[] strArr5, String[] strArr6, String[] strArr7, boolean z6, boolean z7, String str32, AdditionalConsentConfig additionalConsentConfig, ServiceDump[] serviceDumpArr) {
        new ApplicationErrorReport();
        this.applicationErrorReport = applicationErrorReport;
        this.description = str;
        this.packageVersion = i;
        this.packageVersionName = str2;
        this.device = str3;
        this.buildId = str4;
        this.buildType = str5;
        this.model = str6;
        this.product = str7;
        this.buildFingerprint = str8;
        this.sdkInt = i2;
        this.release = str9;
        this.incremental = str10;
        this.codename = str11;
        this.board = str12;
        this.brand = str13;
        this.runningApplications = strArr;
        this.systemLog = strArr2;
        this.eventLog = strArr3;
        this.anrStackTraces = str14;
        this.screenshot = str15;
        this.screenshotBytes = bArr;
        this.screenshotHeight = i3;
        this.screenshotWidth = i4;
        this.phoneType = i5;
        this.networkType = i6;
        this.networkName = str16;
        this.account = str17;
        this.localeString = str18;
        this.psdBundle = bundle;
        this.isSilentSend = z;
        this.networkMcc = i7;
        this.networkMnc = i8;
        this.isCtlAllowed = z2;
        this.exceptionClassName = str19;
        this.throwFileName = str20;
        this.throwLineNumber = i9;
        this.throwClassName = str21;
        this.throwMethodName = str22;
        this.stackTrace = str23;
        this.exceptionMessage = str24;
        this.categoryTag = str25;
        this.color = str26;
        this.submittingPackageName = str27;
        this.bitmapTeleporter = bitmapTeleporter;
        this.screenshotPath = str28;
        this.fileTeleporterList = fileTeleporterArr;
        this.psdFilePaths = strArr4;
        this.excludePii = z3;
        this.launcher = str29;
        this.themeSettings = themeSettings;
        this.logOptions = logOptions;
        this.suggestionSessionId = str30;
        this.suggestionShown = z4;
        this.classificationSignals = bundle2;
        this.highlightBounds = list;
        this.psdHasNoPii = z5;
        this.screenshotBitmap = bitmap;
        this.sessionId = str31;
        this.phenotypeExperimentTokens = list2;
        this.noExperimentTokensReason = i10;
        this.reportSource = i11;
        this.systemFullLog = strArr5;
        this.mainFullLog = strArr6;
        this.contentCaptureServiceDump = strArr7;
        this.enableDynamicColor = z6;
        this.feedbackRedirectionEnabled = z7;
        this.configuratorTriggerId = str32;
        this.additionalConsentConfig = additionalConsentConfig;
        this.serviceDumps = serviceDumpArr;
    }

    public ErrorReport(FeedbackOptions feedbackOptions, File file) {
        this.applicationErrorReport = new ApplicationErrorReport();
        Bundle bundle = feedbackOptions.psdBundle;
        if (bundle != null && !bundle.isEmpty()) {
            this.psdBundle = feedbackOptions.psdBundle;
        }
        if (!TextUtils.isEmpty(feedbackOptions.accountInUse)) {
            this.account = feedbackOptions.accountInUse;
        }
        if (!TextUtils.isEmpty(feedbackOptions.description)) {
            this.description = feedbackOptions.description;
        }
        ApplicationErrorReport.CrashInfo crashInfo = feedbackOptions.applicationErrorReport.crashInfo;
        if (crashInfo != null) {
            this.throwMethodName = crashInfo.throwMethodName;
            this.throwLineNumber = crashInfo.throwLineNumber;
            this.throwClassName = crashInfo.throwClassName;
            this.stackTrace = crashInfo.stackTrace;
            this.exceptionClassName = crashInfo.exceptionClassName;
            this.exceptionMessage = crashInfo.exceptionMessage;
            this.throwFileName = crashInfo.throwFileName;
        }
        ThemeSettings themeSettings = feedbackOptions.themeSettings;
        if (themeSettings != null) {
            this.themeSettings = themeSettings;
        }
        if (!TextUtils.isEmpty(feedbackOptions.categoryTag)) {
            this.categoryTag = feedbackOptions.categoryTag;
        }
        String str = feedbackOptions.packageName;
        if (!TextUtils.isEmpty(str)) {
            this.applicationErrorReport.packageName = str;
        }
        if (!TextUtils.isEmpty(feedbackOptions.sessionId)) {
            this.sessionId = feedbackOptions.sessionId;
        }
        Bitmap bitmap = feedbackOptions.screenshot;
        if (bitmap != null) {
            this.screenshotBitmap = bitmap;
        }
        if (file != null) {
            BitmapTeleporter bitmapTeleporter = feedbackOptions.bitmapTeleporter;
            this.bitmapTeleporter = bitmapTeleporter;
            if (bitmapTeleporter != null) {
                bitmapTeleporter.tempDir = file;
            }
            List list = feedbackOptions.fileTeleporters;
            if (list != null && !list.isEmpty()) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ((FileTeleporter) it.next()).tempDir = file;
                }
                this.fileTeleporterList = (FileTeleporter[]) list.toArray(new FileTeleporter[0]);
            }
        }
        LogOptions logOptions = feedbackOptions.logOptions;
        if (logOptions != null) {
            this.logOptions = logOptions;
        }
        this.excludePii = feedbackOptions.excludePii;
        this.psdHasNoPii = feedbackOptions.psdHasNoPii;
        this.isSilentSend = feedbackOptions.isSilentSend;
        this.enableDynamicColor = feedbackOptions.enableDynamicColor;
        this.configuratorTriggerId = feedbackOptions.configuratorTriggerId;
        this.additionalConsentConfig = feedbackOptions.additionalConsentConfig;
    }
}
