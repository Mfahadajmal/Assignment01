package com.google.android.libraries.storage.file;

import com.google.android.libraries.performance.primes.metrics.jank.DisplayStats;
import com.google.android.libraries.storage.file.common.internal.ForwardingOutputStream;
import com.google.android.libraries.storage.file.spi.Monitor;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MonitorOutputStream extends ForwardingOutputStream {
    private final List outputMonitors;

    public MonitorOutputStream(OutputStream outputStream, List list) {
        super(outputStream);
        this.outputMonitors = list;
        DisplayStats.checkArgument(true, "Output was null", new Object[0]);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Iterator it = this.outputMonitors.iterator();
        while (it.hasNext()) {
            try {
                ((Monitor.OutputMonitor) it.next()).close();
            } catch (Throwable unused) {
            }
        }
        super.close();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public final void write(int i) {
        this.out.write(i);
        Iterator it = this.outputMonitors.iterator();
        while (it.hasNext()) {
            ((Monitor.OutputMonitor) it.next()).bytesWritten$ar$ds();
        }
    }

    @Override // com.google.android.libraries.storage.file.common.internal.ForwardingOutputStream, java.io.FilterOutputStream, java.io.OutputStream
    public final void write(byte[] bArr) {
        this.out.write(bArr);
        for (Monitor.OutputMonitor outputMonitor : this.outputMonitors) {
            int length = bArr.length;
            outputMonitor.bytesWritten$ar$ds();
        }
    }

    @Override // com.google.android.libraries.storage.file.common.internal.ForwardingOutputStream, java.io.FilterOutputStream, java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        this.out.write(bArr, i, i2);
        Iterator it = this.outputMonitors.iterator();
        while (it.hasNext()) {
            ((Monitor.OutputMonitor) it.next()).bytesWritten$ar$ds();
        }
    }
}
