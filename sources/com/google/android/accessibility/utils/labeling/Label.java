package com.google.android.accessibility.utils.labeling;

import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Label {
    public long mId;
    public String mLocale;
    public String mPackageName;
    public String mPackageSignature;
    public int mPackageVersion;
    public String mScreenshotPath;
    public String mText;
    public long mTimestampMillis;
    public String mViewName;

    public Label(long j, String str, String str2, String str3, String str4, String str5, int i, String str6, long j2) {
        this.mId = j;
        this.mPackageName = str;
        this.mPackageSignature = str2;
        this.mViewName = str3;
        this.mText = str4;
        setLocale(str5);
        this.mPackageVersion = i;
        this.mScreenshotPath = str6;
        this.mTimestampMillis = j2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Label)) {
            return false;
        }
        Label label = (Label) obj;
        String str = this.mLocale;
        if (str == null) {
            if (label.mLocale != null) {
                return false;
            }
        } else if (!str.equals(label.mLocale)) {
            return false;
        }
        String str2 = this.mPackageName;
        if (str2 == null) {
            if (label.mPackageName != null) {
                return false;
            }
        } else if (!str2.equals(label.mPackageName)) {
            return false;
        }
        String str3 = this.mPackageSignature;
        if (str3 == null) {
            if (label.mPackageSignature != null) {
                return false;
            }
        } else if (!str3.equals(label.mPackageSignature)) {
            return false;
        }
        String str4 = this.mScreenshotPath;
        if (str4 == null) {
            if (label.mScreenshotPath != null) {
                return false;
            }
        } else if (!str4.equals(label.mScreenshotPath)) {
            return false;
        }
        String str5 = this.mText;
        if (str5 == null) {
            if (label.mText != null) {
                return false;
            }
        } else if (!str5.equals(label.mText)) {
            return false;
        }
        if (this.mTimestampMillis != label.mTimestampMillis || this.mPackageVersion != label.mPackageVersion) {
            return false;
        }
        String str6 = this.mViewName;
        if (str6 == null) {
            if (label.mViewName != null) {
                return false;
            }
        } else if (!str6.equals(label.mViewName)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int hashCode;
        int hashCode2;
        int hashCode3;
        int hashCode4;
        int hashCode5;
        String str = this.mLocale;
        int i = 0;
        if (str == null) {
            hashCode = 0;
        } else {
            hashCode = str.hashCode();
        }
        String str2 = this.mPackageName;
        if (str2 == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = str2.hashCode();
        }
        int i2 = hashCode + 31;
        String str3 = this.mPackageSignature;
        if (str3 == null) {
            hashCode3 = 0;
        } else {
            hashCode3 = str3.hashCode();
        }
        int i3 = ((((i2 * 31) + hashCode2) * 31) + hashCode3) * 31;
        String str4 = this.mScreenshotPath;
        if (str4 == null) {
            hashCode4 = 0;
        } else {
            hashCode4 = str4.hashCode();
        }
        int i4 = (i3 + hashCode4) * 31;
        String str5 = this.mText;
        if (str5 == null) {
            hashCode5 = 0;
        } else {
            hashCode5 = str5.hashCode();
        }
        int i5 = (i4 + hashCode5) * 31;
        long j = this.mTimestampMillis;
        int i6 = (((i5 + ((int) (j ^ (j >>> 32)))) * 31) + this.mPackageVersion) * 31;
        String str6 = this.mViewName;
        if (str6 != null) {
            i = str6.hashCode();
        }
        return i6 + i;
    }

    public final void setLocale(String str) {
        this.mLocale = SpannableUtils$IdentifierSpan.getLanguageLocale(str);
    }

    public final String toString() {
        return String.format("%s[id=%d, packageName=%s, packageSignature=%s, viewName=%s, text=%s, locale=%s, packageVersion=%d, screenshotPath=%s, timestamp=%d]", getClass().getSimpleName(), Long.valueOf(this.mId), this.mPackageName, this.mPackageSignature, this.mViewName, this.mText, this.mLocale, Integer.valueOf(this.mPackageVersion), this.mScreenshotPath, Long.valueOf(this.mTimestampMillis));
    }

    public Label(Label label, long j) {
        if (label.mId == -1) {
            this.mId = j;
            this.mPackageName = label.mPackageName;
            this.mPackageSignature = label.mPackageSignature;
            this.mViewName = label.mViewName;
            this.mText = label.mText;
            setLocale(label.mLocale);
            this.mPackageVersion = label.mPackageVersion;
            this.mScreenshotPath = label.mScreenshotPath;
            this.mTimestampMillis = label.mTimestampMillis;
            return;
        }
        throw new IllegalArgumentException("Label to copy cannot have an ID already assigned.");
    }

    public Label(String str, String str2, String str3, String str4, String str5, int i, String str6, long j) {
        this(-1L, str, str2, str3, str4, str5, i, str6, j);
    }
}
