package com.google.android.libraries.performance.primes.metrics.crash.applicationexit;

import android.content.Context;
import java.util.List;
import javax.inject.Provider;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApplicationExitInfoCaptureImpl implements ApplicationExitInfoCapture {
    private final Context context;
    private final Provider enableCollectingAnrDiagnostics;

    public ApplicationExitInfoCaptureImpl(Context context, Provider<Boolean> provider) {
        this.context = context;
        this.enableCollectingAnrDiagnostics = provider;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01fc A[Catch: all -> 0x020a, LOOP:1: B:53:0x0196->B:63:0x01fc, LOOP_END, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x020a, blocks: (B:47:0x0180, B:49:0x0186, B:52:0x018e, B:53:0x0196, B:55:0x019b, B:57:0x01a5, B:65:0x01b1, B:63:0x01fc, B:69:0x01ab), top: B:46:0x0180, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01b1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01ab A[Catch: all -> 0x020a, TryCatch #2 {all -> 0x020a, blocks: (B:47:0x0180, B:49:0x0186, B:52:0x018e, B:53:0x0196, B:55:0x019b, B:57:0x01a5, B:65:0x01b1, B:63:0x01fc, B:69:0x01ab), top: B:46:0x0180, outer: #1 }] */
    @Override // com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitInfoCapture
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.google.common.collect.ImmutableList<logs.proto.wireless.performance.mobile.ApplicationExitInfo> getApplicationExits(int r16, int r17, java.lang.String r18, long r19) {
        /*
            Method dump skipped, instructions count: 620
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitInfoCaptureImpl.getApplicationExits(int, int, java.lang.String, long):com.google.common.collect.ImmutableList");
    }

    @Override // com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitInfoCapture
    public /* bridge */ /* synthetic */ List getApplicationExits(int i, int i2, String str, long j) {
        return getApplicationExits(0, 0, str, j);
    }
}
