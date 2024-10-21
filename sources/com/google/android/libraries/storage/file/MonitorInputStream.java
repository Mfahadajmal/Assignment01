package com.google.android.libraries.storage.file;

import com.google.android.libraries.performance.primes.metrics.jank.DisplayStats;
import com.google.android.libraries.storage.file.common.internal.ForwardingInputStream;
import com.google.android.libraries.storage.file.spi.Monitor;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MonitorInputStream extends ForwardingInputStream {
    private final List inputMonitors;

    public MonitorInputStream(InputStream inputStream, List list) {
        super(inputStream);
        this.inputMonitors = list;
        DisplayStats.checkArgument(true, "Input was null", new Object[0]);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Iterator it = this.inputMonitors.iterator();
        while (it.hasNext()) {
            try {
                ((Monitor.InputMonitor) it.next()).close();
            } catch (Throwable unused) {
            }
        }
        super.close();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() {
        int read = this.in.read();
        if (read != -1) {
            Iterator it = this.inputMonitors.iterator();
            while (it.hasNext()) {
                ((Monitor.InputMonitor) it.next()).bytesRead$ar$ds();
            }
        }
        return read;
    }

    @Override // com.google.android.libraries.storage.file.common.internal.ForwardingInputStream, java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr) {
        int read = this.in.read(bArr);
        if (read != -1) {
            Iterator it = this.inputMonitors.iterator();
            while (it.hasNext()) {
                ((Monitor.InputMonitor) it.next()).bytesRead$ar$ds();
            }
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        int read = this.in.read(bArr, i, i2);
        if (read != -1) {
            Iterator it = this.inputMonitors.iterator();
            while (it.hasNext()) {
                ((Monitor.InputMonitor) it.next()).bytesRead$ar$ds();
            }
        }
        return read;
    }
}
