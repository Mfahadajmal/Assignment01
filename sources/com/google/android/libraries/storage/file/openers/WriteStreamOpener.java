package com.google.android.libraries.storage.file.openers;

import android.net.Uri;
import com.google.android.libraries.storage.file.Opener;
import com.google.mlkit.logging.schema.ImageInfo;
import java.io.OutputStream;
import java.util.List;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WriteStreamOpener implements Opener {
    public ImageInfo.Builder[] behaviors$ar$class_merging$ar$class_merging$ar$class_merging;

    /* JADX WARN: Type inference failed for: r1v0, types: [com.google.android.libraries.storage.file.spi.Backend, java.lang.Object] */
    @Override // com.google.android.libraries.storage.file.Opener
    public final /* bridge */ /* synthetic */ Object open$ar$class_merging$a9b907d0_0$ar$class_merging(WorkQueue workQueue) {
        List chainTransformsForWrite = workQueue.chainTransformsForWrite(workQueue.WorkQueue$ar$buffer.openForWrite((Uri) workQueue.WorkQueue$ar$consumerIndex));
        ImageInfo.Builder[] builderArr = this.behaviors$ar$class_merging$ar$class_merging$ar$class_merging;
        if (builderArr != null) {
            builderArr[0].forOutputChain(chainTransformsForWrite);
        }
        return (OutputStream) chainTransformsForWrite.get(0);
    }
}
