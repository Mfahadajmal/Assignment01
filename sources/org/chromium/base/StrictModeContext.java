package org.chromium.base;

import J.N;
import android.os.StrictMode;
import java.io.Closeable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StrictModeContext implements Closeable {
    private final StrictMode.ThreadPolicy mThreadPolicy;
    private final StrictMode.VmPolicy mVmPolicy;

    private StrictModeContext(StrictMode.ThreadPolicy threadPolicy) {
        this(threadPolicy, null);
    }

    public static StrictModeContext allowDiskReads() {
        TraceEvent scoped = TraceEvent.scoped("StrictModeContext.allowDiskReads");
        try {
            StrictModeContext strictModeContext = new StrictModeContext(StrictMode.allowThreadDiskReads());
            if (scoped != null) {
                scoped.close();
            }
            return strictModeContext;
        } catch (Throwable th) {
            if (scoped != null) {
                try {
                    scoped.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static StrictModeContext allowDiskWrites() {
        TraceEvent scoped = TraceEvent.scoped("StrictModeContext.allowDiskWrites");
        try {
            StrictModeContext strictModeContext = new StrictModeContext(StrictMode.allowThreadDiskWrites());
            if (scoped != null) {
                scoped.close();
            }
            return strictModeContext;
        } catch (Throwable th) {
            if (scoped != null) {
                try {
                    scoped.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        StrictMode.ThreadPolicy threadPolicy = this.mThreadPolicy;
        if (threadPolicy != null) {
            StrictMode.setThreadPolicy(threadPolicy);
        }
        StrictMode.VmPolicy vmPolicy = this.mVmPolicy;
        if (vmPolicy != null) {
            StrictMode.setVmPolicy(vmPolicy);
        }
        long hashCode = hashCode();
        if (EarlyTraceEvent.enabled()) {
            System.nanoTime();
            synchronized (EarlyTraceEvent.sLock) {
                if (EarlyTraceEvent.enabled()) {
                    throw null;
                }
            }
        }
        if (TraceEvent.sEnabled) {
            N.MffNhCLU(hashCode);
        }
    }

    public StrictModeContext(StrictMode.ThreadPolicy threadPolicy, StrictMode.VmPolicy vmPolicy) {
        long hashCode = hashCode();
        if (EarlyTraceEvent.enabled()) {
            System.nanoTime();
            synchronized (EarlyTraceEvent.sLock) {
                if (EarlyTraceEvent.enabled()) {
                    throw null;
                }
            }
        }
        if (TraceEvent.sEnabled) {
            N.MHopMqLX("StrictModeContext", hashCode);
        }
        this.mThreadPolicy = threadPolicy;
        this.mVmPolicy = vmPolicy;
    }
}
