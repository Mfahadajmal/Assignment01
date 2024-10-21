package com.google.android.libraries.performance.primes.metrics.crash.applicationexit;

import android.os.Binder;
import com.google.android.libraries.phenotype.client.FlagLoader;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ApplicationExitMetricService {
    public ApplicationExitMetricService() {
    }

    public static Object executeBinderAware(FlagLoader.BinderAwareFunction binderAwareFunction) {
        try {
            return binderAwareFunction.execute();
        } catch (SecurityException unused) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                return binderAwareFunction.execute();
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
    }

    public ApplicationExitMetricService(byte[] bArr) {
    }

    public ApplicationExitMetricService(byte[] bArr, byte[] bArr2) {
        this((byte[]) null);
    }

    public ApplicationExitMetricService(char[] cArr) {
    }
}
