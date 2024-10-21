package com.google.common.io;

import com.google.common.flogger.context.ContextDataProvider;
import java.nio.charset.Charset;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ByteSource$AsCharSource extends ContextDataProvider {
    public final Charset charset;
    public final /* synthetic */ ContextDataProvider this$0$ar$class_merging$412157e4_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    public ByteSource$AsCharSource(ContextDataProvider contextDataProvider, Charset charset) {
        this.this$0$ar$class_merging$412157e4_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = contextDataProvider;
        charset.getClass();
        this.charset = charset;
    }

    public final String toString() {
        Charset charset = this.charset;
        return this.this$0$ar$class_merging$412157e4_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.toString() + ".asCharSource(" + charset.toString() + ")";
    }
}
