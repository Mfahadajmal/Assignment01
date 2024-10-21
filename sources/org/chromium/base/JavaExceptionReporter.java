package org.chromium.base;

import J.N;
import java.lang.Thread;
import org.chromium.base.JniAndroid;

/* compiled from: PG */
/* loaded from: classes.dex */
public class JavaExceptionReporter implements Thread.UncaughtExceptionHandler {
    private final boolean mCrashAfterReport;
    private boolean mHandlingException;
    private final Thread.UncaughtExceptionHandler mParent;

    private JavaExceptionReporter(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, boolean z) {
        this.mParent = uncaughtExceptionHandler;
        this.mCrashAfterReport = z;
    }

    private static void installHandler(boolean z) {
        Thread.setDefaultUncaughtExceptionHandler(new JavaExceptionReporter(Thread.getDefaultUncaughtExceptionHandler(), z));
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        Throwable th2;
        if (!this.mHandlingException && !BuildInfo$$ExternalSyntheticApiModelOutline1.m264m((Object) th)) {
            this.mHandlingException = true;
            boolean z = this.mCrashAfterReport;
            if (th instanceof JniAndroid.UncaughtExceptionException) {
                th2 = th.getCause();
            } else {
                th2 = th;
            }
            N.MLlibBXh(z, th2);
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.mParent;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}
