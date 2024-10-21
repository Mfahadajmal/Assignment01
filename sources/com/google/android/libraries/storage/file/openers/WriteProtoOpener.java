package com.google.android.libraries.storage.file.openers;

import android.net.Uri;
import android.os.Process;
import com.google.android.libraries.storage.file.Opener;
import com.google.mlkit.logging.schema.ImageInfo;
import com.google.protobuf.MessageLite;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WriteProtoOpener implements Opener {
    public ImageInfo.Builder[] behaviors$ar$class_merging$ar$class_merging$ar$class_merging;
    private final MessageLite proto;

    public WriteProtoOpener(MessageLite messageLite) {
        this.proto = messageLite;
    }

    /* JADX WARN: Type inference failed for: r10v3, types: [com.google.android.libraries.storage.file.spi.Backend, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.libraries.storage.file.spi.Backend, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v6, types: [com.google.android.libraries.storage.file.spi.Backend, java.lang.Object] */
    @Override // com.google.android.libraries.storage.file.Opener
    public final /* bridge */ /* synthetic */ Object open$ar$class_merging$a9b907d0_0$ar$class_merging(WorkQueue workQueue) {
        String str = ".mobstore_tmp-" + Process.myPid() + "-" + Thread.currentThread().getId() + "-" + System.currentTimeMillis() + "-" + ScratchFile.SCRATCH_COUNTER.getAndIncrement();
        Uri uri = (Uri) workQueue.WorkQueue$ar$consumerIndex;
        Uri build = uri.buildUpon().path(String.valueOf(uri.getPath()).concat(str)).build();
        List chainTransformsForWrite = workQueue.chainTransformsForWrite(workQueue.WorkQueue$ar$buffer.openForWrite(build));
        ImageInfo.Builder[] builderArr = this.behaviors$ar$class_merging$ar$class_merging$ar$class_merging;
        if (builderArr != null) {
            builderArr[0].forOutputChain(chainTransformsForWrite);
        }
        try {
            OutputStream outputStream = (OutputStream) chainTransformsForWrite.get(0);
            try {
                this.proto.writeTo(outputStream);
                ImageInfo.Builder[] builderArr2 = this.behaviors$ar$class_merging$ar$class_merging$ar$class_merging;
                if (builderArr2 != null) {
                    builderArr2[0].commit();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                workQueue.WorkQueue$ar$buffer.rename(build, (Uri) workQueue.WorkQueue$ar$consumerIndex);
                return null;
            } finally {
            }
        } catch (Exception e) {
            try {
                workQueue.WorkQueue$ar$buffer.deleteFile(build);
            } catch (FileNotFoundException unused) {
            }
            if (e instanceof IOException) {
                throw ((IOException) e);
            }
            throw new IOException(e);
        }
    }
}
