package com.google.android.gms.googlehelp;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ErrorReport;
import com.google.android.gms.feedback.FeedbackOptions;
import com.google.android.gms.feedback.ThemeSettings;
import com.google.android.gms.googlehelp.internal.common.OverflowMenuItem;
import com.google.android.gms.googlehelp.internal.common.TogglingData;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleHelp extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<GoogleHelp> CREATOR = new GoogleHelpCreator();
    boolean accountPickerEnabled;
    String apiDebugOption;
    String appContext;
    String appPackageNameOverride;

    @Deprecated
    Bitmap backupScreenshot;
    String base64GseSessionOptions;
    String callingPackageName;
    public int clientVersion;
    Bitmap customCallingAppIcon;
    String customCallingAppLabel;
    public PendingIntent customFeedbackPendingIntent;
    boolean dynamicColorEnabled;
    public Uri fallbackSupportUri;
    ErrorReport feedbackErrorReport;

    @Deprecated
    Bundle feedbackPsdBundle;
    List frdPsds;
    Account googleAccount;
    boolean hasFeedbackPsd;
    boolean hasHelpPsd;

    @Deprecated
    String languageTag;
    boolean metricsReportingEnabled;
    ND4CSettings nd4cSettings;
    List offlineSuggestions;
    public int openingMode;
    List overflowMenuItems;
    int pipInitPos;
    Bundle psdBundle;

    @Deprecated
    byte[] screenshotBytes;

    @Deprecated
    int screenshotHeight;

    @Deprecated
    int screenshotWidth;
    boolean searchEnabled;
    boolean sendFeedbackDisabled;
    String sessionId;
    boolean showContactCardFirst;
    List supportPhoneNumbers;
    int syncHelpPsdTimeoutMs;
    public ThemeSettings themeSettings;
    public TogglingData togglingData;
    public int trailsConsent;
    public List trailsInteractions;
    final int versionCode;

    public GoogleHelp(int i, String str, Account account, Bundle bundle, String str2, String str3, Bitmap bitmap, boolean z, boolean z2, List list, Bundle bundle2, Bitmap bitmap2, byte[] bArr, int i2, int i3, String str4, Uri uri, List list2, int i4, ThemeSettings themeSettings, List list3, boolean z3, ErrorReport errorReport, TogglingData togglingData, int i5, PendingIntent pendingIntent, int i6, boolean z4, boolean z5, int i7, String str5, boolean z6, String str6, boolean z7, ND4CSettings nD4CSettings, boolean z8, List list4, String str7, int i8, int i9, List list5, String str8) {
        this.feedbackErrorReport = new ErrorReport();
        if (!TextUtils.isEmpty(str)) {
            this.versionCode = i;
            this.clientVersion = i6;
            this.hasHelpPsd = z4;
            this.hasFeedbackPsd = z5;
            this.syncHelpPsdTimeoutMs = i7;
            this.sessionId = str5;
            this.appContext = str;
            this.googleAccount = account;
            this.psdBundle = bundle;
            this.callingPackageName = str2;
            this.customCallingAppLabel = str3;
            this.customCallingAppIcon = bitmap;
            this.searchEnabled = z;
            this.metricsReportingEnabled = z2;
            this.accountPickerEnabled = z6;
            this.supportPhoneNumbers = list;
            this.customFeedbackPendingIntent = pendingIntent;
            this.feedbackPsdBundle = bundle2;
            this.backupScreenshot = bitmap2;
            this.screenshotBytes = bArr;
            this.screenshotWidth = i2;
            this.screenshotHeight = i3;
            this.apiDebugOption = str4;
            this.fallbackSupportUri = uri;
            this.overflowMenuItems = list2;
            if (i < 4) {
                ThemeSettings themeSettings2 = new ThemeSettings();
                themeSettings2.themeId = i4;
                this.themeSettings = themeSettings2;
            } else {
                this.themeSettings = themeSettings == null ? new ThemeSettings() : themeSettings;
            }
            this.offlineSuggestions = list3;
            this.showContactCardFirst = z3;
            this.feedbackErrorReport = errorReport;
            if (errorReport != null) {
                errorReport.launcher = "GoogleHelp";
            }
            this.togglingData = togglingData;
            this.pipInitPos = i5;
            this.appPackageNameOverride = str6;
            this.sendFeedbackDisabled = z7;
            this.nd4cSettings = nD4CSettings;
            this.dynamicColorEnabled = z8;
            this.frdPsds = list4;
            this.languageTag = str7;
            this.openingMode = i8;
            this.trailsConsent = i9;
            this.trailsInteractions = list5;
            this.base64GseSessionOptions = str8;
            return;
        }
        throw new IllegalStateException("Help requires a non-empty appContext");
    }

    public final void addAdditionalOverflowMenuItem$ar$ds(String str, Intent intent) {
        this.overflowMenuItems.add(new OverflowMenuItem(0, str, intent));
    }

    public final Intent buildHelpIntent() {
        return new Intent("com.google.android.gms.googlehelp.HELP").setPackage("com.google.android.gms").putExtra("EXTRA_GOOGLE_HELP", this);
    }

    public final void enableSearch$ar$ds() {
        this.searchEnabled = true;
    }

    public final void setFeedbackOptions$ar$ds(FeedbackOptions feedbackOptions, File file) {
        ErrorReport errorReport = new ErrorReport(feedbackOptions, file);
        this.feedbackErrorReport = errorReport;
        errorReport.launcher = "GoogleHelp";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1, this.versionCode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, this.appContext);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 3, this.googleAccount, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBundle$ar$ds(parcel, 4, this.psdBundle);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 5, this.searchEnabled);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 6, this.metricsReportingEnabled);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringList$ar$ds(parcel, 7, this.supportPhoneNumbers);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBundle$ar$ds(parcel, 10, this.feedbackPsdBundle);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 11, this.backupScreenshot, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 14, this.apiDebugOption);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 15, this.fallbackSupportUri, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedList$ar$ds(parcel, 16, this.overflowMenuItems);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 17, 0);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedList$ar$ds(parcel, 18, this.offlineSuggestions);
        StrictModeUtils$VmPolicyBuilderCompatS.writeByteArray$ar$ds(parcel, 19, this.screenshotBytes);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 20, this.screenshotWidth);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 21, this.screenshotHeight);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 22, this.showContactCardFirst);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 23, this.feedbackErrorReport, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 25, this.themeSettings, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 28, this.callingPackageName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 31, this.togglingData, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 32, this.pipInitPos);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 33, this.customFeedbackPendingIntent, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 34, this.customCallingAppLabel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 35, this.customCallingAppIcon, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 36, this.clientVersion);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 37, this.hasHelpPsd);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 38, this.hasFeedbackPsd);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 39, this.syncHelpPsdTimeoutMs);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 40, this.sessionId);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 41, this.accountPickerEnabled);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 42, this.appPackageNameOverride);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 43, this.sendFeedbackDisabled);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 44, this.nd4cSettings, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 45, this.dynamicColorEnabled);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedList$ar$ds(parcel, 46, this.frdPsds);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 47, this.languageTag);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 48, this.openingMode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 49, this.trailsConsent);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedList$ar$ds(parcel, 50, this.trailsInteractions);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 51, this.base64GseSessionOptions);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    @Deprecated
    public GoogleHelp() {
        this(23, "android_default", null, null, null, null, null, true, true, new ArrayList(), null, null, null, 0, 0, null, null, new ArrayList(), 3, null, new ArrayList(), false, new ErrorReport(), null, 0, null, -1, false, false, 200, null, false, null, false, null, false, new ArrayList(), null, 0, 0, new ArrayList(), null);
    }
}
