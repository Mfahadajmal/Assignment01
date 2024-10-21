package com.google.android.apps.aicore.client.api;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AiCoreException extends Exception {
    public final int errorCode;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AiCoreException(int r9, int r10, java.lang.String r11, java.lang.Throwable r12) {
        /*
            r8 = this;
            java.lang.Integer r0 = java.lang.Integer.valueOf(r9)
            r1 = 3
            r2 = 2
            r3 = 1
            if (r9 == r3) goto L16
            if (r9 == r2) goto L13
            if (r9 == r1) goto L10
            java.lang.String r9 = "CONNECTION_ERROR"
            goto L18
        L10:
            java.lang.String r9 = "PREPARATION_ERROR"
            goto L18
        L13:
            java.lang.String r9 = "INFERENCE_ERROR"
            goto L18
        L16:
            java.lang.String r9 = "DOWNLOAD_ERROR"
        L18:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r5 = 15
            if (r10 == r5) goto L61
            r5 = 16
            if (r10 == r5) goto L5e
            r5 = 501(0x1f5, float:7.02E-43)
            if (r10 == r5) goto L5b
            switch(r10) {
                case 2: goto L58;
                case 3: goto L55;
                case 4: goto L61;
                case 5: goto L52;
                case 6: goto L4f;
                case 7: goto L4c;
                case 8: goto L49;
                case 9: goto L46;
                case 10: goto L61;
                case 11: goto L43;
                case 12: goto L40;
                default: goto L2b;
            }
        L2b:
            switch(r10) {
                case 601: goto L3d;
                case 602: goto L3a;
                case 603: goto L37;
                case 604: goto L34;
                case 605: goto L31;
                default: goto L2e;
            }
        L2e:
            java.lang.String r5 = "UNKNOWN"
            goto L63
        L31:
            java.lang.String r5 = "NULL_BINDING"
            goto L63
        L34:
            java.lang.String r5 = "NEEDS_SYSTEM_UPDATE"
            goto L63
        L37:
            java.lang.String r5 = "BINDING_DIED"
            goto L63
        L3a:
            java.lang.String r5 = "SERVICE_DISCONNECTED"
            goto L63
        L3d:
            java.lang.String r5 = "BINDING_FAILURE"
            goto L63
        L40:
            java.lang.String r5 = "REQUEST_TOO_LARGE"
            goto L63
        L43:
            java.lang.String r5 = "RESPONSE_PROCESSING_ERROR"
            goto L63
        L46:
            java.lang.String r5 = "BUSY"
            goto L63
        L49:
            java.lang.String r5 = "NOT_AVAILABLE"
            goto L63
        L4c:
            java.lang.String r5 = "CANCELLED"
            goto L63
        L4f:
            java.lang.String r5 = "IPC_ERROR"
            goto L63
        L52:
            java.lang.String r5 = "COMPUTE_ERROR"
            goto L63
        L55:
            java.lang.String r5 = "BAD_REQUEST"
            goto L63
        L58:
            java.lang.String r5 = "BAD_DATA"
            goto L63
        L5b:
            java.lang.String r5 = "NOT_ENOUGH_DISK_SPACE"
            goto L63
        L5e:
            java.lang.String r5 = "NOT_SUPPORTED"
            goto L63
        L61:
            java.lang.String r5 = "REQUEST_PROCESSING_ERROR"
        L63:
            r6 = 5
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r7 = 0
            r6[r7] = r0
            r6[r3] = r9
            r6[r2] = r4
            r6[r1] = r5
            r9 = 4
            r6[r9] = r11
            java.lang.String r9 = "AICore failed with error type %s-%s and error code %s-%s: %s"
            java.lang.String r9 = java.lang.String.format(r9, r6)
            r8.<init>(r9, r12)
            r8.errorCode = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.aicore.client.api.AiCoreException.<init>(int, int, java.lang.String, java.lang.Throwable):void");
    }

    public static AiCoreException newConnectionError(int i, String str) {
        return new AiCoreException(4, i, str, null);
    }

    public static AiCoreException newDownloadError(int i, String str) {
        return new AiCoreException(1, i, str, null);
    }

    public static AiCoreException newPreparationError(int i, String str) {
        return new AiCoreException(3, i, str, null);
    }

    public static AiCoreException newDownloadError(String str) {
        return new AiCoreException(1, 0, str, null);
    }
}
