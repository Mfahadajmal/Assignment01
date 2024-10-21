package com.google.common.flogger.backend.android;

import android.os.Build;
import android.util.Log;
import com.google.common.flogger.backend.LogData;
import com.google.common.flogger.backend.LogSiteFormatter;
import com.google.common.flogger.backend.LogSiteFormatters;
import com.google.common.flogger.backend.LoggerBackend;
import com.google.common.flogger.backend.android.SimpleAndroidLoggerBackend;
import com.google.mlkit.common.model.RemoteModelManager;
import j$.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ProxyAndroidLoggerBackend extends AbstractAndroidBackend {
    public static final AtomicReference backendFactory;
    private static final AtomicLong bufferSize;
    static final boolean isDeveloperDevice;
    static final boolean isEmulator;
    static final boolean isRobolectric;
    private static final ConcurrentLinkedQueue logBuffer;
    public volatile LoggerBackend backend;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class LazyProxyQueueHolder {
        static final ConcurrentLinkedQueue backendlessProxyQueue = new ConcurrentLinkedQueue();
    }

    static {
        boolean z;
        boolean z2;
        boolean z3 = false;
        if (Build.FINGERPRINT != null && !"robolectric".equals(Build.FINGERPRINT)) {
            z = false;
        } else {
            z = true;
        }
        isRobolectric = z;
        if (!"goldfish".equals(Build.HARDWARE) && !"ranchu".equals(Build.HARDWARE)) {
            z2 = false;
        } else {
            z2 = true;
        }
        isEmulator = z2;
        if ("eng".equals(Build.TYPE) || "userdebug".equals(Build.TYPE)) {
            z3 = true;
        }
        isDeveloperDevice = z3;
        backendFactory = new AtomicReference();
        bufferSize = new AtomicLong();
        logBuffer = new ConcurrentLinkedQueue();
    }

    public ProxyAndroidLoggerBackend(String str) {
        super(str);
        if (!isRobolectric && !isEmulator) {
            if (isDeveloperDevice) {
                SimpleAndroidLoggerBackend.Factory factory = SimpleAndroidLoggerBackend.SINGLETON_DEFAULT_FACTORY;
                String str2 = factory.prefix;
                this.backend = new SimpleAndroidLoggerBackend.Factory(factory.logSiteFormatter, Level.OFF, factory.keysToIgnore, factory.metadataHandler).create(getLoggerName());
                return;
            }
            this.backend = null;
            return;
        }
        this.backend = new AndroidBackendFactory() { // from class: com.google.common.flogger.backend.android.AlwaysLogBackend$Factory
            private final LogSiteFormatter logSiteFormatter;
            private final String prefix;

            {
                LogSiteFormatters logSiteFormatters = LogSiteFormatters.NO_OP;
                this.prefix = "";
                this.logSiteFormatter = logSiteFormatters;
            }

            @Override // com.google.common.flogger.backend.android.AndroidBackendFactory
            public final LoggerBackend create(String str3) {
                return new SimpleAndroidLoggerBackend.LogSiteBasedBackend(str3, this.logSiteFormatter, Level.ALL, true, SimpleAndroidLoggerBackend.DEFAULT_KEYS_TO_IGNORE, SimpleAndroidLoggerBackend.DEFAULT_METADATA_HANDLER);
            }
        }.create(getLoggerName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.common.flogger.backend.LogData, java.lang.Object] */
    public static void flushBuffer() {
        while (true) {
            RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration = (RemoteModelManager.RemoteModelManagerRegistration) logBuffer.poll();
            if (remoteModelManagerRegistration != null) {
                bufferSize.getAndDecrement();
                ?? r1 = remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider;
                Object obj = remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass;
                if (!r1.wasForced()) {
                    if (((LoggerBackend) obj).isLoggable(r1.getLevel())) {
                    }
                }
                ((LoggerBackend) obj).log(r1);
            } else {
                return;
            }
        }
    }

    @Override // com.google.common.flogger.backend.android.AbstractAndroidBackend, com.google.common.flogger.backend.LoggerBackend
    public final void handleError(RuntimeException runtimeException, LogData logData) {
        if (this.backend != null) {
            this.backend.handleError(runtimeException, logData);
        } else {
            Log.e("ProxyAndroidLoggerBackend", "Internal logging error before configuration", runtimeException);
        }
    }

    @Override // com.google.common.flogger.backend.LoggerBackend
    public final boolean isLoggable(Level level) {
        if (this.backend != null && !this.backend.isLoggable(level)) {
            return false;
        }
        return true;
    }

    @Override // com.google.common.flogger.backend.LoggerBackend
    public final void log(LogData logData) {
        if (this.backend != null) {
            this.backend.log(logData);
            return;
        }
        if (bufferSize.incrementAndGet() > 20) {
            logBuffer.poll();
            Log.w("ProxyAndroidLoggerBackend", "Too many Flogger logs received before configuration. Dropping old logs.");
        }
        logBuffer.offer(new RemoteModelManager.RemoteModelManagerRegistration(this, logData));
        if (this.backend != null) {
            flushBuffer();
        }
    }
}
