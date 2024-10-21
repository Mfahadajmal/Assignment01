package com.google.android.libraries.performance.primes.metrics.crash;

import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.common.flogger.GoogleLogger;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* compiled from: PG */
/* loaded from: classes.dex */
final class CrashCounter {
    public final File dir;
    private File file;
    private final String id;
    public int value = 0;
    public boolean loaded = false;

    public CrashCounter(File file, String str) {
        this.dir = file;
        this.id = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int get() {
        if (!maybeLoad()) {
            return 0;
        }
        return this.value;
    }

    public final File getFile() {
        if (this.file == null) {
            this.file = new File(this.dir, String.valueOf(this.id).concat("_crash_counter_storage.pb"));
        }
        return this.file;
    }

    public final boolean maybeLoad() {
        FileInputStream fileInputStream;
        if (this.loaded) {
            return true;
        }
        try {
            fileInputStream = new FileInputStream(getFile());
        } catch (FileNotFoundException unused) {
            this.value = 0;
        } catch (IOException e) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/crash/CrashCounter", "maybeLoad", 102, "CrashCounter.java")).log("failed to read counter from disk.");
            return false;
        }
        try {
            this.value = ((CrashLoopStorage) GeneratedMessageLite.parseFrom(CrashLoopStorage.DEFAULT_INSTANCE, fileInputStream, ExtensionRegistryLite.getGeneratedRegistry())).crashCount_;
            fileInputStream.close();
            this.loaded = true;
            return true;
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
