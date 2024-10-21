package com.google.android.gms.clearcut.internal;

import _COROUTINE._BOUNDARY;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.widget.MainSwitchBar;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PlayLoggerContext extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PlayLoggerContext> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(20);
    public final Integer appMobilespecId;
    public final boolean isAnonymous;
    public final boolean logAndroidId;
    public final int logSource;
    public final String logSourceName;
    public final String packageName;
    public final int packageVersionCode;
    public final int piiLevelSet;
    public final int qosTier;
    public final boolean scrubMccMnc;
    public final String uploadAccountName;

    public PlayLoggerContext(String str, int i, int i2, String str2, boolean z, String str3, boolean z2, int i3, Integer num, boolean z3, int i4) {
        this.packageName = str;
        this.packageVersionCode = i;
        this.logSource = i2;
        this.uploadAccountName = str2;
        this.logAndroidId = z;
        this.logSourceName = str3;
        this.isAnonymous = z2;
        this.qosTier = i3;
        this.appMobilespecId = num;
        this.scrubMccMnc = z3;
        this.piiLevelSet = i4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PlayLoggerContext) {
            PlayLoggerContext playLoggerContext = (PlayLoggerContext) obj;
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.packageName, playLoggerContext.packageName) && this.packageVersionCode == playLoggerContext.packageVersionCode && this.logSource == playLoggerContext.logSource && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.logSourceName, playLoggerContext.logSourceName) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.uploadAccountName, playLoggerContext.uploadAccountName) && this.logAndroidId == playLoggerContext.logAndroidId && this.isAnonymous == playLoggerContext.isAnonymous && this.qosTier == playLoggerContext.qosTier && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.appMobilespecId, playLoggerContext.appMobilespecId) && this.scrubMccMnc == playLoggerContext.scrubMccMnc && this.piiLevelSet == playLoggerContext.piiLevelSet) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.packageName, Integer.valueOf(this.packageVersionCode), Integer.valueOf(this.logSource), this.logSourceName, this.uploadAccountName, Boolean.valueOf(this.logAndroidId), Boolean.valueOf(this.isAnonymous), Integer.valueOf(this.qosTier), this.appMobilespecId, Boolean.valueOf(this.scrubMccMnc), Integer.valueOf(this.piiLevelSet)});
    }

    public final String toString() {
        return "PlayLoggerContext[package=" + this.packageName + ",packageVersionCode=" + this.packageVersionCode + ",logSource=" + this.logSource + ",logSourceName=" + this.logSourceName + ",uploadAccount=" + this.uploadAccountName + ",logAndroidId=" + this.logAndroidId + ",isAnonymous=" + this.isAnonymous + ",qosTier=" + this.qosTier + ",appMobilespecId=" + this.appMobilespecId + ",scrubMccMnc=" + this.scrubMccMnc + "piiLevelset=" + this.piiLevelSet + "]";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, this.packageName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, this.packageVersionCode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 4, this.logSource);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 5, this.uploadAccountName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 7, this.logAndroidId);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 8, this.logSourceName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 9, this.isAnonymous);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 10, this.qosTier);
        Integer num = this.appMobilespecId;
        if (num != null) {
            StrictModeUtils$VmPolicyBuilderCompatS.writeHeader(parcel, 11, 4);
            parcel.writeInt(num.intValue());
        }
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 12, this.scrubMccMnc);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 13, this.piiLevelSet);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public PlayLoggerContext(java.lang.String r15, int r16, java.lang.String r17, java.lang.String r18, int r19, java.util.EnumSet r20) {
        /*
            r14 = this;
            r0 = r20
            com.google.android.gms.clearcut.PIILevel r1 = com.google.android.gms.clearcut.PIILevel.ANDROID_ID
            boolean r7 = r0.contains(r1)
            java.util.EnumSet r1 = com.google.android.gms.clearcut.PIILevel.deidentified
            boolean r9 = r0.equals(r1)
            if (r19 == 0) goto L44
            java.util.EnumSet r1 = com.google.android.gms.clearcut.PIILevel.noRestrictions
            boolean r1 = r0.equals(r1)
            r2 = -1
            if (r1 == 0) goto L1c
            r0 = 0
            r13 = r0
            goto L33
        L1c:
            java.util.Iterator r0 = r20.iterator()
            r1 = r2
        L21:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L32
            java.lang.Object r3 = r0.next()
            com.google.android.gms.clearcut.PIILevel r3 = (com.google.android.gms.clearcut.PIILevel) r3
            int r3 = r3.id
            int r3 = ~r3
            r1 = r1 & r3
            goto L21
        L32:
            r13 = r1
        L33:
            int r10 = r19 + (-1)
            r11 = 0
            r12 = 0
            r5 = -1
            r2 = r14
            r3 = r15
            r4 = r16
            r6 = r18
            r8 = r17
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return
        L44:
            r0 = 0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.clearcut.internal.PlayLoggerContext.<init>(java.lang.String, int, java.lang.String, java.lang.String, int, java.util.EnumSet):void");
    }
}
