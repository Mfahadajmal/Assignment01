package com.google.android.gms.clearcut;

import _COROUTINE._BOUNDARY;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.widget.MainSwitchBar;
import com.google.android.gms.clearcut.internal.DataCollectionIdentifierParcelable;
import com.google.android.gms.clearcut.internal.LogVerifierResultParcelable;
import com.google.android.gms.clearcut.internal.PlayLoggerContext;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.phenotype.ExperimentTokens;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics$LogEvent;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LogEventParcelable extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LogEventParcelable> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(15);
    private static final String[] EMPTY_ARRAY = new String[0];
    public final boolean addPhenotypeExperimentTokens;
    public final DataCollectionIdentifierParcelable dataCollectionIdentifier;
    public final int eventCode;
    public final int[] experimentIds;
    public final byte[][] experimentTokens;
    public final ExperimentTokens[] experimentTokensParcelables;
    public final ClientAnalytics$LogEvent logEvent;
    public final byte[] logEventBytes;
    public LogVerifierResultParcelable logVerifierResult;
    public final String[] mendelPackages;
    private final String[] mendelPackagesToFilter;
    public final PlayLoggerContext playLoggerContext;
    public final int[] testCodes;

    public LogEventParcelable(PlayLoggerContext playLoggerContext, ClientAnalytics$LogEvent clientAnalytics$LogEvent, byte[] bArr, int[] iArr, String[] strArr, int[] iArr2, ExperimentTokens[] experimentTokensArr, boolean z, String[] strArr2, int i, DataCollectionIdentifierParcelable dataCollectionIdentifierParcelable) {
        this.playLoggerContext = playLoggerContext;
        this.logEvent = clientAnalytics$LogEvent;
        this.logEventBytes = bArr;
        this.testCodes = iArr;
        this.mendelPackages = strArr;
        this.experimentIds = iArr2;
        this.experimentTokens = null;
        this.experimentTokensParcelables = experimentTokensArr;
        this.addPhenotypeExperimentTokens = true;
        this.mendelPackagesToFilter = strArr2;
        this.eventCode = i;
        this.dataCollectionIdentifier = null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LogEventParcelable) {
            LogEventParcelable logEventParcelable = (LogEventParcelable) obj;
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.playLoggerContext, logEventParcelable.playLoggerContext) && Arrays.equals(this.logEventBytes, logEventParcelable.logEventBytes) && Arrays.equals(this.testCodes, logEventParcelable.testCodes) && Arrays.equals(this.mendelPackages, logEventParcelable.mendelPackages) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.logEvent, logEventParcelable.logEvent) && Arrays.equals(this.experimentIds, logEventParcelable.experimentIds) && Arrays.deepEquals(this.experimentTokens, logEventParcelable.experimentTokens) && Arrays.equals(this.experimentTokensParcelables, logEventParcelable.experimentTokensParcelables) && Arrays.equals(this.mendelPackagesToFilter, logEventParcelable.mendelPackagesToFilter) && this.addPhenotypeExperimentTokens == logEventParcelable.addPhenotypeExperimentTokens && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.logVerifierResult, logEventParcelable.logVerifierResult) && this.eventCode == logEventParcelable.eventCode && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.dataCollectionIdentifier, logEventParcelable.dataCollectionIdentifier)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.playLoggerContext, this.logEventBytes, this.testCodes, this.mendelPackages, this.logEvent, this.experimentIds, this.experimentTokens, this.experimentTokensParcelables, Boolean.valueOf(this.addPhenotypeExperimentTokens), this.mendelPackagesToFilter, this.logVerifierResult, Integer.valueOf(this.eventCode), this.dataCollectionIdentifier});
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("LogEventParcelable[");
        sb.append(this.playLoggerContext);
        sb.append(", LogEventBytes: ");
        byte[] bArr = this.logEventBytes;
        String str2 = null;
        if (bArr == null) {
            str = null;
        } else {
            str = new String(bArr, StandardCharsets.UTF_8);
        }
        sb.append(str);
        sb.append(", TestCodes: ");
        sb.append(Arrays.toString(this.testCodes));
        sb.append(", MendelPackages: ");
        sb.append(Arrays.toString(this.mendelPackages));
        sb.append(", LogEvent: ");
        sb.append(this.logEvent);
        sb.append(", , ExperimentIDs: ");
        sb.append(Arrays.toString(this.experimentIds));
        sb.append(", ExperimentTokens: ");
        sb.append(Arrays.deepToString(this.experimentTokens));
        sb.append(", ExperimentTokensParcelables: ");
        sb.append(Arrays.toString(this.experimentTokensParcelables));
        sb.append(", MendelPackagesToFilter: ");
        sb.append(Arrays.toString(this.mendelPackagesToFilter));
        sb.append("AddPhenotypeExperimentTokens: ");
        sb.append(this.addPhenotypeExperimentTokens);
        sb.append(", LogVerifierResult: ");
        LogVerifierResultParcelable logVerifierResultParcelable = this.logVerifierResult;
        if (logVerifierResultParcelable != null) {
            str2 = logVerifierResultParcelable.toString();
        }
        sb.append(str2);
        sb.append("EventCode: ");
        sb.append(this.eventCode);
        sb.append(", ");
        sb.append(this.dataCollectionIdentifier);
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 2, this.playLoggerContext, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeByteArray$ar$ds(parcel, 3, this.logEventBytes);
        StrictModeUtils$VmPolicyBuilderCompatS.writeIntArray$ar$ds(parcel, 4, this.testCodes);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringArray$ar$ds(parcel, 5, this.mendelPackages);
        StrictModeUtils$VmPolicyBuilderCompatS.writeIntArray$ar$ds(parcel, 6, this.experimentIds);
        StrictModeUtils$VmPolicyBuilderCompatS.writeByteArrayArray$ar$ds(parcel, 7, this.experimentTokens);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 8, this.addPhenotypeExperimentTokens);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedArray$ar$ds(parcel, 9, this.experimentTokensParcelables, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 11, this.logVerifierResult, i);
        String[] strArr = this.mendelPackagesToFilter;
        if (strArr == null) {
            strArr = EMPTY_ARRAY;
        }
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringArray$ar$ds(parcel, 12, strArr);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 13, this.eventCode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 14, this.dataCollectionIdentifier, i);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    public LogEventParcelable(PlayLoggerContext playLoggerContext, byte[] bArr, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr2, boolean z, ExperimentTokens[] experimentTokensArr, LogVerifierResultParcelable logVerifierResultParcelable, String[] strArr2, int i, DataCollectionIdentifierParcelable dataCollectionIdentifierParcelable) {
        this.playLoggerContext = playLoggerContext;
        this.logEventBytes = bArr;
        this.testCodes = iArr;
        this.mendelPackages = strArr;
        this.experimentIds = iArr2;
        this.experimentTokens = bArr2;
        this.addPhenotypeExperimentTokens = z;
        this.experimentTokensParcelables = experimentTokensArr;
        this.logVerifierResult = logVerifierResultParcelable;
        this.mendelPackagesToFilter = strArr2;
        this.eventCode = i;
        this.logEvent = null;
        this.dataCollectionIdentifier = dataCollectionIdentifierParcelable;
    }
}
