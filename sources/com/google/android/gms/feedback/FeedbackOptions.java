package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FeedbackOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<FeedbackOptions> CREATOR = new FeedbackOptionsCreator();
    String accountInUse;
    AdditionalConsentConfig additionalConsentConfig;
    ApplicationErrorReport applicationErrorReport;
    BitmapTeleporter bitmapTeleporter;
    String categoryTag;
    String configuratorTriggerId;
    String description;
    boolean enableDynamicColor;
    boolean excludePii;
    List fileTeleporters;
    boolean isSilentSend;
    LogOptions logOptions;
    String packageName;
    Bundle psdBundle;
    boolean psdHasNoPii;
    Bitmap screenshot;
    String sessionId;
    long startTickNanos;
    ThemeSettings themeSettings;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private String categoryTag;
        private boolean excludePii;
        private final String sessionId;
        public ThemeSettings themeSettings;
        private final Bundle psdBundle = new Bundle();
        private final List fileTeleporters = new ArrayList();

        @Deprecated
        public Builder() {
            new ApplicationErrorReport();
            this.sessionId = System.currentTimeMillis() + "-" + Math.abs(new SecureRandom().nextLong());
        }

        public final FeedbackOptions build() {
            FeedbackOptions feedbackOptions = new FeedbackOptions(null, null, null, new ApplicationErrorReport(), null, null, null, null, true, null, null, false, null, null, false, 0L, false, null, null);
            feedbackOptions.screenshot = null;
            feedbackOptions.bitmapTeleporter = null;
            feedbackOptions.accountInUse = null;
            feedbackOptions.description = null;
            feedbackOptions.psdBundle = this.psdBundle;
            feedbackOptions.categoryTag = this.categoryTag;
            feedbackOptions.fileTeleporters = this.fileTeleporters;
            feedbackOptions.excludePii = this.excludePii;
            feedbackOptions.themeSettings = this.themeSettings;
            feedbackOptions.logOptions = null;
            feedbackOptions.psdHasNoPii = false;
            feedbackOptions.sessionId = this.sessionId;
            feedbackOptions.isSilentSend = false;
            feedbackOptions.startTickNanos = 0L;
            feedbackOptions.enableDynamicColor = false;
            feedbackOptions.configuratorTriggerId = null;
            feedbackOptions.additionalConsentConfig = null;
            return feedbackOptions;
        }

        public final void setCategoryTag$ar$ds() {
            this.categoryTag = "com.google.android.marvin.talkback.USER_INITIATED_FEEDBACK_REPORT";
        }

        public final void setExcludePii$ar$ds() {
            this.excludePii = true;
        }
    }

    public FeedbackOptions(String str, Bundle bundle, String str2, ApplicationErrorReport applicationErrorReport, String str3, BitmapTeleporter bitmapTeleporter, String str4, List list, boolean z, ThemeSettings themeSettings, LogOptions logOptions, boolean z2, Bitmap bitmap, String str5, boolean z3, long j, boolean z4, String str6, AdditionalConsentConfig additionalConsentConfig) {
        this.accountInUse = str;
        this.psdBundle = bundle == null ? new Bundle() : bundle;
        this.description = str2;
        this.applicationErrorReport = applicationErrorReport == null ? new ApplicationErrorReport() : applicationErrorReport;
        this.categoryTag = str3;
        this.bitmapTeleporter = bitmapTeleporter;
        this.packageName = str4;
        this.fileTeleporters = list == null ? new ArrayList() : list;
        this.excludePii = z;
        this.themeSettings = themeSettings;
        this.logOptions = logOptions;
        this.psdHasNoPii = z2;
        this.screenshot = bitmap;
        this.sessionId = str5;
        this.isSilentSend = z3;
        this.startTickNanos = j;
        this.enableDynamicColor = z4;
        this.configuratorTriggerId = str6;
        this.additionalConsentConfig = additionalConsentConfig;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, this.accountInUse);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBundle$ar$ds(parcel, 3, this.psdBundle);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 5, this.description);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 6, this.applicationErrorReport, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 7, this.categoryTag);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 8, this.bitmapTeleporter, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 9, this.packageName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedList$ar$ds(parcel, 10, this.fileTeleporters);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 11, this.excludePii);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 12, this.themeSettings, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 13, this.logOptions, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 14, this.psdHasNoPii);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 15, this.screenshot, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 16, this.sessionId);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 17, this.isSilentSend);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 18, this.startTickNanos);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 19, this.enableDynamicColor);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 20, this.configuratorTriggerId);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 21, this.additionalConsentConfig, i);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
