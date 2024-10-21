package com.google.android.libraries.storage.file.spi;

import java.io.Closeable;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Monitor {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface InputMonitor extends Closeable {
        void bytesRead$ar$ds();

        @Override // java.io.Closeable, java.lang.AutoCloseable
        void close();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OutputMonitor extends Closeable {
        void bytesWritten$ar$ds();

        @Override // java.io.Closeable, java.lang.AutoCloseable
        void close();
    }

    InputMonitor monitorRead$ar$ds();

    OutputMonitor monitorWrite$ar$ds();
}
