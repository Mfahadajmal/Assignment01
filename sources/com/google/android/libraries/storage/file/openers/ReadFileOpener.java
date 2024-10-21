package com.google.android.libraries.storage.file.openers;

import android.net.Uri;
import com.google.android.libraries.storage.file.Opener;
import com.google.android.libraries.storage.file.common.FileConvertible;
import com.google.android.libraries.storage.file.common.ReleasableResource;
import com.google.android.libraries.storage.file.common.UnsupportedFileStorageOperation;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReadFileOpener implements Opener {
    public boolean shortCircuit = false;

    static {
        new AtomicInteger();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.google.android.libraries.storage.file.spi.Backend, java.lang.Object] */
    @Override // com.google.android.libraries.storage.file.Opener
    public final /* bridge */ /* synthetic */ Object open$ar$class_merging$a9b907d0_0$ar$class_merging(WorkQueue workQueue) {
        if (this.shortCircuit) {
            if (workQueue.WorkQueue$ar$blockingTasksInBuffer.isEmpty()) {
                return workQueue.WorkQueue$ar$buffer.toFile((Uri) workQueue.WorkQueue$ar$consumerIndex);
            }
            throw new UnsupportedFileStorageOperation("Short circuit would skip transforms.");
        }
        ReleasableResource releasableResource = new ReleasableResource(ReadStreamOpener.open$ar$ds$ar$class_merging$ar$class_merging(workQueue), 0);
        try {
            Object obj = releasableResource.ReleasableResource$ar$resource;
            if (obj instanceof FileConvertible) {
                File file = ((FileConvertible) obj).toFile();
                releasableResource.close();
                return file;
            }
            throw new IOException("Not convertible and fallback to pipe is disabled.");
        } catch (Throwable th) {
            try {
                releasableResource.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
