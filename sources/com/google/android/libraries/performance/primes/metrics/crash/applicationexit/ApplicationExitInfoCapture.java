package com.google.android.libraries.performance.primes.metrics.crash.applicationexit;

import java.util.List;
import logs.proto.wireless.performance.mobile.ApplicationExitInfo;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public interface ApplicationExitInfoCapture {
    List<ApplicationExitInfo> getApplicationExits(int i, int i2, String str, long j);
}
