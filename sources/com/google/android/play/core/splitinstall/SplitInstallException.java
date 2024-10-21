package com.google.android.play.core.splitinstall;

import com.google.android.gms.common.api.ApiException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SplitInstallException extends ApiException {
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public SplitInstallException(int r6) {
        /*
            r5 = this;
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status
            java.lang.Integer r1 = java.lang.Integer.valueOf(r6)
            java.util.Map r2 = com.google.android.play.core.splitinstall.model.SplitInstallErrorCodeHelper.summaryByError
            java.lang.Integer r3 = java.lang.Integer.valueOf(r6)
            boolean r2 = r2.containsKey(r3)
            if (r2 == 0) goto L45
            java.util.Map r2 = com.google.android.play.core.splitinstall.model.SplitInstallErrorCodeHelper.urlLabelByError
            boolean r2 = r2.containsKey(r3)
            if (r2 != 0) goto L1b
            goto L45
        L1b:
            java.util.Map r2 = com.google.android.play.core.splitinstall.model.SplitInstallErrorCodeHelper.summaryByError
            java.lang.Object r2 = r2.get(r3)
            java.lang.String r2 = (java.lang.String) r2
            java.util.Map r4 = com.google.android.play.core.splitinstall.model.SplitInstallErrorCodeHelper.urlLabelByError
            java.lang.Object r3 = r4.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r2)
            java.lang.String r2 = " (https://developer.android.com/reference/com/google/android/play/core/splitinstall/model/SplitInstallErrorCode.html#"
            r4.append(r2)
            r4.append(r3)
            java.lang.String r2 = ")"
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            goto L47
        L45:
            java.lang.String r2 = ""
        L47:
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = 0
            r3[r4] = r1
            r1 = 1
            r3[r1] = r2
            java.lang.String r1 = "Split Install Error(%d): %s"
            java.lang.String r1 = java.lang.String.format(r1, r3)
            r0.<init>(r6, r1)
            r5.<init>(r0)
            if (r6 == 0) goto L5f
            return
        L5f:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "errorCode should not be 0."
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.SplitInstallException.<init>(int):void");
    }

    public final int getErrorCode() {
        return super.getStatusCode();
    }
}
